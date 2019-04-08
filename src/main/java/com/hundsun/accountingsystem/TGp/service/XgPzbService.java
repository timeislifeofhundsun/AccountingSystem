package com.hundsun.accountingsystem.TGp.service;

import com.alibaba.fastjson.JSONArray;
import com.hundsun.accountingsystem.Global.bean.TPzb;

import java.util.Date;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XgPzbService
 * @Date 2019/3/22 10:15
 * @Version 1.0
 * @Description
 **/
public interface XgPzbService {
    boolean insert_pz(int ztbh, Date rq);
    JSONArray get_pz(int ztbh, Date rq);
    JSONArray get_bb(int ztbh);

}
