package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkInstallationFaceDailyReport;
import com.ruoyi.system.mapper.work.WorkInstallationFaceDailyReportMapper;
import com.ruoyi.system.service.work.IWorkInstallationFaceDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安装面信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkInstallationFaceDailyReportServiceImpl implements IWorkInstallationFaceDailyReportService {
    @Autowired
    private WorkInstallationFaceDailyReportMapper workInstallationFaceDailyReportMapper;

    /**
     * 查询安装面信息
     *
     * @param id 安装面信息主键
     * @return 安装面信息
     */
    @Override
    public WorkInstallationFaceDailyReport getWorkInstallationFaceDailyReportById(Integer id) {
        return workInstallationFaceDailyReportMapper.selectWorkInstallationFaceDailyReportById(id);
    }

    /**
     * 查询安装面信息列表
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 安装面信息
     */
    @Override
    public List<WorkInstallationFaceDailyReport> listWorkInstallationFaceDailyReport(WorkInstallationFaceDailyReport workInstallationFaceDailyReport) {
        return workInstallationFaceDailyReportMapper.selectWorkInstallationFaceDailyReportList(workInstallationFaceDailyReport);
    }

    /**
     * 新增安装面信息
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 结果
     */
    @Override
    public int saveWorkInstallationFaceDailyReport(List<WorkInstallationFaceDailyReport> workInstallationFaceDailyReport) {
        try {
            for (WorkInstallationFaceDailyReport work:workInstallationFaceDailyReport) {
                work.setCreateTime(DateUtils.getNowDate());
                WorkInstallationFaceDailyReport report=new WorkInstallationFaceDailyReport();
                report.setUnit(work.getUnit());
                report.setTeamNo(work.getTeamNo());
                report.setReportDate(work.getReportDate());
                List<WorkInstallationFaceDailyReport> workReport = workInstallationFaceDailyReportMapper.selectWorkInstallationFaceDailyReportList(report);
                if(workReport.size()>0){
                    work.setStatus("0");
                    work.setId(workReport.get(0).getId());
                    workInstallationFaceDailyReportMapper.updateWorkInstallationFaceDailyReport(work);
                }else {
                    work.setStatus("0");
                    workInstallationFaceDailyReportMapper.insertWorkInstallationFaceDailyReport(work);
                }
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    /**
     * 修改安装面信息
     *
     * @param workInstallationFaceDailyReport 安装面信息
     * @return 结果
     */
    @Override
    public int updateWorkInstallationFaceDailyReport(WorkInstallationFaceDailyReport workInstallationFaceDailyReport) {
        return workInstallationFaceDailyReportMapper.updateWorkInstallationFaceDailyReport(workInstallationFaceDailyReport);
    }

    /**
     * 批量删除安装面信息
     *
     * @param ids 需要删除的安装面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkInstallationFaceDailyReportByIds(Integer[] ids) {
        return workInstallationFaceDailyReportMapper.deleteWorkInstallationFaceDailyReportByIds(ids);
    }

    /**
     * 删除安装面信息信息
     *
     * @param id 安装面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkInstallationFaceDailyReportById(Integer id) {
        return workInstallationFaceDailyReportMapper.deleteWorkInstallationFaceDailyReportById(id);
    }

}

