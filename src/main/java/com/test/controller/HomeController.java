package com.test.controller;

import com.test.entity.StatusMessage;
import com.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Doublestar on 2017/11/28 20:53).
 */
//后台控制器
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index(){
        return "chat";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String mq() throws ServletException, IOException {
        return "mq";
    }

    //用户注销模块非Ajax
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        userService.userLogout(session);
        return "redirect:/login";
    }

    //用户注销模块Ajax
    @RequestMapping(value = "/post/logout.do", method = RequestMethod.POST)
    public StatusMessage logoutAjax(HttpSession session, String name) {
        return userService.userLogoutAjax(session, name);
    }

}
