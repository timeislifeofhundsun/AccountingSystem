package com.hundsun.accountingsystem.Global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class HomeController {

    //跳转到主页
    @RequestMapping("/")
    public String index(Map<String,Object> map){
        return "index";
    }


    //跳转到登陆
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }












    //权限测试
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    //必须有这个权限才可以使用
    //@Secured("ROLE_ADMIN")
    //@PreAuthorize("hasRole('ADMIN')")
    public String getUser(Model model) {
        System.out.println("ROLE_ADMIN权限");
        return "home";
    }



}
