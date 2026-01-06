package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubMineDevelopmentDataPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long mineDevelopmentDataID;
    private String unitNameJSON;  // 原JSON名，现用字符串存
    private String unitCode;
    private Integer productionData;
    private Integer expandData;
    private Integer footageData;
    private Integer enterWellNum;
    private Integer comeOutWellNum;
}
