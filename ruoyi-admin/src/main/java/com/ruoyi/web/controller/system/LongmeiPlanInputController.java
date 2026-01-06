package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.LongmeiPlanInput;
import com.ruoyi.system.domain.LongmeiPlanInputInput;
import com.ruoyi.system.domain.LongmeiPlanInputOutput;
import com.ruoyi.system.domain.SubLongmeiPlanInput;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.ILongmeiPlanInputService;
import com.ruoyi.system.service.ISubLongmeiPlanInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 龙煤计划录入Controller
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/input")
public class LongmeiPlanInputController extends BaseController {
    @Autowired
    private ILongmeiPlanInputService longmeiPlanInputService;

    @Autowired
    private ISubLongmeiPlanInputService subLongmeiPlanInputService;

    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    /**
     * 查询龙煤计划录入列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(LongmeiPlanInput longmeiPlanInput) {
        startPage();
        List<LongmeiPlanInput> list = longmeiPlanInputService.listLongmeiPlanInput(longmeiPlanInput);
        List<LongmeiPlanInputOutput> listLong=new ArrayList<>();

        for (LongmeiPlanInput longmei: list) {
            LongmeiPlanInputOutput out=new LongmeiPlanInputOutput();
            out.setId(longmei.getId());
            out.setWorkDaysInMonth(longmei.getWorkDaysInMonth());
            out.setIsDeleted(longmei.getIsDeleted());
            SubLongmeiPlanInput sublong=new SubLongmeiPlanInput();//coalPlantStorageId
            sublong.setCoalPlantStorageId(longmei.getId());
            List<SubLongmeiPlanInput> sub = subLongmeiPlanInputService.listSubLongmeiPlanInput(sublong);
            out.setList(sub);
            listLong.add(out);
        }
        return getDataTable(listLong);
    }

    /**
     * 查询龙煤计划录入列表
     */
    @GetMapping("/Alllist")
    public TableDataInfo<BaseEntity> Alllist(LongmeiPlanInput longmeiPlanInput) {
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setIsSealed(0);
        fac.setLevel(1);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司

        List<LongmeiPlanInput> list = longmeiPlanInputService.listLongmeiPlanInput(longmeiPlanInput);
        List<LongmeiPlanInputOutput> listLong=new ArrayList<>();
        List<SubLongmeiPlanInput> subLongmeiList=new ArrayList<>();
        if(list.size()>0){
            SubLongmeiPlanInput sub=new SubLongmeiPlanInput();
            sub.setCoalPlantStorageId(list.get(0).getId());
            List<SubLongmeiPlanInput> subLongmeiPlanInputs = subLongmeiPlanInputService.listSubLongmeiPlanInput(sub);

            tosubLong(subLongmeiList,miningAreaCategories,subLongmeiPlanInputs,list.get(0).getId());
            LongmeiPlanInputOutput out=new LongmeiPlanInputOutput();
            out.setList(subLongmeiList);
            out.setPlanMonth(list.get(0).getPlanMonth());
            out.setWorkDaysInMonth(list.get(0).getWorkDaysInMonth());
            out.setIsDeleted(list.get(0).getIsDeleted());
            listLong.add(out);


        }else {
            List<SubLongmeiPlanInput> subLongmeiPlanInputs =new ArrayList<>();
            tosubLong(subLongmeiList,miningAreaCategories,subLongmeiPlanInputs, 0L);
        }



        return getDataTable(listLong);
    }

    /**
     * 修改龙煤计划修改
     */
    @Log(title = "龙煤计划录入", businessType = BusinessType.UPDATE)
    @PostMapping("/Alledit")
    public AjaxResult Alledit(@RequestBody LongmeiPlanInputInput input) {

        LongmeiPlanInput longmeiPlanInput=new LongmeiPlanInput();
        longmeiPlanInput.setPlanMonth(input.getPlanMonth());
        List<LongmeiPlanInput> longmeiPlanInputs = longmeiPlanInputService.listLongmeiPlanInput(longmeiPlanInput);
        if(longmeiPlanInputs.size()>0){
            subLongmeiPlanInputService.deleteSubLongmeiPlanInputByPlanId(longmeiPlanInputs.get(0).getId());

            for (SubLongmeiPlanInput sub:input.getList()) {
                sub.setCoalPlantStorageId(longmeiPlanInputs.get(0).getId());
            }
            subLongmeiPlanInputService.saveSubLongmeiPlanInput(input.getList());
            longmeiPlanInput.setId(longmeiPlanInputs.get(0).getId());
            longmeiPlanInput.setIsDeleted(Long.valueOf(3));
            return toAjax(longmeiPlanInputService.updateLongmeiPlanInput(longmeiPlanInput));
        }
        else {
            LongmeiPlanInput longmei=new LongmeiPlanInput();
            longmei.setPlanMonth(input.getPlanMonth());
            longmei.setWorkDaysInMonth(input.getWorkDaysInMonth());
            longmei.setIsDeleted(Long.valueOf(3));
            longmei.setCreateUser(SecurityUtils.getUserId());
            longmei.setCreateTime(DateUtils.getNowDate());

            int num = longmeiPlanInputService.saveLongmeiPlanInput(longmei);
            if(num>0){
                for (SubLongmeiPlanInput sub:input.getList()) {
                    sub.setCoalPlantStorageId(longmei.getId());
                }
                subLongmeiPlanInputService.saveSubLongmeiPlanInput(input.getList());
            }

            return toAjax(num);
        }


    }



    /**
     * 导出龙煤计划录入列表
     */
    @Log(title = "龙煤计划录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LongmeiPlanInput longmeiPlanInput) {
        List<LongmeiPlanInput> list = longmeiPlanInputService.listLongmeiPlanInput(longmeiPlanInput);
        ExcelUtil<LongmeiPlanInput> util = new ExcelUtil<LongmeiPlanInput>(LongmeiPlanInput.class);
        util.exportExcel(response, list, "龙煤计划录入数据");
    }

    /**
     * 获取龙煤计划录入详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(longmeiPlanInputService.getLongmeiPlanInputById(id));
    }

    /**
     * 新增龙煤计划录入
     */
    @Anonymous
    @Log(title = "龙煤计划录入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LongmeiPlanInputInput input) {
        LongmeiPlanInput longmei=new LongmeiPlanInput();
        longmei.setPlanMonth(input.getPlanMonth());
        longmei.setWorkDaysInMonth(input.getWorkDaysInMonth());
        longmei.setIsDeleted(0L);
        longmei.setCreateUser(SecurityUtils.getUserId());
        longmei.setCreateTime(DateUtils.getNowDate());

        int num = longmeiPlanInputService.saveLongmeiPlanInput(longmei);
        if(num>0){
            for (SubLongmeiPlanInput sub:input.getList()) {
                sub.setCoalPlantStorageId(longmei.getId());
            }
            subLongmeiPlanInputService.saveSubLongmeiPlanInput(input.getList());
        }

        return toAjax(num);
    }

    /**
     * 修改龙煤计划录入
     */
    @Log(title = "龙煤计划录入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LongmeiPlanInput longmeiPlanInput) {
        return toAjax(longmeiPlanInputService.updateLongmeiPlanInput(longmeiPlanInput));
    }

    /**
     * 删除龙煤计划录入
     */
    @PreAuthorize("@ss.hasPermi('system:input:remove')")
    @Log(title = "龙煤计划录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(longmeiPlanInputService.deleteLongmeiPlanInputByIds(ids));
    }
    private List<SubLongmeiPlanInput> tosubLong(List<SubLongmeiPlanInput> subLongmeiList, List<MiningAreaCategory> miningAreaCategories,
                                                List<SubLongmeiPlanInput> subLongmeiPlanInputs,Long coalid) {

        for (MiningAreaCategory mining:miningAreaCategories) {
            SubLongmeiPlanInput subout=new SubLongmeiPlanInput();
            subout.setCoalPlantStorageId(coalid);
            subout.setUnitName(mining.getAreaName());
            subout.setRawCoalProductionPlanDay(BigDecimal.valueOf(0));
            subout.setRawCoalProductionPlanMonth(BigDecimal.valueOf(0));
            subout.setExcavationPlanDay(BigDecimal.valueOf(0));
            subout.setExcavationPlanMonth(BigDecimal.valueOf(0));
            subout.setDevelopmentPlanDay(BigDecimal.valueOf(0));
            subout.setDevelopmentPlanMonth(BigDecimal.valueOf(0));

            for (SubLongmeiPlanInput sublong:subLongmeiPlanInputs) {
                if(mining.getAreaName().equals(sublong.getUnitName())){
                    subout.setRawCoalProductionPlanDay(sublong.getRawCoalProductionPlanDay());
                    subout.setRawCoalProductionPlanMonth(sublong.getRawCoalProductionPlanMonth());
                    subout.setExcavationPlanDay(sublong.getExcavationPlanDay());
                    subout.setExcavationPlanMonth(sublong.getExcavationPlanMonth());
                    subout.setDevelopmentPlanDay(sublong.getDevelopmentPlanDay());
                    subout.setDevelopmentPlanMonth(sublong.getDevelopmentPlanMonth());
                }

            }
            subLongmeiList.add(subout);
        }

        return subLongmeiList;
    }

}
