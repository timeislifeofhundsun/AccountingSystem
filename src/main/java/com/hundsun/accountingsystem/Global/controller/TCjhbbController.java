package com.hundsun.accountingsystem.Global.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hundsun.accountingsystem.Global.service.TCjhbbService;

@Controller
public class TCjhbbController {

	@Autowired
	TCjhbbService tcjhbbServiceImpl;
	
	public void insertTest() {
		tcjhbbServiceImpl.insertCjhbbByRzqs(10004, new Date());
	}
}
