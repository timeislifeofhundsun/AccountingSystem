/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service.serviceImpl;

import com.hundsun.accountingsystem.Global.bean.TKjkm;
import com.hundsun.accountingsystem.Global.mapper.TKjkmMapper;
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
  public TKjkmMapper TKjkmMapper;
  @Override
  public int insertKJKM(TKjkm TKjkm) {
    System.out.println(TKjkmMapper);
    int i= TKjkmMapper.insertKJKM(TKjkm);
    return i;
  }

  @Override
  public int deleteKJKM(String id) {
    int i = TKjkmMapper.deleteKJKM(id);
    return i;
  }

  @Override
  public int updateKJKM(TKjkm TKjkm) {
    int i = TKjkmMapper.updateKJKM(TKjkm);
    return i;
  }

  @Override
  public TKjkm findOneKJKM(String id) {
    TKjkm TKjkm = TKjkmMapper.findOneKJKM(id);
    return TKjkm;
  }

  @Override
  public List<TKjkm> findAllKJKM() {
    List<TKjkm> list = TKjkmMapper.findAllKJKM();
    return list;
  }
}
