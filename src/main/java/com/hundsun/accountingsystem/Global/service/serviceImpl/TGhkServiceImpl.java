package com.hundsun.accountingsystem.Global.service.serviceImpl;

import com.hundsun.accountingsystem.Global.mapper.TGhkMapper;
import com.hundsun.accountingsystem.Global.service.TGhkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TGhkServiceImpl
 * @Aurhor yangjf25257
 * @Date 2019/3/13 16:18
 * @Version 1.0
 * @Description
 **/
@Service
public class TGhkServiceImpl implements TGhkService {
//    @Autowired
//    TGhkMapper tGhkMapper;

    @Override
    public boolean readGhDataByFile(String SHFilePath, String SZFilePath, String date) throws ParseException {

        System.out.println(date);
//        tGhkMapper.deleteByTime(date);
        return false;
    }
}
