/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service;

import com.hundsun.accountingsystem.Global.bean.TKjkmb;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
public interface TKjkmService {
  //删除会计科目BY主键
  int deleteByPrimaryKey(String id);
  //插入会计科目
  int insert(TKjkmb record);
  //插入会计科目（动态插入字段）
  int insertSelective(TKjkmb record);
  //获取会计科目BY主键（动态查询）
  TKjkmb selectByPrimaryKey(String id);
  //更新会计科目BY主键（动态更新字段）
  int updateByPrimaryKeySelective(TKjkmb record);
  //更新会计科目BY主键（更新全部字段）
  int updateByPrimaryKey(TKjkmb record);
  //查询全部会计科目
  List<TKjkmb> findAllKJKM();
}
