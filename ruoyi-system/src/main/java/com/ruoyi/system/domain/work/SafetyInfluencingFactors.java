package com.ruoyi.system.domain.work;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 公司各单位影响安全生产因素（原因）对象 safety_influencing_factors
 *
 * @author ruoyi
 * @date 2025-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SafetyInfluencingFactors extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String unitName;//单位名称
    private String unitCode;//单位编码
    private Date recordDate;//记录时间
    private String remarks;//备注


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unitName", getUnitName())
                .append("unitCode", getUnitCode())
                .append("remarks", getRemarks())
                .append("createTime", getCreateTime())
                .toString();
    }
}
