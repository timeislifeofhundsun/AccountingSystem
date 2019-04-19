/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/19  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.THg.VO.Union_HG;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface Union_HGService {
  public List<Union_HG> unionHg(List<TQsb> tQsbs);
}
