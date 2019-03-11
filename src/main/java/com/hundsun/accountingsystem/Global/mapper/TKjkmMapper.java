/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/3/8  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.Global.mapper;

import com.hundsun.accountingsystem.Global.bean.TKjkm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明:
 *
 * @author wanggk25832
 * 会计科目Mapper接口
 */
@Repository
public interface TKjkmMapper {
    int insertKJKM(TKjkm TKjkm);
    int deleteKJKM(String id);
    int updateKJKM(TKjkm TKjkm);
    TKjkm findOneKJKM(String id);
    List<TKjkm> findAllKJKM();
}
