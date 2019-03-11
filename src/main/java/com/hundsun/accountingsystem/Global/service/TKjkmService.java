/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service;

import com.hundsun.accountingsystem.Global.bean.TKjkm;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface TKjkmService {
  public int insertKJKM(TKjkm TKjkm);
  public int deleteKJKM(String id);
  public int updateKJKM(TKjkm TKjkm);
  public TKjkm findOneKJKM(String id);
  public List<TKjkm> findAllKJKM();
}
