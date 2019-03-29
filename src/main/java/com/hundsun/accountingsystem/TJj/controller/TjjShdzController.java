package com.hundsun.accountingsystem.TJj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.VO.TQsbVO;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.TJj.service.TjjShdzService;

@RestController
public class TjjShdzController {

	@Autowired
	TjjShdzService tjjshdzServiceImpl;
	
	@GetMapping("/TShxx")
	public String findShxxList(int page,int limit) {
		List<TQsb> findList = tjjshdzServiceImpl.selectByPage(page,limit);
		int count = tjjshdzServiceImpl.getCounts();
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/findShxxByZtbh")
	public String findListByZtbh(int page,int limit,int ztbh) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按账套进行区分）
		 * */
		List<TQsb> findList = tjjshdzServiceImpl.selectByPageAndZtbh(page,limit,ztbh);
		int count = tjjshdzServiceImpl.getCountsByZtbh(ztbh);
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@GetMapping("/TShxxByDate")
	public String findListByDate(int page,int limit,String date) {
		/*
		 * 根据业务类别为（4101和4201从清算表里面查出数据，这里按账套进行区分）
		 * */
		List<TQsb> findList = tjjshdzServiceImpl.selectByPageAndDate(page,limit,date);
		int count = tjjshdzServiceImpl.getCountsByDate(date);
		TQsbVO layuiJson = new TQsbVO();
		layuiJson.setCode(0);
	    layuiJson.setCount(count);
	    layuiJson.setMsg("");
	    layuiJson.setData(findList);
	    String jsonString = JSON.toJSONString(layuiJson);
		return jsonString;
	}
	
	@PostMapping("/TShxx")
	public String insertShxxAndUpdateCcye(@RequestParam(value = "Shxx",required = true) String data) {
		TQsb tqsb = JSON.parseObject(data,TQsb.class);
		try {
			tjjshdzServiceImpl.insertShxxAndUpdateCcye(tqsb);
		} catch (Exception e) {
			return e.getMessage();
		}
		return String.valueOf(1);
	}
}
