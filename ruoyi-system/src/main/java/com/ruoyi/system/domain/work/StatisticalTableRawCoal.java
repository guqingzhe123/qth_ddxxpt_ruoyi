package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 原煤产量统计表
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticalTableRawCoal extends BaseEntity {
    /** 单位 */
    @Excel(name = "单位")
    private String unitName;
    /** 日计划（单位：吨/米） */
    @Excel(name = "月计划")
    private Integer monthPlan;
    /** 日计划（单位：吨/米） */
    @Excel(name = "月目标")
    private Integer monthTarget;
    /** 日计划（单位：吨/米） */
    @Excel(name = "日计划")
    private Integer dayPlan;
    /** 日计划（单位：吨/米） */
    @Excel(name = "日目标")
    private Integer dayTarget;
    /** 日完成（单位：吨/米） */
    @Excel(name = "日完成", readConverterExp = "单=位：吨/米")
    private Integer dayComplete;
    /** 日销售 */
    @Excel(name = "日销售")
    private Integer mailySales;

    /** 累计月计划 */
    @Excel(name = "累计月计划")
    private Integer monthlyPlanTotal;

    /** 累计月完成 */
    @Excel(name = "累计月目标")
    private Integer monthlyCompleteTarget;

    /** 累计月完成 */
    @Excel(name = "累计月完成")
    private Integer monthlyCompleteTotal;

    /** 累计月销售 */
    @Excel(name = "累计月销售")
    private Integer monthlyMailySales;

    /** 累计月销售 */
    @Excel(name = "上月地存")
    private Integer groundStorage;
}
