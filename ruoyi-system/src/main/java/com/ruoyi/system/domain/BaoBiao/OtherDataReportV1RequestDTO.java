package com.ruoyi.system.domain.BaoBiao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class OtherDataReportV1RequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "查询日期（yyyy-MM-dd）", example = "2025-10-15", required = true)
    private String recordDate;
}
