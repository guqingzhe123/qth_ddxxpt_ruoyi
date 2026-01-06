package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.WorkInstallationFace;
import com.ruoyi.system.service.work.IWorkInstallationFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 安装面信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkInstallationFaceController")
public class WorkInstallationFaceController extends BaseController {
    @Autowired
    private IWorkInstallationFaceService workInstallationFaceService;

    /**
     * 查询安装面信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkInstallationFace workInstallationFace) {
        List<WorkInstallationFace> list = workInstallationFaceService.listWorkInstallationFace(workInstallationFace);
        return getDataTable(list);
    }

    /**
     * 导出安装面信息列表
     */
    @Log(title = "安装面信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkInstallationFace workInstallationFace) {
        List<WorkInstallationFace> list = workInstallationFaceService.listWorkInstallationFace(workInstallationFace);
        ExcelUtil<WorkInstallationFace> util = new ExcelUtil<WorkInstallationFace>(WorkInstallationFace.class);
        util.exportExcel(response, list, "安装面信息数据");
    }

    /**
     * 获取安装面信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workInstallationFaceService.getWorkInstallationFaceById(id));
    }

    /**
     * 新增安装面信息
     */
    @Log(title = "安装面信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkInstallationFace> workInstallationFace) {
        return toAjax(workInstallationFaceService.saveWorkInstallationFace(workInstallationFace));
    }

    /**
     * 修改安装面信息
     */
    @Log(title = "安装面信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkInstallationFace workInstallationFace) {
        return toAjax(workInstallationFaceService.updateWorkInstallationFace(workInstallationFace));
    }

    /**
     * 删除安装面信息
     */
    @Log(title = "安装面信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workInstallationFaceService.deleteWorkInstallationFaceByIds(ids));
    }
}
