package com.hundsun.accountingsystem.Global.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZtxxb;

public interface TZtxxbService {

	public void insertZt(TZtxxb tztxxb) throws Exception;
	
	public boolean updateZt(TZtxxb tztxxb);
	
	public List<TZtxxb> findZtList(int page,int limit);
	
	public TZtxxb findZtById(int ztbh);
	
	public void deleteZtById(int ztbh) throws Exception;
	
}
