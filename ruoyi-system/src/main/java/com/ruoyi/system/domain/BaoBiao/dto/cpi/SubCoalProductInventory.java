package com.ruoyi.system.domain.BaoBiao.dto.cpi;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubCoalProductInventory  extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 记录日期（库存对应的日期） */
    @Excel(name = "记录日期", readConverterExp = "库=存对应的日期")
    private Date recordDate;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String unitCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 精煤前存 */
    @Excel(name = "精煤前存")
    private BigDecimal cleanCoalPrevStock;

    /** 精煤现存 */
    @Excel(name = "精煤现存")
    private BigDecimal cleanCoalCurrentStock;

    /** 末(块)煤前存 */
    @Excel(name = "末(块)煤前存")
    private BigDecimal slackLumpPrevStock;

    /** 末(块)煤现存 */
    @Excel(name = "末(块)煤现存")
    private BigDecimal slackLumpCurrentStock;

    /** 合计前存 */
    @Excel(name = "合计前存")
    private BigDecimal totalPrevStock;

    /** 合计现存 */
    @Excel(name = "合计现存")
    private BigDecimal totalCurrentStock;

    /** 日厂自用 */
    @Excel(name = "日厂自用")
    private BigDecimal dailyPlantSelfUse;

    /** 日厂外用 */
    @Excel(name = "日厂外用")
    private BigDecimal dailyOutsideSelfUse;

    /** 日合计自用 */
    @Excel(name = "日合计自用")
    private BigDecimal dailyTotalSelfUse;

    /** 月合计自用 */
    @Excel(name = "月合计自用")
    private BigDecimal monthlyTotalSelfUse;

    /** 年合计自用 */
    @Excel(name = "年合计自用")
    private BigDecimal yearlyTotalSelfUse;

    /** 单位编码 */
    @Excel(name = "用户id")
    private String userId;
    /** 单位编码 */
    @Excel(name = "创建时间")
    private Date createTime;
    /** 单位编码 */
    @Excel(name = "修改时间")
    private Date updateTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("recordDate", getRecordDate())
                .append("unitCode", getUnitCode())
                .append("unitName", getUnitName())
                .append("cleanCoalPrevStock", getCleanCoalPrevStock())
                .append("cleanCoalCurrentStock", getCleanCoalCurrentStock())
                .append("slackLumpPrevStock", getSlackLumpPrevStock())
                .append("slackLumpCurrentStock", getSlackLumpCurrentStock())
                .append("totalPrevStock", getTotalPrevStock())
                .append("totalCurrentStock", getTotalCurrentStock())
                .append("dailyPlantSelfUse", getDailyPlantSelfUse())
                .append("dailyOutsideSelfUse", getDailyOutsideSelfUse())
                .append("dailyTotalSelfUse", getDailyTotalSelfUse())
                .append("monthlyTotalSelfUse", getMonthlyTotalSelfUse())
                .append("yearlyTotalSelfUse", getYearlyTotalSelfUse())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
