package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 外运统计对象 comprehensive_production_stats
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComprehensiveProductionStats extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键，唯一标识每日综合统计记录 */
    private String id;

    /** 统计日期（如2025-11-06，所有指标均对应此日期） */
    @Excel(name = "统计日期", readConverterExp = "如=2025-11-06，所有指标均对应此日期")
    private Date statsDate;

    /** 全公司销售-车数 */
    @Excel(name = "全公司销售-车数")
    private String totalSalesCars;

    /** 全公司销售-吨数 */
    @Excel(name = "全公司销售-吨数")
    private BigDecimal totalSalesTonnage;

    /** 其中精煤-车数 */
    @Excel(name = "其中精煤-车数")
    private String cleanCoalSalesCars;

    /** 其中精煤-吨数 */
    @Excel(name = "其中精煤-吨数")
    private BigDecimal cleanCoalSalesTonnage;

    /** 承认车数合计 */
    @Excel(name = "承认车数合计")
    private String acknowledgedCarsTotal;

    /** 其中精煤-承认车数 */
    @Excel(name = "其中精煤-承认车数")
    private String cleanCoalAcknowledged;

    /** 沈局-车数 */
    @Excel(name = "沈局-车数")
    private String shenjuCars;

    /** 七台河电厂-外运数（车数） */
    @Excel(name = "七台河电厂-外运数", readConverterExp = "车=数")
    private String qitaihePowerPlantCars;

    /** 七台河电厂-外运数（车数） */
    @Excel(name = "七台河电厂")
    private String qitaihePowerPlant;

    /** 龙洋-外运数（车数） */
    @Excel(name = "龙洋-外运数", readConverterExp = "车=数")
    private String longyangCars;

    /** 日发电量（单位：千瓦时） */
    @Excel(name = "日发电量", readConverterExp = "单=位：千瓦时")
    private BigDecimal dailyPowerGeneration;

    /** 日供电量（单位：千瓦时） */
    @Excel(name = "日供电量", readConverterExp = "单=位：千瓦时")
    private BigDecimal dailyPowerSupply;

    /** 最大电力（单位：千瓦） */
    @Excel(name = "最大电力", readConverterExp = "单=位：千瓦")
    private BigDecimal maxPower;

    /** 最小电力（单位：千瓦） */
    @Excel(name = "最小电力", readConverterExp = "单=位：千瓦")
    private BigDecimal minPower;

    /** 仓存煤量（吨） */
    @Excel(name = "仓存煤量", readConverterExp = "吨=")
    private BigDecimal coalStorage;

    /** 日进煤量（吨） */
    @Excel(name = "日进煤量", readConverterExp = "吨=")
    private BigDecimal dailyCoalIn;

    /** 日耗煤量（吨） */
    @Excel(name = "日耗煤量", readConverterExp = "吨=")
    private BigDecimal dailyCoalConsumption;

    /** 本日生产完成（吨） */
    @Excel(name = "本日生产完成", readConverterExp = "吨=")
    private BigDecimal cokeDailyProduction;

    /** 本日销售完成-车数 */
    @Excel(name = "本日销售完成-车数")
    private String cokeDailySalesCars;

    /** 本日销售完成-吨数 */
    @Excel(name = "本日销售完成-吨数")
    private BigDecimal cokeDailySalesTonnage;

    /** 本日生活供水量（吨） */
    @Excel(name = "本日生活供水量", readConverterExp = "吨=")
    private BigDecimal dailyLivingWater;

    /** 本日工业供水量（吨） */
    @Excel(name = "本日工业供水量", readConverterExp = "吨=")
    private BigDecimal dailyIndustrialWater;

    /** 东网-送水温度-最高（℃） */
    @Excel(name = "东网-送水温度-最高", readConverterExp = "℃=")
    private BigDecimal eastNetworkSupplyTempMax;

    /** 东网-送水温度-最低（℃） */
    @Excel(name = "东网-送水温度-最低", readConverterExp = "℃=")
    private BigDecimal eastNetworkSupplyTempMin;

    /** 东网-回水温度-最高（℃） */
    @Excel(name = "东网-回水温度-最高", readConverterExp = "℃=")
    private BigDecimal eastNetworkReturnTempMax;

    /** 东网-回水温度-最低（℃） */
    @Excel(name = "东网-回水温度-最低", readConverterExp = "℃=")
    private BigDecimal eastNetworkReturnTempMin;

    /** 西网-送水温度-最高（℃） */
    @Excel(name = "西网-送水温度-最高", readConverterExp = "℃=")
    private BigDecimal westNetworkSupplyTempMax;

    /** 西网-送水温度-最低（℃） */
    @Excel(name = "西网-送水温度-最低", readConverterExp = "℃=")
    private BigDecimal westNetworkSupplyTempMin;

    /** 西网-回水温度-最高（℃） */
    @Excel(name = "西网-回水温度-最高", readConverterExp = "℃=")
    private BigDecimal westNetworkReturnTempMax;

    /** 西网-回水温度-最低（℃） */
    @Excel(name = "西网-回水温度-最低", readConverterExp = "℃=")
    private BigDecimal westNetworkReturnTempMin;

    /** 创建用户id */
    @Excel(name = "创建用户id")
    private String createUser;

    /** 更新用户id */
    @Excel(name = "更新用户id")
    private String updateUser;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("statsDate", getStatsDate())
            .append("totalSalesCars", getTotalSalesCars())
            .append("totalSalesTonnage", getTotalSalesTonnage())
            .append("cleanCoalSalesCars", getCleanCoalSalesCars())
            .append("cleanCoalSalesTonnage", getCleanCoalSalesTonnage())
            .append("acknowledgedCarsTotal", getAcknowledgedCarsTotal())
            .append("cleanCoalAcknowledged", getCleanCoalAcknowledged())
            .append("shenjuCars", getShenjuCars())
            .append("qitaihePowerPlantCars", getQitaihePowerPlantCars())
            .append("longyangCars", getLongyangCars())
            .append("dailyPowerGeneration", getDailyPowerGeneration())
            .append("dailyPowerSupply", getDailyPowerSupply())
            .append("maxPower", getMaxPower())
            .append("minPower", getMinPower())
            .append("coalStorage", getCoalStorage())
            .append("dailyCoalIn", getDailyCoalIn())
            .append("dailyCoalConsumption", getDailyCoalConsumption())
            .append("cokeDailyProduction", getCokeDailyProduction())
            .append("cokeDailySalesCars", getCokeDailySalesCars())
            .append("cokeDailySalesTonnage", getCokeDailySalesTonnage())
            .append("dailyLivingWater", getDailyLivingWater())
            .append("dailyIndustrialWater", getDailyIndustrialWater())
            .append("eastNetworkSupplyTempMax", getEastNetworkSupplyTempMax())
            .append("eastNetworkSupplyTempMin", getEastNetworkSupplyTempMin())
            .append("eastNetworkReturnTempMax", getEastNetworkReturnTempMax())
            .append("eastNetworkReturnTempMin", getEastNetworkReturnTempMin())
            .append("westNetworkSupplyTempMax", getWestNetworkSupplyTempMax())
            .append("westNetworkSupplyTempMin", getWestNetworkSupplyTempMin())
            .append("westNetworkReturnTempMax", getWestNetworkReturnTempMax())
            .append("westNetworkReturnTempMin", getWestNetworkReturnTempMin())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("updateTime", getUpdateTime())
            .append("updateUser", getUpdateUser())
            .toString();
    }
}
