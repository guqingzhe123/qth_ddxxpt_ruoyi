package com.ruoyi.system.vo.dto.wash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CoalPlantStorageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("record_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("mine_category")
    private String mineCategory;

    @JsonProperty("data_JSON")
    private List<CoalPlantStorageUnitItem> dataJSON;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
