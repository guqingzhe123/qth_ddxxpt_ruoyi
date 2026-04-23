package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WDailyReport;
import com.ruoyi.system.mapper.export.WDailyReportMapper;
import com.ruoyi.system.service.export.IWDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 龙煤股份煤炭营销分公司调度日报一Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WDailyReportServiceImpl implements IWDailyReportService {
    @Autowired
    private WDailyReportMapper wDailyReportMapper;

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 龙煤股份煤炭营销分公司调度日报一
     */
    @Override
    public WDailyReport getWDailyReportById(Long id) {
        return wDailyReportMapper.selectWDailyReportById(id);
    }

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一列表
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 龙煤股份煤炭营销分公司调度日报一
     */
    @Override
    public List<WDailyReport> listWDailyReport(WDailyReport wDailyReport) {
        return wDailyReportMapper.selectWDailyReportList(wDailyReport);
    }

    /**
     * 新增龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    @Override
    public int saveWDailyReport(WDailyReport wDailyReport) {
        wDailyReport.setCreateTime(DateUtils.getNowDate());
        return wDailyReportMapper.insertWDailyReport(wDailyReport);
    }

    /**
     * 修改龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    @Override
    public int updateWDailyReport(WDailyReport wDailyReport) {
        wDailyReport.setUpdateTime(DateUtils.getNowDate());
        return wDailyReportMapper.updateWDailyReport(wDailyReport);
    }

    /**
     * 批量删除龙煤股份煤炭营销分公司调度日报一
     * 
     * @param ids 需要删除的龙煤股份煤炭营销分公司调度日报一主键
     * @return 结果
     */
    @Override
    public int deleteWDailyReportByIds(Long[] ids) {
        return wDailyReportMapper.deleteWDailyReportByIds(ids);
    }

    /**
     * 删除龙煤股份煤炭营销分公司调度日报一信息
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 结果
     */
    @Override
    public int deleteWDailyReportById(Long id) {
        return wDailyReportMapper.deleteWDailyReportById(id);
    }
}
