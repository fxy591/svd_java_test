package com.chsi.svd.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(){
        return "login";
    }


    @RequestMapping("test")
    public String t(){
        return "test";
    }
}

