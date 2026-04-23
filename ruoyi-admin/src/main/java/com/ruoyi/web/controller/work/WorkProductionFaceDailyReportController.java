package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.work.WorkProductionFaceDailyReport;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.work.IWorkProductionFaceDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产面日报Controller
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkProductionFaceDailyReportController")
public class WorkProductionFaceDailyReportController extends BaseController {
    @Autowired
    private IWorkProductionFaceDailyReportService workProductionFaceDailyReportService;

    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    /**
     * 查询生产面日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkProductionFaceDailyReport workProductionFaceDailyReport) {
        List<WorkProductionFaceDailyReport> list = workProductionFaceDailyReportService.listWorkProductionFaceDailyReport(workProductionFaceDailyReport);
        if(workProductionFaceDailyReport.getUnit() !=null){
           return getDataTable(list);
       }else {
            List<WorkProductionFaceDailyReport> AllList=new ArrayList<>();
            MiningAreaCategory fac = new MiningAreaCategory();
            fac.setLevel(1);
            fac.setIsSealed(0);
            List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名称
            for (MiningAreaCategory mining : miningAreaCategories) {
                if(list.stream().filter(x -> x.getUnit().equals(mining.getAreaName())).count() > 0){
                    list.stream().filter(x -> x.getUnit().equals(mining.getAreaName())).forEach(AllList::add);
                }else {
                    WorkProductionFaceDailyReport workProduction = new WorkProductionFaceDailyReport();
                    workProduction.setUnit(mining.getAreaName());
                    workProduction.setUnit(mining.getAreaName());
                    AllList.add( workProduction);
                }
            }
            return getDataTable(AllList);
        }

    }

    /**
     * 导出生产面日报列表
     */
    @Log(title = "生产面日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkProductionFaceDailyReport workProductionFaceDailyReport) {
        List<WorkProductionFaceDailyReport> list = workProductionFaceDailyReportService.listWorkProductionFaceDailyReport(workProductionFaceDailyReport);
        ExcelUtil<WorkProductionFaceDailyReport> util = new ExcelUtil<WorkProductionFaceDailyReport>(WorkProductionFaceDailyReport.class);
        util.exportExcel(response, list, "生产面日报数据");
    }

    /**
     * 获取生产面日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workProductionFaceDailyReportService.getWorkProductionFaceDailyReportById(id));
    }

    /**
     * 新增生产面日报
     */
    @Log(title = "生产面日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkProductionFaceDailyReport> workProductionFaceDailyReport) {
        int saved = workProductionFaceDailyReportService.saveWorkProductionFaceDailyReport(workProductionFaceDailyReport);
        if (saved == 0) {
            return error("请联系局里进行退回");
        }

        return toAjax(saved);
    }

    /**
     * 修改生产面日报
     */
    @Log(title = "生产面日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkProductionFaceDailyReport workProductionFaceDailyReport) {
        return toAjax(workProductionFaceDailyReportService.updateWorkProductionFaceDailyReport(workProductionFaceDailyReport));
    }

    @Log(title = "退回生产面日报")
    @PostMapping("/backWorkProductionFaceDailyReport")
    public AjaxResult backWorkProductionFaceDailyReport(@RequestBody String json) {
        try {
            return workProductionFaceDailyReportService.backWorkProductionFaceDailyReport(json);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 删除生产面日报
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "生产面日报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workProductionFaceDailyReportService.deleteWorkProductionFaceDailyReportByIds(ids));
    }
}
