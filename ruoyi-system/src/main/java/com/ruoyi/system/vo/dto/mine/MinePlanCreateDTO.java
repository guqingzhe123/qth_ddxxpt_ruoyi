package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MinePlanCreateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("plan_type")
    private String planType; // 生产/开拓/进尺

    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planMonth; // 入库建议当月1日

    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    @JsonProperty("data_JSON")
    private List<MinePlanUnitItem> dataJSON;
}
