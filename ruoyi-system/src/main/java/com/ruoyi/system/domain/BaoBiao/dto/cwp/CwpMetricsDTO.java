package com.ruoyi.system.domain.BaoBiao.dto.cwp;

import java.io.Serializable;
import java.math.BigDecimal;

public class CwpMetricsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Integer car_count;
    private BigDecimal sales_volume;
    private BigDecimal clean_coal_yield;       // 百分比
    private BigDecimal comprehensive_yield;    // 百分比
    private BigDecimal daily_stock;

    // getters/setters
    public BigDecimal getDrop_in() { return drop_in; }
    public void setDrop_in(BigDecimal drop_in) { this.drop_in = drop_in; }
    public BigDecimal getWash_in() { return wash_in; }
    public void setWash_in(BigDecimal wash_in) { this.wash_in = wash_in; }
    public BigDecimal getClean_coal() { return clean_coal; }
    public void setClean_coal(BigDecimal clean_coal) { this.clean_coal = clean_coal; }
    public BigDecimal getLump_coal() { return lump_coal; }
    public void setLump_coal(BigDecimal lump_coal) { this.lump_coal = lump_coal; }
    public BigDecimal getSlack_coal() { return slack_coal; }
    public void setSlack_coal(BigDecimal slack_coal) { this.slack_coal = slack_coal; }
    public BigDecimal getSludge_coal() { return sludge_coal; }
    public void setSludge_coal(BigDecimal sludge_coal) { this.sludge_coal = sludge_coal; }
    public BigDecimal getAvailable_gangue() { return available_gangue; }
    public void setAvailable_gangue(BigDecimal available_gangue) { this.available_gangue = available_gangue; }
    public BigDecimal getWaste() { return waste; }
    public void setWaste(BigDecimal waste) { this.waste = waste; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public BigDecimal getSelf_use() { return self_use; }
    public void setSelf_use(BigDecimal self_use) { this.self_use = self_use; }
    public Integer getCar_count() { return car_count; }
    public void setCar_count(Integer car_count) { this.car_count = car_count; }
    public BigDecimal getSales_volume() { return sales_volume; }
    public void setSales_volume(BigDecimal sales_volume) { this.sales_volume = sales_volume; }
    public BigDecimal getClean_coal_yield() { return clean_coal_yield; }
    public void setClean_coal_yield(BigDecimal clean_coal_yield) { this.clean_coal_yield = clean_coal_yield; }
    public BigDecimal getComprehensive_yield() { return comprehensive_yield; }
    public void setComprehensive_yield(BigDecimal comprehensive_yield) { this.comprehensive_yield = comprehensive_yield; }
    public BigDecimal getDaily_stock() { return daily_stock; }
    public void setDaily_stock(BigDecimal daily_stock) { this.daily_stock = daily_stock; }
}
