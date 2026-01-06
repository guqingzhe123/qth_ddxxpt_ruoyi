package com.ruoyi.system.service;

import com.ruoyi.system.domain.RawCoalToDailyReport;

import java.util.List;

/**
 * 原煤去向月报Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IRawCoalToDailyReportService {
    /**
     * 查询原煤去向月报
     * 
     * @param id 原煤去向月报主键
     * @return 原煤去向月报
     */
    public RawCoalToDailyReport getRawCoalToDailyReportById(Long id);

    /**
     * 查询原煤去向月报列表
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 原煤去向月报集合
     */
    public List<RawCoalToDailyReport> listRawCoalToDailyReport(RawCoalToDailyReport rawCoalToDailyReport);

    /**
     * 新增原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    public int saveRawCoalToDailyReport(List<RawCoalToDailyReport> rawCoalToDailyReport);

    /**
     * 修改原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    public int updateRawCoalToDailyReport(RawCoalToDailyReport rawCoalToDailyReport);

    /**
     * 批量删除原煤去向月报
     * 
     * @param ids 需要删除的原煤去向月报主键集合
     * @return 结果
     */
    public int deleteRawCoalToDailyReportByIds(Long[] ids);

    /**
     * 删除原煤去向月报信息
     * 
     * @param id 原煤去向月报主键
     * @return 结果
     */
    public int deleteRawCoalToDailyReportById(Long id);
}
