package com.test.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.test.entity.StatusMessage;
import com.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Doublestar on 2017/11/24 11:16).
 */
//用户数据控制器
@RestController
@RequestMapping(value="/post")
public class UserController {

    @Autowired
    private IUserService userService;

    //    用户登录功能
    @RequestMapping(value ="/login.do", method = RequestMethod.POST)
    public StatusMessage search(HttpSession session, String name, String password, String authcode) {
        return userService.userLogin(session, name, password,authcode);
    }

    //    用户注册模块
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public StatusMessage register(String name, String password, String repassword){
        return userService.userResistor(name, password, repassword);
    }

    //    用户忘记密码模块
    @RequestMapping(value = "forgetpassword.do", method = RequestMethod.POST)
    public StatusMessage forgetpassword(String name, String address) {
        return userService.forgetPassword(name, address);
    }

}
