package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安全生产信息日报对象 work_production_status
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkProductionStatus extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 上级id */
    @Excel(name = "上级id")
    private String parentId;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;

    /** 日计划（单位：吨/米） */
    @Excel(name = "日计划", readConverterExp = "单=位：吨/米")
    private Long dailyPlan;

    /** 日完成（单位：吨/米） */
    @Excel(name = "日完成", readConverterExp = "单=位：吨/米")
    private Long dailyComplete;
    /** 日销售 */
    @Excel(name = "日销售")
    private Long mailySales;

    /** 累计月计划 */
    @Excel(name = "累计月计划")
    private Long monthlyPlanTotal;

    /** 累计月完成 */
    @Excel(name = "累计月完成")
    private Long monthlyCompleteTotal;




    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("unit", getUnit())
            .append("unitCode", getUnitCode())
            .append("dailyPlan", getDailyPlan())
            .append("mailySales", getMailySales())
            .append("dailyComplete", getDailyComplete())
            .append("monthlyPlanTotal", getMonthlyPlanTotal())
            .append("monthlyCompleteTotal", getMonthlyCompleteTotal())
            .append("createTime", getCreateTime())
            .toString();
    }
}
