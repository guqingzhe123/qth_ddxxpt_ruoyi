package com.ruoyi.system.service.work;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.work.WorkProductionFace;

import java.util.List;

/**
 * 生产面信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkProductionFaceService {
    /**
     * 查询生产面信息
     * 
     * @param id 生产面信息主键
     * @return 生产面信息
     */
    public WorkProductionFace getWorkProductionFaceById(String id);

    /**
     * 查询生产面信息列表
     * 
     * @param workProductionFace 生产面信息
     * @return 生产面信息集合
     */
    public List<WorkProductionFace> listWorkProductionFace(WorkProductionFace workProductionFace);

    /**
     * 新增生产面信息
     * 
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    public int saveWorkProductionFace(List<WorkProductionFace> workProductionFace);

    /**
     * 修改生产面信息
     * 
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    public int updateWorkProductionFace(WorkProductionFace workProductionFace);

    /**
     * 批量删除生产面信息
     * 
     * @param ids 需要删除的生产面信息主键集合
     * @return 结果
     */
    public int deleteWorkProductionFaceByIds(String[] ids);

    /**
     * 删除生产面信息信息
     * 
     * @param id 生产面信息主键
     * @return 结果
     */
    public int deleteWorkProductionFaceById(String id);


    AjaxResult getProductionFaceListByUnit(String json);
}
