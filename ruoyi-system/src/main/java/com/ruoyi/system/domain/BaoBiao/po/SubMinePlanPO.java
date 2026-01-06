package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class SubMinePlanPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long minePlanId;
    private BigDecimal monthPlan;
    private BigDecimal dayPlan;
    private BigDecimal monthTarget;
    private BigDecimal dayTarget;
    private String unitName;
    private String unitCode;
    private Integer isDeleted;
}
