package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CokeSalesPlan;
import com.ruoyi.system.service.ICokeSalesPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【焦炭销售计划】Controller
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/plan")
public class CokeSalesPlanController extends BaseController {
    @Autowired
    private ICokeSalesPlanService cokeSalesPlanService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(CokeSalesPlan cokeSalesPlan) {
        startPage();
        List<CokeSalesPlan> list = cokeSalesPlanService.listCokeSalesPlan(cokeSalesPlan);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CokeSalesPlan cokeSalesPlan) {
        List<CokeSalesPlan> list = cokeSalesPlanService.listCokeSalesPlan(cokeSalesPlan);
        ExcelUtil<CokeSalesPlan> util = new ExcelUtil<CokeSalesPlan>(CokeSalesPlan.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(cokeSalesPlanService.getCokeSalesPlanById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CokeSalesPlan cokeSalesPlan) {
        return toAjax(cokeSalesPlanService.saveCokeSalesPlan(cokeSalesPlan));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CokeSalesPlan cokeSalesPlan) {
        return toAjax(cokeSalesPlanService.updateCokeSalesPlan(cokeSalesPlan));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cokeSalesPlanService.deleteCokeSalesPlanByIds(ids));
    }
}
