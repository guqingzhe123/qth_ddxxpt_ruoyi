package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductExportSituation implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date exportDate;
    private String unitCode;
    private String unitName;

    private Integer totalCars;
    private Integer cleanCoalCars;
    private Integer lumpCoalCars;
    private Integer slackCoalCars;
    private Integer mixedCoalCars;

    private BigDecimal totalTonnage;
    private BigDecimal cleanCoalTonnage;
    private BigDecimal lumpCoalTonnage;
    private BigDecimal slackCoalTonnage;
    private BigDecimal mixedCoalTonnage;

    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;
    private String mineCategory;
}
