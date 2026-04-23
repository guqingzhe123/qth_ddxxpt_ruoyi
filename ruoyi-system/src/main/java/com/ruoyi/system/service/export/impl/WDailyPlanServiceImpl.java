package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WDailyPlan;
import com.ruoyi.system.mapper.export.WDailyPlanMapper;
import com.ruoyi.system.service.export.IWDailyPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外销商品煤日执行情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WDailyPlanServiceImpl implements IWDailyPlanService {
    @Autowired
    private WDailyPlanMapper wDailyPlanMapper;

    /**
     * 查询外销商品煤日执行情况
     * 
     * @param id 外销商品煤日执行情况主键
     * @return 外销商品煤日执行情况
     */
    @Override
    public WDailyPlan getWDailyPlanById(String id) {
        return wDailyPlanMapper.selectWDailyPlanById(id);
    }

    /**
     * 查询外销商品煤日执行情况列表
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 外销商品煤日执行情况
     */
    @Override
    public List<WDailyPlan> listWDailyPlan(WDailyPlan wDailyPlan) {
        return wDailyPlanMapper.selectWDailyPlanList(wDailyPlan);
    }

    /**
     * 新增外销商品煤日执行情况
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 结果
     */
    @Override
    public int saveWDailyPlan(WDailyPlan wDailyPlan) {
        wDailyPlan.setCreateTime(DateUtils.getNowDate());
        return wDailyPlanMapper.insertWDailyPlan(wDailyPlan);
    }

    /**
     * 修改外销商品煤日执行情况
     * 
     * @param wDailyPlan 外销商品煤日执行情况
     * @return 结果
     */
    @Override
    public int updateWDailyPlan(WDailyPlan wDailyPlan) {
        wDailyPlan.setUpdateTime(DateUtils.getNowDate());
        return wDailyPlanMapper.updateWDailyPlan(wDailyPlan);
    }

    /**
     * 批量删除外销商品煤日执行情况
     * 
     * @param ids 需要删除的外销商品煤日执行情况主键
     * @return 结果
     */
    @Override
    public int deleteWDailyPlanByIds(String[] ids) {
        return wDailyPlanMapper.deleteWDailyPlanByIds(ids);
    }

    /**
     * 删除外销商品煤日执行情况信息
     * 
     * @param id 外销商品煤日执行情况主键
     * @return 结果
     */
    @Override
    public int deleteWDailyPlanById(String id) {
        return wDailyPlanMapper.deleteWDailyPlanById(id);
    }
}
