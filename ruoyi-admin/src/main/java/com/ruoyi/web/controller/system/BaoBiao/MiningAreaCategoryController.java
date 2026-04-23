package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.BaoBiao.IUnitManagementService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "采区分类档案")
@Anonymous
@RestController
@RequestMapping("/dev-api/system/miningAreaCategory")
public class MiningAreaCategoryController extends BaseController {

    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;
    @Resource
    private IUnitManagementService unitManagementService;

//    @Anonymous
//    @Operation(summary = "查询列表")
//    @GetMapping("/list")
//    public AjaxResult list(MiningAreaCategory query) {
//        return AjaxResult.success(miningAreaCategoryService.list(query));
//    }

    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/list")
    public TableDataInfo list(MiningAreaCategory query) {
        //startPage(); // 读取 pageNum/pageSize/orderByColumn/isAsc

//        // 如果没有传 orderBy 参数，则默认最新优先
//        PageDomain pd = TableSupport.buildPageRequest();
//        String orderBy = pd.getOrderBy(); // RuoYi 已做过 SQL 安全处理
//        if (orderBy == null || orderBy.isEmpty()) {
//            // 用【数据库列名】而不是 Java 字段名
//            PageHelper.orderBy("production_date desc"); // 或者 "create_time desc"
//        }
        query.setIsSealed(0);
        List<MiningAreaCategory> list = miningAreaCategoryService.list(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(miningAreaCategoryService.get(id));
    }


    @Anonymous
    @Operation(summary = "按层级查询详情")
    @GetMapping("level")
    public TableDataInfo getLevel(MiningAreaCategory query) {
        List<MiningAreaCategory> list = miningAreaCategoryService.list(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody MiningAreaCategory entity) {

        MiningAreaCategory areaName = miningAreaCategoryService.getAreaName(entity.getAreaName());
        MiningAreaCategory areaCode = miningAreaCategoryService.getAreaCode(entity.getAreaCode());
        if (areaName !=null){
            if(areaName.getLevel()==1){
                AjaxResult.error("名称不能为矿名");
            }
        }
        if (areaCode !=null){
            AjaxResult.error("采区编码重复");
        }
        return AjaxResult.toAjax(miningAreaCategoryService.add(entity));
    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody MiningAreaCategory entity) {
        return AjaxResult.toAjax(miningAreaCategoryService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(miningAreaCategoryService.remove(id));
//        MiningAreaCategory miningAreaCategory = miningAreaCategoryService.get(id);
//        UnitManagement unit=new UnitManagement();
//        unit.setUnitCode(miningAreaCategory.getAreaCode());
//        List<UnitManagement> list = unitManagementService.list(unit);
//        if(list.size()>0){
//            return AjaxResult.toAjax(0);
//        }else {
//            return AjaxResult.toAjax(miningAreaCategoryService.remove(id));
//        }
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(miningAreaCategoryService.removeBatch(ids));
    }
    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/oneList")
    public TableDataInfo oneList(MiningAreaCategory query) {
        List<MiningAreaCategory> list = miningAreaCategoryService.oneList(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "查询队组名称")
    @GetMapping("/QueryTeamName")
    public TableDataInfo QueryTeamName(@RequestParam String areaCode) {
        List<MiningAreaCategory> list = miningAreaCategoryService.QueryTeamName(areaCode);
        return getDataTable(list);
    }
}
