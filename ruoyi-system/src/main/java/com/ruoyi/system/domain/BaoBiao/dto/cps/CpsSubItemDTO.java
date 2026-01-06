package com.ruoyi.system.domain.BaoBiao.dto.cps;

import java.io.Serializable;
import java.math.BigDecimal;

public class CpsSubItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String record_date;     // "yyyy-MM-dd"
    private BigDecimal clean_coal;
    private BigDecimal slack_coal;
    private BigDecimal lump_coal;
    private BigDecimal raw_coal;

    // getters/setters
    public String getRecord_date() { return record_date; }
    public void setRecord_date(String record_date) { this.record_date = record_date; }
    public BigDecimal getClean_coal() { return clean_coal; }
    public void setClean_coal(BigDecimal clean_coal) { this.clean_coal = clean_coal; }
    public BigDecimal getSlack_coal() { return slack_coal; }
    public void setSlack_coal(BigDecimal slack_coal) { this.slack_coal = slack_coal; }
    public BigDecimal getLump_coal() { return lump_coal; }
    public void setLump_coal(BigDecimal lump_coal) { this.lump_coal = lump_coal; }
    public BigDecimal getRaw_coal() { return raw_coal; }
    public void setRaw_coal(BigDecimal raw_coal) { this.raw_coal = raw_coal; }
}
