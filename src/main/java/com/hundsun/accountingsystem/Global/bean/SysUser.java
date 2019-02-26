package com.hundsun.accountingsystem.Global.bean;

import lombok.Data;

import java.util.List;

@Data
public class SysUser {
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String compangy;
	private List<SysRole> roles;
}
