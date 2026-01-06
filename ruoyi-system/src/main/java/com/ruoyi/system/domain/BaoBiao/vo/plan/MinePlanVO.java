package com.ruoyi.system.domain.BaoBiao.vo.plan;

import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanUnitItem;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Alias("MinePlanRespVO")  // 起一个项目里独一无二的名字
@Data
public class MinePlanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String plan_type;
    private Date plan_month;
    private Integer work_days_in_month;
    private String user_id;
    private String mine_category;

    private BigDecimal company_wide_plan;
    private BigDecimal branch_plan;
    private BigDecimal seventh_company_plan;

    private BigDecimal company_wide_goals;
    private BigDecimal branch_company_goals;
    private BigDecimal seventh_coal_company;


    private List<MinePlanUnitItem> data_JSON;

}
