/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/19  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.THg.Service.Union_HGService;
import com.hundsun.accountingsystem.THg.VO.Union_HG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class Union_HgServiceImpl implements Union_HGService {
  @Autowired
  public HttpServletRequest request;
  @Override
  public List<Union_HG> unionHg(List<TQsb> tQsbs) {
    HttpSession session = request.getSession();
    List<TZtxxb> ztxxbs = (List<TZtxxb>) session.getAttribute("ztxxbs");
    List<TZqxx> zqxxes = (List<TZqxx>) session.getAttribute("zqxxes");
    List<Union_HG> newlist = new ArrayList<>();
    for (TQsb tqsb:tQsbs
        ) {
      Union_HG union_hg = new Union_HG();
      union_hg.setZtbh(tqsb.getZtbh());
      String ztbhname = "";
      for (TZtxxb ztxx:ztxxbs
          ) {
        if (ztxx.getZtbh().equals(tqsb.getZtbh())){
          ztbhname = ztxx.getZtbh()+"_"+ztxx.getName();
        }
      }
      union_hg.setZtbhname(ztbhname);
      String zqname = "";
      for (TZqxx tZqxx:zqxxes
          ) {
        if (tZqxx.getZqdm().equals(tqsb.getZqcode())){
          zqname = tZqxx.getZqdm()+"_"+tZqxx.getZqjg();
        }
      }
      union_hg.setId(tqsb.getId());
      union_hg.setZqcodename(zqname);
      union_hg.setZqcode(tqsb.getZqcode());
      union_hg.setYwlb(tqsb.getYwlb());
      union_hg.setBs(tqsb.getBs());
      union_hg.setQuantity(tqsb.getQuantity());
      union_hg.setAmount(tqsb.getAmount());
      union_hg.setYhs(tqsb.getYhs());
      union_hg.setJsf(tqsb.getJsf());
      union_hg.setGhf(tqsb.getGhf());
      union_hg.setZgf(tqsb.getZgf());
      union_hg.setYj(tqsb.getYj());
      union_hg.setLumpsum(tqsb.getLumpsum());
      union_hg.setExtenda(tqsb.getExtenda());
      union_hg.setExtendb(tqsb.getExtendb());
      union_hg.setExtendc(tqsb.getExtendc());
      union_hg.setExtendd(tqsb.getExtendd());
      if (tqsb.getExtendc().equals("303")){
        union_hg.setExtende(tqsb.getExtende());
        union_hg.setCjsr(tqsb.getCjsr());
        union_hg.setCost(tqsb.getCost());
      }
      union_hg.setSclb(tqsb.getSclb());
      newlist.add(union_hg);
    }
    return newlist;
  }
}
