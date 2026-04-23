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
 * 外销商品煤日执行情况对象 w_daily_plan
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WDailyPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增序号主键 */
    private String id;

    /** 厂矿 */
    @Excel(name = "厂矿")
    private String factoryMine;

    /** 煤种级别 */
    @Excel(name = "煤种级别")
    private String coalLevel;

    /** 用户 */
    @Excel(name = "用户")
    private String userUnit;

    /** 到站 */
    @Excel(name = "到站")
    private String station;

    /** 计划量车 */
    @Excel(name = "计划量车")
    private BigDecimal planCar;

    /** 计划量吨 */
    @Excel(name = "计划量吨")
    private BigDecimal planTon;

    /** 当日实装车 */
    @Excel(name = "当日实装车")
    private BigDecimal todayLoadCar;

    /** 当日实装吨 */
    @Excel(name = "当日实装吨")
    private BigDecimal todayLoadTon;

    /** 累计实装车 */
    @Excel(name = "累计实装车")
    private BigDecimal totalLoadCar;

    /** 累计实装吨 */
    @Excel(name = "累计实装吨")
    private BigDecimal totalLoadTon;

    /** 剩余计划车 */
    @Excel(name = "剩余计划车")
    private BigDecimal remainPlanCar;

    /** 剩余计划吨 */
    @Excel(name = "剩余计划吨")
    private BigDecimal remainPlanTon;

    /** 剩余日均车 */
    @Excel(name = "剩余日均车")
    private BigDecimal remainAvgDailyCar;

    /** 剩余日均吨 */
    @Excel(name = "剩余日均吨")
    private BigDecimal remainAvgDailyTon;

    /** 完成百分比(%) */
    @Excel(name = "完成百分比(%)")
    private BigDecimal completeRate;

    /** 明日承认车 */
    @Excel(name = "明日承认车")
    private BigDecimal tomorrowAcceptCar;

    /** 统计日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("factoryMine", getFactoryMine())
            .append("coalLevel", getCoalLevel())
            .append("userUnit", getUserUnit())
            .append("station", getStation())
            .append("planCar", getPlanCar())
            .append("planTon", getPlanTon())
            .append("todayLoadCar", getTodayLoadCar())
            .append("todayLoadTon", getTodayLoadTon())
            .append("totalLoadCar", getTotalLoadCar())
            .append("totalLoadTon", getTotalLoadTon())
            .append("remainPlanCar", getRemainPlanCar())
            .append("remainPlanTon", getRemainPlanTon())
            .append("remainAvgDailyCar", getRemainAvgDailyCar())
            .append("remainAvgDailyTon", getRemainAvgDailyTon())
            .append("completeRate", getCompleteRate())
            .append("tomorrowAcceptCar", getTomorrowAcceptCar())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
