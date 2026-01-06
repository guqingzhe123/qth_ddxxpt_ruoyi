package com.ruoyi.system.domain.BaoBiao.dto.mine;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class MinePlanUpdateDTO extends MinePlanCreateDTO {
    private Long id;

    private String plan_type;
    private String unit_code;
    private String unit_name;
    private String plan_month;
    private Integer work_days_in_month;

    private BigDecimal company_wide_plan;
    private BigDecimal branch_plan;
    private BigDecimal seventh_company_plan;

    private BigDecimal company_wide_goals;
    private BigDecimal branch_company_goals;
    private BigDecimal seventh_coal_company;

    private String user_id;
    private Integer is_deleted;
    private String mine_category;

    private List<MinePlanUnitItem> data_JSON;
}
