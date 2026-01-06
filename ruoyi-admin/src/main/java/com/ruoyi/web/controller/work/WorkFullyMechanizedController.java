package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.work.WorkFullyMechanized;
import com.ruoyi.system.service.work.IWorkFullyMechanizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 综采综掘配置Controller
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkFullyMechanizedController")
public class WorkFullyMechanizedController extends BaseController {
    @Autowired
    private IWorkFullyMechanizedService workFullyMechanizedService;

    /**
     * 查询综采综掘配置列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkFullyMechanized workFullyMechanized) {
        List<WorkFullyMechanized> list = workFullyMechanizedService.listWorkFullyMechanized(workFullyMechanized);
        return getDataTable(list);
    }

    /**
     * 新增综采综掘配置
     */
    @Log(title = "综采综掘配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkFullyMechanized> workFullyMechanized) {
        return toAjax(workFullyMechanizedService.saveWorkFullyMechanized(workFullyMechanized));
    }

    /**
     * 删除综采综掘配置
     */
    @Log(title = "综采综掘配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(workFullyMechanizedService.deleteWorkFullyMechanizedByIds(ids));
    }
}

