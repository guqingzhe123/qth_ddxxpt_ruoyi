package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.LongmeiProductionSalesStats;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class waiyuntongjiBiao extends BaseEntity {

    /** 本日车数 */
    private String dailyCars;

    /** 本日吨数 */
    private BigDecimal dailyTons;

    /** 本日其中精煤车数 */
    private String dailyCleanCoalCars;

    /** 本日其中精煤吨数 */
    private BigDecimal dailyCleanCoalTons;


    // ======================== 本月数据 ========================
    /** 本月车数 */
    private String monthlyCars;

    /** 本月吨数 */
    private BigDecimal monthlyTons;

    /** 本月其中精煤车数 */
    private String monthlyCleanCoalCars;

    /** 本月其中精煤吨数 */
    private BigDecimal monthlyCleanCoalTons;


    // ======================== 本年数据 ========================
    /** 本年车数 */
    private String yearlyCars;

    /** 本年吨数 */
    private BigDecimal yearlyTons;

    /** 本年其中精煤车数 */
    private String yearlyCleanCoalCars;

    /** 本年其中精煤吨数 */
    private BigDecimal yearlyCleanCoalTons;


    // ======================== 其他统计数据 ========================
    /** 承认车合计 */
    private String admittedCarsTotal;

    /** 其中精煤（承认车中） */
    private String admittedCleanCoal;

    /** 沈局（数量） */
    private String shenJu;

    /** 外运数 */
    private String outwardTransport;

    /** 七电厂（数量） */
    private String qiDianChang;

    /** 龙洋（数量） */
    private String longYang;
    /**  各分公司生产、外销 */
    private List<LongmeiProductionSalesStats> list ;
}
