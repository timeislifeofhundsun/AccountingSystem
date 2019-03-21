package com.hundsun.accountingsystem.Global.controller;

import com.hundsun.accountingsystem.Global.bean.SysUser;
import com.hundsun.accountingsystem.Global.service.UserService;
import com.hundsun.accountingsystem.Global.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

/*
    //用户注册跳转
    @ResponseBody
    @GetMapping("/user")
    public String User_Register(HttpServletRequest request){

        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //登录名
        System.out.println("Username:" + securityContextImpl.getAuthentication().getName());
        //登录密码，未加密的
        System.out.println("Credentials:" + securityContextImpl.getAuthentication().getCredentials());

        WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl.getAuthentication().getDetails();
        //获得访问地址
        System.out.println("RemoteAddress" + details.getRemoteAddress());
        //获得sessionid
        System.out.println("SessionId" + details.getSessionId());
        //获得当前用户所拥有的权限
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println("Authority" + grantedAuthority.getAuthority());
        }
        return "111s";
    }

    //用户注册
    @PostMapping("/user")
    public String addUserRegisterInfo(SysUser sysUser){
        sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        return "login";
    }


*/


}
