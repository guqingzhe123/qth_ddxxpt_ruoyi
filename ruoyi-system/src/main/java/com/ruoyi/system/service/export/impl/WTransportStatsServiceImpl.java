package com.ruoyi.system.service.export.impl;

import com.ruoyi.system.domain.export.WTransportStats;
import com.ruoyi.system.mapper.export.WTransportStatsMapper;
import com.ruoyi.system.service.export.IWTransportStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 驻矿公司煤炭调运日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WTransportStatsServiceImpl implements IWTransportStatsService {
    @Autowired
    private WTransportStatsMapper wTransportStatsMapper;

    /**
     * 查询驻矿公司煤炭调运日报
     * 
     * @param id 驻矿公司煤炭调运日报主键
     * @return 驻矿公司煤炭调运日报
     */
    @Override
    public WTransportStats getWTransportStatsById(String id) {
        return wTransportStatsMapper.selectWTransportStatsById(id);
    }

    /**
     * 查询驻矿公司煤炭调运日报列表
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 驻矿公司煤炭调运日报
     */
    @Override
    public List<WTransportStats> listWTransportStats(WTransportStats wTransportStats) {
        return wTransportStatsMapper.selectWTransportStatsList(wTransportStats);
    }

    /**
     * 新增驻矿公司煤炭调运日报
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 结果
     */
    @Override
    public int saveWTransportStats(WTransportStats wTransportStats) {
        return wTransportStatsMapper.insertWTransportStats(wTransportStats);
    }

    /**
     * 修改驻矿公司煤炭调运日报
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 结果
     */
    @Override
    public int updateWTransportStats(WTransportStats wTransportStats) {
        return wTransportStatsMapper.updateWTransportStats(wTransportStats);
    }

    /**
     * 批量删除驻矿公司煤炭调运日报
     * 
     * @param ids 需要删除的驻矿公司煤炭调运日报主键
     * @return 结果
     */
    @Override
    public int deleteWTransportStatsByIds(String[] ids) {
        return wTransportStatsMapper.deleteWTransportStatsByIds(ids);
    }

    /**
     * 删除驻矿公司煤炭调运日报信息
     * 
     * @param id 驻矿公司煤炭调运日报主键
     * @return 结果
     */
    @Override
    public int deleteWTransportStatsById(String id) {
        return wTransportStatsMapper.deleteWTransportStatsById(id);
    }
}
