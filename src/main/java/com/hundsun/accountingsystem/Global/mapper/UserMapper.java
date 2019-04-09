package com.hundsun.accountingsystem.Global.mapper;


import com.hundsun.accountingsystem.Global.bean.SysRoleUser;
import com.hundsun.accountingsystem.Global.bean.SysUser;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    SysUser findByUserName(String username);

    int updatePwd(SysUser sysUser);
    //int register(SysUser sysUser);

	List<SysUser> selectList();

	int getCounts();

	void insertUser(SysUser sysUser);

	void insertSysRoleUser(SysRoleUser role);

	void deleteUser(Integer uid);

	void deleteSysRoleUser(Integer uid);

}
