package com.ruoyi.system.domain.BaoBiao.dto.cpi;

public class CpiPageQueryDTO {
    // 主表过滤
    private String user_id;
    private String mine_category;
    private Integer is_deleted;

    // 子表过滤（可选）
    private String unit_code;
    private String unit_name;
    private String record_date;      // 精确日（与 from/to 二选一）
    private String record_date_from;
    private String record_date_to;

    // 分页
    private Integer pageNum;
    private Integer pageSize;

    // getters/setters
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }
    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }
    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
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
