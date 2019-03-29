package com.hundsun.accountingsystem.TJj.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TQsb;

public interface TjjShdzService {

	List<TQsb> selectByPage(int page, int limit);
	
	public int getCounts();

	List<TQsb> selectByPageAndZtbh(int page, int limit, int ztbh);

	int getCountsByZtbh(int ztbh);

	List<TQsb> selectByPageAndDate(int page, int limit, String date);

	int getCountsByDate(String date);

	void insertShxxAndUpdateCcye(TQsb tqsb) throws Exception;

}
