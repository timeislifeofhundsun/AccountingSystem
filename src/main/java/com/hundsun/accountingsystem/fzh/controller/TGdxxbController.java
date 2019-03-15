package com.hundsun.accountingsystem.fzh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.fzh.service.TGdxxbService;

@Controller
public class TGdxxbController {
	
	@Autowired
	TGdxxbService tgdxxbServiceImpl;
	
	public void insertGdTest() {
		TGdxxb tgdxxb = new TGdxxb();
		tgdxxb.setGddm("B880580627");
		tgdxxb.setName("测试股东11");
		tgdxxb.setXwbh("32562");
		tgdxxb.setZtbh(10004);
		try {
			tgdxxbServiceImpl.insertGd(tgdxxb);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("insert successful!!!");
	}
	
	public void updateGdTest() {
		TGdxxb tgdxxb = new TGdxxb();
		tgdxxb.setGddm("B880580627");
		tgdxxb.setName("第一个股东账号");
		tgdxxb.setXwbh("32562");
		tgdxxb.setZtbh(10004);
		tgdxxbServiceImpl.updateGd(tgdxxb);
		System.out.println("update successful!!!");
	}
	
	public void deleteGdTest() {
		try {
			tgdxxbServiceImpl.deleteGdById("B00523155");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("delete successful!!!");
	}
	
	public void findGdAllTest() {
		List<TGdxxb> list = tgdxxbServiceImpl.findGdList();
		for (TGdxxb tGdxxb : list) {
			System.out.println(tGdxxb.toString());
		}
	}

	public void findGdByIdTest() {
		TGdxxb tgdxxb = tgdxxbServiceImpl.findGdById("B880580627");
		System.out.println(tgdxxb.toString());
	}
}
