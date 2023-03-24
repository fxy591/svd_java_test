package com.chsi.svd.webapp.controller;

import com.chsi.svd.webapp.utils.CheckLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("history")
public class HistoryController {

    @RequestMapping("index")
    public String start(HttpSession session) {
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "history";
    }
}
