package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WDailyReport;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 龙煤股份煤炭营销分公司调度日报一Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/reportMarketing")
public class WDailyReportController extends BaseController {
    @Autowired
    private IWDailyReportService wDailyReportService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WDailyReport wDailyReport) {
        List<WDailyReport> list = wDailyReportService.listWDailyReport(wDailyReport);
        return getDataTable(list);
    }

    /**
     * 查询龙煤股份煤炭营销分公司调度日报一列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WDailyReport wDailyReport) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("龙煤股份煤炭营销分公司调度日报一");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wDailyReport.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WDailyReport> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WDailyReport> list = wDailyReportService.listWDailyReport(wDailyReport);
            return getDataTable(list);
        }
    }
    /**
     * 查询龙煤股份煤炭营销分公司调度日报一状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("龙煤股份煤炭营销分公司调度日报一");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WDailyReport wDailyReport=new WDailyReport();
            wDailyReport.setStatsDate(tiaojian.getStatsDate());
            List<WDailyReport> list = wDailyReportService.listWDailyReport(wDailyReport);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出龙煤股份煤炭营销分公司调度日报一列表
     */
    @Log(title = "龙煤股份煤炭营销分公司调度日报一", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WDailyReport wDailyReport) {
        List<WDailyReport> list = wDailyReportService.listWDailyReport(wDailyReport);
        ExcelUtil<WDailyReport> util = new ExcelUtil<WDailyReport>(WDailyReport.class);
        util.exportExcel(response, list, "龙煤股份煤炭营销分公司调度日报一数据");
    }

    /**
     * 获取龙煤股份煤炭营销分公司调度日报一详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(wDailyReportService.getWDailyReportById(id));
    }

    /**
     * 新增龙煤股份煤炭营销分公司调度日报一
     */
    @Log(title = "龙煤股份煤炭营销分公司调度日报一", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WDailyReport> wDailyReportList) {
        if (wDailyReportList == null || wDailyReportList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WDailyReport firstReport = wDailyReportList.get(0);
        WDailyReport wDailyReport = new WDailyReport();
        wDailyReport.setStatsDate(firstReport.getStatsDate());
        List<WDailyReport> list = wDailyReportService.listWDailyReport(wDailyReport);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("龙煤股份煤炭营销分公司调度日报一");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstReport.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WDailyReport report : wDailyReportList) {
                    if(report.getId()!=null&&!"".equals(report.getId())){
                        result += wDailyReportService.updateWDailyReport(report);
                    }else{
                        result += wDailyReportService.saveWDailyReport(report);
                    }
                }
                return toAjax(result);
            }

            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WDailyReport report : wDailyReportList) {
                result += wDailyReportService.saveWDailyReport(report);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改龙煤股份煤炭营销分公司调度日报一
     */
    @Log(title = "龙煤股份煤炭营销分公司调度日报一", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WDailyReport wDailyReport) {
        return toAjax(wDailyReportService.updateWDailyReport(wDailyReport));
    }

    /**
     * 删除龙煤股份煤炭营销分公司调度日报一
     */
    @Log(title = "龙煤股份煤炭营销分公司调度日报一", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wDailyReportService.deleteWDailyReportByIds(ids));
    }



    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("龙煤股份煤炭营销分公司调度日报一");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return AjaxResult.error("已经退回");
        }else {
            mineInfoService.saveMineInfo(mineInfo);
            return AjaxResult.success("退回成功");
        }
    }
}
