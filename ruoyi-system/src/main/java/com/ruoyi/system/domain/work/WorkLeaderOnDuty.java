package com.ruoyi.system.domain.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）对象 work_leader_on_duty
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkLeaderOnDuty extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;

    /** 带班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "带班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyDate;

    /** 一班领导（早班） */
    @Excel(name = "一班领导", readConverterExp = "早=班")
    private String leaderShift1;

    /** 二班领导（中班） */
    @Excel(name = "二班领导", readConverterExp = "中=班")
    private String leaderShift2;

    /** 三班领导（晚班） */
    @Excel(name = "三班领导", readConverterExp = "晚=班")
    private String leaderShift3;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unit", getUnit())
                .append("unitCode", getUnitCode())
                .append("dutyDate", getDutyDate())
                .append("leaderShift1", getLeaderShift1())
                .append("leaderShift2", getLeaderShift2())
                .append("leaderShift3", getLeaderShift3())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .toString();
    }
}
