package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DispatchDailyReportOtherDataV1PO {
    private Long id;
    private Date recordDate;

    private BigDecimal dailyCleanCoalOutput;
    private BigDecimal dailyProductSales;
    private BigDecimal dailyOutboundCars;
    private BigDecimal dailyOutboundVolume;
    private BigDecimal dailySalesTotal;

    private BigDecimal subtotal;
    private BigDecimal rawCoal;
    private BigDecimal cleanCoal;
    private BigDecimal other;

    private BigDecimal dailyLocalSales;

    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;
}
