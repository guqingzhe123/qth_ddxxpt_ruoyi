package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoalPlantStorageUnitItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("clean_coal")
    private BigDecimal cleanCoal;

    @JsonProperty("slack_coal")
    private BigDecimal slackCoal;

    @JsonProperty("lump_coal")
    private BigDecimal lumpCoal;

    @JsonProperty("raw_coal")
    private BigDecimal rawCoal;
}
