package com.ruoyi.system.domain.BaoBiao;

import java.io.Serializable;
import java.util.Date;

/**
 * 与表 wash_coal_plan 对应的实体（列式 JSON 列用 String 存 JSON 文本）
 */
public class WashCoalPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 计划月份（存当月1号，例如 2025-11-01） */
    private Date planMonth;

    /** 本月工作天数 */
    private Integer workDaysInMonth;

    /** 数据来源 */
    private String dataSource;

    /** 列式 JSON：以下字段都是 JSON 数组（用 String 存 JSON 文本） */
    private String unitCode;
    private String unitName;

    private String washInMonthPlan;
    private String washInDayPlan;

    private String cleanCoalMonthPlan;
    private String cleanCoalDayPlan;
    private String cleanCoalMonthCarPlan;
    private String cleanCoalDayCarPlan;

    private String slackCoalMonthPlan;
    private String slackCoalDayPlan;
    private String slackCoalMonthCarPlan;
    private String slackCoalDayCarPlan;

    /** 通用字段 */
    private String userId;
    private Integer isDeleted;
    private String mineCategory;

    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getPlanMonth() { return planMonth; }
    public void setPlanMonth(Date planMonth) { this.planMonth = planMonth; }

    public Integer getWorkDaysInMonth() { return workDaysInMonth; }
    public void setWorkDaysInMonth(Integer workDaysInMonth) { this.workDaysInMonth = workDaysInMonth; }

    public String getDataSource() { return dataSource; }
    public void setDataSource(String dataSource) { this.dataSource = dataSource; }

    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }

    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public String getWashInMonthPlan() { return washInMonthPlan; }
    public void setWashInMonthPlan(String washInMonthPlan) { this.washInMonthPlan = washInMonthPlan; }

    public String getWashInDayPlan() { return washInDayPlan; }
    public void setWashInDayPlan(String washInDayPlan) { this.washInDayPlan = washInDayPlan; }

    public String getCleanCoalMonthPlan() { return cleanCoalMonthPlan; }
    public void setCleanCoalMonthPlan(String cleanCoalMonthPlan) { this.cleanCoalMonthPlan = cleanCoalMonthPlan; }

    public String getCleanCoalDayPlan() { return cleanCoalDayPlan; }
    public void setCleanCoalDayPlan(String cleanCoalDayPlan) { this.cleanCoalDayPlan = cleanCoalDayPlan; }

    public String getCleanCoalMonthCarPlan() { return cleanCoalMonthCarPlan; }
    public void setCleanCoalMonthCarPlan(String cleanCoalMonthCarPlan) { this.cleanCoalMonthCarPlan = cleanCoalMonthCarPlan; }

    public String getCleanCoalDayCarPlan() { return cleanCoalDayCarPlan; }
    public void setCleanCoalDayCarPlan(String cleanCoalDayCarPlan) { this.cleanCoalDayCarPlan = cleanCoalDayCarPlan; }

    public String getSlackCoalMonthPlan() { return slackCoalMonthPlan; }
    public void setSlackCoalMonthPlan(String slackCoalMonthPlan) { this.slackCoalMonthPlan = slackCoalMonthPlan; }

    public String getSlackCoalDayPlan() { return slackCoalDayPlan; }
    public void setSlackCoalDayPlan(String slackCoalDayPlan) { this.slackCoalDayPlan = slackCoalDayPlan; }

    public String getSlackCoalMonthCarPlan() { return slackCoalMonthCarPlan; }
    public void setSlackCoalMonthCarPlan(String slackCoalMonthCarPlan) { this.slackCoalMonthCarPlan = slackCoalMonthCarPlan; }

    public String getSlackCoalDayCarPlan() { return slackCoalDayCarPlan; }
    public void setSlackCoalDayCarPlan(String slackCoalDayCarPlan) { this.slackCoalDayCarPlan = slackCoalDayCarPlan; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    public String getMineCategory() { return mineCategory; }
    public void setMineCategory(String mineCategory) { this.mineCategory = mineCategory; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
