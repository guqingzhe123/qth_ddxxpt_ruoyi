package com.ruoyi.system.domain;

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
 * 龙煤集团各分公司生产外销统计对象 longmei_production_sales_stats
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LongmeiProductionSalesStats extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键，唯一标识每条统计记录 */
    private String id;

    /** 分公司名称（如：鸡西、鹤岗、双鸭山、七台河） */
    @Excel(name = "分公司名称", readConverterExp = "如=：鸡西、鹤岗、双鸭山、七台河")
    private String companyName;

    /** 统计日期（对应“当日”数据的日期，如2025-11-06） */
    @Excel(name = "统计日期", readConverterExp = "对=应“当日”数据的日期，如2025-11-06")
    private Date statsDate;

    /** 当日生产吨数 */
    @Excel(name = "当日生产吨数")
    private BigDecimal dailyProduction;

    /** 月累计生产吨数（截至stats_date所在月） */
    @Excel(name = "月累计生产吨数", readConverterExp = "截=至stats_date所在月")
    private BigDecimal monthlyCumulativeProduction;

    /** 年累计生产吨数（截至stats_date所在年） */
    @Excel(name = "年累计生产吨数", readConverterExp = "截=至stats_date所在年")
    private BigDecimal annualCumulativeProduction;

    /** 当日销售车数 */
    @Excel(name = "当日销售车数")
    private String dailySalesCars;

    /** 月累计销售车数 */
    @Excel(name = "月累计销售车数")
    private String monthlyCumulativeSalesCars;

    /** 年累计销售车数 */
    @Excel(name = "年累计销售车数")
    private String annualCumulativeSalesCars;

    /** 当日销售吨数 */
    @Excel(name = "当日销售吨数")
    private BigDecimal dailySalesTonnage;

    /** 月累计销售吨数 */
    @Excel(name = "月累计销售吨数")
    private BigDecimal monthlyCumulativeSalesTonnage;

    /** 年累计销售吨数 */
    @Excel(name = "年累计销售吨数")
    private BigDecimal annualCumulativeSalesTonnage;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyName", getCompanyName())
            .append("statsDate", getStatsDate())
            .append("dailyProduction", getDailyProduction())
            .append("monthlyCumulativeProduction", getMonthlyCumulativeProduction())
            .append("annualCumulativeProduction", getAnnualCumulativeProduction())
            .append("dailySalesCars", getDailySalesCars())
            .append("monthlyCumulativeSalesCars", getMonthlyCumulativeSalesCars())
            .append("annualCumulativeSalesCars", getAnnualCumulativeSalesCars())
            .append("dailySalesTonnage", getDailySalesTonnage())
            .append("monthlyCumulativeSalesTonnage", getMonthlyCumulativeSalesTonnage())
            .append("annualCumulativeSalesTonnage", getAnnualCumulativeSalesTonnage())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
