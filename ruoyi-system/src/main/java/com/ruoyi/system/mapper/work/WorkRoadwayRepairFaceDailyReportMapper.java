package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.WorkRoadwayRepairFaceDailyReport;

import java.util.List;

/**
 * 巷面配置日报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
public interface WorkRoadwayRepairFaceDailyReportMapper {
    /**
     * 查询巷面配置日报
     * 
     * @param id 巷面配置日报主键
     * @return 巷面配置日报
     */
    public WorkRoadwayRepairFaceDailyReport selectWorkRoadwayRepairFaceDailyReportById(String id);

    /**
     * 查询巷面配置日报列表
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 巷面配置日报集合
     */
    public List<WorkRoadwayRepairFaceDailyReport> selectWorkRoadwayRepairFaceDailyReportList(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport);

    /**
     * 新增巷面配置日报
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 结果
     */
    public int insertWorkRoadwayRepairFaceDailyReport(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport);

    /**
     * 修改巷面配置日报
     * 
     * @param workRoadwayRepairFaceDailyReport 巷面配置日报
     * @return 结果
     */
    public int updateWorkRoadwayRepairFaceDailyReport(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport);

    /**
     * 删除巷面配置日报
     * 
     * @param id 巷面配置日报主键
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceDailyReportById(String id);

    /**
     * 批量删除巷面配置日报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkRoadwayRepairFaceDailyReportByIds(String[] ids);
}
