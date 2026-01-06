package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
