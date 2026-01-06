package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 龙煤计划录入子对象 sub_longmei_plan_input
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubLongmeiPlanInput extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 上级id */
    @Excel(name = "上级id")
    private Long coalPlantStorageId;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 原煤生产月计划 */
    @Excel(name = "原煤生产月计划")
    private BigDecimal rawCoalProductionPlanMonth;

    /** 原煤生产日计划 */
    @Excel(name = "原煤生产日计划")
    private BigDecimal rawCoalProductionPlanDay;

    /** 掘进月计划 */
    @Excel(name = "掘进月计划")
    private BigDecimal excavationPlanMonth;

    /** 掘进日计划 */
    @Excel(name = "掘进日计划")
    private BigDecimal excavationPlanDay;

    /** 开拓月计划 */
    @Excel(name = "开拓月计划")
    private BigDecimal developmentPlanMonth;

    /** 开拓日计划 */
    @Excel(name = "开拓日计划")
    private BigDecimal developmentPlanDay;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("coalPlantStorageId", getCoalPlantStorageId())
                .append("unitName", getUnitName())
                .append("rawCoalProductionPlanMonth", getRawCoalProductionPlanMonth())
                .append("rawCoalProductionPlanDay", getRawCoalProductionPlanDay())
                .append("excavationPlanMonth", getExcavationPlanMonth())
                .append("excavationPlanDay", getExcavationPlanDay())
                .append("developmentPlanMonth", getDevelopmentPlanMonth())
                .append("developmentPlanDay", getDevelopmentPlanDay())
                .toString();
    }
}
