package com.hundsun.accountingsystem.Global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.VO.TGdxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;
import com.hundsun.accountingsystem.Global.service.TGdxxbService;

@Service
public class TGdxxbServiceImpl implements TGdxxbService {

	@Autowired
	TGdxxbMapper tgdxxbMapper;
	
	@Override
	public void insertGd(TGdxxb tgdxxb) throws Exception {
		TGdxxbExample example=new TGdxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andGddmEqualTo(tgdxxb.getGddm());
		List<TGdxxb> list = tgdxxbMapper.selectByExample(example);
		if(list.size()!=0) {
			throw new Exception("股东代码不能重复！！！");
		}else {
			tgdxxbMapper.insert(tgdxxb);
		}
	}

	@Override
	public boolean updateGd(TGdxxb tgdxxb) {
		TGdxxbExample example=new TGdxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andGddmEqualTo(tgdxxb.getGddm());
		criteria.andXwbhEqualTo(tgdxxb.getXwbh());
		criteria.andZtbhEqualTo(tgdxxb.getZtbh());
		tgdxxbMapper.updateByExample(tgdxxb, example);
		return true;
	}

	@Override
	public List<TGdxxb> findGdList() {
		return tgdxxbMapper.selectByExample(null);
	}

	@Override
	public TGdxxb findGdById(String gddm) {
		return tgdxxbMapper.selectByPrimaryKey(gddm);
	}

	@Override
	public void deleteGdById(String gddm) throws Exception {
		try {
			tgdxxbMapper.deleteByPrimaryKey(gddm);
		} catch(Exception e) {
			throw new Exception("未知原因");
		}

	}

	@Override
	public int getCounts() {
		return tgdxxbMapper.countByExample(null);
	}

	@Override
	public List<TGdxxb> findGdListByPage(int page, int limit) {
		TGdxxbParamPojo params = new TGdxxbParamPojo();
		params.setStart((page-1)*limit);
		params.setEnd(limit);
		return tgdxxbMapper.selectByLimit(params);
	}

	@Override
	public List<TGdxxb> findgDgByZtbh(int ztbh) {
		TGdxxbExample example = new TGdxxbExample();
		Criteria criteria = example.createCriteria();
		criteria.andZtbhEqualTo(ztbh);
		return tgdxxbMapper.selectByExample(example);
	}

}
