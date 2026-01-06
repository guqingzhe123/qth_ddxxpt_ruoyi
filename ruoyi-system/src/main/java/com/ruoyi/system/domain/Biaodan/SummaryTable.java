package com.ruoyi.system.domain.Biaodan;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SummaryTable extends BaseEntity {
    /** 单位 */
    private String unitName;
    /** 原煤本月计划 */
    private Integer productionMonthPlan;//原煤本月计划
    /** 原煤本月完成 */
    private Integer productionMonthCompleted;//原煤本月完成
    /** 原煤本年累计计划 */
    private Integer productionCumulativePlan;//原煤本年累计计划
    /** 原煤本年累计完成 */
    private Integer productionCumulativeCompleted;//原煤本年累计完成
    /** 进尺本月计划 */
    private Integer footageMonthPlan;//进尺本月计划
    /** 进尺本月完成 */
    private Integer footageMonthCompleted;//进尺本月完成
    /** 进尺本年累计计划 */
    private Integer footageCumulativePlan;//进尺本年累计计划
    /** 进尺本年累计完成 */
    private Integer footageCumulativeCompleted;//进尺本年累计完成
    /** 开拓本月计划 */
    private Integer expandMonthPlan;//开拓本月计划
    /** 开拓本月完成 */
    private Integer expandMonthCompleted;//开拓本月完成
    /** 开拓本年累计计划 */
    private Integer expandCumulativePlan;//开拓本年累计计划
    /** 开拓本年累计完成 */
    private Integer expandCumulativeCompleted;//开拓本年累计完成

}
