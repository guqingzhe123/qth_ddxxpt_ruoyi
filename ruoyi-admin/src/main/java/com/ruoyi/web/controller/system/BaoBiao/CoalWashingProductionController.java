package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.BaoBiao.ICoalWashingProductionService;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionCreateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionPageQueryDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionUpdateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionVO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "洗煤生产")
@RestController
@RequestMapping("/dev-api/system/coalWashingProduction")
public class CoalWashingProductionController extends BaseController {

    @Resource
    private ICoalWashingProductionService service;

    @Anonymous
    @Operation(summary = "新增（production_date + unit_code 唯一）")
    @PostMapping
    public AjaxResult add(@RequestBody CoalWashingProductionCreateDTO dto) {
        dto.setUser_id(SecurityUtils.getUserId());
        dto.setMine_category("0");
        Long id = service.add(dto);
        return AjaxResult.success(id);
    }

    @Anonymous
    @Operation(summary = "修改（全量字段；变更唯一键会做冲突校验）")
    @PutMapping
    public AjaxResult edit(@RequestBody CoalWashingProductionUpdateDTO dto) {
        return AjaxResult.toAjax(service.edit(dto));
    }

    @Anonymous
    @Operation(summary = "删除（按ID）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(service.remove(id));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        CoalWashingProductionVO vo = service.get(id);
        return AjaxResult.success(vo);
    }

    @Anonymous
    @Operation(summary = "分页列表（支持日期范围/单位/矿类别/用户/状态过滤）")
    @PostMapping("/page")
    public TableDataInfo page(@RequestBody CoalWashingProductionPageQueryDTO query) {
        //startPage();
        List<CoalWashingProductionVO> list = service.page(query);
        return getDataTable(list);
    }
}
