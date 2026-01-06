package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ComprehensiveProductionStats;
import com.ruoyi.system.mapper.ComprehensiveProductionStatsMapper;
import com.ruoyi.system.service.IComprehensiveProductionStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外运统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class ComprehensiveProductionStatsServiceImpl implements IComprehensiveProductionStatsService {
    @Autowired
    private ComprehensiveProductionStatsMapper comprehensiveProductionStatsMapper;

    /**
     * 查询外运统计
     * 
     * @param id 外运统计主键
     * @return 外运统计
     */
    @Override
    public ComprehensiveProductionStats getComprehensiveProductionStatsById(String id) {
        return comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsById(id);
    }

    /**
     * 查询外运统计列表
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 外运统计
     */
    @Override
    public List<ComprehensiveProductionStats> listComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats) {
        return comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsList(comprehensiveProductionStats);
    }

    /**
     * 新增外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    @Override
    public int saveComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats) {
        ComprehensiveProductionStats comp=new ComprehensiveProductionStats();
        comp.setStatsDate(comprehensiveProductionStats.getStatsDate());
        List<ComprehensiveProductionStats> comprehensive1 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsList(comp);
        if(comprehensive1.size()>0){
            comprehensiveProductionStats.setId(comprehensive1.get(0).getId());
            comprehensiveProductionStats.setUpdateTime(DateUtils.getNowDate());
            return comprehensiveProductionStatsMapper.updateComprehensiveProductionStats(comprehensiveProductionStats);
        }else {
            comprehensiveProductionStats.setCreateTime(DateUtils.getNowDate());
            return comprehensiveProductionStatsMapper.insertComprehensiveProductionStats(comprehensiveProductionStats);
        }
    }

    /**
     * 修改外运统计
     * 
     * @param comprehensiveProductionStats 外运统计
     * @return 结果
     */
    @Override
    public int updateComprehensiveProductionStats(ComprehensiveProductionStats comprehensiveProductionStats) {
        comprehensiveProductionStats.setUpdateTime(DateUtils.getNowDate());
        return comprehensiveProductionStatsMapper.updateComprehensiveProductionStats(comprehensiveProductionStats);
    }

    /**
     * 批量删除外运统计
     * 
     * @param ids 需要删除的外运统计主键
     * @return 结果
     */
    @Override
    public int deleteComprehensiveProductionStatsByIds(String[] ids) {
        return comprehensiveProductionStatsMapper.deleteComprehensiveProductionStatsByIds(ids);
    }

    /**
     * 删除外运统计信息
     * 
     * @param id 外运统计主键
     * @return 结果
     */
    @Override
    public int deleteComprehensiveProductionStatsById(String id) {
        return comprehensiveProductionStatsMapper.deleteComprehensiveProductionStatsById(id);
    }
}
