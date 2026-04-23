package com.ruoyi.system.domain.export;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 龙煤股份煤炭营销分公司调度日报一对象 w_daily_report
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WDailyReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键ID */
    private Long id;

    /** 填报日期（统计日期，你要求的stats_date） */
    @Excel(name = "填报日期", readConverterExp = "统=计日期，你要求的stats_date")
    private Date statsDate;

    /** 分公司名称 */
    @Excel(name = "分公司名称")
    private String company;

    /** 类型(区分模块的) */
    @Excel(name = "类型(区分模块的)")
    private String type;

    /** 总运量-当日、总装车数-当日、煤炭总销量-当日 */
    @Excel(name = "总运量-当日、总装车数-当日、煤炭总销量-当日")
    private BigDecimal totalDaily;

    /** 总运量-累计、总装车数-累计、煤炭总销量-累计 */
    @Excel(name = "总运量-累计、总装车数-累计、煤炭总销量-累计")
    private BigDecimal totalAcc;

    /** 铁路运量-当日、铁路装车数-当日、其中自产-当日 */
    @Excel(name = "铁路运量-当日、铁路装车数-当日、其中自产-当日")
    private BigDecimal railDaily;

    /** 铁路运量-累计、铁路装车数-累计、其中自产-累计 */
    @Excel(name = "铁路运量-累计、铁路装车数-累计、其中自产-累计")
    private BigDecimal railAcc;

    /** 矿铁运量-当日、矿铁装车数-当日、统销-当日 */
    @Excel(name = "矿铁运量-当日、矿铁装车数-当日、统销-当日")
    private BigDecimal mineRailDaily;

    /** 矿铁运量-累计、矿铁装车数-累计、统销-累计 */
    @Excel(name = "矿铁运量-累计、矿铁装车数-累计、统销-累计")
    private BigDecimal mineRailAcc;

    /** 公路运量-当日、铁路流向哈局-当日、分销-当日 */
    @Excel(name = "公路运量-当日、铁路流向哈局-当日、分销-当日")
    private BigDecimal roadDaily;

    /** 公路运量-累计、铁路流向哈局-累计、分销-累计 */
    @Excel(name = "公路运量-累计、铁路流向哈局-累计、分销-累计")
    private BigDecimal roadAcc;

    /** 其中集港-当日、铁路流向沈局-当日、精煤-当日 */
    @Excel(name = "其中集港-当日、铁路流向沈局-当日、精煤-当日")
    private BigDecimal portDaily;

    /** 其中集港-累计、铁路流向沈局-累计、精煤-累计 */
    @Excel(name = "其中集港-累计、铁路流向沈局-累计、精煤-累计")
    private BigDecimal portAcc;

    /** 其中入洗-当日、铁路流向其它局-当日、电煤-当日 */
    @Excel(name = "其中入洗-当日、铁路流向其它局-当日、电煤-当日")
    private BigDecimal washDaily;

    /** 其中入洗-累计、铁路流向其它局-累计、电煤-累计 */
    @Excel(name = "其中入洗-累计、铁路流向其它局-累计、电煤-累计")
    private BigDecimal washAcc;

    /** 明日承认车-承认车、商品煤铁路外运车-当日、市场煤-当日 */
    @Excel(name = "明日承认车-承认车、商品煤铁路外运车-当日、市场煤-当日")
    private Long tomorrowAccept;

    /** 明日承认车-商品煤车、商品煤铁路外运车-累计、市场煤-累计 */
    @Excel(name = "明日承认车-商品煤车、商品煤铁路外运车-累计、市场煤-累计")
    private Long tomorrowCoalCar;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("statsDate", getStatsDate())
            .append("company", getCompany())
            .append("type", getType())
            .append("totalDaily", getTotalDaily())
            .append("totalAcc", getTotalAcc())
            .append("railDaily", getRailDaily())
            .append("railAcc", getRailAcc())
            .append("mineRailDaily", getMineRailDaily())
            .append("mineRailAcc", getMineRailAcc())
            .append("roadDaily", getRoadDaily())
            .append("roadAcc", getRoadAcc())
            .append("portDaily", getPortDaily())
            .append("portAcc", getPortAcc())
            .append("washDaily", getWashDaily())
            .append("washAcc", getWashAcc())
            .append("tomorrowAccept", getTomorrowAccept())
            .append("tomorrowCoalCar", getTomorrowCoalCar())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
