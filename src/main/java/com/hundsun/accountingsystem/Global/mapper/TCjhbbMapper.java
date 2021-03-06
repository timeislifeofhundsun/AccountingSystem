package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.VO.TCjhbbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TCjhbb;
import com.hundsun.accountingsystem.Global.bean.TCjhbbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TCjhbbMapper {
    int countByExample(TCjhbbExample example);

    int deleteByExample(TCjhbbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCjhbb record);

    int insertSelective(TCjhbb record);

    List<TCjhbb> selectByExample(TCjhbbExample example);

    TCjhbb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCjhbb record, @Param("example") TCjhbbExample example);

    int updateByExample(@Param("record") TCjhbb record, @Param("example") TCjhbbExample example);

    int updateByPrimaryKeySelective(TCjhbb record);

    int updateByPrimaryKey(TCjhbb record);
    
    int deleteByDate(TCjhbbParamPojo param);
    
    int insertList(List<TCjhbb> tcjhbbList);
}