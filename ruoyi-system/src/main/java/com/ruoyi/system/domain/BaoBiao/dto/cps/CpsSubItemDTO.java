package com.ruoyi.system.domain.BaoBiao.dto.cps;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class CpsSubItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;     // "yyyy-MM-dd"
    private String record_date;     // "yyyy-MM-dd"
    private BigDecimal clean_coal;
    private BigDecimal slack_coal;
    private BigDecimal lump_coal;
    private BigDecimal raw_coal;

}
