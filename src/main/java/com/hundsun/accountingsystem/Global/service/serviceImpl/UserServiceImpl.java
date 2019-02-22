package com.hundsun.accountingsystem.Global.service.serviceImpl;

import com.hundsun.accountingsystem.Global.mapper.UserDao;
import com.hundsun.accountingsystem.Global.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


}
