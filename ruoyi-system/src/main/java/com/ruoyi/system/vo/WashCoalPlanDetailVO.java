package com.ruoyi.system.vo;

import com.ruoyi.system.dto.WashCoalPlanItemDTO;
import java.util.Date;
import java.util.List;

/**
 * 详情返回：将列式 JSON 再拼回 data_JSON 数组1
 */
public class WashCoalPlanDetailVO {
    private Long id;
    private String data_source;
    private String plan_month; // 返回 yyyy-MM
    private Integer work_days_in_month;
    private String user_id;
    private String mine_category;
    private List<WashCoalPlanItemDTO> data_JSON;
    private Date create_time;
    private Date update_time;

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

    public Date getCreate_time() { return create_time; }
    public void setCreate_time(Date create_time) { this.create_time = create_time; }

    public Date getUpdate_time() { return update_time; }
    public void setUpdate_time(Date update_time) { this.update_time = update_time; }
}
