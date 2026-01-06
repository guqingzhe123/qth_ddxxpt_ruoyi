package com.ruoyi.system.vo.dto.wash;

import com.ruoyi.system.vo.dto.base.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CoalWashingProductionPageQueryDTO extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String record_time; //YYYY-MM-DD
    private String unit_code;
    private String unit_name;
    private String mine_category;
    private String user_id;
    private Integer is_deleted;

    private Integer pageNum;
    private Integer pageSize;
}
