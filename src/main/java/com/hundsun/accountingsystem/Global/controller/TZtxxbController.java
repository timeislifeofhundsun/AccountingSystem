package com.hundsun.accountingsystem.Global.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TZtxxbVO;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;


@RestController
public class TZtxxbController {
	
	@Autowired
	TZtxxbService tztxxbServiceImpl;

	public void InsertTest() {
		TZtxxb tztxxb = new TZtxxb();
		tztxxb.setMoney(100000000.00);
		tztxxb.setNumber(10000000);
		tztxxb.setName("测试账套11");
		tztxxb.setJjdm(654712);
		//System.out.println(tztxxbServiceImpl);
		try {
			tztxxbServiceImpl.insertZt(tztxxb);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("insert successful!!!");
	}
	
	@PutMapping("/TZtxx")
	public String updateZtxx(@RequestParam(value = "Ztxx",required = true) String data) {
		System.out.println(data);
		TZtxxb tztxxb=JSON.parseObject(data,TZtxxb.class);
		tztxxbServiceImpl.updateZt(tztxxb);
		System.out.println("update successful!!!");
		return String.valueOf(1);
	}
	@GetMapping("/TZtxx")
	public String findList() {
		List<TZtxxb> findZtList = tztxxbServiceImpl.findZtList();
		TZtxxbVO layuiJson = new TZtxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(findZtList.size());
	    layuiJson.setMsg("");
	    layuiJson.setData(findZtList);
	    String jsonString = JSON.toJSONString(layuiJson);
	    System.out.println(jsonString);
		return jsonString;
	}
	
	public void findByIdTest() {
		TZtxxb tztxxb=tztxxbServiceImpl.findZtById(10003);
		System.out.println(tztxxb.toString());
	}
	
	public void deleteTest() {
		try {
			tztxxbServiceImpl.deleteZtById(10003);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("delete successful!!!");
	}
}
