package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DispatchDailyReportOtherData;

import java.util.List;

/**
 * 调度日报其他数据Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface DispatchDailyReportOtherDataMapper {
    /**
     * 查询调度日报其他数据
     * 
     * @param id 调度日报其他数据主键
     * @return 调度日报其他数据
     */
    public DispatchDailyReportOtherData selectDispatchDailyReportOtherDataById(Long id);
    public DispatchDailyReportOtherData selectDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 查询调度日报其他数据列表
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 调度日报其他数据集合
     */
    public List<DispatchDailyReportOtherData> selectDispatchDailyReportOtherDataList(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 新增调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    public int insertDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 修改调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    public int updateDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 删除调度日报其他数据
     * 
     * @param id 调度日报其他数据主键
     * @return 结果
     */
    public int deleteDispatchDailyReportOtherDataById(Long id);

    /**
     * 批量删除调度日报其他数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDispatchDailyReportOtherDataByIds(Long[] ids);
}
