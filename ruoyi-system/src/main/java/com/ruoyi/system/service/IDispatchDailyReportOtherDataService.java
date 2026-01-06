package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DispatchDailyReportOtherData;

/**
 * 调度日报其他数据Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IDispatchDailyReportOtherDataService {
    /**
     * 查询调度日报其他数据
     * 
     * @param id 调度日报其他数据主键
     * @return 调度日报其他数据
     */
    public DispatchDailyReportOtherData getDispatchDailyReportOtherDataById(Long id);

    /**
     * 查询调度日报其他数据列表
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 调度日报其他数据集合
     */
    public List<DispatchDailyReportOtherData> listDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 新增调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    public int saveDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 修改调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    public int updateDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData);

    /**
     * 批量删除调度日报其他数据
     * 
     * @param ids 需要删除的调度日报其他数据主键集合
     * @return 结果
     */
    public int deleteDispatchDailyReportOtherDataByIds(Long[] ids);

    /**
     * 删除调度日报其他数据信息
     * 
     * @param id 调度日报其他数据主键
     * @return 结果
     */
    public int deleteDispatchDailyReportOtherDataById(Long id);
}
