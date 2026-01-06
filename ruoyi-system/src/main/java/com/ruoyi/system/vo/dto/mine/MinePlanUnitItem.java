package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MinePlanUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_code")
    private String unitCode;
    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("month_plan")
    private BigDecimal monthPlan;
    @JsonProperty("day_plan")
    private BigDecimal dayPlan;
    @JsonProperty("month_target")
    private BigDecimal monthTarget;
    @JsonProperty("day_target")
    private BigDecimal dayTarget;
}
