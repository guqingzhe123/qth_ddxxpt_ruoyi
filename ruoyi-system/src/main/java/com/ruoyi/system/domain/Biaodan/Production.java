package com.ruoyi.system.domain.Biaodan;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Production extends BaseEntity {
    /** 全公司 */
    private String unitName;
    /** 类型 */
    private String unitType;
    /** 全年计划 */
    private Integer YearPlan;
    /** 本月计划 */
    private Integer monthPlan;
    /** 本月完成 */
    private Integer monthComplete;
    /** 上年同期月完成 */
    private Integer lastYearmonthComplete;
    /** 累计月计划 */
    private Integer cumulativeMonthPlan;
    /** 累计月完成 */
    private Integer cumulativeMonthComplete;
    /** 累计上年同期月计划 */
    private Integer cumulativeLastYearmonthComplete;
}
