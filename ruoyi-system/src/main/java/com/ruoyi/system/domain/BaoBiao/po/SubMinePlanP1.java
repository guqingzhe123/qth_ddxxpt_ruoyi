package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SubMinePlanP1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal dayPlan;
    private BigDecimal monthPlan;
    private BigDecimal yearPlan;
    private BigDecimal dayTarget;
    private BigDecimal monthTarget;
    private BigDecimal yearTarget;
}
