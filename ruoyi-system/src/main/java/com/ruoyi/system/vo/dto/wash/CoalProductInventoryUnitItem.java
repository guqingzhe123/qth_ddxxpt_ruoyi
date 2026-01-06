package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoalProductInventoryUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_code")
    private String unitCode;
    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("clean_coal_prev_stock")
    private BigDecimal cleanCoalPrevStock;
    @JsonProperty("clean_coal_current_stock")
    private BigDecimal cleanCoalCurrentStock;

    @JsonProperty("slack_lump_prev_stock")
    private BigDecimal slackLumpPrevStock;
    @JsonProperty("slack_lump_current_stock")
    private BigDecimal slackLumpCurrentStock;

    @JsonProperty("total_prev_stock")
    private BigDecimal totalPrevStock;
    @JsonProperty("total_current_stock")
    private BigDecimal totalCurrentStock;

    @JsonProperty("daily_plant_self_use")
    private BigDecimal dailyPlantSelfUse;
    @JsonProperty("daily_outside_self_use")
    private BigDecimal dailyOutsideSelfUse;
    @JsonProperty("daily_total_self_use")
    private BigDecimal dailyTotalSelfUse;

    @JsonProperty("monthly_total_self_use")
    private BigDecimal monthlyTotalSelfUse;
    @JsonProperty("yearly_total_self_use")
    private BigDecimal yearlyTotalSelfUse;
}
