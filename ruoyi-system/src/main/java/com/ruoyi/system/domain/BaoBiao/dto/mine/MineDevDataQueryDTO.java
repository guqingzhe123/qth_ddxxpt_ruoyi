package com.ruoyi.system.domain.BaoBiao.dto.mine;

public class MineDevDataQueryDTO {
    private String record_date;
    private String data_type;
    private String unit_code;
    private String mine_category;
    private String user_id;
    private Integer is_deleted;

    private Integer pageNum;
    private Integer pageSize;

    public String getRecord_date() { return record_date; }
    public void setRecord_date(String record_date) { this.record_date = record_date; }
    public String getData_type() { return data_type; }
    public void setData_type(String data_type) { this.data_type = data_type; }
    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
