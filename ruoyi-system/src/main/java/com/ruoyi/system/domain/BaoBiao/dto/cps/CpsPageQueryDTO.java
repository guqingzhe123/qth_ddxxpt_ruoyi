package com.ruoyi.system.domain.BaoBiao.dto.cps;

public class CpsPageQueryDTO {
    // 主表过滤
    private String unit_name;
    private String user_id;
    private String mine_category;
    private Integer is_deleted;
    private String record_time_from; // yyyy-MM-dd HH:mm:ss  (可选)
    private String record_time_to;

    // 子表过滤（按日）
    private String record_date;      // yyyy-MM-dd
    private String record_date_from;
    private String record_date_to;

    // 分页
    private Integer pageNum;
    private Integer pageSize;

    // getters/setters
    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }
    public String getRecord_time_from() { return record_time_from; }
    public void setRecord_time_from(String record_time_from) { this.record_time_from = record_time_from; }
    public String getRecord_time_to() { return record_time_to; }
    public void setRecord_time_to(String record_time_to) { this.record_time_to = record_time_to; }
    public String getRecord_date() { return record_date; }
    public void setRecord_date(String record_date) { this.record_date = record_date; }
    public String getRecord_date_from() { return record_date_from; }
    public void setRecord_date_from(String record_date_from) { this.record_date_from = record_date_from; }
    public String getRecord_date_to() { return record_date_to; }
    public void setRecord_date_to(String record_date_to) { this.record_date_to = record_date_to; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
