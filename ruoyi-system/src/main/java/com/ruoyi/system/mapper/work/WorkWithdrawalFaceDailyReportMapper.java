package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkWithdrawalFaceDailyReport;

import java.util.List;

/**
 * 回撤面日报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkWithdrawalFaceDailyReportMapper {
    /**
     * 查询回撤面日报
     * 
     * @param id 回撤面日报主键
     * @return 回撤面日报
     */
    public WorkWithdrawalFaceDailyReport selectWorkWithdrawalFaceDailyReportById(String id);

    /**
     * 查询回撤面日报列表
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 回撤面日报集合
     */
    public List<WorkWithdrawalFaceDailyReport> selectWorkWithdrawalFaceDailyReportList(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport);

    /**
     * 新增回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    public int insertWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport);

    /**
     * 修改回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    public int updateWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport);

    /**
     * 删除回撤面日报
     * 
     * @param id 回撤面日报主键
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceDailyReportById(String id);

    /**
     * 批量删除回撤面日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceDailyReportByIds(String[] ids);
}
