package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.domain.work.WorkProductionStatus;

import java.util.List;

/**
 * 安全生产信息日报Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkProductionStatusService {
    /**
     * 查询安全生产信息日报
     * 
     * @param id 安全生产信息日报主键
     * @return 安全生产信息日报
     */
    public WorkProductionStatus getWorkProductionStatusById(String id);

    /**
     * 查询安全生产信息日报列表
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 安全生产信息日报集合
     */
    public List<WorkProductionStatus> listWorkProductionStatus(WorkProductionStatus workProductionStatus);
    /**
     * 查询安全生产信息日报列表
     *
     * @param WorkProductionDailyReport 安全生产信息主表信息
     * @return 安全生产信息日报集合
     */
    public WorkProductionStatus listWorkProductionStatusDay(WorkProductionDailyReport workProductionDailyReport);
    /**
     * 新增安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    public int saveWorkProductionStatus(List<WorkProductionStatus> workProductionStatus);

    /**
     * 修改安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    public int updateWorkProductionStatus(WorkProductionStatus workProductionStatus);
    public int updateWorkProductionStatusList(List<WorkProductionStatus> workProductionStatus);
    /**
     * 批量删除安全生产信息日报
     * 
     * @param ids 需要删除的安全生产信息日报主键集合
     * @return 结果
     */
    public int deleteWorkProductionStatusByIds(String[] ids);

    /**
     * 删除安全生产信息日报信息
     * 
     * @param id 安全生产信息日报主键
     * @return 结果
     */
    public int deleteWorkProductionStatusById(String id);



}
