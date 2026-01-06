package com.ruoyi.system.service.work.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkProductionFace;
import com.ruoyi.system.mapper.work.WorkProductionFaceMapper;
import com.ruoyi.system.service.work.IWorkProductionFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产面信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkProductionFaceServiceImpl implements IWorkProductionFaceService {

    @Autowired
    private WorkProductionFaceMapper workProductionFaceMapper;

    @Override
    public AjaxResult getProductionFaceListByUnit(String json) {
        JSONObject entries = JSONUtil.parseObj(json);
        String unit = entries.getStr("unit");
        if (StrUtil.isBlank(unit)) {
            return AjaxResult.warn("请选择单位");
        }
        WorkProductionFace workProductionFace = new WorkProductionFace();
        if (StrUtil.isNotBlank(unit)) {
            workProductionFace.setUnit(unit);
        }
        List<WorkProductionFace> list = workProductionFaceMapper.selectWorkProductionFaceList(workProductionFace);
        return AjaxResult.success(list);
    }

    /**
     * 查询生产面信息
     *
     * @param id 生产面信息主键
     * @return 生产面信息
     */
    @Override
    public WorkProductionFace getWorkProductionFaceById(String id) {
        return workProductionFaceMapper.selectWorkProductionFaceById(id);
    }

    /**
     * 查询生产面信息列表
     *
     * @param workProductionFace 生产面信息
     * @return 生产面信息
     */
    @Override
    public List<WorkProductionFace> listWorkProductionFace(WorkProductionFace workProductionFace) {
        return workProductionFaceMapper.selectWorkProductionFaceList(workProductionFace);
    }

    /**
     * 新增生产面信息
     *
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    @Override
    public int saveWorkProductionFace(List<WorkProductionFace> workProductionFace) {
        try {
            for (WorkProductionFace work : workProductionFace) {
                work.setStatus("0");
                WorkProductionFace w1 = new WorkProductionFace();
                w1.setUnit(work.getUnit());
                w1.setTeamId(work.getTeamId());
                List<WorkProductionFace> workInstallationFaces = workProductionFaceMapper.selectWorkProductionFaceList(w1);
                if (workInstallationFaces.size() > 0) {
                    work.setId(workInstallationFaces.get(0).getId());
                    workProductionFaceMapper.updateWorkProductionFace(work);
                } else {
                    work.setStatus("0");
                    work.setCreateTime(DateUtils.getNowDate());
                    workProductionFaceMapper.insertWorkProductionFace(work);
                }
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
//
//        workProductionFace.setCreateTime(DateUtils.getNowDate());
//        return workProductionFaceMapper.insertWorkProductionFace(workProductionFace);
    }

    /**
     * 修改生产面信息
     *
     * @param workProductionFace 生产面信息
     * @return 结果
     */
    @Override
    public int updateWorkProductionFace(WorkProductionFace workProductionFace) {
        return workProductionFaceMapper.updateWorkProductionFace(workProductionFace);
    }

    /**
     * 批量删除生产面信息
     *
     * @param ids 需要删除的生产面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionFaceByIds(String[] ids) {
        return workProductionFaceMapper.deleteWorkProductionFaceByIds(ids);
    }

    /**
     * 删除生产面信息信息
     *
     * @param id 生产面信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkProductionFaceById(String id) {
        return workProductionFaceMapper.deleteWorkProductionFaceById(id);
    }
}
