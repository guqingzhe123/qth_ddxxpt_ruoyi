package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.RawCoalToDailyReport;
import com.ruoyi.system.service.IRawCoalToDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 原煤去向月报Controller
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/report")
public class RawCoalToDailyReportController extends BaseController {
    @Autowired
    private IRawCoalToDailyReportService rawCoalToDailyReportService;

    /**
     * 查询原煤去向月报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(RawCoalToDailyReport rawCoalToDailyReport) {
        startPage();
        List<RawCoalToDailyReport> list = rawCoalToDailyReportService.listRawCoalToDailyReport(rawCoalToDailyReport);
        return getDataTable(list);
    }

    /**
     * 导出原煤去向月报列表
     */
    @Log(title = "原煤去向月报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RawCoalToDailyReport rawCoalToDailyReport) {
        List<RawCoalToDailyReport> list = rawCoalToDailyReportService.listRawCoalToDailyReport(rawCoalToDailyReport);
        ExcelUtil<RawCoalToDailyReport> util = new ExcelUtil<RawCoalToDailyReport>(RawCoalToDailyReport.class);
        util.exportExcel(response, list, "原煤去向月报数据");
    }

    /**
     * 获取原煤去向月报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(rawCoalToDailyReportService.getRawCoalToDailyReportById(id));
    }

    /**
     * 新增原煤去向月报
     */
    @Log(title = "原煤去向月报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<RawCoalToDailyReport> rawCoalToDailyReport) {
        return toAjax(rawCoalToDailyReportService.saveRawCoalToDailyReport(rawCoalToDailyReport));
    }

    /**
     * 修改原煤去向月报
     */
    @Log(title = "原煤去向月报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RawCoalToDailyReport rawCoalToDailyReport) {
        return toAjax(rawCoalToDailyReportService.updateRawCoalToDailyReport(rawCoalToDailyReport));
    }

    /**
     * 删除原煤去向月报
     */
    @Log(title = "原煤去向月报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(rawCoalToDailyReportService.deleteRawCoalToDailyReportByIds(ids));
    }
}
