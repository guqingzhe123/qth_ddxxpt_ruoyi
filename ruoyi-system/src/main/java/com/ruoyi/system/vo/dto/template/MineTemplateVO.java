package com.ruoyi.system.vo.dto.template;

import com.ruoyi.system.domain.BaoBiao.TemplateDetailsVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MineTemplateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unitCode;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /** 可选：带出详情 */
    private List<TemplateDetailsVO> details;
}
