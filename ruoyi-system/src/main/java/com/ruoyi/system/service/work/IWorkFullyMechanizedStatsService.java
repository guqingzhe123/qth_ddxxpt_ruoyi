package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkFullyMechanizedStats;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStatsList;

import java.util.List;

/**
 * 综采综掘统计Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkFullyMechanizedStatsService {
    /**
     * 查询综采综掘统计
     * 
     * @param id 综采综掘统计主键
     * @return 综采综掘统计
     */
    public WorkFullyMechanizedStats getWorkFullyMechanizedStatsById(String id);

    /**
     * 查询综采综掘统计列表
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计集合
     */
    public List<WorkFullyMechanizedStats> listWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats);
    /**
     * 查询综采综掘统计列表
     *
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计集合
     */
    public List<WorkFullyMechanizedStats> listWorkFullyMechanizedStatsALL(WorkFullyMechanizedStats workFullyMechanizedStats);

    /**
     * 新增综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    public int saveWorkFullyMechanizedStats(WorkFullyMechanizedStatsList workFullyMechanizedStats);

    /**
     * 修改综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    public int updateWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats);

    /**
     * 批量删除综采综掘统计
     * 
     * @param ids 需要删除的综采综掘统计主键集合
     * @return 结果
     */
    public int deleteWorkFullyMechanizedStatsByIds(String[] ids);

    /**
     * 删除综采综掘统计信息
     * 
     * @param id 综采综掘统计主键
     * @return 结果
     */
    public int deleteWorkFullyMechanizedStatsById(String id);
}
