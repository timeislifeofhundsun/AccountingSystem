/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/13  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service;

import com.hundsun.accountingsystem.Global.bean.TJyfl;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface TJyflService {
  int deleteByPrimaryKey(Integer ywcode);

  int insert(TJyfl record);

  int insertSelective(TJyfl record);

  TJyfl selectByPrimaryKey(Integer ywcode);

  public List<TJyfl> getTJyflPage(int curr, int pagesize);

  int updateByPrimaryKeySelective(TJyfl record);

  int updateByPrimaryKey(TJyfl record);

  List<TJyfl> findAllTJyfl();
}
