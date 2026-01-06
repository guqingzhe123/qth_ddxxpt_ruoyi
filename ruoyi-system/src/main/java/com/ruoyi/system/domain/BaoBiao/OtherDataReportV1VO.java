package com.ruoyi.system.domain.BaoBiao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OtherDataReportV1VO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 精煤产量
    @Schema(description = "当日精煤产量")
    @JsonProperty("精煤产量当日")
    private BigDecimal dailyCleanCoalOutput;

    @Schema(description = "精煤产量累计（当月1号~当日）")
    @JsonProperty("精煤产量累计")
    private BigDecimal cumCleanCoalOutput;

    // 商品产量
    @JsonProperty("商品产量当日")
    private BigDecimal dailyProductSales;

    @JsonProperty("商品产量累计")
    private BigDecimal cumProductSales;

    // 外运车
    @JsonProperty("外运车当日")
    private BigDecimal dailyOutboundCars;

    @JsonProperty("外运车累计")
    private BigDecimal cumOutboundCars;

    // 外运量
    @JsonProperty("外运量当日")
    private BigDecimal dailyOutboundVolume;

    @JsonProperty("外运量累计")
    private BigDecimal cumOutboundVolume;

    // 销量合计（仅当日）
    @JsonProperty("当日销量合计")
    private BigDecimal dailySalesTotal;

    // 当体销售量（仅当日）
    @JsonProperty("当体销售量小计")
    private BigDecimal subtotal;

    @JsonProperty("当体销售量原煤")
    private BigDecimal rawCoal;

    @JsonProperty("当体销售量精煤")
    private BigDecimal cleanCoal;

    @JsonProperty("当体销售量其他")
    private BigDecimal other;

    // 地销
    @JsonProperty("当日地销")
    private BigDecimal dailyLocalSales;

    @JsonProperty("累计销售地销")
    private BigDecimal cumLocalSales;

    // 精煤累计销量（按 clean_coal 累计）
    @JsonProperty("累计销售精煤")
    private BigDecimal cumCleanCoal;
}
