package com.ruoyi.system.mapper.export;

import com.ruoyi.system.domain.export.WDispatchStatistics;

import java.util.List;

/**
 * 七矿公司外采煤炭日报Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WDispatchStatisticsMapper {
    /**
     * 查询七矿公司外采煤炭日报
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 七矿公司外采煤炭日报
     */
    public WDispatchStatistics selectWDispatchStatisticsById(String id);

    /**
     * 查询七矿公司外采煤炭日报列表
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 七矿公司外采煤炭日报集合
     */
    public List<WDispatchStatistics> selectWDispatchStatisticsList(WDispatchStatistics wDispatchStatistics);

    /**
     * 新增七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    public int insertWDispatchStatistics(WDispatchStatistics wDispatchStatistics);

    /**
     * 修改七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    public int updateWDispatchStatistics(WDispatchStatistics wDispatchStatistics);

    /**
     * 删除七矿公司外采煤炭日报
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 结果
     */
    public int deleteWDispatchStatisticsById(String id);

    /**
     * 批量删除七矿公司外采煤炭日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWDispatchStatisticsByIds(String[] ids);
}
