package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStats;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStatsList;
import com.ruoyi.system.mapper.work.WorkFullyMechanizedStatsMapper;
import com.ruoyi.system.service.work.IWorkFullyMechanizedStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 综采综掘统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkFullyMechanizedStatsServiceImpl implements IWorkFullyMechanizedStatsService {
    @Autowired
    private WorkFullyMechanizedStatsMapper workFullyMechanizedStatsMapper;

    /**
     * 查询综采综掘统计
     * 
     * @param id 综采综掘统计主键
     * @return 综采综掘统计
     */
    @Override
    public WorkFullyMechanizedStats getWorkFullyMechanizedStatsById(String id) {
        return workFullyMechanizedStatsMapper.selectWorkFullyMechanizedStatsById(id);
    }

    /**
     * 查询综采综掘统计列表
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计
     */
    @Override
    public List<WorkFullyMechanizedStats> listWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats) {
        return workFullyMechanizedStatsMapper.selectWorkFullyMechanizedStatsList(workFullyMechanizedStats);
    }
    /**
     * 查询综采综掘统计列表
     *
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 综采综掘统计
     */
    @Override
    public List<WorkFullyMechanizedStats> listWorkFullyMechanizedStatsALL(WorkFullyMechanizedStats workFullyMechanizedStats) {
        return workFullyMechanizedStatsMapper.selectWorkFullyMechanizedStatsListAll(workFullyMechanizedStats);
    }

    /**
     * 新增综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    @Override
    public int saveWorkFullyMechanizedStats(WorkFullyMechanizedStatsList workFullyMechanizedStats) {

        try{
            for (WorkFullyMechanizedStats 综采 :workFullyMechanizedStats.getFullyMining()) {
                WorkFullyMechanizedStats w1=new WorkFullyMechanizedStats();
                w1.setStatsType(综采.getStatsType());
                w1.setUnitName(综采.getUnitName());
                w1.setTeamName(综采.getTeamName());
                w1.setUnitCode(综采.getUnitCode());
                w1.setDutyDate(workFullyMechanizedStats.getDutyDate());
                List<WorkFullyMechanizedStats> workInstallationFaces = workFullyMechanizedStatsMapper.selectWorkFullyMechanizedStatsList(w1);
                if(workInstallationFaces.size()>0){
                    if(workInstallationFaces.get(0).getStatus().equals("0")){
                        throw new IllegalArgumentException("请联系局里进行驳回");
                    }
                    综采.setStatus("0");
                    综采.setId(workInstallationFaces.get(0).getId());
                    综采.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    workFullyMechanizedStatsMapper.updateWorkFullyMechanizedStats(综采);
                }else {
                    综采.setStatus("0");
                    综采.setCreateTime(DateUtils.getNowDate());
                    综采.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    workFullyMechanizedStatsMapper.insertWorkFullyMechanizedStats(综采);
                }
            }
            for (WorkFullyMechanizedStats 综掘 :workFullyMechanizedStats.getComprehensive()) {
                WorkFullyMechanizedStats w1=new WorkFullyMechanizedStats();
                w1.setStatsType(综掘.getStatsType());
                w1.setUnitName(综掘.getUnitName());
                w1.setTeamName(综掘.getTeamName());
                w1.setUnitCode(综掘.getUnitCode());
                w1.setDutyDate(workFullyMechanizedStats.getDutyDate());
                List<WorkFullyMechanizedStats> workInstallationFaces = workFullyMechanizedStatsMapper.selectWorkFullyMechanizedStatsList(w1);
                if(workInstallationFaces.size()>0){
                    if(workInstallationFaces.get(0).getStatus().equals("0")){
                        throw new IllegalArgumentException("请联系局里进行驳回");
                    }
                    综掘.setStatus("0");
                    综掘.setId(workInstallationFaces.get(0).getId());
                    综掘.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    workFullyMechanizedStatsMapper.updateWorkFullyMechanizedStats(综掘);
                }else {
                    综掘.setStatus("0");
                    综掘.setCreateTime(DateUtils.getNowDate());
                    综掘.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    workFullyMechanizedStatsMapper.insertWorkFullyMechanizedStats(综掘);
                }
            }
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    /**
     * 修改综采综掘统计
     * 
     * @param workFullyMechanizedStats 综采综掘统计
     * @return 结果
     */
    @Override
    public int updateWorkFullyMechanizedStats(WorkFullyMechanizedStats workFullyMechanizedStats) {
        return workFullyMechanizedStatsMapper.updateWorkFullyMechanizedStats(workFullyMechanizedStats);
    }

    /**
     * 批量删除综采综掘统计
     * 
     * @param ids 需要删除的综采综掘统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkFullyMechanizedStatsByIds(String[] ids) {
        return workFullyMechanizedStatsMapper.deleteWorkFullyMechanizedStatsByIds(ids);
    }

    /**
     * 删除综采综掘统计信息
     * 
     * @param id 综采综掘统计主键
     * @return 结果
     */
    @Override
    public int deleteWorkFullyMechanizedStatsById(String id) {
        return workFullyMechanizedStatsMapper.deleteWorkFullyMechanizedStatsById(id);
    }
}
