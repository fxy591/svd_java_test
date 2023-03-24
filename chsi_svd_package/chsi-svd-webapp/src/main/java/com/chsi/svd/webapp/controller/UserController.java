package com.chsi.svd.webapp.controller;

import com.chsi.svd.DTO.UserCondition;
import com.chsi.svd.DTO.UserDTO;
import com.chsi.svd.DTO.UserLogin;
import com.chsi.svd.pojo.PageBean;
import com.chsi.svd.pojo.User;
import com.chsi.svd.service.UserService;
import com.chsi.svd.webapp.utils.CheckLogin;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    private String code;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("index")
    public String index(HttpSession session){
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "user";
    }

    @RequestMapping(value = "query")
    @ResponseBody
    public PageBean<User> queryUserByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                        @RequestParam(defaultValue = "15")Integer pageSize){
        PageBean<User> pageBean = userService.selectByPage(currentPage, pageSize);
        return pageBean;
    }

    @RequestMapping(value = "query1")
    @ResponseBody
    public PageBean<User> queryUserByPageAndCondition(@RequestBody UserCondition userCondition){
        System.out.println(userCondition);
        User user = new User();
        user.setName(userCondition.getName());
        PageBean<User> pageBean = userService.selectByPageAndCondition(userCondition.getCurrentPage(), userCondition.getPageSize(), user);
        return pageBean;
        // return pageBean;
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public String add(@RequestBody User user){
        System.out.println("add: "+user);
        userService.add(user);
        return "success";
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public String update(@RequestBody User user){
        System.out.println("update: "+user);
        userService.update(user);
        return "success";
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(@RequestBody User user){
        System.out.println("del: "+user);
        userService.delete(user);
        return "success";
    }

    @RequestMapping(value = "deleteAll")
    @ResponseBody
    public String deleteAll(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        List<User> users = userDTO.getUsers();
        for (User user : users){
            System.out.println("delete: "+user);
            userService.delete(user);
        }
        return "success";
    }


    @RequestMapping("userinfo")
    public String userInfoIndex(HttpSession session){
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "userInfo";
    }

    @RequestMapping("id")
    @ResponseBody
    public User userInfo(){
        User user = userService.selectById("fhhows3z53y6bbq8");
        return user;
    }


    @RequestMapping("modify")
    public String modify(HttpSession session){
        // System.out.println();
        // System.out.println(session.getAttribute("user"));
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "modifyPass";
    }

    @RequestMapping("loginPhone")
    @ResponseBody
    public String loginPhone(@RequestBody UserLogin userLogin, HttpSession session){
        // System.out.println(userLogin.getPhone());
        User user = userService.select("phone", userLogin.getPhone());
        session.setAttribute("loginUser", user);
        if (user.getPassword().equals(userLogin.getPassword())){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("loginEmail")
    @ResponseBody
    public String loginEmail(@RequestBody UserLogin userLogin, HttpSession session){
        System.out.println(userLogin);
        User user = userService.select("email", userLogin.getEmail());
        if (user.getPassword().equals(userLogin.getPassword())){
            session.setAttribute("loginUser", user);
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("findEmail")
    @ResponseBody
    public String findEmail(@RequestBody UserLogin userLogin, HttpSession session){
        System.out.println(userLogin);// 收件人邮箱地址

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userLogin.getEmail());
        message.setFrom("1348619724@qq.com");
        message.setSubject("爬取新库");
        code = RandomStringUtils.random(6);
        message.setText("验证码为：" + code);
        // message.setText("blabla");
        mailSender.send(message);

        System.out.println("邮件已发送。");
        return "success";
    }

    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(@RequestBody UserLogin userLogin, HttpSession session){
        System.out.println(userLogin);// 收件人邮箱地址

        if (userLogin.getCode().equals(code)){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("loginOff")
    @ResponseBody
    public String loginOff(HttpSession session){
        System.out.println(session.getAttribute("loginUser"));
        session.removeAttribute("loginUser");
        System.out.println(session.getAttribute("loginUser"));
        return "ok";
    }

}
