/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/15  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service;

import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface TQsbSearchService {
  List<TQsb> search(int[] ywlb,String extenda,String extendc,String keyword);
  List<TQsb> searchPage(int[] ywlb,String extenda,String extendc,String keyword,int curr, int pagesize);
}
