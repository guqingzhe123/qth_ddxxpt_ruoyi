package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MineDevelopmentDataUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_code")
    private String unitCode;
    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("output")
    private BigDecimal output;

    @JsonProperty("attendance_count")
    private Integer attendanceCount;

    @JsonProperty("total_down_count")
    private Integer totalDownCount;

    @JsonProperty("mining_down_count")
    private Integer miningDownCount;

    @JsonProperty("driving_down_count")
    private Integer drivingDownCount;

    @JsonProperty("other_down_count")
    private Integer otherDownCount;

    @JsonProperty("total_up_count")
    private Integer totalUpCount;

    @JsonProperty("mining_up_count")
    private Integer miningUpCount;

    @JsonProperty("driving_up_count")
    private Integer drivingUpCount;

    @JsonProperty("other_up_count")
    private Integer otherUpCount;
}
