package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.DateTotalRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MddStatMapper {

    /** 全公司聚合（不筛 is_separate） */
    MineData sumOutputByDateAndType(@Param("date") LocalDate date);

    /** 分/七煤聚合（按 is_separate=0/1） */
    MineData sumOutputByDateTypeSeparate(@Param("date") LocalDate date,
                                           @Param("isSeparate") Integer isSeparate);

    /** 七日序列（分/七煤） */
    List<DateTotalRow> sevenDaySeries(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      @Param("isSeparate") Integer isSeparate);
    /** 近七日生产数据 */
    List<MineData> sevenDaySeriesProductionAndPlanning(@Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate);
    /** 近七日生产计划数据 */
    DateTotalRow sevendayPlanData(@Param("Date") LocalDate startDate,
                                  @Param("dataType") String dataType);

    /** 计划数据 */
    BigDecimal PlanData(@Param("Date") LocalDate date,
                        @Param("dataType") String dataType);
    /** 目标数据 */
    BigDecimal targetData(@Param("Date") LocalDate date,
                        @Param("dataType") String dataType);


}
