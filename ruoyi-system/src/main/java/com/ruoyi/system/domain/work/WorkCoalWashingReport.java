package com.ruoyi.system.domain.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 洗煤数据填报对象 work_coal_washing_report
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkCoalWashingReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 上报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportTime;

    /** 单位 */
    @Excel(name = "单位")
    private String unitName;

    /** 单位Code */
    @Excel(name = "单位Code")
    private String unitCode;

    /** 调入合计（吨） */
    @Excel(name = "调入合计", readConverterExp = "吨=")
    private Long totalInput;

    /** 调入内部（吨） */
    @Excel(name = "调入内部", readConverterExp = "吨=")
    private Long internalInput;

    /** 调入外购（吨） */
    @Excel(name = "调入外购", readConverterExp = "吨=")
    private Long externalInput;

    /** 调出（吨） */
    @Excel(name = "调出", readConverterExp = "吨=")
    private Long outputCoal;

    /** 调出后原煤（吨） */
    @Excel(name = "调出后原煤", readConverterExp = "吨=")
    private Long remainingRawCoal;

    /** 入洗（吨） */
    @Excel(name = "入洗", readConverterExp = "吨=")
    private Long washingInput;

    /** 精煤（吨） */
    @Excel(name = "精煤", readConverterExp = "吨=")
    private Long cleanCoal;

    /** 精煤产率（%） */
    @Excel(name = "精煤产率", readConverterExp = "%=")
    private BigDecimal cleanCoalYield;

    /** 洗块（吨） */
    @Excel(name = "洗块", readConverterExp = "吨=")
    private Long washedLumpCoal;

    /** 洗末（吨） */
    @Excel(name = "洗末", readConverterExp = "吨=")
    private Long washedFineCoal;

    /** 综合产率（%） */
    @Excel(name = "综合产率", readConverterExp = "%=")
    private BigDecimal comprehensiveYield;

    /** 洗耗（吨/吨） */
    @Excel(name = "洗耗", readConverterExp = "吨=/吨")
    private Long washingConsumption;

    /** 洗耗率（%） */
    @Excel(name = "洗耗率", readConverterExp = "%=")
    private BigDecimal washingConsumptionRate;

    /** 三选末（吨） */
    @Excel(name = "三选末", readConverterExp = "吨=")
    private Long thirdSelectionFineCoal;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;
    /** 备注 */
    @Excel(name = "状态")
    private Integer state;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("reportTime", getReportTime())
                .append("unitName", getUnitName())
                .append("unitCode", getUnitCode())
                .append("totalInput", getTotalInput())
                .append("internalInput", getInternalInput())
                .append("externalInput", getExternalInput())
                .append("outputCoal", getOutputCoal())
                .append("remainingRawCoal", getRemainingRawCoal())
                .append("washingInput", getWashingInput())
                .append("cleanCoal", getCleanCoal())
                .append("cleanCoalYield", getCleanCoalYield())
                .append("washedLumpCoal", getWashedLumpCoal())
                .append("washedFineCoal", getWashedFineCoal())
                .append("comprehensiveYield", getComprehensiveYield())
                .append("washingConsumption", getWashingConsumption())
                .append("washingConsumptionRate", getWashingConsumptionRate())
                .append("thirdSelectionFineCoal", getThirdSelectionFineCoal())
                .append("remarks", getRemarks())
                .append("createTime", getCreateTime())
                .toString();
    }
}

