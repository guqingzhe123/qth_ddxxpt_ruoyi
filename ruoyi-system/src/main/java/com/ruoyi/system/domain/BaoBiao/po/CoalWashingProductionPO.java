package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoalWashingProductionPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date productionDate;
    private String unitCode;
    private String unitName;

    private BigDecimal dropIn;
    private BigDecimal washIn;
    private BigDecimal cleanCoal;
    private BigDecimal lumpCoal;
    private BigDecimal slackCoal;
    private BigDecimal sludgeCoal;
    private BigDecimal availableGangue;
    private BigDecimal waste;
    private BigDecimal total;
    private BigDecimal selfUse;
    private BigDecimal carCount;
    private BigDecimal salesVolume;
    private BigDecimal cleanCoalYield;
    private BigDecimal comprehensiveYield;
    private BigDecimal dailyStock;

    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;
    private String mineCategory;

}
