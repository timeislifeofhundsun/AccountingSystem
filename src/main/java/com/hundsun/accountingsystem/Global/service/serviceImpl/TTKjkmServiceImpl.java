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
public class TTKjkmServiceImpl implements TKjkmService {
  @Autowired
  public TKjkmbMapper tKjkmbMapper;
  @Override
  public int insertKJKM(TKjkmb TKjkm) {
    int i= tKjkmbMapper.insert(TKjkm);
    return i;
  }

  @Override
  public int deleteKJKM(String id) {
    int i = tKjkmbMapper.deleteByPrimaryKey(id);
    return i;
  }

  @Override
  public int updateKJKM(TKjkmb TKjkm) {
    int i = tKjkmbMapper.updateByPrimaryKey(TKjkm);
    return i;
  }

  @Override
  public TKjkmb findOneKJKM(String id) {
    TKjkmb TKjkm = tKjkmbMapper.selectByPrimaryKey(id);
    return TKjkm;
  }

  @Override
  public List<TKjkmb> findAllKJKM() {
    List<TKjkmb> list = tKjkmbMapper.findAllKJKM();
    return list;
  }
}
