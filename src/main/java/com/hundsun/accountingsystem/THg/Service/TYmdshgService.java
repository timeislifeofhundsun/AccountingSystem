/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/27  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service;

import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 银行买断式回购服务接口
 */
public interface TYmdshgService {
  TQsb selectTQsbById(Integer id);

  int insertTQsb(TQsb value);

  int deleteTQsbById(Integer id);

  int updateTQsbById(TQsb enti);

  List<TQsb> findAllTQsb(int[] ywlb, String extenda , String extendc);

  List<TQsb> selectTQsbbyPage(int[] ywlb,String extenda ,String extendc,int curr, int pagesize);
}
