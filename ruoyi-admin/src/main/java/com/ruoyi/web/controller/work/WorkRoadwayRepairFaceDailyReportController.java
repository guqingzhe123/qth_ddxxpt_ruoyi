package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.work.WorkRoadwayRepairFaceDailyReport;
import com.ruoyi.system.service.work.IWorkRoadwayRepairFaceDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 巷修面日报Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkRoadwayRepairFaceDailyReportController")
public class WorkRoadwayRepairFaceDailyReportController extends BaseController {
    @Autowired
    private IWorkRoadwayRepairFaceDailyReportService workRoadwayRepairFaceDailyReportService;

    /**
     * 查询巷修面日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport) {
        List<WorkRoadwayRepairFaceDailyReport> list = workRoadwayRepairFaceDailyReportService.listWorkRoadwayRepairFaceDailyReport(workRoadwayRepairFaceDailyReport);
        return getDataTable(list);
    }
    /**
     * 新增巷修面配置日报
     */
    @Log(title = "巷修面日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkRoadwayRepairFaceDailyReport> workRoadwayRepairFaceDailyReport) {
        return toAjax(workRoadwayRepairFaceDailyReportService.saveWorkRoadwayRepairFaceDailyReport(workRoadwayRepairFaceDailyReport));
    }

}
