package com.hundsun.accountingsystem.TGp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.service.LfjxQsService;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.GPPZService;
import com.hundsun.accountingsystem.TGp.service.XgPzbService;
import com.hundsun.accountingsystem.THg.Service.HGPZBService;
import com.hundsun.accountingsystem.TJj.service.TjjScpzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XGPzController
 * @Date 2019/4/2 9:59
 * @Version 1.0
 * @Description
 **/
@RestController
@Slf4j
public class XGPzController {

    @Autowired
    XgPzbService xgPzbService;

    @Autowired
    GPPZService gppzService;

    @Autowired
    TjjScpzService tjjScpzService;

    @Autowired
    HGPZBService hgpzbService;
    
    @Autowired
    LfjxQsService lfjxQsService;    

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [tQsb]
     * @Return java.lang.String
     * @Description 生成凭证controller，根据账套编号和时间
     **/
    @Transactional
    @PostMapping("/inerst_pz")
    public JSONObject insert_pz(@RequestBody String reqstr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        log.info("请求原始数据:"+reqstr);
        JSONObject response = new JSONObject();
        response.put("res", false);
        try {
            JSONObject resquest = JSONObject.parseObject(reqstr);
            Integer ztbh = resquest.getInteger("ztbh");
            Date rq = sdf.parse(resquest.getString("rq"));
            if (ztbh != null && rq != null){
            	xgPzbService.insert_pz(ztbh, rq);
                tjjScpzService.scpz(ztbh,sdf.format(rq));
                gppzService.insertGPPZ(ztbh, rq);
                hgpzbService.HG_pz(ztbh,rq);
                lfjxQsService.lfjxPz(ztbh, rq);
            }
        } catch (ParseException e) {
        	e.printStackTrace();
            log.error(e.getMessage());
            response.put("msg","参数不规范\n"+e.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            response.put("msg",e.getMessage());
        }

        response.put("res", true);

        return response;
    }

    /**
    * @Author yangjf25257
    * @MethodName get_pz
     * @Param [ztbh, rq]
     * @Return java.lang.String
     * @Description 获取凭证
     **/
    @RequestMapping("/get_pz")
    public String get_pz(@RequestParam Integer ztbh, @RequestParam String rq) throws ParseException {
        log.info("获取凭证,业务日期:"+rq+",账套编号:"+ztbh);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject response = new JSONObject();
        try{
            JSONArray jsonArray = xgPzbService.get_pz(ztbh, sdf.parse(rq));
            response.put("code",0);
            response.put("msg","");
            response.put("count",jsonArray.size());
            response.put("data",jsonArray);
        }catch (Exception e){
            response.put("code",1);
            response.put("msg",e.getMessage());
        }
        return response.toJSONString();
    }

    /**
    * @Author yangjf25257
    * @MethodName get_bb
     * @Param [ztbh]
     * @Return java.lang.String
     * @Description 获取报表
     **/
    @RequestMapping("/get_bb")
    public String get_bb(@RequestParam Integer ztbh){
        log.info("获取报表，账套编号是：" + ztbh);
        JSONObject response = new JSONObject();
        try{
            JSONArray jsonArray = xgPzbService.get_bb(ztbh);
            response.put("code",0);
            response.put("msg","");
            response.put("count",jsonArray.size());
            response.put("data",jsonArray);
        }catch (Exception e){
            response.put("code",1);
            response.put("msg",e.getMessage());
        }
        return response.toJSONString();
    }

}
