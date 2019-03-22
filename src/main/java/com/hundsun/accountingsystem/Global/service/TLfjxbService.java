/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/22  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service;

import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import org.springframework.stereotype.Service;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */

public interface TLfjxbService {
  TLfjxb selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TLfjxb record);

  int updateByPrimaryKey(TLfjxb record);
}
