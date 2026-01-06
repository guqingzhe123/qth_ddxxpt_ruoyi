package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkCoalStockSalesStat;

import java.util.List;

/**
 * 煤炭库存销售统计Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IWorkCoalStockSalesStatService {
    /**
     * 查询煤炭库存销售统计
     *
     * @param id 煤炭库存销售统计主键
     * @return 煤炭库存销售统计
     */
    public WorkCoalStockSalesStat getWorkCoalStockSalesStatById(String id);

    /**
     * 查询煤炭库存销售统计列表
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 煤炭库存销售统计集合
     */
    public List<WorkCoalStockSalesStat> listWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat);

    /**
     * 新增煤炭库存销售统计
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 结果
     */
    public int saveWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat);

    /**
     * 修改煤炭库存销售统计
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 结果
     */
    public int updateWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat);

    /**
     * 批量删除煤炭库存销售统计
     *
     * @param ids 需要删除的煤炭库存销售统计主键集合
     * @return 结果
     */
    public int deleteWorkCoalStockSalesStatByIds(String[] ids);

    /**
     * 删除煤炭库存销售统计信息
     *
     * @param id 煤炭库存销售统计主键
     * @return 结果
     */
    public int deleteWorkCoalStockSalesStatById(String id);
}
