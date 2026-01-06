package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.PesDateAggRow;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface PesStatMapper {

    /** 车次合计（total_cars）按日汇总 */
    List<PesDateAggRow> selectDailyCarTotals(@Param("start") LocalDate start,
                                             @Param("end") LocalDate end);

    /** 吨数合计（total_tonnage）按日汇总 */
    List<PesDateAggRow> selectDailyTonnageTotals(@Param("start") LocalDate start,
                                                 @Param("end") LocalDate end);
}
