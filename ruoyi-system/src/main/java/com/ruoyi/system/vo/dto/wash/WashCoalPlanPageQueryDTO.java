package com.ruoyi.system.vo.dto.wash;

import com.ruoyi.system.vo.dto.base.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询入参（含常见过滤项）
 */
@Data
public class WashCoalPlanPageQueryDTO extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 过滤：计划类型暂不需要；可用 mineCategory/userId/unitCode/beginDate/endDate */
}
