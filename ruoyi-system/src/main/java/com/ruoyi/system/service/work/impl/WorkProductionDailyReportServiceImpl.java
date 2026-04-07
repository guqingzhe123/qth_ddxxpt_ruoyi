package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.mapper.work.WorkProductionDailyReportMapper;
import com.ruoyi.system.service.work.IWorkProductionDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产信息日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkProductionDailyReportServiceImpl implements IWorkProductionDailyReportService {
    @Autowired
    private WorkProductionDailyReportMapper workProductionDailyReportMapper;

    /**
     * 查询生产信息日报
     * 
     * @param id 生产信息日报主键
     * @return 生产信息日报
     */
    @Override
    public WorkProductionDailyReport getWorkProductionDailyReportById(String id) {
        return workProductionDailyReportMapper.selectWorkProductionDailyReportById(id);
    }
    /**
     * 查询生产信息日报列表
     *
     * @param workProductionDailyReport 生产信息日报
     * @return 生产信息日报
     */
    @Override
    public WorkProductionDailyReport WorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport) {
        return workProductionDailyReportMapper.selectWorkProductionDailyRepor(workProductionDailyReport);
    }
    /**
     * 查询生产信息日报列表
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 生产信息日报
     */
    @Override
    public List<WorkProductionDailyReport> listWorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport) {
        return workProductionDailyReportMapper.selectWorkProductionDailyReportList(workProductionDailyReport);
    }


    /**
     * 新增生产信息日报
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 结果
     */
    @Override
    public int saveWorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport) {
        workProductionDailyReport.setCreateTime(DateUtils.getNowDate());
        return workProductionDailyReportMapper.insertWorkProductionDailyReport(workProductionDailyReport);
    }

    /**
     * 修改生产信息日报
     * 
     * @param workProductionDailyReport 生产信息日报
     * @return 结果
     */
    @Override
    public int updateWorkProductionDailyReport(WorkProductionDailyReport workProductionDailyReport) {
        return workProductionDailyReportMapper.updateWorkProductionDailyReport(workProductionDailyReport);
    }

    /**
     * 批量删除生产信息日报
     * 
     * @param ids 需要删除的生产信息日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionDailyReportByIds(String[] ids) {
        return workProductionDailyReportMapper.deleteWorkProductionDailyReportByIds(ids);
    }

    /**
     * 删除生产信息日报信息
     * 
     * @param id 生产信息日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionDailyReportById(String id) {
        return workProductionDailyReportMapper.deleteWorkProductionDailyReportById(id);
    }

    /**
     * 查询日销售
     *
     * @param id 生产信息日报主键
     * @return 结果
     */
    public int listWorkProductionStatusDay(WorkProductionDailyReport workProductionDailyReport) {
        return workProductionDailyReportMapper.listWorkProductionStatusDay(workProductionDailyReport);
    }

    /**
     * 查询日销售
     *
     * @param id 生产信息日报主键
     * @return 结果
     */
    public int listWorkProductionStatusMonth(WorkProductionDailyReport workProductionDailyReport) {
        return workProductionDailyReportMapper.listWorkProductionStatusMonth(workProductionDailyReport);
    }

}
