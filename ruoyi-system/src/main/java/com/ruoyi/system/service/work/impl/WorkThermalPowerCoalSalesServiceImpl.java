package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkThermalPowerCoalSales;
import com.ruoyi.system.mapper.work.WorkThermalPowerCoalSalesMapper;
import com.ruoyi.system.service.work.IWorkThermalPowerCoalSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热电厂煤种销售库存统计Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Slf4j
@Service
public class WorkThermalPowerCoalSalesServiceImpl implements IWorkThermalPowerCoalSalesService {
    @Autowired
    private WorkThermalPowerCoalSalesMapper workThermalPowerCoalSalesMapper;

    /**
     * 查询热电厂煤种销售库存统计
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 热电厂煤种销售库存统计
     */
    @Override
    public WorkThermalPowerCoalSales getWorkThermalPowerCoalSalesById(String id) {
        return workThermalPowerCoalSalesMapper.selectWorkThermalPowerCoalSalesById(id);
    }

    /**
     * 查询热电厂煤种销售库存统计列表
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 热电厂煤种销售库存统计
     */
    @Override
    public List<WorkThermalPowerCoalSales> listWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales) {
        return workThermalPowerCoalSalesMapper.selectWorkThermalPowerCoalSalesList(workThermalPowerCoalSales);
    }

    /**
     * 新增热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    @Override
    public int saveWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales) {
        workThermalPowerCoalSales.setCreateTime(DateUtils.getNowDate());
        return workThermalPowerCoalSalesMapper.insertWorkThermalPowerCoalSales(workThermalPowerCoalSales);
    }

    /**
     * 修改热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    @Override
    public int updateWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales) {
        return workThermalPowerCoalSalesMapper.updateWorkThermalPowerCoalSales(workThermalPowerCoalSales);
    }

    /**
     * 批量删除热电厂煤种销售库存统计
     *
     * @param ids 需要删除的热电厂煤种销售库存统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkThermalPowerCoalSalesByIds(String[] ids) {
        return workThermalPowerCoalSalesMapper.deleteWorkThermalPowerCoalSalesByIds(ids);
    }

    /**
     * 删除热电厂煤种销售库存统计信息
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkThermalPowerCoalSalesById(String id) {
        return workThermalPowerCoalSalesMapper.deleteWorkThermalPowerCoalSalesById(id);
    }
}
