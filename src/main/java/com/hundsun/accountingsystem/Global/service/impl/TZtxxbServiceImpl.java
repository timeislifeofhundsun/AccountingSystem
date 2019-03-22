package com.hundsun.accountingsystem.Global.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TZtxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;

@Service
public class TZtxxbServiceImpl implements TZtxxbService {
	
	@Autowired
	TZtxxbMapper tztxxbMapper;
	
	@Autowired
	TCcyebMapper tccyebMapper;

	@Override
	public void insertZt(TZtxxb tztxxb) throws Exception {
		/*
		 * 1.将账套信息存入t_ztxxb
		 * 2.获取账套信息中的初始金额和初始份额，分别对应到t_ccyeb的证券成本和持有数量
		 * 3.将会账套编号、计科目信息、发生日期、证券成本、持有数量存入t_ccyeb中
		 * */
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameEqualTo(tztxxb.getName());
		
		TZtxxbExample example1=new TZtxxbExample();
		Criteria criteria1=example1.createCriteria();
		criteria1.andJjdmEqualTo(tztxxb.getJjdm());
		
		List<TZtxxb> list = tztxxbMapper.selectByExample(example);
		List<TZtxxb> list1 = tztxxbMapper.selectByExample(example1);
		if(list.size()!=0) {
			throw new Exception("账套名称不能重复");
		}
		if(list1.size()!=0) {
			throw new Exception("基金代码不能重复");
		}
		else
		{
			int maxZtbh = tztxxbMapper.getMaxZtbh();
			List<TCcyeb> tccyebList = new ArrayList<TCcyeb>();
			TCcyeb tccyeb = new TCcyeb();
			tccyeb.setZtbh(maxZtbh+1);
			tccyeb.setKjkmdm("100201");
			tccyeb.setFsrq(tztxxb.getCreatedate());
			tccyeb.setZqcb(tztxxb.getMoney());
			tccyeb.setExtenda("银行存款_活期存款");			
			
			tccyebList.add(tccyeb);
			
			TCcyeb tccyeb1 = new TCcyeb();
			tccyeb1.setZtbh(maxZtbh+1);
			tccyeb1.setKjkmdm("400101");
			tccyeb1.setFsrq(tztxxb.getCreatedate());
			tccyeb1.setCysl(tztxxb.getNumber());
			tccyeb1.setZqcb(tztxxb.getMoney());
			tccyeb1.setExtenda("实收基金_已实现");
			
			tccyebList.add(tccyeb1);
			tccyebMapper.insertTCcyebByBatch(tccyebList);
			tztxxbMapper.insert(tztxxb);
		}
		
	}

	@Override
	public boolean updateZt(TZtxxb tztxxb) throws Exception {
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andZtbhEqualTo(tztxxb.getZtbh());	
		
		TZtxxbExample example1=new TZtxxbExample();
		Criteria criteria1=example1.createCriteria();
		criteria1.andNameEqualTo(tztxxb.getName());
		TZtxxb bean = tztxxbMapper.selectByPrimaryKey(tztxxb.getZtbh());
		if(tztxxb.getName().equals(bean.getName())) {
			tztxxbMapper.updateByExample(tztxxb, example);
		}else {
			List<TZtxxb> list = tztxxbMapper.selectByExample(example1);
			if(list.size()!=0) {
				throw new Exception("账套名称重复了");
			}
			tztxxbMapper.updateByExample(tztxxb, example);
		}	
		return true;
	}

	@Override
	public List<TZtxxb> findZtList() {		
		return tztxxbMapper.selectByExample(null);
	}

	@Override
	public TZtxxb findZtById(int ztbh) {
		return tztxxbMapper.selectByPrimaryKey(ztbh);
	}

	@Override
	public void deleteZtById(int ztbh) throws Exception {
		try {
			tztxxbMapper.deleteByPrimaryKey(ztbh);
		} catch (Exception e) {
			throw new Exception("删除失败！");
		}		
		
	}

	@Override
	public List<TZtxxb> findZtListByPage(int page, int limit) {
		int start = (page-1)*limit;
		int end = limit;
		TZtxxbParamPojo params = new TZtxxbParamPojo();
		params.setStart(start);
		params.setEnd(end);
		return tztxxbMapper.selectByLimit(params);
	}

	@Override
	public int getCounts() {		
		return tztxxbMapper.countByExample(null);
	}
	
	
}
