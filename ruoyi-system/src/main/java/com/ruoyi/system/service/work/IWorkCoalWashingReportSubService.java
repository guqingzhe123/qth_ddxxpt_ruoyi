package com.ruoyi.system.service.work;


import com.ruoyi.system.domain.work.WorkCoalWashingReportSub;

import java.util.List;

/**
 * 洗煤数据填报备注Service接口
 *
 * @author ruoyi
 * @date 2025-12-04
 */
public interface IWorkCoalWashingReportSubService {
    /**
     * 查询洗煤数据填报备注
     *
     * @param id 洗煤数据填报备注主键
     * @return 洗煤数据填报备注
     */
    public WorkCoalWashingReportSub getWorkCoalWashingReportSubById(Integer id);

    /**
     * 查询洗煤数据填报备注列表
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 洗煤数据填报备注集合
     */
    public List<WorkCoalWashingReportSub> listWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    public WorkCoalWashingReportSub WorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 新增洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    public int saveWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 修改洗煤数据填报备注
     *
     * @param workCoalWashingReportSub 洗煤数据填报备注
     * @return 结果
     */
    public int updateWorkCoalWashingReportSub(WorkCoalWashingReportSub workCoalWashingReportSub);

    /**
     * 批量删除洗煤数据填报备注
     *
     * @param ids 需要删除的洗煤数据填报备注主键集合
     * @return 结果
     */
    public int deleteWorkCoalWashingReportSubByIds(Integer[] ids);

    /**
     * 删除洗煤数据填报备注信息
     *
     * @param id 洗煤数据填报备注主键
     * @return 结果
     */
    public int deleteWorkCoalWashingReportSubById(Integer id);
}

