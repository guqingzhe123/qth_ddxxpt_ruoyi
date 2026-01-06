package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/** 洗煤生产 - 当日汇总 出参对象（单条） */
@Data
public class CwpDailySummaryItemVO {

    @JsonProperty("调入数量")
    private BigDecimal totalDropIn;     // SUM(drop_in)

    @JsonProperty("入洗数量")
    private BigDecimal totalWashIn;     // SUM(wash_in)

    @JsonProperty("精煤数量")
    private BigDecimal totalCleanCoal;  // SUM(clean_coal)

    @JsonProperty("精煤产率百分比")
    private BigDecimal yieldPercent;    // (wash_in / clean_coal) * 100（按你的要求）
}
