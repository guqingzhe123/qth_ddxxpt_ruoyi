package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DateTotalRow {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;
    private Integer total;
}
