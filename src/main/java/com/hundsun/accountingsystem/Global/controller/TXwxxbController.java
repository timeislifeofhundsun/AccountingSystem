package com.hundsun.accountingsystem.Global.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TXwxxbVO;
import com.hundsun.accountingsystem.Global.bean.TXwxxb;
import com.hundsun.accountingsystem.Global.service.TXwxxbService;

@RestController
public class TXwxxbController {

	@Autowired
	TXwxxbService txwxxbServiceImpl;
	
	@PostMapping("/TXwxx")
	public String insertXwxx(String data) {
		TXwxxb txwxxb = JSON.parseObject(data,TXwxxb.class);
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if(!pattern.matcher(txwxxb.getXwbh()).matches()) {
			return "席位编号只能为数字类型";
		}
		if(txwxxb.getXwbh().length()>10) {
			return "席位编号不能大于10位";
		}
		try {
			txwxxbServiceImpl.insertXw(txwxxb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@PutMapping("/TXwxx")
	public String updateXwxx(@RequestParam(value = "Xwxx",required = true) String data) {
		TXwxxb txwxxb = JSON.parseObject(data,TXwxxb.class);
		txwxxbServiceImpl.updateXw(txwxxb);
		return String.valueOf(1);
	}
	
	@PostMapping("/deleteTXwxx")
	public String deleteXwxx(String xwbh) {
		try {
			txwxxbServiceImpl.deleteXwById(xwbh);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@GetMapping("/TXwxx")
	public String findList(int page,int limit) {
		int count = txwxxbServiceImpl.getCounts();
		List<TXwxxb> findList = txwxxbServiceImpl.findListByPage(page,limit);
		TXwxxbVO layuiJson = new TXwxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/findTXwxxByXwbh")
	public String findById(String xwbh) {
		TXwxxb txwxxb=txwxxbServiceImpl.findXwById(xwbh);
		List<TXwxxb> findList = new ArrayList<TXwxxb>();
		findList.add(txwxxb);
		TXwxxbVO layuiJson = new TXwxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(1);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
		
	}
}
