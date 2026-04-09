package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.WorkLeaderOnDuty;
import com.ruoyi.system.mapper.work.WorkLeaderOnDutyMapper;
import com.ruoyi.system.service.work.IWorkLeaderOnDutyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Slf4j
@Service
public class WorkLeaderOnDutyServiceImpl implements IWorkLeaderOnDutyService {
    @Autowired
    private WorkLeaderOnDutyMapper workLeaderOnDutyMapper;

    /**
     * 查询领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param id 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）主键
     * @return 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     */
    @Override
    public WorkLeaderOnDuty getWorkLeaderOnDutyById(Integer id) {
        return workLeaderOnDutyMapper.selectWorkLeaderOnDutyById(id);
    }

    /**
     * 查询领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）列表
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     */
    @Override
    public List<WorkLeaderOnDuty> listWorkLeaderOnDuty(WorkLeaderOnDuty workLeaderOnDuty) {
        return workLeaderOnDutyMapper.selectWorkLeaderOnDutyList(workLeaderOnDuty);
    }
    @Override
    public List<WorkLeaderOnDuty> selectWorkLeaderOnDutyALLList(WorkLeaderOnDuty workLeaderOnDuty) {
        return workLeaderOnDutyMapper.selectWorkLeaderOnDutyALLList(workLeaderOnDuty);
    }

    /**
     * 新增领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 结果
     */
    @Override
    public int saveWorkLeaderOnDuty(WorkLeaderOnDuty workLeaderOnDuty) {
        WorkLeaderOnDuty onDuty=new WorkLeaderOnDuty();
        onDuty.setUnit(workLeaderOnDuty.getUnit());
        onDuty.setDutyDate(workLeaderOnDuty.getDutyDate());
        onDuty.setUnitCode(workLeaderOnDuty.getUnitCode());
        List<WorkLeaderOnDuty> workLeaderOnDuties = workLeaderOnDutyMapper.selectWorkLeaderOnDutyList(onDuty);

        if(workLeaderOnDuties.size()>0){
            workLeaderOnDuty.setId(workLeaderOnDuties.get(0).getId());
            workLeaderOnDuty.setLeaderShift1(workLeaderOnDuty.getLeaderShift1());
            workLeaderOnDuty.setLeaderShift2(workLeaderOnDuty.getLeaderShift2());
            workLeaderOnDuty.setLeaderShift3(workLeaderOnDuty.getLeaderShift3());
            workLeaderOnDuty.setUpdateTime(DateUtils.getNowDate());
            return workLeaderOnDutyMapper.updateWorkLeaderOnDuty(workLeaderOnDuty);
        }else {
            workLeaderOnDuty.setCreateTime(DateUtils.getNowDate());
            return workLeaderOnDutyMapper.insertWorkLeaderOnDuty(workLeaderOnDuty);
        }

    }

    /**
     * 修改领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 结果
     */
    @Override
    public int updateWorkLeaderOnDuty(WorkLeaderOnDuty workLeaderOnDuty) {
        return workLeaderOnDutyMapper.updateWorkLeaderOnDuty(workLeaderOnDuty);
    }

    /**
     * 批量删除领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param ids 需要删除的领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）主键
     * @return 结果
     */
    @Override
    public int deleteWorkLeaderOnDutyByIds(Integer[] ids) {
        return workLeaderOnDutyMapper.deleteWorkLeaderOnDutyByIds(ids);
    }

    /**
     * 删除领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）信息
     *
     * @param id 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）主键
     * @return 结果
     */
    @Override
    public int deleteWorkLeaderOnDutyById(Integer id) {
        return workLeaderOnDutyMapper.deleteWorkLeaderOnDutyById(id);
    }
}
