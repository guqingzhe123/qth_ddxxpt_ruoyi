package com.ruoyi.system.domain.ribaobaobiao;

import java.io.Serializable;

/** 入参只需要一天：record_date = yyyy-MM-dd（兼容 yyyy-M-d） */
public class EnterExitQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String record_date;

    public String getRecord_date() { return record_date; }
    public void setRecord_date(String record_date) { this.record_date = record_date; }
}
