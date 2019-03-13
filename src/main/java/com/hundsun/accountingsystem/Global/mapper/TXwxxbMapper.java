package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TXwxxb;
import com.hundsun.accountingsystem.Global.bean.TXwxxbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TXwxxbMapper {
    int countByExample(TXwxxbExample example);

    int deleteByExample(TXwxxbExample example);

    int deleteByPrimaryKey(String xwbh);

    int insert(TXwxxb record);

    int insertSelective(TXwxxb record);

    List<TXwxxb> selectByExample(TXwxxbExample example);

    TXwxxb selectByPrimaryKey(String xwbh);

    int updateByExampleSelective(@Param("record") TXwxxb record, @Param("example") TXwxxbExample example);

    int updateByExample(@Param("record") TXwxxb record, @Param("example") TXwxxbExample example);

    int updateByPrimaryKeySelective(TXwxxb record);

    int updateByPrimaryKey(TXwxxb record);
}