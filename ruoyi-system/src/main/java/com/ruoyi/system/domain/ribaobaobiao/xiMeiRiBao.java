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
public class xiMeiRiBao extends BaseEntity {

    /** 单位 */
    private String unitName;

    /** 调入 */
    private Integer dropIn;
    /** 入洗 */
    private Integer washIn;
    /** 精煤 */
    private Integer cleanCoal;
    /** 块煤 */
    private Integer lumpCoal;
    /** 沫煤 */
    private Integer slackCoal;
    /** 煤泥 */
    private Integer sludgeCoal;
    /** 可用矸 */
    private Integer availableGangue;
    /** 废弃物 */
    private Integer waste;
    /** 合计 */
    private Integer total;

    /** 入洗计划量 */
    private BigDecimal washInPlan;
    /** 精煤计划量 */
    private BigDecimal cleanCoalPlan;

}
