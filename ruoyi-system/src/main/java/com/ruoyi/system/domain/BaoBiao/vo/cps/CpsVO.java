package com.ruoyi.system.domain.BaoBiao.vo.cps;

import com.ruoyi.system.domain.BaoBiao.dto.cps.CpsSubItemDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class CpsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unit_name;
    private String record_time;
    private String user_id;
    private String mine_category;
    private Date create_time;
    private Date update_time;

    private List<CpsSubItemDTO> data_JSON;

}
