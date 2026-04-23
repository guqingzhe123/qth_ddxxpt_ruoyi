package com.ruoyi.system.domain.export;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驻矿公司煤炭发运承认车情况_承认车对象 w_freight_plan
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WFreightPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID(自增，唯一标识) */
    private String id;

    /** 计划号【业务唯一编号】 */
    @Excel(name = "计划号【业务唯一编号】")
    private String planNo;

    /** 到站 */
    @Excel(name = "到站")
    private String arrivalStation;

    /** 收货人 */
    @Excel(name = "收货人")
    private String receiver;

    /** 品种 */
    @Excel(name = "品种")
    private String goodsType;

    /** 级别 */
    @Excel(name = "级别")
    private String goodsLevel;

    /** 产地 */
    @Excel(name = "产地")
    private String origin;

    /** 承认车(车辆数量) */
    @Excel(name = "承认车(车辆数量)")
    private Long approvedCars;

    /** 填报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planNo", getPlanNo())
            .append("arrivalStation", getArrivalStation())
            .append("receiver", getReceiver())
            .append("goodsType", getGoodsType())
            .append("goodsLevel", getGoodsLevel())
            .append("origin", getOrigin())
            .append("approvedCars", getApprovedCars())
            .append("statsDate", getStatsDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
