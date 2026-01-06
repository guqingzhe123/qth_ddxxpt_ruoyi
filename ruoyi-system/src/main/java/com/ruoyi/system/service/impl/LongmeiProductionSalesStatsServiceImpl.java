package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.LongmeiProductionSalesStats;
import com.ruoyi.system.mapper.LongmeiProductionSalesStatsMapper;
import com.ruoyi.system.service.ILongmeiProductionSalesStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 龙煤集团各分公司生产外销统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class LongmeiProductionSalesStatsServiceImpl implements ILongmeiProductionSalesStatsService {
    @Autowired
    private LongmeiProductionSalesStatsMapper longmeiProductionSalesStatsMapper;

    /**
     * 查询龙煤集团各分公司生产外销统计
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 龙煤集团各分公司生产外销统计
     */
    @Override
    public LongmeiProductionSalesStats getLongmeiProductionSalesStatsById(String id) {
        return longmeiProductionSalesStatsMapper.selectLongmeiProductionSalesStatsById(id);
    }

    /**
     * 查询龙煤集团各分公司生产外销统计列表
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 龙煤集团各分公司生产外销统计
     */
    @Override
    public List<LongmeiProductionSalesStats> listLongmeiProductionSalesStats(LongmeiProductionSalesStats longmeiProductionSalesStats) {
        return longmeiProductionSalesStatsMapper.selectLongmeiProductionSalesStatsList(longmeiProductionSalesStats);
    }
    /**
     * 查询龙煤集团各分公司生产外销统计列表
     *
     * @param statsDate 日期
     * @return 龙煤集团各分公司生产外销统计
     */
    @Override
    public List<LongmeiProductionSalesStats> Alllist(String statsDate) {
        return longmeiProductionSalesStatsMapper.Alllist(statsDate);
    }

    /**
     * 新增龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    @Override
    public int saveLongmeiProductionSalesStats(List<LongmeiProductionSalesStats> longmeiProductionSalesStats) {
        for (LongmeiProductionSalesStats longmeiProductionSalesStat:longmeiProductionSalesStats             ) {
            longmeiProductionSalesStat.setCreateTime(DateUtils.getNowDate());

        }
        return longmeiProductionSalesStatsMapper.insertLongmeiProductionSalesStats(longmeiProductionSalesStats);
    }

    /**
     * 修改龙煤集团各分公司生产外销统计
     * 
     * @param longmeiProductionSalesStats 龙煤集团各分公司生产外销统计
     * @return 结果
     */
    @Override
    public int updateLongmeiProductionSalesStats(LongmeiProductionSalesStats longmeiProductionSalesStats) {
        longmeiProductionSalesStats.setUpdateTime(DateUtils.getNowDate());
        return longmeiProductionSalesStatsMapper.updateLongmeiProductionSalesStats(longmeiProductionSalesStats);
    }

    /**
     * 批量删除龙煤集团各分公司生产外销统计
     * 
     * @param ids 需要删除的龙煤集团各分公司生产外销统计主键
     * @return 结果
     */
    @Override
    public int deleteLongmeiProductionSalesStatsByIds(String[] ids) {
        return longmeiProductionSalesStatsMapper.deleteLongmeiProductionSalesStatsByIds(ids);
    }

    /**
     * 删除龙煤集团各分公司生产外销统计信息
     * 
     * @param id 龙煤集团各分公司生产外销统计主键
     * @return 结果
     */
    @Override
    public int deleteLongmeiProductionSalesStatsById(String id) {
        return longmeiProductionSalesStatsMapper.deleteLongmeiProductionSalesStatsById(id);
    }
}
