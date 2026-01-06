package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkWithdrawalFace;

import java.util.List;

/**
 * 回撤面配置Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkWithdrawalFaceMapper {
    /**
     * 查询回撤面配置
     * 
     * @param id 回撤面配置主键
     * @return 回撤面配置
     */
    public WorkWithdrawalFace selectWorkWithdrawalFaceById(String id);

    /**
     * 查询回撤面配置列表
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 回撤面配置集合
     */
    public List<WorkWithdrawalFace> selectWorkWithdrawalFaceList(WorkWithdrawalFace workWithdrawalFace);

    /**
     * 新增回撤面配置
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 结果
     */
    public int insertWorkWithdrawalFace(WorkWithdrawalFace workWithdrawalFace);

    /**
     * 修改回撤面配置
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 结果
     */
    public int updateWorkWithdrawalFace(WorkWithdrawalFace workWithdrawalFace);

    /**
     * 删除回撤面配置
     * 
     * @param id 回撤面配置主键
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceById(String id);

    /**
     * 批量删除回撤面配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkWithdrawalFaceByIds(String[] ids);
}
