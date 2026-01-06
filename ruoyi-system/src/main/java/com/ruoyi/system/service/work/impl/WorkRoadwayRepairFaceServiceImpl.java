package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkRoadwayRepairFace;
import com.ruoyi.system.mapper.work.WorkRoadwayRepairFaceMapper;
import com.ruoyi.system.service.work.IWorkRoadwayRepairFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 巷修面信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkRoadwayRepairFaceServiceImpl implements IWorkRoadwayRepairFaceService {
    @Autowired
    private WorkRoadwayRepairFaceMapper workRoadwayRepairFaceMapper;

    /**
     * 查询巷修面信息
     * 
     * @param id 巷修面信息主键
     * @return 巷修面信息
     */
    @Override
    public WorkRoadwayRepairFace getWorkRoadwayRepairFaceById(String id) {
        return workRoadwayRepairFaceMapper.selectWorkRoadwayRepairFaceById(id);
    }

    /**
     * 查询巷修面信息列表
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 巷修面信息
     */
    @Override
    public List<WorkRoadwayRepairFace> listWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace) {
        return workRoadwayRepairFaceMapper.selectWorkRoadwayRepairFaceList(workRoadwayRepairFace);
    }

    /**
     * 新增巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    @Override
    public int saveWorkRoadwayRepairFace(List<WorkRoadwayRepairFace> workRoadwayRepairFace) {

        try{
            for (WorkRoadwayRepairFace work:workRoadwayRepairFace) {
                work.setStatus("0");
                WorkRoadwayRepairFace w1=new WorkRoadwayRepairFace();
                w1.setUnit(work.getUnit());
                w1.setWorkLocation(work.getWorkLocation());
                w1.setShipmentMethod(work.getShipmentMethod());
                List<WorkRoadwayRepairFace> workInstallationFaces = workRoadwayRepairFaceMapper.selectWorkRoadwayRepairFaceList(w1);
                if(workInstallationFaces.size()>0){
                    work.setId(workInstallationFaces.get(0).getId());
                    workRoadwayRepairFaceMapper.updateWorkRoadwayRepairFace(work);
                }else {
                    work.setCreateTime(DateUtils.getNowDate());
                    workRoadwayRepairFaceMapper.insertWorkRoadwayRepairFace(work);
                }
            }
        }catch (Exception e){
            return 0;
        }

        return  1;
    }

    /**
     * 修改巷修面信息
     * 
     * @param workRoadwayRepairFace 巷修面信息
     * @return 结果
     */
    @Override
    public int updateWorkRoadwayRepairFace(WorkRoadwayRepairFace workRoadwayRepairFace) {
        return workRoadwayRepairFaceMapper.updateWorkRoadwayRepairFace(workRoadwayRepairFace);
    }

    /**
     * 批量删除巷修面信息
     * 
     * @param ids 需要删除的巷修面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkRoadwayRepairFaceByIds(String[] ids) {
        return workRoadwayRepairFaceMapper.deleteWorkRoadwayRepairFaceByIds(ids);
    }

    /**
     * 删除巷修面信息信息
     * 
     * @param id 巷修面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkRoadwayRepairFaceById(String id) {
        return workRoadwayRepairFaceMapper.deleteWorkRoadwayRepairFaceById(id);
    }
}
