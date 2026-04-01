package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 回撤面日报对象 work_withdrawal_face_daily_report
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkWithdrawalFaceDailyReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;

    /** 队组号 */
    @Excel(name = "队组号")
    private String teamNo;

    /** 队组号 */
    @Excel(name = "上报日期")
    private Date reportDate;
    /** 采煤工艺 */
    @Excel(name = "采煤工艺")
    private String miningTechnology;

    /** 安装面地点 */
    @Excel(name = "安装面地点")
    private String installationLocation;

    /** 面场 */
    @Excel(name = "面场")
    private String faceYard;

    /** 计划支架数量 */
    @Excel(name = "计划支架数量")
    private String plannedSupportQty;

    /** 计划溜槽 */
    @Excel(name = "计划溜槽")
    private String plannedChuteQty;

    /** 一班支架数量 */
    @Excel(name = "一班支架数量")
    private String onePlannedSupportQty;

    /** 二班支架数量 */
    @Excel(name = "二班支架数量")
    private String twoPlannedSupportQty;

    /** 三班支架数量 */
    @Excel(name = "三班支架数量")
    private String threePlannedSupportQty;

    /** 一班支架数量 */
    @Excel(name = "一班溜槽数量")
    private String onePlannedChuteQty;

    /** 二班支架数量 */
    @Excel(name = "二班溜槽数量")
    private String twoPlannedChuteQty;

    /** 三班支架数量 */
    @Excel(name = "三班溜槽数量")
    private String threePlannedChuteQty;

    /** 计划支架数量 */
    @Excel(name = "剩余支架数量")
    private String remainingStentCount;

    /** 计划溜槽 */
    @Excel(name = "剩余溜槽")
    private String remainingChute;
    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unit", getUnit())
            .append("unitCode", getUnitCode())
            .append("teamNo", getTeamNo())
            .append("miningTechnology", getMiningTechnology())
            .append("installationLocation", getInstallationLocation())
            .append("faceYard", getFaceYard())
            .append("plannedSupportQty", getPlannedSupportQty())
            .append("plannedChuteQty", getPlannedChuteQty())
            .append("onePlannedSupportQty", getOnePlannedSupportQty())
            .append("twoPlannedSupportQty", getTwoPlannedSupportQty())
            .append("threePlannedSupportQty", getThreePlannedSupportQty())
            .append("onePlannedChuteQty", getOnePlannedChuteQty())
            .append("twoPlannedChuteQty", getTwoPlannedChuteQty())
            .append("threePlannedChuteQty", getThreePlannedChuteQty())
            .append("remarks", getRemarks())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
