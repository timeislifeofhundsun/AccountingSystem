package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.SysUser;

public interface UserService {

	List<SysUser> selectList();

	int getCounts();

	void insertUser(SysUser sysUser) throws Exception;

	void deleteUser(int uid);

}
