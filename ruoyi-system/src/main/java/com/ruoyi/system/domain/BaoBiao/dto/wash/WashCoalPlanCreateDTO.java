package com.ruoyi.system.domain.BaoBiao.dto.wash;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class WashCoalPlanCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String plan_month;            // 任何可解析格式，后台归一化为当月1号
    private Integer work_days_in_month;
    private String user_id;
    private String mine_category;
    private List<WashCoalPlanUnitItem> data_JSON;

}
