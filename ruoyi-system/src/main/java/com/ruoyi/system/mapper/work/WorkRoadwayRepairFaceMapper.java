package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkRoadwayRepairFace;

import java.util.List;

/**
 * 巷修面信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkRoadwayRepairFaceMapper {
    /**
     * 查询巷修面信息
     * 
     * @param id 巷修面信息主键
     * @return 巷修面信息
     */
    public WorkRoadwayRepairFace selectWorkRoadwayRepairFaceById(String id);

    /**
     * 查询巷修面信息列表
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 巷修面信息集合
     */
    public List<WorkRoadwayRepairFace> selectWorkRoadwayRepairFaceList(WorkRoadwayRepairFace workRoadwayRepairFace);

    /**
     * 新增巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    public int insertWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace);

    /**
     * 修改巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    public int updateWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace);

    /**
     * 删除巷修面信息
     * 
     * @param id 巷修面信息主键
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceById(String id);

    /**
     * 批量删除巷修面信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceByIds(String[] ids);
}
