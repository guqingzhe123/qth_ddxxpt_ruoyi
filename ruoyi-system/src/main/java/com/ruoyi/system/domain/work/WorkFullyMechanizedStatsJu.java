package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 综采综掘统计对象 work_fully_mechanized_stats
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkFullyMechanizedStatsJu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 类型（综采/综掘） */
    @Excel(name = "类型", readConverterExp = "综=采/综掘")
    private String statsType;

    /** 单位 */
    @Excel(name = "单位")
    private String unitName;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;
    /** 单位 */
    @Excel(name = "单位")
    private String teamName;

    /** 带班日期（统计日期） */
    @Excel(name = "带班日期", readConverterExp = "统=计日期")
    private Date dutyDate;

    /** 一班产量 */
    @Excel(name = "一班产量")
    private Integer outputShift1;

    /** 二班产量 */
    @Excel(name = "二班产量")
    private Integer outputShift2;

    /** 三班产量 */
    @Excel(name = "三班产量")
    private Integer outputShift3;

    /** 原班产量 */
    @Excel(name = "原班产量")
    private Integer originalOutput;

    /** 累计产量 */
    @Excel(name = "累计产量")
    private Integer cumulativeOutput;
    /** 备注（异常说明、调整记录等） */
    @Excel(name = "备注", readConverterExp = "异=常说明、调整记录等")
    private String remarks;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("statsType", getStatsType())
            .append("unitName", getUnitName())
            .append("unitCode", getUnitCode())
            .append("dutyDate", getDutyDate())
            .append("outputShift1", getOutputShift1())
            .append("outputShift2", getOutputShift2())
            .append("outputShift3", getOutputShift3())
            .append("originalOutput", getOriginalOutput())
            .append("remarks", getRemarks())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
