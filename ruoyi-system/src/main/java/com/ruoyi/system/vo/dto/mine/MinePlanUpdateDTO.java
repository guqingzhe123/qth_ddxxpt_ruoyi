package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MinePlanUpdateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("plan_type")
    private String planType;

    @JsonProperty("plan_month")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planMonth;

    @JsonProperty("work_days_in_month")
    private Integer workDaysInMonth;

    @JsonProperty("data_JSON")
    private List<MinePlanUnitItem> dataJSON;
}
