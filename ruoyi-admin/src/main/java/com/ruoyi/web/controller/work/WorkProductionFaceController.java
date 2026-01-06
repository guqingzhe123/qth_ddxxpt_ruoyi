package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.WorkProductionFace;
import com.ruoyi.system.service.work.IWorkProductionFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 生产面信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkProductionFaceController")
public class WorkProductionFaceController extends BaseController {
    @Autowired
    private IWorkProductionFaceService workProductionFaceService;

    /**
     * 查询生产面信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkProductionFace workProductionFace) {
        List<WorkProductionFace> list = workProductionFaceService.listWorkProductionFace(workProductionFace);
        return getDataTable(list);
    }

    /**
     * 导出生产面信息列表
     */
    @Log(title = "生产面信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkProductionFace workProductionFace) {
        List<WorkProductionFace> list = workProductionFaceService.listWorkProductionFace(workProductionFace);
        ExcelUtil<WorkProductionFace> util = new ExcelUtil<WorkProductionFace>(WorkProductionFace.class);
        util.exportExcel(response, list, "生产面信息数据");
    }

    /**
     * 获取生产面信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workProductionFaceService.getWorkProductionFaceById(id));
    }

    /**
     * 新增生产面信息
     */
    @Log(title = "生产面信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkProductionFace> workProductionFace) {
        return toAjax(workProductionFaceService.saveWorkProductionFace(workProductionFace));
    }

    /**
     * 修改生产面信息
     */
    @Log(title = "生产面信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkProductionFace workProductionFace) {
        return toAjax(workProductionFaceService.updateWorkProductionFace(workProductionFace));
    }

    /**
     * 删除生产面信息
     */
    @Log(title = "生产面信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workProductionFaceService.deleteWorkProductionFaceByIds(ids));
    }

    /**
     * 查询生产面信息
     */
    @PostMapping("/getProductionFaceListByUnit")
    public AjaxResult getProductionFaceListByUnit(@RequestBody String json) {
        return workProductionFaceService.getProductionFaceListByUnit(json);
    }

}
