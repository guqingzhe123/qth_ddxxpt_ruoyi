package com.ruoyi.system.domain.work;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产面信息对象 work_production_face
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@TableName("work_production_face")
@EqualsAndHashCode(callSuper = false)
public class WorkProductionFace extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    private String id;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unit;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String unitCode;

    /** 队组号 */
    @Excel(name = "队组号")
    private String teamId;

    /** 作业地点 */
    @Excel(name = "作业地点")
    private String workLocation;

    /** 采煤工艺 */
    @Excel(name = "采煤工艺")
    private String miningTechnology;

    /** 机组型号 */
    @Excel(name = "机组型号")
    private String unitModel;

    /** 刮板运输机型号 */
    @Excel(name = "刮板运输机型号")
    private String scraperConveyorModel;

    /** 支护形式 */
    @Excel(name = "支护形式")
    private String supportForm;

    /** 状态（0-正常，1-停用，2-退回） */
    @Excel(name = "状态", readConverterExp = "0=-正常，1-停用，2-退回")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unit", getUnit())
                .append("unitCode", getUnitCode())
                .append("teamId", getTeamId())
                .append("workLocation", getWorkLocation())
                .append("miningTechnology", getMiningTechnology())
                .append("unitModel", getUnitModel())
                .append("scraperConveyorModel", getScraperConveyorModel())
                .append("supportForm", getSupportForm())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .toString();
    }
}
