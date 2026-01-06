package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class FactoryArchive implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /** 枚举：所属厂档案/发电厂档案 */
    private String factoryType;
    private String factoryCode;
    private String factoryName;
    /** 0=未封存 1=已封存 */
    private Integer isSealed;
    private Integer sortOrder;

    private Date createTime;
    private Date updateTime;
    private String userid;
}
