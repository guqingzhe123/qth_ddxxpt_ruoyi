package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubWashCoalPlanPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long washCoalPlanId;
    private Integer isDeleted;
    private String unitCode;
    private String unitName;

    private java.math.BigDecimal washInMonthPlan;
    private java.math.BigDecimal washInDayPlan;

    private java.math.BigDecimal cleanCoalMonthPlan;
    private java.math.BigDecimal cleanCoalDayPlan;
    private Integer cleanCoalMonthCarPlan;
    private Integer cleanCoalDayCarPlan;

    private java.math.BigDecimal slackCoalMonthPlan;
    private java.math.BigDecimal slackCoalDayPlan;
    private Integer slackCoalMonthCarPlan;
    private Integer slackCoalDayCarPlan;


}
