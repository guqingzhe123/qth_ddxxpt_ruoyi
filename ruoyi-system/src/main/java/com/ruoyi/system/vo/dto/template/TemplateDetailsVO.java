package com.ruoyi.system.vo.dto.template;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TemplateDetailsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long mineTemplateID;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
