package com.test.service.impl;

import com.test.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Doublestar on 2017/12/1 8:54).
 */
@Service
@Transactional
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendMail(String address, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("541794749@qq.com");
            message.setTo(address);
            message.setSubject("主题：忘记密码");
            message.setText("Hi,你好。这是你的密码:"+content+" 请注意密码安全，及时修改！");
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            System.err.println("sendMail错误:"+e);
            return false;
        }

    }
}
