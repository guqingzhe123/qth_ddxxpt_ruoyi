package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * D14公司其他数据统计表
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class otherShuJuBaoBiao extends BaseEntity {
    // 焦炭生产相关
    /** 焦炭生产当日计划 */
    private BigDecimal cokeProductionDailyPlan;
    /** 焦炭生产当日完成 */
    private BigDecimal cokeProductionDailyCompletion;
    /** 焦炭生产本月计划 */
    private BigDecimal cokeProductionMonthlyPlan;
    /** 焦炭生产本月完成 */
    private BigDecimal cokeProductionMonthlyCompletion;
    /** 焦炭生产本年计划 */
    private BigDecimal cokeProductionYearlyPlan;
    /** 焦炭生产本年完成 */
    private BigDecimal cokeProductionYearlyCompletion;


    // 焦炭销售（车数）相关
    /** 焦炭销售当日车数计划 */
    private String cokeSalesDailyCarPlan;
    /** 焦炭销售当日车数完成 */
    private String cokeSalesDailyCarCompletion;
    /** 焦炭销售本月车数计划 */
    private String cokeSalesMonthlyCarPlan;
    /** 焦炭销售本月车数完成 */
    private String cokeSalesMonthlyCarCompletion;
    /** 焦炭销售本年车数计划 */
    private String cokeSalesYearlyCarPlan;
    /** 焦炭销售本年车数完成 */
    private String cokeSalesYearlyCarCompletion;


    // 焦炭销售（吨数）相关
    /** 焦炭销售当日吨数计划 */
    private String cokeSalesDailyTonPlan;
    /** 焦炭销售当日吨数完成 */
    private BigDecimal cokeSalesDailyTonCompletion;
    /** 焦炭销售本月吨数计划 */
    private String cokeSalesMonthlyTonPlan;
    /** 焦炭销售本月吨数完成 */
    private BigDecimal cokeSalesMonthlyTonCompletion;
    /** 焦炭销售本年吨数计划 */
    private String cokeSalesYearlyTonPlan;
    /** 焦炭销售本年吨数完成 */
    private BigDecimal cokeSalesYearlyTonCompletion;


    // 发电供电统计（本日）
    /** 发电供电统计本日发电 */
    private BigDecimal powerGenerationDaily;
    /** 发电供电统计本日供电 */
    private BigDecimal powerSupplyDaily;
    /** 发电供电统计本日最大电力 */
    private BigDecimal powerMaxDaily;
    /** 发电供电统计本日最小电力 */
    private BigDecimal powerMinDaily;
    /** 发电供电统计本日进煤量 */
    private BigDecimal coalInflowDaily;
    /** 发电供电统计本日耗煤量 */
    private BigDecimal coalConsumptionDaily;


    // 发电供电统计（本月）
    /** 发电供电统计本月发电 */
    private BigDecimal powerGenerationMonthly;
    /** 发电供电统计本月供电 */
    private BigDecimal powerSupplyMonthly;
    /** 发电供电统计本月进煤量 */
    private BigDecimal coalInflowMonthly;
    /** 发电供电统计本月耗煤量 */
    private BigDecimal coalConsumptionMonthly;


    // 发电供电统计（本年）
    /** 发电供电统计本年发电 */
    private BigDecimal powerGenerationYearly;
    /** 发电供电统计本年供电 */
    private BigDecimal powerSupplyYearly;
    /** 发电供电统计本年进煤量 */
    private BigDecimal coalInflowYearly;
    /** 发电供电统计本年耗煤量 */
    private BigDecimal coalConsumptionYearly;


    // 供水相关
    /** 日供水生活用水 */
    private BigDecimal dailyWaterSupplyDomestic;
    /** 日供水工业用水 */
    private BigDecimal dailyWaterSupplyIndustrial;
    /** 月供水生活用水 */
    private BigDecimal monthlyWaterSupplyDomestic;
    /** 月供水工业用水 */
    private BigDecimal monthlyWaterSupplyIndustrial;
    /** 年供水生活用水 */
    private BigDecimal yearlyWaterSupplyDomestic;
    /** 年供水工业用水 */
    private BigDecimal yearlyWaterSupplyIndustrial;


    // 东网水温相关
    /** 东网送水最高温度 */
    private BigDecimal eastNetworkSupplyWaterMaxTemp;
    /** 东网送水最低温度 */
    private BigDecimal eastNetworkSupplyWaterMinTemp;
    /** 东网回水最高温度 */
    private BigDecimal eastNetworkReturnWaterMaxTemp;
    /** 东网回水最低温度 */
    private BigDecimal eastNetworkReturnWaterMinTemp;


    // 西网水温相关
    /** 西网送水最高温度 */
    private BigDecimal westNetworkSupplyWaterMaxTemp;
    /** 西网送水最低温度 */
    private BigDecimal westNetworkSupplyWaterMinTemp;
    /** 西网回水最高温度 */
    private BigDecimal westNetworkReturnWaterMaxTemp;
    /** 西网回水最低温度 */
    private BigDecimal westNetworkReturnWaterMinTemp;

    /** 焦炭生产当日计划 */
    /** 焦炭生产当日完成 */
    /** 焦炭生产本月计划 */
    /** 焦炭生产本月完成 */
    /** 焦炭生产本年计划 */
    /** 焦炭生产本年完成 */


    /** 焦炭销售当日车数计划 */
    /** 焦炭销售当日车数完成 */
    /** 焦炭销售本月车数计划 */
    /** 焦炭销售本月车数完成 */
    /** 焦炭销售本年车数计划 */
    /** 焦炭销售本年车数完成 */

    /** 焦炭销售当日吨数计划 */
    /** 焦炭销售当日吨数完成 */
    /** 焦炭销售本月吨数计划 */
    /** 焦炭销售本月吨数完成 */
    /** 焦炭销售本年吨数计划 */
    /** 焦炭销售本年吨数完成 */


    /** 发电供电统计本日发电 */
    /** 发电供电统计本日供电 */
    /** 发电供电统计本日最大电力 */
    /** 发电供电统计本日最小电力 */
    /** 发电供电统计本日进煤量 */
    /** 发电供电统计本日耗煤量 */

    /** 发电供电统计本月发电 */
    /** 发电供电统计本月供电 */
    /** 发电供电统计本月进煤量 */
    /** 发电供电统计本月耗煤量 */

    /** 发电供电统计本年发电 */
    /** 发电供电统计本年供电 */
    /** 发电供电统计本年进煤量 */
    /** 发电供电统计本年耗煤量 */


    /** 日供水生活用水 */
    /** 日供水工业用水 */
    /** 月供水生活用水 */
    /** 月供水工业用水 */
    /** 年供水生活用水 */
    /** 年供水工业用水 */


    /** 东网送水最高温度 */
    /** 东网送水最低温度 */
    /** 东网回水最高温度 */
    /** 东网回水最低温度 */

    /** 西网送水最高温度 */
    /** 西网送水最低温度 */
    /** 西网回水最高温度 */
    /** 西网回水最低温度 */

}
