package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 焦化产品产销存日报对象 t_daily_production_sales_stock
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TDailyProductionSalesStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 统计日期（日报日期） */
    @Excel(name = "统计日期", readConverterExp = "日=报日期")
    private Date statDate;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 期初库存数量 */
    @Excel(name = "期初库存数量")
    private BigDecimal beginQty;

    /** 期初售价（含税） */
    @Excel(name = "期初售价", readConverterExp = "含=税")
    private BigDecimal beginPriceTaxIn;

    /** 期初售价（不含税，自动计算） */
    @Excel(name = "期初售价", readConverterExp = "不=含税，自动计算")
    private BigDecimal beginPriceTaxEx;

    /** 期初金额（不含税，自动计算） */
    @Excel(name = "期初金额", readConverterExp = "不=含税，自动计算")
    private BigDecimal beginAmountTaxEx;

    /** 当日产量 */
    @Excel(name = "当日产量")
    private BigDecimal dailyProductionQty;

    /** 当日外销量 */
    @Excel(name = "当日外销量")
    private BigDecimal dailySalesQty;

    /** 当日售价（含税） */
    @Excel(name = "当日售价", readConverterExp = "含=税")
    private BigDecimal dailySalesPriceTaxIn;

    /** 当日售价（不含税，自动计算） */
    @Excel(name = "当日售价", readConverterExp = "不=含税，自动计算")
    private BigDecimal dailySalesPriceTaxEx;

    /** 当日外销金额（不含税，自动计算） */
    @Excel(name = "当日外销金额", readConverterExp = "不=含税，自动计算")
    private BigDecimal dailySalesAmountTaxEx;

    /** 本月累计产量 */
    @Excel(name = "本月累计产量")
    private BigDecimal monthlyProductionQty;

    /** 本月累计销量 */
    @Excel(name = "本月累计销量")
    private BigDecimal monthlySalesQty;

    /** 本月累计外销金额（不含税） */
    @Excel(name = "本月累计外销金额", readConverterExp = "不=含税")
    private BigDecimal monthlySalesAmountTaxEx;

    /** 本月累计平均售价（不含税，自动计算） */
    @Excel(name = "本月累计平均售价", readConverterExp = "不=含税，自动计算")
    private BigDecimal monthlySalesAvgPriceTaxEx;

    /** 期末库存数量（自动计算） */
    @Excel(name = "期末库存数量", readConverterExp = "自=动计算")
    private BigDecimal endQty;

    /** 期末售价（不含税，手工填写） */
    @Excel(name = "期末售价", readConverterExp = "不=含税，手工填写")
    private BigDecimal endPriceTaxEx;

    /** 期末金额（不含税，自动计算） */
    @Excel(name = "期末金额", readConverterExp = "不=含税，自动计算")
    private BigDecimal endAmountTaxEx;

    /** 增值税税率（冗余，方便计算） */
    @Excel(name = "增值税税率", readConverterExp = "冗=余，方便计算")
    private BigDecimal taxRate;


    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=-正常，1-停用，2-退回")
    private Integer state;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("statDate", getStatDate())
            .append("productName", getProductName())
            .append("beginQty", getBeginQty())
            .append("beginPriceTaxIn", getBeginPriceTaxIn())
            .append("beginPriceTaxEx", getBeginPriceTaxEx())
            .append("beginAmountTaxEx", getBeginAmountTaxEx())
            .append("dailyProductionQty", getDailyProductionQty())
            .append("dailySalesQty", getDailySalesQty())
            .append("dailySalesPriceTaxIn", getDailySalesPriceTaxIn())
            .append("dailySalesPriceTaxEx", getDailySalesPriceTaxEx())
            .append("dailySalesAmountTaxEx", getDailySalesAmountTaxEx())
            .append("monthlyProductionQty", getMonthlyProductionQty())
            .append("monthlySalesQty", getMonthlySalesQty())
            .append("monthlySalesAmountTaxEx", getMonthlySalesAmountTaxEx())
            .append("monthlySalesAvgPriceTaxEx", getMonthlySalesAvgPriceTaxEx())
            .append("endQty", getEndQty())
            .append("endPriceTaxEx", getEndPriceTaxEx())
            .append("endAmountTaxEx", getEndAmountTaxEx())
            .append("taxRate", getTaxRate())
            .append("createTime", getCreateTime())
            .append("state", getState())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
