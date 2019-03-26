package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZqxx;

public interface TZqxxService {
	public List<TZqxx> findByZqdm(String zqdm);

	int insertSelective(TZqxx record);

	public List<TZqxx> findAllTZqxx();

	public List<TZqxx> getTZqxxPage(int curr, int pagesize);

	int updateByPrimaryKeySelective(TZqxx record);

	int deleteByPrimaryKey(Integer zqnm);

	public List<TZqxx> selectByZqlb(int zqlb);
}
