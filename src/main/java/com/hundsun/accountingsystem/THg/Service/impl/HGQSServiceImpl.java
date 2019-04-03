/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/28  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service.impl;

import com.hundsun.accountingsystem.Global.bean.Assist;
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
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 回购清算服务实现
 */
@Service
public class HGQSServiceImpl implements HGQSService {
  public static Log log = LogFactory.getLog(HGQSServiceImpl.class);
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

  @Transactional
  @Override
  public boolean hgqs(int ztbh, Date ywrq) throws ParseException {
    int[] ywlb = {3101, 3102};
    //恢复余额数据
    List<TQsb> allTQsb = tQsbMapper.findAllTQsb(ywlb, DateFormatUtil.getStringByDate(ywrq), "303");
    for (TQsb tqsb : allTQsb) {
      if (tqsb.getZtbh().equals(ztbh)) {
        //恢复其他科目数据
        //获取应收利息科目值
        TCcyeb Obj1 = new TCcyeb();
        Obj1.setKjkmdm("6142");
        Obj1.setZtbh(ztbh);
        TCcyeb tCcyeb1 = tCcyebMapper.selectTCcyebByObj(Obj1);
        //获取回购清算款科目值
        TCcyeb Obj2 = new TCcyeb();
        Obj2.setKjkmdm("1232");
        Obj2.setZtbh(ztbh);
        TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
        //获取应付交易费用科目值
        TCcyeb Obj3 = new TCcyeb();
        Obj3.setKjkmdm("2002");
        Obj3.setZtbh(ztbh);
        TCcyeb tCcyeb3 = tCcyebMapper.selectTCcyebByObj(Obj3);
        //获取利息收入科目值
        TCcyeb Obj4 = new TCcyeb();
        Obj4.setKjkmdm("1142");
        Obj4.setZtbh(ztbh);
        TCcyeb tCcyeb4 = tCcyebMapper.selectTCcyebByObj(Obj4);
        //证券清算款-回购
        TCcyeb Obj5 = new TCcyeb();
        Obj5.setKjkmdm("1132");
        Obj5.setZtbh(ztbh);
        TCcyeb tCcyeb5 = tCcyebMapper.selectTCcyebByObj(Obj5);
        if (tCcyeb1 != null && tCcyeb2 != null && tCcyeb3 != null && tCcyeb4 != null && tCcyeb5 != null) {
          //应收利息科目 融资：加上相应的差值 融券：减去相应的差值
          if (tqsb.getBs().equals("B")) {
            tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb1);
          //回购清算款  融资：回购清算款（删除时应加上减少值） 融券：回购清算款（删除时应减掉增加值）
          if (tqsb.getBs().equals("B")) {
            //融资 回购清算款=证券清算款（成交金额-交易费）-佣金  融资时回购清算款是贷方，因此恢复时应添加
            tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf()) - tqsb.getYj());
          } else {
            //融券 回购清算款=成交金额+交易费+佣金
            tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (tqsb.getAmount() + tqsb.getGhf() + tqsb.getJsf() + tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb2);
          //应付交易费用     融资：交易费用（删除时减少） 融券：交易费用（删除时减少）
          tCcyeb3.setZqcb(tCcyeb3.getZqcb() - tqsb.getGhf() - tqsb.getJsf() - tqsb.getYj());
          tCcyebMapper.updateTCcyebById(tCcyeb3);
          //利息收入       融资：付出利息（删除时应加上减少值） 融券：收入利息（删除时应减到增加值）
          if (tqsb.getBs().equals("B")) {
            tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb4);
          //证券清算款       融资：付出利息（删除时应加上减少值） 融券：收入利息（删除时应减到增加值）
          if (tqsb.getBs().equals("B")) {
            //融资 证券清算款=成交金额-交易费用 融资时证券清算款是借方，因此恢复时应减少
            tCcyeb5.setZqcb(tCcyeb5.getZqcb() - (tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf()));
          } else {
            //融券 证券清算款=成交金额+交易费用
            tCcyeb5.setZqcb(tCcyeb5.getZqcb() + (tqsb.getAmount() + tqsb.getGhf() + tqsb.getJsf()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb5);
        }
      }
    }
    //删除清算数据
    int i = tQsbMapper.deleteAllTQsb(ywlb, DateFormatUtil.getStringByDate(ywrq), "303", ztbh);
    //删除持仓数据
    Assist assist = new Assist();
    assist.setRequires(Assist.andEq("ztbh", ztbh));
    assist.setRequires(Assist.andEq("extenda", "3103"));
    assist.setRequires(Assist.andEq("fsrq", DateFormatUtil.getStringByDate(ywrq)));
    int deleteTCcyeb = tCcyebMapper.deleteTCcyeb(assist);
    //获取利率对象
    TLfjxb tLfjxb = tLfjxbMapper.selectByPrimaryKey(1);
    if (tLfjxb == null)

    {
      log.error("数据库中不存在两费计息对象");
    }

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
    if (tCjhbbList.isEmpty())

    {
      log.info("成交回报没有获取到数据集");
      return true;
    }

    //进行数据合笔
    Map<String, List<TCjhbb>> hbMap = new HashMap<>();
    for (
        TCjhbb tCjhbb : tCjhbbList
        )

    {
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
    for (
        List<TCjhbb> tCjhbbs : hbMap.values()
        )

    {
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
      } else {
        String day = tCjhbbs.get(0).getZqdm().substring(4, 6);
        if (day.equals("10")) {
          day = "1";
        } else if (day.equals("11")) {
          day = "2";
        } else if (day.equals("00")) {
          day = "3";
        } else if (day.equals("09")) {
          day = "4";
        } else if (day.equals("01")) {
          day = "7";
        } else if (day.equals("02")) {
          day = "14";
        } else if (day.equals("03")) {
          day = "28";
        } else if (day.equals("05")) {
          day = "91";
        } else if (day.equals("06")) {
          day = "182";
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
      String dqrq = CalcDate(DateFormatUtil.getStringByDate(tCjhbbs.get(0).getYwrq()), Integer.valueOf(tQsb.getQuantity()));
      tQsb.setExtendb(dqrq);
      //设置成交金额 成交数量
      Double CJJE = 0.00;
      Double CJSL = 0.00;
      for (TCjhbb tCjhbb : tCjhbbs) {
        CJJE = CJJE + tCjhbb.getCjje();
        CJSL = CJSL + tCjhbb.getCjsl();
      }
      if (tCjhbbs.get(0).getJysc() == 0) {
        CJSL = CJSL * 10;
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
      if (jysrz == null) {
        log.info("交易费率表中没有交易所融资利率");
        return false;
      }

      TJyfl jysrq = tJyflMapper.selectByPrimaryKey(3102);
      if (jysrq == null) {
        log.info("交易费率表中没有交易所融券利率");
        return false;
      }
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
      Double DQJE = CJJE - jssxf - jysxf - (tQsb.getYj()) + Zlx;
      tQsb.setYhs(DQJE);
      //设置股东代码
      tQsb.setExtendd(tCjhbbList.get(0).getGddm());
      //设置席位代码
      tQsb.setExtende(tCjhbbList.get(0).getXwbh());
      tQsbList.add(tQsb);
    }
    if (tQsbList.isEmpty()) {
      return false;
    } else {
      int count = 0;
      for (TQsb tqsb : tQsbList) {
        //获取应收利息科目值
        TCcyeb Obj1 = new TCcyeb();
        Obj1.setKjkmdm("6142");
        Obj1.setZtbh(tqsb.getZtbh());
        TCcyeb tCcyeb1 = tCcyebMapper.selectTCcyebByObj(Obj1);
        //获取回购清算款科目值
        TCcyeb Obj2 = new TCcyeb();
        Obj2.setKjkmdm("1232");
        Obj2.setZtbh(tqsb.getZtbh());
        TCcyeb tCcyeb2 = tCcyebMapper.selectTCcyebByObj(Obj2);
        //获取应付交易费用科目值
        TCcyeb Obj3 = new TCcyeb();
        Obj3.setKjkmdm("2002");
        Obj3.setZtbh(tqsb.getZtbh());
        TCcyeb tCcyeb3 = tCcyebMapper.selectTCcyebByObj(Obj3);
        //获取利息收入科目值
        TCcyeb Obj4 = new TCcyeb();
        Obj4.setKjkmdm("1142");
        Obj4.setZtbh(tqsb.getZtbh());
        TCcyeb tCcyeb4 = tCcyebMapper.selectTCcyebByObj(Obj4);
        //证券清算款-回购
        TCcyeb Obj5 = new TCcyeb();
        Obj5.setKjkmdm("1132");
        Obj5.setZtbh(ztbh);
        TCcyeb tCcyeb5 = tCcyebMapper.selectTCcyebByObj(Obj5);
        //应收利息   如果没有科目则插入科目      融资：付利息（应收利息减少，如果为负值 则表示应付利息） 融券：收利息（应收利息增加，如果为正值 表示应收利息）
        if (tCcyeb1 == null) {
          if (tqsb.getBs().equals("B")) {
            Obj1.setZqcb(-(tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            Obj1.setZqcb((tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          Obj1.setExtenda("应收利息-回购");
          tCcyebMapper.insertTCcyeb(Obj1);
        } else {
          if (tqsb.getBs().equals("B")) {
            tCcyeb1.setZqcb(tCcyeb1.getZqcb() - (tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            tCcyeb1.setZqcb(tCcyeb1.getZqcb() + (tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb1);
        }
        //回购清算款 如果没有科目则插入科目      融资：回购清算款（减少） 融券：回购清算款（增加）
        if (tCcyeb2 == null) {
          //融资 回购清算款=证券清算款（成交金额-交易费）-佣金  融资时回购清算款是贷方，因此应该减少
          if (tqsb.getBs().equals("B")) {
            Obj2.setZqcb(-(tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf() - tqsb.getYj()));
          } else {
            //融券 回购清算款=成交金额+交易费+佣金
            Obj2.setZqcb(tqsb.getAmount() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj());
          }
          Obj2.setExtenda("回购清算款-回购");
          tCcyebMapper.insertTCcyeb(Obj2);
        } else {
          if (tqsb.getBs().equals("B")) {
            tCcyeb2.setZqcb(tCcyeb2.getZqcb() - (tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf() - tqsb.getYj()));
          } else {
            tCcyeb2.setZqcb(tCcyeb2.getZqcb() + (tqsb.getAmount() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb2);
        }
        //应付交易费用 如果没有科目则插入科目      融资：交易费用（增加） 融券：交易费用（增加）
        if (tCcyeb3 == null) {
          Obj3.setZqcb(tqsb.getGhf() + tqsb.getJsf() + tqsb.getYj());
          Obj3.setExtenda("应付交易费用-回购");
          tCcyebMapper.insertTCcyeb(Obj3);
        } else {
          tCcyeb3.setZqcb(tCcyeb3.getZqcb() + tqsb.getGhf() + tqsb.getJsf() + tqsb.getYj());
          tCcyebMapper.updateTCcyebById(tCcyeb3);
        }
        //利息收入   如果没有科目则插入科目      融资：付出利息（利息收入减少，如果为负值 则表示利息支出） 融券：收入利息（利息收入增加，如果为正值 表示利息收入）
        if (tCcyeb4 == null) {
          if (tqsb.getBs().equals("B")) {
            Obj4.setZqcb(-(tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            Obj4.setZqcb((tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          Obj4.setExtenda("利息收入-回购");
          tCcyebMapper.insertTCcyeb(Obj4);
        } else {
          if (tqsb.getBs().equals("B")) {
            tCcyeb4.setZqcb(tCcyeb4.getZqcb() - (tqsb.getCjsr() + tqsb.getJsf() + tqsb.getGhf() + tqsb.getYj()));
          } else {
            tCcyeb4.setZqcb(tCcyeb4.getZqcb() + (tqsb.getCjsr() - tqsb.getJsf() - tqsb.getGhf() - tqsb.getYj()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb4);
        }
        if (tCcyeb5 == null) {
          if (tqsb.getBs().equals("B")) {
            Obj5.setZqcb((tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf()));
          } else {
            Obj5.setZqcb(-(tqsb.getAmount() + tqsb.getGhf() + tqsb.getJsf()));
          }
          Obj5.setExtenda("证券清算款-回购");
          tCcyebMapper.insertTCcyeb(Obj5);
        } else {
          //证券清算款       融资：证券清算款（增加） 融券：证券清算款（减少）
          if (tqsb.getBs().equals("B")) {
            //融资 证券清算款=成交金额-交易费用 融资时证券清算款是借方，因此应增加
            tCcyeb5.setZqcb(tCcyeb5.getZqcb() + (tqsb.getAmount() - tqsb.getGhf() - tqsb.getJsf()));
          } else {
            //融券 证券清算款=成交金额+交易费用
            tCcyeb5.setZqcb(tCcyeb5.getZqcb() - (tqsb.getAmount() + tqsb.getGhf() + tqsb.getJsf()));
          }
          tCcyebMapper.updateTCcyebById(tCcyeb5);
        }

        //生成持仓数据
        int i1 = tQsbMapper.insertTQsb(tqsb);
        TCcyeb tCcyeb = new TCcyeb();
        tCcyeb.setZqdm(tqsb.getZqcode());
        tCcyeb.setZtbh(tqsb.getZtbh());
        tCcyeb.setZqcb(tqsb.getAmount());
        tCcyeb.setExtendc(String.valueOf(tqsb.getId()));
        tCcyeb.setLjjx(tqsb.getCjsr());
        tCcyeb.setFsrq(DateFormatUtil.getDateByString(tqsb.getExtenda()));
        tCcyeb.setExtenda("3103");
        tCcyeb.setExtendb(tqsb.getExtendb());
        int i2 = tCcyebMapper.insertTCcyeb(tCcyeb);
        if (i1 != 0 && i2 != 0) {
          count++;
        }
      }
      //新增持仓余额变动数据
      if (count != 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  //根据给定日期和天数计算到期时间
  @Override
  public String CalcDate(String start, int day) throws ParseException {
    String end = "";
    String[] Holiday = {"2018/6/18", "2018/9/24", "2018/10/1", "2018/10/2", "2018/10/3",
        "2018/10/4", "2018/10/5", "2018/12/31", "2019/1/1", "2019/2/4",
        "2019/2/5", "2019/2/6", "2019/2/7", "2019/2/8", "2019/4/5",
        "2019/5/1", "2019/5/2", "2019/5/3", "2019/6/7", "2019/9/13",
        "2019/10/1", "2019/10/2", "2019/10/3", "2019/10/4", "2019/10/7"};
    List<String> Holidays = Arrays.asList(Holiday);
    String[] Workday = {"2018/9/29", "2018/9/30", "2018/12/29", "2019/2/2", "2019/2/3",
        "2019/4/28", "2019/5/5", "2019/9/29", "2019/10/12"};
    List<String> Workdays = Arrays.asList(Workday);
    Date dateByString = DateFormatUtil.getDateByString(start);
    Long nowTime = dateByString.getTime();
    int i = 0;
    while (i != day) {
      i++;
      int dayTime = i * 24 * 60 * 60 * 1000;
      Date t = new Date(nowTime + dayTime);
      int week = t.getDay();
      String ymd = DateFormatUtil.getStringByDate(t);
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
