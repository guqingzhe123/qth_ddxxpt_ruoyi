package com.ruoyi.system.domain.BaoBiao.dto.dev;

import lombok.Data;

import java.io.Serializable;

@Data
public class MddSubItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_nameJSON;
    private String unit_code;
    private Integer productionData;
    private Integer expandData;
    private Integer footageData;
    private Integer enterWellNum;
    private Integer comeOutWellNum;
}
