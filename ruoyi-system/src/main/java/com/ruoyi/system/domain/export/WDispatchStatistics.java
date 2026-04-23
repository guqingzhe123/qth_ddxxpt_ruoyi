package com.ruoyi.system.domain.export;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 七矿公司外采煤炭日报对象 w_dispatch_statistics
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WDispatchStatistics extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（唯一标识） */
    private String id;

    /** 类型（如：原煤/精煤等） */
    @Excel(name = "类型", readConverterExp = "如=：原煤/精煤等")
    private String dataType;

    /** 调度单位 */
    @Excel(name = "调度单位")
    private String dispatchUnit;

    /** 矿别 */
    @Excel(name = "矿别")
    private String mineName;

    /** 当日车数 */
    @Excel(name = "当日车数")
    private Long dailyCarCount;

    /** 当日净重（吨） */
    @Excel(name = "当日净重", readConverterExp = "吨=")
    private BigDecimal dailyNetWeight;

    /** 当日含税单价 */
    @Excel(name = "当日含税单价")
    private BigDecimal dailyTaxPrice;

    /** 当日金额 */
    @Excel(name = "当日金额")
    private BigDecimal dailyAmount;

    /** 当月车数 */
    @Excel(name = "当月车数")
    private Long monthlyCarCount;

    /** 当月净重（吨） */
    @Excel(name = "当月净重", readConverterExp = "吨=")
    private BigDecimal monthlyNetWeight;

    /** 当月含税单价 */
    @Excel(name = "当月含税单价")
    private BigDecimal monthlyTaxPrice;

    /** 当月金额 */
    @Excel(name = "当月金额")
    private BigDecimal monthlyAmount;

    /** 当年车数 */
    @Excel(name = "当年车数")
    private Long yearlyCarCount;

    /** 当年净重（吨） */
    @Excel(name = "当年净重", readConverterExp = "吨=")
    private BigDecimal yearlyNetWeight;

    /** 当年含税单价 */
    @Excel(name = "当年含税单价")
    private BigDecimal yearlyTaxPrice;

    /** 当年金额 */
    @Excel(name = "当年金额")
    private BigDecimal yearlyAmount;

    /** 统计日期（核心：yyyy-MM-dd） */
    @Excel(name = "统计日期", readConverterExp = "核=心：yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dataType", getDataType())
            .append("dispatchUnit", getDispatchUnit())
            .append("mineName", getMineName())
            .append("dailyCarCount", getDailyCarCount())
            .append("dailyNetWeight", getDailyNetWeight())
            .append("dailyTaxPrice", getDailyTaxPrice())
            .append("dailyAmount", getDailyAmount())
            .append("monthlyCarCount", getMonthlyCarCount())
            .append("monthlyNetWeight", getMonthlyNetWeight())
            .append("monthlyTaxPrice", getMonthlyTaxPrice())
            .append("monthlyAmount", getMonthlyAmount())
            .append("yearlyCarCount", getYearlyCarCount())
            .append("yearlyNetWeight", getYearlyNetWeight())
            .append("yearlyTaxPrice", getYearlyTaxPrice())
            .append("yearlyAmount", getYearlyAmount())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
