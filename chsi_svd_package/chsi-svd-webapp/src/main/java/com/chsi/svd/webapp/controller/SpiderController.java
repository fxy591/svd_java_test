package com.chsi.svd.webapp.controller;


import com.chsi.svd.webapp.utils.CheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("spider")
public class SpiderController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("index")
    public String index(HttpSession session){
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "spider";
    }


    @RequestMapping("new")
    @ResponseBody
    public String send(@RequestParam(defaultValue = "收到的内容为空") String url) throws MessagingException {
        System.out.println(url);
        String to = "19301145@bjtu.edu.cn"; // 收件人邮箱地址

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("1348619724@qq.com");
        message.setSubject("爬取新库");
        message.setText(url);
        // message.setText("blabla");
        mailSender.send(message);

        System.out.println("邮件已发送。");


        return "success";
    }
}
