package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoalPlantStorage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /** 记录时间（到分） */
    private Date recordTime;

    private BigDecimal cleanCoal;
    private BigDecimal slackCoal;
    private BigDecimal lumpCoal;
    private BigDecimal rawCoal;

    private Date createTime;
    private Date updateTime;
    private String userid;
}
