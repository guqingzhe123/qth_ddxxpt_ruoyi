package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkInstallationFace;

import java.util.List;

/**
 * 安装面信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkInstallationFaceMapper {
    /**
     * 查询安装面信息
     * 
     * @param id 安装面信息主键
     * @return 安装面信息
     */
    public WorkInstallationFace selectWorkInstallationFaceById(String id);

    /**
     * 查询安装面信息列表
     * 
     * @param workInstallationFace 安装面信息
     * @return 安装面信息集合
     */
    public List<WorkInstallationFace> selectWorkInstallationFaceList(WorkInstallationFace workInstallationFace);

    /**
     * 新增安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    public int insertWorkInstallationFace(WorkInstallationFace workInstallationFace);

    /**
     * 修改安装面信息
     * 
     * @param workInstallationFace 安装面信息
     * @return 结果
     */
    public int updateWorkInstallationFace(WorkInstallationFace workInstallationFace);

    /**
     * 删除安装面信息
     * 
     * @param id 安装面信息主键
     * @return 结果
     */
    public int deleteWorkInstallationFaceById(String id);

    /**
     * 批量删除安装面信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkInstallationFaceByIds(String[] ids);
}
