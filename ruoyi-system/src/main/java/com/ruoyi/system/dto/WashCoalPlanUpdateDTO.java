package com.ruoyi.system.dto;

import java.util.List;

/** 整单更新：可部分字段（null 不改），可携带 data_JSON 全量替换 */
public class WashCoalPlanUpdateDTO {
    private Long id;

    private String data_source;
    private String plan_month;            // yyyy-MM 或完整时间
    private Integer work_days_in_month;
    private String user_id;
    private String mine_category;

    private List<WashCoalPlanItemDTO> data_JSON;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getData_source() { return data_source; }
    public void setData_source(String data_source) { this.data_source = data_source; }

    public String getPlan_month() { return plan_month; }
    public void setPlan_month(String plan_month) { this.plan_month = plan_month; }

    public Integer getWork_days_in_month() { return work_days_in_month; }
    public void setWork_days_in_month(Integer work_days_in_month) { this.work_days_in_month = work_days_in_month; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }

    public List<WashCoalPlanItemDTO> getData_JSON() { return data_JSON; }
    public void setData_JSON(List<WashCoalPlanItemDTO> data_JSON) { this.data_JSON = data_JSON; }
}
