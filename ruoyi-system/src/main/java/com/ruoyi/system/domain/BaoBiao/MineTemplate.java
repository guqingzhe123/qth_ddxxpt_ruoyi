package com.ruoyi.system.domain.BaoBiao;

import java.io.Serializable;

public class MineTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unitCode;
    private String name; // 对应列 Name
    private String Userid; // 对应列 Name

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserid() { return Userid; }
    public void setUserid(String Userid) { this.Userid = Userid; }
}
