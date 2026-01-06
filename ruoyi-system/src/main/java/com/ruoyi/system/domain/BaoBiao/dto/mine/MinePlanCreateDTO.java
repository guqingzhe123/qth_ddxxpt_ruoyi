package com.ruoyi.system.domain.BaoBiao.dto.mine;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Data
public class MinePlanCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String plan_type;           // 生产/开拓/进尺
    private String plan_month;          // 任意可解析格式，后端归一化当月1号
    private Integer work_days_in_month; //每个月日期
    private String user_id;
    private String mine_category;
    private String unit_code;
    private String unit_name;

    private BigDecimal company_wide_plan;
    private BigDecimal branch_plan;
    private BigDecimal seventh_company_plan;

    private BigDecimal company_wide_goals;
    private BigDecimal branch_company_goals;
    private BigDecimal seventh_coal_company;



    private List<MinePlanUnitItem> data_JSON;
}

