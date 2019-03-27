package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.TSequence;
import java.util.List;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
public interface TSequenceMapper{
	/**
	 * 获得TSequence数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTSequenceRowCount(Assist assist);
	/**
	 * 获得TSequence数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<TSequence> selectTSequence(Assist assist);
	/**
	 * 获得一个TSequence对象,以参数TSequence对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    TSequence selectTSequenceByObj(TSequence obj);
	/**
	 * 通过TSequence的id获得TSequence对象
	 * @param id
	 * @return
	 */
    TSequence selectTSequenceById(String id);
	/**
	 * 插入TSequence到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTSequence(TSequence value);
	/**
	 * 插入TSequence中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTSequence(TSequence value);
	/**
	 * 批量插入TSequence到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTSequenceByBatch(List<TSequence> value);
	/**
	 * 通过TSequence的id删除TSequence
	 * @param id
	 * @return
	 */
    int deleteTSequenceById(String id);
	/**
	 * 通过辅助工具Assist的条件删除TSequence
	 * @param assist
	 * @return
	 */
    int deleteTSequence(Assist assist);
	/**
	 * 通过TSequence的id更新TSequence中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTSequenceById(TSequence enti);
 	/**
	 * 通过辅助工具Assist的条件更新TSequence中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTSequence(@Param("enti") TSequence value, @Param("assist") Assist assist);
	/**
	 * 通过TSequence的id更新TSequence中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTSequenceById(TSequence enti);
 	/**
	 * 通过辅助工具Assist的条件更新TSequence中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTSequence(@Param("enti") TSequence value, @Param("assist") Assist assist);
}