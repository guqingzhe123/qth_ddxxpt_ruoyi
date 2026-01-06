package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BaoBiao.UnitManagement;
import com.ruoyi.system.service.BaoBiao.IUnitManagementService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "单位管理（原煤/开拓/进尺）")
@RestController
@RequestMapping("/dev-api/system/unitManagement")
public class UnitManagementController extends BaseController {

    @Resource
    private IUnitManagementService unitManagementService;

//    @Anonymous
//    @Operation(summary = "查询列表")
//    @GetMapping("/list")
//    public AjaxResult list(UnitManagement query) {
//        return AjaxResult.success(unitManagementService.list(query));
//    }

    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/list")
    public TableDataInfo list(UnitManagement query) {
        List<UnitManagement> list = unitManagementService.list(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(unitManagementService.get(id));
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody UnitManagement entity) {
        return AjaxResult.toAjax(unitManagementService.add(entity));
    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody UnitManagement entity) {
        return AjaxResult.toAjax(unitManagementService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(unitManagementService.remove(id));
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(unitManagementService.removeBatch(ids));
    }

    @Anonymous
    @Operation(summary = "矿所有的队组")
    @GetMapping("/Alllist")
    public TableDataInfo Alllist(UnitManagement query) {
        List<UnitManagement> list = unitManagementService.list(query);
        return getDataTable(list);
    }
}
