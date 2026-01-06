package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.service.work.IWorkProductionDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 生产信息日报Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkProductionDailyReportController")
public class WorkProductionDailyReportController extends BaseController {
    @Autowired
    private IWorkProductionDailyReportService workProductionDailyReportService;

    /**
     * 查询生产信息日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkProductionDailyReport workProductionDailyReport) {
        startPage();
        List<WorkProductionDailyReport> list = workProductionDailyReportService.listWorkProductionDailyReport(workProductionDailyReport);
        return getDataTable(list);
    }

    /**
     * 导出生产信息日报列表
     */
    @Log(title = "生产信息日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkProductionDailyReport workProductionDailyReport) {
        List<WorkProductionDailyReport> list = workProductionDailyReportService.listWorkProductionDailyReport(workProductionDailyReport);
        ExcelUtil<WorkProductionDailyReport> util = new ExcelUtil<WorkProductionDailyReport>(WorkProductionDailyReport.class);
        util.exportExcel(response, list, "生产信息日报数据");
    }

    /**
     * 获取生产信息日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workProductionDailyReportService.getWorkProductionDailyReportById(id));
    }

    /**
     * 新增生产信息日报
     */
    @Log(title = "生产信息日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkProductionDailyReport workProductionDailyReport) {
        return toAjax(workProductionDailyReportService.saveWorkProductionDailyReport(workProductionDailyReport));
    }

    /**
     * 修改生产信息日报
     */
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @Log(title = "生产信息日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkProductionDailyReport workProductionDailyReport) {
        return toAjax(workProductionDailyReportService.updateWorkProductionDailyReport(workProductionDailyReport));
    }

    /**
     * 删除生产信息日报
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "生产信息日报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workProductionDailyReportService.deleteWorkProductionDailyReportByIds(ids));
    }
}
