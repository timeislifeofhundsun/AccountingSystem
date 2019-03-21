package com.hundsun.accountingsystem.TGp.service.impl;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.SysRole;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import com.hundsun.accountingsystem.Global.mapper.TQsbMapper;
import com.hundsun.accountingsystem.Global.util.FileParsing;
import com.hundsun.accountingsystem.TGp.service.XGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Aurhor yangjf25257
 * @ClassName XGServiceImpl
 * @Date 2019/3/20 10:18
 * @Version 1.0
 * @Description 新股service层
 **/
@Service
public class XGServiceImpl implements XGService {

    @Autowired
    TQsbMapper tQsbMapper;


    /**
    * @Author yangjf25257
    * @MethodName insert_xg
     * @Param [path]
     * @Return int
     * @Description 将新股数据放进清算库
     **/
    @Override
    public int insert_xg(String path) {
        List<TQsb> list = null;
        try {
            list = FileParsing.ReadXGDBf(path);
            Assist assist = new Assist();
            for (TQsb tQsb : list){
                assist.setRequires(Assist.andEq("ywlb",tQsb.getYwlb()));
                tQsbMapper.deleteTQsb(assist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null) {
            return tQsbMapper.insertTQsbByBatch(list);
        }
        return 0;
    }

    /**
    * @Author yangjf25257
    * @MethodName insert_zqAndjk
     * @Param [tPzb]
     * @Return int
     * @Description 新股生成中签和缴款数据
     **/
    @Override
    public int insert_zqAndjk() {
        TPzb tPzb = new TPzb();
        Assist assist = new Assist();
        List<TQsb> tQsbs = tQsbMapper.selectTQsb(assist);
        for (TQsb t:tQsbs) {
            
        }
        return 0;
    }
}
