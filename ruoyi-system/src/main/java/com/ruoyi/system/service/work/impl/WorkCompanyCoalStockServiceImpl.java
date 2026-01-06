package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkCompanyCoalStock;
import com.ruoyi.system.mapper.work.WorkCompanyCoalStockMapper;
import com.ruoyi.system.service.work.IWorkCompanyCoalStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 煤气公司精煤库存统计Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Slf4j
@Service
public class WorkCompanyCoalStockServiceImpl implements IWorkCompanyCoalStockService {
    @Autowired
    private WorkCompanyCoalStockMapper workCompanyCoalStockMapper;

    /**
     * 查询煤气公司精煤库存统计
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 煤气公司精煤库存统计
     */
    @Override
    public WorkCompanyCoalStock getWorkCompanyCoalStockById(String id) {
        return workCompanyCoalStockMapper.selectWorkCompanyCoalStockById(id);
    }

    /**
     * 查询煤气公司精煤库存统计列表
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 煤气公司精煤库存统计
     */
    @Override
    public List<WorkCompanyCoalStock> listWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock) {
        return workCompanyCoalStockMapper.selectWorkCompanyCoalStockList(workCompanyCoalStock);
    }

    /**
     * 新增煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    @Override
    public int saveWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock) {
        workCompanyCoalStock.setCreateTime(DateUtils.getNowDate());
        return workCompanyCoalStockMapper.insertWorkCompanyCoalStock(workCompanyCoalStock);
    }

    /**
     * 修改煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    @Override
    public int updateWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock) {
        return workCompanyCoalStockMapper.updateWorkCompanyCoalStock(workCompanyCoalStock);
    }

    /**
     * 批量删除煤气公司精煤库存统计
     *
     * @param ids 需要删除的煤气公司精煤库存统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkCompanyCoalStockByIds(String[] ids) {
        return workCompanyCoalStockMapper.deleteWorkCompanyCoalStockByIds(ids);
    }

    /**
     * 删除煤气公司精煤库存统计信息
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkCompanyCoalStockById(String id) {
        return workCompanyCoalStockMapper.deleteWorkCompanyCoalStockById(id);
    }
}
