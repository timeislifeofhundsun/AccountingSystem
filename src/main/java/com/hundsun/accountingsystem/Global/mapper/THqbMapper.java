package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.THqb;

public interface THqbMapper {
    int deleteByPrimaryKey(Integer zqnm);

    int insert(THqb record);

    int insertSelective(THqb record);

    THqb selectByPrimaryKey(Integer zqnm);

    int updateByPrimaryKeySelective(THqb record);

    int updateByPrimaryKey(THqb record);
}