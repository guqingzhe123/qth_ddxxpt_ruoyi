package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LongmeiPlanInputOutput extends BaseEntity {
    private Long id;

    /** 月份 */
    private String planMonth;

    /** 工作天数 */
    private Long workDaysInMonth;

    /** 状态 */
    private Long isDeleted;

    private String createUser;

    private Date updateUser;
    private List<SubLongmeiPlanInput> list;


}
