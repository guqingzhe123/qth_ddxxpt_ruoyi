package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.WorkRoadwayRepairFace;
import com.ruoyi.system.service.work.IWorkRoadwayRepairFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 巷修面信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkRoadwayRepairFaceController")
public class WorkRoadwayRepairFaceController extends BaseController {
    @Autowired
    private IWorkRoadwayRepairFaceService workRoadwayRepairFaceService;

    /**
     * 查询巷修面信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkRoadwayRepairFace workRoadwayRepairFace) {
        startPage();
        List<WorkRoadwayRepairFace> list = workRoadwayRepairFaceService.listWorkRoadwayRepairFace(workRoadwayRepairFace);
        return getDataTable(list);
    }

    /**
     * 导出巷修面信息列表
     */
    @Log(title = "巷修面信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkRoadwayRepairFace workRoadwayRepairFace) {
        List<WorkRoadwayRepairFace> list = workRoadwayRepairFaceService.listWorkRoadwayRepairFace(workRoadwayRepairFace);
        ExcelUtil<WorkRoadwayRepairFace> util = new ExcelUtil<WorkRoadwayRepairFace>(WorkRoadwayRepairFace.class);
        util.exportExcel(response, list, "巷修面信息数据");
    }

    /**
     * 获取巷修面信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workRoadwayRepairFaceService.getWorkRoadwayRepairFaceById(id));
    }

    /**
     * 新增巷修面信息
     */
    @Log(title = "巷修面信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkRoadwayRepairFace> workRoadwayRepairFace) {
        return toAjax(workRoadwayRepairFaceService.saveWorkRoadwayRepairFace(workRoadwayRepairFace));
    }

    /**
     * 修改巷修面信息
     */
    @Log(title = "巷修面信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkRoadwayRepairFace workRoadwayRepairFace) {
        return toAjax(workRoadwayRepairFaceService.updateWorkRoadwayRepairFace(workRoadwayRepairFace));
    }

    /**
     * 删除巷修面信息
     */
    @Log(title = "巷修面信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workRoadwayRepairFaceService.deleteWorkRoadwayRepairFaceByIds(ids));
    }


}