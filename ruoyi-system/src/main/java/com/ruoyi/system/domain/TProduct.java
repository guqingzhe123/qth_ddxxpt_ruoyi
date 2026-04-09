package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 产品基础对象 t_product
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 单位：吨/立方 */
    @Excel(name = "单位：吨/立方")
    private String unit;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("unit", getUnit())
            .append("taxRate", getTaxRate())
            .toString();
    }
}
