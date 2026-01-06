package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanThree;
import com.ruoyi.system.domain.EnteringAndExitingTheMine;
import com.ruoyi.system.service.IEnteringAndExitingTheMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人员入井出井日报录入Controller
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/mine")
public class EnteringAndExitingTheMineController extends BaseController {
    @Autowired
    private IEnteringAndExitingTheMineService enteringAndExitingTheMineService;

    /**
     * 查询人员入井出井日报录入列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(EnteringAndExitingTheMine enteringAndExitingTheMine) {
        startPage();
        List<EnteringAndExitingTheMine> list = enteringAndExitingTheMineService.listEnteringAndExitingTheMine(enteringAndExitingTheMine);
        return getDataTable(list);
    }

    /**
     * 导出人员入井出井日报录入列表
     */
    @Log(title = "人员入井出井日报录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnteringAndExitingTheMine enteringAndExitingTheMine) {
        List<EnteringAndExitingTheMine> list = enteringAndExitingTheMineService.listEnteringAndExitingTheMine(enteringAndExitingTheMine);
        ExcelUtil<EnteringAndExitingTheMine> util = new ExcelUtil<EnteringAndExitingTheMine>(EnteringAndExitingTheMine.class);
        util.exportExcel(response, list, "人员入井出井日报录入数据");
    }

    /**
     * 获取人员入井出井日报录入详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(enteringAndExitingTheMineService.getEnteringAndExitingTheMineById(id));
    }

    /**
     * 新增人员入井出井日报录入
     */
    @Log(title = "人员入井出井日报录入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnteringAndExitingTheMine enteringAndExitingTheMine) {
        List<EnteringAndExitingTheMine> enteringAndExitingTheMines = enteringAndExitingTheMineService.listEnteringAndExitingTheMine(enteringAndExitingTheMine);

        if(enteringAndExitingTheMines.size()>0){
            return error("当前班次添加过");
        }else {
            String message=enteringAndExitingTheMine.getUnitName()+"提交了"+enteringAndExitingTheMine.getCurrentShift()+"班出勤人数";
            return toAjax(enteringAndExitingTheMineService.saveEnteringAndExitingTheMine(enteringAndExitingTheMine));
        }
    }

    /**
     * 修改人员入井出井日报录入
     */
    @Log(title = "人员入井出井日报录入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnteringAndExitingTheMine enteringAndExitingTheMine) {
        return toAjax(enteringAndExitingTheMineService.updateEnteringAndExitingTheMine(enteringAndExitingTheMine));
    }

    /**
     * 删除人员入井出井日报录入
     */
    @Log(title = "人员入井出井日报录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(enteringAndExitingTheMineService.deleteEnteringAndExitingTheMineByIds(ids));
    }
    /**
     * 查询人员3班是否都录入
     */
    @GetMapping("/threeList")
    public AjaxResult threeList(EnteringAndExitingTheMine enteringAndExitingTheMine) {
        List<EnteringAndExitingTheMine> list = enteringAndExitingTheMineService.listEnteringAndExitingTheMine(enteringAndExitingTheMine);
        MinePlanThree ju=new MinePlanThree();
        for (EnteringAndExitingTheMine vo: list) {
            if(vo.getCurrentShift() ==1 ){
                ju.setOneClass(0);
            }
            if(vo.getCurrentShift() ==2 ){
                ju.setTwoClass(0);
            }
            if(vo.getCurrentShift() ==3 ){
                ju.setThreeClass(0);
            }
        }
        return success(ju);
    }

}
