package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkInstallationFace;
import com.ruoyi.system.mapper.work.WorkInstallationFaceMapper;
import com.ruoyi.system.service.work.IWorkInstallationFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安装面信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkInstallationFaceServiceImpl implements IWorkInstallationFaceService {
    @Autowired
    private WorkInstallationFaceMapper workInstallationFaceMapper;

    /**
     * 查询安装面信息
     * 
     * @param id 安装面信息主键
     * @return 安装面信息
     */
    @Override
    public WorkInstallationFace getWorkInstallationFaceById(String id) {
        return workInstallationFaceMapper.selectWorkInstallationFaceById(id);
    }

    /**
     * 查询安装面信息列表
     * 
     * @param workInstallationFace 安装面信息
     * @return 安装面信息
     */
    @Override
    public List<WorkInstallationFace> listWorkInstallationFace(WorkInstallationFace workInstallationFace) {
        return workInstallationFaceMapper.selectWorkInstallationFaceList(workInstallationFace);
    }

    /**
     * 新增安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    @Override
    public int saveWorkInstallationFace(List<WorkInstallationFace> workInstallationFace) {
        try {
            for (WorkInstallationFace  work:workInstallationFace) {
                work.setStatus("0");
                WorkInstallationFace w1=new WorkInstallationFace();
                w1.setUnit(work.getUnit());
                w1.setTeamNo(work.getTeamNo());
                List<WorkInstallationFace> workInstallationFaces = workInstallationFaceMapper.selectWorkInstallationFaceList(w1);
                if(workInstallationFaces.size()>0){
                    work.setId(workInstallationFaces.get(0).getId());
                    workInstallationFaceMapper.updateWorkInstallationFace(work);
                }else {
                    work.setStatus("0");
                    work.setCreateTime(DateUtils.getNowDate());
                    workInstallationFaceMapper.insertWorkInstallationFace(work);
                }
            }
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    /**
     * 修改安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    @Override
    public int updateWorkInstallationFace(WorkInstallationFace workInstallationFace) {
        return workInstallationFaceMapper.updateWorkInstallationFace(workInstallationFace);
    }

    /**
     * 批量删除安装面信息
     * 
     * @param ids 需要删除的安装面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkInstallationFaceByIds(String[] ids) {
        return workInstallationFaceMapper.deleteWorkInstallationFaceByIds(ids);
    }

    /**
     * 删除安装面信息信息
     * 
     * @param id 安装面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkInstallationFaceById(String id) {
        return workInstallationFaceMapper.deleteWorkInstallationFaceById(id);
    }
}
