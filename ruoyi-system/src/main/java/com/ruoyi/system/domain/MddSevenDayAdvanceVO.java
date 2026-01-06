package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MddSevenDayAdvanceVO {

    @JsonProperty("分公司七日进尺数据")
    private List<SeriesPointVO> branchSevenDays;

    @JsonProperty("七煤公司七日进尺数据")
    private List<SeriesPointVO> qimeiSevenDays;
}
