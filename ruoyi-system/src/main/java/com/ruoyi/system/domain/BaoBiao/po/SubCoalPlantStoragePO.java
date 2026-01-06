package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class SubCoalPlantStoragePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long coalPlantStorageID;
    private String recordDate;              // yyyy-MM-dd
    private BigDecimal cleanCoal;
    private BigDecimal slackCoal;
    private BigDecimal lumpCoal;
    private BigDecimal rawCoal;
}
