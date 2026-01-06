package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/** 单日合计项（只输出“总合”字段以匹配示例） */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PesTotalItemVO {
    @JsonProperty("总合")
    private BigDecimal total;
}
