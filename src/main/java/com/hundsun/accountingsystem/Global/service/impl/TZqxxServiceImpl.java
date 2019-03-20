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

	@Override
	public int insertSelective(TZqxx record) {
		int i = tzqxxMapper.insertSelective(record);
		return i;
	}

	@Override
	public List<TZqxx> findAllTZqxx() {
		return tzqxxMapper.findAllTZqxx();
	}

	@Override
	public List<TZqxx> getTZqxxPage(int curr,int pagesize) {
      List<TZqxx> allTZqxx = tzqxxMapper.findAllTZqxx();
      int firstindex = (curr-1)*pagesize;
      int lastindex =  curr*pagesize;
      if (lastindex>allTZqxx.size()){
        return allTZqxx.subList(firstindex,allTZqxx.size());
      }
      if (firstindex>allTZqxx.size()){
        return null;
      }
      return allTZqxx.subList(firstindex,lastindex);
	}

	@Override
	public int updateByPrimaryKeySelective(TZqxx record) {
      int i = tzqxxMapper.updateByPrimaryKeySelective(record);
      return i;
	}

  @Override
  public int deleteByPrimaryKey(Integer zqnm) {
    int i = tzqxxMapper.deleteByPrimaryKey(zqnm);
    return i;
  }

}
