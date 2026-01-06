package com.ruoyi.system.vo.dto.mine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MineDevelopmentDataVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("data_type")
    private String dataType;

    @JsonProperty("record_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @JsonProperty("current_shift")
    private Integer currentShift;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("mine_category")
    private String mineCategory;

    @JsonProperty("data_JSON")
    private List<MineDevelopmentDataUnitItem> dataJSON;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
