package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 龙煤计划录入对象 longmei_plan_input
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LongmeiPlanInput extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 月份 */
    @Excel(name = "月份")
    private String planMonth;

    /** 工作天数 */
    @Excel(name = "工作天数")
    private Long workDaysInMonth;

    /** 状态 */
    @Excel(name = "状态")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updateUser;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("planMonth", getPlanMonth())
                .append("workDaysInMonth", getWorkDaysInMonth())
                .append("isDeleted", getIsDeleted())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .toString();
    }
}
