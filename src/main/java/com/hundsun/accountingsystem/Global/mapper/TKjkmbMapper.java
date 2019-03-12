package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TKjkmb;

public interface TKjkmbMapper {
    int deleteByPrimaryKey(String id);

    int insert(TKjkmb record);

    int insertSelective(TKjkmb record);

    TKjkmb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TKjkmb record);

    int updateByPrimaryKey(TKjkmb record);
}