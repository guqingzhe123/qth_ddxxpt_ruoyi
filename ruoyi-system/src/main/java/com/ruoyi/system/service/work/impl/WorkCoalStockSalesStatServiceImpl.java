package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkCoalStockSalesStat;
import com.ruoyi.system.mapper.work.WorkCoalStockSalesStatMapper;
import com.ruoyi.system.service.work.IWorkCoalStockSalesStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 煤炭库存销售统计Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Slf4j
@Service
public class WorkCoalStockSalesStatServiceImpl implements IWorkCoalStockSalesStatService {
    @Autowired
    private WorkCoalStockSalesStatMapper workCoalStockSalesStatMapper;

    /**
     * 查询煤炭库存销售统计
     *
     * @param id 煤炭库存销售统计主键
     * @return 煤炭库存销售统计
     */
    @Override
    public WorkCoalStockSalesStat getWorkCoalStockSalesStatById(String id) {
        return workCoalStockSalesStatMapper.selectWorkCoalStockSalesStatById(id);
    }

    /**
     * 查询煤炭库存销售统计列表
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 煤炭库存销售统计
     */
    @Override
    public List<WorkCoalStockSalesStat> listWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat) {
        return workCoalStockSalesStatMapper.selectWorkCoalStockSalesStatList(workCoalStockSalesStat);
    }

    /**
     * 新增煤炭库存销售统计
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 结果
     */
    @Override
    public int saveWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat) {
        workCoalStockSalesStat.setCreateTime(DateUtils.getNowDate());
        return workCoalStockSalesStatMapper.insertWorkCoalStockSalesStat(workCoalStockSalesStat);
    }

    /**
     * 修改煤炭库存销售统计
     *
     * @param workCoalStockSalesStat 煤炭库存销售统计
     * @return 结果
     */
    @Override
    public int updateWorkCoalStockSalesStat(WorkCoalStockSalesStat workCoalStockSalesStat) {
        return workCoalStockSalesStatMapper.updateWorkCoalStockSalesStat(workCoalStockSalesStat);
    }

    /**
     * 批量删除煤炭库存销售统计
     *
     * @param ids 需要删除的煤炭库存销售统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalStockSalesStatByIds(String[] ids) {
        return workCoalStockSalesStatMapper.deleteWorkCoalStockSalesStatByIds(ids);
    }

    /**
     * 删除煤炭库存销售统计信息
     *
     * @param id 煤炭库存销售统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalStockSalesStatById(String id) {
        return workCoalStockSalesStatMapper.deleteWorkCoalStockSalesStatById(id);
    }
}
