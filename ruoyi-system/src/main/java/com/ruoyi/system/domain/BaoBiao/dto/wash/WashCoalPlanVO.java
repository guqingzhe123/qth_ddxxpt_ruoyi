package com.ruoyi.system.domain.BaoBiao.dto.wash;

import com.ruoyi.system.domain.BaoBiao.dto.wash.WashCoalPlanUnitItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WashCoalPlanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date plan_month;
    private Integer work_days_in_month;
    private String user_id;
    private String mine_category;

    private List<WashCoalPlanUnitItem> data_JSON;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getPlan_month() { return plan_month; }
    public void setPlan_month(Date plan_month) { this.plan_month = plan_month; }
    public Integer getWork_days_in_month() { return work_days_in_month; }
    public void setWork_days_in_month(Integer work_days_in_month) { this.work_days_in_month = work_days_in_month; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public List<WashCoalPlanUnitItem> getData_JSON() { return data_JSON; }
    public void setData_JSON(List<WashCoalPlanUnitItem> data_JSON) { this.data_JSON = data_JSON; }
}
