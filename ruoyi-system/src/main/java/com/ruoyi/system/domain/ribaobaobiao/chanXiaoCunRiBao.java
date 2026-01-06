package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 产销存日报表
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class chanXiaoCunRiBao extends BaseEntity {

    /**
     * 单位
     */
    private String unitName;

    /**
     * 本日计划
     */
    private BigDecimal todayPlan;

    /**
     * 本日实际
     */
    private BigDecimal todayActual;

    /**
     * 累计计划
     */
    private BigDecimal cumulativePlan;

    /**
     * 累计实际
     */
    private BigDecimal cumulativeActual;

    /**
     * 销量本日
     */
    private BigDecimal salesToday;

    /**
     * 销量累计
     */
    private BigDecimal salesCumulative;

    /**
     * 期初库存
     */
    private BigDecimal initialInventory;

    /**
     * 本日库存
     */
    private BigDecimal inventoryToday;

    /**
     * 累计库存
     */
    private BigDecimal inventoryCumulative;

}
