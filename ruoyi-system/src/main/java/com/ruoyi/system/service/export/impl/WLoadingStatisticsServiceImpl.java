package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WLoadingStatistics;
import com.ruoyi.system.mapper.export.WLoadingStatisticsMapper;
import com.ruoyi.system.service.export.IWLoadingStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 4月份外销品种煤日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WLoadingStatisticsServiceImpl implements IWLoadingStatisticsService {
    @Autowired
    private WLoadingStatisticsMapper wLoadingStatisticsMapper;

    /**
     * 查询4月份外销品种煤日报
     * 
     * @param id 4月份外销品种煤日报主键
     * @return 4月份外销品种煤日报
     */
    @Override
    public WLoadingStatistics getWLoadingStatisticsById(String id) {
        return wLoadingStatisticsMapper.selectWLoadingStatisticsById(id);
    }

    /**
     * 查询4月份外销品种煤日报列表
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 4月份外销品种煤日报
     */
    @Override
    public List<WLoadingStatistics> listWLoadingStatistics(WLoadingStatistics wLoadingStatistics) {
        return wLoadingStatisticsMapper.selectWLoadingStatisticsList(wLoadingStatistics);
    }

    /**
     * 新增4月份外销品种煤日报
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 结果
     */
    @Override
    public int saveWLoadingStatistics(WLoadingStatistics wLoadingStatistics) {
        wLoadingStatistics.setCreateTime(DateUtils.getNowDate());
        return wLoadingStatisticsMapper.insertWLoadingStatistics(wLoadingStatistics);
    }

    /**
     * 修改4月份外销品种煤日报
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 结果
     */
    @Override
    public int updateWLoadingStatistics(WLoadingStatistics wLoadingStatistics) {
        wLoadingStatistics.setUpdateTime(DateUtils.getNowDate());
        return wLoadingStatisticsMapper.updateWLoadingStatistics(wLoadingStatistics);
    }

    /**
     * 批量删除4月份外销品种煤日报
     * 
     * @param ids 需要删除的4月份外销品种煤日报主键
     * @return 结果
     */
    @Override
    public int deleteWLoadingStatisticsByIds(String[] ids) {
        return wLoadingStatisticsMapper.deleteWLoadingStatisticsByIds(ids);
    }

    /**
     * 删除4月份外销品种煤日报信息
     * 
     * @param id 4月份外销品种煤日报主键
     * @return 结果
     */
    @Override
    public int deleteWLoadingStatisticsById(String id) {
        return wLoadingStatisticsMapper.deleteWLoadingStatisticsById(id);
    }
}
