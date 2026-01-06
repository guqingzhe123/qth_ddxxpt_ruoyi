package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class UnitManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /** 枚举：原煤生产单位管理/开拓单位管理/进尺单位管理 */
    private String unitType;
    private String unitCode;//单位编码
    private String unitName;//单位名称

    private Integer level;//级次
    private String miningAreaInfo;//采区信息
    /** 单位细分分类 */
    private String category;//分类

    /** 0=未封存 1=已封存 */
    private Integer isSealed;//是否封存
    private Integer sortOrder;//顺序

    private Date createTime;
    private Date updateTime;
    private String userid;
    private Long parentId;
}
