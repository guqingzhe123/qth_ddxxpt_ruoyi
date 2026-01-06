package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 原煤去向月报对象 raw_coal_to_daily_report
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RawCoalToDailyReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String unitName;

    /** 月计划 */
    @Excel(name = "月计划")
    private Long monthlyPlan;

    /** 洗煤厂 */
    @Excel(name = "洗煤厂")
    private Long coalWashingPlant;

    /** 燃气公司 */
    @Excel(name = "燃气公司")
    private Long gasCompany;

    /** 不可洗入 */
    @Excel(name = "不可洗入")
    private Long cannotWashedIn;

    /** 储煤量 */
    @Excel(name = "储煤量")
    private String coalStorageCapacity;

    /** 月份 */
    @Excel(name = "月份")
    private String exportDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unitName", getUnitName())
            .append("monthlyPlan", getMonthlyPlan())
            .append("coalWashingPlant", getCoalWashingPlant())
            .append("gasCompany", getGasCompany())
            .append("cannotWashedIn", getCannotWashedIn())
            .append("coalStorageCapacity", getCoalStorageCapacity())
            .append("exportDate", getExportDate())
            .toString();
    }
}
