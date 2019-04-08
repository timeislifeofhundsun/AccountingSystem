package com.hundsun.accountingsystem.TGp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TCcyebMapper;
import com.hundsun.accountingsystem.Global.mapper.TPzbMapper;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [ztbh, rq]
     * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TPzb>
     * @Description 获取所有凭证
     **/
    @Override
    public JSONArray get_pz(int ztbh, Date rq) {
        JSONArray returnData = new JSONArray();
        //查询条件
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("rq",rq));
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        List<TPzb> tPzbs = tPzbMapper.selectTPzb(assist);
        for (TPzb tPzb : tPzbs){
            JSONObject obj = new JSONObject();
            obj.put("ztbh",tPzb.getZtbh());
            obj.put("rq", DateFormatUtil.getStringByDate(tPzb.getRq()));
            obj.put("kmmc",tPzb.getKmms());
            obj.put("zy",tPzb.getZy());
            obj.put("BS",tPzb.getBs());
            obj.put("jfje",tPzb.getBs().equals("借") ? tPzb.getJe() : 0);
            obj.put("dfje",tPzb.getBs().equals("贷") ? tPzb.getJe() : 0);
            obj.put("pzid",tPzb.getPzid());
            returnData.add(obj);
        }
        return returnData;
    }

    @Override
    public JSONArray get_bb(int ztbh) {
        JSONArray returnData = new JSONArray();
        Assist assist = new Assist();
        assist.setRequires(Assist.andEq("ztbh",ztbh));
        List<TCcyeb> tCcyebs = tCcyebMapper.selectTCcyeb(assist);
        for (TCcyeb tCcyeb : tCcyebs){
            JSONObject obj = new JSONObject();
            obj.put("ztbh",tCcyeb.getZtbh());
            obj.put("kmmc",tCcyeb.getExtenda());
            obj.put("sl", tCcyeb.getCysl());
            obj.put("zqcb",tCcyeb.getZqcb());
            obj.put("ljgz",tCcyeb.getLjgz());
            returnData.add(obj);
        }
        return returnData;
    }

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [tQsb]
     * @Return java.util.List<com.hundsun.accountingsystem.Global.bean.TPzb>
     * @Description 凭证Service层
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

                String zqjg = tZqxxMapper.selectByZqdm(tQsb_pz.getZqcode()).getZqjg();//获取证券简称

                if (tQsb_pz.getYwlb() == 1309){
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
