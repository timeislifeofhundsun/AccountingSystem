package com.hundsun.accountingsystem.TGp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.bean.*;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Aurhor yangjf25257
 * @ClassName XgPzbServiceImpl
 * @Date 2019/3/22 10:38
 * @Version 1.0
 * @Description
 **/
@Service
public class XgPzbServiceImpl implements XgPzbService {

    @Autowired
    private TSequenceService tSequenceService;

    @Autowired
    TCcyebMapper tCcyebMapper;

    @Autowired
    TQsbMapper tQsbMapper;

    @Autowired
    TPzbMapper tPzbMapper;

    @Autowired
    TZqxxMapper tZqxxMapper;

    @Autowired
    HttpServletRequest request;

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [ztbh, rq]
     * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TPzb>
     * @Description 获取所有凭证
     **/
    @Override
    public JSONArray get_pz(int ztbh, Date rq) {
        DecimalFormat df   = new DecimalFormat("######0.00"); //保留两位有效数字
        JSONArray returnData = new JSONArray();
        //查询条件
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("rq",rq));
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        List<TPzb> tPzbs = tPzbMapper.selectTPzb(assist);
        for (TPzb tPzb : tPzbs){
            JSONObject obj = new JSONObject();
            HttpSession session = request.getSession();
            List<TZtxxb> ztxxbs = (List<TZtxxb>) session.getAttribute("ztxxbs");
            String ztxx = null;
            for (TZtxxb tZtxxb : ztxxbs){
                if (tPzb.getZtbh().equals(tZtxxb.getZtbh())){
                    ztxx = tPzb.getZtbh() + "_" + tZtxxb.getName();
                    obj.put("ztbh",ztxx);
                    break;
                }
            }
            obj.put("rq", DateFormatUtil.getStringByDate(tPzb.getRq()));
            obj.put("kmmc",tPzb.getKmms());
            obj.put("zy",tPzb.getZy());
            obj.put("BS",tPzb.getBs());
            obj.put("jfje",tPzb.getBs().equals("借") ? Double.valueOf(df.format(tPzb.getJe())) : df.format(0));
            obj.put("dfje",tPzb.getBs().equals("贷") ? Double.valueOf(df.format(tPzb.getJe())) : df.format(0));
            obj.put("pzid",tPzb.getPzid());
            returnData.add(obj);
        }
        return returnData;
    }

    /**
    * @Author yangjf25257
    * @MethodName get_bb
     * @Param [ztbh]
     * @Return com.alibaba.fastjson.JSONArray
     * @Description 根据账套编号获取报表
     **/
    @Override
    public JSONArray get_bb(int ztbh) {
        DecimalFormat    df   = new DecimalFormat("######0.00"); //保留两位有效数字
        JSONArray returnData = new JSONArray();
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        List<TCcyeb> tCcyebs = tCcyebMapper.selectTCcyeb(assist);

        //新建汇总,把相同类型的放在一个list中
        List<TCcyeb> tCcyeb_yhck = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_yfjy = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_tzsy = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_gpmm = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_zqqs = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_jyfy = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_jinj = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_huig = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_other = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_all = new ArrayList<TCcyeb>();//存放两个汇总的list

        for (TCcyeb tCcyeb : tCcyebs){
            if (tCcyeb.getExtenda().contains("银行存款")){
                tCcyeb_yhck.add(tCcyeb);
                continue;
            }
            else if (tCcyeb.getExtenda().contains("应付交易费用")){
                tCcyeb_yfjy.add(tCcyeb);
                continue;
            }
            else if (tCcyeb.getExtenda().contains("投资收益")){
                tCcyeb_tzsy.add(tCcyeb);
                continue;
            }
            else if (tCcyeb.getExtenda().contains("11") || tCcyeb.getExtenda().contains("13")){
                tCcyeb_gpmm.add(tCcyeb);
                continue;
            }
            else if (tCcyeb.getExtenda().contains("证券清算款_")){
                tCcyeb_zqqs.add(tCcyeb);
                continue;
            }  else if (tCcyeb.getExtenda().contains("交易费用_")){
                tCcyeb_jyfy.add(tCcyeb);
                continue;
            } else if (tCcyeb.getZqnm() != null && tCcyeb.getZqnm().equals(4)) {
                tCcyeb_jinj.add(tCcyeb);
                continue;
            } else if (tCcyeb.getExtenda() != null && tCcyeb.getExtenda().contains("3101") || tCcyeb.getExtenda().contains("3102") || tCcyeb.getExtenda().contains("3103")) {
                tCcyeb_huig.add(tCcyeb);
            }
            else {
                tCcyeb_other.add(tCcyeb);
            }
        }

        //汇总，把上面新建汇总的list的值进行合并
        List<TCcyeb> tCcyeb_yhck_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_yfjy_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_tzsy_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_gpmm_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_zqqs_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_jyfy_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_jinj_all = new ArrayList<TCcyeb>();
        List<TCcyeb> tCcyeb_huig_all = new ArrayList<TCcyeb>();
        while (true){
            if (tCcyeb_yhck.size() > 0){
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_yhck){
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setZtbh(tCcyeb_yhck.get(0).getZtbh()).setZqcb(zqcb).setExtenda("银行存款汇总");
                tCcyeb_yhck_all.add(tCcyeb_temp);
            }
            if (tCcyeb_gpmm.size() > 0){
                double ljgz = 0.00;
                double zqcb = 0.00;
                int cysl = 0;
                for (TCcyeb tCcyeb : tCcyeb_gpmm){
                    ljgz += tCcyeb.getLjgz();
                    cysl += tCcyeb.getCysl();
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setLjgz(ljgz).setZtbh(tCcyeb_yhck.get(0).getZtbh()).setCysl(cysl).setZqcb(zqcb).setExtenda("股票交易汇总");
                tCcyeb_gpmm_all.add(tCcyeb_temp);
            }
            if (tCcyeb_jinj.size() > 0) {
                double ljgz = 0.00;
                double zqcb = 0.00;
                int cysl = 0;
                for (TCcyeb tCcyeb : tCcyeb_jinj){
                    ljgz += tCcyeb.getLjgz();
                    cysl += tCcyeb.getCysl();
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setLjgz(ljgz).setZtbh(tCcyeb_jinj.get(0).getZtbh()).setCysl(cysl).setZqcb(zqcb).setExtenda("基金交易汇总");
                tCcyeb_jinj_all.add(tCcyeb_temp);
            }
            if (tCcyeb_huig.size() > 0) {
                double ljjx = 0.00;
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_huig){
                	ljjx += tCcyeb.getLjjx();
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setLjjx(ljjx).setZtbh(tCcyeb_huig.get(0).getZtbh()).setZqcb(zqcb).setExtenda("回购交易汇总");
                tCcyeb_huig_all.add(tCcyeb_temp);
            }
            if (tCcyeb_tzsy.size() > 0){
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_tzsy){
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setZtbh(tCcyeb_yhck.get(0).getZtbh()).setZqcb(zqcb).setExtenda("资产收益汇总");
                tCcyeb_tzsy_all.add(tCcyeb_temp);
            }
            if (tCcyeb_yfjy.size() > 0){
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_yfjy){
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setZtbh(tCcyeb_yhck.get(0).getZtbh()).setZqcb(zqcb).setExtenda("应付交易费用汇总");
                tCcyeb_yfjy_all.add(tCcyeb_temp);
            }
            if (tCcyeb_zqqs.size() > 0){
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_zqqs){
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setZtbh(tCcyeb_yhck.get(0).getZtbh()).setZqcb(zqcb).setExtenda("证券清算款汇总");
                tCcyeb_zqqs_all.add(tCcyeb_temp);
            }
            if (tCcyeb_jyfy.size() > 0){
                double zqcb = 0.00;
                for (TCcyeb tCcyeb : tCcyeb_jyfy){
                    zqcb += tCcyeb.getZqcb();
                }
                TCcyeb tCcyeb_temp = new TCcyeb();
                tCcyeb_temp.setZtbh(tCcyeb_yhck.get(0).getZtbh()).setZqcb(zqcb).setExtenda("交易费用汇总");
                tCcyeb_jyfy_all.add(tCcyeb_temp);
            }
            break;
        }

        //总共的合并
        tCcyeb_all.addAll(tCcyeb_yhck_all);
        tCcyeb_all.addAll(tCcyeb_yhck);
        tCcyeb_all.addAll(tCcyeb_yfjy_all);
        tCcyeb_all.addAll(tCcyeb_yfjy);
        tCcyeb_all.addAll(tCcyeb_tzsy_all);
        tCcyeb_all.addAll(tCcyeb_tzsy);
        tCcyeb_all.addAll(tCcyeb_gpmm_all);
        tCcyeb_all.addAll(tCcyeb_gpmm);
        tCcyeb_all.addAll(tCcyeb_jinj_all);
        tCcyeb_all.addAll(tCcyeb_jinj);
        tCcyeb_all.addAll(tCcyeb_huig_all);
        tCcyeb_all.addAll(tCcyeb_huig);
        tCcyeb_all.addAll(tCcyeb_zqqs_all);
        tCcyeb_all.addAll(tCcyeb_zqqs);
        tCcyeb_all.addAll(tCcyeb_jyfy_all);
        tCcyeb_all.addAll(tCcyeb_jyfy);
        tCcyeb_all.addAll(tCcyeb_other);

        for (TCcyeb tCcyeb : tCcyeb_all){
            JSONObject obj = new JSONObject();
            if (tCcyeb.getZqdm() == null){
                obj.put("kmmc",tCcyeb.getExtenda());
            } else {
                String kmmc = null;
                HttpSession session = request.getSession();
                List<TZqxx> zqxxes = (List<TZqxx>) session.getAttribute("zqxxes");
                for (TZqxx tZqxx : zqxxes){
                    /**
                     * 000001股票单独判断
                     */
                	if(tCcyeb.getZqdm().equals("000001") && tCcyeb.getExtenda().equals("11")) {
                		if (tZqxx.getZqdm().equals("000001") && tZqxx.getZqlb()==1){
                			 kmmc = tCcyeb.getZqdm() + "_" + tZqxx.getZqjg();
                             obj.put("kmmc",kmmc);
                             break;
                		}else {
                			continue;
                		}
                	}else {
                        if (tZqxx.getZqdm().equals(tCcyeb.getZqdm())){
                            kmmc = tCcyeb.getZqdm() + "_" + tZqxx.getZqjg();
                            obj.put("kmmc",kmmc);
                            break;
                        }
                	}
                }
            }
            obj.put("sl", tCcyeb.getCysl());
            obj.put("zqcb",df.format(tCcyeb.getZqcb()));
            obj.put("ljgz",tCcyeb.getLjgz() == null ? df.format(0) :df.format(tCcyeb.getLjgz()));
            if (tCcyeb.getCysl() != null && tCcyeb.getCysl() > 0 && tCcyeb.getZqcb() > 0){
                double dwcb = tCcyeb.getZqcb() / tCcyeb.getCysl();
                obj.put("dwcb",df.format(dwcb));
            }
            obj.put("ljjx",tCcyeb.getLjjx() == null ? df.format(0) : df.format(tCcyeb.getLjjx()));
            returnData.add(obj);
        }
        return returnData;
    }

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [tQsb]
     * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TPzb>
     * @Description 生成凭证Service层
     **/
    @Override
    public boolean insert_pz(int ztbh, Date rq) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //条件
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("rq",rq));
        assist.setRequires(Assist.andEq("ztbh",ztbh));

        tPzbMapper.deleteTPzb(assist);//凭证库去重
        List<TQsb> tQsbs_list = tQsbMapper.selectTQsb(assist);//从清算库中获取数据

        if (tQsbs_list.size() > 0){
            TPzb tPzb = new TPzb();//凭证插入实体

            for (TQsb tQsb_pz : tQsbs_list){
                String zqjg = null;

                if (tQsb_pz.getYwlb() == 1309){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                	
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "新股中签-网下" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//网下中签摘要
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("股票投资成本").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1309(tPzb);
                    tPzb.setZy(zy).setKmms("证券清算款").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1309(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1308){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                	
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "新股中签-网下" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//网下缴款摘要
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("证券清算款").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1308(tPzb);
                    tPzb.setZy(zy).setKmms("银行存款").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1308(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1311){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                	
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "新发行股票" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]" + "上市流通";//网下上市流通摘要
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("股票投资-A股成本").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1311(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-A股估值增值").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getLumpsum())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1311(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-新股成本").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1311(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-新股估值增值").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getLumpsum())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1311(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1302){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "网上新股中签" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//网上新股中签
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("证券清算款").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1302(tPzb);
                    tPzb.setZy(zy).setKmms("待缴证券清算款").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1302(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1303){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "网上新股新股缴款" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//网上新股缴款
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("待缴证券清算款").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1303(tPzb);
                    tPzb.setZy(zy).setKmms("证券清算款").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1303(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1304){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "股票估值增值" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//股票估值增值
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("新股估值增值" + zqjg).setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getGyjzbd())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1304(tPzb);
                    tPzb.setZy(zy).setKmms("公允价值变动损益").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getGyjzbd())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1304(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1305){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "新发行股票" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]" + "上市流通";//网上上市流通
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("股票投资-A股成本").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1305(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-A股估值增值").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getLumpsum())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1305(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-新股成本").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1305(tPzb);
                    tPzb.setZy(zy).setKmms("股票投资-新股估值增值").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getLumpsum())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1305(tPzb);
                    continue;
                }

                if (tQsb_pz.getYwlb() == 1306){
                	zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称
                    String zy = "[" + sdf.format(tQsb_pz.getRq()) +"]" + "网上新股中签交收" + "[" + tQsb_pz.getZqcode() + "]" + "[" + zqjg + "]";//网上新股中签交收
                    int pzid = tSequenceService.getSequenceByName("pz");
                    tPzb.setZy(zy).setKmms("股票投资").setBs("借").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1306(tPzb);
                    tPzb.setZy(zy).setKmms("证券清算款").setBs("贷").setZtbh(tQsb_pz.getZtbh()).setRq(tQsb_pz.getRq()).setJe(tQsb_pz.getAmount())
                            .setExtendf("13").setPzid(pzid);
                    insert_pz_1306(tPzb);
                    continue;
                }
            }
        }

        return true;
    }


    //网下中签
    private int insert_pz_1309(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //网下缴款
    private int  insert_pz_1308(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //网下上市流通
    private int  insert_pz_1311(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //新股中签（网上）
    private int  insert_pz_1302(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //新股缴款（网上）
    private int  insert_pz_1303(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //估值增值
    private int  insert_pz_1304(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //新股流通（网上）
    private int  insert_pz_1305(TPzb tPzb){
        return tPzbMapper.insertTPzb(tPzb);
    }

    //新股中签交收（网上）
    private int  insert_pz_1306(TPzb tPzb){
        return  tPzbMapper.insertTPzb(tPzb);
    }
}
