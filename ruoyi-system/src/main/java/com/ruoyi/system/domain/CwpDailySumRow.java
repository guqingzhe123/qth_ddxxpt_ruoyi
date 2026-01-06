package com.ruoyi.system.domain;

import lombok.Data;

import java.math.BigDecimal;

/** Mapper SQL 汇总行（内部使用） */
@Data
public class CwpDailySumRow {
    private BigDecimal sumDropIn;
    private BigDecimal sumWashIn;
    private BigDecimal sumCleanCoal;
}
