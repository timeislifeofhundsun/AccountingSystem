package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.GHK;

import java.util.List;

public interface GHKMapper {

    int deleteByPrimaryKey(Integer ywlsid);

    int insert(GHK record);

    int insert_list(List<GHK> list);

    int insertSelective(GHK record);

    GHK selectByPrimaryKey(Integer ywlsid);

    int updateByPrimaryKeySelective(GHK record);

    int updateByPrimaryKey(GHK record);
}