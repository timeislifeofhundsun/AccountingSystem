/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/19  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.TJj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.THqbVO;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.service.THqbService;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */

@RestController
public class TjjHqxxController {
	
	@Autowired
	THqbService thqbServiceImpl;
	
	@GetMapping("/THqbxx")
	public String findJjhqList(int page,int limit) {
		List<THqb> findList = thqbServiceImpl.findHqxxByPage(page,limit);
		int count = thqbServiceImpl.selectCounts();
		THqbVO layuiJson = new THqbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	@GetMapping("/findTHqbxxByZqdm")
	public String findById(@RequestParam(value = "zqdm",required = true) int zqdm) {		
		List<THqb> findList = thqbServiceImpl.findByZqdm(zqdm);
		THqbVO layuiJson = new THqbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(1);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/findByDate")
	public String findByDate(@RequestParam(value = "date",required = true) String date) {		
		List<THqb> findList = thqbServiceImpl.findByDate(date);
		int count = thqbServiceImpl.selectCounts();
		THqbVO layuiJson = new THqbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@PutMapping("/THqxx")
	public String updateHqxx(@RequestParam(value = "Hqxx",required = true) String data) {
		THqb thqb = JSON.parseObject(data,THqb.class);
		thqb.setZqnm(4);
		thqbServiceImpl.updateHqxx(thqb);
		return String.valueOf(1);
	}
	
	
	@PostMapping("/THqxx")
	public String addHqxx(@RequestParam(value = "Hqxx",required = true) String data) {
		THqb thqb = JSON.parseObject(data,THqb.class);
		thqb.setZqnm(4);
		try {
			thqbServiceImpl.insertHqxx(thqb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
}
