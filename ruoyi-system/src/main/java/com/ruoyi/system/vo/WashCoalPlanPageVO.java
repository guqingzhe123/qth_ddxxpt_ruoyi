package com.ruoyi.system.vo;

import java.util.Date;

public class WashCoalPlanPageVO {
    private Long id;
    /** yyyy-MM，用 SQL 里 DATE_FORMAT 生成 */
    private String planMonth;
    private Integer workDaysInMonth;
    private String mineCategory;
    private String userId;
    /** JSON 数组长度（单位数） */
    private Integer unitCount;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlanMonth() { return planMonth; }
    public void setPlanMonth(String planMonth) { this.planMonth = planMonth; }

    public Integer getWorkDaysInMonth() { return workDaysInMonth; }
    public void setWorkDaysInMonth(Integer workDaysInMonth) { this.workDaysInMonth = workDaysInMonth; }

    public String getMineCategory() { return mineCategory; }
    public void setMineCategory(String mineCategory) { this.mineCategory = mineCategory; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Integer getUnitCount() { return unitCount; }
    public void setUnitCount(Integer unitCount) { this.unitCount = unitCount; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
