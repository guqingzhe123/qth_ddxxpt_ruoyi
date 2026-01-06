package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkProductionFace;

import java.util.List;

/**
 * 生产面信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkProductionFaceMapper {
    /**
     * 查询生产面信息
     * 
     * @param id 生产面信息主键
     * @return 生产面信息
     */
    public WorkProductionFace selectWorkProductionFaceById(String id);

    /**
     * 查询生产面信息列表
     * 
     * @param workProductionFace 生产面信息
     * @return 生产面信息集合
     */
    public List<WorkProductionFace> selectWorkProductionFaceList(WorkProductionFace workProductionFace);

    /**
     * 新增生产面信息
     * 
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    public int insertWorkProductionFace(WorkProductionFace workProductionFace);

    /**
     * 修改生产面信息
     * 
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    public int updateWorkProductionFace(WorkProductionFace workProductionFace);

    /**
     * 删除生产面信息
     * 
     * @param id 生产面信息主键
     * @return 结果
     */
    public int deleteWorkProductionFaceById(String id);

    /**
     * 批量删除生产面信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkProductionFaceByIds(String[] ids);
}
