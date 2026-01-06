package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.BaoBiao.MineTemplate;
import com.ruoyi.system.service.BaoBiao.IMineTemplateService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "矿模板")
@Anonymous
@RestController
@RequestMapping("/dev-api/system/mineTemplate")
public class MineTemplateController {

    @Resource
    private IMineTemplateService mineTemplateService;

    @Anonymous
    @Operation(summary = "查询列表")
    @GetMapping("/list")
    public AjaxResult list(MineTemplate query) {
        return AjaxResult.success(mineTemplateService.list(query));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(mineTemplateService.get(id));
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody MineTemplate entity) {
        return AjaxResult.toAjax(mineTemplateService.add(entity));
    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody MineTemplate entity) {
        return AjaxResult.toAjax(mineTemplateService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(mineTemplateService.remove(id));
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(mineTemplateService.removeBatch(ids));
    }
}
