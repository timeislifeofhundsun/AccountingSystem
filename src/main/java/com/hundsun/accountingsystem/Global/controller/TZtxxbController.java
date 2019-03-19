package com.hundsun.accountingsystem.Global.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;


@Controller
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
	
	
	public void updateTest() {
		TZtxxb tztxxb = new TZtxxb();
		tztxxb.setMoney(100000000.00);
		tztxxb.setNumber(10000000);
		tztxxb.setZtbh(10005);
		tztxxb.setName("测试账套666");
		tztxxb.setJjdm(654712);
		tztxxbServiceImpl.updateZt(tztxxb);
		System.out.println("update successful!!!");
	}
	
	public void findTest() {
		List<TZtxxb> findZtList = tztxxbServiceImpl.findZtList();
		for (TZtxxb tZtxxb : findZtList) {
			System.out.println(tZtxxb.toString());
		}
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