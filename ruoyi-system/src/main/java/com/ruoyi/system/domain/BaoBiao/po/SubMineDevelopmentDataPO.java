package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SubMineDevelopmentDataPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long mineDevelopmentDataID;
    private String unitNameJSON;  // 原JSON名，现用字符串存
    private String unitCode;
    private Integer productionData;
    private BigDecimal expandData;
    private BigDecimal footageData;
    private Integer enterWellNum;
    private Integer comeOutWellNum;
}
