package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.Ccyeb;
import java.util.List;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
public interface TCcyebMapper{
	/**
	 * 获得Ccyeb数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getCcyebRowCount(Assist assist);
	/**
	 * 获得Ccyeb数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Ccyeb> selectCcyeb(Assist assist);
	/**
	 * 获得一个Ccyeb对象,以参数Ccyeb对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Ccyeb selectCcyebByObj(Ccyeb obj);
	/**
	 * 通过Ccyeb的id获得Ccyeb对象
	 * @param id
	 * @return
	 */
    Ccyeb selectCcyebById(Integer id);
	/**
	 * 插入Ccyeb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertCcyeb(Ccyeb value);
	/**
	 * 插入Ccyeb中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyCcyeb(Ccyeb value);
	/**
	 * 批量插入Ccyeb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertCcyebByBatch(List<Ccyeb> value);
	/**
	 * 通过Ccyeb的id删除Ccyeb
	 * @param id
	 * @return
	 */
    int deleteCcyebById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Ccyeb
	 * @param assist
	 * @return
	 */
    int deleteCcyeb(Assist assist);
	/**
	 * 通过Ccyeb的id更新Ccyeb中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateCcyebById(Ccyeb enti);
 	/**
	 * 通过辅助工具Assist的条件更新Ccyeb中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateCcyeb(@Param("enti") Ccyeb value, @Param("assist") Assist assist);
	/**
	 * 通过Ccyeb的id更新Ccyeb中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyCcyebById(Ccyeb enti);
 	/**
	 * 通过辅助工具Assist的条件更新Ccyeb中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyCcyeb(@Param("enti") Ccyeb value, @Param("assist") Assist assist);
}