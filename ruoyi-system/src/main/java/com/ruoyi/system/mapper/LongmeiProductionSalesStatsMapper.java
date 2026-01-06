package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LongmeiProductionSalesStats;

import java.util.List;

/**
 * 龙煤集团各分公司生产外销统计Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface LongmeiProductionSalesStatsMapper {
    /**
     * 查询龙煤集团各分公司生产外销统计
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 龙煤集团各分公司生产外销统计
     */
    public LongmeiProductionSalesStats selectLongmeiProductionSalesStatsById(String id);

    /**
     * 查询龙煤集团各分公司生产外销统计列表
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 龙煤集团各分公司生产外销统计集合
     */
    public List<LongmeiProductionSalesStats> selectLongmeiProductionSalesStatsList(LongmeiProductionSalesStats longmeiProductionSalesStats);
    /**
     * 查询龙煤集团各分公司生产外销统计列表
     *
     * @param statsDate 龙煤集团各分公司生产外销统计
     * @return 龙煤集团各分公司生产外销统计集合
     */
    public List<LongmeiProductionSalesStats> Alllist(String statsDate);

    /**
     * 新增龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    public int insertLongmeiProductionSalesStats(List<LongmeiProductionSalesStats> longmeiProductionSalesStats);

    /**
     * 修改龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    public int updateLongmeiProductionSalesStats(LongmeiProductionSalesStats longmeiProductionSalesStats);

    /**
     * 删除龙煤集团各分公司生产外销统计
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 结果
     */
    public int deleteLongmeiProductionSalesStatsById(String id);

    /**
     * 批量删除龙煤集团各分公司生产外销统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLongmeiProductionSalesStatsByIds(String[] ids);
}
