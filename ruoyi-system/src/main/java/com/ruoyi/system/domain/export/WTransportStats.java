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
 * 驻矿公司煤炭调运日报对象 w_transport_stats
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WTransportStats extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 产地 */
    @Excel(name = "产地")
    private String origin;

    /** 填报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;

    /** 合计当日车数 */
    @Excel(name = "合计当日车数")
    private Long totalDayVehicles;

    /** 合计当日吨数 */
    @Excel(name = "合计当日吨数")
    private BigDecimal totalDayTons;

    /** 合计本月车数 */
    @Excel(name = "合计本月车数")
    private Long totalMonthVehicles;

    /** 合计本月吨数 */
    @Excel(name = "合计本月吨数")
    private BigDecimal totalMonthTons;

    /** 路车当日车数 */
    @Excel(name = "路车当日车数")
    private Long roadDayVehicles;

    /** 路车当日吨数 */
    @Excel(name = "路车当日吨数")
    private BigDecimal roadDayTons;

    /** 路车本月车数 */
    @Excel(name = "路车本月车数")
    private Long roadMonthVehicles;

    /** 路车本月吨数 */
    @Excel(name = "路车本月吨数")
    private BigDecimal roadMonthTons;

    /** 矿车当日车数 */
    @Excel(name = "矿车当日车数")
    private Long mineDayVehicles;

    /** 矿车当日吨数 */
    @Excel(name = "矿车当日吨数")
    private BigDecimal mineDayTons;

    /** 矿车本月车数 */
    @Excel(name = "矿车本月车数")
    private Long mineMonthVehicles;

    /** 矿车本月吨数 */
    @Excel(name = "矿车本月吨数")
    private BigDecimal mineMonthTons;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("origin", getOrigin())
            .append("statsDate", getStatsDate())
            .append("totalDayVehicles", getTotalDayVehicles())
            .append("totalDayTons", getTotalDayTons())
            .append("totalMonthVehicles", getTotalMonthVehicles())
            .append("totalMonthTons", getTotalMonthTons())
            .append("roadDayVehicles", getRoadDayVehicles())
            .append("roadDayTons", getRoadDayTons())
            .append("roadMonthVehicles", getRoadMonthVehicles())
            .append("roadMonthTons", getRoadMonthTons())
            .append("mineDayVehicles", getMineDayVehicles())
            .append("mineDayTons", getMineDayTons())
            .append("mineMonthVehicles", getMineMonthVehicles())
            .append("mineMonthTons", getMineMonthTons())
            .toString();
    }
}
