package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZqxx;

public interface TZqxxService {
	public List<TZqxx> findByZqdm(String zqdm);

	public List<TZqxx> findAllTZqxx();

	public List<TZqxx> getTZqxxPage(int curr, int pagesize);
}
