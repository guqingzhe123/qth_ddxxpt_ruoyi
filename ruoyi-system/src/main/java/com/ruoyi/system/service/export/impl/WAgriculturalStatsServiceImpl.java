package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WAgriculturalStats;
import com.ruoyi.system.mapper.export.WAgriculturalStatsMapper;
import com.ruoyi.system.service.export.IWAgriculturalStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外销商品煤销量情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WAgriculturalStatsServiceImpl implements IWAgriculturalStatsService {
    @Autowired
    private WAgriculturalStatsMapper wAgriculturalStatsMapper;

    /**
     * 查询外销商品煤销量情况
     * 
     * @param id 外销商品煤销量情况主键
     * @return 外销商品煤销量情况
     */
    @Override
    public WAgriculturalStats getWAgriculturalStatsById(String id) {
        return wAgriculturalStatsMapper.selectWAgriculturalStatsById(id);
    }

    /**
     * 查询外销商品煤销量情况列表
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 外销商品煤销量情况
     */
    @Override
    public List<WAgriculturalStats> listWAgriculturalStats(WAgriculturalStats wAgriculturalStats) {
        return wAgriculturalStatsMapper.selectWAgriculturalStatsList(wAgriculturalStats);
    }

    /**
     * 新增外销商品煤销量情况
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 结果
     */
    @Override
    public int saveWAgriculturalStats(WAgriculturalStats wAgriculturalStats) {
        wAgriculturalStats.setCreateTime(DateUtils.getNowDate());
        return wAgriculturalStatsMapper.insertWAgriculturalStats(wAgriculturalStats);
    }

    /**
     * 修改外销商品煤销量情况
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 结果
     */
    @Override
    public int updateWAgriculturalStats(WAgriculturalStats wAgriculturalStats) {
        wAgriculturalStats.setUpdateTime(DateUtils.getNowDate());
        return wAgriculturalStatsMapper.updateWAgriculturalStats(wAgriculturalStats);
    }

    /**
     * 批量删除外销商品煤销量情况
     * 
     * @param ids 需要删除的外销商品煤销量情况主键
     * @return 结果
     */
    @Override
    public int deleteWAgriculturalStatsByIds(String[] ids) {
        return wAgriculturalStatsMapper.deleteWAgriculturalStatsByIds(ids);
    }

    /**
     * 删除外销商品煤销量情况信息
     * 
     * @param id 外销商品煤销量情况主键
     * @return 结果
     */
    @Override
    public int deleteWAgriculturalStatsById(String id) {
        return wAgriculturalStatsMapper.deleteWAgriculturalStatsById(id);
    }
}
