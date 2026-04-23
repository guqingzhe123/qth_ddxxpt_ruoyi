package com.ruoyi.system.domain.export;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 七矿焦炭铁路外运承认车情况对象 w_cargo_receipt
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WCargoReceipt extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 序号（自增主键） */
    private String id;

    /** 序号 */
    @Excel(name = "序号")
    private String serialNumber;

    /** 统计日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsDate;

    /** 到站 */
    @Excel(name = "到站")
    private String station;

    /** 收货单位 */
    @Excel(name = "收货单位")
    private String receivingUnit;

    /** 产地 */
    @Excel(name = "产地")
    private String origin;

    /** 品种 */
    @Excel(name = "品种")
    private String variety;

    /** 承认车 */
    @Excel(name = "承认车")
    private Long approvedTrain;

    /** 月累计 */
    @Excel(name = "月累计")
    private Long monthlyTotal;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialNumber", getSerialNumber())
            .append("statsDate", getStatsDate())
            .append("station", getStation())
            .append("receivingUnit", getReceivingUnit())
            .append("origin", getOrigin())
            .append("variety", getVariety())
            .append("approvedTrain", getApprovedTrain())
            .append("monthlyTotal", getMonthlyTotal())
            .append("remark", getRemark())
            .toString();
    }
}
