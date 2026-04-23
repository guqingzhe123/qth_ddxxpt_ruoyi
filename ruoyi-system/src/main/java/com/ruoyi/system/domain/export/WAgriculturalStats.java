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
 * 外销商品煤销量情况对象 w_agricultural_stats
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WAgriculturalStats extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 品种 */
    @Excel(name = "品种")
    private String variety;

    /** 产地 */
    @Excel(name = "产地")
    private String origin;

    /** 水分热值 */
    @Excel(name = "水分热值")
    private String moistureCalorific;

    /** 2日数量 */
    @Excel(name = "2日数量")
    private BigDecimal day2Quantity;

    /** 2日售价 */
    @Excel(name = "2日售价")
    private BigDecimal day2Price;

    /** 2日单价 */
    @Excel(name = "2日单价")
    private BigDecimal day2UnitPrice;

    /** 1日数量 */
    @Excel(name = "1日数量")
    private BigDecimal day1Quantity;

    /** 1日售价 */
    @Excel(name = "1日售价")
    private BigDecimal day1Price;

    /** 1日金额 */
    @Excel(name = "1日金额")
    private BigDecimal day1Amount;

    /** 累计数量 */
    @Excel(name = "累计数量")
    private BigDecimal totalQuantity;

    /** 累计售价 */
    @Excel(name = "累计售价")
    private BigDecimal totalPrice;

    /** 累计金额 */
    @Excel(name = "累计金额")
    private BigDecimal totalAmount;

    /** 填报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("variety", getVariety())
            .append("origin", getOrigin())
            .append("moistureCalorific", getMoistureCalorific())
            .append("day2Quantity", getDay2Quantity())
            .append("day2Price", getDay2Price())
            .append("day2UnitPrice", getDay2UnitPrice())
            .append("day1Quantity", getDay1Quantity())
            .append("day1Price", getDay1Price())
            .append("day1Amount", getDay1Amount())
            .append("totalQuantity", getTotalQuantity())
            .append("totalPrice", getTotalPrice())
            .append("totalAmount", getTotalAmount())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
