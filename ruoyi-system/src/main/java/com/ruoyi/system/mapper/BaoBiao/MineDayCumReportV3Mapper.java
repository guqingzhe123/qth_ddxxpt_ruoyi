package com.ruoyi.system.mapper.BaoBiao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface MineDayCumReportV3Mapper {

    /** 取 level=1 的矿名（area_name），默认过滤 is_sealed=0，可按传入名单过滤 */
    List<String> selectTopLevelMines(@Param("includeSealed") Boolean includeSealed,
                                     @Param("unitNames") List<String> unitNames);

    /** 当日完成（某 data_type 的当日 output 之和） */
    BigDecimal sumDailyOutput(@Param("unitName") String unitName,
                              @Param("recordDate") String recordDate,
                              @Param("dataType") String dataType);

    /** 累完成（某 data_type 的月初至当日 output 之和） */
    BigDecimal sumCumOutput(@Param("unitName") String unitName,
                            @Param("fromDate") String fromDate,
                            @Param("toDate") String toDate,
                            @Param("dataType") String dataType);

    /** 日计划合计（按 plan_type & plan_month=当月1号 & unit_name） */
    BigDecimal sumDayPlanForMine(@Param("unitName") String unitName,
                                 @Param("planMonth") String planMonthFirstDay,
                                 @Param("planType") String planType);
}
