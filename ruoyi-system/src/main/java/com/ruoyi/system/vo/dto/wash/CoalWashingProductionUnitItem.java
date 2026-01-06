package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoalWashingProductionUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_code")
    private String unitCode;
    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("drop_in")
    private BigDecimal dropIn;
    @JsonProperty("wash_in")
    private BigDecimal washIn;
    @JsonProperty("clean_coal")
    private BigDecimal cleanCoal;
    @JsonProperty("lump_coal")
    private BigDecimal lumpCoal;
    @JsonProperty("slack_coal")
    private BigDecimal slackCoal;
    @JsonProperty("sludge_coal")
    private BigDecimal sludgeCoal;
    @JsonProperty("available_gangue")
    private BigDecimal availableGangue;
    @JsonProperty("waste")
    private BigDecimal waste;
    @JsonProperty("total")
    private BigDecimal total;
    @JsonProperty("self_use")
    private BigDecimal selfUse;
    @JsonProperty("car_count")
    private Integer carCount;
    @JsonProperty("sales_volume")
    private BigDecimal salesVolume;
    @JsonProperty("clean_coal_yield")
    private BigDecimal cleanCoalYield;        // %
    @JsonProperty("comprehensive_yield")
    private BigDecimal comprehensiveYield;    // %
    @JsonProperty("daily_stock")
    private BigDecimal dailyStock;
}
