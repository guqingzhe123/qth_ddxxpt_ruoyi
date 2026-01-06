package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分页列表/详情 出参 VO（把列式 JSON 还原为 data_JSON 数组）
 */
@Data
public class WashCoalPlanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime planMonth;

    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("mine_category")
    private String mineCategory;

    /** 还原后的 data_JSON 数组 */
    @JsonProperty("data_JSON")
    private List<WashCoalPlanUnitItem> dataJSON;

    /** 创建/更新时间（可选） */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
