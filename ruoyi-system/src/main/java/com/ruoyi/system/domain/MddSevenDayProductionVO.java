package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MddSevenDayProductionVO {

    @JsonProperty("近七日生产数据")
    private List<SeriesPointVO> branchSevenDays;

    @JsonProperty("近七日计划数据")
    private List<SeriesPointVO> planSevenDays;
}
