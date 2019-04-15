package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZqxx;

public interface TZqxxService {
	//根据证券代码查询证券信息
	public List<TZqxx> findByZqdm(String zqdm);
	//插入一条证券信息
	int insertSelective(TZqxx record);
	//查询全部的证券信息
	public List<TZqxx> findAllTZqxx();
	//分页查询证券信息
	public List<TZqxx> getTZqxxPage(int curr, int pagesize);
	//更新证券信息BY主键
	int updateByPrimaryKeySelective(TZqxx record);
	//删除证券信息BY主键
	int deleteByPrimaryKey(Integer zqnm);
	//通过证券类别获取证券信息
	public List<TZqxx> selectByZqlb(int zqlb);
	//分页搜索
	public List<TZqxx> searchTZqxxPage(int curr, int pagesize,String keyword);
    //搜索
    public List<TZqxx> searchTZqxx(String keyword);
}
