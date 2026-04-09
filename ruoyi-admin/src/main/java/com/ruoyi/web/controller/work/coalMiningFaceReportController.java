package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.work.*;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.work.IWorkInstallationFaceDailyReportService;
import com.ruoyi.system.service.work.IWorkRoadwayRepairFaceDailyReportService;
import com.ruoyi.system.service.work.IWorkWithdrawalFaceDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采煤工作面安装、回撤巷修日报Controller
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/coalMiningFaceReportController")
public class coalMiningFaceReportController  extends BaseController {
    @Autowired
    private IWorkRoadwayRepairFaceDailyReportService workRoadwayRepairFaceDailyReportService;

    @Autowired
    private IWorkInstallationFaceDailyReportService workInstallationFaceDailyReportService;
    @Autowired
    private IWorkWithdrawalFaceDailyReportService workWithdrawalFaceDailyReportService;
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表

    /**
     * 查询回撤面日报列表
     */
    @GetMapping("/list")
    public AjaxResult list(CoalMiningFaceReport coalMiningFaceReport) {
        WorkInstallationFaceDailyReport 安装=new WorkInstallationFaceDailyReport();
        安装.setUnit(coalMiningFaceReport.getUnit());
        安装.setReportDate(coalMiningFaceReport.getReportDate());

        WorkWithdrawalFaceDailyReport 回撤=new WorkWithdrawalFaceDailyReport();
        回撤.setUnit(coalMiningFaceReport.getUnit());
        回撤.setReportDate(coalMiningFaceReport.getReportDate());

        WorkRoadwayRepairFaceDailyReport 巷修=new WorkRoadwayRepairFaceDailyReport();
        巷修.setUnit(coalMiningFaceReport.getUnit());
        巷修.setReportDate(coalMiningFaceReport.getReportDate());


        List<WorkInstallationFaceDailyReport> 安装面 = workInstallationFaceDailyReportService.listWorkInstallationFaceDailyReport(安装);
        List<WorkWithdrawalFaceDailyReport> 回撤面 = workWithdrawalFaceDailyReportService.listWorkWithdrawalFaceDailyReport(回撤);
        List<WorkRoadwayRepairFaceDailyReport> 巷修面 = workRoadwayRepairFaceDailyReportService.listWorkRoadwayRepairFaceDailyReport(巷修);


        if(coalMiningFaceReport.getUnit() !=null){
            coalMiningFaceReport.setWorkInstallationFaceDailyReport(安装面);
            coalMiningFaceReport.setWorkWithdrawalFaceDailyReport(回撤面);
            coalMiningFaceReport.setWorkRoadwayRepairFaceDailyReport(巷修面);
        }else {
            MiningAreaCategory fac = new MiningAreaCategory();
            fac.setLevel(1);
            fac.setIsSealed(0);
            List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名称
            for (MiningAreaCategory mining : miningAreaCategories) {
                if(安装面.stream().filter(x -> x.getUnit().equals(mining.getAreaName())).count() == 0){
                    WorkInstallationFaceDailyReport workInstallationFaceDailyReport = new WorkInstallationFaceDailyReport();
                    workInstallationFaceDailyReport.setUnit(mining.getAreaName());
                    安装面.add(workInstallationFaceDailyReport);
                }
                if(回撤面.stream().filter(x -> x.getUnit().equals(mining.getAreaName())).count() == 0){
                    WorkWithdrawalFaceDailyReport workWithdrawalFaceDailyReport = new WorkWithdrawalFaceDailyReport();
                    workWithdrawalFaceDailyReport.setUnit(mining.getAreaName());
                    回撤面.add(workWithdrawalFaceDailyReport);
                }
                if(巷修面.stream().filter(x -> x.getUnit().equals(mining.getAreaName())).count() == 0){
                    WorkRoadwayRepairFaceDailyReport workRoadwayRepairFaceDailyReport = new WorkRoadwayRepairFaceDailyReport();
                    workRoadwayRepairFaceDailyReport.setUnit(mining.getAreaName());
                    巷修面.add(workRoadwayRepairFaceDailyReport);
                }
            }

            coalMiningFaceReport.setWorkInstallationFaceDailyReport(安装面);
            coalMiningFaceReport.setWorkWithdrawalFaceDailyReport(回撤面);
            coalMiningFaceReport.setWorkRoadwayRepairFaceDailyReport(巷修面);
        }


        return success(coalMiningFaceReport);
    }

    /**
     * 新增回撤面日报
     */
    @Log(title = "回撤面日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoalMiningFaceReport coalMiningFaceReport) {

        try   {
            int 安装面 = workInstallationFaceDailyReportService.saveWorkInstallationFaceDailyReport(coalMiningFaceReport.getWorkInstallationFaceDailyReport());
            int 回撤面 = workWithdrawalFaceDailyReportService.saveWorkWithdrawalFaceDailyReport(coalMiningFaceReport.getWorkWithdrawalFaceDailyReport());
            int 巷修面 = workRoadwayRepairFaceDailyReportService.saveWorkRoadwayRepairFaceDailyReport(coalMiningFaceReport.getWorkRoadwayRepairFaceDailyReport());
            return  toAjax(1);
        }catch (Exception e){
            return AjaxResult.error("请联系局里进行退回");
        }
    }
}
