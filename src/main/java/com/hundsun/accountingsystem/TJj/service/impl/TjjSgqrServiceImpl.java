package com.hundsun.accountingsystem.TJj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.TJj.service.TjjSgqrService;

@Service
public class TjjSgqrServiceImpl implements TjjSgqrService {

	@Autowired
	TQsbMapper tqsbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;
	
	@Override
	public void updateSgsqStatus(int id) {
		TQsb tqsb = tqsbMapper.selectTQsbById(id);
		tqsb.setExtendf("1");
		tqsbMapper.updateTQsbById(tqsb);
		
	}

	@Override
	public void insertSgqrIntoTqsb(TQsb sgqr) {
		tqsbMapper.insertTQsb(sgqr);		
	}

	@Override
	public void updateTccyeb(TCcyeb tccyeb) {
		/*
		 * 判断持仓表中有没有已有持仓，如果有则取出来进行同步更新，如果没有则新增一条
		 * */
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("zqnm", 4));
		assist.setRequires(Assist.andEq("zqdm", tccyeb.getZqdm()));
		assist.setRequires(Assist.andEq("ztbh", tccyeb.getZtbh()));
		List<TCcyeb> list = tccyebMapper.selectTCcyeb(assist);
		if(list!=null && list.size()>0) {
			TCcyeb tCcyebBefore = list.get(0);
			Integer cysl = tCcyebBefore.getCysl();
			Double zqcb = tCcyebBefore.getZqcb();
			tCcyebBefore.setCysl(cysl+tccyeb.getCysl());
			tCcyebBefore.setZqcb(zqcb+tccyeb.getZqcb());
			tCcyebBefore.setFsrq(tccyeb.getFsrq());
			tccyebMapper.updateTCcyebById(tCcyebBefore);
		}else {
			tccyebMapper.insertTCcyeb(tccyeb);
		}			
	}

	@Override
	public void updateYe(double jyfy, double yfjyfy, double zqqsk,int ztbh) throws Exception {
		//更新证券清算款
		Assist assist = new Assist();
		assist.setRequires(Assist.andEq("ztbh", ztbh));
		assist.setRequires(Assist.andEq("kjkmdm", "1133"));
		List<TCcyeb> zqList = tccyebMapper.selectTCcyeb(assist);
		if(zqList!=null && zqList.size()>0) {
			TCcyeb tCcyeb = zqList.get(0);
			double zqcb = tCcyeb.getZqcb()-zqqsk;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			throw new Exception("请先进行申购申请！");
		}
		
		//更新交易费用
		Assist assist1 = new Assist();
		assist1.setRequires(Assist.andEq("ztbh", ztbh));
		assist1.setRequires(Assist.andEq("kjkmdm", "6203"));
		List<TCcyeb> jyList = tccyebMapper.selectTCcyeb(assist1);
		if(jyList!=null && jyList.size()>0) {
			TCcyeb tCcyeb = jyList.get(0);
			double zqcb = tCcyeb.getZqcb()+jyfy;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			throw new Exception("请先配置交易费用科目余额！");
		}
		
		//更新应付交易费用
		Assist assist2 = new Assist();
		assist2.setRequires(Assist.andEq("ztbh", ztbh));
		assist2.setRequires(Assist.andEq("kjkmdm", "2003"));
		List<TCcyeb> yfList = tccyebMapper.selectTCcyeb(assist2);
		if(yfList!=null && yfList.size()>0) {
			TCcyeb tCcyeb = yfList.get(0);
			double zqcb = tCcyeb.getZqcb()+yfjyfy;
			tCcyeb.setZqcb(zqcb);
			tccyebMapper.updateTCcyebById(tCcyeb);
		}else {
			throw new Exception("请先配置应付交易费用科目余额！");
		}
	}


}
