package com.ruoyi.system.dto;

import java.util.List;

/**
 * 提交的整包数据（包含 data_JSON 列表）1
 */
public class WashCoalPlanSaveDTO {
    private String data_source;            // 数据来源
    private String plan_month;             // "2025-11-02 10:30:00" 或 "2025-11"
    private Integer work_days_in_month;    // 本月工作天数
    private String user_id;                // 用户ID（示例为数值，转字符串存）
    private String mine_category;          // 矿类别

    private List<WashCoalPlanItemDTO> data_JSON;

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
