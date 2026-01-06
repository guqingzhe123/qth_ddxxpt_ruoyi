package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 洗煤生产日报表
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class xiMeiRiBaoBaoBiao extends BaseEntity {

    /** 单位 */
    private String unitName;

    /** 调入 */
    private BigDecimal dropIn;
    /** 入洗 */
    private BigDecimal washIn;


    /** 精煤计划 */
    private BigDecimal cleanCoalPlan;
    /** 精煤实际 */
    private BigDecimal actualCleanCoal;
    /** 甲产率 */
    private BigDecimal aYieldRate;
    /** 其他精煤 */
    private BigDecimal otherCleanCoal;
    /** 乙产率 */
    private BigDecimal yieldOfB;
    /** 洗块 */
    private BigDecimal washBlock;
    /** 洗沫 */
    private BigDecimal washingFoam;

    /** 综合产率 */
    private BigDecimal comprehensiveYield;
    /** 洗耗 */
    private BigDecimal washingConsumption;
    /** 洗耗率 */
    private BigDecimal washingConsumptionRate;

}
