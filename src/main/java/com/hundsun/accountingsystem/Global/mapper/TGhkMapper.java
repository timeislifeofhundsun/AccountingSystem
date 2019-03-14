package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.TGhk;
import java.util.List;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
public interface TGhkMapper{
	/**
	 * 获得TGhk数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTGhkRowCount(Assist assist);
	/**
	 * 获得TGhk数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<TGhk> selectTGhk(Assist assist);
	/**
	 * 获得一个TGhk对象,以参数TGhk对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    TGhk selectTGhkByObj(TGhk obj);
	/**
	 * 通过TGhk的id获得TGhk对象
	 * @param id
	 * @return
	 */
    TGhk selectTGhkById(Integer id);
	/**
	 * 插入TGhk到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTGhk(TGhk value);
	/**
	 * 插入TGhk中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTGhk(TGhk value);
	/**
	 * 批量插入TGhk到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTGhkByBatch(List<TGhk> value);
	/**
	 * 通过TGhk的id删除TGhk
	 * @param id
	 * @return
	 */
    int deleteTGhkById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除TGhk
	 * @param assist
	 * @return
	 */
    int deleteTGhk(Assist assist);
	/**
	 * 通过TGhk的id更新TGhk中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTGhkById(TGhk enti);
 	/**
	 * 通过辅助工具Assist的条件更新TGhk中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTGhk(@Param("enti") TGhk value, @Param("assist") Assist assist);
	/**
	 * 通过TGhk的id更新TGhk中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTGhkById(TGhk enti);
 	/**
	 * 通过辅助工具Assist的条件更新TGhk中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTGhk(@Param("enti") TGhk value, @Param("assist") Assist assist);
}