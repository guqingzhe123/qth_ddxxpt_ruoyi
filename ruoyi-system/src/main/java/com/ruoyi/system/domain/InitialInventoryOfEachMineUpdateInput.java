package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class InitialInventoryOfEachMineUpdateInput{
    /** 月份（建议存当月1号） */
    @Excel(name = "月份", readConverterExp = "建=议存当月1号")
    private Date recordDate;
    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;
}
