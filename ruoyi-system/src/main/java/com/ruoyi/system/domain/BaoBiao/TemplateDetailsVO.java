package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("TemplateDetailsTplVO")  // ✅ 给这个类一个唯一别名，避免和另一个同名类冲突
@Data
public class TemplateDetailsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;               // 详情ID
    private String name;           // 详情名称
    private Long mineTemplateId;   // 模板ID
    private String Code;       // 模板编码
    private String templateName;   // 模板名称（Mine_template.Name）
    private String unitCode;       // 模板单位编码（Mine_template.unitCode）

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getMineTemplateId() { return mineTemplateId; }
    public void setMineTemplateId(Long mineTemplateId) { this.mineTemplateId = mineTemplateId; }

    public String getCode() { return Code; }
    public void setCode(String code) { this.Code = code; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
}
