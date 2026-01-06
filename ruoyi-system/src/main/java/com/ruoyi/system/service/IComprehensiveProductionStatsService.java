package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ComprehensiveProductionStats;

/**
 * 外运统计Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IComprehensiveProductionStatsService {
    /**
     * 查询外运统计
     * 
     * @param id 外运统计主键
     * @return 外运统计
     */
    public ComprehensiveProductionStats getComprehensiveProductionStatsById(String id);

    /**
     * 查询外运统计列表
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 外运统计集合
     */
    public List<ComprehensiveProductionStats> listComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 新增外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    public int saveComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 修改外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    public int updateComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 批量删除外运统计
     * 
     * @param ids 需要删除的外运统计主键集合
     * @return 结果
     */
    public int deleteComprehensiveProductionStatsByIds(String[] ids);

    /**
     * 删除外运统计信息
     * 
     * @param id 外运统计主键
     * @return 结果
     */
    public int deleteComprehensiveProductionStatsById(String id);
}
