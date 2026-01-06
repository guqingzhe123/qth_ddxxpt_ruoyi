package com.ruoyi.system.domain.BaoBiao.vo.cwp;

import com.ruoyi.system.domain.BaoBiao.dto.cwp.CwpMetricsDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CwpVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date production_date;
    private String unit_code;
    private String unit_name;
    private String user_id;
    private String mine_category;
    private Date create_time;
    private Date update_time;

    private List<CwpMetricsDTO> data_JSON;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getProduction_date() { return production_date; }
    public void setProduction_date(Date production_date) { this.production_date = production_date; }
    public String getUnit_code() { return unit_code; }
    public void setUnit_code(String unit_code) { this.unit_code = unit_code; }
    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public String getMine_category() { return mine_category; }
    public void setMine_category(String mine_category) { this.mine_category = mine_category; }
    public Date getCreate_time() { return create_time; }
    public void setCreate_time(Date create_time) { this.create_time = create_time; }
    public Date getUpdate_time() { return update_time; }
    public void setUpdate_time(Date update_time) { this.update_time = update_time; }
    public List<CwpMetricsDTO> getData_JSON() { return data_JSON; }
    public void setData_JSON(List<CwpMetricsDTO> data_JSON) { this.data_JSON = data_JSON; }
}
