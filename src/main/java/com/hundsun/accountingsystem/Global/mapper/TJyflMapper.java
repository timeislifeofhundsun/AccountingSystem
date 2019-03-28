package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TJyfl;
import com.hundsun.accountingsystem.Global.bean.TKjkmb;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TJyflMapper {
    int deleteByPrimaryKey(Integer ywcode);

    int insert(TJyfl record);

    int insertSelective(TJyfl record);

    TJyfl selectByPrimaryKey(Integer ywcode);

    int updateByPrimaryKeySelective(TJyfl record);

    int updateByPrimaryKey(TJyfl record);

    List<TJyfl> findAllTJyfl();
}