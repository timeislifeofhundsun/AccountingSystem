package com.hundsun.accountingsystem.Global.service.impl;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import com.hundsun.accountingsystem.Global.util.FileParsing;

/**
 * @ClassName TGhkServiceImpl
 * @Aurhor yangjf25257
 * @Date 2019/3/13 16:18
 * @Version 1.0
 * @Description
 * 高振添加返回结果
 **/
@Service
public class TGhkServiceImpl implements TGhkService {
    @Autowired
    TGhkMapper tGhkMapper;

    @Override
    public boolean readGhDataByFile(String SHFilePath, String SZFilePath, String date) throws ParseException {
    	boolean res = false;
        if (date != null){
            Assist assist = new Assist();
            assist.setRequires(Assist.andEq("bctime",date));
             tGhkMapper.deleteTGhk(assist);
        }
        if (SHFilePath != null){
            try {
                tGhkMapper.insertTGhkByBatch(FileParsing.ReadDbf(SHFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SZFilePath != null){
            try {
                tGhkMapper.insertTGhkByBatch(FileParsing.ReadSJSDbf(SZFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        res = true;
        return res;
    }
}
