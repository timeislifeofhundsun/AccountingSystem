package com.hundsun.accountingsystem.TGp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.mapper.TZqxxMapper;
import com.hundsun.accountingsystem.Global.util.DateFormatUtil;
import com.hundsun.accountingsystem.TGp.service.GPManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: GPManageService
 * @projectName accountingsystem
 * @description:
 * @author gaozhen
 * @date 2019-03-2717:05
 */
@Service
public class GPManageServiceImpl implements GPManageService {

    @Autowired
    private TQsbMapper qsbMapper;

    @Autowired
    private TZqxxMapper zqxxMapper;

    /**
     * 证券信息，key：zqdm，value：zqxx
     */
    private Map<String, TZqxx> zqxxMap;

    @Override
    public JSONArray selectQsb(int ztbh, Date ywrq) throws Exception {
        this.loadZqxxMap();
        JSONArray returnData = new JSONArray();
        Assist assist = new Assist();
        Assist.WhereRequire<?> require = assist.new WhereRequire<Object>(
                "rq = " +"'"+ DateFormatUtil.getStringByDate(ywrq) +"'"
                        +" and ztbh ="+ztbh
                        +" and ( ywlb=1101 or ywlb=1102 )", null);
        assist.setRequires(require);
        List<TQsb> qsbs = qsbMapper.selectTQsb(assist);
        for (TQsb qsb:qsbs) {
            JSONObject obj = new JSONObject();
            obj.put("rq",DateFormatUtil.getStringByDate(qsb.getRq()));
            obj.put("zqmc",zqxxMap.get(qsb.getZqcode()).getZqjg());
            obj.put("bs",qsb.getBs().equals("B")?"买入":"卖出");
            obj.put("zqdm",qsb.getZqcode());
            obj.put("cjje",qsb.getAmount());
            obj.put("cjsl",qsb.getQuantity());
            obj.put("yhs",qsb.getYhs()==null?0:qsb.getYhs());
            obj.put("jsf",qsb.getJsf());
            obj.put("ghf",qsb.getGhf());
            obj.put("zgf",qsb.getZgf());
            obj.put("yj",qsb.getYj());
            returnData.add(obj);
        }
        this.zqxxMap=null;
        return returnData;
    }


    /**
     * 加载证券信息map
     */
    private void loadZqxxMap() {
        this.zqxxMap = new HashMap<String,TZqxx>();
        List<TZqxx> zqxxs = zqxxMapper.findAllTZqxx();
        for (TZqxx tZqxx : zqxxs) {
            this.zqxxMap.put(tZqxx.getZqdm(), tZqxx);
        }
    }


}
