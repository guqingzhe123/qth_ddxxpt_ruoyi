package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CoalProductInventoryUpdateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("record_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @JsonProperty("data_JSON")
    private List<CoalProductInventoryUnitItem> dataJSON;
}
