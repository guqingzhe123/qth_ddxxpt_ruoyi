package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 领导带班信息
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkLeaderOnDutyList  {
    private String unitName;
    private String unitCode;
    List<String> one;
    List<String> two;
    List<String> three;
}
