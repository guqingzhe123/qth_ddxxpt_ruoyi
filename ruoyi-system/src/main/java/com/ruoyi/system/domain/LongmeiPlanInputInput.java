package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class LongmeiPlanInputInput extends BaseEntity {
    /** 月份 */
    private String planMonth;
    /** 工作天数 */
    private Long workDaysInMonth;
    /** 工作天数 */
    private List<SubLongmeiPlanInput> list;

}
