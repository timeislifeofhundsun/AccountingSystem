package com.hundsun.accountingsystem.TJj.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.TJj.VO.TCcyebSh;

public interface TjjShqrService {

	List<TCcyeb> selectByPage(int page, int limit);

	int getCountsByZqnm(int zqnm);

	List<TCcyeb> selectByPageAndZtbh(int page, int limit, int ztbh);

	int getCountsByZtbh(int ztbh);

	List<TCcyeb> selectByPageAndDate(int page, int limit, String date);

	public int getCountsByDate(String date);

	void confirmShxx(TCcyebSh tccyebSh) throws Exception;
}
