package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TGhk;

import java.util.List;

public interface TGhkMapper {
    int deleteByPrimaryKey(Integer ywlsid);

    int insert(TGhk record);

    int insertSelective(TGhk record);

    TGhk selectByPrimaryKey(Integer ywlsid);

    int updateByPrimaryKeySelective(TGhk record);

    int updateByPrimaryKey(TGhk record);

    int insert_list(List<TGhk> list);
}