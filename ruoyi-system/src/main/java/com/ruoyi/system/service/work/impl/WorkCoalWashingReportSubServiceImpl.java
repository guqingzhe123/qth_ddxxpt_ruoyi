package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkCoalWashingReportSub;
import com.ruoyi.system.mapper.work.WorkCoalWashingReportSubMapper;
import com.ruoyi.system.service.work.IWorkCoalWashingReportSubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 洗煤数据填报备注Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkCoalWashingReportSubServiceImpl implements IWorkCoalWashingReportSubService {
    @Autowired
    private WorkCoalWashingReportSubMapper workCoalWashingReportSubMapper;

    /**
     * 查询洗煤数据填报备注
     *
     * @param id 洗煤数据填报备注主键
     * @return 洗煤数据填报备注
     */
    @Override
    public WorkCoalWashingReportSub getWorkCoalWashingReportSubById(Integer id) {
        return workCoalWashingReportSubMapper.selectWorkCoalWashingReportSubById(id);
    }

    /**
     * 查询洗煤数据填报备注列表
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 洗煤数据填报备注
     */
    @Override
    public List<WorkCoalWashingReportSub> listWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub) {
        return workCoalWashingReportSubMapper.selectWorkCoalWashingReportSubList(workCoalWashingReportSub);
    }
    /**
     * 查询洗煤数据填报备注列表
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 洗煤数据填报备注
     */
    @Override
    public WorkCoalWashingReportSub WorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub) {
        return workCoalWashingReportSubMapper.selectWorkCoalWashingReportSub(workCoalWashingReportSub);
    }

    /**
     * 新增洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    @Override
    public int saveWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub) {
        workCoalWashingReportSub.setReportTime(DateUtils.getNowDate());
        return workCoalWashingReportSubMapper.insertWorkCoalWashingReportSub(workCoalWashingReportSub);
    }

    /**
     * 修改洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    @Override
    public int updateWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub) {
        return workCoalWashingReportSubMapper.updateWorkCoalWashingReportSub(workCoalWashingReportSub);
    }

    /**
     * 批量删除洗煤数据填报备注
     *
     * @param ids 需要删除的洗煤数据填报备注主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalWashingReportSubByIds(Integer[] ids) {
        return workCoalWashingReportSubMapper.deleteWorkCoalWashingReportSubByIds(ids);
    }

    /**
     * 删除洗煤数据填报备注信息
     *
     * @param id 洗煤数据填报备注主键
     * @return 结果
     */
    @Override
    public int deleteWorkCoalWashingReportSubById(Integer id) {
        return workCoalWashingReportSubMapper.deleteWorkCoalWashingReportSubById(id);
    }
}

