package com.ruoyi.system.mapper.export;

import java.util.List;
import com.ruoyi.system.domain.export.WDailyReport;

/**
 * 龙煤股份煤炭营销分公司调度日报一Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WDailyReportMapper {
    /**
     * 查询龙煤股份煤炭营销分公司调度日报一
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 龙煤股份煤炭营销分公司调度日报一
     */
    public WDailyReport selectWDailyReportById(Long id);

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一列表
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 龙煤股份煤炭营销分公司调度日报一集合
     */
    public List<WDailyReport> selectWDailyReportList(WDailyReport wDailyReport);

    /**
     * 新增龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    public int insertWDailyReport(WDailyReport wDailyReport);

    /**
     * 修改龙煤股份煤炭营销分公司调度日报一
     * 
     * @param wDailyReport 龙煤股份煤炭营销分公司调度日报一
     * @return 结果
     */
    public int updateWDailyReport(WDailyReport wDailyReport);

    /**
     * 删除龙煤股份煤炭营销分公司调度日报一
     * 
     * @param id 龙煤股份煤炭营销分公司调度日报一主键
     * @return 结果
     */
    public int deleteWDailyReportById(Long id);

    /**
     * 批量删除龙煤股份煤炭营销分公司调度日报一
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWDailyReportByIds(Long[] ids);
}
