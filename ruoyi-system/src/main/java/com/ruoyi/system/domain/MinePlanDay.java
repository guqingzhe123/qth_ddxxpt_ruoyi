package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【日计划数存储位置】对象 mine_plan_day
 *
 * @author ruoyi
 * @date 2025-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinePlanDay extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 计划类型 */
    @Excel(name = "计划类型")
    private String planType;

    /** 矿名 */
    @Excel(name = "矿名")
    private String areaName;

    /** 月份 */
    @Excel(name = "月份")
    private String planMonth;

    /** 天 */
    @Excel(name = "天")
    private Integer planDay;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String unitCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 日计划数 */
    @Excel(name = "日计划数")
    private Integer dayPlan;

    /** 日目标数 */
    @Excel(name = "日目标数")
    private Integer dayTarget;


    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("planType", getPlanType())
                .append("areaName", getAreaName())
                .append("planMonth", getPlanMonth())
                .append("planDay", getPlanDay())
                .append("unitCode", getUnitCode())
                .append("unitName", getUnitName())
                .append("dayPlan", getDayPlan())
                .append("dayTarget", getDayTarget())
                .toString();
    }
}
