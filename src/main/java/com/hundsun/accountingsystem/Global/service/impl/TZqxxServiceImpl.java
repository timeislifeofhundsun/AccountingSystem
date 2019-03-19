package com.hundsun.accountingsystem.Global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample.Criteria;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.service.TZqxxService;

@Service
public class TZqxxServiceImpl implements TZqxxService {

	@Autowired
	TZqxxMapper tzqxxMapper;
	
	@Override
	public List<TZqxx> findByZqdm(String zqdm) {
		TZqxxExample example=new TZqxxExample();
		Criteria criteria = example.createCriteria();
		criteria.andZqdmEqualTo(zqdm);
		List<TZqxx> selectByExample = tzqxxMapper.selectByExample(example);
		return selectByExample;
	}

}
