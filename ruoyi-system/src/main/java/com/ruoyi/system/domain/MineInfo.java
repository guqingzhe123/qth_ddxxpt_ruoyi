package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 退回对象 mine_info
 *
 * @author ruoyi
 * @date 2026-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MineInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 所属模块 */
    @Excel(name = "所属模块")
    private String moduleName;

    /** 状态 2=正常 */
    @Excel(name = "状态 2=退回")
    private Long status;

    /** 矿名 */
    @Excel(name = "矿名")
    private String mineName;

    /** 矿code */
    @Excel(name = "矿code")
    private String mineCode;
    /** 统计日期（日报日期） */
    @Excel(name = "退回日期", readConverterExp = "退回日期")
    private Date statDate;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("moduleName", getModuleName())
                .append("status", getStatus())
                .append("mineName", getMineName())
                .append("mineCode", getMineCode())
                .append("createTime", getCreateTime())
                .append("statDate", getStatDate())
                .toString();
    }
}
