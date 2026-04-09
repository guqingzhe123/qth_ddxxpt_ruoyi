package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IInitialInventoryOfEachMineService;
import com.ruoyi.system.service.ISubInitialInventoryOfEachMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 各矿期初库存录入Controller
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/mine/Initial")
public class InitialInventoryOfEachMineController extends BaseController {
    @Autowired
    private IInitialInventoryOfEachMineService initialInventoryOfEachMineService;
    @Autowired
    private ISubInitialInventoryOfEachMineService subInitialInventoryOfEachMineService;
    /**
     * 查询各矿期初库存录入列表  局端
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(InitialInventoryOfEachMine initialInventoryOfEachMine) {
        List<InitialInventoryOfEachMineOutput> list = initialInventoryOfEachMineService.listInitialInventoryOfEachMineList(initialInventoryOfEachMine);
        return getDataTable(list);
    }
    /**
     * 查询各矿期初库存录入列表  矿端
     */
    @GetMapping("/mineList")
    public TableDataInfo<BaseEntity> mineList(InitialInventoryOfEachMineUpdateInput output) {

        InitialInventoryOfEachMine mine=new InitialInventoryOfEachMine();
        mine.setRecordDate(output.getRecordDate());
        List<InitialInventoryOfEachMine> list = initialInventoryOfEachMineService.listInitialInventoryOfEachMine(mine);
        if (list.size()>0){
            SubInitialInventoryOfEachMine submine=new SubInitialInventoryOfEachMine();
            submine.setInitialInventoryId(list.get(0).getId());
            submine.setUnitName(output.getUnitName());
            List<SubInitialInventoryOfEachMine> subInitialInventoryOfEachMines = subInitialInventoryOfEachMineService.listSubInitialInventoryOfEachMine(submine);
            if(subInitialInventoryOfEachMines.size()>0){
                return getDataTable(subInitialInventoryOfEachMines);
            }else {
                subInitialInventoryOfEachMines.add(submine);
                return getDataTable(subInitialInventoryOfEachMines);
            }
        }else {
            List<SubInitialInventoryOfEachMine> subInitialInventoryOfEachMines =new ArrayList<>();
            SubInitialInventoryOfEachMine submine=new SubInitialInventoryOfEachMine();
            submine.setUnitName(output.getUnitName());
            subInitialInventoryOfEachMines.add(submine);
            return getDataTable(subInitialInventoryOfEachMines);
        }
    }
    /**
     * 导出各矿期初库存录入列表
     */
    @Log(title = "各矿期初库存录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InitialInventoryOfEachMine initialInventoryOfEachMine) {
        List<InitialInventoryOfEachMine> list = initialInventoryOfEachMineService.listInitialInventoryOfEachMine(initialInventoryOfEachMine);
        ExcelUtil<InitialInventoryOfEachMine> util = new ExcelUtil<InitialInventoryOfEachMine>(InitialInventoryOfEachMine.class);
        util.exportExcel(response, list, "各矿期初库存录入数据");
    }

//    /**
//     * 获取各矿期初库存录入详细信息
//     */
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id) {
//        return success(initialInventoryOfEachMineService.getInitialInventoryOfEachMineById(id));
//    }

    /**
     * 新增各矿期初库存录入
     */
    @Log(title = "各矿期初库存录入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InitialInventoryOfEachMineInput initialInventoryOfEachMine) {
        InitialInventoryOfEachMine initquery=new InitialInventoryOfEachMine();
        initquery.setRecordDate(initialInventoryOfEachMine.getRecordDate());
        List<InitialInventoryOfEachMine> list = initialInventoryOfEachMineService.listInitialInventoryOfEachMine(initquery);
        if(list.size()>0){
            InitialInventoryOfEachMine init=new InitialInventoryOfEachMine();
            init.setId(list.get(0).getId());
            init.setRecordDate(initialInventoryOfEachMine.getRecordDate());
            init.setUnitType(initialInventoryOfEachMine.getUnitType());
            init.setBranchCompany(initialInventoryOfEachMine.getBranchCompany());
            init.setIsDeleted(0);
            init.setUserId(SecurityUtils.getUserId());
            init.setCreateTime(DateUtils.getNowDate());
            init.setSevenCoalCompany(initialInventoryOfEachMine.getSevenCoalCompany());
            initialInventoryOfEachMineService.updateInitialInventoryOfEachMine(init);

            SubInitialInventoryOfEachMine sub=new SubInitialInventoryOfEachMine();
            sub.setInitialInventoryId(list.get(0).getId());
            List<SubInitialInventoryOfEachMine> subInitial = subInitialInventoryOfEachMineService.listSubInitialInventoryOfEachMine(sub);

            for (SubInitialInventoryOfEachMine SubInitial:initialInventoryOfEachMine.getList()) {
                SubInitialInventoryOfEachMine subInitialMine = subInitial.stream().filter(item -> SubInitial.getUnitName().equals(item.getUnitName())).findFirst().orElse(new SubInitialInventoryOfEachMine());
                if(subInitialMine.getId() !=null){
                    SubInitial.setIsReject(0);
                    SubInitial.setId(subInitialMine.getId());
                    subInitialInventoryOfEachMineService.updateSubInitialInventoryOfEachMine(SubInitial);
                }else {
                    SubInitial.setIsReject(0);
                    SubInitial.setInitialInventoryId(list.get(0).getId());
                    subInitialInventoryOfEachMineService.saveSubInitialInventoryOfEachMine(SubInitial);
                }
            }
            return toAjax(Math.toIntExact(list.get(0).getId()));
        }
        else {
            InitialInventoryOfEachMine init=new InitialInventoryOfEachMine();
            init.setRecordDate(initialInventoryOfEachMine.getRecordDate());
            init.setUnitType(initialInventoryOfEachMine.getUnitType());
            init.setBranchCompany(initialInventoryOfEachMine.getBranchCompany());
            init.setIsDeleted(0);
            init.setBranchCompany(initialInventoryOfEachMine.getBranchCompany());
            init.setUserId(SecurityUtils.getUserId());
            init.setCreateTime(DateUtils.getNowDate());
            init.setSevenCoalCompany(initialInventoryOfEachMine.getSevenCoalCompany());
            int num = initialInventoryOfEachMineService.saveInitialInventoryOfEachMine(init);

            if(num>0){
                List<SubInitialInventoryOfEachMine> list1 = initialInventoryOfEachMine.getList();
                for (SubInitialInventoryOfEachMine sub:list1) {
                    sub.setIsReject(0);
                    sub.setInitialInventoryId(init.getId());
                }
                subInitialInventoryOfEachMineService.saveSubInitialInventoryOfEachMineBatch(list1);
            }
            return toAjax(num);
        }
    }

    /**
     * 修改各矿期初库存录入
     */
    @Log(title = "各矿期初库存录入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InitialInventoryOfEachMine initialInventoryOfEachMine) {
        return toAjax(initialInventoryOfEachMineService.updateInitialInventoryOfEachMine(initialInventoryOfEachMine));
    }
    /**
     * 退回各矿期初库存录入
     */
    @Log(title = "退回各矿期初库存录入", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult editlist(@RequestBody InitialInventoryOfEachMineUpdateInput output) {
        try{
            InitialInventoryOfEachMine mine=new InitialInventoryOfEachMine();
            mine.setRecordDate(output.getRecordDate());
            List<InitialInventoryOfEachMine> list = initialInventoryOfEachMineService.listInitialInventoryOfEachMine(mine);
            if (list.size()>0){
                SubInitialInventoryOfEachMine submine=new SubInitialInventoryOfEachMine();
                submine.setInitialInventoryId(list.get(0).getId());
                submine.setUnitName(output.getUnitName());
                List<SubInitialInventoryOfEachMine> subInitialInventoryOfEachMines = subInitialInventoryOfEachMineService.listSubInitialInventoryOfEachMine(submine);
                for (SubInitialInventoryOfEachMine  sub:subInitialInventoryOfEachMines) {
                    sub.setIsReject(2);
                    subInitialInventoryOfEachMineService.updateSubInitialInventoryOfEachMine(sub);
                }
            }
        }catch (Exception e){
            return toAjax(0);
        }
        return toAjax(1);
    }
    /**
     * 删除各矿期初库存录入
     */
    @Log(title = "各矿期初库存录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(initialInventoryOfEachMineService.deleteInitialInventoryOfEachMineByIds(ids));
    }
}
