package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 各矿期初库存录入对象 initial_inventory_of_each_mine
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InitialInventoryOfEachMine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 单位类型 */
    @Excel(name = "单位类型")
    private String unitType;

    /** 月份（建议存当月1号） */
    @Excel(name = "月份", readConverterExp = "建=议存当月1号")
    private Date recordDate;

    /** 分公司 */
    @Excel(name = "分公司")
    private BigDecimal branchCompany;

    /** 七煤公司 */
    @Excel(name = "七煤公司")
    private BigDecimal sevenCoalCompany;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 状态：0有效 1删除 */
    @Excel(name = "状态：0有效 1删除")
    private Integer isDeleted;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unitType", getUnitType())
            .append("recordDate", getRecordDate())
            .append("branchCompany", getBranchCompany())
            .append("sevenCoalCompany", getSevenCoalCompany())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
