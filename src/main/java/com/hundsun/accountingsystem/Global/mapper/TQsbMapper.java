package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TQsb;

public interface TQsbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TQsb record);

    int insertSelective(TQsb record);

    TQsb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TQsb record);

    int updateByPrimaryKey(TQsb record);
}