package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WDailyPlan;

import java.util.List;

/**
 * 外销商品煤日执行情况Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWDailyPlanService {
    /**
     * 查询外销商品煤日执行情况
     * 
     * @param id 外销商品煤日执行情况主键
     * @return 外销商品煤日执行情况
     */
    public WDailyPlan getWDailyPlanById(String id);

    /**
     * 查询外销商品煤日执行情况列表
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 外销商品煤日执行情况集合
     */
    public List<WDailyPlan> listWDailyPlan(WDailyPlan wDailyPlan);

    /**
     * 新增外销商品煤日执行情况
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 结果
     */
    public int saveWDailyPlan(WDailyPlan wDailyPlan);

    /**
     * 修改外销商品煤日执行情况
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 结果
     */
    public int updateWDailyPlan(WDailyPlan wDailyPlan);

    /**
     * 批量删除外销商品煤日执行情况
     * 
     * @param ids 需要删除的外销商品煤日执行情况主键集合
     * @return 结果
     */
    public int deleteWDailyPlanByIds(String[] ids);

    /**
     * 删除外销商品煤日执行情况信息
     * 
     * @param id 外销商品煤日执行情况主键
     * @return 结果
     */
    public int deleteWDailyPlanById(String id);
}
