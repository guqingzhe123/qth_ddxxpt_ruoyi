package com.ruoyi.system.domain.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 煤炭库存销售统计对象 work_coal_stock_sales_stat
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkCoalStockSalesStat extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（自增） */
    private String id;

    /** 记录日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 煤种 */
    @Excel(name = "煤种")
    private String coalType;

    /** 原煤前日库存 */
    @Excel(name = "原煤前日库存")
        private Long rawCoalPreviousStock;

    /** 原煤当日增减 */
    @Excel(name = "原煤当日增减")
    private Long rawCoalDailyChange;

    /** 原煤当日库存 */
    @Excel(name = "原煤当日库存")
    private Long rawCoalCurrentStock;

    /** 精煤前日仓存 */
    @Excel(name = "精煤前日仓存")
    private Long cleanCoalPreviousWarehouse;

    /** 精煤当日生成 */
    @Excel(name = "精煤当日生成")
    private Long cleanCoalDailyProduction;

    /** 精煤当日销售量 */
    @Excel(name = "精煤当日销售量")
    private Long cleanCoalDailySales;

    /** 精煤当日仓存 */
    @Excel(name = "精煤当日仓存")
    private Long cleanCoalCurrentWarehouse;

    /** 精煤现存 */
    @Excel(name = "精煤现存")
    private Long cleanCoalCurrentStock;

    /** 末煤前日仓存 */
    @Excel(name = "末煤前日仓存")
    private Long leanCoalPreviousWarehouse;

    /** 末煤当日生成 */
    @Excel(name = "末煤当日生成")
    private Long leanCoalDailyProduction;

    /** 末煤当日销售量 */
    @Excel(name = "末煤当日销售量")
    private Long leanCoalDailySales;

    /** 末煤当日仓存 */
    @Excel(name = "末煤当日仓存")
    private Long leanCoalCurrentWarehouse;

    /** 末煤现存 */
    @Excel(name = "末煤现存")
    private Long leanCoalCurrentStock;

    /** 煤泥前日仓存 */
    @Excel(name = "煤泥前日仓存")
    private Long slimePreviousWarehouse;

    /** 煤泥当日生成 */
    @Excel(name = "煤泥当日生成")
    private Long slimeDailyProduction;

    /** 煤泥当日销售量 */
    @Excel(name = "煤泥当日销售量")
    private Long slimeDailySales;

    /** 煤泥当日仓存 */
    @Excel(name = "煤泥当日仓存")
    private Long slimeCurrentWarehouse;

    /** 煤泥现存 */
    @Excel(name = "煤泥现存")
    private Long slimeCurrentStock;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("recordDate", getRecordDate())
                .append("coalType", getCoalType())
                .append("rawCoalPreviousStock", getRawCoalPreviousStock())
                .append("rawCoalDailyChange", getRawCoalDailyChange())
                .append("rawCoalCurrentStock", getRawCoalCurrentStock())
                .append("cleanCoalPreviousWarehouse", getCleanCoalPreviousWarehouse())
                .append("cleanCoalDailyProduction", getCleanCoalDailyProduction())
                .append("cleanCoalDailySales", getCleanCoalDailySales())
                .append("cleanCoalCurrentWarehouse", getCleanCoalCurrentWarehouse())
                .append("cleanCoalCurrentStock", getCleanCoalCurrentStock())
                .append("leanCoalPreviousWarehouse", getLeanCoalPreviousWarehouse())
                .append("leanCoalDailyProduction", getLeanCoalDailyProduction())
                .append("leanCoalDailySales", getLeanCoalDailySales())
                .append("leanCoalCurrentWarehouse", getLeanCoalCurrentWarehouse())
                .append("leanCoalCurrentStock", getLeanCoalCurrentStock())
                .append("slimePreviousWarehouse", getSlimePreviousWarehouse())
                .append("slimeDailyProduction", getSlimeDailyProduction())
                .append("slimeDailySales", getSlimeDailySales())
                .append("slimeCurrentWarehouse", getSlimeCurrentWarehouse())
                .append("slimeCurrentStock", getSlimeCurrentStock())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .toString();
    }
}
