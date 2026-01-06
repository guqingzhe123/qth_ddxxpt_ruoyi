package com.ruoyi.system.domain.BaoBiao.po;

import java.io.Serializable;
import java.util.Date;

public class WashCoalPlanPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date planMonth;            // 当月1号
    private Integer workDaysInMonth;   // 本月工作天数
    private Date createTime;
    private Date updateTime;
    private String userId;
    private Integer isDeleted;         // 0/1
    private String mineCategory;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getPlanMonth() { return planMonth; }
    public void setPlanMonth(Date planMonth) { this.planMonth = planMonth; }
    public Integer getWorkDaysInMonth() { return workDaysInMonth; }
    public void setWorkDaysInMonth(Integer workDaysInMonth) { this.workDaysInMonth = workDaysInMonth; }
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
