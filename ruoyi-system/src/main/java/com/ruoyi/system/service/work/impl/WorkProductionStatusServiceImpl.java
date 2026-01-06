package com.ruoyi.system.service.work.impl;

import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.domain.work.WorkProductionStatus;
import com.ruoyi.system.mapper.work.WorkProductionStatusMapper;
import com.ruoyi.system.service.work.IWorkProductionStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安全生产信息日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkProductionStatusServiceImpl implements IWorkProductionStatusService {
    @Autowired
    private WorkProductionStatusMapper workProductionStatusMapper;

    /**
     * 查询安全生产信息日报
     * 
     * @param id 安全生产信息日报主键
     * @return 安全生产信息日报
     */
    @Override
    public WorkProductionStatus getWorkProductionStatusById(String id) {
        return workProductionStatusMapper.selectWorkProductionStatusById(id);
    }

    /**
     * 查询安全生产信息日报列表
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 安全生产信息日报
     */
    @Override
    public List<WorkProductionStatus> listWorkProductionStatus(WorkProductionStatus workProductionStatus) {
        return workProductionStatusMapper.selectWorkProductionStatusList(workProductionStatus);
    }

    /**
     * 查询安全生产信息日报列表
     *
     * @param workProductionStatus 安全生产信息日报
     * @return 安全生产信息日报
     */
    @Override
    public WorkProductionStatus listWorkProductionStatusDay(WorkProductionDailyReport workProductionDailyReport){
        return workProductionStatusMapper.listWorkProductionStatusDay(workProductionDailyReport);
    }

    /**
     * 新增安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    @Override
    public int saveWorkProductionStatus(List<WorkProductionStatus> workProductionStatus) {
        return workProductionStatusMapper.insertWorkProductionStatus(workProductionStatus);
    }

    /**
     * 修改安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    @Override
    public int updateWorkProductionStatus(WorkProductionStatus workProductionStatus) {
        return workProductionStatusMapper.updateWorkProductionStatus(workProductionStatus);
    }
    /**
     * 修改安全生产信息日报
     *
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    @Override
    public int updateWorkProductionStatusList(List<WorkProductionStatus> workProductionStatus) {
        return workProductionStatusMapper.updateWorkProductionStatusList(workProductionStatus);
    }

    /**
     * 批量删除安全生产信息日报
     * 
     * @param ids 需要删除的安全生产信息日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionStatusByIds(String[] ids) {
        return workProductionStatusMapper.deleteWorkProductionStatusByIds(ids);
    }

    /**
     * 删除安全生产信息日报信息
     * 
     * @param id 安全生产信息日报主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionStatusById(String id) {
        return workProductionStatusMapper.deleteWorkProductionStatusById(id);
    }
}
