package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 综采综掘配置对象 work_fully_mechanized
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkFullyMechanized extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类型(综采或综掘) */
    @Excel(name = "类型(综采或综掘)")
    private String statsType;

    /** 单位 */
    @Excel(name = "单位")
    private String unitName;

    /** 单位code */
    @Excel(name = "单位code")
    private String unitCode;

    /** 队组名称 */
    @Excel(name = "队组名称")
    private String teamName;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("statsType", getStatsType())
                .append("unitName", getUnitName())
                .append("unitCode", getUnitCode())
                .append("teamName", getTeamName())
                .toString();
    }
}
