
package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 原煤生成完成情况统计表
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CompletionRawCoalGeneration extends BaseEntity {
    @Excel(name = "单位")
    private String unitName;
    @Excel(name = "考核计划")
    private Integer monthPlan;
    @Excel(name = "目标计划")
    private Integer monthTarget;
    @Excel(name = "实际完成")
    private Integer productionData;
    @Excel(name = "与计划比")
    private Integer comparedToPlan;
    @Excel(name = "与计划比%")
    private String comparedToPlanPercentage;
    @Excel(name = "与计划比名次")
    private String comparedToPlanRank;
    @Excel( name= "与目标比")
    private Integer comparedToTarget;
    @Excel(name = "与目标计划%")
    private String comparedToTargetPlan;
    @Excel(name = "与目标比名次")
    private String comparedToTargetRank;
}
