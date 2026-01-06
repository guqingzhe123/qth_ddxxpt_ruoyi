package com.ruoyi.system.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Mapper 返回的按日汇总行（内部使用） */
@Data
public class PesDateAggRow {
    private LocalDate day;   // export_date
    private BigDecimal total;
}
