package com.hundsun.accountingsystem.TJj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TCcyebVO;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.TJj.VO.TCcyebSh;
import com.hundsun.accountingsystem.TJj.service.TjjShqrService;

@RestController
public class TjjShqrController {

	@Autowired
	TjjShqrService tjjshqrServiceImpl;
	
	@GetMapping("/TShqr")
	public String findListFromTccyeb(int page,int limit) {
		/*
		 * 根据zqnm从持仓表里面得到所有账套的基金持仓，并展示到前台页面
		 * 后续可以根据账套编号或者日期进行持仓的检索
		 * */
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPage(page,limit);
		int count = tjjshqrServiceImpl.getCountsByZqnm(4);
		TCcyebVO layuiJson = new TCcyebVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/findTShqrByZtbh")
	public String findListFromTccyebByZtbh(int page,int limit,int ztbh) {
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPageAndZtbh(page,limit,ztbh);
		int count = tjjshqrServiceImpl.getCountsByZtbh(ztbh);
		TCcyebVO layuiJson = new TCcyebVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@PostMapping("/TShqr")
	public String findListFromTccyebByDate(int page,int limit,String date) {
		List<TCcyeb> findList = tjjshqrServiceImpl.selectByPageAndDate(page,limit,date);
		int count = tjjshqrServiceImpl.getCountsByDate(date);
		TCcyebVO layuiJson = new TCcyebVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@PostMapping("/TConfirmTSh")
	public String confirmTSh(@RequestParam(value = "shxx",required = true)String data) {
		TCcyebSh tccyeb = JSON.parseObject(data,TCcyebSh.class);
		/*
		 * 1.计算出各种费用到并插入到清算表中（证券清算款，需要得到收盘价），经手费，证管费，应付交易费用（佣金）
		 * 2.同步更新持仓表（计算估值增值、计算证券成本、计算持有数量）
		 * 3.同步更新余额表（计算证券清算款、计算交易费用、计算投资收益、应付交易费用）
		 * */
		try {
			tjjshqrServiceImpl.confirmShxx(tccyeb);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return String.valueOf(1);
	}
	
}
