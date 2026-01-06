package com.ruoyi.system.service;

import com.ruoyi.system.domain.LongmeiProductionSalesStats;

import java.util.List;

/**
 * 龙煤集团各分公司生产外销统计Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ILongmeiProductionSalesStatsService {
    /**
     * 查询龙煤集团各分公司生产外销统计
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 龙煤集团各分公司生产外销统计
     */
    public LongmeiProductionSalesStats getLongmeiProductionSalesStatsById(String id);

    /**
     * 查询龙煤集团各分公司生产外销统计列表
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 龙煤集团各分公司生产外销统计集合
     */
    public List<LongmeiProductionSalesStats> listLongmeiProductionSalesStats(LongmeiProductionSalesStats longmeiProductionSalesStats);
    /**
     * 查询龙煤集团各分公司生产外销统计列表
     *
     * @param statsDate 查询日期
     * @return 龙煤集团各分公司生产外销统计集合
     */
    public List<LongmeiProductionSalesStats> Alllist(String statsDate);

    /**
     * 新增龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    public int saveLongmeiProductionSalesStats(List<LongmeiProductionSalesStats> longmeiProductionSalesStats);

    /**
     * 修改龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    public int updateLongmeiProductionSalesStats(LongmeiProductionSalesStats longmeiProductionSalesStats);

    /**
     * 批量删除龙煤集团各分公司生产外销统计
     * 
     * @param ids 需要删除的龙煤集团各分公司生产外销统计主键集合
     * @return 结果
     */
    public int deleteLongmeiProductionSalesStatsByIds(String[] ids);

    /**
     * 删除龙煤集团各分公司生产外销统计信息
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 结果
     */
    public int deleteLongmeiProductionSalesStatsById(String id);
}
