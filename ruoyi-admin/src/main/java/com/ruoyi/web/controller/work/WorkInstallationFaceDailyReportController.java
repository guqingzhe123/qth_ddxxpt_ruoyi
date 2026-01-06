package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.work.WorkInstallationFaceDailyReport;
import com.ruoyi.system.service.work.IWorkInstallationFaceDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 安装面信息日报表
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkInstallationFaceDailyReportController")
public class WorkInstallationFaceDailyReportController extends BaseController {
    @Autowired
    private IWorkInstallationFaceDailyReportService workInstallationFaceDailyReportService;

    /**
     * 查询安装面信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkInstallationFaceDailyReport workInstallationFaceDailyReport) {
        List<WorkInstallationFaceDailyReport> list = workInstallationFaceDailyReportService.listWorkInstallationFaceDailyReport(workInstallationFaceDailyReport);
        return getDataTable(list);
    }


    /**
     * 新增安装面信息
     */
    @Log(title = "安装面信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkInstallationFaceDailyReport> workInstallationFaceDailyReport) {
        return toAjax(workInstallationFaceDailyReportService.saveWorkInstallationFaceDailyReport(workInstallationFaceDailyReport));
    }

}

