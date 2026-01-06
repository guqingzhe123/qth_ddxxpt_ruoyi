package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MinePlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /** 枚举：生产/开拓/进尺 */
    private String planType;
    private String unitCode;
    private String unitName;
    private Date planMonth;
    private Integer workDaysInMonth;

    private BigDecimal monthPlan;
    private BigDecimal dayPlan;
    private BigDecimal monthTarget;
    private BigDecimal dayTarget;

    private Date createTime;
    private Date updateTime;
    private String userid;
}
