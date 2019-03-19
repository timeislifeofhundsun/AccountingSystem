package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TXwxxb;

public interface TXwxxbService {
	
	public void insertXw(TXwxxb txwxxb) throws Exception;
	
	public boolean updateXw(TXwxxb txwxxb);
	
	public List<TXwxxb> findXwList();
	
	public TXwxxb findXwById(String xwbh);
	
	public void deleteXwById(String xwbh) throws Exception;
}
