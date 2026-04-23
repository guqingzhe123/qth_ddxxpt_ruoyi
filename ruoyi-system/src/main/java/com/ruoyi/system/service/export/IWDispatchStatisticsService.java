package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WDispatchStatistics;

import java.util.List;

/**
 * 七矿公司外采煤炭日报Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWDispatchStatisticsService {
    /**
     * 查询七矿公司外采煤炭日报
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 七矿公司外采煤炭日报
     */
    public WDispatchStatistics getWDispatchStatisticsById(String id);

    /**
     * 查询七矿公司外采煤炭日报列表
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 七矿公司外采煤炭日报集合
     */
    public List<WDispatchStatistics> listWDispatchStatistics(WDispatchStatistics wDispatchStatistics);

    /**
     * 新增七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    public int saveWDispatchStatistics(WDispatchStatistics wDispatchStatistics);

    /**
     * 修改七矿公司外采煤炭日报
     * 
     * @param wDispatchStatistics 七矿公司外采煤炭日报
     * @return 结果
     */
    public int updateWDispatchStatistics(WDispatchStatistics wDispatchStatistics);

    /**
     * 批量删除七矿公司外采煤炭日报
     * 
     * @param ids 需要删除的七矿公司外采煤炭日报主键集合
     * @return 结果
     */
    public int deleteWDispatchStatisticsByIds(String[] ids);

    /**
     * 删除七矿公司外采煤炭日报信息
     * 
     * @param id 七矿公司外采煤炭日报主键
     * @return 结果
     */
    public int deleteWDispatchStatisticsById(String id);
}
