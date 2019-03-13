package com.hundsun.accountingsystem.fzh.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TXwxxb;

public interface TxwxxbService {
	
	public void insertXw(TXwxxb txwxxb) throws Exception;
	
	public boolean updateXw(TXwxxb txwxxb);
	
	public List<TXwxxb> findXwList();
	
	public TXwxxb findXwById(String xwbh);
	
	public void deleteXwById(String xwbh) throws Exception;
}
