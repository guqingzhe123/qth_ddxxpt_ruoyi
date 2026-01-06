package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkInstallationFace;

import java.util.List;

/**
 * 安装面信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkInstallationFaceService {
    /**
     * 查询安装面信息
     * 
     * @param id 安装面信息主键
     * @return 安装面信息
     */
    public WorkInstallationFace getWorkInstallationFaceById(String id);

    /**
     * 查询安装面信息列表
     * 
     * @param workInstallationFace 安装面信息
     * @return 安装面信息集合
     */
    public List<WorkInstallationFace> listWorkInstallationFace(WorkInstallationFace workInstallationFace);

    /**
     * 新增安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    public int saveWorkInstallationFace(List<WorkInstallationFace> workInstallationFace);

    /**
     * 修改安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    public int updateWorkInstallationFace(WorkInstallationFace workInstallationFace);

    /**
     * 批量删除安装面信息
     * 
     * @param ids 需要删除的安装面信息主键集合
     * @return 结果
     */
    public int deleteWorkInstallationFaceByIds(String[] ids);

    /**
     * 删除安装面信息信息
     * 
     * @param id 安装面信息主键
     * @return 结果
     */
    public int deleteWorkInstallationFaceById(String id);
}
