package com.ruoyi.system.domain.BaoBiao.dto.dev;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MddSubItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_nameJSON;
    private String unit_code;
    private Integer productionData;
    private BigDecimal expandData;
    private BigDecimal footageData;
    private Integer enterWellNum;
    private Integer comeOutWellNum;
}
