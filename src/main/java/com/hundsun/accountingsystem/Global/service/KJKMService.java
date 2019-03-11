/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service;

import com.hundsun.accountingsystem.Global.bean.KJKM;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface KJKMService {
  public int insertKJKM(KJKM kjkm);
  public int deleteKJKM(String id);
  public int updateKJKM(KJKM kjkm);
  public KJKM findOneKJKM(String id);
  public List<KJKM> findAllKJKM();
}
