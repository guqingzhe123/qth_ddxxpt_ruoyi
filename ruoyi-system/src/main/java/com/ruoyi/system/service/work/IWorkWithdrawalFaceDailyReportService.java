package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkWithdrawalFaceDailyReport;

import java.util.List;

/**
 * 回撤面日报Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkWithdrawalFaceDailyReportService {
    /**
     * 查询回撤面日报
     * 
     * @param id 回撤面日报主键
     * @return 回撤面日报
     */
    public WorkWithdrawalFaceDailyReport getWorkWithdrawalFaceDailyReportById(String id);

    /**
     * 查询回撤面日报列表
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 回撤面日报集合
     */
    public List<WorkWithdrawalFaceDailyReport> listWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport);

    /**
     * 新增回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    public int saveWorkWithdrawalFaceDailyReport(List<WorkWithdrawalFaceDailyReport> workWithdrawalFaceDailyReport);

    /**
     * 修改回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    public int updateWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport);

    /**
     * 批量删除回撤面日报
     * 
     * @param ids 需要删除的回撤面日报主键集合
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceDailyReportByIds(String[] ids);

    /**
     * 删除回撤面日报信息
     * 
     * @param id 回撤面日报主键
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceDailyReportById(String id);
}
