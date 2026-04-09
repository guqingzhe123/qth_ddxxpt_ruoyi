package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class InitialInventoryOfEachMineOutput extends BaseEntity {
    /** 单位类型 */
    private String unitType;
    /** 单位Code */
    @Excel(name = "单位Code")
    private String unitCode;
    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;
    /** 是否分公司 */
    @Excel(name = "是否分公司")
    private Integer isseparate;

    /** 本月初期库存 */
    @Excel(name = "本月初期库存")
    private BigDecimal initialInventoryOfThisMonth;

    /** 是否分公司 */
    @Excel(name = "是否退回")
    private Integer isreject;
}
