package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 新增：wash_coal_plan
 * 你会传 data_source / plan_month / user_id / data_JSON
 */
@Data
public class WashCoalPlanCreateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 计划月份（你示例是 yyyy-MM-dd HH:mm:ss，这里按时间接收；入库建议转当月1号的 DATE） */
    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime planMonth;

    /** 本月工作天数 */
    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    /** data_JSON 数组 */
    @JsonProperty("data_JSON")
    private List<WashCoalPlanUnitItem> dataJSON;
}
