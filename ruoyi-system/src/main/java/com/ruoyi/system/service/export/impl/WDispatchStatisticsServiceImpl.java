package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WDispatchStatistics;
import com.ruoyi.system.mapper.export.WDispatchStatisticsMapper;
import com.ruoyi.system.service.export.IWDispatchStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 七矿公司外采煤炭日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WDispatchStatisticsServiceImpl implements IWDispatchStatisticsService {
    @Autowired
    private WDispatchStatisticsMapper wDispatchStatisticsMapper;

    /**
     * 查询七矿公司外采煤炭日报
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 七矿公司外采煤炭日报
     */
    @Override
    public WDispatchStatistics getWDispatchStatisticsById(String id) {
        return wDispatchStatisticsMapper.selectWDispatchStatisticsById(id);
    }

    /**
     * 查询七矿公司外采煤炭日报列表
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 七矿公司外采煤炭日报
     */
    @Override
    public List<WDispatchStatistics> listWDispatchStatistics(WDispatchStatistics wDispatchStatistics) {
        return wDispatchStatisticsMapper.selectWDispatchStatisticsList(wDispatchStatistics);
    }

    /**
     * 新增七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    @Override
    public int saveWDispatchStatistics(WDispatchStatistics wDispatchStatistics) {
        wDispatchStatistics.setCreateTime(DateUtils.getNowDate());
        return wDispatchStatisticsMapper.insertWDispatchStatistics(wDispatchStatistics);
    }

    /**
     * 修改七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    @Override
    public int updateWDispatchStatistics(WDispatchStatistics wDispatchStatistics) {
        wDispatchStatistics.setUpdateTime(DateUtils.getNowDate());
        return wDispatchStatisticsMapper.updateWDispatchStatistics(wDispatchStatistics);
    }

    /**
     * 批量删除七矿公司外采煤炭日报
     * 
     * @param ids 需要删除的七矿公司外采煤炭日报主键
     * @return 结果
     */
    @Override
    public int deleteWDispatchStatisticsByIds(String[] ids) {
        return wDispatchStatisticsMapper.deleteWDispatchStatisticsByIds(ids);
    }

    /**
     * 删除七矿公司外采煤炭日报信息
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 结果
     */
    @Override
    public int deleteWDispatchStatisticsById(String id) {
        return wDispatchStatisticsMapper.deleteWDispatchStatisticsById(id);
    }
}
