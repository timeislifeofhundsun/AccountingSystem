/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/2  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.THg.Service.HGPZBService;
import com.hundsun.accountingsystem.THg.Service.HGQSService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class HGPZServiceImpl implements HGPZBService {
  public Log log = LogFactory.getLog(HGPZServiceImpl.class);
  @Autowired
  public TQsbMapper tQsbMapper;
  @Autowired
  public TZqxxMapper tZqxxMapper;
  @Autowired
  public TSequenceService tSequenceService;
  @Autowired
  public TPzbMapper tPzbMapper;
  @Autowired
  public HGQSService hgqsService;

  //回购业务生成凭证入口
  @Transactional
  @Override
  public void HG_pz(int ztbh, Date rq) throws ParseException {
    String today = DateFormatUtil.getStringByDate(rq);

    if (!hgqsService.isWorkDay(today)){
      return;
    }
    //根据账套编号和日期删除已经生成得凭证
    deleteAll_pz(ztbh, rq);
    //获取清算库中银行质押式回购清算信息
    Assist yhzyassist = new Assist();
    yhzyassist.setRequires(Assist.andEq("ztbh", ztbh));
    yhzyassist.setRequires(Assist.andEq("extendc", "301"));
    List<TQsb> yhzyTQsbList = tQsbMapper.selectTQsb(yhzyassist);
    //获取清算库中银行买断式回购清算信息
    Assist yhmdassist = new Assist();
    yhmdassist.setRequires(Assist.andEq("ztbh", ztbh));
    yhmdassist.setRequires(Assist.andEq("extendc", "302"));
    List<TQsb> yhmdTQsbList = tQsbMapper.selectTQsb(yhmdassist);
    //获取清算库中交易所质押式回购清算信息
    Assist jyzyassist = new Assist();
    jyzyassist.setRequires(Assist.andEq("ztbh", ztbh));
    jyzyassist.setRequires(Assist.andEq("extendc", "303"));
    List<TQsb> jyzyTQsbList = tQsbMapper.selectTQsb(jyzyassist);
    //对银行质押式回购清算信息根据时间生成相应凭证
    for (TQsb yhzy : yhzyTQsbList
        ) {
      if (yhzy.getZtbh().equals(ztbh)) {
        if (yhzy.getExtenda().equals(today)) {
          YHQS_pz(yhzy, rq);
          YHJX_pz(yhzy, rq);
        } else if (belong(yhzy.getExtenda(), yhzy.getExtendb(), today)) {
          YHJX_pz(yhzy, rq);
        } else if (yhzy.getExtendb().equals(today)) {
          YHDQ_pz(yhzy, rq);
        }
      }
    }
    //对银行买断式回购清算信息根据时间生成相应凭证
    for (TQsb yhmd : yhmdTQsbList
        ) {
      if (yhmd.getZtbh().equals(ztbh)) {
        if (yhmd.getExtenda().equals(today)) {
          YHQS_pz(yhmd, rq);
          YHJX_pz(yhmd, rq);
        } else if (belong(yhmd.getExtenda(), yhmd.getExtendb(), today)) {
          YHJX_pz(yhmd, rq);
        } else if (yhmd.getExtendb().equals(today)) {
          YHDQ_pz(yhmd, rq);
        }
      }
    }
    //对交易所质押式回购清算信息根据时间生成相应凭证
    for (TQsb jyzy : jyzyTQsbList
        ) {
      if (jyzy.getZtbh().equals(ztbh)) {
        if (jyzy.getExtenda().equals(today)) {
          JYQS_pz(jyzy, rq);
          JYJX_pz(jyzy, rq);
        } else if (belong(jyzy.getExtenda(), jyzy.getExtendb(), today)) {
          JYJX_pz(jyzy, rq);
        } else if (jyzy.getExtendb().equals(today)) {
          JYDQ_pz(jyzy, rq);
        }
      }
    }
  }

  //银行买入卖出凭证生成
  public void YHQS_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "";
    if (tQsb.getExtendc().equals("301")) {
      ywmc = "银行间质押式买入卖出凭证";
    } else if (tQsb.getExtendc().equals("302")) {
      ywmc = "银行间买断式买入卖出凭证";
    }
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("银行存款");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("银行存款");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("回购清算款");
      tPzb.setBs("贷");
      tPzb.setJe(tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf());
    } else {
      tPzb.setKmms("回购清算款");
      tPzb.setBs("借");
      tPzb.setJe(tQsb.getAmount() + tQsb.getGhf() + tQsb.getJsf());
    }
    tPzbMapper.insertTPzb(tPzb);
    tPzb.setKmms("结算手续费");
    tPzb.setBs("贷");
    tPzb.setJe(tQsb.getJsf());
    tPzbMapper.insertTPzb(tPzb);
    tPzb.setKmms("交易手续费");
    tPzb.setJe(tQsb.getGhf());
    tPzbMapper.insertTPzb(tPzb);
  }

  //银行计息凭证生成
  public void YHJX_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "";
    if (tQsb.getExtendc().equals("301")) {
      ywmc = "银行间质押式计息凭证";
    } else if (tQsb.getExtendc().equals("302")) {
      ywmc = "银行间买断式计息凭证";
    }
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("利息支出");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("利息收入");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("应付利息");
      tPzb.setBs("贷");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
    } else {
      tPzb.setKmms("应收利息");
      tPzb.setBs("借");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
    }
    tPzbMapper.insertTPzb(tPzb);
  }

  //银行到期凭证生成
  public void YHDQ_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "";
    if (tQsb.getExtendc().equals("301")) {
      ywmc = "银行间质押式到期凭证";
    } else if (tQsb.getExtendc().equals("302")) {
      ywmc = "银行间买断式到期凭证";
    }
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("回购清算款");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("回购清算款");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() + tQsb.getGhf() + tQsb.getJsf());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("银行存款");
      tPzb.setBs("贷");
      tPzb.setJe(tQsb.getYhs() );//- tQsb.getGhf() - tQsb.getJsf()
    } else {
      tPzb.setKmms("银行存款");
      tPzb.setBs("借");
      tPzb.setJe(tQsb.getYhs() );//+ tQsb.getGhf() + tQsb.getJsf()
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("应付利息");
      tPzb.setBs("借");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()+ tQsb.getGhf() + tQsb.getJsf()));
    } else {
      tPzb.setKmms("应收利息");
      tPzb.setBs("贷");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()- tQsb.getGhf() - tQsb.getJsf()));
    }
    tPzbMapper.insertTPzb(tPzb);
  }

  //交易所买入卖出凭证生成
  public void JYQS_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "交易所质押式买入卖出凭证";
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("证券清算款");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() - tQsb.getJsf() - tQsb.getGhf());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("证券清算款");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() + tQsb.getJsf() + tQsb.getGhf());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("回购清算款");
      tPzb.setBs("贷");
      tPzb.setJe(tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf() - tQsb.getYj());
    } else {
      tPzb.setKmms("回购清算款");
      tPzb.setBs("借");
      tPzb.setJe(tQsb.getAmount() + tQsb.getGhf() + tQsb.getJsf() + tQsb.getYj());
    }
    tPzbMapper.insertTPzb(tPzb);
    tPzb.setKmms("应付交易费用");
    tPzb.setJe(tQsb.getYj());
    tPzbMapper.insertTPzb(tPzb);
  }

  //交易所计息凭证生成
  public void JYJX_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "交易所质押式计息凭证";
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("利息支出");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("利息收入");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("应付利息");
      tPzb.setBs("贷");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
    } else {
      tPzb.setKmms("应收利息");
      tPzb.setBs("借");
      tPzb.setJe((tQsb.getYhs() - tQsb.getAmount()) / tQsb.getQuantity());
    }
    tPzbMapper.insertTPzb(tPzb);
  }

  //交易所到期凭证生成
  public void JYDQ_pz(TQsb tQsb, Date rq) {
    TPzb tPzb = new TPzb();
    String ywmc = "交易所质押式到期凭证";
    int pzid = tSequenceService.getSequenceByName("pz");
    TZqxx tZqxx = tZqxxMapper.selectByZqdm((tQsb.getZqcode()));
    if (tZqxx==null){
      log.error("证券信息获取失败");
    }
    String date = DateFormatUtil.getStringByDate(rq);
    String zy = "[" + date + "]" + ywmc + "[" + tQsb.getZqcode() + "]" + "[" + tZqxx.getZqjg() + "]";
    if (tQsb.getBs().equals("B")) {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("回购清算款");
      tPzb.setBs("借");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() - tQsb.getGhf() - tQsb.getJsf() - tQsb.getYj());
      tPzb.setPzid(pzid);
    } else {
      tPzb.setZy(zy);
      tPzb.setZtbh(tQsb.getZtbh());
      tPzb.setKmms("回购清算款");
      tPzb.setBs("贷");
      tPzb.setRq(rq);
      tPzb.setExtendf("31");
      tPzb.setJe(tQsb.getAmount() + tQsb.getGhf() + tQsb.getJsf() + tQsb.getYj());
      tPzb.setPzid(pzid);
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("应付利息");
      tPzb.setBs("借");
      tPzb.setJe(tQsb.getCjsr()+ tQsb.getGhf() + tQsb.getJsf() + tQsb.getYj());
    } else {
      tPzb.setKmms("应收利息");
      tPzb.setBs("贷");
      tPzb.setJe(tQsb.getCjsr() - tQsb.getGhf() - tQsb.getJsf() - tQsb.getYj());
    }
    tPzbMapper.insertTPzb(tPzb);
    if (tQsb.getBs().equals("B")) {
      tPzb.setKmms("证券清算款");
      tPzb.setBs("贷");
      tPzb.setJe(tQsb.getAmount()  + tQsb.getCjsr());//- tQsb.getGhf() - tQsb.getJsf() - tQsb.getYj()
    } else {
      tPzb.setKmms("证券清算款");
      tPzb.setBs("借");
      tPzb.setJe(tQsb.getAmount() + tQsb.getCjsr());//+ tQsb.getGhf() + tQsb.getJsf() + tQsb.getYj()
    }
    tPzbMapper.insertTPzb(tPzb);
  }

  @Override
  public void deleteAll_pz(int ztbh, Date rq) {
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("ztbh", ztbh));
    assist.setRequires(Assist.andEq("rq", rq));
    assist.setRequires(Assist.andEq("extendf", "31"));
    tPzbMapper.deleteTPzb(assist);
  }

  public static Boolean belong(String start, String end, String today) throws ParseException {
    Calendar startC = Calendar.getInstance();
    startC.setTime(DateFormatUtil.getDateByString(start));
    Calendar endC = Calendar.getInstance();
    endC.setTime(DateFormatUtil.getDateByString(end));
    Calendar todayC = Calendar.getInstance();
    todayC.setTime(DateFormatUtil.getDateByString(today));
    if (todayC.after(startC) && todayC.before(endC)) {
      return true;
    } else {
      return false;
    }
  }
}
