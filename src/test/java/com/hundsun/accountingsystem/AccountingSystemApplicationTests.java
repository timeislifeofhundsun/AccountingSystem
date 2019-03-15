package com.hundsun.accountingsystem;



import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TXwxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.controller.TCjhbbController;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.mapper.TXwxxbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.fzh.controller.TZtxxbController;
import com.hundsun.accountingsystem.fzh.controller.TGdxxbController;
import com.hundsun.accountingsystem.fzh.controller.TXwxxbController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSystemApplicationTests {

	@Autowired
	TXwxxbMapper txwxxbMapper;
	@Autowired
	TZtxxbMapper tztxxbMapper;
	@Autowired
	TGdxxbMapper tgdxxbMapper;
	
	@Autowired
	TZtxxbController testController;
	
	@Autowired
	TXwxxbController txwxxbController;
	
	@Autowired
	TGdxxbController tgdxxbController;
	
	@Autowired
	TCjhbbController tcjhbbController;
	
	@Autowired
	TGhkMapper tghkMapper;
	
	
	
	@Test
	public void contextLoads() {
		
	}

	//测试股东信息表、账套信息表、席位信息表的插入
	@Test
	public void insertTest() {
		TXwxxb txwxxb=new TXwxxb();
		txwxxb.setQsbh("1003");
		txwxxb.setQsName("中正券商13");
		txwxxb.setXwbh("32677");
		txwxxb.setXwName("测试席位13");
		txwxxbMapper.insert(txwxxb);
		
		TZtxxb tztxxb=new TZtxxb();
		tztxxb.setCreatedate(new Date());
		tztxxb.setCreater(32156);
		tztxxb.setEnddate(new Date());
		tztxxb.setJjdm(600579);
		tztxxb.setMoney(100000000.0);
		tztxxb.setNumber(100000000);
		tztxxb.setZtbh(10002);
		tztxxbMapper.insert(tztxxb);
		
		TGdxxb tgdxxb=new TGdxxb();
		tgdxxb.setGddm("B00523159");
		tgdxxb.setName("测试股东");
		tgdxxb.setXwbh("32677");
		tgdxxb.setZtbh(10000);
		
		tgdxxbMapper.insert(tgdxxb);
		
	}
	
	
	@Test
	public void testZt() {
		//testController.InsertTest();
		//testController.updateTest();
		//testController.findTest();
		//testController.findByIdTest();
		//testController.deleteTest();
	}
	@Test
	public void testXw() {
		//txwxxbController.insertTest();
		//txwxxbController.updateTest();
		//txwxxbController.deleteTest();
		//txwxxbController.findAllTest();
		txwxxbController.findByIdTest();
	}
	
	@Test
	public void testGd() {
		tgdxxbController.insertGdTest();
		//tgdxxbController.updateGdTest();
		//tgdxxbController.deleteGdTest();
		//tgdxxbController.findGdAllTest();
		//tgdxxbController.findGdByIdTest();
	}
	
	@Test
	public void testcjhb() {
		tcjhbbController.insertTest();
	}
}
