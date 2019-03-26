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
    public String readGhDataByFile(String SHFilePath, String SZFilePath, String date) throws ParseException {
    	String res = "";
        if (date != null && !date.equals("")){
            Assist assist = new Assist();
            assist.setRequires(Assist.andEq("bctime",date));
             tGhkMapper.deleteTGhk(assist);
        }
        if (SHFilePath != null && !SHFilePath.equals("")){
            try {
                tGhkMapper.insertTGhkByBatch(FileParsing.ReadDbf(SHFilePath));
                res += "上海接口文件读取成功  ";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SZFilePath != null && !SZFilePath.equals("")){
            System.out.println(SZFilePath);
            try {
                tGhkMapper.insertTGhkByBatch(FileParsing.ReadSJSDbf(SZFilePath));
                res += "深圳接口文件读取成功  ";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
