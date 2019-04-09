package com.hundsun.accountingsystem.Global.service.impl;

import com.hundsun.accountingsystem.Global.bean.SysRole;
import com.hundsun.accountingsystem.Global.bean.SysRoleUser;
import com.hundsun.accountingsystem.Global.bean.SysUser;
import com.hundsun.accountingsystem.Global.mapper.UserMapper;
import com.hundsun.accountingsystem.Global.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

	@Override
	public List<SysUser> selectList() {
		
		return userMapper.selectList();
	}

	@Override
	public int getCounts() {
		return userMapper.getCounts();
	}

	@Override
	public void insertUser(SysUser sysUser) throws Exception {
		SysUser findByUserName = userMapper.findByUserName(sysUser.getUsername());
		if(findByUserName!=null) {
			throw new Exception("用户名已经存在");
		}
		userMapper.insertUser(sysUser);
		int id = sysUser.getUid();
		SysRoleUser role = new SysRoleUser();
		role.setUserId(id);
		role.setRoleId(2);
		userMapper.insertSysRoleUser(role);
		
	}

	@Override
	public void deleteUser(int uid) {
		
		userMapper.deleteUser(uid);
		userMapper.deleteSysRoleUser(uid);
		
	}


}
