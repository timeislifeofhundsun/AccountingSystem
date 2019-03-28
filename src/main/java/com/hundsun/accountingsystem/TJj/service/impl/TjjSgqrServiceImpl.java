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

}
