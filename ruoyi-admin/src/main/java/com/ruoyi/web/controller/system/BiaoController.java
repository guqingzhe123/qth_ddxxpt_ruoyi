package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Biaodan.Production;
import com.ruoyi.system.domain.Biaodan.StatisticsFootage;
import com.ruoyi.system.domain.Biaodan.SummaryTable;
import com.ruoyi.system.domain.Biaodan.TargetAll;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.domain.work.CompletionRawCoalGeneration;
import com.ruoyi.system.domain.work.SafetyInfluencingFactors;
import com.ruoyi.system.domain.work.StatisticalTableRawCoal;
import com.ruoyi.system.service.PingBao.IShengChanRiBaoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日报Controller
 *
 * @author ruoyi
 *  2025-11-06
 */
@Api(tags = "表单")
@RestController
@RequestMapping("/system/ribao")
public class BiaoController extends BaseController {

    @Autowired
    private IShengChanRiBaoService service;

    /**
     * 公司数据报表-生产指标完成情况
     */
    @Anonymous
    @Operation(summary = "生产指标完成情况")
    @PostMapping("/ProductionCompleted")
    public TableDataInfo<BaseEntity> ProductionCompleted(@RequestBody riBao tiaojian) {
        List<Production> list = service.ProductionCompleted(tiaojian.getStatsDate());
        return getDataTable(list);
    }

    /**
     * 公司数据报表-生产指标完成情况统计表(上报龙煤)
     */
    @Anonymous
    @Operation(summary = "生产指标完成情况统计表(上报龙煤)")
    @PostMapping("/statisticsProductionTarget")
    public AjaxResult statisticsProductionTargetCompletion(@RequestBody riBao tiaojian) {
        TargetAll list = service.statisticsProductionTargetCompletion(tiaojian.getStatsDate());
        return success(list);
    }
    /**
     * 公司数据报表-生产汇总表
     */
    @Anonymous
    @Operation(summary = "生产汇总表")
    @PostMapping("/ProductionSummaryTable")
    public TableDataInfo<BaseEntity> ProductionSummaryTable(@RequestBody riBao tiaojian) {
        List<SummaryTable> list = service.ProductionSummaryTable(tiaojian.getStatsDate());
        return getDataTable(list);
    }
    /**
     * 公司数据报表-总进尺实际完成统计表
     */
    @Anonymous
    @Operation(summary = "总进尺实际完成统计表")
    @PostMapping("/actualCompletionStatisticsFootage")
    public AjaxResult actualCompletionStatisticsFootage(@RequestBody riBao tiaojian) {
        List<StatisticsFootage> list = service.actualCompletionStatisticsFootage(tiaojian.getStatsDate());
        return success(list);
    }
    /**
     * 公司数据报表-原煤产量统计表
     */
    @Anonymous
    @Operation(summary = "原煤产量统计表")
    @PostMapping("/statisticalTableRawCoalProduction")
    public AjaxResult statisticalTableRawCoalProduction(@RequestBody riBao tiaojian) {
        List<StatisticalTableRawCoal> list = service.statisticalTableRawCoalProduction(tiaojian.getStatsDate());
        return success(list);
    }


    /**
     * 公司数据报表-当月原煤生成完成情况统计表
     */
    @Anonymous
    @Operation(summary = "当月原煤生成完成情况统计表")
    @PostMapping("/StatisticalTableCompletionRawCoalGeneration")
    public AjaxResult StatisticalTableCompletionRawCoalGeneration(@RequestBody riBao tiaojian) {
        List<CompletionRawCoalGeneration> list = service.StatisticalTableCompletionRawCoalGeneration(tiaojian.getStatsDate());
        return success(list);
    }
    /**
     * 公司数据报表-原煤生成完成情况统计表
     */
    @Anonymous
    @Operation(summary = "累计原煤生成完成情况统计表当月")
    @PostMapping("/YearStatisticalTableCompletionRawCoalGeneration")
    public AjaxResult YearStatisticalTableCompletionRawCoalGeneration(@RequestBody riBao tiaojian) {
        List<CompletionRawCoalGeneration> list = service.YearStatisticalTableCompletionRawCoalGeneration(tiaojian.getStatsDate());
        return success(list);
    }

    /**
     * 公司数据报表-公司各单位影响安全生产因素
     */
    @Anonymous
    @Operation(summary = "公司各单位影响安全生产因素")
    @PostMapping("/CompanySafetyFactors")
    public AjaxResult CompanySafetyFactors(@RequestBody riBao tiaojian) {
        List<SafetyInfluencingFactors> list = service.CompanySafetyFactors(tiaojian.getStatsDate());
        return success(list);
    }








}
