package com.ruoyi.system.domain.BaoBiao.vo.mine;

import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanDataItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MinePlanJsonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String plan_type;
    private String unit_code;         // 标量
    private String unit_name;         // 标量
    private Date plan_month;          // 当月1日
    private Integer work_days_in_month;

    private String user_id;
    private String mine_category;

    private List<MinePlanDataItem> data_JSON;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlan_type() { return plan_type; }
    public void setPlan_type(String plan_type) { this.plan_type = plan_type; }

    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public Date getPlan_month() { return plan_month; }
    public void setPlan_month(Date plan_month) { this.plan_month = plan_month; }

    public Integer getWork_days_in_month() { return work_days_in_month; }
    public void setWork_days_in_month(Integer work_days_in_month) { this.work_days_in_month = work_days_in_month; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }

    public List<MinePlanDataItem> getData_JSON() { return data_JSON; }
    public void setData_JSON(List<MinePlanDataItem> data_JSON) { this.data_JSON = data_JSON; }
}
