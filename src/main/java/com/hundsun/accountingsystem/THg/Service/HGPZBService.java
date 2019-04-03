/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/2  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Service;

import com.hundsun.accountingsystem.Global.bean.TPzb;
import com.hundsun.accountingsystem.Global.bean.TQsb;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 生成凭证
 */
public interface HGPZBService {
    //回购生成凭证
    void HG_pz(int ztbh,Date rq) throws ParseException;

    void deleteAll_pz(int ztbh,Date rq);
}
