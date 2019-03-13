package com.hundsun.accountingsystem.fzh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TZtxxbMapper;
import com.hundsun.accountingsystem.fzh.service.TztxxbService;

@Service
public class TztxxbServiceImpl implements TztxxbService {
	
	@Autowired
	TZtxxbMapper tztxxbMapper;

	@Override
	public void insertZt(TZtxxb tztxxb) throws Exception {
		
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameEqualTo(tztxxb.getName());
		List<TZtxxb> list = tztxxbMapper.selectByExample(example);
		if(list.size()!=0) {
			throw new Exception("账套名称不能重复");
		}
		else
		{
			tztxxbMapper.insert(tztxxb);
		}
		
	}

	@Override
	public boolean updateZt(TZtxxb tztxxb) {
		TZtxxbExample example=new TZtxxbExample();
		Criteria criteria=example.createCriteria();
		criteria.andZtbhEqualTo(tztxxb.getZtbh());	
		tztxxbMapper.updateByExample(tztxxb, example);
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
	
	
}
