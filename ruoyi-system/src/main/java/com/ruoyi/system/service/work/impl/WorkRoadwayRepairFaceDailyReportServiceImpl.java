package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkRoadwayRepairFaceDailyReport;
import com.ruoyi.system.mapper.work.WorkRoadwayRepairFaceDailyReportMapper;
import com.ruoyi.system.service.work.IWorkRoadwayRepairFaceDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 巷面配置日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkRoadwayRepairFaceDailyReportServiceImpl implements IWorkRoadwayRepairFaceDailyReportService {
    @Autowired
    private WorkRoadwayRepairFaceDailyReportMapper workRoadwayRepairFaceDailyReportMapper;

    /**
     * 查询巷面配置日报
     * 
     * @param id 巷面配置日报主键
     * @return 巷面配置日报
     */
    @Override
    public WorkRoadwayRepairFaceDailyReport getWorkRoadwayRepairFaceDailyReportById(String id) {
        return workRoadwayRepairFaceDailyReportMapper.selectWorkRoadwayRepairFaceDailyReportById(id);
    }

    /**
     * 查询巷面配置日报列表
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 巷面配置日报
     */
    @Override
    public List<WorkRoadwayRepairFaceDailyReport> listWorkRoadwayRepairFaceDailyReport(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport) {
        return workRoadwayRepairFaceDailyReportMapper.selectWorkRoadwayRepairFaceDailyReportList(workRoadwayRepairFaceDailyReport);
    }

    /**
     * 新增巷面配置日报
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 结果
     */
    @Override
    public int saveWorkRoadwayRepairFaceDailyReport(List<WorkRoadwayRepairFaceDailyReport> workRoadwayRepairFaceDailyReport) {
        try {
            for (WorkRoadwayRepairFaceDailyReport work:workRoadwayRepairFaceDailyReport) {
                work.setCreateTime(DateUtils.getNowDate());
                WorkRoadwayRepairFaceDailyReport report=new WorkRoadwayRepairFaceDailyReport();
                report.setUnit(work.getUnit());
                report.setWorkLocation(work.getWorkLocation());
                report.setShipmentMethod(work.getShipmentMethod());
                List<WorkRoadwayRepairFaceDailyReport> workReport = workRoadwayRepairFaceDailyReportMapper.selectWorkRoadwayRepairFaceDailyReportList(report);
                if(workReport.size()>0){
                    work.setStatus("0");
                    work.setId(workReport.get(0).getId());
                    workRoadwayRepairFaceDailyReportMapper.updateWorkRoadwayRepairFaceDailyReport(work);
                }else {
                    work.setStatus("0");
                    workRoadwayRepairFaceDailyReportMapper.insertWorkRoadwayRepairFaceDailyReport(work);
                }

            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    /**
     * 修改巷面配置日报
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 结果
     */
    @Override
    public int updateWorkRoadwayRepairFaceDailyReport(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport) {
        return workRoadwayRepairFaceDailyReportMapper.updateWorkRoadwayRepairFaceDailyReport(workRoadwayRepairFaceDailyReport);
    }

    /**
     * 批量删除巷面配置日报
     * 
     * @param ids 需要删除的巷面配置日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkRoadwayRepairFaceDailyReportByIds(String[] ids) {
        return workRoadwayRepairFaceDailyReportMapper.deleteWorkRoadwayRepairFaceDailyReportByIds(ids);
    }

    /**
     * 删除巷面配置日报信息
     * 
     * @param id 巷面配置日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkRoadwayRepairFaceDailyReportById(String id) {
        return workRoadwayRepairFaceDailyReportMapper.deleteWorkRoadwayRepairFaceDailyReportById(id);
    }
}
