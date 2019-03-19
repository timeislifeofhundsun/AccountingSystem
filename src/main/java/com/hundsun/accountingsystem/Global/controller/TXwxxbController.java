package com.hundsun.accountingsystem.Global.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hundsun.accountingsystem.Global.bean.TXwxxb;
import com.hundsun.accountingsystem.Global.service.TXwxxbService;

@Controller
public class TXwxxbController {

	@Autowired
	TXwxxbService txwxxbServiceImpl;
	
	public void insertTest() {
		TXwxxb txwxxb = new TXwxxb();
		txwxxb.setQsbh("10000");
		txwxxb.setQsName("中国证券");
		txwxxb.setXwbh("32562");
		txwxxb.setXwName("测试席位");
		try {
			txwxxbServiceImpl.insertXw(txwxxb);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("insert successful!!!");
	}
	
	public void updateTest() {
		TXwxxb txwxxb = new TXwxxb();
		txwxxb.setQsbh("10000");
		txwxxb.setQsName("中国证券");
		txwxxb.setXwbh("32562");
		txwxxb.setXwName("测试席位111");
		txwxxbServiceImpl.updateXw(txwxxb);
		System.out.println("update successful!!!");
	}
	
	public void deleteTest() {
		try {
			txwxxbServiceImpl.deleteXwById("32679");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("delete successful!!!");
	}
	
	public void findAllTest() {
		List<TXwxxb> xwList = txwxxbServiceImpl.findXwList();
		for (TXwxxb tXwxxb : xwList) {
			System.out.println(tXwxxb.toString());
		}
	}
	
	public void findByIdTest() {
		TXwxxb txwxxb=txwxxbServiceImpl.findXwById("32562");
		System.out.println(txwxxb.toString());
		
	}
}
