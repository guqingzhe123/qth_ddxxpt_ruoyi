package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.LongmeiProductionSalesStats;
import com.ruoyi.system.service.ILongmeiProductionSalesStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 龙煤集团各分公司生产外销统计Controller
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/stats")
public class LongmeiProductionSalesStatsController extends BaseController {
    @Autowired
    private ILongmeiProductionSalesStatsService longmeiProductionSalesStatsService;

    /**
     * 查询龙煤集团各分公司生产外销统计列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(LongmeiProductionSalesStats longmeiProductionSalesStats) {
        List<LongmeiProductionSalesStats> list = longmeiProductionSalesStatsService.listLongmeiProductionSalesStats(longmeiProductionSalesStats);
        return getDataTable(list);
    }
    /**
     * 查询龙煤集团各分公司生产外销统计列表
     */
    @GetMapping("/Alllist")
    public TableDataInfo<BaseEntity> Alllist(String statsDate) {
        List<LongmeiProductionSalesStats> list = longmeiProductionSalesStatsService.Alllist(statsDate);
        return getDataTable(list);
    }
    /**
     * 导出龙煤集团各分公司生产外销统计列表
     */
    @Log(title = "龙煤集团各分公司生产外销统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LongmeiProductionSalesStats longmeiProductionSalesStats) {
        List<LongmeiProductionSalesStats> list = longmeiProductionSalesStatsService.listLongmeiProductionSalesStats(longmeiProductionSalesStats);
        ExcelUtil<LongmeiProductionSalesStats> util = new ExcelUtil<LongmeiProductionSalesStats>(LongmeiProductionSalesStats.class);
        util.exportExcel(response, list, "龙煤集团各分公司生产外销统计数据");
    }

    /**
     * 获取龙煤集团各分公司生产外销统计详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(longmeiProductionSalesStatsService.getLongmeiProductionSalesStatsById(id));
    }

    /**
     * 新增龙煤集团各分公司生产外销统计
     */
    @Log(title = "龙煤集团各分公司生产外销统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<LongmeiProductionSalesStats> longmeiProductionSalesStats) {
        return toAjax(longmeiProductionSalesStatsService.saveLongmeiProductionSalesStats(longmeiProductionSalesStats));
    }

    /**
     * 修改龙煤集团各分公司生产外销统计
     */
    @Log(title = "龙煤集团各分公司生产外销统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LongmeiProductionSalesStats longmeiProductionSalesStats) {
        return toAjax(longmeiProductionSalesStatsService.updateLongmeiProductionSalesStats(longmeiProductionSalesStats));
    }

    /**
     * 删除龙煤集团各分公司生产外销统计
     */
    @Log(title = "龙煤集团各分公司生产外销统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(longmeiProductionSalesStatsService.deleteLongmeiProductionSalesStatsByIds(ids));
    }
}
