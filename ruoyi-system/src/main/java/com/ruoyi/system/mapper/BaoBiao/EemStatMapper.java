package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.EemDateAggRow;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EemStatMapper {

    /** 入井人数（total_down_count）按日汇总 */
    List<EemDateAggRow> selectDailyDownTotals(@Param("startTs") LocalDateTime startTs,
                                              @Param("endExclusiveTs") LocalDateTime endExclusiveTs);

    /** 升井人数（total_up_count）按日汇总 */
    List<EemDateAggRow> selectDailyUpTotals(@Param("startTs") LocalDateTime startTs,
                                            @Param("endExclusiveTs") LocalDateTime endExclusiveTs);
}
