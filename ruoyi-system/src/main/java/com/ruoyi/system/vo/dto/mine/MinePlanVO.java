package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MinePlanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("plan_type")
    private String planType;

    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planMonth;

    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("mine_category")
    private String mineCategory;

    @JsonProperty("data_JSON")
    private List<MinePlanUnitItem> dataJSON;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
