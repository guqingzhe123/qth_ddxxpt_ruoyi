package com.ruoyi.system.domain.export;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 4月份外销品种煤日报对象 w_loading_statistics
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WLoadingStatistics extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 煤种 */
    @Excel(name = "煤种")
    private String coalType;

    /** 销售方式 */
    @Excel(name = "销售方式")
    private String salesMode;

    /** 运输方式 */
    @Excel(name = "运输方式")
    private String transportMode;

    /** 厂矿名称 */
    @Excel(name = "厂矿名称")
    private String factoryName;

    /** 级别 */
    @Excel(name = "级别")
    private String coalLevel;

    /** 当日承认车(辆) */
    @Excel(name = "当日承认车(辆)")
    private Long dailyApprovedCars;

    /** 当日实装车(辆) */
    @Excel(name = "当日实装车(辆)")
    private Long dailyLoadedCars;

    /** 当日实装吨(吨) */
    @Excel(name = "当日实装吨(吨)")
    private BigDecimal dailyLoadedWeight;

    /** 当月承认车(辆) */
    @Excel(name = "当月承认车(辆)")
        private Long monthlyApprovedCars;

    /** 当月实装车(辆) */
    @Excel(name = "当月实装车(辆)")
    private Long monthlyLoadedCars;

    /** 当月实装吨(吨) */
    @Excel(name = "当月实装吨(吨)")
    private BigDecimal monthlyLoadedWeight;

    /** 统计日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("coalType", getCoalType())
            .append("salesMode", getSalesMode())
            .append("transportMode", getTransportMode())
            .append("factoryName", getFactoryName())
            .append("coalLevel", getCoalLevel())
            .append("dailyApprovedCars", getDailyApprovedCars())
            .append("dailyLoadedCars", getDailyLoadedCars())
            .append("dailyLoadedWeight", getDailyLoadedWeight())
            .append("monthlyApprovedCars", getMonthlyApprovedCars())
            .append("monthlyLoadedCars", getMonthlyLoadedCars())
            .append("monthlyLoadedWeight", getMonthlyLoadedWeight())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
