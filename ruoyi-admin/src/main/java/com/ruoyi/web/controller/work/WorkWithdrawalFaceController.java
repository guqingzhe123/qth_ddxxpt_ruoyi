package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.WorkWithdrawalFace;
import com.ruoyi.system.service.work.IWorkWithdrawalFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 回撤面配置Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkWithdrawalFaceController")
public class WorkWithdrawalFaceController extends BaseController {
    @Autowired
    private IWorkWithdrawalFaceService workWithdrawalFaceService;

    /**
     * 查询回撤面配置列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkWithdrawalFace workWithdrawalFace) {
        startPage();
        List<WorkWithdrawalFace> list = workWithdrawalFaceService.listWorkWithdrawalFace(workWithdrawalFace);
        return getDataTable(list);
    }

    /**
     * 导出回撤面配置列表
     */
    @Log(title = "回撤面配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkWithdrawalFace workWithdrawalFace) {
        List<WorkWithdrawalFace> list = workWithdrawalFaceService.listWorkWithdrawalFace(workWithdrawalFace);
        ExcelUtil<WorkWithdrawalFace> util = new ExcelUtil<WorkWithdrawalFace>(WorkWithdrawalFace.class);
        util.exportExcel(response, list, "回撤面配置数据");
    }

    /**
     * 获取回撤面配置详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workWithdrawalFaceService.getWorkWithdrawalFaceById(id));
    }

    /**
     * 新增回撤面配置
     */
    @Log(title = "回撤面配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkWithdrawalFace> workWithdrawalFace) {
        return toAjax(workWithdrawalFaceService.saveWorkWithdrawalFace(workWithdrawalFace));
    }

    /**
     * 修改回撤面配置
     */
    @Log(title = "回撤面配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkWithdrawalFace workWithdrawalFace) {
        return toAjax(workWithdrawalFaceService.updateWorkWithdrawalFace(workWithdrawalFace));
    }

    /**
     * 删除回撤面配置
     */
    @Log(title = "回撤面配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workWithdrawalFaceService.deleteWorkWithdrawalFaceByIds(ids));
    }
}
