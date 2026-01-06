package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkCoalWashingReportSub;

import java.util.List;

/**
 * 洗煤数据填报备注Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkCoalWashingReportSubMapper {
    /**
     * 查询洗煤数据填报备注
     *
     * @param id 洗煤数据填报备注主键
     * @return 洗煤数据填报备注
     */
    public WorkCoalWashingReportSub selectWorkCoalWashingReportSubById(Integer id);

    /**
     * 查询洗煤数据填报备注列表
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 洗煤数据填报备注集合
     */
    public List<WorkCoalWashingReportSub> selectWorkCoalWashingReportSubList(WorkCoalWashingReportSub workCoalWashingReportSub);
    /**
     * 查询洗煤数据填报备注列表
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 洗煤数据填报备注集合
     */
    public WorkCoalWashingReportSub selectWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 新增洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    public int insertWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 修改洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    public int updateWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 删除洗煤数据填报备注
     *
     * @param id 洗煤数据填报备注主键
     * @return 结果
     */
    public int deleteWorkCoalWashingReportSubById(Integer id);

    /**
     * 批量删除洗煤数据填报备注
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkCoalWashingReportSubByIds(Integer[] ids);
}

