package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 巷面面日报对象 work_roadway_repair_face_daily_report
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkRoadwayRepairFaceDailyReport extends BaseEntity {
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
    @Excel(name = "上报日期")
    private Date reportDate;
    /** 断面 */
    @Excel(name = "断面")
    private BigDecimal section;

    /** 计划工程 */
    @Excel(name = "计划工程")
    private String plannedProject;

    /** 作业地点 */
    @Excel(name = "作业地点")
    private String workLocation;

    /** 出货方式 */
    @Excel(name = "出货方式")
    private String shipmentMethod;

    /** 已完成 */
    @Excel(name = "已完成")
    private Long completedWork;

    /** 一班出勤 */
    @Excel(name = "一班出勤")
    private Long oneAttendance;

    /** 二班出勤 */
    @Excel(name = "二班出勤")
    private Long twoAttendance;

    /** 三班出勤 */
    @Excel(name = "三班出勤")
    private Long threeAttendance;

    /** 一班出勤 */
    @Excel(name = "一班出勤")
    private String oneWorkload;

    /** 二班出勤 */
    @Excel(name = "二班出勤")
    private String twoWorkload;

    /** 三班出勤 */
    @Excel(name = "三班出勤")
    private String threeWorkload;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unit", getUnit())
            .append("unitCode", getUnitCode())
            .append("section", getSection())
            .append("plannedProject", getPlannedProject())
            .append("workLocation", getWorkLocation())
            .append("shipmentMethod", getShipmentMethod())
            .append("completedWork", getCompletedWork())
            .append("oneAttendance", getOneAttendance())
            .append("twoAttendance", getTwoAttendance())
            .append("threeAttendance", getThreeAttendance())
            .append("oneWorkload", getOneWorkload())
            .append("twoWorkload", getTwoWorkload())
            .append("threeWorkload", getThreeWorkload())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
