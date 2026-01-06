package com.ruoyi.system.vo.dto.template;

import lombok.Data;

import java.io.Serializable;

@Data
public class TemplateDetailsUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long mineTemplateID;
    private String name;
}
