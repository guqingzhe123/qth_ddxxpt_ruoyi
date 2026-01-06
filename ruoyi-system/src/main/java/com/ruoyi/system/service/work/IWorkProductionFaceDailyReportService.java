package com.ruoyi.system.service.work;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.work.WorkProductionFaceDailyReport;

import java.util.List;

/**
 * 生产面日报Service接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkProductionFaceDailyReportService {
    /**
     * 查询生产面日报
     *
     * @param id 生产面日报主键
     * @return 生产面日报
     */
    public WorkProductionFaceDailyReport getWorkProductionFaceDailyReportById(String id);

    /**
     * 查询生产面日报列表
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 生产面日报集合
     */
    public List<WorkProductionFaceDailyReport> listWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport);

    /**
     * 新增生产面日报
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 结果
     */
    public int saveWorkProductionFaceDailyReport(List<WorkProductionFaceDailyReport> workProductionFaceDailyReport);

    /**
     * 修改生产面日报
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 结果
     */
    public int updateWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport);

    /**
     * 退回生产面日报
     *
     * @param json 生产面日报
     * @return 结果
     */
    AjaxResult backWorkProductionFaceDailyReport(String json);
    /**
     * 批量删除生产面日报
     *
     * @param ids 需要删除的生产面日报主键集合
     * @return 结果
     */
    public int deleteWorkProductionFaceDailyReportByIds(String[] ids);

    /**
     * 删除生产面日报信息
     *
     * @param id 生产面日报主键
     * @return 结果
     */
    public int deleteWorkProductionFaceDailyReportById(String id);
}
