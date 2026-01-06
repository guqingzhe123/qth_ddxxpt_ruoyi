package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkProductionFaceDailyReport;

import java.util.List;

/**
 * 生产面日报Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkProductionFaceDailyReportMapper {
    /**
     * 查询生产面日报
     *
     * @param id 生产面日报主键
     * @return 生产面日报
     */
    public WorkProductionFaceDailyReport selectWorkProductionFaceDailyReportById(String id);

    /**
     * 查询生产面日报列表
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 生产面日报集合
     */
    public List<WorkProductionFaceDailyReport> selectWorkProductionFaceDailyReportList(WorkProductionFaceDailyReport workProductionFaceDailyReport);

    /**
     * 新增生产面日报
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 结果
     */
    public int insertWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport);

    /**
     * 修改生产面日报
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 结果
     */
    public int updateWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport);

    int batchUpdateWorkProductionFaceDailyReport(List<WorkProductionFaceDailyReport> reports);

    /**
     * 删除生产面日报
     *
     * @param id 生产面日报主键
     * @return 结果
     */
    public int deleteWorkProductionFaceDailyReportById(String id);

    /**
     * 批量删除生产面日报
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkProductionFaceDailyReportByIds(String[] ids);
}
