package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.service.BaoBiao.IFactoryArchiveService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "厂档案（所属/发电）")
@RestController
@RequestMapping("/dev-api/system/factoryArchive")
public class FactoryArchiveController extends BaseController {

    @Resource
    private IFactoryArchiveService factoryArchiveService;

//    @Anonymous
//    @Operation(summary = "查询列表")
//    @GetMapping("/list")
//    public AjaxResult list(FactoryArchive query) {
//        return AjaxResult.success(factoryArchiveService.list(query));
//    }

    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/list")
    public TableDataInfo list(FactoryArchive query) {
        startPage(); // 读取 pageNum/pageSize/orderByColumn/isAsc

//        // 如果没有传 orderBy 参数，则默认最新优先
//        PageDomain pd = TableSupport.buildPageRequest();
//        String orderBy = pd.getOrderBy(); // RuoYi 已做过 SQL 安全处理
//        if (orderBy == null || orderBy.isEmpty()) {
//            // 用【数据库列名】而不是 Java 字段名
//            PageHelper.orderBy("production_date desc"); // 或者 "create_time desc"
//        }

        List<FactoryArchive> list = factoryArchiveService.list(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/coalWashinglist")
    public TableDataInfo CoalWashinglist(FactoryArchive query) {
        query.setFactoryType("所属厂档案");
        query.setIsSealed(0);
        List<FactoryArchive> list = factoryArchiveService.list(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(factoryArchiveService.get(id));
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody FactoryArchive entity) {
        return AjaxResult.toAjax(factoryArchiveService.add(entity));
    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody FactoryArchive entity) {
        return AjaxResult.toAjax(factoryArchiveService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(factoryArchiveService.remove(id));
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(factoryArchiveService.removeBatch(ids));
    }
}
