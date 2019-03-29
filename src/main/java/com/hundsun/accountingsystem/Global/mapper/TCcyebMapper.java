package com.hundsun.accountingsystem.Global.mapper;
import com.hundsun.accountingsystem.Global.bean.TCcyeb;
import java.util.List;
import com.hundsun.accountingsystem.Global.bean.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TCcyebMapper{

	/**
	* @Author yangjf25257
	* @MethodName update_cysl
	 * @Param [tCcyeb]
	 * @Return int
	 * @Description 成交数量
	 **/
	int update_cysl(TCcyeb tCcyeb);

	/**
	* @Author yangjf25257
	* @MethodName update_ljgz
	 * @Param [tCcyeb]
	 * @Return int
	 * @Description 累计估值
	 **/
	int update_ljgz(TCcyeb tCcyeb);


	int update_ltlx(TCcyeb tCcyeb);
	/**
	 * 获得TCcyeb数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getTCcyebRowCount(Assist assist);
	/**
	 * 获得TCcyeb数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<TCcyeb> selectTCcyeb(Assist assist);
	/**
	 * 获得一个TCcyeb对象,以参数TCcyeb对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    TCcyeb selectTCcyebByObj(TCcyeb obj);
	/**
	 * 通过TCcyeb的id获得TCcyeb对象
	 * @param id
	 * @return
	 */
    TCcyeb selectTCcyebById(Integer id);
	/**
	 * 插入TCcyeb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTCcyeb(TCcyeb value);
	/**
	 * 插入TCcyeb中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyTCcyeb(TCcyeb value);
	/**
	 * 批量插入TCcyeb到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertTCcyebByBatch(List<TCcyeb> value);
	/**
	 * 通过TCcyeb的id删除TCcyeb
	 * @param id
	 * @return
	 */
    int deleteTCcyebById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除TCcyeb
	 * @param assist
	 * @return
	 */
    int deleteTCcyeb(Assist assist);
	/**
	 * 通过TCcyeb的id更新TCcyeb中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateTCcyebById(TCcyeb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TCcyeb中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateTCcyeb(@Param("enti") TCcyeb value, @Param("assist") Assist assist);
	/**
	 * 通过TCcyeb的id更新TCcyeb中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyTCcyebById(TCcyeb enti);
 	/**
	 * 通过辅助工具Assist的条件更新TCcyeb中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyTCcyeb(@Param("enti") TCcyeb value, @Param("assist") Assist assist);
}