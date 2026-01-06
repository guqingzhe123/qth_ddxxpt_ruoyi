package com.ruoyi.system.domain.BaoBiao.dto.mine;

public class MinePlanQueryDTO {
    private String plan_month;     // 按月归一
    private String mine_category;
    private String user_id;
    private String plan_type;      // 可选过滤
    private Integer is_deleted;    // 默认 0

    private Integer pageNum;       // RuoYi 分页
    private Integer pageSize;

    public String getPlan_month() { return plan_month; }
    public void setPlan_month(String plan_month) { this.plan_month = plan_month; }

    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getPlan_type() { return plan_type; }
    public void setPlan_type(String plan_type) { this.plan_type = plan_type; }

    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }

    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
