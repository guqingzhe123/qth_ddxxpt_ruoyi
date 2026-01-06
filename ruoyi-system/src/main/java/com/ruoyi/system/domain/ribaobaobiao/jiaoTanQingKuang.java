package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 焦炭生产、销售情况
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class jiaoTanQingKuang extends BaseEntity {
    /** 本日生产吨数 */
    private BigDecimal cokeDailyProduction;
    /** 外运销售车数 */
    private String totalSalesCars;
    /** 外运销售吨数 */
    private BigDecimal totalSalesTonnage;
    /** 其中精煤车数 */
    private String cleanCoalSalesCars;
    /** 其中精煤吨数 */
    private BigDecimal cleanCoalSalesTonnage;
}
