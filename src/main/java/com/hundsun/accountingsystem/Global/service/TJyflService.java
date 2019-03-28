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
  //删除交易费率BY主键
  int deleteByPrimaryKey(Integer ywcode);
  //插入一条交易费率
  int insert(TJyfl record);
  //动态插入一条交易费率
  int insertSelective(TJyfl record);
  //查询交易费率BY主键
  TJyfl selectByPrimaryKey(Integer ywcode);
  //分页查询交易费率
  public List<TJyfl> getTJyflPage(int curr, int pagesize);
  //更新交易费率BY主键（动态更新字段）
  int updateByPrimaryKeySelective(TJyfl record);
  //更新交易费率BY主键（更新全部字段）
  int updateByPrimaryKey(TJyfl record);
  //查询全部交易费率
  List<TJyfl> findAllTJyfl();
}
