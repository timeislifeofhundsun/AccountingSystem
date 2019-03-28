package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.VO.TGdxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TGdxxb;
import com.hundsun.accountingsystem.Global.bean.TGdxxbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGdxxbMapper {
    int countByExample(TGdxxbExample example);

    int deleteByExample(TGdxxbExample example);

    int deleteByPrimaryKey(String gddm);

    int insert(TGdxxb record);

    int insertSelective(TGdxxb record);

    List<TGdxxb> selectByExample(TGdxxbExample example);

    TGdxxb selectByPrimaryKey(String gddm);

    TGdxxb selectByGddmAndXwbh(TGdxxb tGdxxb);

    int updateByExampleSelective(@Param("record") TGdxxb record, @Param("example") TGdxxbExample example);

    int updateByExample(@Param("record") TGdxxb record, @Param("example") TGdxxbExample example);

    int updateByPrimaryKeySelective(TGdxxb record);

    int updateByPrimaryKey(TGdxxb record);

	List<TGdxxb> selectByLimit(TGdxxbParamPojo params);
}