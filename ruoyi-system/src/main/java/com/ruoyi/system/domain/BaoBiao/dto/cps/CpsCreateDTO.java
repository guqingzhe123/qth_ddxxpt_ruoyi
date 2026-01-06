package com.ruoyi.system.domain.BaoBiao.dto.cps;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class CpsCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_name;
    private String record_time;     // "yyyy-MM-dd HH:mm:ss"
    private String user_id;
    private String mine_category;

    private List<CpsSubItemDTO> list;
}
