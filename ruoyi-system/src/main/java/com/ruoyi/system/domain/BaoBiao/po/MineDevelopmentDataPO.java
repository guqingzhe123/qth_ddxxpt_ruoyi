package com.ruoyi.system.domain.BaoBiao.po;

import java.io.Serializable;
import java.util.Date;

public class MineDevelopmentDataPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String dataType;      // 枚举：开拓/进尺/生产
    private String unitCode;
    private String unitName;
    private Date recordDate;      // 到日
    private Integer currentShift; // 1/2/3

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
    private String userId;
    private Integer isDeleted;    // 0/1
    private String mineCategory;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }
    public Date getRecordDate() { return recordDate; }
    public void setRecordDate(Date recordDate) { this.recordDate = recordDate; }
    public Integer getCurrentShift() { return currentShift; }
    public void setCurrentShift(Integer currentShift) { this.currentShift = currentShift; }
    public Integer getTotalDownCount() { return totalDownCount; }
    public void setTotalDownCount(Integer totalDownCount) { this.totalDownCount = totalDownCount; }
    public Integer getMiningDownCount() { return miningDownCount; }
    public void setMiningDownCount(Integer miningDownCount) { this.miningDownCount = miningDownCount; }
    public Integer getDrivingDownCount() { return drivingDownCount; }
    public void setDrivingDownCount(Integer drivingDownCount) { this.drivingDownCount = drivingDownCount; }
    public Integer getOtherDownCount() { return otherDownCount; }
    public void setOtherDownCount(Integer otherDownCount) { this.otherDownCount = otherDownCount; }
    public Integer getTotalUpCount() { return totalUpCount; }
    public void setTotalUpCount(Integer totalUpCount) { this.totalUpCount = totalUpCount; }
    public Integer getMiningUpCount() { return miningUpCount; }
    public void setMiningUpCount(Integer miningUpCount) { this.miningUpCount = miningUpCount; }
    public Integer getDrivingUpCount() { return drivingUpCount; }
    public void setDrivingUpCount(Integer drivingUpCount) { this.drivingUpCount = drivingUpCount; }
    public Integer getOtherUpCount() { return otherUpCount; }
    public void setOtherUpCount(Integer otherUpCount) { this.otherUpCount = otherUpCount; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
    public String getMineCategory() { return mineCategory; }
    public void setMineCategory(String mineCategory) { this.mineCategory = mineCategory; }
}
