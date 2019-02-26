package com.hundsun.accountingsystem.Global.controller;

import com.hundsun.accountingsystem.Global.bean.SysUser;
import com.hundsun.accountingsystem.Global.service.UserService;
import com.hundsun.accountingsystem.Global.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /*//用户注册跳转
    @GetMapping("/user")
    public String User_Register(){
        return "user_register";
    }

    //用户注册
    @PostMapping("/user")
    public String UserRegisterInfo(SysUser sysUser){
        sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        return "login";
    }*/


}
