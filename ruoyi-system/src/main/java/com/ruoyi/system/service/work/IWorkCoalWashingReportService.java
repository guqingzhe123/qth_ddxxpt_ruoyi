package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkCoalWashingReport;

import java.util.List;

/**
 * 洗煤数据填报Service接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkCoalWashingReportService {
    /**
     * 查询洗煤数据填报
     *
     * @param id 洗煤数据填报主键
     * @return 洗煤数据填报
     */
    public WorkCoalWashingReport getWorkCoalWashingReportById(Integer id);

    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报集合
     */
    public List<WorkCoalWashingReport> listWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport);
    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报集合
     */
    public List<WorkCoalWashingReport> WorkCoalWashingReportlist(WorkCoalWashingReport workCoalWashingReport);
    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报集合
     */
    public List<WorkCoalWashingReport> selectWorkCoalWashingReportYearList(WorkCoalWashingReport workCoalWashingReport);

    /**
     * 新增洗煤数据填报
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 结果
     */
    public int saveWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport);

    /**
     * 修改洗煤数据填报
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 结果
     */
    public int updateWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport);

    /**
     * 批量删除洗煤数据填报
     *
     * @param ids 需要删除的洗煤数据填报主键集合
     * @return 结果
     */
    public int deleteWorkCoalWashingReportByIds(Integer[] ids);

    /**
     * 删除洗煤数据填报信息
     *
     * @param id 洗煤数据填报主键
     * @return 结果
     */
    public int deleteWorkCoalWashingReportById(Integer id);
}

