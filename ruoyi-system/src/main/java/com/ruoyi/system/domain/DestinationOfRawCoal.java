package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 原煤去向录入表 destination_of_raw_coal
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DestinationOfRawCoal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 月份（或日期；建议按需统一） */
    @Excel(name = "月份", readConverterExp = "或=日期；建议按需统一")
    private Date recordDate;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 销量 */
    @Excel(name = "销量")
    private BigDecimal salesVolume;

    /** 铁路运量 */
    @Excel(name = "铁路运量")
    private BigDecimal railwayTransportVolume;

    /** 入洗煤厂 */
    @Excel(name = "入洗煤厂")
    private BigDecimal washPlantInbound;

    /** 入销货场 */
    @Excel(name = "入销货场")
    private BigDecimal salesYardInbound;

    /** 煤气公司 */
    @Excel(name = "煤气公司")
    private BigDecimal gasCompanySupply;

    /** 自用 */
    @Excel(name = "自用")
    private BigDecimal selfUse;

    /** 其他 */
    @Excel(name = "其他")
    private BigDecimal otherUse;

    /** 库存 */
    @Excel(name = "库存")
    private BigDecimal inventory;

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
            .append("recordDate", getRecordDate())
            .append("unitName", getUnitName())
            .append("salesVolume", getSalesVolume())
            .append("railwayTransportVolume", getRailwayTransportVolume())
            .append("washPlantInbound", getWashPlantInbound())
            .append("salesYardInbound", getSalesYardInbound())
            .append("gasCompanySupply", getGasCompanySupply())
            .append("selfUse", getSelfUse())
            .append("otherUse", getOtherUse())
            .append("inventory", getInventory())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
