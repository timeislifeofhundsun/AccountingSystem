package com.hundsun.accountingsystem.yang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
public class TempController {

    @GetMapping("/test")
    public String Test(){
        return "hello world";
    }

}
