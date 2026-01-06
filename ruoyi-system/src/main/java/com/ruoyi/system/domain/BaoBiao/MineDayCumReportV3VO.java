package com.ruoyi.system.domain.BaoBiao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MineDayCumReportV3VO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "矿名（mining_area_category.level=1 的 area_name）")
    @JsonProperty("unit_name")
    private String unitName;

    // 生产
    @JsonProperty("原煤当日计划")
    private BigDecimal productionDaily;

    @JsonProperty("原煤当日实际")
    private BigDecimal productionActual; // 与当日完成一致

    @JsonProperty("原煤月计划")
    private BigDecimal productionPlanCum;

    @JsonProperty("原煤月实际")
    private BigDecimal productionDoneCum;

    // 进尺
    @JsonProperty("进尺当日计划")
    private BigDecimal advanceDaily;

    @JsonProperty("进尺当日实际")
    private BigDecimal advanceActual; // 与当日完成一致

    @JsonProperty("进尺月计划")
    private BigDecimal advancePlanCum;

    @JsonProperty("进尺月实际")
    private BigDecimal advanceDoneCum;

    // 开拓
    @JsonProperty("开拓当日计划")
    private BigDecimal developDaily;

    @JsonProperty("开拓当日实际")
    private BigDecimal developActual; // 与当日完成一致

    @JsonProperty("开拓月计划")
    private BigDecimal developPlanCum;

    @JsonProperty("开拓月累计")
    private BigDecimal developDoneCum;
}
