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
 * 调度日报其他数据对象 dispatch_daily_report_other_data
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DispatchDailyReportOtherData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 日期（或月份当月1号） */
    @Excel(name = "日期", readConverterExp = "或=月份当月1号")
    private Date recordDate;

    /** 当日精煤产量 */
    @Excel(name = "当日精煤产量")
    private BigDecimal dailyCleanCoalOutput;

    /** 当日商品销量 */
    @Excel(name = "当日商品销量")
    private BigDecimal dailyProductSales;

    /** 当日外运车 */
    @Excel(name = "当日外运车")
    private BigDecimal dailyOutboundCars;

    /** 当日外运量 */
    @Excel(name = "当日外运量")
    private BigDecimal dailyOutboundVolume;

    /** 当日销量合计 */
    @Excel(name = "当日销量合计")
    private BigDecimal dailySalesTotal;

    /** 小计 */
    @Excel(name = "小计")
    private BigDecimal subtotal;

    /** 原煤精煤 */
    @Excel(name = "原煤")
    private BigDecimal rawCoal;

    /** 精煤 */
    @Excel(name = "精煤")
    private BigDecimal cleanCoal;

    /** 其他 */
    @Excel(name = "其他")
    private BigDecimal other;

    /** 当日地销 */
    @Excel(name = "当日地销")
    private BigDecimal dailyLocalSales;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 状态：0有效 1删除 */
    @Excel(name = "状态：0有效 1删除")
    private Integer isDeleted;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordDate", getRecordDate())
            .append("dailyCleanCoalOutput", getDailyCleanCoalOutput())
            .append("dailyProductSales", getDailyProductSales())
            .append("dailyOutboundCars", getDailyOutboundCars())
            .append("dailyOutboundVolume", getDailyOutboundVolume())
            .append("dailySalesTotal", getDailySalesTotal())
            .append("subtotal", getSubtotal())
            .append("rawCoal", getRawCoal())
            .append("cleanCoal", getCleanCoal())
            .append("other", getOther())
            .append("dailyLocalSales", getDailyLocalSales())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
