package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkCompanyCoalStock;

import java.util.List;

/**
 * 煤气公司精煤库存统计Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface WorkCompanyCoalStockMapper {
    /**
     * 查询煤气公司精煤库存统计
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 煤气公司精煤库存统计
     */
    public WorkCompanyCoalStock selectWorkCompanyCoalStockById(String id);

    /**
     * 查询煤气公司精煤库存统计列表
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 煤气公司精煤库存统计集合
     */
    public List<WorkCompanyCoalStock> selectWorkCompanyCoalStockList(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 新增煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    public int insertWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 修改煤气公司精煤库存统计
     *
     * @param workCompanyCoalStock 煤气公司精煤库存统计
     * @return 结果
     */
    public int updateWorkCompanyCoalStock(WorkCompanyCoalStock workCompanyCoalStock);

    /**
     * 删除煤气公司精煤库存统计
     *
     * @param id 煤气公司精煤库存统计主键
     * @return 结果
     */
    public int deleteWorkCompanyCoalStockById(String id);

    /**
     * 批量删除煤气公司精煤库存统计
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkCompanyCoalStockByIds(String[] ids);
}
