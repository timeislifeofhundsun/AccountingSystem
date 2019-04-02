/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/29  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.THg.Service.TJzyshgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 交易所质押式回购服务实现
 */
@Service
public class TJzyshgServiceImpl implements TJzyshgService {
  @Autowired
  public TQsbMapper tQsbMapper;
  @Override
  public TQsb selectTQsbById(Integer id) {
    TQsb tQsb = tQsbMapper.selectTQsbById(id);
    return tQsb;
  }

  @Override
  public int insertTQsb(TQsb value) {
    int i = tQsbMapper.insertTQsb(value);
    return i;
  }

  @Override
  public int deleteTQsbById(Integer id) {
    int i = tQsbMapper.deleteTQsbById(id);
    return i;
  }

  @Override
  public int updateTQsbById(TQsb enti) {
    int i = tQsbMapper.updateTQsbById(enti);
    return i;
  }

  @Override
  public List<TQsb> findAllTQsb(int[] ywlb, String extenda, String extendc) {
    List<TQsb> allTQsb = tQsbMapper.findAllTQsb(ywlb, extenda, extendc);
    return allTQsb;
  }

  @Override
  public List<TQsb> selectTQsbbyPage(int[] ywlb, String extenda, String extendc, int curr, int pagesize) {
    List<TQsb> allTQsb = tQsbMapper.findAllTQsb(ywlb, extenda, extendc);
    int firstindex = (curr-1)*pagesize;
    int lastindex =  curr*pagesize;
    if (lastindex>allTQsb.size()){
      return allTQsb.subList(firstindex,allTQsb.size());
    }
    if (firstindex>allTQsb.size()){
      return null;
    }
    return allTQsb.subList(firstindex,lastindex);
  }
}
