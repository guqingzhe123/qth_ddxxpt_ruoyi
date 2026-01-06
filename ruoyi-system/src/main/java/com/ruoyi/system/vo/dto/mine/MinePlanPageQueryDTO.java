package com.ruoyi.system.vo.dto.mine;

import com.ruoyi.system.vo.dto.base.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class MinePlanPageQueryDTO extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String planType;
}
