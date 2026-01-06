package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 外运计划对象 mine_plan_outward_transport
 *
 * @author ruoyi
 * @date 2025-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinePlanOutwardTransport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 计划月份 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planMonth;

    /** 全公司销售车数 */
    @Excel(name = "全公司销售车数")
    private Integer totalCompanySalesVehicles;

    /** 全公司销售吨数	 */
    @Excel(name = "全公司销售吨数	")
    private Integer totalCompanySalesTons;

    /** 其中精煤销售车数	 */
    @Excel(name = "其中精煤销售车数	")
    private Integer cleanCoalSalesVehicles;

    /** 其中精煤销售吨数	 */
    @Excel(name = "其中精煤销售吨数	")
    private Integer cleanCoalSalesTons;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("planMonth", getPlanMonth())
                .append("totalCompanySalesVehicles", getTotalCompanySalesVehicles())
                .append("totalCompanySalesTons", getTotalCompanySalesTons())
                .append("cleanCoalSalesVehicles", getCleanCoalSalesVehicles())
                .append("cleanCoalSalesTons", getCleanCoalSalesTons())
                .toString();
    }
}
