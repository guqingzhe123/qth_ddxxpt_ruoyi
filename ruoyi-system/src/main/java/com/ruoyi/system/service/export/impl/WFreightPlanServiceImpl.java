package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WFreightPlan;
import com.ruoyi.system.mapper.export.WFreightPlanMapper;
import com.ruoyi.system.service.export.IWFreightPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 驻矿公司煤炭发运承认车情况_承认车Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WFreightPlanServiceImpl implements IWFreightPlanService {
    @Autowired
    private WFreightPlanMapper wFreightPlanMapper;

    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param id 驻矿公司煤炭发运承认车情况_承认车主键
     * @return 驻矿公司煤炭发运承认车情况_承认车
     */
    @Override
    public WFreightPlan getWFreightPlanById(String id) {
        return wFreightPlanMapper.selectWFreightPlanById(id);
    }

    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车列表
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 驻矿公司煤炭发运承认车情况_承认车
     */
    @Override
    public List<WFreightPlan> listWFreightPlan(WFreightPlan wFreightPlan) {
        return wFreightPlanMapper.selectWFreightPlanList(wFreightPlan);
    }

    /**
     * 新增驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 结果
     */
    @Override
    public int saveWFreightPlan(WFreightPlan wFreightPlan) {
        wFreightPlan.setCreateTime(DateUtils.getNowDate());
        return wFreightPlanMapper.insertWFreightPlan(wFreightPlan);
    }

    /**
     * 修改驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param wFreightPlan 驻矿公司煤炭发运承认车情况_承认车
     * @return 结果
     */
    @Override
    public int updateWFreightPlan(WFreightPlan wFreightPlan) {
        wFreightPlan.setUpdateTime(DateUtils.getNowDate());
        return wFreightPlanMapper.updateWFreightPlan(wFreightPlan);
    }

    /**
     * 批量删除驻矿公司煤炭发运承认车情况_承认车
     * 
     * @param ids 需要删除的驻矿公司煤炭发运承认车情况_承认车主键
     * @return 结果
     */
    @Override
    public int deleteWFreightPlanByIds(String[] ids) {
        return wFreightPlanMapper.deleteWFreightPlanByIds(ids);
    }

    /**
     * 删除驻矿公司煤炭发运承认车情况_承认车信息
     * 
     * @param id 驻矿公司煤炭发运承认车情况_承认车主键
     * @return 结果
     */
    @Override
    public int deleteWFreightPlanById(String id) {
        return wFreightPlanMapper.deleteWFreightPlanById(id);
    }
}
