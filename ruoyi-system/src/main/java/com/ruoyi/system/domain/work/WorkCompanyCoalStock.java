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
 * 煤气公司精煤库存统计对象 work_company_coal_stock
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkCompanyCoalStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 记录日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 煤种（如龙选12级、选、洗等） */
    @Excel(name = "煤种", readConverterExp = "如=龙选12级、选、洗等")
    private String coalGrade;

    /** 1/3焦煤-前日库存 */
    @Excel(name = "1/3焦煤-前日库存")
    private Long leanCoalPreviousStock;

    /** 1/3焦煤-当日调入 */
    @Excel(name = "1/3焦煤-当日调入")
    private Long leanCoalDailyIn;

    /** 1/3焦煤-当日消耗 */
    @Excel(name = "1/3焦煤-当日消耗")
    private Long leanCoalDailyOut;

    /** 1/3焦煤-当日库存 */
    @Excel(name = "1/3焦煤-当日库存")
    private Long leanCoalCurrentStock;

    /** 主焦-前日库存 */
    @Excel(name = "主焦-前日库存")
    private Long mainCokePreviousStock;

    /** 主焦-当日调入 */
    @Excel(name = "主焦-当日调入")
    private Long mainCokeDailyIn;

    /** 主焦-当日消耗 */
    @Excel(name = "主焦-当日消耗")
    private Long mainCokeDailyOut;

    /** 主焦-当日库存 */
    @Excel(name = "主焦-当日库存")
    private Long mainCokeCurrentStock;

    /** 肥煤-前日库存 */
    @Excel(name = "肥煤-前日库存")
    private Long fatCoalPreviousStock;

    /** 肥煤-当日调入 */
    @Excel(name = "肥煤-当日调入")
    private Long fatCoalDailyIn;

    /** 肥煤-当日消耗 */
    @Excel(name = "肥煤-当日消耗")
    private Long fatCoalDailyOut;

    /** 肥煤-当日库存 */
    @Excel(name = "肥煤-当日库存")
    private Long fatCoalCurrentStock;

    /** 合计-前日库存 */
    @Excel(name = "合计-前日库存")
    private Long totalPreviousStock;

    /** 合计-当日调入 */
    @Excel(name = "合计-当日调入")
    private Long totalDailyIn;

    /** 合计-当日消耗 */
    @Excel(name = "合计-当日消耗")
    private Long totalDailyOut;

    /** 合计-当日库存 */
    @Excel(name = "合计-当日库存")
    private Long totalCurrentStock;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("recordDate", getRecordDate())
                .append("coalGrade", getCoalGrade())
                .append("leanCoalPreviousStock", getLeanCoalPreviousStock())
                .append("leanCoalDailyIn", getLeanCoalDailyIn())
                .append("leanCoalDailyOut", getLeanCoalDailyOut())
                .append("leanCoalCurrentStock", getLeanCoalCurrentStock())
                .append("mainCokePreviousStock", getMainCokePreviousStock())
                .append("mainCokeDailyIn", getMainCokeDailyIn())
                .append("mainCokeDailyOut", getMainCokeDailyOut())
                .append("mainCokeCurrentStock", getMainCokeCurrentStock())
                .append("fatCoalPreviousStock", getFatCoalPreviousStock())
                .append("fatCoalDailyIn", getFatCoalDailyIn())
                .append("fatCoalDailyOut", getFatCoalDailyOut())
                .append("fatCoalCurrentStock", getFatCoalCurrentStock())
                .append("totalPreviousStock", getTotalPreviousStock())
                .append("totalDailyIn", getTotalDailyIn())
                .append("totalDailyOut", getTotalDailyOut())
                .append("totalCurrentStock", getTotalCurrentStock())
                .append("createTime", getCreateTime())
                .toString();
    }
}
