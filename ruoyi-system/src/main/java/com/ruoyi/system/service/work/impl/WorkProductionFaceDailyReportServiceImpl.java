package com.ruoyi.system.service.work.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.domain.work.WorkProductionFaceDailyReport;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.mapper.work.WorkProductionFaceDailyReportMapper;
import com.ruoyi.system.service.work.IWorkProductionFaceDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 生产面日报Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkProductionFaceDailyReportServiceImpl implements IWorkProductionFaceDailyReportService {

    @Autowired
    private WorkProductionFaceDailyReportMapper workProductionFaceDailyReportMapper;

    @Resource
    private UserMessageMapper messageMapper;

    @Resource
    private SysUserMapper sysUserMapper;//查找用户id
    /**
     * 查询生产面日报
     *
     * @param id 生产面日报主键
     * @return 生产面日报
     */
    @Override
    public WorkProductionFaceDailyReport getWorkProductionFaceDailyReportById(String id) {
        return workProductionFaceDailyReportMapper.selectWorkProductionFaceDailyReportById(id);
    }

    /**
     * 查询生产面日报列表
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 生产面日报
     */
    @Override
    public List<WorkProductionFaceDailyReport> listWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport) {
        return workProductionFaceDailyReportMapper.selectWorkProductionFaceDailyReportList(workProductionFaceDailyReport);
    }
    /**
     * 新增生产面日报
     *
     * @param
     * @return 结果
     */
    @Override
    public int saveWorkProductionFaceDailyReport(List<WorkProductionFaceDailyReport> workProduction) {
        try{
            for (WorkProductionFaceDailyReport work :workProduction) {
                WorkProductionFaceDailyReport queryParam = new WorkProductionFaceDailyReport();
                queryParam.setUnit(work.getUnit());
                queryParam.setReportDate(work.getReportDate());
                queryParam.setTeamId(work.getTeamId());
                List<WorkProductionFaceDailyReport> existingReports = workProductionFaceDailyReportMapper.selectWorkProductionFaceDailyReportList(queryParam);
                if(existingReports.size()>0){
                    if(existingReports.get(0).getStatus()==2l){
                        work.setId(existingReports.get(0).getId());
                        work.setStatus(0L);
                        work.setCreateTime(DateUtils.getNowDate());
                        workProductionFaceDailyReportMapper.updateWorkProductionFaceDailyReport(work);
                    }else {
                        return 0;
                    }

                }else {
                    work.setStatus(0L);
                    work.setCreateTime(DateUtils.getNowDate());
                    workProductionFaceDailyReportMapper.insertWorkProductionFaceDailyReport(work);
                }
            }
        }catch (Exception e){
            return 0;
        }
       return 1;
    }

    /**
     * 修改生产面日报
     *
     * @param workProductionFaceDailyReport 生产面日报
     * @return 结果
     */
    @Override
    public int updateWorkProductionFaceDailyReport(WorkProductionFaceDailyReport workProductionFaceDailyReport) {
        // 判断相同 unit 和 reportDate 是否已存在（排除当前正在更新的记录）
        WorkProductionFaceDailyReport queryParam = new WorkProductionFaceDailyReport();
        queryParam.setUnit(workProductionFaceDailyReport.getUnit());
        queryParam.setReportDate(workProductionFaceDailyReport.getReportDate());
        List<WorkProductionFaceDailyReport> existingReports = workProductionFaceDailyReportMapper.selectWorkProductionFaceDailyReportList(queryParam);

        // 如果存在记录，检查是否是当前记录本身
        if (CollUtil.isNotEmpty(existingReports)) {
            // 过滤掉当前正在更新的记录（根据ID判断）
            long count = existingReports.stream()
                    .filter(report -> !report.getId().equals(workProductionFaceDailyReport.getId()))
                    .count();

            if (count > 0) {
                throw new RuntimeException("相同单位和报告日期的记录已存在，不能重复添加");
            }
        }
        workProductionFaceDailyReport.setStatus(0L);
        return workProductionFaceDailyReportMapper.updateWorkProductionFaceDailyReport(workProductionFaceDailyReport);
    }

    /**
     * 退回生产面日报
     *
     * @param json 生产面日报
     * @return 结果
     */
    @Override
    public AjaxResult backWorkProductionFaceDailyReport(String json) {
        if (StrUtil.isBlank(json)) {
            return AjaxResult.warn("参数不能为空");
        }

        JSONObject entries = JSONUtil.parseObj(json);
        String unit = entries.getStr("unit");
        if (StrUtil.isBlank(unit)) {
            return AjaxResult.warn("请选择单位");
        }
        String reportDate = entries.getStr("reportDate");
        if (StrUtil.isBlank(reportDate)) {
            return AjaxResult.warn("请选择报告日期");
        }
        WorkProductionFaceDailyReport workProductionFaceDailyReport = new WorkProductionFaceDailyReport();
        workProductionFaceDailyReport.setUnit(unit);
        workProductionFaceDailyReport.setReportDate(reportDate);
        List<WorkProductionFaceDailyReport> workProductionFaceDailyReports = workProductionFaceDailyReportMapper.selectWorkProductionFaceDailyReportList(workProductionFaceDailyReport);
        if (CollUtil.isEmpty(workProductionFaceDailyReports)) {
            return AjaxResult.warn("该矿未查询到生产面日报！");
        }
//        if (workProductionFaceDailyReports.size() > 1){
//            return AjaxResult.warn("该矿有多条生产面日报,请处理后在选择！");
//        }
        WorkProductionFaceDailyReport dailyReport = workProductionFaceDailyReports.get(0);
        if (dailyReport.getStatus() == 2L){
            return AjaxResult.warn("该日报已退回，请勿重复处理！");
        }
        for (WorkProductionFaceDailyReport report : workProductionFaceDailyReports) {
            report.setStatus(2L);
            workProductionFaceDailyReportMapper.updateWorkProductionFaceDailyReport(report);
        }
        String userId = sysUserMapper.selectUserByNickName(unit).getUserId();
        String message="七煤调度退回了您的日报请重新编写!";
        messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userId,message,new java.util.Date()));
        return AjaxResult.success("退回成功");
    }


    /**
     * 批量删除生产面日报
     *
     * @param ids 需要删除的生产面日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionFaceDailyReportByIds(String[] ids) {
        return workProductionFaceDailyReportMapper.deleteWorkProductionFaceDailyReportByIds(ids);
    }

    /**
     * 删除生产面日报信息
     *
     * @param id 生产面日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionFaceDailyReportById(String id) {
        return workProductionFaceDailyReportMapper.deleteWorkProductionFaceDailyReportById(id);
    }
}
