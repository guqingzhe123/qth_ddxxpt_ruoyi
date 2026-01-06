package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkFullyMechanized;

import java.util.List;

/**
 * 综采综掘配置Service接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkFullyMechanizedService {
    /**
     * 查询综采综掘配置
     *
     * @param id 综采综掘配置主键
     * @return 综采综掘配置
     */
    public WorkFullyMechanized getWorkFullyMechanizedById(Long id);

    /**
     * 查询综采综掘配置列表
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 综采综掘配置集合
     */
    public List<WorkFullyMechanized> listWorkFullyMechanized(WorkFullyMechanized workFullyMechanized);

    /**
     * 新增综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    public int saveWorkFullyMechanized(List<WorkFullyMechanized> workFullyMechanized);

    /**
     * 修改综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    public int updateWorkFullyMechanized(WorkFullyMechanized workFullyMechanized);

    /**
     * 批量删除综采综掘配置
     *
     * @param ids 需要删除的综采综掘配置主键集合
     * @return 结果
     */
    public int deleteWorkFullyMechanizedByIds(Long[] ids);

    /**
     * 删除综采综掘配置信息
     *
     * @param id 综采综掘配置主键
     * @return 结果
     */
    public int deleteWorkFullyMechanizedById(Long id);
}

