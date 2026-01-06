package com.ruoyi.system.domain.BaoBiao.dto.dev;

public class MddPageQueryDTO {
    private String data_type;
    private String unit_code;
    private String unit_name;
    private String record_date;      // yyyy-MM-dd，可选
    private Integer current_shift;
    private String user_id;
    private String mine_category;
    private Integer is_deleted;

    private Integer pageNum;
    private Integer pageSize;

    // getters/setters
    public String getData_type() { return data_type; }
    public void setData_type(String data_type) { this.data_type = data_type; }
    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }
    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
    public String getRecord_date() { return record_date; }
    public void setRecord_date(String record_date) { this.record_date = record_date; }
    public Integer getCurrent_shift() { return current_shift; }
    public void setCurrent_shift(Integer current_shift) { this.current_shift = current_shift; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
