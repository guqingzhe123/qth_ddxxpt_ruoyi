package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 原煤去向对照（记录各煤矿原煤每日及累计去向数据）对象 raw_coal_whereabouts
 *
 * @author ruoyi
 * @date 2025-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RawCoalWhereaboutsAddInput{
    /** 煤矿/单位名称（如“新建矿”“货场处”） */
    @Excel(name = "洗煤厂")
    private String coalWashing;
    /** 煤矿/单位名称（如“新建矿”“货场处”） */
    @Excel(name = "煤矿/单位名称")
    private String danwei;

    /** 数据日期（格式：YYYY-MM-DD） */
    @Excel(name = "数据日期")
    private Date rq;

    /** 洗厂-日产量 */
    @Excel(name = "日产量")
    private BigDecimal ri;

    /** 累计产量 */
    @Excel(name = "累计产量")
    private BigDecimal leiji;
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("danwei", getDanwei())
                .append("rq", getRq())
                .append("ri", getRi())
                .append("Leiji", getLeiji())
                .toString();
    }
}
