package com.ruoyi.system.domain.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 安全生产信息日报返回对象
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnQuan{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;
    /** 日报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

    /** 全矿进尺（单位：米） */
    @Excel(name = "全矿进尺", readConverterExp = "单=位：米")
    private Long mineDailyAdvance;

    /** 全矿累计进尺（单位：米） */
    @Excel(name = "全矿累计进尺", readConverterExp = "单=位：米")
    private Long mineTotalAdvance;

    /** 全矿开拓（单位：米） */
    @Excel(name = "全矿开拓", readConverterExp = "单=位：米")
    private Long mineDailyDevelopment;

    /** 全矿累计开拓（单位：米） */
    @Excel(name = "全矿累计开拓", readConverterExp = "单=位：米")
    private Long mineTotalDevelopment;

    /** 搬家情况 */
    @Excel(name = "搬家情况")
    private String movingStatus;

    /** 设备运行情况 */
    @Excel(name = "设备运行情况")
    private String equipmentStatus;

    /** 影响生产因素 */
    @Excel(name = "影响生产因素")
    private String productionImpact;

    /** 安全情况 */
    @Excel(name = "安全情况")
    private String safetyStatus;

    /** 上岗情况 */
    @Excel(name = "上岗情况")
    private String attendanceStatus;

    /** 值班领导 */
    @Excel(name = "值班领导")
    private String onDutyLeader;

    /** 一班领导带班（早班） */
    @Excel(name = "一班领导带班", readConverterExp = "早=班")
    private String oneShift;

    /** 二班领导带班（中班） */
    @Excel(name = "二班领导带班", readConverterExp = "中=班")
    private String twoShift;

    /** 三班领导带班（晚班） */
    @Excel(name = "三班领导带班", readConverterExp = "晚=班")
    private String threeShift;

    /** 领导值班签字 */
    @Excel(name = "领导值班签字")
    private String leaderSign;

    /** 值班调度签字 */
    @Excel(name = "值班调度签字")
    private String dispatchSign;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    /** 生产情况 */
    @JsonProperty("ProductionStatus") // 对应JSON中的大写开头字段名
    private List<WorkProductionStatus> productionStatus;

    // 必须添加getter/setter方法（框架通过反射获取/设置字段值）
    public List<WorkProductionStatus> getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(List<WorkProductionStatus> productionStatus) {
        this.productionStatus = productionStatus;
    }
}
