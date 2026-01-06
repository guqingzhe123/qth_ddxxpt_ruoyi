package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 洗煤数据填报备注对象 work_coal_washing_report_sub
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkCoalWashingReportSub{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    @Excel(name = "上报时间")
    private Date reportTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("reportTime", getReportTime())
                .append("remarks", getRemarks())
                .toString();
    }
}

