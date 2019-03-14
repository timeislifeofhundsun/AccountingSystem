/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service.serviceImpl;

import com.hundsun.accountingsystem.Global.bean.TKjkmb;
import com.hundsun.accountingsystem.Global.mapper.TKjkmbMapper;
import com.hundsun.accountingsystem.Global.service.TKjkmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class TKjkmServiceImpl implements TKjkmService {
  @Autowired
  public TKjkmbMapper tKjkmbMapper;

  @Override
  public int deleteByPrimaryKey(String id) {
    int i = tKjkmbMapper.deleteByPrimaryKey(id);
    return i;
  }

  @Override
  public int insert(TKjkmb record) {
    int i = tKjkmbMapper.insert(record);
    return i;
  }

  @Override
  public int insertSelective(TKjkmb record) {
    int i = tKjkmbMapper.insertSelective(record);
    return i;
  }

  @Override
  public TKjkmb selectByPrimaryKey(String id) {
    TKjkmb tKjkmb = tKjkmbMapper.selectByPrimaryKey(id);
    return tKjkmb;
  }

  @Override
  public int updateByPrimaryKeySelective(TKjkmb record) {
    int i = tKjkmbMapper.updateByPrimaryKeySelective(record);
    return i;
  }

  @Override
  public int updateByPrimaryKey(TKjkmb record) {
    int i = tKjkmbMapper.updateByPrimaryKey(record);
    return i;
  }

  @Override
  public List<TKjkmb> findAllKJKM() {
    List<TKjkmb> allKJKM = tKjkmbMapper.findAllKJKM();
    return allKJKM;
  }
}
