package com.ruoyi.system.domain.BaoBiao.dto.wash;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WashCoalPlanUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_code;
    private String unit_name;
    private Integer is_deleted;

    private BigDecimal wash_in_month_plan;//入洗-月计划数
    private BigDecimal wash_in_day_plan;//入洗-日计划数

    private BigDecimal clean_coal_month_plan;//精煤量-月计划数
    private BigDecimal clean_coal_day_plan;//精煤量-日计划数
    private Integer   clean_coal_month_car_plan;//精煤量-月计划车数
    private Integer   clean_coal_day_car_plan;//精煤量-日计划车数

    private BigDecimal slack_coal_month_plan;//末(块)煤-月计划数
    private BigDecimal slack_coal_day_plan;//末(块)煤-日计划数
    private Integer   slack_coal_month_car_plan;//末(块)煤-月计划车数
    private Integer   slack_coal_day_car_plan;//末(块)煤-日计划车数

}
