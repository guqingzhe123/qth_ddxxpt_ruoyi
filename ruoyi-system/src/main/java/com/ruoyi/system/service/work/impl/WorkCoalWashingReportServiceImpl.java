package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkCoalWashingReport;
import com.ruoyi.system.mapper.work.WorkCoalWashingReportMapper;
import com.ruoyi.system.service.work.IWorkCoalWashingReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 洗煤数据填报Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkCoalWashingReportServiceImpl implements IWorkCoalWashingReportService {
    @Autowired
    private WorkCoalWashingReportMapper workCoalWashingReportMapper;

    /**
     * 查询洗煤数据填报
     *
     * @param id 洗煤数据填报主键
     * @return 洗煤数据填报
     */
    @Override
    public WorkCoalWashingReport getWorkCoalWashingReportById(Integer id) {
        return workCoalWashingReportMapper.selectWorkCoalWashingReportById(id);
    }

    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报
     */
    @Override
    public List<WorkCoalWashingReport> listWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport) {
        return workCoalWashingReportMapper.selectWorkCoalWashingReportList(workCoalWashingReport);
    }
    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报
     */
    @Override
    public List<WorkCoalWashingReport> WorkCoalWashingReportlist(WorkCoalWashingReport workCoalWashingReport) {
        return workCoalWashingReportMapper.selectWorkCoalWashingReportMonthList(workCoalWashingReport);
    }

    /**
     * 查询洗煤数据填报列表
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 洗煤数据填报
     */
    @Override
    public List<WorkCoalWashingReport> selectWorkCoalWashingReportYearList(WorkCoalWashingReport workCoalWashingReport) {
        return workCoalWashingReportMapper.selectWorkCoalWashingReportYearList(workCoalWashingReport);
    }
    /**
     * 新增洗煤数据填报
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 结果
     */
    @Override
    public int saveWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport) {
        workCoalWashingReport.setCreateTime(DateUtils.getNowDate());
        return workCoalWashingReportMapper.insertWorkCoalWashingReport(workCoalWashingReport);
    }

    /**
     * 修改洗煤数据填报
     *
     * @param workCoalWashingReport 洗煤数据填报
     * @return 结果
     */
    @Override
    public int updateWorkCoalWashingReport(WorkCoalWashingReport workCoalWashingReport) {
        return workCoalWashingReportMapper.updateWorkCoalWashingReport(workCoalWashingReport);
    }

    /**
     * 批量删除洗煤数据填报
     *
     * @param ids 需要删除的洗煤数据填报主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalWashingReportByIds(Integer[] ids) {
        return workCoalWashingReportMapper.deleteWorkCoalWashingReportByIds(ids);
    }

    /**
     * 删除洗煤数据填报信息
     *
     * @param id 洗煤数据填报主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalWashingReportById(Integer id) {
        return workCoalWashingReportMapper.deleteWorkCoalWashingReportById(id);
    }
}

