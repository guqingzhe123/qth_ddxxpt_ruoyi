package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.domain.work.WorkProductionStatus;

import java.util.List;

/**
 * 安全生产信息日报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkProductionStatusMapper {
    /**
     * 查询安全生产信息日报
     * 
     * @param id 安全生产信息日报主键
     * @return 安全生产信息日报
     */
    public WorkProductionStatus selectWorkProductionStatusById(String id);

    /**
     * 查询安全生产信息日报列表
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 安全生产信息日报集合
     */
    public List<WorkProductionStatus> selectWorkProductionStatusList(WorkProductionStatus workProductionStatus);
    /**
     * 查询安全生产信息日报列表
     *
     * @param workProductionStatus 安全生产信息日报
     * @return 安全生产信息日报集合
     */
    public WorkProductionStatus listWorkProductionStatusDay(WorkProductionDailyReport workProductionDailyReport);

    /**
     * 新增安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    public int insertWorkProductionStatus(List<WorkProductionStatus> workProductionStatus);

    /**
     * 修改安全生产信息日报
     * 
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    public int updateWorkProductionStatus(WorkProductionStatus workProductionStatus);
    /**
     * 修改安全生产信息日报
     *
     * @param workProductionStatus 安全生产信息日报
     * @return 结果
     */
    public int updateWorkProductionStatusList(List<WorkProductionStatus> workProductionStatus);

    /**
     * 删除安全生产信息日报
     * 
     * @param id 安全生产信息日报主键
     * @return 结果
     */
    public int deleteWorkProductionStatusById(String id);

    /**
     * 批量删除安全生产信息日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkProductionStatusByIds(String[] ids);
}
