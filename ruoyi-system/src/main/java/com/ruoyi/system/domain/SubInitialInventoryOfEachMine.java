package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 各矿期初库存录入子对象 sub_initial_inventory_of_each_mine
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubInitialInventoryOfEachMine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 主表ID */
    @Excel(name = "主表ID")
    private Long initialInventoryId;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 本月初期库存 */
    @Excel(name = "本月初期库存")
    private BigDecimal initialInventoryOfThisMonth;
    /** 是否退回 */
    @Excel(name = "是否退回")
    private Integer isReject;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("initialInventoryId", getInitialInventoryId())
            .append("unitName", getUnitName())
            .append("initialInventoryOfThisMonth", getInitialInventoryOfThisMonth())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
