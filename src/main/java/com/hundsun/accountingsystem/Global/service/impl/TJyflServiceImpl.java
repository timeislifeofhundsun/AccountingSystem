/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/13  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service.impl;

import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.service.TJyflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class TJyflServiceImpl implements TJyflService {
  @Autowired
  public TJyflMapper tJyflMapper;
  @Override
  public int deleteByPrimaryKey(Integer ywcode) {
    int i = tJyflMapper.deleteByPrimaryKey(ywcode);
    return i;
  }

  @Override
  public int insert(TJyfl record) {
    int i = tJyflMapper.insert(record);
    return i;
  }

  @Override
  public int insertSelective(TJyfl record) {
    int i = tJyflMapper.insertSelective(record);
    return i;
  }

  @Override
  public TJyfl selectByPrimaryKey(Integer ywcode) {
    TJyfl tJyfl = tJyflMapper.selectByPrimaryKey(ywcode);
    return tJyfl;
  }

  @Override
  public List<TJyfl> getTJyflPage(int curr, int pagesize) {
    List<TJyfl> allTJyfl = tJyflMapper.findAllTJyfl();
    int firstindex = (curr-1)*pagesize;
    int lastindex =  curr*pagesize;
    if (lastindex>allTJyfl.size()){
      return allTJyfl.subList(firstindex,allTJyfl.size());
    }
    if (firstindex>allTJyfl.size()){
      return null;
    }
    return allTJyfl.subList(firstindex,lastindex);
  }

  @Override
  public int updateByPrimaryKeySelective(TJyfl record) {
    int i = tJyflMapper.updateByPrimaryKeySelective(record);
    return i;
  }

  @Override
  public int updateByPrimaryKey(TJyfl record) {
    int i = tJyflMapper.updateByPrimaryKey(record);
    return i;
  }

  @Override
  public List<TJyfl> findAllTJyfl() {
    List<TJyfl> allTJyfl = tJyflMapper.findAllTJyfl();
    return allTJyfl;
  }
}
