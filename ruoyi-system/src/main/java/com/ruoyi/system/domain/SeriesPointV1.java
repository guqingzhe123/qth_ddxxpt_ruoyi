package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/** 只暴露“总合”字段，符合你的列表样式 */
@Data
public class SeriesPointV1 {
    @JsonProperty("总合")
    private BigDecimal total;
}
