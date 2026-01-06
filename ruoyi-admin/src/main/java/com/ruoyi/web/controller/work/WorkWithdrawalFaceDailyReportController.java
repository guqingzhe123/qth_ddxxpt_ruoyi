package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.work.WorkWithdrawalFaceDailyReport;
import com.ruoyi.system.service.work.IWorkWithdrawalFaceDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回撤面日报Controller
 * WorkProductionFaceDailyReportController
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkWithdrawalFaceDailyReportController")
public class WorkWithdrawalFaceDailyReportController extends BaseController {
    @Autowired
    private IWorkWithdrawalFaceDailyReportService workWithdrawalFaceDailyReportService;

    /**
     * 查询回撤面日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport) {
        List<WorkWithdrawalFaceDailyReport> list = workWithdrawalFaceDailyReportService.listWorkWithdrawalFaceDailyReport(workWithdrawalFaceDailyReport);
        return getDataTable(list);
    }

    /**
     * 新增回撤面日报
     */
    @Log(title = "回撤面日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkWithdrawalFaceDailyReport> workWithdrawalFaceDailyReport) {
        return toAjax(workWithdrawalFaceDailyReportService.saveWorkWithdrawalFaceDailyReport(workWithdrawalFaceDailyReport));
    }

}
