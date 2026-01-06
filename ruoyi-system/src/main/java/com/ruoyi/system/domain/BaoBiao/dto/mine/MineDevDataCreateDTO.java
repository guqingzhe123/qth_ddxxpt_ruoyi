package com.ruoyi.system.domain.BaoBiao.dto.mine;

import java.io.Serializable;
import java.util.List;

public class MineDevDataCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data_type;      // 开拓/进尺/生产
    private String unit_code;      // 标量
    private String unit_name;      // 标量
    private String record_date;    // 任意可解析，归一化为 yyyy-MM-dd
    private Integer current_shift; // 1/2/3
    private String user_id;
    private String mine_category;

    private List<MineDevDataItem> data_JSON;

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
    public List<MineDevDataItem> getData_JSON() { return data_JSON; }
    public void setData_JSON(List<MineDevDataItem> data_JSON) { this.data_JSON = data_JSON; }
}
