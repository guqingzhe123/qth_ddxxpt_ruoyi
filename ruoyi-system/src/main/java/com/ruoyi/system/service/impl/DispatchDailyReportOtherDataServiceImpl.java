package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.DispatchDailyReportOtherData;
import com.ruoyi.system.mapper.DispatchDailyReportOtherDataMapper;
import com.ruoyi.system.service.IDispatchDailyReportOtherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调度日报其他数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class DispatchDailyReportOtherDataServiceImpl implements IDispatchDailyReportOtherDataService {
    @Autowired
    private DispatchDailyReportOtherDataMapper dispatchDailyReportOtherDataMapper;

    /**
     * 查询调度日报其他数据
     * 
     * @param id 调度日报其他数据主键
     * @return 调度日报其他数据
     */
    @Override
    public DispatchDailyReportOtherData getDispatchDailyReportOtherDataById(Long id) {
        return dispatchDailyReportOtherDataMapper.selectDispatchDailyReportOtherDataById(id);
    }

    /**
     * 查询调度日报其他数据列表
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 调度日报其他数据
     */
    @Override
    public List<DispatchDailyReportOtherData> listDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        return dispatchDailyReportOtherDataMapper.selectDispatchDailyReportOtherDataList(dispatchDailyReportOtherData);
    }

    /**
     * 新增调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    @Override
    public int saveDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        DispatchDailyReportOtherData data=new DispatchDailyReportOtherData();
        data.setRecordDate(dispatchDailyReportOtherData.getRecordDate());
        DispatchDailyReportOtherData data1 = dispatchDailyReportOtherDataMapper.selectDispatchDailyReportOtherData(data);
        if(data1 !=null){
            dispatchDailyReportOtherData.setId(data1.getId());
            dispatchDailyReportOtherData.setCreateTime(DateUtils.getNowDate());
            return dispatchDailyReportOtherDataMapper.updateDispatchDailyReportOtherData(dispatchDailyReportOtherData);
        }else {
            dispatchDailyReportOtherData.setCreateTime(DateUtils.getNowDate());
            return dispatchDailyReportOtherDataMapper.insertDispatchDailyReportOtherData(dispatchDailyReportOtherData);
        }
    }

    /**
     * 修改调度日报其他数据
     * 
     * @param dispatchDailyReportOtherData 调度日报其他数据
     * @return 结果
     */
    @Override
    public int updateDispatchDailyReportOtherData(DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        dispatchDailyReportOtherData.setUpdateTime(DateUtils.getNowDate());
        return dispatchDailyReportOtherDataMapper.updateDispatchDailyReportOtherData(dispatchDailyReportOtherData);
    }

    /**
     * 批量删除调度日报其他数据
     * 
     * @param ids 需要删除的调度日报其他数据主键
     * @return 结果
     */
    @Override
    public int deleteDispatchDailyReportOtherDataByIds(Long[] ids) {
        return dispatchDailyReportOtherDataMapper.deleteDispatchDailyReportOtherDataByIds(ids);
    }

    /**
     * 删除调度日报其他数据信息
     * 
     * @param id 调度日报其他数据主键
     * @return 结果
     */
    @Override
    public int deleteDispatchDailyReportOtherDataById(Long id) {
        return dispatchDailyReportOtherDataMapper.deleteDispatchDailyReportOtherDataById(id);
    }
}
