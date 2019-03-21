package com.hundsun.accountingsystem.Global.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/TZtxx")
	public String InsertZtxx(String data) {
		TZtxxb tztxxb=JSON.parseObject(data,TZtxxb.class);		
		try {
			tztxxbServiceImpl.insertZt(tztxxb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@PutMapping("/TZtxx")
	public String updateZtxx(@RequestParam(value = "Ztxx",required = true) String data) {
		TZtxxb tztxxb=JSON.parseObject(data,TZtxxb.class);
		try {
			tztxxbServiceImpl.updateZt(tztxxb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	@GetMapping("/TZtxx")
	public String findList(int page,int limit) {
		List<TZtxxb> findZtList = tztxxbServiceImpl.findZtListByPage(page,limit);
		int count=tztxxbServiceImpl.getCounts();
		TZtxxbVO layuiJson = new TZtxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findZtList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	@GetMapping("/findTZtxxByZtbh")
	public String findById(@RequestParam(value = "ztbh",required = true) int ztbh) {
		TZtxxb tztxxb=tztxxbServiceImpl.findZtById(ztbh);
		List<TZtxxb> findZtList = new ArrayList<TZtxxb>();
		findZtList.add(tztxxb);
		TZtxxbVO layuiJson = new TZtxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(findZtList.size());
	    layuiJson.setMsg("");
	    layuiJson.setData(findZtList);
		return JSON.toJSONString(layuiJson);
	}
	@PostMapping("/deleteTZtxx")
	public String deleteZtxx(int ztbh) {
		try {
			tztxxbServiceImpl.deleteZtById(ztbh);
		} catch (Exception e) {			
			return e.getMessage();
		}
		return String.valueOf(1);
	}
}
