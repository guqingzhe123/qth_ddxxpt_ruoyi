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
public class yuanMeiQuXiangRiBao extends BaseEntity {
    /** 单位名称 */
    private String unitName;
    /** 入洗 */
    private BigDecimal washIn;
    /** 月计划 */
    private Long monthlyPlan;

    /** 可洗入洗煤厂 */
    private Long washInWashingPlant;
    /** 可洗入煤气公司 */
    private Long washableIntoGas;
    /** 不可入洗 */
    private Long cannotWashedIn;

    /** 储煤量 */
    private String coalStorageCapacity;
    /** 当日实际 */
    private BigDecimal dailyActual ;
    /** 当月累计完成 */
    private BigDecimal monthActual;

}
