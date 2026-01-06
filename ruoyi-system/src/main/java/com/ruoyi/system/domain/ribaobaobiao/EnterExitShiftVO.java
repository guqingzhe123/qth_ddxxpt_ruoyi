package com.ruoyi.system.domain.ribaobaobiao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EnterExitShiftVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String unit_name;

    @JsonProperty("一班")
    private Stats class1;
    @JsonProperty("二班")
    private Stats class2;
    @JsonProperty("三班")
    private Stats class3;

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public Stats getClass1() { return class1; }
    public void setClass1(Stats class1) { this.class1 = class1; }

    public Stats getClass2() { return class2; }
    public void setClass2(Stats class2) { this.class2 = class2; }

    public Stats getClass3() { return class3; }
    public void setClass3(Stats class3) { this.class3 = class3; }

    public static class Stats implements Serializable {
        private static final long serialVersionUID = 1L;

        // 全部字符串，保证与示例一致（"1"）
        private String total_down_count;
        private String mining_down_count;
        private String driving_down_count;
        private String other_down_count;
        private String total_up_count;
        private String mining_up_count;
        private String driving_up_count;
        private String other_up_count;

        public String getTotal_down_count() { return total_down_count; }
        public void setTotal_down_count(String v) { this.total_down_count = v; }
        public String getMining_down_count() { return mining_down_count; }
        public void setMining_down_count(String v) { this.mining_down_count = v; }
        public String getDriving_down_count() { return driving_down_count; }
        public void setDriving_down_count(String v) { this.driving_down_count = v; }
        public String getOther_down_count() { return other_down_count; }
        public void setOther_down_count(String v) { this.other_down_count = v; }
        public String getTotal_up_count() { return total_up_count; }
        public void setTotal_up_count(String v) { this.total_up_count = v; }
        public String getMining_up_count() { return mining_up_count; }
        public void setMining_up_count(String v) { this.mining_up_count = v; }
        public String getDriving_up_count() { return driving_up_count; }
        public void setDriving_up_count(String v) { this.driving_up_count = v; }
        public String getOther_up_count() { return other_up_count; }
        public void setOther_up_count(String v) { this.other_up_count = v; }
    }
}
