package com.ruoyi.system.domain.BaoBiao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class MineDayCumReportV2RequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "查询日期（yyyy-MM-dd）", example = "2025-10-15", required = true)
    private String recordDate;

    @Schema(description = "是否包含已封存采区（默认 false=不包含）")
    private Boolean includeSealed;

    @Schema(description = "可选：仅统计这些矿名（与 mining_area_category.area_name 对应），逗号分隔", example = "一矿,二矿")
    private String unitNamesCsv;
}
