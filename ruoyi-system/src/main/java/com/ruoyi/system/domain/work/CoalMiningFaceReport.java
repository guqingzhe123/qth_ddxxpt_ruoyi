package com.ruoyi.system.domain.work;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class CoalMiningFaceReport {
    /** 单位 */
    @Excel(name = "单位")
    private String unit;
    /** 队组号 */
    @Excel(name = "上报日期")
    private Date reportDate;

    /** 巷修面日报 */
    List<WorkRoadwayRepairFaceDailyReport> workRoadwayRepairFaceDailyReport ;
    /** 安装面信息 */
    List<WorkInstallationFaceDailyReport> workInstallationFaceDailyReport;
    /** 回撤面信息 */
    List<WorkWithdrawalFaceDailyReport> workWithdrawalFaceDailyReport;
}
