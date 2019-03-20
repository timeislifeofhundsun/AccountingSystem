package com.hundsun.accountingsystem.Global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TZtxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import com.hundsun.accountingsystem.Global.service.TZtxxbService;

@Service
public class TZtxxbServiceImpl implements TZtxxbService {
	
	@Autowired
	TZtxxbMapper tztxxbMapper;

	@Override
	public void insertZt(TZtxxb tztxxb) throws Exception {
		
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
