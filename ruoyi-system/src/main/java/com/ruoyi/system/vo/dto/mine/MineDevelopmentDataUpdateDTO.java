package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MineDevelopmentDataUpdateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("data_type")
    private String dataType;

    @JsonProperty("record_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @JsonProperty("current_shift")
    private Integer currentShift;

    @JsonProperty("data_JSON")
    private List<MineDevelopmentDataUnitItem> dataJSON;
}
