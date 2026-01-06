package com.ruoyi.system.service.work;

import com.ruoyi.system.domain.work.WorkRoadwayRepairFace;

import java.util.List;

/**
 * 巷修面信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkRoadwayRepairFaceService {
    /**
     * 查询巷修面信息
     * 
     * @param id 巷修面信息主键
     * @return 巷修面信息
     */
    public WorkRoadwayRepairFace getWorkRoadwayRepairFaceById(String id);

    /**
     * 查询巷修面信息列表
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 巷修面信息集合
     */
    public List<WorkRoadwayRepairFace> listWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace);

    /**
     * 新增巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    public int saveWorkRoadwayRepairFace(List<WorkRoadwayRepairFace> workRoadwayRepairFace);

    /**
     * 修改巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    public int updateWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace);

    /**
     * 批量删除巷修面信息
     * 
     * @param ids 需要删除的巷修面信息主键集合
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceByIds(String[] ids);

    /**
     * 删除巷修面信息信息
     * 
     * @param id 巷修面信息主键
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceById(String id);
}
