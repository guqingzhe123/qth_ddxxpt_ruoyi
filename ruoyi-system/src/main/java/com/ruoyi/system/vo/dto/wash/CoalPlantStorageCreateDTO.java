package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CoalPlantStorageCreateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("record_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    /** data_JSON 这里只有各仓存值 */
    @JsonProperty("data_JSON")
    private List<CoalPlantStorageUnitItem> dataJSON;
}
