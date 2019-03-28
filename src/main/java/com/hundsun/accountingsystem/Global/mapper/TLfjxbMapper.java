package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TLfjxb;
import com.hundsun.accountingsystem.Global.bean.TLfjxbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TLfjxbMapper {
    int countByExample(TLfjxbExample example);

    int deleteByExample(TLfjxbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLfjxb record);

    int insertSelective(TLfjxb record);

    List<TLfjxb> selectByExample(TLfjxbExample example);

    TLfjxb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLfjxb record, @Param("example") TLfjxbExample example);

    int updateByExample(@Param("record") TLfjxb record, @Param("example") TLfjxbExample example);

    int updateByPrimaryKeySelective(TLfjxb record);

    int updateByPrimaryKey(TLfjxb record);
}