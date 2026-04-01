package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 安装面信息对象 work_installation_face_daily_report
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkInstallationFaceDailyReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;

    /** 队组号 */
    @Excel(name = "队组号")
    private String teamNo;

    /**上报日期（统计日期） */
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
    private Integer plannedSupportQty;

    /** 计划溜槽 */
    @Excel(name = "计划溜槽")
    private Integer plannedChuteQty;

    /** 一班支架数量 */
    @Excel(name = "一班支架数量")
    private Integer onePlannedSupportQty;

    /** 二班支架数量 */
    @Excel(name = "二班支架数量")
    private Integer twoPlannedSupportQty;

    /** 三班支架数量 */
    @Excel(name = "三班支架数量")
    private Integer threePlannedSupportQty;

    /** 一班支架数量 */
    @Excel(name = "一班支架数量")
    private Integer onePlannedChuteQty;

    /** 二班支架数量 */
    @Excel(name = "二班支架数量")
    private Integer twoPlannedChuteQty;

    /** 三班支架数量 */
    @Excel(name = "三班支架数量")
    private Integer threePlannedChuteQty;


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

