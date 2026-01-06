package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工作日对象 coal_mine_working_day
 *
 * @author ruoyi
 * @date 2025-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoalMineWorkingDay extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 单位 */
    @Excel(name = "单位")
    private String unitName;
    /** 单位Code */
    @Excel(name = "单位Code")
    private String unitCode;
    /** 月份 */
    @Excel(name = "月份")
    private String workingMonth;

    /** 工作天数 */
    @Excel(name = "工作天数")
    private Long workingDays;

    /** 是否工作 */
    @Excel(name = "是否工作")
    private Long whetherWorking;
    /** 生产计划 */
    @Excel(name = "生产计划--入洗日计划")
    private Long productionPlan;
    /** 生产目标 */
    @Excel(name = "生产目标--精煤日计划")
    private Long productionObjective;
    /** 开拓计划 */
    @Excel(name = "开拓计划--精煤日计划车数")
    private Long explorationPlan;
    /** 开拓目标 */
    @Excel(name = "开拓目标--块末日计划")
    private Long explorationObjective;
    /** 进尺计划 */
    @Excel(name = "进尺计划--块末日计划车数")
    private Long footagePlan;
    /** 进尺目标 */
    @Excel(name = "进尺目标")
    private Long footageObjective;
}
