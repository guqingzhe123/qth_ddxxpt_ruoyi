package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MineDevelopmentDataCreateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("data_type")
    private String dataType; // 开拓/进尺/生产

    @JsonProperty("record_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @JsonProperty("current_shift")
    private Integer currentShift; // 1/2/3

    @JsonProperty("data_JSON")
    private List<MineDevelopmentDataUnitItem> dataJSON;
}
