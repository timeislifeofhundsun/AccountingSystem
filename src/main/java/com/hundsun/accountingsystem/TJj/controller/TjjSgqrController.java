package com.hundsun.accountingsystem.TJj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.service.TJyflService;
import com.hundsun.accountingsystem.Global.service.TZqxxService;
import com.hundsun.accountingsystem.TJj.service.TjjSgqrService;

@RestController
public class TjjSgqrController {

	@Autowired
	TjjSgqrService tjjsgqrServiceImpl;
	
	@Autowired
	TZqxxService tzqxxServiceImpl;
	
	@Autowired
	TJyflService tjyflServiceImpl;
	
	
	@PostMapping("/TSgqr")
	public String confirmSgsq(@RequestParam(value = "Sgsq",required = true) String data) {
		/*
		 * 1.将data数据转换为bean对象
		 * 2.修改申请记录为已经被确认状态
		 * 3.计算申购确认后发生的各种费用,并封装到sgqr中
		 * 4.添加一条申购确认数据到清算表
		 * 5.同步更新持仓表
		 * */
		
		TQsb tqsb = JSON.parseObject(data,TQsb.class);
		try {
			tjjsgqrServiceImpl.updateSgsqStatus(tqsb.getId());
		}catch (Exception e) {
			return "更新申请数据状态出错";
		}	
		
		//计算各种费用封装到sgqr中，并插入到清算表中
		TQsb sgqr = counting(tqsb);
		try {
			tjjsgqrServiceImpl.insertSgqrIntoTqsb(sgqr);
		}catch (Exception e) {
			return "插入申购确认数据出错";
		}
		
		//同步更新持仓表
		try {
			updateCcyeb(sgqr);
		}catch (Exception e) {
			return "更新持仓表时出错";
		}
		
		//更新余额表（计算交易费用、计算证券清算款、应付交易费用（佣金））
		TJyfl tjyfl = tjyflServiceImpl.selectByPrimaryKey(4101);
		double jyfy = sgqr.getJsf()+sgqr.getZgf();
		double yfjyfy = sgqr.getAmount()*tjyfl.getYj()-jyfy;
		double zqqsk = sgqr.getAmount()+jyfy - yfjyfy;
		try {
			tjjsgqrServiceImpl.updateYe(jyfy,yfjyfy,zqqsk,sgqr.getZtbh());
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return String.valueOf(1);
	}
	
	//同步更新到持仓表
	private void updateCcyeb(TQsb sgqr) {
		TCcyeb tccyeb = new TCcyeb();
		tccyeb.setZqnm(4);
		tccyeb.setZqdm(sgqr.getZqcode());
		tccyeb.setZtbh(sgqr.getZtbh());
		tccyeb.setCysl(sgqr.getQuantity());
		tccyeb.setZqcb(sgqr.getAmount());
		tccyeb.setLjgz(0.0);
		tccyeb.setLjjx(0.0);
		tccyeb.setFsrq(sgqr.getRq());
		List<TZqxx> findByZqdm = tzqxxServiceImpl.findByZqdm(sgqr.getZqcode());
		if(findByZqdm!=null && findByZqdm.size()==1) {
			tccyeb.setExtenda(findByZqdm.get(0).getZqjg());
		}		
		tjjsgqrServiceImpl.updateTccyeb(tccyeb);
	}
	
	//计算费用并封装持仓表数据
	private TQsb counting(TQsb tqsb) {
		TQsb sgqr = new TQsb();
		sgqr.setZtbh(tqsb.getZtbh());
		sgqr.setRq(tqsb.getRq());
		sgqr.setZqcode(tqsb.getZqcode());
		sgqr.setBs("B");
		//根据证券代码去证券信息表中查出证券信息，根据fxfs判断基金类别，并补充业务类别
		List<TZqxx> list = tzqxxServiceImpl.findByZqdm(tqsb.getZqcode());
		if(list!=null && list.size()==1) {
			int flag = list.get(0).getFxfs();
			if(flag==1) {
				sgqr.setYwlb(4202);
			}else if(flag==2){
				sgqr.setYwlb(4102);
			}
		}
		sgqr.setAmount(tqsb.getAmount());
		sgqr.setQuantity(tqsb.getQuantity());
		sgqr.setExtendc("401");
		
		//计算证券清算款、经手费、证管费、应付交易费用（佣金）
		//先获取交易费率表（根据业务类别4101，代表的是基金申购，这里货币和非货币基金的申购费率是一样的，所以不仅型区分）
		TJyfl tjyfl = tjyflServiceImpl.selectByPrimaryKey(4101);
		double jsf = tqsb.getAmount()*tjyfl.getJsfl();
		double zgf = tqsb.getAmount()*tjyfl.getZg();
		double yfjyfy = tqsb.getAmount()*tjyfl.getYj()-(jsf+zgf);
		double zqqsk = tqsb.getAmount()+(jsf+zgf)-yfjyfy;
		sgqr.setJsf(jsf);
		sgqr.setZgf(zgf);
		sgqr.setYj(yfjyfy);
		sgqr.setZqqsk(zqqsk);
			
		return sgqr;
	}
}
