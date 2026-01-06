package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 焦炭销售计划录入对象 coke_sales_plan
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class shengChanRiBao extends BaseEntity {
    /** 单位 */
    private String unitName;
    /** 日计划 */
    private BigDecimal dayPlan;
    /** 月计划 */
    private BigDecimal monthPlan;
    /** 年计划 */
    private BigDecimal YearPlan;

    /** 日完成 */
    private BigDecimal dayComplete;
    /** 月完成 */
    private BigDecimal monthComplete;
    /** 年完成 */
    private BigDecimal YearComplete;

}
