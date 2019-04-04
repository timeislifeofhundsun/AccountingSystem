/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/28  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service;


import java.text.ParseException;
import java.util.Date;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 回购清算服务接口
 */
public interface HGQSService {
  public boolean hgqs(int ztbh,Date ywrq ) throws ParseException;

  public String CalcDate(String start, int day) throws ParseException;

  public boolean isWorkDay(String rq) throws ParseException;
}
