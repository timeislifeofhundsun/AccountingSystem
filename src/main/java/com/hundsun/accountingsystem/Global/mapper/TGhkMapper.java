package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TGhk;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TGhkMapper {
    int deleteByPrimaryKey(Integer ywlsid);

    int insert(TGhk record);

    int deleteByTime(@Param("bctime") String date);

    int insertSelective(TGhk record);

    TGhk selectByPrimaryKey(Integer ywlsid);

    int updateByPrimaryKeySelective(TGhk record);

    int updateByPrimaryKey(TGhk record);

    int insert_list(List<TGhk> list);
}