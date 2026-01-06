package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.SafetyInfluencingFactors;
import com.ruoyi.system.service.work.ISafetyInfluencingFactorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 公司各单位影响安全生产因素（原因）Controller
 *
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/factors")
public class SafetyInfluencingFactorsController extends BaseController {
    @Autowired
    private ISafetyInfluencingFactorsService safetyInfluencingFactorsService;

    /**
     * 查询公司各单位影响安全生产因素（原因）列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(SafetyInfluencingFactors safetyInfluencingFactors) {
        List<SafetyInfluencingFactors> list = safetyInfluencingFactorsService.listSafetyInfluencingFactors(safetyInfluencingFactors);
        return getDataTable(list);
    }

    /**
     * 导出公司各单位影响安全生产因素（原因）列表
     */
    @Log(title = "公司各单位影响安全生产因素（原因）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyInfluencingFactors safetyInfluencingFactors) {
        List<SafetyInfluencingFactors> list = safetyInfluencingFactorsService.listSafetyInfluencingFactors(safetyInfluencingFactors);
        ExcelUtil<SafetyInfluencingFactors> util = new ExcelUtil<SafetyInfluencingFactors>(SafetyInfluencingFactors.class);
        util.exportExcel(response, list, "公司各单位影响安全生产因素（原因）数据");
    }

    /**
     * 获取公司各单位影响安全生产因素（原因）详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(safetyInfluencingFactorsService.getSafetyInfluencingFactorsById(id));
    }

    /**
     * 新增公司各单位影响安全生产因素（原因）
     */
    @Log(title = "公司各单位影响安全生产因素（原因）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyInfluencingFactors safetyInfluencingFactors) {
        SafetyInfluencingFactors safety=new SafetyInfluencingFactors();
        safety.setUnitCode(safetyInfluencingFactors.getUnitCode());
        safety.setRecordDate(safetyInfluencingFactors.getRecordDate());
        List<SafetyInfluencingFactors> list = safetyInfluencingFactorsService.listSafetyInfluencingFactors(safety);
        if(list.size()>0){
            return error("保存过数据");
        }
        else {
            return toAjax(safetyInfluencingFactorsService.saveSafetyInfluencingFactors(safetyInfluencingFactors));
        }
    }

    /**
     * 修改公司各单位影响安全生产因素（原因）
     */
    @Log(title = "公司各单位影响安全生产因素（原因）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyInfluencingFactors safetyInfluencingFactors) {
        return toAjax(safetyInfluencingFactorsService.updateSafetyInfluencingFactors(safetyInfluencingFactors));
    }

    /**
     * 删除公司各单位影响安全生产因素（原因）
     */
    @Log(title = "公司各单位影响安全生产因素（原因）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(safetyInfluencingFactorsService.deleteSafetyInfluencingFactorsByIds(ids));
    }
}
