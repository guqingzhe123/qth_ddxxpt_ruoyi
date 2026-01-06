package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DispatchDailyReportOtherData;
import com.ruoyi.system.service.IDispatchDailyReportOtherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 调度日报其他数据Controller
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/data")
public class DispatchDailyReportOtherDataController extends BaseController {
    @Autowired
    private IDispatchDailyReportOtherDataService dispatchDailyReportOtherDataService;

    /**
     * 查询调度日报其他数据列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        startPage();
        List<DispatchDailyReportOtherData> list = dispatchDailyReportOtherDataService.listDispatchDailyReportOtherData(dispatchDailyReportOtherData);
        return getDataTable(list);
    }

    /**
     * 导出调度日报其他数据列表
     */
    @Log(title = "调度日报其他数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        List<DispatchDailyReportOtherData> list = dispatchDailyReportOtherDataService.listDispatchDailyReportOtherData(dispatchDailyReportOtherData);
        ExcelUtil<DispatchDailyReportOtherData> util = new ExcelUtil<DispatchDailyReportOtherData>(DispatchDailyReportOtherData.class);
        util.exportExcel(response, list, "调度日报其他数据数据");
    }

    /**
     * 获取调度日报其他数据详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(dispatchDailyReportOtherDataService.getDispatchDailyReportOtherDataById(id));
    }

    /**
     * 新增调度日报其他数据
     */
    @Log(title = "调度日报其他数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        return toAjax(dispatchDailyReportOtherDataService.saveDispatchDailyReportOtherData(dispatchDailyReportOtherData));
    }

    /**
     * 修改调度日报其他数据
     */
    @Log(title = "调度日报其他数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DispatchDailyReportOtherData dispatchDailyReportOtherData) {
        return toAjax(dispatchDailyReportOtherDataService.updateDispatchDailyReportOtherData(dispatchDailyReportOtherData));
    }

    /**
     * 删除调度日报其他数据
     */
    @Log(title = "调度日报其他数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dispatchDailyReportOtherDataService.deleteDispatchDailyReportOtherDataByIds(ids));
    }
}
