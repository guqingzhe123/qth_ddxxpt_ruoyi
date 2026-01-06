package com.ruoyi.system.domain.BaoBiao.dto.wash;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class WashCoalPlanV1 implements Serializable {
    private Date planMonth;//月份
    private String unitName;
}
