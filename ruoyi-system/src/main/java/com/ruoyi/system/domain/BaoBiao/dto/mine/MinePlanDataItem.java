package com.ruoyi.system.domain.BaoBiao.dto.mine;

import java.io.Serializable;
import java.math.BigDecimal;

public class MinePlanDataItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_name;           // 对应 unit_nameJSON 的每个元素
    private BigDecimal month_plan;
    private BigDecimal day_plan;
    private BigDecimal month_target;
    private BigDecimal day_target;

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public BigDecimal getMonth_plan() { return month_plan; }
    public void setMonth_plan(BigDecimal month_plan) { this.month_plan = month_plan; }

    public BigDecimal getDay_plan() { return day_plan; }
    public void setDay_plan(BigDecimal day_plan) { this.day_plan = day_plan; }

    public BigDecimal getMonth_target() { return month_target; }
    public void setMonth_target(BigDecimal month_target) { this.month_target = month_target; }

    public BigDecimal getDay_target() { return day_target; }
    public void setDay_target(BigDecimal day_target) { this.day_target = day_target; }
}
