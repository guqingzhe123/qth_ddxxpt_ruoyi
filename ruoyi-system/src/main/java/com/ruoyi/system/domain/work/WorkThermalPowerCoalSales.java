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
 * 热电厂煤种销售库存统计对象 work_thermal_power_coal_sales
 *
 * @author ruoyi
 * @date 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkThermalPowerCoalSales extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（自增） */
    private String id;

    /** 记录日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 煤种（冗余字段，便于扩展） */
    @Excel(name = "煤种", readConverterExp = "冗=余字段，便于扩展")
    private String coalType;

    /** 劣质煤期初库存 */
    @Excel(name = "劣质煤期初库存")
    private Long inferiorCoalInitialStock;

    /** 劣质煤前日库存 */
    @Excel(name = "劣质煤前日库存")
    private Long inferiorCoalPreviousStock;

    /** 劣质煤当日销售 */
    @Excel(name = "劣质煤当日销售")
    private Long inferiorCoalDailySales;

    /** 劣质煤累计销售 */
    @Excel(name = "劣质煤累计销售")
    private Long inferiorCoalCumulativeSales;

    /** 劣质煤帐外量 */
    @Excel(name = "劣质煤帐外量")
    private Long inferiorCoalOffBookQuantity;

    /** 劣质煤当日库存 */
    @Excel(name = "劣质煤当日库存")
    private Long inferiorCoalCurrentStock;

    /** 三选煤期初库存 */
    @Excel(name = "三选煤期初库存")
    private Long threeSelectedCoalInitialStock;

    /** 三选煤前日库存 */
    @Excel(name = "三选煤前日库存")
    private Long threeSelectedCoalPreviousStock;

    /** 三选煤当日销售 */
    @Excel(name = "三选煤当日销售")
    private Long threeSelectedCoalDailySales;

    /** 三选煤累计销售 */
    @Excel(name = "三选煤累计销售")
    private Long threeSelectedCoalCumulativeSales;

    /** 三选煤当日库存 */
    @Excel(name = "三选煤当日库存")
    private Long threeSelectedCoalCurrentStock;

    /** 洗末煤期初库存 */
    @Excel(name = "洗末煤期初库存")
    private Long washedLeanCoalInitialStock;

    /** 洗末煤前日库存 */
    @Excel(name = "洗末煤前日库存")
    private Long washedLeanCoalPreviousStock;

    /** 洗末煤当日销售 */
    @Excel(name = "洗末煤当日销售")
    private Long washedLeanCoalDailySales;

    /** 洗末煤累计销售 */
    @Excel(name = "洗末煤累计销售")
    private Long washedLeanCoalCumulativeSales;

    /** 洗末煤当日库存 */
    @Excel(name = "洗末煤当日库存")
    private Long washedLeanCoalCurrentStock;

    /** 合计煤期初库存 */
    @Excel(name = "合计煤期初库存")
    private Long totalCoalInitialStock;

    /** 合计煤前日库存 */
    @Excel(name = "合计煤前日库存")
    private Long totalCoalPreviousStock;

    /** 合计煤当日销售 */
    @Excel(name = "合计煤当日销售")
    private Long totalCoalDailySales;

    /** 合计煤累计销售 */
    @Excel(name = "合计煤累计销售")
    private Long totalCoalCumulativeSales;

    /** 合计帐外量 */
    @Excel(name = "合计帐外量")
    private Long totalCoalBookQuantity;

    /** 合计当日库存 */
    @Excel(name = "合计当日库存")
    private Long totalCurrentStock;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("recordDate", getRecordDate())
                .append("coalType", getCoalType())
                .append("inferiorCoalInitialStock", getInferiorCoalInitialStock())
                .append("inferiorCoalPreviousStock", getInferiorCoalPreviousStock())
                .append("inferiorCoalDailySales", getInferiorCoalDailySales())
                .append("inferiorCoalCumulativeSales", getInferiorCoalCumulativeSales())
                .append("inferiorCoalOffBookQuantity", getInferiorCoalOffBookQuantity())
                .append("inferiorCoalCurrentStock", getInferiorCoalCurrentStock())
                .append("threeSelectedCoalInitialStock", getThreeSelectedCoalInitialStock())
                .append("threeSelectedCoalPreviousStock", getThreeSelectedCoalPreviousStock())
                .append("threeSelectedCoalDailySales", getThreeSelectedCoalDailySales())
                .append("threeSelectedCoalCumulativeSales", getThreeSelectedCoalCumulativeSales())
                .append("threeSelectedCoalCurrentStock", getThreeSelectedCoalCurrentStock())
                .append("washedLeanCoalInitialStock", getWashedLeanCoalInitialStock())
                .append("washedLeanCoalPreviousStock", getWashedLeanCoalPreviousStock())
                .append("washedLeanCoalDailySales", getWashedLeanCoalDailySales())
                .append("washedLeanCoalCumulativeSales", getWashedLeanCoalCumulativeSales())
                .append("washedLeanCoalCurrentStock", getWashedLeanCoalCurrentStock())
                .append("totalCoalInitialStock", getTotalCoalInitialStock())
                .append("totalCoalPreviousStock", getTotalCoalPreviousStock())
                .append("totalCoalDailySales", getTotalCoalDailySales())
                .append("totalCoalCumulativeSales", getTotalCoalCumulativeSales())
                .append("totalCoalBookQuantity", getTotalCoalBookQuantity())
                .append("totalCurrentStock", getTotalCurrentStock())
                .append("createTime", getCreateTime())
                .toString();
    }
}
