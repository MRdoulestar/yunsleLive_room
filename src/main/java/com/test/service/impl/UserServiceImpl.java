package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.entity.StatusMessage;
import com.test.entity.User;
import com.test.service.IMailService;
import com.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Doublestar on 2017/11/29 16:26).
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{

//    Logger log = Logger.getLogger(UserServiceImpl.super.toString());

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    IMailService mailService;

    @Override
    public boolean isLogin(String name){
        List<String> data = redisTemplate.opsForList().range("user", 0, -1);
        //查询缓存判断用户是否已经登录
        for(String r : data) {
            if(r.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StatusMessage userResistor(String name, String password, String repassword) {
        //校验值是否为空
        if(name == "" || password == "" || repassword == "") {
            return new StatusMessage(404, "error", "输入不能为空！");
        }
        //校验两次密码是否一致
        if(!password.equals(repassword)) {
            return new StatusMessage(404, "error", "两次密码不一致！");
        }
        try {
            //校验是否已存在用户
            User r = userDao.findByName(name);
            if(r != null) {
                return new StatusMessage(404, "error", "此用户名已经被注册！");
            }
            //添加用户数据
            userDao.addUser(new User(name,password));
            return new StatusMessage(200, "success", "注册成功!");
        }catch (Exception e) {
            return new StatusMessage(404, "error", "注册失败:"+e);
        }
    }

    @Override
    public StatusMessage userLogin(HttpSession session, String name, String password, String authcode) {
        try {
            User r = userDao.findByName(name);
//            //利用Redis，判断该用户是否已经登录
            if(isLogin(r.getName())) {
                return new StatusMessage(404, "error", "抱歉，该用户已经在其他地方登录！");
            }
            //校验验证码
            if(authcode != null && authcode == session.getAttribute("authcode")) {
                return new StatusMessage(404, "error", "验证码错误！");
            }
            // 用户名密码校验
            if(r != null && password.equals(r.getPassword())) {
                //登录成功，写入session，设置过期事件30分钟
                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("name", r.getName());
                //写入Redis
                redisTemplate.opsForList().rightPush("user", r.getName());
                return new StatusMessage(200, "success", "登录成功！");
            }else {
            //登录失败
                return new StatusMessage(404, "error", "登录失败，用户名或密码错误！");
            }
        }catch (Exception e){
            //后续日志输出
            System.err.println("数据库错误:"+e);
            //返回数据库错误信息
            return new StatusMessage(404, "error", "数据库错误:"+e);
        }
    }

    @Override
    public StatusMessage userLogoutAjax(HttpSession session, String name) {
        try {
            //删除Redis中用户登录状态
            redisTemplate.opsForList().remove("user", 1, name);
            //删除用户登录session信息
            session.removeAttribute("name");
            return new StatusMessage(200, "success", "注销成功！");
        } catch (Exception e) {
            return new StatusMessage(404, "error", "注销失败:"+e);
        }
    }

    @Override
    public boolean userLogout(HttpSession session) {
        try {
            //删除Redis中用户登录状态
            redisTemplate.opsForList().remove("user", 1, session.getAttribute("name"));
            //删除用户登录session信息
            session.removeAttribute("name");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StatusMessage forgetPassword(String name, String address) {
        try {
//            IMailService mailService = new MailServiceImpl();
            User r = userDao.findByName(name);
            String password = r.getPassword();
            if(mailService.sendMail(address, password)){
                return new StatusMessage(200, "success", "密码已经发送至邮箱！");
            }
            return new StatusMessage(404, "error", "邮件发送错误！");
        } catch (Exception e) {
            return new StatusMessage(404, "error", "发送失败，可能没有该用户或邮件发送错误："+e);
        }
    }
}
