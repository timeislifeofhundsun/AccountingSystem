package com.hundsun.accountingsystem.Global.mapper;


import com.hundsun.accountingsystem.Global.bean.SysUser;
import org.springframework.context.annotation.Bean;

public interface UserMapper {

    SysUser findByUserName(String username);

    int updatePwd(SysUser sysUser);
    //int register(SysUser sysUser);

}
