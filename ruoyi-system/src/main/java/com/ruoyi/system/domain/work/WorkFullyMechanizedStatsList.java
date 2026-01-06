package com.ruoyi.system.domain.work;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 综采综掘统计对象 work_fully_mechanized_stats
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkFullyMechanizedStatsList extends BaseEntity {
    /** 单位 */
    private String unit;

    /** 单位code */
    private String unitCode;

    /** 带班日期（统计日期） */
    private Date dutyDate;

    /** 综采 */
    private List<WorkFullyMechanizedStats> fullyMining;
    /** 综掘 */
    private List<WorkFullyMechanizedStats> comprehensive;


}
