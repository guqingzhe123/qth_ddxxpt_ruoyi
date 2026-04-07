package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.ProductExportSituation;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.IFactoryArchiveService;
import com.ruoyi.system.service.BaoBiao.IProductExportSituationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Api(tags = "产品外销情况")
@RestController
@RequestMapping("/dev-api/system/productExportSituation")
public class ProductExportSituationController extends BaseController {

    @Resource
    private IProductExportSituationService productExportSituationService;

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private UserMessageMapper messageMapper;
    @Resource
    private IFactoryArchiveService factoryArchiveService;//洗煤厂列表

//    @Anonymous
//    @Operation(summary = "查询列表")
//    @GetMapping("/list")
//    public AjaxResult list(ProductExportSituation query) {
//        return AjaxResult.success(productExportSituationService.list(query));
//    }

    @Anonymous
    @Operation(summary = "分页查询列表（默认最新优先）")
    @GetMapping("/list")
    public TableDataInfo list(ProductExportSituation query) {
        if(query.getUnitCode() ==null){
            FactoryArchive factoryArchive = new FactoryArchive();
            factoryArchive.setFactoryType("所属厂档案");
            factoryArchive.setIsSealed(0);

            List<FactoryArchive> factory = factoryArchiveService.list(factoryArchive);
            List<ProductExportSituation> list = productExportSituationService.list(query);
            List<ProductExportSituation> AllList=new ArrayList<>();
            for (FactoryArchive factoryAr:factory) {
                if(list.stream().filter(x -> x.getUnitCode().equals(factoryAr.getFactoryCode())).count() > 0){
                    list.stream().filter(x -> x.getUnitCode().equals(factoryAr.getFactoryCode())).forEach(AllList::add);
                }else {
                    ProductExportSituation productExportSituation=new ProductExportSituation();
                    productExportSituation.setUnitCode(factoryAr.getFactoryCode());
                    productExportSituation.setUnitName(factoryAr.getFactoryName());
                    AllList.add(productExportSituation);
                }
            }

            return getDataTable(AllList);
        }else {
            startPage(); // 读取 pageNum/pageSize/orderByColumn/isAsc
            List<ProductExportSituation> list = productExportSituationService.list(query);
            return getDataTable(list);
        }
    }

    @Anonymous
    @Operation(summary = "按ID查询详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(productExportSituationService.get(id));
    }

    @Anonymous
    @Operation(summary = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody ProductExportSituation entity) {
        entity.setUserId(SecurityUtils.getUserId());
        entity.setMineCategory("0");
        ProductExportSituation query =new ProductExportSituation();
        query.setUnitCode(entity.getUnitCode());
        query.setExportDate(entity.getExportDate());

        List<ProductExportSituation> list = productExportSituationService.list(query);
        if(list.size()>0){
            entity.setId(list.get(0).getId());
            return AjaxResult.toAjax(productExportSituationService.edit(entity));
//            return AjaxResult.error("每天保存一次");
        }else {
            SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
            for (SysUserRole userRole:sysUserRoles) {
                String message=entity.getUnitName()+"提交了产品外销情况";
                messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
            }

            return AjaxResult.toAjax(productExportSituationService.add(entity));
        }


    }

    @Anonymous
    @Operation(summary = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody ProductExportSituation entity) {
        return AjaxResult.toAjax(productExportSituationService.edit(entity));
    }

    @Anonymous
    @Operation(summary = "删除（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(productExportSituationService.remove(id));
    }

    @Anonymous
    @Operation(summary = "删除（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(productExportSituationService.removeBatch(ids));
    }
}
