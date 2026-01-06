package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkThermalPowerCoalSales;

import java.util.List;

/**
 * 热电厂煤种销售库存统计Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IWorkThermalPowerCoalSalesService {
    /**
     * 查询热电厂煤种销售库存统计
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 热电厂煤种销售库存统计
     */
    public WorkThermalPowerCoalSales getWorkThermalPowerCoalSalesById(String id);

    /**
     * 查询热电厂煤种销售库存统计列表
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 热电厂煤种销售库存统计集合
     */
    public List<WorkThermalPowerCoalSales> listWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 新增热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    public int saveWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 修改热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    public int updateWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 批量删除热电厂煤种销售库存统计
     *
     * @param ids 需要删除的热电厂煤种销售库存统计主键集合
     * @return 结果
     */
    public int deleteWorkThermalPowerCoalSalesByIds(String[] ids);

    /**
     * 删除热电厂煤种销售库存统计信息
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 结果
     */
    public int deleteWorkThermalPowerCoalSalesById(String id);
}
