package com.test.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 * Created by Doublestar on 2017/11/24 13:52).
 */
//前台视图控制器，可以不用，只需要使用html调用RESTful API
@Controller
public class IndexController {
    //创建颜色
    Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() throws ServletException, IOException {
        return "redirect:home/index";
    }
    /////////////////////////////


//    @RequestMapping(value = "/chat",method = RequestMethod.GET)
//    public String chat() throws ServletException, IOException {
//        return "chat";
//    }

    /////////////////////////////
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() throws ServletException, IOException {
        return "login";
    }
    @RequestMapping(value = "/authcode", method = RequestMethod.GET)
    public void getAuthCode(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(String.valueOf(IndexController.class));
        // 在内存中创建图象
        int width=60, height=20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics(); // 获取图形上下文

        Random random = new Random(); //生成随机类

        g.setColor(getRandColor(200,250)); // 设定背景色
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman",Font.PLAIN,18)); //设定字体
        String sRand=""; // 取随机产生的认证码(4位数字)
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand; // 将认证码显示到图象中
            g.setColor(new  Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand,13*i+6,16);
        }
        // 将认证码存入SESSION,key为"authcode"
        session.setAttribute("authcode",sRand);
        g.dispose();
        try {
            // 输出图象到页面
            ImageIO.write(image, "JPEG", response.getOutputStream());
            // 此处要加
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception e){
            //log4j日志输出
            logger.error("获取验证码错误！");
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register() throws ServletException, IOException {
        return "register";
    }
    @RequestMapping(value = "/forgetpassword",method = RequestMethod.GET)
    public String forgetpassword() throws ServletException, IOException {
        return "forgetpassword";
    }
}
