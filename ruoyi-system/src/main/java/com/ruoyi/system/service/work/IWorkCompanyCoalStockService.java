package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkCompanyCoalStock;

import java.util.List;

/**
 * 煤气公司精煤库存统计Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IWorkCompanyCoalStockService {
    /**
     * 查询煤气公司精煤库存统计
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 煤气公司精煤库存统计
     */
    public WorkCompanyCoalStock getWorkCompanyCoalStockById(String id);

    /**
     * 查询煤气公司精煤库存统计列表
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 煤气公司精煤库存统计集合
     */
    public List<WorkCompanyCoalStock> listWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 新增煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    public int saveWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 修改煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    public int updateWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 批量删除煤气公司精煤库存统计
     *
     * @param ids 需要删除的煤气公司精煤库存统计主键集合
     * @return 结果
     */
    public int deleteWorkCompanyCoalStockByIds(String[] ids);

    /**
     * 删除煤气公司精煤库存统计信息
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 结果
     */
    public int deleteWorkCompanyCoalStockById(String id);
}
