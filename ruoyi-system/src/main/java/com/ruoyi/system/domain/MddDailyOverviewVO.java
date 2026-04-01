package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MddDailyOverviewVO {

    @JsonProperty("全公司生产")
    private Integer companyProduction;

    @JsonProperty("全公司生产计划")
    private Integer branchProduction;

    @JsonProperty("全公司生产目标")
    private Integer qimeiProduction;

    @JsonProperty("全公司进尺")
    private BigDecimal companyAdvance;

    @JsonProperty("全公司进尺计划")
    private Integer branchAdvance;

    @JsonProperty("全公司进尺目标")
    private Integer qimeiAdvance;

    @JsonProperty("全公司开拓")
    private BigDecimal companyTunneling;

    @JsonProperty("全公司开拓计划")
    private Integer branchTunneling;

    @JsonProperty("全公司开拓目标")
    private Integer qimeiTunneling;
}
