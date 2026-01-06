package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkFullyMechanized;

import java.util.List;

/**
 * 综采综掘配置Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkFullyMechanizedMapper {
    /**
     * 查询综采综掘配置
     *
     * @param id 综采综掘配置主键
     * @return 综采综掘配置
     */
    public WorkFullyMechanized selectWorkFullyMechanizedById(Long id);

    /**
     * 查询综采综掘配置列表
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 综采综掘配置集合
     */
    public List<WorkFullyMechanized> selectWorkFullyMechanizedList(WorkFullyMechanized workFullyMechanized);

    /**
     * 新增综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    public int insertWorkFullyMechanized(WorkFullyMechanized workFullyMechanized);

    /**
     * 修改综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    public int updateWorkFullyMechanized(WorkFullyMechanized workFullyMechanized);

    /**
     * 删除综采综掘配置
     *
     * @param id 综采综掘配置主键
     * @return 结果
     */
    public int deleteWorkFullyMechanizedById(Long id);

    /**
     * 批量删除综采综掘配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkFullyMechanizedByIds(Long[] ids);
}
