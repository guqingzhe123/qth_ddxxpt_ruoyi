package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class MinePlanPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String planType;        // 枚举：生产/开拓/进尺
    private Date planMonth;         // 当月1号
    private Integer workDaysInMonth;
    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;      // 0/1
    private String mineCategory;
    private String unitCode;
    private String unitName;

    private BigDecimal companyWidePlan;
    private BigDecimal branchPlan;            // DB: Branch_plan
    private BigDecimal seventhCompanyPlan;
    private BigDecimal companyWideGoals;
    private BigDecimal branchCompanyGoals;
    private BigDecimal seventhCoalCompany;

}
