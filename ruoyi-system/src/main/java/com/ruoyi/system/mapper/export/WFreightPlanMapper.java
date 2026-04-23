package com.ruoyi.system.mapper.export;

import java.util.List;
import com.ruoyi.system.domain.export.WFreightPlan;

/**
 * 驻矿公司煤炭发运承认车情况_承认车Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WFreightPlanMapper {
    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param id 驻矿公司煤炭发运承认车情况_承认车主键
     * @return 驻矿公司煤炭发运承认车情况_承认车
     */
    public WFreightPlan selectWFreightPlanById(String id);

    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车列表
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 驻矿公司煤炭发运承认车情况_承认车集合
     */
    public List<WFreightPlan> selectWFreightPlanList(WFreightPlan wFreightPlan);

    /**
     * 新增驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 结果
     */
    public int insertWFreightPlan(WFreightPlan wFreightPlan);

    /**
     * 修改驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 结果
     */
    public int updateWFreightPlan(WFreightPlan wFreightPlan);

    /**
     * 删除驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param id 驻矿公司煤炭发运承认车情况_承认车主键
     * @return 结果
     */
    public int deleteWFreightPlanById(String id);

    /**
     * 批量删除驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWFreightPlanByIds(String[] ids);
}
