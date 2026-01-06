package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ComprehensiveProductionStats;
import com.ruoyi.system.service.IComprehensiveProductionStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 外运统计Controller
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/stats/Comprehensive")
public class ComprehensiveProductionStatsController extends BaseController {
    @Autowired
    private IComprehensiveProductionStatsService comprehensiveProductionStatsService;

    /**
     * 查询外运统计列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(ComprehensiveProductionStats comprehensiveProductionStats) {
        startPage();
        List<ComprehensiveProductionStats> list = comprehensiveProductionStatsService.listComprehensiveProductionStats(comprehensiveProductionStats);
        return getDataTable(list);
    }

    /**
     * 导出外运统计列表
     */
    @Log(title = "外运统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ComprehensiveProductionStats comprehensiveProductionStats) {
        List<ComprehensiveProductionStats> list = comprehensiveProductionStatsService.listComprehensiveProductionStats(comprehensiveProductionStats);
        ExcelUtil<ComprehensiveProductionStats> util = new ExcelUtil<ComprehensiveProductionStats>(ComprehensiveProductionStats.class);
        util.exportExcel(response, list, "外运统计数据");
    }

    /**
     * 获取外运统计详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(comprehensiveProductionStatsService.getComprehensiveProductionStatsById(id));
    }

    /**
     * 新增外运统计
     */
    @Log(title = "外运统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComprehensiveProductionStats comprehensiveProductionStats) {
        return toAjax(comprehensiveProductionStatsService.saveComprehensiveProductionStats(comprehensiveProductionStats));
    }

    /**
     * 修改外运统计
     */
    @Log(title = "外运统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComprehensiveProductionStats comprehensiveProductionStats) {
        return toAjax(comprehensiveProductionStatsService.updateComprehensiveProductionStats(comprehensiveProductionStats));
    }

    /**
     * 删除外运统计
     */
    @Log(title = "外运统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(comprehensiveProductionStatsService.deleteComprehensiveProductionStatsByIds(ids));
    }
}
