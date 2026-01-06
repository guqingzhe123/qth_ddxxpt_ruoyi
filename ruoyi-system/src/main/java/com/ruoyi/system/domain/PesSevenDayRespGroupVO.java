package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/** 最外层对象（示例要求返回一个数组，里层对象有两个数组字段） */
@Data
public class PesSevenDayRespGroupVO {
    @JsonProperty("车次合计")
    private List<PesTotalItemVO> carTotals;

    @JsonProperty("吨数合计")
    private List<PesTotalItemVO> tonnageTotals;
}
