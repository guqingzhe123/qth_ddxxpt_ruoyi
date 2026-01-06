package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ComprehensiveProductionStats;

import java.util.List;

/**
 * 外运统计Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ComprehensiveProductionStatsMapper {
    /**
     * 查询外运统计
     * 
     * @param id 外运统计主键
     * @return 外运统计
     */
    public ComprehensiveProductionStats selectComprehensiveProductionStatsById(String id);

    /**
     * 查询外运统计列表
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 外运统计集合
     */
    public List<ComprehensiveProductionStats> selectComprehensiveProductionStatsList(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 新增外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    public int insertComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 修改外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    public int updateComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats);

    /**
     * 删除外运统计
     * 
     * @param id 外运统计主键
     * @return 结果
     */
    public int deleteComprehensiveProductionStatsById(String id);

    /**
     * 批量删除外运统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComprehensiveProductionStatsByIds(String[] ids);

    public ComprehensiveProductionStats selectComprehensiveProductionStatsDayList(ComprehensiveProductionStats comprehensiveProductionStats);

    public ComprehensiveProductionStats selectComprehensiveProductionStatsMonthList(ComprehensiveProductionStats comprehensiveProductionStats);

    public ComprehensiveProductionStats selectComprehensiveProductionStatsYearList(ComprehensiveProductionStats comprehensiveProductionStats);
}
