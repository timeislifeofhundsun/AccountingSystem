package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TGdxxb;

public interface TGdxxbService {
	public void insertGd(TGdxxb tgdxxb) throws Exception;
	
	public boolean updateGd(TGdxxb tgdxxb);
	
	public List<TGdxxb> findGdList();
	
	public TGdxxb findGdById(String gddm);
	
	public void deleteGdById(String gddm) throws Exception;
}
