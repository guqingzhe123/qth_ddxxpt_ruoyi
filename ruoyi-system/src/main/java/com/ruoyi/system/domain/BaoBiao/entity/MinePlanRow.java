package com.ruoyi.system.domain.BaoBiao.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MinePlanRow implements Serializable {
    private Long id;
    private String planType;                 // 计划类型
    private String unitCode;                 // 标量（若你后续改为 JSON 数组，这里再调整）
    private String unitName;                 // 标量
    private LocalDateTime planMonth;
    private Integer workDaysInMonth;

    // JSON 列
    private String unitNameJson;             // unit_nameJSON
    private String monthPlanJson;            // month_plan
    private String dayPlanJson;              // day_plan
    private String monthTargetJson;          // month_target
    private String dayTargetJson;            // day_target

    private String userId;
    private Integer isDeleted;
    private String mineCategory;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
