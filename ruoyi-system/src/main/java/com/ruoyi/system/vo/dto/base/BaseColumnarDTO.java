package com.ruoyi.system.vo.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 列式 JSON 入参的公共字段
 * dataSource 仅作为入参审计字段，不入库
 */
@Data
public class BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 数据来源（仅入参用；不入库） */
    @JsonProperty("data_source")
    private String dataSource;

    /** 用户ID（入库字段） */
    @JsonProperty("user_id")
    private String userId;

    /** 状态：0=有效，1=已删除（入库字段） */
    @JsonProperty("is_deleted")
    private Integer isDeleted = 0;

    /** 矿类别（入库字段） */
    @JsonProperty("mine_category")
    private String mineCategory;
}
