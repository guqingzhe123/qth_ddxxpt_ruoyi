package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.BaoBiao.TemplateDetails;
import com.ruoyi.system.service.BaoBiao.ITemplateDetailsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "模板详情")
@RestController
@RequestMapping("/dev-api/system/templateDetails")
public class TemplateDetailsController {

    @Resource
    private ITemplateDetailsService templateDetailsService;

    @Anonymous
    @Operation(summary = "查询列表（支持 name/mineTemplateId）")
    @GetMapping("/list")
    public AjaxResult list(TemplateDetails query) {
        return AjaxResult.success(templateDetailsService.list(query));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(templateDetailsService.get(id));
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody TemplateDetails entity) {
        return AjaxResult.toAjax(templateDetailsService.add(entity));
    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody TemplateDetails entity) {
        return AjaxResult.toAjax(templateDetailsService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(templateDetailsService.remove(id));
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(templateDetailsService.removeBatch(ids));
    }

    // ===== 你要的：根据矿模板ID查模板详情（引用到的模板数据一起返回） =====
    @Anonymous
    @Operation(summary = "根据矿模板ID查询详情（携带模板信息）")
    @GetMapping("/byTemplate/{mineTemplateId}")
    public AjaxResult listByTemplate(@PathVariable Long mineTemplateId) {
        return AjaxResult.success(templateDetailsService.listVOByTemplateId(mineTemplateId));
    }
}
