package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 产品基础信息对象 t_product_info
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TProductInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 产品编码（唯一） */
    @Excel(name = "产品编码", readConverterExp = "唯=一")
    private String productCode;

    /** 产品名称（如：焦炭(二级)、焦粉、焦油等） */
    @Excel(name = "产品名称", readConverterExp = "如=：焦炭(二级)、焦粉、焦油等")
    private String productName;

    /** 增值税税率（%），默认13% */
    @Excel(name = "增值税税率", readConverterExp = "%=")
    private BigDecimal taxRate;

    /** 计量单位（如：吨、立方米） */
    @Excel(name = "计量单位", readConverterExp = "如=：吨、立方米")
    private String unit;

    /** 是否启用：1-启用，0-停用 */
    @Excel(name = "是否启用：1-启用，0-停用")
    private Integer isActive;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("taxRate", getTaxRate())
            .append("unit", getUnit())
            .append("isActive", getIsActive())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
