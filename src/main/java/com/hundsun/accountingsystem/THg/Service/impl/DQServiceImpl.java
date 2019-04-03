/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/3  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.THg.Service.DQService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class DQServiceImpl implements DQService {
  public Log log = LogFactory.getLog(DQServiceImpl.class);
  @Autowired
  public TQsbMapper tQsbMapper;

  @Autowired
  public TCcyebMapper tCcyebMapper;

  @Transactional
  @Override
  public void HG_dq(String today) {
    //获取清算库中银行质押式回购清算信息
    Assist yhzyassist = new Assist();
    yhzyassist.setRequires(Assist.andEq("extendc", "301"));
    List<TQsb> yhzyTQsbList = tQsbMapper.selectTQsb(yhzyassist);
    //获取清算库中银行买断式回购清算信息
    Assist yhmdassist = new Assist();
    yhmdassist.setRequires(Assist.andEq("extendc", "302"));
    List<TQsb> yhmdTQsbList = tQsbMapper.selectTQsb(yhmdassist);
    //获取清算库中交易所质押式回购清算信息
    Assist jyzyassist = new Assist();
    jyzyassist.setRequires(Assist.andEq("extendc", "303"));
    List<TQsb> jyzyTQsbList = tQsbMapper.selectTQsb(jyzyassist);
    //对银行质押式回购清算信息根据时间判断是否到期
    for (TQsb yhzy : yhzyTQsbList
        ) {
      if (yhzy.getExtendb().equals(today)) {
        YHZYMD_DQ(yhzy);
      }
    }
    //对银行买断式回购清算信息根据时间判断是否到期
    for (TQsb yhmd : yhmdTQsbList
        ) {
      if (yhmd.getExtendb().equals(today)) {
        YHZYMD_DQ(yhmd);
      }
    }
    //对交易所质押式回购清算信息根据时间判断是否到期
    for (TQsb jyzy : jyzyTQsbList
        ) {
      if (jyzy.getExtendb().equals(today)) {
        JYZY_DQ(jyzy);
      }
    }
  }

  //银行回购到期处理
  @Transactional
  public void YHZYMD_DQ(TQsb yhzy) {
    //对持仓的变动
    TCcyeb tCcyeb = new TCcyeb();
    tCcyeb.setExtendc(String.valueOf(yhzy.getId()));
    TCcyeb Ccyeb = tCcyebMapper.selectTCcyebByObj(tCcyeb);
    if (Ccyeb == null) {
      log.error("没有相应的持仓数据");
    }
    Ccyeb.setZqcb(0.00);
    Ccyeb.setLjjx(0.00);
    //获取银行存款科目
    TCcyeb Obj = new TCcyeb();
    Obj.setKjkmdm("100201");
    Obj.setZtbh(yhzy.getZtbh());
    TCcyeb ccyeb = tCcyebMapper.selectTCcyebByObj(Obj);
    if (ccyeb == null) {
      log.error("没有相应的银行存款数据");
    }
    //获取回购清算款科目值
    TCcyeb Obj2 = new TCcyeb();
    Obj2.setKjkmdm("1232");
    Obj2.setZtbh(yhzy.getZtbh());
    TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
    if (tCcyeb2 == null) {
      log.error("没有相应的回购清算款数据");
    }
    //对余额的变动
    if (yhzy.getBs().equals("B")) {
      //银行存款余额变动
      if (ccyeb.getZqcb() < yhzy.getAmount()) {
        log.error("银行存款不足");
      } else {
        ccyeb.setZqcb(ccyeb.getZqcb() - (yhzy.getYhs() - yhzy.getGhf() - yhzy.getJsf()));
      }
      //回购清算款变动
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (yhzy.getAmount() - yhzy.getGhf() - yhzy.getJsf()));
    } else {
      //银行存款变动
      ccyeb.setZqcb(ccyeb.getZqcb() + (yhzy.getYhs() + yhzy.getJsf() + yhzy.getGhf()));
      //回购清算款变动
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (yhzy.getAmount() + yhzy.getJsf() + yhzy.getGhf()));
    }
    tCcyebMapper.updateTCcyebById(Ccyeb);
    tCcyebMapper.updateTCcyebById(ccyeb);
    tCcyebMapper.updateTCcyebById(tCcyeb2);
  }

  //对交易所回购到期处理
  @Transactional
  public void JYZY_DQ(TQsb jyzy) {
    //对持仓的变动
    TCcyeb tCcyeb = new TCcyeb();
    tCcyeb.setExtendc(String.valueOf(jyzy.getId()));
    TCcyeb Ccyeb = tCcyebMapper.selectTCcyebByObj(tCcyeb);
    if (Ccyeb == null) {
      log.error("没有相应的持仓数据");
    }
    Ccyeb.setZqcb(0.00);
    Ccyeb.setLjjx(0.00);
    //获取证券清算款科目
    TCcyeb Obj = new TCcyeb();
    Obj.setKjkmdm("1132");
    Obj.setZtbh(jyzy.getZtbh());
    TCcyeb ccyeb = tCcyebMapper.selectTCcyebByObj(Obj);
    if (ccyeb == null) {
      log.error("没有相应的证券清算款数据");
    }
    //获取回购清算款科目值
    TCcyeb Obj2 = new TCcyeb();
    Obj2.setKjkmdm("1232");
    Obj2.setZtbh(jyzy.getZtbh());
    TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
    if (tCcyeb2 == null) {
      log.error("没有相应的回购清算款数据");
    }
    //对余额的变动
    if (jyzy.getBs().equals("B")) {
      //证券清算款变动
      ccyeb.setZqcb(ccyeb.getZqcb() - jyzy.getYhs());
      //回购清算款变动
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (jyzy.getAmount() - jyzy.getGhf() - jyzy.getJsf() - jyzy.getYj()));
    } else {
      //证券清算款变动
      ccyeb.setZqcb(ccyeb.getZqcb() + jyzy.getYhs());
      //回购清算款变动
      tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (jyzy.getAmount() + jyzy.getGhf() + jyzy.getJsf() + jyzy.getYj()));
    }
    tCcyebMapper.updateTCcyebById(Ccyeb);
    tCcyebMapper.updateTCcyebById(ccyeb);
    tCcyebMapper.updateTCcyebById(tCcyeb2);
  }
}
