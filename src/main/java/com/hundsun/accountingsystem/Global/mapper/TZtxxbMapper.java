package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.VO.TZtxxbParamPojo;
import com.hundsun.accountingsystem.Global.bean.TZtxxb;
import com.hundsun.accountingsystem.Global.bean.TZtxxbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TZtxxbMapper {

    List<TZtxxb> findAll();

    int countByExample(TZtxxbExample example);

    int deleteByExample(TZtxxbExample example);

    int deleteByPrimaryKey(Integer ztbh);

    int insert(TZtxxb record);

    int insertSelective(TZtxxb record);

    List<TZtxxb> selectByExample(TZtxxbExample example);

    TZtxxb selectByPrimaryKey(Integer ztbh);

    int updateByExampleSelective(@Param("record") TZtxxb record, @Param("example") TZtxxbExample example);

    int updateByExample(@Param("record") TZtxxb record, @Param("example") TZtxxbExample example);

    int updateByPrimaryKeySelective(TZtxxb record);

    int updateByPrimaryKey(TZtxxb record);
    
    List<TZtxxb> selectByLimit(TZtxxbParamPojo params);

	int getMaxZtbh();
}