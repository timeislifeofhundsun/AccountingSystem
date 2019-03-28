package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.TQsb;
import java.util.List;

import com.hundsun.accountingsystem.Global.VO.TQsbParamPojo;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TQsbMapper{
	/**
	 * 获得TQsb数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTQsbRowCount(Assist assist);
	/**
	 * 获得TQsb数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<TQsb> selectTQsb(Assist assist);
	/**
	 * 获得一个TQsb对象,以参数TQsb对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    TQsb selectTQsbByObj(TQsb obj);
	/**
	 * 通过TQsb的id获得TQsb对象
	 * @param id
	 * @return
	 */
    TQsb selectTQsbById(Integer id);
	/**
	 * 插入TQsb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTQsb(TQsb value);
	/**
	 * 插入TQsb中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTQsb(TQsb value);
	/**
	 * 批量插入TQsb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTQsbByBatch(List<TQsb> value);
	/**
	 * 通过TQsb的id删除TQsb
	 * @param id
	 * @return
	 */
    int deleteTQsbById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除TQsb
	 * @param assist
	 * @return
	 */
    int deleteTQsb(Assist assist);
	/**
	 * 通过TQsb的id更新TQsb中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTQsbById(TQsb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TQsb中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTQsb(@Param("enti") TQsb value, @Param("assist") Assist assist);
	/**
	 * 通过TQsb的id更新TQsb中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTQsbById(TQsb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TQsb中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTQsb(@Param("enti") TQsb value, @Param("assist") Assist assist);

    List<TQsb> findAllTQsb(@Param("ywlb")int[] ywlb,@Param("extenda") String extenda ,@Param("extendc") String extendc);
	
    List<TQsb> selectByYwlbAndLimit(TQsbParamPojo params);
    
	List<TQsb> selectByPageAndZtbh(TQsbParamPojo params);
	
	int getCountsByZtbh(TQsbParamPojo params);
	
	List<TQsb> selectByPageAndDate(TQsbParamPojo params);
	
	int getCountsByDate(TQsbParamPojo params);
}