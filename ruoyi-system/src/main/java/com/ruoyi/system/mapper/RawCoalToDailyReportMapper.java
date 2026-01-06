package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RawCoalToDailyReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 原煤去向月报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface RawCoalToDailyReportMapper {
    /**
     * 查询原煤去向月报
     * 
     * @param id 原煤去向月报主键
     * @return 原煤去向月报
     */
    public RawCoalToDailyReport selectRawCoalToDailyReportById(Long id);

    /**
     * 查询原煤去向月报列表
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 原煤去向月报集合
     */
    public List<RawCoalToDailyReport> selectRawCoalToDailyReportList(RawCoalToDailyReport rawCoalToDailyReport);

    /**
     * 新增原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    public int insertRawCoalToDailyReport(List<RawCoalToDailyReport> rawCoalToDailyReport);

    /**
     * 修改原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    public int updateRawCoalToDailyReport(RawCoalToDailyReport rawCoalToDailyReport);

    /**
     * 删除原煤去向月报
     * 
     * @param id 原煤去向月报主键
     * @return 结果
     */
    public int deleteRawCoalToDailyReportById(Long id);

    /**
     * 批量删除原煤去向月报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRawCoalToDailyReportByIds(Long[] ids);


    /**
     * 查询原煤去向月报总数
     *
     * @param statsDate 原煤去向月报 统计月份
     * @return 原煤去向月报
     */
    public RawCoalToDailyReport selectRawAll(@Param("statsDate") String statsDate,@Param("unitName") String unitName);
}
