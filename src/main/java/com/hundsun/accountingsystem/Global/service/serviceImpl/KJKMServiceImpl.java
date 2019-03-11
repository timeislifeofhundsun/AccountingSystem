/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.service.serviceImpl;

import com.hundsun.accountingsystem.Global.bean.KJKM;
import com.hundsun.accountingsystem.Global.mapper.KJKMMapper;
import com.hundsun.accountingsystem.Global.service.KJKMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class KJKMServiceImpl implements KJKMService {
  @Autowired
  public KJKMMapper kjkmMapper;
  @Override
  public int insertKJKM(KJKM kjkm) {
    System.out.println(kjkmMapper);
    int i= kjkmMapper.insertKJKM(kjkm);
    return i;
  }

  @Override
  public int deleteKJKM(String id) {
    int i = kjkmMapper.deleteKJKM(id);
    return i;
  }

  @Override
  public int updateKJKM(KJKM kjkm) {
    int i = kjkmMapper.updateKJKM(kjkm);
    return i;
  }

  @Override
  public KJKM findOneKJKM(String id) {
    KJKM kjkm = kjkmMapper.findOneKJKM(id);
    return kjkm;
  }

  @Override
  public List<KJKM> findAllKJKM() {
    List<KJKM> list = kjkmMapper.findAllKJKM();
    return list;
  }
}
