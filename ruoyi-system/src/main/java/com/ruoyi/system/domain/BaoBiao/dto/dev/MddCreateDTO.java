package com.ruoyi.system.domain.BaoBiao.dto.dev;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class MddCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data_type;
    private String unit_code;
    private String unit_name;
    private String record_date;     // yyyy-MM-dd
    private Integer current_shift;

    private Integer total_down_count;
    private Integer mining_down_count;
    private Integer driving_down_count;
    private Integer other_down_count;
    private Integer total_up_count;
    private Integer mining_up_count;
    private Integer driving_up_count;
    private Integer other_up_count;

    private String user_id;
    private String mine_category;

    private List<MddSubItemDTO> data_JSON;

}
