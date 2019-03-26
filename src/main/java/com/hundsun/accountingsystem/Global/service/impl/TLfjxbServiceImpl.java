/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/22  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service.impl;

import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.mapper.TLfjxbMapper;
import com.hundsun.accountingsystem.Global.service.TLfjxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class TLfjxbServiceImpl implements TLfjxbService {
  @Autowired
  public TLfjxbMapper tLfjxbMapper;
  @Override
  public TLfjxb selectByPrimaryKey(Integer id) {
    TLfjxb tLfjxb = tLfjxbMapper.selectByPrimaryKey(id);
    return  tLfjxb;
  }

  @Override
  public int updateByPrimaryKeySelective(TLfjxb record) {
    int i = tLfjxbMapper.updateByPrimaryKeySelective(record);
    return i;
  }

  @Override
  public int updateByPrimaryKey(TLfjxb record) {
    int i = tLfjxbMapper.updateByPrimaryKey(record);
    return i;
  }
}
