package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 人员入井出井日报录入对象 entering_and_exiting_the_mine
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EnteringAndExitingTheMine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String unitCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 记录日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 班次 */
    @Excel(name = "班次")
    private Long currentShift;

    /** 总入井人数 */
    @Excel(name = "总入井人数")
    private Long totalDownCount;

    /** 采煤入井人数 */
    @Excel(name = "采煤入井人数")
    private Long miningDownCount;

    /** 掘进入井人数 */
    @Excel(name = "掘进入井人数")
    private Long drivingDownCount;

    /** 开拓入井人数 */
    @Excel(name = "开拓入井人数")
    private Long pioneerDownCount;

    /** 其他入境人数 */
    @Excel(name = "其他入境人数")
    private Long otherDownCount;

    /** 总升井人数 */
    @Excel(name = "总升井人数")
    private Long totalUpCount;

    /** 采煤出井人数 */
    @Excel(name = "采煤出井人数")
    private Long miningUpCount;

    /** 开拓入井人数 */
    @Excel(name = "开拓入井人数")
    private Long pioneerUpcount;

    /** 掘进出井人数 */
    @Excel(name = "掘进出井人数")
    private Long drivingUpCount;

    /** 其它出井人数 */
    @Excel(name = "其它出井人数")
    private Long otherUpCount;

    /** 记录创建用户id */
    @Excel(name = "记录创建用户id")
    private String createUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updateUser;

    /** 状态 */
    @Excel(name = "状态")
    private Long isDeleted;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unitCode", getUnitCode())
                .append("unitName", getUnitName())
                .append("recordDate", getRecordDate())
                .append("currentShift", getCurrentShift())
                .append("totalDownCount", getTotalDownCount())
                .append("miningDownCount", getMiningDownCount())
                .append("drivingDownCount", getDrivingDownCount())
                .append("pioneerDownCount", getPioneerDownCount())
                .append("otherDownCount", getOtherDownCount())
                .append("totalUpCount", getTotalUpCount())
                .append("miningUpCount", getMiningUpCount())
                .append("pioneerUpcount", getPioneerUpcount())
                .append("drivingUpCount", getDrivingUpCount())
                .append("otherUpCount", getOtherUpCount())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createUser", getCreateUser())
                .append("updateUser", getUpdateUser())
                .append("isDeleted", getIsDeleted())
                .toString();
    }
}
