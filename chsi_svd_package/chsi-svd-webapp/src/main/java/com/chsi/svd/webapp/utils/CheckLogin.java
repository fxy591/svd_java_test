package com.chsi.svd.webapp.utils;

import javax.servlet.http.HttpSession;

public class CheckLogin {
    public static boolean check(HttpSession session){
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null){
            return true;
        } else {
            return false;
        }
    }
}
