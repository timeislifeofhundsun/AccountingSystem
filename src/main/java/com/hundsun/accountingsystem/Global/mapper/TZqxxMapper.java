package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TZqxx;
import com.hundsun.accountingsystem.Global.bean.TZqxxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TZqxxMapper {
    int countByExample(TZqxxExample example);

    int deleteByExample(TZqxxExample example);

    int deleteByPrimaryKey(Integer zqnm);

    int insert(TZqxx record);

    int insertSelective(TZqxx record);

    List<TZqxx> selectByExample(TZqxxExample example);

    TZqxx selectByPrimaryKey(Integer zqnm);

    int updateByExampleSelective(@Param("record") TZqxx record, @Param("example") TZqxxExample example);

    int updateByExample(@Param("record") TZqxx record, @Param("example") TZqxxExample example);

    int updateByPrimaryKeySelective(TZqxx record);

    int updateByPrimaryKey(TZqxx record);
}