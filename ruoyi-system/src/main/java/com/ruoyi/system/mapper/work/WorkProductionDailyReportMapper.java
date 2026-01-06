package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkProductionDailyReport;

import java.util.List;

/**
 * 生产信息日报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkProductionDailyReportMapper {
    /**
     * 查询生产信息日报
     * 
     * @param id 生产信息日报主键
     * @return 生产信息日报
     */
    public WorkProductionDailyReport selectWorkProductionDailyReportById(String id);

    /**
     * 查询生产信息日报列表
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 生产信息日报集合
     */
    public List<WorkProductionDailyReport> selectWorkProductionDailyReportList(WorkProductionDailyReport workProductionDailyReport);


    /**
     * 查询生产信息日报列表
     *
     * @param workProductionDailyReport 生产信息日报
     * @return 生产信息日报集合
     */
    public WorkProductionDailyReport selectWorkProductionDailyRepor(WorkProductionDailyReport workProductionDailyReport);

    /**
     * 新增生产信息日报
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 结果
     */
    public int insertWorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport);

    /**
     * 修改生产信息日报
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 结果
     */
    public int updateWorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport);

    /**
     * 删除生产信息日报
     * 
     * @param id 生产信息日报主键
     * @return 结果
     */
    public int deleteWorkProductionDailyReportById(String id);

    /**
     * 批量删除生产信息日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkProductionDailyReportByIds(String[] ids);
}
