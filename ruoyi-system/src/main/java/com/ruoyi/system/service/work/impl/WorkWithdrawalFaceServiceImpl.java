package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkWithdrawalFace;
import com.ruoyi.system.mapper.work.WorkWithdrawalFaceMapper;
import com.ruoyi.system.service.work.IWorkWithdrawalFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回撤面配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkWithdrawalFaceServiceImpl implements IWorkWithdrawalFaceService {
    @Autowired
    private WorkWithdrawalFaceMapper workWithdrawalFaceMapper;

    /**
     * 查询回撤面配置
     * 
     * @param id 回撤面配置主键
     * @return 回撤面配置
     */
    @Override
    public WorkWithdrawalFace getWorkWithdrawalFaceById(String id) {
        return workWithdrawalFaceMapper.selectWorkWithdrawalFaceById(id);
    }

    /**
     * 查询回撤面配置列表
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 回撤面配置
     */
    @Override
    public List<WorkWithdrawalFace> listWorkWithdrawalFace(WorkWithdrawalFace workWithdrawalFace) {
        return workWithdrawalFaceMapper.selectWorkWithdrawalFaceList(workWithdrawalFace);
    }

    /**
     * 新增回撤面配置
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 结果
     */
    @Override
    public int saveWorkWithdrawalFace(List<WorkWithdrawalFace> workWithdrawalFace) {
        try{
            for (WorkWithdrawalFace work:workWithdrawalFace) {
                WorkWithdrawalFace w1=new WorkWithdrawalFace();
                w1.setUnit(work.getUnit());
                w1.setStatus(0);
                w1.setTeamNo(work.getTeamNo());
                List<WorkWithdrawalFace> workInstallationFaces = workWithdrawalFaceMapper.selectWorkWithdrawalFaceList(w1);
                if(workInstallationFaces.size()>0){
                    w1.setMiningTechnology(work.getMiningTechnology());
                    w1.setInstallationLocation(work.getInstallationLocation());
                    w1.setFaceYard(work.getFaceYard());
                    w1.setPlannedSupportQty(work.getPlannedSupportQty());
                    w1.setPlannedChuteQty(work.getPlannedChuteQty());
                    w1.setRemainingStentCount(work.getPlannedSupportQty());
                    w1.setRemainingChute(work.getPlannedChuteQty());
                    workWithdrawalFaceMapper.updateWorkWithdrawalFace(w1);
                }else {
                    work.setStatus(0);
                    work.setCreateTime(DateUtils.getNowDate());
                    work.setRemainingStentCount(work.getPlannedSupportQty());
                    work.setRemainingChute(work.getPlannedChuteQty());
                    workWithdrawalFaceMapper.insertWorkWithdrawalFace(work);
                }
            }
        }catch (Exception e){
            return 0;
        }

        return  1;

    }

    /**
     * 修改回撤面配置
     * 
     * @param workWithdrawalFace 回撤面配置
     * @return 结果
     */
    @Override
    public int updateWorkWithdrawalFace(WorkWithdrawalFace workWithdrawalFace) {
        return workWithdrawalFaceMapper.updateWorkWithdrawalFace(workWithdrawalFace);
    }

    /**
     * 批量删除回撤面配置
     * 
     * @param ids 需要删除的回撤面配置主键
     * @return 结果
     */
    @Override
    public int deleteWorkWithdrawalFaceByIds(String[] ids) {
        return workWithdrawalFaceMapper.deleteWorkWithdrawalFaceByIds(ids);
    }

    /**
     * 删除回撤面配置信息
     * 
     * @param id 回撤面配置主键
     * @return 结果
     */
    @Override
    public int deleteWorkWithdrawalFaceById(String id) {
        return workWithdrawalFaceMapper.deleteWorkWithdrawalFaceById(id);
    }
}
