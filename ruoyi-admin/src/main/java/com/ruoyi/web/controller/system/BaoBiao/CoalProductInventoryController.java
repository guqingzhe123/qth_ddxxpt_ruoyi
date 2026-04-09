package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.BaoBiao.SubCoalProductInventoryMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.ICoalProductInventoryService;
import com.ruoyi.system.service.BaoBiao.IFactoryArchiveService;
import com.ruoyi.system.service.IMineInfoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = "洗煤产品库存及自用")
@RestController
@RequestMapping("/dev-api/system/coalProductInventory")
public class CoalProductInventoryController extends BaseController {

    @Resource
    private ICoalProductInventoryService service;
    @Resource
    private IFactoryArchiveService factoryArchiveService;//洗煤厂列表

    @Resource
    private SysUserMapper sysUserMapper;//查找用户id
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private UserMessageMapper messageMapper;
    @Autowired
    private SubCoalProductInventoryMapper subCoalProductInventoryMapper;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    @Anonymous
    @Operation(summary = "新增洗煤产品库存及自用煤（入参含 data_JSON）")
    @PostMapping
    public AjaxResult add(@RequestBody List<SubCoalProductInventory> dto) {

        if(dto.size()>0){
            SubCoalProductInventory subc=new SubCoalProductInventory();
            subc.setUnitCode(dto.get(0).getUnitCode());
            subc.setRecordDate(dto.get(0).getRecordDate());
            List<SubCoalProductInventory> subCoalProductInventories = subCoalProductInventoryMapper.selectSubCoalProductInventoryList(subc);
            if(subCoalProductInventories.size()>0){
                MineInfo mineInfo = new MineInfo();
                mineInfo.setModuleName("洗煤产品库存及自用");
                mineInfo.setStatDate(subCoalProductInventories.get(0).getRecordDate());
                mineInfo.setMineName(subCoalProductInventories.get(0).getUnitName());
                List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
                if(mineInfos.size()==0){
                    return AjaxResult.error("请联系局里进行驳回");
                }
                if(mineInfos.size()>=0){
                    mineInfoService.deleteMineInfoByDate(mineInfo);
                }
            }
        }
        SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
        for (SysUserRole userRole:sysUserRoles) {
            String message=dto.get(0).getUnitName()+"提交了洗煤产品库存及自用煤录入";
            messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
        }




        return AjaxResult.success(service.saveSubCoalProductInventory(dto));
    }

    @Anonymous
    @Operation(summary = "修改（全量替换子表 data_JSON）")
    @PutMapping
    public AjaxResult edit(@RequestBody SubCoalProductInventory dto) {
        return AjaxResult.toAjax(service.updateSubCoalProductInventory(dto));
    }

    @Anonymous
    @Operation(summary = "删除（软删主表）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(service.deleteSubCoalProductInventoryById(id));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情（含 data_JSON）")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(service.getSubCoalProductInventoryById(id));
    }

    @Anonymous
    @Operation(summary = "分页列表（含 data_JSON；支持子表条件过滤）")
    @PostMapping("/page")
    public TableDataInfo page(@RequestBody SubCoalProductInventory query) {
        //startPage();
        List<SubCoalProductInventory> list = service.listSubCoalProductInventory(query);
        if(query.getUnitName() ==null){
            FactoryArchive factoryArchive=new FactoryArchive();
            factoryArchive.setFactoryType("所属厂档案");
            factoryArchive.setIsSealed(0);
            if(query.getUnitName()!=null){
                factoryArchive.setFactoryName(query.getUnitName());
            }
            List<FactoryArchive> list1 = factoryArchiveService.list(factoryArchive);
            List<SubCoalProductInventory> month = service.selectProductMonth(query.getRecordDate());
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("洗煤产品库存及自用");
            mineInfo.setStatDate(query.getRecordDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            List<SubCoalProductInventory> returnList=new ArrayList<>();

            for (FactoryArchive fact :list1) {
                SubCoalProductInventory listMatch = list.stream()
                        .filter(item -> fact.getFactoryCode().equals(item.getUnitCode()))
                        .findFirst()
                        .orElse(new SubCoalProductInventory());

                SubCoalProductInventory monthMatch = month.stream()
                        .filter(item -> fact.getFactoryCode().equals(item.getUnitCode()))
                        .findFirst()
                        .orElse(new SubCoalProductInventory());


                MineInfo mineInfo1 = mineInfos.stream()
                        .filter(s -> s.getMineName().equals(fact.getFactoryName()))
                        .findFirst()
                        .orElse(null);

                SubCoalProductInventory subCoalProductInventory = new SubCoalProductInventory();

                if(mineInfo1 != null){
                    subCoalProductInventory.setUnitCode(fact.getFactoryCode());
                    subCoalProductInventory.setUnitName(fact.getFactoryName());
                }else {
                    if(listMatch.getUnitCode()!=null){
                        subCoalProductInventory = listMatch;
                    }else {
                        subCoalProductInventory.setUnitCode(fact.getFactoryCode());
                        subCoalProductInventory.setUnitName(fact.getFactoryName());
                    }
                }

                if(monthMatch.getUnitCode()!=null){
                    subCoalProductInventory.setMonthlyTotalSelfUse(monthMatch.getMonthlyTotalSelfUse());
                    subCoalProductInventory.setYearlyTotalSelfUse(monthMatch.getYearlyTotalSelfUse());
                }else {
                    subCoalProductInventory.setUnitCode(fact.getFactoryCode());
                    subCoalProductInventory.setUnitName(fact.getFactoryName());
                }
                returnList.add(subCoalProductInventory);
            }
            return getDataTable(returnList);


//            if(month.size()>0){
//
//            }else {
//                for (FactoryArchive fact :list1) {
//                    SubCoalProductInventory subc=new SubCoalProductInventory();
//                    subc.setUnitCode(fact.getFactoryCode());
//                    subc.setUnitName(fact.getFactoryName());
//                    list.add(subc);
//                }
//            }
        }
        else {
            if(list.size()==0){

                List<SubCoalProductInventory> month = service.selectProductInventoryMonth(query.getRecordDate());
                List<SubCoalProductInventory> year = service.selectProductInventoryYear(query.getRecordDate());

                query.setRecordDate(DateUtils.getDayBefore(query.getRecordDate()));
                List<SubCoalProductInventory> beforlist = service.listSubCoalProductInventory(query);

                SubCoalProductInventory day=new SubCoalProductInventory();
                day.setUnitCode(query.getUnitCode());
                day.setUnitName(query.getUnitName());

                if(beforlist.size()>0){
                    SubCoalProductInventory befor=beforlist.get(0);

                    day.setCleanCoalPrevStock(befor.getCleanCoalCurrentStock());
                    day.setSlackLumpPrevStock(befor.getSlackLumpCurrentStock());
                    day.setTotalPrevStock(befor.getTotalCurrentStock());
                    day.setCleanCoalPrevStock(befor.getCleanCoalCurrentStock());
                }
                // 获取 month 中 unitName 与 query.getUnitName() 相同的数据，并计算 dailyPlantSelfUse 和 dailyOutsideSelfUse 的总和 (类型为 BigDecimal)
                java.math.BigDecimal monthlyTotalSelfUse = month.stream()
                        .filter(item -> query.getUnitName().equals(item.getUnitName()))
                        .map(item -> {
                            java.math.BigDecimal plantSelfUse = item.getDailyPlantSelfUse() != null ? item.getDailyPlantSelfUse() : java.math.BigDecimal.ZERO;
                            java.math.BigDecimal outsideSelfUse = item.getDailyOutsideSelfUse() != null ? item.getDailyOutsideSelfUse() : java.math.BigDecimal.ZERO;
                            return plantSelfUse.add(outsideSelfUse);
                        })
                        .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
                day.setMonthlyTotalSelfUse(monthlyTotalSelfUse);

                java.math.BigDecimal yearlyTotalSelfUse = year.stream()
                        .filter(item -> query.getUnitName().equals(item.getUnitName()))
                        .map(item -> {
                            java.math.BigDecimal plantSelfUse = item.getDailyPlantSelfUse() != null ? item.getDailyPlantSelfUse() : java.math.BigDecimal.ZERO;
                            java.math.BigDecimal outsideSelfUse = item.getDailyOutsideSelfUse() != null ? item.getDailyOutsideSelfUse() : java.math.BigDecimal.ZERO;
                            return plantSelfUse.add(outsideSelfUse);
                        })
                        .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
                day.setYearlyTotalSelfUse(yearlyTotalSelfUse);
                list.add(day);

            }
        }
        return getDataTable(list);
    }


    /**
     * 洗煤产品库存及自用
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(SubCoalProductInventory raw){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("洗煤产品库存及自用");
        mineInfo.setMineName(raw.getUnitName());//那个洗煤厂退回
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(raw.getRecordDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return AjaxResult.error("已经退回");
        }else {
            mineInfoService.saveMineInfo(mineInfo);
            return AjaxResult.success("退回成功");
        }
    }



}
