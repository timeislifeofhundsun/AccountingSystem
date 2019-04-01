/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/28  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TCjhbbMapper;
import com.hundsun.accountingsystem.Global.mapper.TJyflMapper;
import com.hundsun.accountingsystem.Global.mapper.TLfjxbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.THg.Service.HGQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
@Service
public class HGQSServiceImpl implements HGQSService {
  @Autowired
  public TQsbMapper tQsbMapper;
  @Autowired
  public TLfjxbMapper tLfjxbMapper;
  @Autowired
  public TCjhbbMapper tCjhbbMapper;
  @Autowired
  public TJyflMapper tJyflMapper;
  @Autowired
  public TCcyebMapper tCcyebMapper;

  @Override
  public boolean hgqs(int ztbh, Date ywrq) throws ParseException {
    //清除清算表里存在的数据
    int[] ywlb = {3101, 3102};
    int i = tQsbMapper.deleteAllTQsb(ywlb, DateFormatUtil.getStringByDate(ywrq), "303", ztbh);
    //获取利率对象
    TLfjxb tLfjxb = tLfjxbMapper.selectByPrimaryKey(1);
    Double hglv = Double.valueOf(tLfjxb.getHglv());
    Double jylv = Double.valueOf(tLfjxb.getJylv());
    Double jslv = Double.valueOf(tLfjxb.getJslv());
    //获取成交回报对象
    TCjhbbExample example = new TCjhbbExample();
    TCjhbbExample.Criteria criteria = example.createCriteria();
    criteria.andZtbhEqualTo(ztbh);
    criteria.andYwlbEqualTo(3);
    criteria.andYwrqBetween(ywrq, ywrq);
    List<TCjhbb> tCjhbbList = tCjhbbMapper.selectByExample(example);
    if (tCjhbbList.isEmpty()){
      return true;
    }
    //进行数据合笔
    Map<String, List<TCjhbb>> hbMap = new HashMap<>();
    for (TCjhbb tCjhbb : tCjhbbList
        ) {
      String zqdm = tCjhbb.getZqdm();
      List<TCjhbb> list = hbMap.get(zqdm);
      if (list == null) {
        list = new ArrayList<TCjhbb>();
      }
      list.add(tCjhbb);
      hbMap.put(zqdm, list);
    }
    //将map中的数据合一
    List<TQsb> tQsbList = new ArrayList<TQsb>();
    for (List<TCjhbb> tCjhbbs:hbMap.values()
         ) {
      TQsb tQsb = new TQsb();
      //设置证券代码
      tQsb.setZqcode(tCjhbbs.get(0).getZqdm());
      //设置账套编号
      tQsb.setZtbh(tCjhbbs.get(0).getZtbh());
      //回购方向
      tQsb.setBs(tCjhbbs.get(0).getMmfx());
      //回购天数
      if (tCjhbbs.get(0).getJysc() == 0) {
        String day = tCjhbbs.get(0).getZqdm().substring(3, 6);
        String[] days = day.split("");
        if (days[0].equals("0")) {
          if (days[1].equals("0")) {
            day = days[2];
          } else {
            day = days[1] + days[2];
          }
        } else {
          day = days[0] + days[1] + days[2];
        }
        tQsb.setQuantity(Integer.valueOf(day));
      }else{
        String day = tCjhbbs.get(0).getZqdm().substring(4, 6);
        if (day.equals("10")){
          day="1";
        }else if (day.equals("11")){
          day="2";
        }else if (day.equals("00")){
          day="3";
        }else if (day.equals("09")){
          day="4";
        }else if (day.equals("01")){
          day="7";
        }else if (day.equals("02")){
          day="14";
        }else if (day.equals("03")){
          day="28";
        }else if (day.equals("05")){
          day="91";
        }else if (day.equals("06")){
          day="182";
        }
        tQsb.setQuantity(Integer.valueOf(day));
      }
      //设置回购类别
      tQsb.setExtendc("303");
      //设置结算机构
      tQsb.setLumpsum(Double.valueOf("1"));
      //设置交易市场
      if (tCjhbbs.get(0).getJysc() == 0) {
        tQsb.setSclb(1);
      } else {
        tQsb.setSclb(2);
      }
      //设置交易日期
      tQsb.setExtenda(DateFormatUtil.getStringByDate(tCjhbbs.get(0).getYwrq()));
      //设置到期日期
      String dqrq = CalcDate(DateFormatUtil.getStringByDate(tCjhbbs.get(0).getYwrq()),Integer.valueOf(tQsb.getQuantity()));
      tQsb.setExtendb(dqrq);
      //设置成交金额 成交数量
      Double CJJE = 0.00;
      Double CJSL = 0.00;
      for (TCjhbb tCjhbb : tCjhbbs) {
        CJJE = CJJE + tCjhbb.getCjje();
        CJSL = CJSL + tCjhbb.getCjsl();
      }
      if (tCjhbbs.get(0).getJysc() == 0){
        CJSL=CJSL*10;
      }
      tQsb.setAmount(CJJE);
      tQsb.setCost(CJSL);
      //设置券面总额
      Double Mgmz = CJJE / CJSL;
      tQsb.setZgf(Mgmz);
      //设置交易所费用
      Double jssxf = CJJE * jslv;
      Double jysxf = CJJE * jylv;
      tQsb.setJsf(jssxf);
      tQsb.setGhf(jysxf);
      //设置交易所佣金和业务类别
      TJyfl jysrz = tJyflMapper.selectByPrimaryKey(3101);
      TJyfl jysrq = tJyflMapper.selectByPrimaryKey(3102);
      if (tCjhbbs.get(0).getMmfx().equals("B")) {
        tQsb.setYj(CJJE * jysrz.getYj());
        tQsb.setYwlb(3101);
      } else {
        tQsb.setYj(CJJE * jysrq.getYj());
        tQsb.setYwlb(3102);
      }
      //设置总利息
      Double Zlx = CJJE * Integer.valueOf(tQsb.getQuantity()) * hglv;
      tQsb.setCjsr(Zlx);
      //设置到期金额
      Double DQJE = CJJE-jssxf-jysxf-(tQsb.getYj())+Zlx;
      tQsb.setYhs(DQJE);
      //设置股东代码
      tQsb.setExtendd(tCjhbbList.get(0).getGddm());
      //设置席位代码
      tQsb.setExtende(tCjhbbList.get(0).getXwbh());
      tQsbList.add(tQsb);
    }
    if (tQsbList.isEmpty()){
      return false;
    }else{
      //生成持仓余额数据
      int count=0;
      for (TQsb tqsb:tQsbList ) {
        int i1 = tQsbMapper.insertTQsb(tqsb);
        TCcyeb tCcyeb = new TCcyeb();
        tCcyeb.setZqdm(tqsb.getZqcode());
        tCcyeb.setZtbh(tqsb.getZtbh());
        tCcyeb.setZqcb(tqsb.getAmount());
        tCcyeb.setExtendc(String.valueOf(tqsb.getId()));
        tCcyeb.setLjjx(tqsb.getCjsr());
        tCcyeb.setFsrq(DateFormatUtil.getDateByString(tqsb.getExtenda()));
        tCcyeb.setExtenda("31");
        tCcyeb.setExtendb(tqsb.getExtendb());
        int i2 = tCcyebMapper.insertTCcyeb(tCcyeb);
        if (i1!=0&&i2!=0){
          count++;
        }
      }
      //新增持仓余额变动数据
      if (count!=0){
        return true;
      }else{
        return false;
      }
     /*if (i1!=0){
       return true;
     }else{
       return false;
     }*/
    }
  }
  //根据给定日期和天数计算到期时间
  @Override
  public String CalcDate(String start, int day) throws ParseException {
    String end="";
    String[] Holiday = {"2019/4/5", "2019/5/1", "2019/5/2", "2019/5/3", "2019/6/7", "2019/9/13",
        "2019/10/1", "2019/10/2", "2019/10/3", "2019/10/4", "2019/10/7"};
    List<String> Holidays = Arrays.asList(Holiday);
    String[] Workday = {"2019/4/28", "2019/5/5", "2019/9/29", "2019/10/12"};
    List<String> Workdays = Arrays.asList(Workday);
    Date dateByString = DateFormatUtil.getDateByString(start);
    Long nowTime =dateByString.getTime();
    int i = 0;
    while (i != day) {
      i++;
      int dayTime = i * 24 * 60 * 60 * 1000;
      Date t = new Date(nowTime + dayTime);
      int week = t.getDay();
      String ymd=DateFormatUtil.getStringByDate(t);
      if ((week == 6 || week == 0) && !Workdays.contains(ymd)) {
        day++;
      } else if (Workdays.contains(ymd)) {
      } else if (Holidays.contains(ymd)) {
        day++;
      } else {
      }
      end = ymd;
    }
    return end;
  }
}
