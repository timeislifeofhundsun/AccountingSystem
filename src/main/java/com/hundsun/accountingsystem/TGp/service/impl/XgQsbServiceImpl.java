package com.hundsun.accountingsystem.TGp.service.impl;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.THqb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.THqbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.TGp.service.XgQsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    TQsbMapper tQsbMapper;

    @Autowired
    TCcyebMapper tCcyebMapper;

    @Autowired
    THqbMapper tHqbMapper;

    /**
     * @Author yangjf25257
     * @MethodName insert_xg
     * @Param [path]
     * @Return int
     * @Description
     * 一、将新股数据放进清算库
     * 二、把数据同步到持仓余额表
     * 三、业务场景模拟为一个接口文件只能存放一天的数据操作，并且数据量只有一条
     **/
    @Override
    public String insert_xg_qsk(String path) {

        if(path == null || path.equals("")){//判空
            return "输入错误地址";
        }

        List<TQsb> list = null;//存放从dbf文件中取出的值
        String i = "";//i代表清算库去重操作
        String jj = "";//代表持仓库恢复
        String ii = "";//ii代表清算库插入操作
        String iii = "";//代表持仓库更新
        String j = "持仓库未增加记录 ";//j代表持仓库数据操作

        try {

            list = FileParsing.ReadXGDBf(path);

            //清算库去重和持仓表恢复操作
            Assist assist = null;
            for (TQsb tQsb : list){  //清算库去重操作
                assist = new Assist();
                assist.setRequires(Assist.andEq("rq",tQsb.getRq()));
                assist.setRequires(Assist.andEq("zqcode",tQsb.getZqcode()));

                List<TQsb> list1 = tQsbMapper.selectTQsb(assist);//有且只有一条数据
                if (list1.size() != 0){//根据日期和证券代码能查到的有且只有一条数据

                    TQsb tQsbMap = list1.get(0);
                    //根据数据更新持仓余额表
                    TCcyeb tCcyeb_update = new TCcyeb();
                    tCcyeb_update.setZqdm(tQsb.getZqcode());
                    Assist assist1 = new Assist();
                    assist1.setRequires(Assist.andEq("zqdm",tQsb.getZqcode()));
                    if (tCcyebMapper.selectTCcyeb(assist1).size() != 0){//持仓库判空操作
                        int n = tQsbMap.getQuantity();
                        tCcyeb_update.setCysl(-n);
                        tCcyebMapper.update_cysl(tCcyeb_update);
                        jj = "持仓库恢复  ";
                        System.out.println("持仓库恢复");
                    }
                }

                int y = tQsbMapper.deleteTQsb(assist);//去重
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

            //根据数据更新持仓余额表
            for (TQsb tQsb : list){
                Assist assist1 = new Assist();
                assist1.setRequires(Assist.andEq("zqdm",tQsb.getZqcode()));
                if (tCcyebMapper.selectTCcyeb(assist1).size() != 0) {//判空
                    TCcyeb tCcyeb2 = new TCcyeb();
                    tCcyeb2.setCysl(tQsb.getQuantity());
                    tCcyeb2.setZqdm(tQsb.getZqcode());
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
                            .setZtbh(tQsb.getZtbh()).setFsrq(tQsb.getRq()).setLjgz(0.0).setExtenda("13");
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
    @Override
    public String insete_gzzz_qsk(int ztbh, Date date) {

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

            //估值增值计算公式：估值增值=收盘价*当前时刻总数量-当前时刻总成本-上一时刻估值增值
            gzzz = tCcyeb.getCysl() * tHqb.getJrsp() - tCcyeb.getZqcb() - tCcyeb.getLjgz();

            //更新持仓库
           tCcyeb.setLjgz(gzzz);
            tCcyebMapper.update_ljgz(tCcyeb);

            //加上业务类别放入清算库
            TQsb tQsb = new TQsb();
            tQsb.setYwlb(1304).setZqcode(tCcyeb.getZqdm()).setGyjzbd(gzzz).setZtbh(ztbh).setRq(date);
            tQsbMapper.insertTQsb(tQsb);

        }
        System.out.println(gzzz);
        return "估值增值计算完成";
    }
}
