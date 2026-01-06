package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkInstallationFaceDailyReport;

import java.util.List;

/**
 * 安装面信息Service接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkInstallationFaceDailyReportService {
    /**
     * 查询安装面信息
     *
     * @param id 安装面信息主键
     * @return 安装面信息
     */
    public WorkInstallationFaceDailyReport getWorkInstallationFaceDailyReportById(Integer id);

    /**
     * 查询安装面信息列表
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 安装面信息集合
     */
    public List<WorkInstallationFaceDailyReport> listWorkInstallationFaceDailyReport(WorkInstallationFaceDailyReport workInstallationFaceDailyReport);

    /**
     * 新增安装面信息
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 结果
     */
    public int saveWorkInstallationFaceDailyReport(List<WorkInstallationFaceDailyReport> workInstallationFaceDailyReport);

    /**
     * 修改安装面信息
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 结果
     */
    public int updateWorkInstallationFaceDailyReport(WorkInstallationFaceDailyReport workInstallationFaceDailyReport);

    /**
     * 批量删除安装面信息
     *
     * @param ids 需要删除的安装面信息主键集合
     * @return 结果
     */
    public int deleteWorkInstallationFaceDailyReportByIds(Integer[] ids);

    /**
     * 删除安装面信息信息
     *
     * @param id 安装面信息主键
     * @return 结果
     */
    public int deleteWorkInstallationFaceDailyReportById(Integer id);
}

