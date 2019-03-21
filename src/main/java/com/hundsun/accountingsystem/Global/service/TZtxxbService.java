package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZtxxb;

public interface TZtxxbService {

	public void insertZt(TZtxxb tztxxb) throws Exception;
	
	public boolean updateZt(TZtxxb tztxxb) throws Exception;
	
	public List<TZtxxb> findZtList();
	
	public TZtxxb findZtById(int ztbh);
	
	public void deleteZtById(int ztbh) throws Exception;
	
	public List<TZtxxb> findZtListByPage(int page,int limit);
	
	public int getCounts();
	
}
