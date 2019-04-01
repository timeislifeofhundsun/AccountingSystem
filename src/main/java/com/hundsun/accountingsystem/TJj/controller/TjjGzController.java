package com.hundsun.accountingsystem.TJj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.accountingsystem.TJj.service.TjjgzService;

@RestController
public class TjjGzController {

	@Autowired
	TjjgzService tjjgzServiceImpl;
	
	@PostMapping("/Tjjgz")
	public String countJjgz(String date) {
		
		try {
			tjjgzServiceImpl.countJjgz(date);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
}
