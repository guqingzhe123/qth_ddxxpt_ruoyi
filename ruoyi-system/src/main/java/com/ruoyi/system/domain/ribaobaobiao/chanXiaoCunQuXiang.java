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
public class chanXiaoCunQuXiang extends BaseEntity {

    /** 单位 */
    private String unitName;

    /** 生产量计划 */
    private BigDecimal productionPlan;

    /** 生产量当日 */
    private BigDecimal productionToday;

    /** 生产量累计 */
    private BigDecimal productionCumulative;

    /** 销售量当日 */
    private BigDecimal salesToday;

    /** 销售量累计 */
    private BigDecimal salesCumulative;

    /** 铁路运量当日 */
    private BigDecimal railwayVolumeToday;

    /** 铁路运量累计 */
    private BigDecimal railwayVolumeCumulative;

    /** 入洗煤场当日 */
    private BigDecimal washCoalYardInToday;

    /** 入洗煤场累计 */
    private BigDecimal washCoalYardInCumulative;

    /** 入销售货场当日 */
    private BigDecimal salesYardInToday;

    /** 入销售货场累计 */
    private BigDecimal salesYardInCumulative;

    /** 燃气公司当日 */
    private BigDecimal gasCompanyToday;

    /** 燃气公司累计 */
    private BigDecimal gasCompanyCumulative;

    /** 自用当日 */
    private BigDecimal selfUseToday;

    /** 自用累计 */
    private BigDecimal selfUseCumulative;

    /** 其他当日 */
    private BigDecimal otherToday;

    /** 其他累计 */
    private BigDecimal otherCumulative;

    /** 当日库存 */
    private BigDecimal inventoryToday;

    /** 累计库存 */
    private BigDecimal inventoryCumulative;

    /** 期初库存 */
    private BigDecimal initialInventory;

}
