package com.hundsun.accountingsystem.TJj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.accountingsystem.TJj.service.TjjFhService;

@RestController
public class TjjFhController {

	@Autowired
	TjjFhService tjjfhServiceImpl;
	
	@PostMapping("/Tjjfh")
	public String jjGz(String date) {
		
		try {
			tjjfhServiceImpl.countJjfh(date);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
}
