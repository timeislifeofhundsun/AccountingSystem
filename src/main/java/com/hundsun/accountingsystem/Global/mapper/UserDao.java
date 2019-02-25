package com.hundsun.accountingsystem.Global.mapper;


import com.hundsun.accountingsystem.Global.bean.SysUser;

public interface UserDao {

    SysUser findByUserName(String username);

    int register(SysUser sysUser);

}
