package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 洗煤数据填报对象 work_coal_washing_report
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@Data
public class WorkCoalWashingReportList extends BaseEntity {
    /** 备注 */
    @Excel(name = "备注")
    private String remarks;
    List<WorkCoalWashingReport> list;
    List<WorkCoalWashingReport> monthList;


}

