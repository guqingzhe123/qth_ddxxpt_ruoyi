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
 * 煤炭销售汽运计划与完成对象 w_plan_completion
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WPlanCompletion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（自增唯一标识） */
    private String id;

    /** 品种 */
    @Excel(name = "品种")
    private String productType;

    /** 厂矿 */
    @Excel(name = "厂矿")
    private String factoryMine;

    /** 收货单位 */
    @Excel(name = "收货单位")
    private String receiveUnit;

    /** 本月计划 */
    @Excel(name = "本月计划")
    private BigDecimal monthPlan;

    /** 当日计划 */
    @Excel(name = "当日计划")
    private BigDecimal dailyPlan;

    /** 当日完成 */
    @Excel(name = "当日完成")
    private BigDecimal dailyCompleted;

    /** 累计完成 */
    @Excel(name = "累计完成")
    private BigDecimal totalCompleted;

    /** 剩余计划 */
    @Excel(name = "剩余计划")
    private BigDecimal remainingPlan;

    /** 明日计划 */
    @Excel(name = "明日计划")
    private BigDecimal tomorrowPlan;

    /** 销售单位 */
    @Excel(name = "销售单位")
    private String salesUnit;

    /** 填报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productType", getProductType())
            .append("factoryMine", getFactoryMine())
            .append("receiveUnit", getReceiveUnit())
            .append("monthPlan", getMonthPlan())
            .append("dailyPlan", getDailyPlan())
            .append("dailyCompleted", getDailyCompleted())
            .append("totalCompleted", getTotalCompleted())
            .append("remainingPlan", getRemainingPlan())
            .append("tomorrowPlan", getTomorrowPlan())
            .append("salesUnit", getSalesUnit())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
