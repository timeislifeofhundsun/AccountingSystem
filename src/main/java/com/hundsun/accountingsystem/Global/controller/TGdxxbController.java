package com.hundsun.accountingsystem.Global.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TGdxxbVO;
import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.service.TGdxxbService;

@RestController
public class TGdxxbController {
	
	@Autowired
	TGdxxbService tgdxxbServiceImpl;
	
	@PostMapping("/TGdxx")
	public String insertGdTest(String data) {
		TGdxxb tgdxxb = JSON.parseObject(data,TGdxxb.class);
		
		if(tgdxxb.getGddm().length()>10) {
			return "股东代码不能超过10位";
		}
		try {
			tgdxxbServiceImpl.insertGd(tgdxxb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@PutMapping("/TGdxx")
	public String updateGdxx(@RequestParam(value = "Gdxx",required = true) String data) {
		TGdxxb tgdxxb = JSON.parseObject(data,TGdxxb.class);
		tgdxxbServiceImpl.updateGd(tgdxxb);
		return String.valueOf(1);
	}
	
	@PostMapping("/deleteTGdxx")
	public String deleteGdTest(String gddm) {
		try {
			tgdxxbServiceImpl.deleteGdById(gddm);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
	@GetMapping("/TGdxx")
	public String findGdList(int page,int limit) {
		/*
		 * 1.查询出股东信息表中数据的总数量
		 * 2.根据分页信息查询出数据
		 * 3.layuiJson字符串转换
		 * */
		int count = tgdxxbServiceImpl.getCounts();
		List<TGdxxb> findList = tgdxxbServiceImpl.findGdListByPage(page,limit);
		TGdxxbVO layuiJson = new TGdxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}

	@GetMapping("/findByZtbh")
	public String findGdById(int ztbh) {
		List<TGdxxb> findList = tgdxxbServiceImpl.findgDgByZtbh(ztbh);
		TGdxxbVO layuiJson = new TGdxxbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(1);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
}
