package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EemSevenDayRespGroupVO {
    /** 如果你想沿用“车次合计/吨数合计”，把下面两个 @JsonProperty 的值改一下即可 */
    @JsonProperty("入井人数合计")
    private List<EemTotalItemVO> downTotals;

    @JsonProperty("升井人数合计")
    private List<EemTotalItemVO> upTotals;
}
