package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TZqxx;

public interface TZqxxMapper {
    int deleteByPrimaryKey(Integer zqnm);

    int insert(TZqxx record);

    int insertSelective(TZqxx record);

    TZqxx selectByPrimaryKey(Integer zqnm);

    int updateByPrimaryKeySelective(TZqxx record);

    int updateByPrimaryKey(TZqxx record);
}