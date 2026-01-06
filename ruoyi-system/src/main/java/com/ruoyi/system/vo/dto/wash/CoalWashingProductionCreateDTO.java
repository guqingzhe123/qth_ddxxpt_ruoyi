package com.ruoyi.system.vo.dto.wash;

import com.ruoyi.system.vo.dto.base.BaseColumnarDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoalWashingProductionCreateDTO extends BaseColumnarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String production_date;  // "YYYY-MM-DD"
    private String unit_code;
    private String unit_name;

    private BigDecimal drop_in;
    private BigDecimal wash_in;
    private BigDecimal clean_coal;
    private BigDecimal lump_coal;
    private BigDecimal slack_coal;
    private BigDecimal sludge_coal;
    private BigDecimal available_gangue;
    private BigDecimal waste;
    private BigDecimal total;
    private BigDecimal self_use;
    private BigDecimal car_count;
    private BigDecimal sales_volume;
    private BigDecimal clean_coal_yield;
    private BigDecimal comprehensive_yield;
    private BigDecimal daily_stock;

    private String user_id;
    private Integer is_deleted;
    private String mine_category;
}
