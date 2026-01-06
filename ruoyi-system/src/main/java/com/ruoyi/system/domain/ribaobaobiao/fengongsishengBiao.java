package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class fengongsishengBiao extends BaseEntity {

    // ======================== 原煤相关 ========================
    /** 原煤单位（如：吨） */
    private String rawCoalUnit;

    /** 原煤生产当日数量 */
    private BigDecimal rawCoalProductionDaily;

    /** 原煤生产月累计数量 */
    private BigDecimal rawCoalProductionMonthly;

    /** 原煤生产年累计数量 */
    private BigDecimal rawCoalProductionYearly;


    // ======================== 铁路相关 ========================
    /** 铁路单位（如：吨） */
    private String railwayUnit;

    /** 铁路生产当日数量 */
    private BigDecimal railwayProductionDaily;

    /** 铁路生产月累计数量 */
    private BigDecimal railwayProductionMonthly;

    /** 铁路生产年累计数量 */
    private BigDecimal railwayProductionYearly;


    // ======================== 铁路销量相关 ========================
    /** 铁路销量单位（如：吨） */
    private String railwaySalesUnit;

    /** 铁路销量当日数量 */
    private BigDecimal railwaySalesDaily;

    /** 铁路销量月累计数量 */
    private BigDecimal railwaySalesMonthly;

    /** 铁路销量年累计数量 */
    private BigDecimal railwaySalesYearly;
}
