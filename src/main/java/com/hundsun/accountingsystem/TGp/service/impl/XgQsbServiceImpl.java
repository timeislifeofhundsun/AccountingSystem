package com.hundsun.accountingsystem.TGp.service.impl;

import com.hundsun.accountingsystem.Global.bean.*;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TGdxxbMapper;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XgQsbServiceImpl
 * @Date 2019/3/22 10:17
 * @Version 1.0
 * @Description 清算库ServiceImpl
 **/
@Service
public class XgQsbServiceImpl implements XgQsbService {

    @Autowired
    FileParsing  fileParsing;

    @Autowired
    TQsbMapper tQsbMapper;

    @Autowired
    TCcyebMapper tCcyebMapper;

    @Autowired
    THqbMapper tHqbMapper;

    @Autowired
    TGdxxbMapper tGdxxbMapper;

    /**
     * @Author yangjf25257
     * @MethodName insert_xg
     * @Param [path]
     * @Return int
     * @Description 新股中签、新股缴款、新股中签交收放入清算库中
     * 一、将新股数据放进清算库
     * 二、把数据同步到持仓余额表
     * 三、业务场景模拟为一个接口文件只能存放一天的数据操作，并且数据量只有一条
     **/
    private String insert_xg_qsk(String path) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(path == null || path.equals("")){//判空
            return "地址错误";
        }

        List<TQsb> list = null;//存放从dbf文件中取出的值
        String i = "";//i代表清算库去重操作
        String jj = "";//代表持仓库恢复
        String ii = "";//ii代表清算库插入操作
        String iii = "";//代表持仓库更新
        String j = "持仓库未增加记录 ";//j代表持仓库数据操作

        try {
            System.out.println(path);
            list = fileParsing.ReadXGDBf(path);
            if (list == null){
                return "错误文件";
            }
            //清算库去重和持仓表恢复操作
            Assist assist = null;
            for (TQsb tQsb : list){  //清算库去重操作
                assist = new Assist();
                assist.setRequires(Assist.andEq("rq",tQsb.getRq()));
                assist.setRequires(Assist.andEq("ywlb",tQsb.getYwlb()));
                assist.setRequires(Assist.andEq("zqcode",tQsb.getZqcode()));

                List<TQsb> list1 = tQsbMapper.selectTQsb(assist);//有且只有一条数据
                if (list1.size() != 0){//根据日期和证券代码能查到的有且只有一条数据

                    TQsb tQsbMap = list1.get(0);
                    //根据数据更新持仓余额表
                    TCcyeb tCcyeb_update = new TCcyeb();
                    tCcyeb_update.setZqdm(tQsb.getZqcode());
                    tCcyeb_update.setZtbh(tQsb.getZtbh());
                    Assist assist1 = new Assist();
                    assist1.setRequires(Assist.andEq("zqdm",tQsb.getZqcode()));
                    assist1.setRequires(Assist.andEq("ztbh",tQsb.getZtbh()));
                    if (tCcyebMapper.selectTCcyeb(assist1).size() != 0){//持仓库判空操作
                        int n = tQsbMap.getQuantity();
                        tCcyeb_update.setCysl(-n);
                        tCcyebMapper.update_cysl(tCcyeb_update);
                        jj = "持仓库恢复  ";
                        System.out.println("持仓库恢复");
                    }
                }

                //新股缴款去重
                Assist assist_xgjk = new Assist();
                Date date_xgjk = DateFormatUtil.getNextWorkDay(tQsb.getRq());//中签是在缴款后一天
                assist_xgjk.setRequires(Assist.andEq("rq",date_xgjk));
                assist_xgjk.setRequires(Assist.andEq("zqcode",tQsb.getZqcode()));
                tQsbMapper.deleteTQsb(assist_xgjk);//新股缴款去重

                //新股中签交收去重
                Assist assist_zqjs = new Assist();
                assist_zqjs.setRequires(Assist.andEq("rq",DateFormatUtil.getNextWorkDay(date_xgjk)));
                assist_zqjs.setRequires(Assist.andEq("zqcode",tQsb.getZqcode()));
                tQsbMapper.deleteTQsb(assist_zqjs);//新股缴款去重

                //新股中签去重
                int y = tQsbMapper.deleteTQsb(assist);
                if (y == 1){
                    i = "清算数据库数据去重  ";
                    System.out.println("清算数据库数据去重");
                }
            }
        } catch (IOException e) {//进行清算库去重
            e.printStackTrace();
        }

        //把数据插入清算库中和持仓库中
        if (list != null) {
            int x = tQsbMapper.insertTQsbByBatch(list);//当x = 1的插入成功
            if (x > 0){
                ii = "清算库数据插入 ";
                System.out.println("清算库数据插入");
            }

            //新股缴款和新股中签交收放入清算库中
           for (int w = 0; w < list.size(); w ++){
               //新股缴款
               Date date_qsk = DateFormatUtil.getNextWorkDay(list.get(w).getRq());//中签交收是在缴款后一天
               list.get(w).setRq(date_qsk);
               list.get(w).setYwlb(1303);
               tQsbMapper.insertTQsb(list.get(w));

               //新股中签交收
               list.get(w).setRq(DateFormatUtil.getNextWorkDay(date_qsk));
               list.get(w).setYwlb(1306);
               tQsbMapper.insertTQsb(list.get(w));
           }

            //根据数据更新持仓余额表
            for (TQsb tQsb : list){
                Assist assist1 = new Assist();
                assist1.setRequires(Assist.andEq("ztbh",tQsb.getZtbh()));
                assist1.setRequires(Assist.andEq("zqdm",tQsb.getZqcode()));
                if (tCcyebMapper.selectTCcyeb(assist1).size() != 0) {//判空
                    TCcyeb tCcyeb2 = new TCcyeb();
                    tCcyeb2.setCysl(tQsb.getQuantity());
                    tCcyeb2.setZqdm(tQsb.getZqcode());
                    tCcyeb2.setZtbh(tQsb.getZtbh());
                    tCcyebMapper.update_cysl(tCcyeb2);
                    iii = "持仓库更新  ";
                    System.out.println("持仓库更新");
                }
            }

            //向持仓库添加记录
            for (TQsb tQsb : list){
                Assist assist =  new Assist();
                TCcyeb tCcyeb = null;
                assist.setRequires(Assist.andEq("zqdm",tQsb.getZqcode()));
                if (tCcyebMapper.selectTCcyeb(assist).size() == 0){//判断持仓余额表对应的证券代码是否为空
                    tCcyeb = new TCcyeb();
                    tCcyeb.setZqdm(tQsb.getZqcode()).setCysl(tQsb.getQuantity()).setZqcb(tQsb.getAmount())
                            .setZtbh(tQsb.getZtbh()).setFsrq(sdf.parse(tQsb.getExtenda())).setLjgz(0.0).setExtenda("13");
                    int y = tCcyebMapper.insertTCcyeb(tCcyeb);
                    if (y == 1){
                        j = "持仓库数据插入  ";
                        System.out.println("持仓库数据插入");
                    }
                }
            }

            return i + jj  + ii + iii + j;
        }

        return "操作失败";

    }

    /**
    * @Author yangjf25257
    * @MethodName insete_gzzz_qsk
     * @Param [ztbh, date]
     * @Return int
     * @Description 网上估值增值计算方法
     * 1、根据账套编号去持仓余额表查找证券代码
     * 2、根据证券代码和日期去行情表查行情
     * 3、算出估值增值以后，更新持仓表，再加上业务类别放入清算库
     * 4、计算公式：估值增值=收盘价*当前时刻总数量-当前时刻总成本-上一时刻估值增值
     **/
    private String insert_gzzz_qsk(int ztbh, Date date) {

        Double gzzz = 0.00;//估值增值变量

        //根据账套编号去持仓余额表查找证券代码
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        List<TCcyeb> list = tCcyebMapper.selectTCcyeb(assist);

        if (list.size() == 0){
            return "持仓表中没有数据";
        }

        for (TCcyeb tCcyeb : list){
            //根据证券代码和日期去行情表查行情
            Assist assist_ccye = new Assist();
            assist_ccye.setRequires(Assist.andEq("zqdm",tCcyeb.getZqdm()));
            assist_ccye.setRequires(Assist.andEq("hqrq",date));
            THqb tHqb = null;
            if (tHqbMapper.selectTHqb(assist_ccye).size() > 0){
                tHqb = tHqbMapper.selectTHqb(assist_ccye).get(0);//行情表有且只有一条数据
            } else {//如果当日无行情，则结束剩余操作，开始计算新的一次循环
               continue;
            }

            //持仓库恢复以及清算库去重
            TQsb tQsb_qsk = new TQsb();
            tQsb_qsk.setYwlb(1304).setRq(date);
            TQsb tQsb_qc = tQsbMapper.selectTQsbByObj(tQsb_qsk);//根据业务类别和日期查询
            if (tQsb_qc != null){
                double qc = tQsb_qc.getGyjzbd() * (-1);
                tCcyeb.setLjgz(qc);
               tCcyebMapper.update_ljgz(tCcyeb);//持仓余额表恢复
                Assist assist_qc = new Assist();
                assist_qc.setRequires(Assist.andEq("zqcode",tCcyeb.getZqdm()));
                assist_qc.setRequires(Assist.andEq("rq",date));
                assist_qc.setRequires(Assist.andEq("ywlb",1304));
                tQsbMapper.deleteTQsb(assist_qc);//根据证券代码和日期和业务类别
            }

            //更新持仓库
            //估值增值计算公式：估值增值=收盘价*当前时刻总数量-当前时刻总成本-上一时刻估值增值
            Assist assist_gzzz = new Assist();
            assist_gzzz.setRequires(Assist.andEq("zqdm",tCcyeb.getZqdm()));
            TCcyeb tCcyeb_gzzz = tCcyebMapper.selectTCcyeb(assist_gzzz).get(0);
            gzzz = tCcyeb_gzzz.getCysl() * tHqb.getJrsp() - tCcyeb_gzzz.getZqcb() - tCcyeb_gzzz.getLjgz();
            System.out.println(gzzz);
            tCcyeb.setLjgz(gzzz);
            tCcyebMapper.update_ljgz(tCcyeb);//ljgz累计估值



            //加上业务类别放入清算库
            TQsb tQsb = new TQsb();
            tQsb.setYwlb(1304).setZqcode(tCcyeb.getZqdm()).setGyjzbd(gzzz).setZtbh(ztbh).setRq(date);
            tQsbMapper.insertTQsb(tQsb);

        }
        return "估值增值计算完成";
    }

    /**
    * @Author yangjf25257
    * @MethodName insert_xgjk_qsk
     * @Param [ztbh, date]
     * @Return java.lang.String
     * @Description 新股上市流通放入清算库中
     * 1、根据证券账号（股东代码）和席位号去股东信息表中拿账套编号
     * 2、根据账套编号和证券代码去持仓表中更新数据
     * 3、把相应的数据加上业务代码放入清算库中
     **/
    private String insert_sclt_qsk(String path) throws ParseException {
        if (path.equals("") || path.equals(null)){
            return "地址错误";
        }
        List<String> list = null;
        try {
            list = FileParsing.ReadZQBDDbf(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list.size() == 0){
            return "没有查询到数据";
        }

        //1、根据证券账号（股东代码）和席位号去股东信息表中拿账套编号
        TGdxxb tGdxxb = new TGdxxb();
        tGdxxb.setGddm(list.get(0));
        tGdxxb.setXwbh(list.get(1));
        TGdxxb tGdxxb_sclt = tGdxxbMapper.selectByGddmAndXwbh(tGdxxb);

        //2、根据账套编号和证券代码去持仓表中更新数据
        TCcyeb tCcyeb = new TCcyeb();
        tCcyeb.setZqdm(list.get(2));
        tCcyeb.setZtbh(tGdxxb_sclt.getZtbh());
        TCcyeb tCcyeb_sclt = tCcyebMapper.selectTCcyebByObj(tCcyeb);//后面存入清算库中需要

        if (tCcyeb_sclt.getExtenda().equals("13")){
            tCcyebMapper.update_ltlx(tCcyeb);
        } else {
            return "已经上市流通，不需要再操作";
        }

        //根据业务类别去清算库中去重（上市流通）
        Assist assist_sc_qsk = new Assist();
        assist_sc_qsk.setRequires(Assist.andEq("ywlb",1305));
        if (tQsbMapper.selectTQsb(assist_sc_qsk) != null){
            tQsbMapper.deleteTQsb(assist_sc_qsk);
        }

        //3、把操作数据放入清算库中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        TQsb tQsb = new TQsb();
        tQsb.setYwlb(1305).setRq(DateFormatUtil.getNextWorkDay(sdf.parse(list.get(3)))).setZqcode(tCcyeb_sclt.getZqdm()).setAmount(tCcyeb_sclt.getZqcb())
        .setLumpsum(tCcyeb_sclt.getLjgz()).setZtbh(tCcyeb_sclt.getZtbh());
        tQsbMapper.insertTQsb(tQsb);
        return "上市流通成成功";
    }

    /**
    * @Author yangjf25257
    * @MethodName xgqs
     * @Param [path_zq, path_sc, ztbh, date]
     * @Return java.lang.String
     * @Description 调用三个接口
     **/
    @Override
    public String xgqs(String path_xg_qsk, String path_sclt_qsk, int ztbh, Date date) throws ParseException {
        if (path_xg_qsk != null && path_sclt_qsk != null){
            return "不能同时清算";
        }
      String n = insert_xg_qsk(path_xg_qsk);
      System.out.println(n);
      String m = insert_sclt_qsk(path_sclt_qsk);
      System.out.println(m);
      insert_gzzz_qsk(ztbh, date);
      return "清算成功";
    }
}
