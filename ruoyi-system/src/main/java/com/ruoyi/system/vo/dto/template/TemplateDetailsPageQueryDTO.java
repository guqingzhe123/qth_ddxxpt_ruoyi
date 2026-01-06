package com.ruoyi.system.vo.dto.template;

import com.ruoyi.system.vo.dto.base.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class TemplateDetailsPageQueryDTO extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 必传：按模板ID查询详情 */
    private Long mineTemplateID;
}
