package com.ruoyi.system.domain.BaoBiao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SubCoalProductInventoryPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long coalProductInventoryID;
    private Date recordDate;               // yyyy-MM-dd
    private String unitCode;
    private String unitName;

    private BigDecimal cleanCoalPrevStock;
    private BigDecimal cleanCoalCurrentStock;
    private BigDecimal slackLumpPrevStock;
    private BigDecimal slackLumpCurrentStock;
    private BigDecimal totalPrevStock;
    private BigDecimal totalCurrentStock;
    private BigDecimal dailyPlantSelfUse;
    private BigDecimal dailyOutsideSelfUse;
    private BigDecimal dailyTotalSelfUse;
    private BigDecimal monthlyTotalSelfUse;
    private BigDecimal yearlyTotalSelfUse;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCoalProductInventoryID() { return coalProductInventoryID; }
    public void setCoalProductInventoryID(Long coalProductInventoryID) { this.coalProductInventoryID = coalProductInventoryID; }
    public Date getRecordDate() { return recordDate; }
    public void setRecordDate(Date recordDate) { this.recordDate = recordDate; }
    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public BigDecimal getCleanCoalPrevStock() { return cleanCoalPrevStock; }
    public void setCleanCoalPrevStock(BigDecimal v) { this.cleanCoalPrevStock = v; }
    public BigDecimal getCleanCoalCurrentStock() { return cleanCoalCurrentStock; }
    public void setCleanCoalCurrentStock(BigDecimal v) { this.cleanCoalCurrentStock = v; }
    public BigDecimal getSlackLumpPrevStock() { return slackLumpPrevStock; }
    public void setSlackLumpPrevStock(BigDecimal v) { this.slackLumpPrevStock = v; }
    public BigDecimal getSlackLumpCurrentStock() { return slackLumpCurrentStock; }
    public void setSlackLumpCurrentStock(BigDecimal v) { this.slackLumpCurrentStock = v; }
    public BigDecimal getTotalPrevStock() { return totalPrevStock; }
    public void setTotalPrevStock(BigDecimal v) { this.totalPrevStock = v; }
    public BigDecimal getTotalCurrentStock() { return totalCurrentStock; }
    public void setTotalCurrentStock(BigDecimal v) { this.totalCurrentStock = v; }
    public BigDecimal getDailyPlantSelfUse() { return dailyPlantSelfUse; }
    public void setDailyPlantSelfUse(BigDecimal v) { this.dailyPlantSelfUse = v; }
    public BigDecimal getDailyOutsideSelfUse() { return dailyOutsideSelfUse; }
    public void setDailyOutsideSelfUse(BigDecimal v) { this.dailyOutsideSelfUse = v; }
    public BigDecimal getDailyTotalSelfUse() { return dailyTotalSelfUse; }
    public void setDailyTotalSelfUse(BigDecimal v) { this.dailyTotalSelfUse = v; }
    public BigDecimal getMonthlyTotalSelfUse() { return monthlyTotalSelfUse; }
    public void setMonthlyTotalSelfUse(BigDecimal v) { this.monthlyTotalSelfUse = v; }
    public BigDecimal getYearlyTotalSelfUse() { return yearlyTotalSelfUse; }
    public void setYearlyTotalSelfUse(BigDecimal v) { this.yearlyTotalSelfUse = v; }
}
