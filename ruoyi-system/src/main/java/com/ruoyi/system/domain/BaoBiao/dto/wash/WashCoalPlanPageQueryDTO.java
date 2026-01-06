package com.ruoyi.system.domain.BaoBiao.dto.wash;

import lombok.Data;

@Data
public class WashCoalPlanPageQueryDTO {
    private String plan_month;     // yyyy-MM / yyyy-MM-dd 均可，后端归一化到1号
    private String mine_category;
    private String user_id;
    private String unit_code;     //
    private Integer is_deleted;
    private Integer pageNum;
    private Integer pageSize;

}
