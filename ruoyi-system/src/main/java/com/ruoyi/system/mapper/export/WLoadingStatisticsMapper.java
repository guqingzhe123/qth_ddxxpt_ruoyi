package com.ruoyi.system.mapper.export;

import com.ruoyi.system.domain.export.WLoadingStatistics;

import java.util.List;

/**
 * 4月份外销品种煤日报Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WLoadingStatisticsMapper {
    /**
     * 查询4月份外销品种煤日报
     * 
     * @param id 4月份外销品种煤日报主键
     * @return 4月份外销品种煤日报
     */
    public WLoadingStatistics selectWLoadingStatisticsById(String id);

    /**
     * 查询4月份外销品种煤日报列表
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 4月份外销品种煤日报集合
     */
    public List<WLoadingStatistics> selectWLoadingStatisticsList(WLoadingStatistics wLoadingStatistics);

    /**
     * 新增4月份外销品种煤日报
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 结果
     */
    public int insertWLoadingStatistics(WLoadingStatistics wLoadingStatistics);

    /**
     * 修改4月份外销品种煤日报
     * 
     * @param wLoadingStatistics 4月份外销品种煤日报
     * @return 结果
     */
    public int updateWLoadingStatistics(WLoadingStatistics wLoadingStatistics);

    /**
     * 删除4月份外销品种煤日报
     * 
     * @param id 4月份外销品种煤日报主键
     * @return 结果
     */
    public int deleteWLoadingStatisticsById(String id);

    /**
     * 批量删除4月份外销品种煤日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWLoadingStatisticsByIds(String[] ids);
}
