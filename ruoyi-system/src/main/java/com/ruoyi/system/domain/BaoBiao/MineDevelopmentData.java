package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MineDevelopmentData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /** 枚举：开拓/进尺/生产 */
    private String dataType;
    private String unitCode;
    private String unitName;
    private Date recordDate;
    /** 班次：早班/中班/夜班 */
    private String currentShift;

    private BigDecimal output;

    private Integer attendanceCount;
    private Integer totalDownCount;
    private Integer miningDownCount;
    private Integer drivingDownCount;
    private Integer otherDownCount;

    private Integer totalUpCount;
    private Integer miningUpCount;
    private Integer drivingUpCount;
    private Integer otherUpCount;

    private Date createTime;
    private Date updateTime;
    private String userid;
}
