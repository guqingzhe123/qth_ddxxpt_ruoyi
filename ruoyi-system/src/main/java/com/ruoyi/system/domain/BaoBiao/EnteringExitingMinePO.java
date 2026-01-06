package com.ruoyi.system.domain.BaoBiao;

import java.io.Serializable;
import java.util.Date;

/** 对应表 entering_and_exiting_the_mine */
public class EnteringExitingMinePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String unitCode;
    private String unitName;
    private Date recordDate;       // datetime
    private Integer currentShift;  // 1/2/3

    private Integer totalDownCount;
    private Integer miningDownCount;
    private Integer drivingDownCount;
    private Integer pioneerDownCount;  // 未用于输出，但保留映射
    private Integer otherDownCount;

    private Integer totalUpCount;
    private Integer miningUpCount;
    private Integer pioneerUpcount;    // 表字段名即如此（不规范但保持）
    private Integer drivingUpCount;
    private Integer otherUpCount;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private Date updateUser; // 表结构给的是 datetime
    private Integer isDeleted;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public Integer getPioneerDownCount() { return pioneerDownCount; }
    public void setPioneerDownCount(Integer pioneerDownCount) { this.pioneerDownCount = pioneerDownCount; }

    public Integer getOtherDownCount() { return otherDownCount; }
    public void setOtherDownCount(Integer otherDownCount) { this.otherDownCount = otherDownCount; }

    public Integer getTotalUpCount() { return totalUpCount; }
    public void setTotalUpCount(Integer totalUpCount) { this.totalUpCount = totalUpCount; }

    public Integer getMiningUpCount() { return miningUpCount; }
    public void setMiningUpCount(Integer miningUpCount) { this.miningUpCount = miningUpCount; }

    public Integer getPioneerUpcount() { return pioneerUpcount; }
    public void setPioneerUpcount(Integer pioneerUpcount) { this.pioneerUpcount = pioneerUpcount; }

    public Integer getDrivingUpCount() { return drivingUpCount; }
    public void setDrivingUpCount(Integer drivingUpCount) { this.drivingUpCount = drivingUpCount; }

    public Integer getOtherUpCount() { return otherUpCount; }
    public void setOtherUpCount(Integer otherUpCount) { this.otherUpCount = otherUpCount; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    public String getCreateUser() { return createUser; }
    public void setCreateUser(String createUser) { this.createUser = createUser; }

    public Date getUpdateUser() { return updateUser; }
    public void setUpdateUser(Date updateUser) { this.updateUser = updateUser; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
}
