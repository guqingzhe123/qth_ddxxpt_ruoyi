package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 煤气厂销售日报（单版-含期初库存）对象 t_daily_sales
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TDailySales extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 统计日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statDate;

    /** 产品名称（固定：焦炭/焦粉等） */
    @Excel(name = "产品名称", readConverterExp = "固=定：焦炭/焦粉等")
    private String productName;

    /** 收货公司名称 */
    @Excel(name = "收货公司名称")
    private String receiver;

    /** 期初金额（自动计算） */
    @Excel(name = "期初金额", readConverterExp = "自=动计算")
    private Long beginAmount;

    /** 本月计划量 */
    @Excel(name = "本月计划量")
    private Long monthPlanQty;

    /** 售价 */
    @Excel(name = "售价")
    private Long price;

    /** 当日发运量 */
    @Excel(name = "当日发运量")
    private Long dailyShipQty;

    /** 当日收入 */
    @Excel(name = "当日收入")
    private Long dailyIncome;

    /** 月累计发运量 */
    @Excel(name = "月累计发运量")
    private Long monthTotalShipQty;

    /** 月累计收入 */
    @Excel(name = "月累计收入")
    private Long monthTotalIncome;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=-正常，1-停用，2-退回")
    private Integer state;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("statDate", getStatDate())
            .append("productName", getProductName())
            .append("receiver", getReceiver())
            .append("beginAmount", getBeginAmount())
            .append("monthPlanQty", getMonthPlanQty())
            .append("price", getPrice())
            .append("dailyShipQty", getDailyShipQty())
            .append("dailyIncome", getDailyIncome())
            .append("monthTotalShipQty", getMonthTotalShipQty())
            .append("monthTotalIncome", getMonthTotalIncome())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
