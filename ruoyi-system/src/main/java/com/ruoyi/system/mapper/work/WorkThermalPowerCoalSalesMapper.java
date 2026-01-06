package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkThermalPowerCoalSales;

import java.util.List;

/**
 * 热电厂煤种销售库存统计Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface WorkThermalPowerCoalSalesMapper {
    /**
     * 查询热电厂煤种销售库存统计
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 热电厂煤种销售库存统计
     */
    public WorkThermalPowerCoalSales selectWorkThermalPowerCoalSalesById(String id);

    /**
     * 查询热电厂煤种销售库存统计列表
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 热电厂煤种销售库存统计集合
     */
    public List<WorkThermalPowerCoalSales> selectWorkThermalPowerCoalSalesList(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 新增热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    public int insertWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 修改热电厂煤种销售库存统计
     *
     * @param workThermalPowerCoalSales 热电厂煤种销售库存统计
     * @return 结果
     */
    public int updateWorkThermalPowerCoalSales(WorkThermalPowerCoalSales workThermalPowerCoalSales);

    /**
     * 删除热电厂煤种销售库存统计
     *
     * @param id 热电厂煤种销售库存统计主键
     * @return 结果
     */
    public int deleteWorkThermalPowerCoalSalesById(String id);

    /**
     * 批量删除热电厂煤种销售库存统计
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkThermalPowerCoalSalesByIds(String[] ids);
}
