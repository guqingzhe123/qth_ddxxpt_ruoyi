package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkFullyMechanizedStats;

import java.util.List;

/**
 * 综采综掘统计Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkFullyMechanizedStatsMapper {
    /**
     * 查询综采综掘统计
     * 
     * @param id 综采综掘统计主键
     * @return 综采综掘统计
     */
    public WorkFullyMechanizedStats selectWorkFullyMechanizedStatsById(String id);

    /**
     * 查询综采综掘统计列表
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计集合
     */
    public List<WorkFullyMechanizedStats> selectWorkFullyMechanizedStatsList(WorkFullyMechanizedStats workFullyMechanizedStats);
    /**
     * 查询综采综掘统计列表
     *
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计集合
     */
    public List<WorkFullyMechanizedStats> selectWorkFullyMechanizedStatsListAll(WorkFullyMechanizedStats workFullyMechanizedStats);

    /**
     * 新增综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    public int insertWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats);

    /**
     * 修改综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    public int updateWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats);

    /**
     * 删除综采综掘统计
     * 
     * @param id 综采综掘统计主键
     * @return 结果
     */
    public int deleteWorkFullyMechanizedStatsById(String id);

    /**
     * 批量删除综采综掘统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkFullyMechanizedStatsByIds(String[] ids);
}
