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
  //查询两费计息BY主键
  TLfjxb selectByPrimaryKey(Integer id);
  //更新两费计息BY主键（动态选择插入字段）
  int updateByPrimaryKeySelective(TLfjxb record);
  //更新两费计息BY主键（更新全部字段）
  int updateByPrimaryKey(TLfjxb record);
}
