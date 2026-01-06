package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 焦炭销售计划录入对象 coke_sales_plan
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CokeSalesPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 月份 */
    @Excel(name = "月份", dateFormat = "yyyy-MM-dd")
    private String planMonth;

    /** 本月工作天数 */
    @Excel(name = "本月工作天数")
    private Long workDaysInMonth;

    /** 月计划生产吨数 */
    @Excel(name = "月计划生产吨数")
    private BigDecimal productionTonnageMonth;

    /** 日计划生产吨数 */
    @Excel(name = "日计划生产吨数")
    private BigDecimal productionTonnageDay;

    /** 月计划销售车辆 */
    @Excel(name = "月计划销售车辆")
    private String sellingVehiclesMonth;

    /** 日计划销售车辆 */
    @Excel(name = "日计划销售车辆")
    private String sellingVehiclesDay;

    /** 月计划销售吨数 */
    @Excel(name = "月计划销售吨数")
    private String salesTonnageMonth;

    /** 日计划销售吨数 */
    @Excel(name = "日计划销售吨数")
    private String salesTonnageDay;

    /** 记录创建人 */
    @Excel(name = "记录创建人")
    private String createUser;

    /** 记录修改人 */
    @Excel(name = "记录修改人")
    private String updateUser;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("planMonth", getPlanMonth())
                .append("workDaysInMonth", getWorkDaysInMonth())
                .append("productionTonnageMonth", getProductionTonnageMonth())
                .append("productionTonnageDay", getProductionTonnageDay())
                .append("sellingVehiclesMonth", getSellingVehiclesMonth())
                .append("sellingVehiclesDay", getSellingVehiclesDay())
                .append("salesTonnageMonth", getSalesTonnageMonth())
                .append("salesTonnageDay", getSalesTonnageDay())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .toString();
    }
}
