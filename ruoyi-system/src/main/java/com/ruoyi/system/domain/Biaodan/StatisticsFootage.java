package com.ruoyi.system.domain.Biaodan;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticsFootage extends BaseEntity {
    /** 全公司 */
    private String unitName;
    /** 本月计划 */
    private Integer monthPlan;
    /** 本月目标 */
    private Integer monthTarget;
    /** 本日计划 */
    private Integer dayPlan;
    /** 本日计划 */
    private Integer dayTarget;
    /** 本日完成 */
    private BigDecimal dayComplete;
    /** 累计计划 */
    private Integer dayCumulativePlan;
    /** 累计目标 */
    private Integer dayCumulativeTarget;
    /** 累计完成 */
    private Integer dayCumulativeComplete;
}
