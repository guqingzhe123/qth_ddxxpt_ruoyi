package com.ruoyi.system.domain.BaoBiao.dto.mine;

import lombok.Data;

@Data
public class MinePlanPageQueryDTO {
    private String plan_type;
    private String plan_month;  // yyyy-MM / yyyy-MM-dd 均可，后端归一化到当月1号
    private String user_id;
    private String mine_category;
    private Integer is_deleted;
    private String unitCode;

    private Integer pageNum;
    private Integer pageSize;
}
