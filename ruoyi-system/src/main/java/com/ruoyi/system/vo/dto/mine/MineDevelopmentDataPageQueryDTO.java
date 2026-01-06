package com.ruoyi.system.vo.dto.mine;

import com.ruoyi.system.vo.dto.base.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class MineDevelopmentDataPageQueryDTO extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dataType;
    private Integer currentShift; // 可选过滤
}
