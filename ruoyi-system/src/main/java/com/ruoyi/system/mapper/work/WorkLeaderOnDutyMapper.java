package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkLeaderOnDuty;

import java.util.List;

/**
 * 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkLeaderOnDutyMapper {
    /**
     * 查询领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param id 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）主键
     * @return 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     */
    public WorkLeaderOnDuty selectWorkLeaderOnDutyById(Integer id);

    /**
     * 查询领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）列表
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）集合
     */
    public List<WorkLeaderOnDuty> selectWorkLeaderOnDutyList(WorkLeaderOnDuty workLeaderOnDuty);
    public List<WorkLeaderOnDuty> selectWorkLeaderOnDutyALLList(WorkLeaderOnDuty workLeaderOnDuty);

    /**
     * 新增领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 结果
     */
    public int insertWorkLeaderOnDuty(WorkLeaderOnDuty workLeaderOnDuty);

    /**
     * 修改领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param workLeaderOnDuty 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     * @return 结果
     */
    public int updateWorkLeaderOnDuty(WorkLeaderOnDuty workLeaderOnDuty);

    /**
     * 删除领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param id 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）主键
     * @return 结果
     */
    public int deleteWorkLeaderOnDutyById(Integer id);

    /**
     * 批量删除领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkLeaderOnDutyByIds(Integer[] ids);
}

