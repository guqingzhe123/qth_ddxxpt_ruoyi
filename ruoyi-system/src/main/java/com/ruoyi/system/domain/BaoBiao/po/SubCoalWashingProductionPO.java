package com.ruoyi.system.domain.BaoBiao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubCoalWashingProductionPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long coalWashingProductionID;

    private BigDecimal dropIn;
    private BigDecimal washIn;
    private BigDecimal cleanCoal;
    private BigDecimal lumpCoal;
    private BigDecimal slackCoal;
    private BigDecimal sludgeCoal;
    private BigDecimal availableGangue;
    private BigDecimal waste;
    private BigDecimal total;
    private BigDecimal selfUse;
    private Integer carCount;
    private BigDecimal salesVolume;
    private BigDecimal cleanCoalYield;       // 5,2
    private BigDecimal comprehensiveYield;   // 5,2
    private BigDecimal dailyStock;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCoalWashingProductionID() { return coalWashingProductionID; }
    public void setCoalWashingProductionID(Long coalWashingProductionID) { this.coalWashingProductionID = coalWashingProductionID; }
    public BigDecimal getDropIn() { return dropIn; }
    public void setDropIn(BigDecimal dropIn) { this.dropIn = dropIn; }
    public BigDecimal getWashIn() { return washIn; }
    public void setWashIn(BigDecimal washIn) { this.washIn = washIn; }
    public BigDecimal getCleanCoal() { return cleanCoal; }
    public void setCleanCoal(BigDecimal cleanCoal) { this.cleanCoal = cleanCoal; }
    public BigDecimal getLumpCoal() { return lumpCoal; }
    public void setLumpCoal(BigDecimal lumpCoal) { this.lumpCoal = lumpCoal; }
    public BigDecimal getSlackCoal() { return slackCoal; }
    public void setSlackCoal(BigDecimal slackCoal) { this.slackCoal = slackCoal; }
    public BigDecimal getSludgeCoal() { return sludgeCoal; }
    public void setSludgeCoal(BigDecimal sludgeCoal) { this.sludgeCoal = sludgeCoal; }
    public BigDecimal getAvailableGangue() { return availableGangue; }
    public void setAvailableGangue(BigDecimal availableGangue) { this.availableGangue = availableGangue; }
    public BigDecimal getWaste() { return waste; }
    public void setWaste(BigDecimal waste) { this.waste = waste; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public BigDecimal getSelfUse() { return selfUse; }
    public void setSelfUse(BigDecimal selfUse) { this.selfUse = selfUse; }
    public Integer getCarCount() { return carCount; }
    public void setCarCount(Integer carCount) { this.carCount = carCount; }
    public BigDecimal getSalesVolume() { return salesVolume; }
    public void setSalesVolume(BigDecimal salesVolume) { this.salesVolume = salesVolume; }
    public BigDecimal getCleanCoalYield() { return cleanCoalYield; }
    public void setCleanCoalYield(BigDecimal cleanCoalYield) { this.cleanCoalYield = cleanCoalYield; }
    public BigDecimal getComprehensiveYield() { return comprehensiveYield; }
    public void setComprehensiveYield(BigDecimal comprehensiveYield) { this.comprehensiveYield = comprehensiveYield; }
    public BigDecimal getDailyStock() { return dailyStock; }
    public void setDailyStock(BigDecimal dailyStock) { this.dailyStock = dailyStock; }
}
