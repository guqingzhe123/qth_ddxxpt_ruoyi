package com.ruoyi.system.domain.BaoBiao.dto.cwp;

public class CwpPageQueryDTO {
    private String unit_name;
    private String unit_code;
    private String user_id;
    private String mine_category;
    private Integer is_deleted;

    private String production_date;       // = 某天
    private String production_date_from;  // >=
    private String production_date_to;    // <=

    private Integer pageNum;
    private Integer pageSize;

    // getters/setters
    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public Integer getIs_deleted() { return is_deleted; }
    public void setIs_deleted(Integer is_deleted) { this.is_deleted = is_deleted; }
    public String getProduction_date() { return production_date; }
    public void setProduction_date(String production_date) { this.production_date = production_date; }
    public String getProduction_date_from() { return production_date_from; }
    public void setProduction_date_from(String production_date_from) { this.production_date_from = production_date_from; }
    public String getProduction_date_to() { return production_date_to; }
    public void setProduction_date_to(String production_date_to) { this.production_date_to = production_date_to; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
