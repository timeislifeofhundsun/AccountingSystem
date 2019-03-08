package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.JYFL;

/**
 * @InterfaceName JYFLMapper
 * @Aurhor yangjf25257
 * @Date 2019/3/8 15:45
 * @Version 1.0
 * @Description 交易费率Mapper层
 **/
public interface JYFLMapper {
    int insert_jyfl(JYFL jyfl);
    /**
     * @MethodName get_jyfl
     * @Param [ywcode]
     * @Return com.hundsun.accountingsystem.Global.bean.JYFL
     * @Description 获取操作都根据业务编号操作
     **/
    JYFL get_jyfl(int ywcode);
    /**
     * @MethodName delete_jyfl
     * @Param [ywcode]
     * @Return int
     * @Description 删除根据业务编号操作
     **/
    int delete_jyfl(int ywcode);
}
