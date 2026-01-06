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
public class xiMeiTwoRiBao extends BaseEntity {

    /** 单位 */
    private String unitName;

    /** 调入 */
    private BigDecimal transferIn;
    /** 入洗计划 */
    private BigDecimal washInPlan;
    /** 入洗实际 */
    private BigDecimal washInActual;
    /** 精煤量计划 */
    private BigDecimal cleanCoalQuantityPlan;
    /** 精煤量实际 */
    private BigDecimal cleanCoalQuantityActual;
    /** 末块煤计划 */
    private BigDecimal laiKuaiQuantityPlan;
    /** 末块煤实际 */
    private BigDecimal laiKuaiQuantityActual;
    /** 精煤前存 */
    private BigDecimal oalStockPreStock;
    /** 精煤现存 */
    private BigDecimal oalStockCurrentStock;
    /** 末块煤前存 */
    private BigDecimal laiKuaiPreStock;
    /** 末块煤现存 */
    private BigDecimal laiKuaiCurrentStock;
    /** 洗耗量 */
    private BigDecimal washingConsumption;
    /** 自用煤自用 */
    private BigDecimal selfUseCoalSelfUse;
    /** 自用煤外用 */
    private BigDecimal selfUseCoalExternalUse;

}
