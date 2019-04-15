/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/15  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.THg.Service.TQsbSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class TQsbSearchServiceImpl implements TQsbSearchService {

  @Autowired
  TQsbMapper tQsbMapper;

  @Override
  public List<TQsb> search(int[] ywlb, String extenda, String extendc, String keyword) {
    return tQsbMapper.searchTQsb(ywlb,extenda,extendc,keyword);
  }

  @Override
  public List<TQsb> searchPage(int[] ywlb, String extenda, String extendc, String keyword, int curr, int pagesize) {
    List<TQsb> tQsbList = tQsbMapper.searchTQsb(ywlb, extenda, extendc, keyword);
    int firstindex = (curr-1)*pagesize;
    int lastindex =  curr*pagesize;
    if (lastindex>tQsbList.size()){
      return tQsbList.subList(firstindex,tQsbList.size());
    }
    if (firstindex>tQsbList.size()){
      return null;
    }
    return tQsbList.subList(firstindex,lastindex);
  }
}
