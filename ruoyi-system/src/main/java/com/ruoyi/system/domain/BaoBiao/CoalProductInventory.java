package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoalProductInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unitCode;
    private String unitName;

    private BigDecimal cleanCoalPrevStock;
    private BigDecimal cleanCoalCurrentStock;

    private BigDecimal slackLumpPrevStock;
    private BigDecimal slackLumpCurrentStock;

    private BigDecimal totalPrevStock;
    private BigDecimal totalCurrentStock;

    private BigDecimal dailyPlantSelfUse;
    private BigDecimal dailyOutsideSelfUse;
    private BigDecimal dailyTotalSelfUse;

    private BigDecimal monthlyTotalSelfUse;
    private BigDecimal yearlyTotalSelfUse;

    private Date recordDate;

    private Date createTime;
    private Date updateTime;
    private String userid;
}
