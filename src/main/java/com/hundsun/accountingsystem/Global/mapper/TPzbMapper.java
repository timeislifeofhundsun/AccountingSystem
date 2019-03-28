package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.TPzb;
import java.util.List;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TPzbMapper{
	/**
	 * 获得TPzb数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTPzbRowCount(Assist assist);
	/**
	 * 获得TPzb数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<TPzb> selectTPzb(Assist assist);
	/**
	 * 获得一个TPzb对象,以参数TPzb对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    TPzb selectTPzbByObj(TPzb obj);
	/**
	 * 通过TPzb的id获得TPzb对象
	 * @param id
	 * @return
	 */
    TPzb selectTPzbById(Integer id);
	/**
	 * 插入TPzb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTPzb(TPzb value);
	/**
	 * 插入TPzb中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTPzb(TPzb value);
	/**
	 * 批量插入TPzb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTPzbByBatch(List<TPzb> value);
	/**
	 * 通过TPzb的id删除TPzb
	 * @param id
	 * @return
	 */
    int deleteTPzbById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除TPzb
	 * @param assist
	 * @return
	 */
    int deleteTPzb(Assist assist);
	/**
	 * 通过TPzb的id更新TPzb中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTPzbById(TPzb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TPzb中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTPzb(@Param("enti") TPzb value, @Param("assist") Assist assist);
	/**
	 * 通过TPzb的id更新TPzb中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTPzbById(TPzb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TPzb中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTPzb(@Param("enti") TPzb value, @Param("assist") Assist assist);
}