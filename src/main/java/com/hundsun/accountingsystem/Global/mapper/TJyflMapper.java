package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.KJyfl;

/**
 * @InterfaceName TJyflMapper
 * @Aurhor yangjf25257
 * @Date 2019/3/8 15:45
 * @Version 1.0
 * @Description 交易费率Mapper层
 **/
public interface TJyflMapper {
    int insert_jyfl(KJyfl KJyfl);
    /**
     * @MethodName get_jyfl
     * @Param [ywcode]
     * @Return com.hundsun.accountingsystem.Global.bean.KJyfl
     * @Description 获取操作都根据业务编号操作
     **/
    KJyfl get_jyfl(int ywcode);
    /**
     * @MethodName delete_jyfl
     * @Param [ywcode]
     * @Return int
     * @Description 删除根据业务编号操作
     **/
    int delete_jyfl(int ywcode);
}
