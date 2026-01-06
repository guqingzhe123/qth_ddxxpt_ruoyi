package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.CoalMineWorkingDay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MineDayCumReportV2Mapper {

    // 1) 一级矿列表
    List<String> selectTopLevelMines(@Param("includeSealed") Boolean includeSealed,
                                     @Param("unitNames") List<String> unitNames);

    // 2) 当日完成（按 data_type）
    MineData sumDailyOutput(@Param("unitName") String unitName,
                                  @Param("recordDate") String recordDate);

    // 3) 累计完成（按 data_type）
    MineData sumCumOutput(@Param("unitName") String unitName,
                            @Param("fromDate") String fromDate,
                            @Param("toDate") String toDate);

    // 4) 日计划合计（按 plan_type）
    CoalMineWorkingDay sumDayPlanForMine(@Param("unitName") String unitName,
                                               @Param("planMonth") String planMonthFirstDay,
                                               @Param("days") int days);
}
