package com.hundsun.accountingsystem.Global.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hundsun.accountingsystem.Global.bean.Assist;
import com.hundsun.accountingsystem.Global.bean.THqb;

public interface THqbMapper{
	/**
	 * 获得THqb数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTHqbRowCount(Assist assist);
	/**
	 * 获得THqb数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<THqb> selectTHqb(Assist assist);
	/**
	 * 获得一个THqb对象,以参数THqb对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    THqb selectTHqbByObj(THqb obj);
	/**
	 * 通过THqb的id获得THqb对象
	 * @param id
	 * @return
	 */
    THqb selectTHqbById(Integer id);
	/**
	 * 插入THqb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTHqb(THqb value);
	/**
	 * 插入THqb中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTHqb(THqb value);
	/**
	 * 批量插入THqb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTHqbByBatch(List<THqb> value);
	/**
	 * 通过THqb的id删除THqb
	 * @param id
	 * @return
	 */
    int deleteTHqbById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除THqb
	 * @param assist
	 * @return
	 */
    int deleteTHqb(Assist assist);
	/**
	 * 通过THqb的id更新THqb中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTHqbById(THqb enti);
 	/**
	 * 通过辅助工具Assist的条件更新THqb中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTHqb(@Param("enti") THqb value, @Param("assist") Assist assist);
	/**
	 * 通过THqb的id更新THqb中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTHqbById(THqb enti);
 	/**
	 * 通过辅助工具Assist的条件更新THqb中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTHqb(@Param("enti") THqb value, @Param("assist") Assist assist);
}