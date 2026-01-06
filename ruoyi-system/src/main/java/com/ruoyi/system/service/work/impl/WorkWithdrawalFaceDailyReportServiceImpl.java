package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkWithdrawalFaceDailyReport;
import com.ruoyi.system.mapper.work.WorkWithdrawalFaceDailyReportMapper;
import com.ruoyi.system.service.work.IWorkWithdrawalFaceDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回撤面日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkWithdrawalFaceDailyReportServiceImpl implements IWorkWithdrawalFaceDailyReportService {
    @Autowired
    private WorkWithdrawalFaceDailyReportMapper workWithdrawalFaceDailyReportMapper;

    /**
     * 查询回撤面日报
     * 
     * @param id 回撤面日报主键
     * @return 回撤面日报
     */
    @Override
    public WorkWithdrawalFaceDailyReport getWorkWithdrawalFaceDailyReportById(String id) {
        return workWithdrawalFaceDailyReportMapper.selectWorkWithdrawalFaceDailyReportById(id);
    }

    /**
     * 查询回撤面日报列表
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 回撤面日报
     */
    @Override
    public List<WorkWithdrawalFaceDailyReport> listWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport) {
        return workWithdrawalFaceDailyReportMapper.selectWorkWithdrawalFaceDailyReportList(workWithdrawalFaceDailyReport);
    }

    /**
     * 新增回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    @Override
    public int saveWorkWithdrawalFaceDailyReport(List<WorkWithdrawalFaceDailyReport> workWithdrawalFaceDailyReport) {
        try {
            for (WorkWithdrawalFaceDailyReport work:workWithdrawalFaceDailyReport) {
                work.setCreateTime(DateUtils.getNowDate());
                WorkWithdrawalFaceDailyReport report=new WorkWithdrawalFaceDailyReport();
                report.setUnit(work.getUnit());
                report.setTeamNo(work.getTeamNo());
                report.setReportDate(work.getReportDate());
                List<WorkWithdrawalFaceDailyReport> workReport = workWithdrawalFaceDailyReportMapper.selectWorkWithdrawalFaceDailyReportList(report);
                if(workReport.size()>0){
                    work.setStatus("0");
                    work.setId(workReport.get(0).getId());
                    workWithdrawalFaceDailyReportMapper.updateWorkWithdrawalFaceDailyReport(work);
                }else {
                    work.setStatus("0");
                    workWithdrawalFaceDailyReportMapper.insertWorkWithdrawalFaceDailyReport(work);
                }

            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    /**
     * 修改回撤面日报
     * 
     * @param workWithdrawalFaceDailyReport 回撤面日报
     * @return 结果
     */
    @Override
    public int updateWorkWithdrawalFaceDailyReport(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport) {
        return workWithdrawalFaceDailyReportMapper.updateWorkWithdrawalFaceDailyReport(workWithdrawalFaceDailyReport);
    }

    /**
     * 批量删除回撤面日报
     * 
     * @param ids 需要删除的回撤面日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkWithdrawalFaceDailyReportByIds(String[] ids) {
        return workWithdrawalFaceDailyReportMapper.deleteWorkWithdrawalFaceDailyReportByIds(ids);
    }

    /**
     * 删除回撤面日报信息
     * 
     * @param id 回撤面日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkWithdrawalFaceDailyReportById(String id) {
        return workWithdrawalFaceDailyReportMapper.deleteWorkWithdrawalFaceDailyReportById(id);
    }
}
