package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InitialInventoryOfEachMineInput extends BaseEntity {
    /** 单位类型 */
    private String unitType;

    /** 月份（建议存当月1号） */
    @Excel(name = "月份", readConverterExp = "建=议存当月1号")
    private Date recordDate;

    /** 分公司 */
    private BigDecimal branchCompany;

    /** 七煤公司 */
    private BigDecimal sevenCoalCompany;


    private List<SubInitialInventoryOfEachMine> list;
}
