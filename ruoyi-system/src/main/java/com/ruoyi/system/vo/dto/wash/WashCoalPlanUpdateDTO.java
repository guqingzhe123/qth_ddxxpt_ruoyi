package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 整单更新：wash_coal_plan
 */
@Data
public class WashCoalPlanUpdateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime planMonth;

    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    /** 替换整单 data_JSON */
    @JsonProperty("data_JSON")
    private List<WashCoalPlanUnitItem> dataJSON;
}
