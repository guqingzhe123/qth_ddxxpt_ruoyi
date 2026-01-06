package com.ruoyi.system.domain.BaoBiao.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * wash_coal_plan 表的“行实体”
 * 说明：各 JSON 列在这里按 String 接收（保持原样），
 * Service 层负责把这些列式 JSON 组装为 data_JSON 数组返回给前端。
 */
@Data
public class WashCoalPlanRow implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime planMonth;         // 实际库里是 DATE/或DATETIME；此处用 LocalDateTime 兼容
    private Integer workDaysInMonth;

    // —— JSON 列（数据库列名是下划线，XML里用 CAST(...) AS 别名取到下面驼峰字段）——
    private String unitCodeJson;             // 列：unit_code        类型：JSON
    private String unitNameJson;             // 列：unit_name        类型：JSON
    private String washInMonthPlanJson;      // 列：wash_in_month_plan
    private String washInDayPlanJson;        // 列：wash_in_day_plan
    private String cleanCoalMonthPlanJson;   // 列：clean_coal_month_plan
    private String cleanCoalDayPlanJson;     // 列：clean_coal_day_plan
    private String cleanCoalMonthCarPlanJson;// 列：clean_coal_month_car_plan
    private String cleanCoalDayCarPlanJson;  // 列：clean_coal_day_car_plan
    private String slackCoalMonthPlanJson;   // 列：slack_coal_month_plan
    private String slackCoalDayPlanJson;     // 列：slack_coal_day_plan
    private String slackCoalMonthCarPlanJson;// 列：slack_coal_month_car_plan
    private String slackCoalDayCarPlanJson;  // 列：slack_coal_day_car_plan

    // —— 常规审计列 ——
    private String userId;
    private Integer isDeleted;
    private String mineCategory;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
