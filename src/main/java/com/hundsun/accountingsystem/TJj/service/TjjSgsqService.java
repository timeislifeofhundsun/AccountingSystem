package com.hundsun.accountingsystem.TJj.service;

import java.util.List;
import java.util.Map;

import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;

public interface TjjSgsqService {

	List<TQsb> selectByPage(int page, int limit);

	int getCounts();

	List<TQsb> selectByPageAndZtbh(int page, int limit, int ztbh);

	int getCountsByZtbh(int ztbh);

	List<TQsb> selectByPageAndDate(int page, int limit, String date);

	int getCountsByDate(String date);

	void insertIntoQsb(TQsb tqsb);

	Map<String, Double> getCcyebXx(int ztbh);

	void updateCcyeb(TCcyeb tyhck);

	boolean isHavePz(Integer id);

	void updateTqsb(TQsb tqsb);

	TQsb getTqsbByiD(Integer id);

	void deleteTqsbById(int id);

}
