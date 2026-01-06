package com.ruoyi.system.dto;

import java.math.BigDecimal;

/** 只改某个 unit_code 的列值：哪些字段非空就改哪个 */
public class WashCoalPlanUnitPatchDTO {
    private Long id;
    private String unit_code;
    private String unit_name; // 可选改名

    private BigDecimal wash_in_month_plan;
    private BigDecimal wash_in_day_plan;

    private BigDecimal clean_coal_month_plan;
    private BigDecimal clean_coal_day_plan;
    private Integer    clean_coal_month_car_plan;
    private BigDecimal clean_coal_day_car_plan;

    private BigDecimal slack_coal_month_plan;
    private BigDecimal slack_coal_day_plan;
    private Integer    slack_coal_month_car_plan;
    private BigDecimal slack_coal_day_car_plan;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public BigDecimal getWash_in_month_plan() { return wash_in_month_plan; }
    public void setWash_in_month_plan(BigDecimal wash_in_month_plan) { this.wash_in_month_plan = wash_in_month_plan; }

    public BigDecimal getWash_in_day_plan() { return wash_in_day_plan; }
    public void setWash_in_day_plan(BigDecimal wash_in_day_plan) { this.wash_in_day_plan = wash_in_day_plan; }

    public BigDecimal getClean_coal_month_plan() { return clean_coal_month_plan; }
    public void setClean_coal_month_plan(BigDecimal clean_coal_month_plan) { this.clean_coal_month_plan = clean_coal_month_plan; }

    public BigDecimal getClean_coal_day_plan() { return clean_coal_day_plan; }
    public void setClean_coal_day_plan(BigDecimal clean_coal_day_plan) { this.clean_coal_day_plan = clean_coal_day_plan; }

    public Integer getClean_coal_month_car_plan() { return clean_coal_month_car_plan; }
    public void setClean_coal_month_car_plan(Integer clean_coal_month_car_plan) { this.clean_coal_month_car_plan = clean_coal_month_car_plan; }

    public BigDecimal getClean_coal_day_car_plan() { return clean_coal_day_car_plan; }
    public void setClean_coal_day_car_plan(BigDecimal clean_coal_day_car_plan) { this.clean_coal_day_car_plan = clean_coal_day_car_plan; }

    public BigDecimal getSlack_coal_month_plan() { return slack_coal_month_plan; }
    public void setSlack_coal_month_plan(BigDecimal slack_coal_month_plan) { this.slack_coal_month_plan = slack_coal_month_plan; }

    public BigDecimal getSlack_coal_day_plan() { return slack_coal_day_plan; }
    public void setSlack_coal_day_plan(BigDecimal slack_coal_day_plan) { this.slack_coal_day_plan = slack_coal_day_plan; }

    public Integer getSlack_coal_month_car_plan() { return slack_coal_month_car_plan; }
    public void setSlack_coal_month_car_plan(Integer slack_coal_month_car_plan) { this.slack_coal_month_car_plan = slack_coal_month_car_plan; }

    public BigDecimal getSlack_coal_day_car_plan() { return slack_coal_day_car_plan; }
    public void setSlack_coal_day_car_plan(BigDecimal slack_coal_day_car_plan) { this.slack_coal_day_car_plan = slack_coal_day_car_plan; }
}
