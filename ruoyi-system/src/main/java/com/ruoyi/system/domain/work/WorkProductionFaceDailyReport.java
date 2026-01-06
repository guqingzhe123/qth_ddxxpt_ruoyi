package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 生产面日报对象 work_production_face_daily_report
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkProductionFaceDailyReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String unit;

    /**
     * 单位编码
     */
    @Excel(name = "单位编码")
    private String unitCode;

    /**
     * 日报日期
     */
    @Excel(name = "日报日期")
    private String reportDate;

    /**
     * 队组号
     */
    @Excel(name = "队组号")
    private String teamId;

    /**
     * 作业地点
     */
    @Excel(name = "作业地点")
    private String workLocation;

    /**
     * 采煤工艺
     */
    @Excel(name = "采煤工艺")
    private String miningTechnology;

    /**
     * 机组型号
     */
    @Excel(name = "机组型号")
    private String unitModel;

    /**
     * 刮板运输机型号
     */
    @Excel(name = "刮板运输机型号")
    private String scraperConveyorModel;

    /**
     * 支护形式
     */
    @Excel(name = "支护形式")
    private String supportForm;

    /**
     * 一班出勤
     */
    @Excel(name = "一班出勤")
    private Long oneAttendance;

    /**
     * 二班出勤
     */
    @Excel(name = "二班出勤")
    private Long twoAttendance;

    /**
     * 三班出勤
     */
    @Excel(name = "三班出勤")
    private Long threeAttendance;

    /**
     * 总出勤
     */
    @Excel(name = "总出勤")
    private Long countAttendance;

    /**
     * 一班产量（吨）
     */
    @Excel(name = "一班产量", readConverterExp = "吨=")
    private Long oneOutput;

    /**
     * 二班产量（吨）
     */
    @Excel(name = "二班产量", readConverterExp = "吨=")
    private Long twoOutput;

    /**
     * 三班产量（吨）
     */
    @Excel(name = "三班产量", readConverterExp = "吨=")
    private Long threeOutput;

    /**
     * 总产量（吨）
     */
    @Excel(name = "总产量", readConverterExp = "吨=")
    private Long countOutput;

    /**
     * 一班刀数
     */
    @Excel(name = "一班刀数")
    private BigDecimal oneCutsNum;

    /**
     * 二班刀数
     */
    @Excel(name = "二班刀数")
    private BigDecimal twoCutsNum;

    /**
     * 三班刀数
     */
    @Excel(name = "三班刀数")
    private BigDecimal threeCutsNum;

    /**
     * 总刀数
     */
    @Excel(name = "总刀数")
    private BigDecimal countCutsNum;

    /**
     * 备注信息
     */
    @Excel(name = "备注信息")
    private String remarks;

    /**
     * 状态（0-未审核，1-已审核，2-已作废）
     */
    @Excel(name = "状态", readConverterExp = "0-正常，1-退回")
    private Long status;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unit", getUnit())
                .append("unitCode", getUnitCode())
                .append("reportDate", getReportDate())
                .append("teamId", getTeamId())
                .append("workLocation", getWorkLocation())
                .append("miningTechnology", getMiningTechnology())
                .append("unitModel", getUnitModel())
                .append("scraperConveyorModel", getScraperConveyorModel())
                .append("supportForm", getSupportForm())
                .append("oneOutput", getOneOutput())
                .append("twoOutput", getTwoOutput())
                .append("threeOutput", getThreeOutput())
                .append("oneCutsNum", getOneCutsNum())
                .append("twoCutsNum", getTwoCutsNum())
                .append("threeCutsNum", getThreeCutsNum())
                .append("remarks", getRemarks())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .toString();
    }
}
