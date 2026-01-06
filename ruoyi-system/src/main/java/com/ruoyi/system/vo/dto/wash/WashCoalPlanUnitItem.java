package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * data_JSON 的每个元素（入参 & 出参一致）
 */
@Data
public class WashCoalPlanUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_code")
    private String unitCode;

    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("wash_in_month_plan")
    private BigDecimal washInMonthPlan;

    @JsonProperty("wash_in_day_plan")
    private BigDecimal washInDayPlan;

    @JsonProperty("clean_coal_month_plan")
    private BigDecimal cleanCoalMonthPlan;

    @JsonProperty("clean_coal_day_plan")
    private BigDecimal cleanCoalDayPlan;

    @JsonProperty("clean_coal_month_car_plan")
    private Integer cleanCoalMonthCarPlan;

    @JsonProperty("clean_coal_day_car_plan")
    private BigDecimal cleanCoalDayCarPlan;

    @JsonProperty("slack_coal_month_plan")
    private BigDecimal slackCoalMonthPlan;

    @JsonProperty("slack_coal_day_plan")
    private BigDecimal slackCoalDayPlan;

    @JsonProperty("slack_coal_month_car_plan")
    private Integer slackCoalMonthCarPlan;

    @JsonProperty("slack_coal_day_car_plan")
    private BigDecimal slackCoalDayCarPlan;
}
