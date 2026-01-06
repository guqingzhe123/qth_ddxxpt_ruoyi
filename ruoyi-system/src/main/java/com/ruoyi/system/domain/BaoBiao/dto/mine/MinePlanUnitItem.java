package com.ruoyi.system.domain.BaoBiao.dto.mine;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class MinePlanUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unit_code;
    private String unit_name;

    private BigDecimal month_plan;
    private BigDecimal day_plan;
    private BigDecimal month_target;
    private BigDecimal day_target;

    private Integer isDeleted;

}
