package com.ruoyi.system.domain.BaoBiao.dto.mine;

import java.io.Serializable;
import java.math.BigDecimal;

public class MineDevDataItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_name;
    private BigDecimal output;
    private Integer attendance_count;
    private Integer total_down_count;
    private Integer mining_down_count;
    private Integer driving_down_count;
    private Integer other_down_count;
    private Integer total_up_count;
    private Integer mining_up_count;
    private Integer driving_up_count;
    private Integer other_up_count;

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }
    public BigDecimal getOutput() { return output; }
    public void setOutput(BigDecimal output) { this.output = output; }
    public Integer getAttendance_count() { return attendance_count; }
    public void setAttendance_count(Integer attendance_count) { this.attendance_count = attendance_count; }
    public Integer getTotal_down_count() { return total_down_count; }
    public void setTotal_down_count(Integer total_down_count) { this.total_down_count = total_down_count; }
    public Integer getMining_down_count() { return mining_down_count; }
    public void setMining_down_count(Integer mining_down_count) { this.mining_down_count = mining_down_count; }
    public Integer getDriving_down_count() { return driving_down_count; }
    public void setDriving_down_count(Integer driving_down_count) { this.driving_down_count = driving_down_count; }
    public Integer getOther_down_count() { return other_down_count; }
    public void setOther_down_count(Integer other_down_count) { this.other_down_count = other_down_count; }
    public Integer getTotal_up_count() { return total_up_count; }
    public void setTotal_up_count(Integer total_up_count) { this.total_up_count = total_up_count; }
    public Integer getMining_up_count() { return mining_up_count; }
    public void setMining_up_count(Integer mining_up_count) { this.mining_up_count = mining_up_count; }
    public Integer getDriving_up_count() { return driving_up_count; }
    public void setDriving_up_count(Integer driving_up_count) { this.driving_up_count = driving_up_count; }
    public Integer getOther_up_count() { return other_up_count; }
    public void setOther_up_count(Integer other_up_count) { this.other_up_count = other_up_count; }
}
