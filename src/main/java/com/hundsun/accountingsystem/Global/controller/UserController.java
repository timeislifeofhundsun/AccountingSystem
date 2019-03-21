package com.hundsun.accountingsystem.Global.controller;

import com.hundsun.accountingsystem.Global.bean.SysUser;
import com.hundsun.accountingsystem.Global.mapper.UserMapper;
import com.hundsun.accountingsystem.Global.service.UserService;
import com.hundsun.accountingsystem.Global.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  UserMapper userMapper;

  /*//用户注册跳转
  @GetMapping("/user")
  public String User_Register(){
      return "user_register";
  }

  //用户注册
  @PostMapping("/user")
  public String addUserRegisterInfo(SysUser sysUser){
      sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
      return "login";
  }*/
  @GetMapping("/unlock")
  @ResponseBody
  public String unLock(@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
    SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
    SysUser byUserName = userMapper.findByUserName(securityContextImpl.getAuthentication().getName());
    int i = 0;
    String MD5Password = MD5Util.encode(password);
    if (MD5Password.equals(byUserName.getPassword())) {
      i = 1;
    }
    return String.valueOf(i);
  }


}
