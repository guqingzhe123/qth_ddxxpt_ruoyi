package com.ruoyi.system.domain.BaoBiao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MineDayCumReportV2VO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "矿名（来自 mining_area_category.level=1 的 area_name）")
    private String unitName;

    @Schema(description = "生产当日完成（data_type=生产，当日）")
    private BigDecimal productionDaily;

    @Schema(description = "生产累计划（生产：day_plan×日至今）")
    private BigDecimal productionPlanCum;

    @Schema(description = "生产累完成（data_type=生产，月初至今）")
    private BigDecimal productionDoneCum;

    @Schema(description = "进尺当日完成（data_type=进尺，当日）")
    private BigDecimal advanceDaily;

    @Schema(description = "进尺累计划（进尺：day_plan×日至今）")
    private BigDecimal advancePlanCum;

    @Schema(description = "进尺累完成（data_type=进尺，月初至今）")
    private BigDecimal advanceDoneCum;

    @Schema(description = "开拓当日完成（data_type=开拓，当日）")
    private BigDecimal developDaily;

    @Schema(description = "开拓累计划（开拓：day_plan×日至今）")
    private BigDecimal developPlanCum;

    @Schema(description = "开拓累完成（data_type=开拓，月初至今）")
    private BigDecimal developDoneCum;
}
