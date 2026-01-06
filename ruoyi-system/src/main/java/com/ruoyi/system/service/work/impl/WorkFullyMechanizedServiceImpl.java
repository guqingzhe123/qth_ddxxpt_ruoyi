package com.ruoyi.system.service.work.impl;


import com.ruoyi.system.domain.work.WorkFullyMechanized;
import com.ruoyi.system.mapper.work.WorkFullyMechanizedMapper;
import com.ruoyi.system.service.work.IWorkFullyMechanizedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 综采综掘配置Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkFullyMechanizedServiceImpl implements IWorkFullyMechanizedService {
    @Autowired
    private WorkFullyMechanizedMapper workFullyMechanizedMapper;

    /**
     * 查询综采综掘配置
     *
     * @param id 综采综掘配置主键
     * @return 综采综掘配置
     */
    @Override
    public WorkFullyMechanized getWorkFullyMechanizedById(Long id) {
        return workFullyMechanizedMapper.selectWorkFullyMechanizedById(id);
    }

    /**
     * 查询综采综掘配置列表
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 综采综掘配置
     */
    @Override
    public List<WorkFullyMechanized> listWorkFullyMechanized(WorkFullyMechanized workFullyMechanized) {
        return workFullyMechanizedMapper.selectWorkFullyMechanizedList(workFullyMechanized);
    }

    /**
     * 新增综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    @Override
    public int saveWorkFullyMechanized(List<WorkFullyMechanized> workFullyMechanized) {
        try{
            for (WorkFullyMechanized 综采配置 :workFullyMechanized) {
                WorkFullyMechanized w1=new WorkFullyMechanized();
                w1.setStatsType(综采配置.getStatsType());
                w1.setUnitName(综采配置.getUnitName());
                w1.setUnitCode(综采配置.getUnitCode());
                w1.setTeamName(综采配置.getTeamName());
                List<WorkFullyMechanized> workInstallationFaces = workFullyMechanizedMapper.selectWorkFullyMechanizedList(w1);
                if(workInstallationFaces.size()>0){
                    综采配置.setId(workInstallationFaces.get(0).getId());
                    workFullyMechanizedMapper.updateWorkFullyMechanized(综采配置);
                }else {
                    workFullyMechanizedMapper.insertWorkFullyMechanized(综采配置);
                }
            }
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 修改综采综掘配置
     *
     * @param workFullyMechanized 综采综掘配置
     * @return 结果
     */
    @Override
    public int updateWorkFullyMechanized(WorkFullyMechanized workFullyMechanized) {
        return workFullyMechanizedMapper.updateWorkFullyMechanized(workFullyMechanized);
    }

    /**
     * 批量删除综采综掘配置
     *
     * @param ids 需要删除的综采综掘配置主键
     * @return 结果
     */
    @Override
    public int deleteWorkFullyMechanizedByIds(Long[] ids) {
        return workFullyMechanizedMapper.deleteWorkFullyMechanizedByIds(ids);
    }

    /**
     * 删除综采综掘配置信息
     *
     * @param id 综采综掘配置主键
     * @return 结果
     */
    @Override
    public int deleteWorkFullyMechanizedById(Long id) {
        return workFullyMechanizedMapper.deleteWorkFullyMechanizedById(id);
    }
}

