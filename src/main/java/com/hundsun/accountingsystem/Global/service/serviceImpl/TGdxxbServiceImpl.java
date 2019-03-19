package com.hundsun.accountingsystem.Global.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			throw new Exception("删除失败");
		}

	}

}
