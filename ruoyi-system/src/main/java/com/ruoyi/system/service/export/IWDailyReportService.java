package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WDailyReport;

import java.util.List;

/**
 * 龙煤股份煤炭营销分公司调度日报一Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWDailyReportService {
    /**
     * 查询龙煤股份煤炭营销分公司调度日报一
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 龙煤股份煤炭营销分公司调度日报一
     */
    public WDailyReport getWDailyReportById(Long id);

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一列表
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 龙煤股份煤炭营销分公司调度日报一集合
     */
    public List<WDailyReport> listWDailyReport(WDailyReport wDailyReport);

    /**
     * 新增龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    public int saveWDailyReport(WDailyReport wDailyReport);

    /**
     * 修改龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    public int updateWDailyReport(WDailyReport wDailyReport);

    /**
     * 批量删除龙煤股份煤炭营销分公司调度日报一
     * 
     * @param ids 需要删除的龙煤股份煤炭营销分公司调度日报一主键集合
     * @return 结果
     */
    public int deleteWDailyReportByIds(Long[] ids);

    /**
     * 删除龙煤股份煤炭营销分公司调度日报一信息
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 结果
     */
    public int deleteWDailyReportById(Long id);
}
