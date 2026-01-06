package com.ruoyi.system.domain.BaoBiao;

import java.io.Serializable;

public class TemplateDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;  // 类型与数据库列匹配
    private Long mineTemplateId; // 对应列 Mine_templateID
    private String name;         // 对应列 Name

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getMineTemplateId() { return mineTemplateId; }
    public void setMineTemplateId(Long mineTemplateId) { this.mineTemplateId = mineTemplateId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
