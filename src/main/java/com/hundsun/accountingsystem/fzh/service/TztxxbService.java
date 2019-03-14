package com.hundsun.accountingsystem.fzh.service;

import java.util.List;

import com.hundsun.accountingsystem.Global.bean.TZtxxb;

public interface TztxxbService {

	public void insertZt(TZtxxb tztxxb) throws Exception;
	
	public boolean updateZt(TZtxxb tztxxb);
	
	public List<TZtxxb> findZtList();
	
	public TZtxxb findZtById(int ztbh);
	
	public void deleteZtById(int ztbh) throws Exception;
	
}
