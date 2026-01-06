package com.ruoyi.system.domain.BaoBiao.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class CoalPlantStoragePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unitName;
    private String recordTime;     // DATETIME
    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;   // 0/1
    private String mineCategory;


}
