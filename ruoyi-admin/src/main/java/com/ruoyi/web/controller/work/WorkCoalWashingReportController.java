package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.work.WorkCoalStockSalesStat;
import com.ruoyi.system.domain.work.WorkCoalWashingReport;
import com.ruoyi.system.domain.work.WorkCoalWashingReportList;
import com.ruoyi.system.domain.work.WorkCoalWashingReportSub;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.mapper.BaoBiao.MiningAreaCategoryMapper;
import com.ruoyi.system.service.work.IWorkCoalStockSalesStatService;
import com.ruoyi.system.service.work.IWorkCoalWashingReportService;
import com.ruoyi.system.service.work.IWorkCoalWashingReportSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 洗煤数据填报Controller
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkCoalWashingReportController")
public class WorkCoalWashingReportController extends BaseController {
    @Autowired
    private IWorkCoalWashingReportService workCoalWashingReportService;
    @Autowired
    private IWorkCoalWashingReportSubService workCoalWashingReportSubService;//备注
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    @Autowired
    private IWorkCoalStockSalesStatService workCoalStockSalesStatService;//煤炭库存销售统计Controller
    @Resource
    private MiningAreaCategoryMapper miningAreaCategoryMapper;
    /**
     * 查询洗煤数据填报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkCoalWashingReport workCoalWashingReport) {
        List<WorkCoalWashingReport> list = workCoalWashingReportService.listWorkCoalWashingReport(workCoalWashingReport);
        if(workCoalWashingReport.getUnitName() ==null){
            List<WorkCoalWashingReport> list1 =new ArrayList<>();
            FactoryArchive factoryArch = new FactoryArchive();
            factoryArch.setIsSealed(0);
            List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);

            for (FactoryArchive factory : factoryArchives){
                WorkCoalWashingReport workCoalWashingReport2 = list.stream().filter(po -> po != null).filter(item -> factory.getFactoryName().equals(item.getUnitName())).findFirst().orElse(new WorkCoalWashingReport());//去年全公司1-10月完成
                if(workCoalWashingReport2.getUnitName()==null){
                    WorkCoalWashingReport workCoalWashingReport1=new WorkCoalWashingReport();
                    workCoalWashingReport1.setUnitName(factory.getFactoryName());
                    workCoalWashingReport1.setUnitCode(factory.getFactoryCode());
                    list1.add(workCoalWashingReport1);
                }else {
                    if (workCoalWashingReport2.getState() != null && workCoalWashingReport2.getState() == 2) {
                        WorkCoalWashingReport workCoalWashingReport1=new WorkCoalWashingReport();
                        workCoalWashingReport1.setUnitName(factory.getFactoryName());
                        workCoalWashingReport1.setUnitCode(factory.getFactoryCode());
                        list1.add(workCoalWashingReport1);
                    }else {
                        list1.add(workCoalWashingReport2);
                    }
                }
            }
            return getDataTable(list1);
        }
        return getDataTable(list);
    }

    /**
     * 查询洗煤数据填报列表--局端查看
     */
    @GetMapping("/Alllist")
    public AjaxResult Alllist(WorkCoalWashingReport workCoalWashingReport) {
        List<WorkCoalWashingReport> 当日 = workCoalWashingReportService.listWorkCoalWashingReport(workCoalWashingReport);
        List<WorkCoalWashingReport> 当月 = workCoalWashingReportService.WorkCoalWashingReportlist(workCoalWashingReport);
        WorkCoalWashingReportSub sub=new WorkCoalWashingReportSub();
        sub.setReportTime(workCoalWashingReport.getReportTime());
        WorkCoalWashingReportSub workCoalWashingReportSub = workCoalWashingReportSubService.WorkCoalWashingReportSub(sub);
        WorkCoalWashingReportList quankuang=new WorkCoalWashingReportList();
//        MiningAreaCategory fac = new MiningAreaCategory();
//        fac.setLevel(1);
//        fac.setIsSealed(0);
//        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryMapper.selectList(fac);

        FactoryArchive factoryArch = new FactoryArchive();
        factoryArch.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);
        List<WorkCoalWashingReport> 当日数据=new ArrayList<>();
        List<WorkCoalWashingReport> 当月数据=new ArrayList<>();
        for (FactoryArchive fact : factoryArchives) {
            WorkCoalWashingReport workCoalWashingReport1 = 当日.stream().filter(item -> fact.getFactoryName().equals(item.getUnitName())).findFirst().orElse(new WorkCoalWashingReport());
            WorkCoalWashingReport workCoalWashingReport2 = 当月.stream().filter(item -> fact.getFactoryName().equals(item.getUnitName())).findFirst().orElse(new WorkCoalWashingReport());


            if(workCoalWashingReport1.getUnitName() !=null){
                // 当日数据：确保三个率值保留 2 位小数
                if (workCoalWashingReport1.getWashingInput() != null && workCoalWashingReport1.getWashingInput() > 0) {
                    // 精煤产率：(精煤 / 洗入量) * 100，保留 2 位小数
                    BigDecimal cleanCoal = workCoalWashingReport1.getCleanCoal() != null ? BigDecimal.valueOf(workCoalWashingReport1.getCleanCoal()) : BigDecimal.ZERO;
                    BigDecimal washedLumpCoal = workCoalWashingReport1.getWashedLumpCoal() != null ? BigDecimal.valueOf(workCoalWashingReport1.getWashedLumpCoal()) : BigDecimal.ZERO;
                    BigDecimal washedFineCoal = workCoalWashingReport1.getWashedFineCoal() != null ? BigDecimal.valueOf(workCoalWashingReport1.getWashedFineCoal()) : BigDecimal.ZERO;
                    BigDecimal washingConsumption = workCoalWashingReport1.getWashingConsumption() != null ? BigDecimal.valueOf(workCoalWashingReport1.getWashingConsumption()) : BigDecimal.ZERO;
                    BigDecimal washingInput = BigDecimal.valueOf(workCoalWashingReport1.getWashingInput());
                    
                    workCoalWashingReport1.setCleanCoalYield(cleanCoal
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                    // 综合产率：(精煤 + 洗块煤 + 洗末煤) / 洗入量 * 100，保留 2 位小数
                    workCoalWashingReport1.setComprehensiveYield(cleanCoal.add(washedLumpCoal).add(washedFineCoal)
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                    // 洗耗率：(洗耗 / 洗入量) * 100，保留 2 位小数
                    workCoalWashingReport1.setWashingConsumptionRate(washingConsumption
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                }
                当日数据.add(workCoalWashingReport1);
            }else {
                WorkCoalWashingReport workCoalWashingReport1_1 = new WorkCoalWashingReport();
                workCoalWashingReport1_1.setUnitName(fact.getFactoryName());
                当日数据.add(workCoalWashingReport1_1);
            }
            if(workCoalWashingReport2.getUnitName() !=null){
                if (workCoalWashingReport2.getWashingInput() != null && workCoalWashingReport2.getWashingInput() > 0) {
                    // 精煤产率：(精煤 / 洗入量) * 100，保留 2 位小数
                    BigDecimal cleanCoal = workCoalWashingReport2.getCleanCoal() != null ? BigDecimal.valueOf(workCoalWashingReport2.getCleanCoal()) : BigDecimal.ZERO;
                    BigDecimal washedLumpCoal = workCoalWashingReport2.getWashedLumpCoal() != null ? BigDecimal.valueOf(workCoalWashingReport2.getWashedLumpCoal()) : BigDecimal.ZERO;
                    BigDecimal washedFineCoal = workCoalWashingReport2.getWashedFineCoal() != null ? BigDecimal.valueOf(workCoalWashingReport2.getWashedFineCoal()) : BigDecimal.ZERO;
                    BigDecimal washingConsumption = workCoalWashingReport2.getWashingConsumption() != null ? BigDecimal.valueOf(workCoalWashingReport2.getWashingConsumption()) : BigDecimal.ZERO;
                    BigDecimal washingInput = BigDecimal.valueOf(workCoalWashingReport2.getWashingInput());
                    
                    workCoalWashingReport2.setCleanCoalYield(cleanCoal
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                    // 综合产率：(精煤 + 洗块煤 + 洗末煤) / 洗入量 * 100，保留 2 位小数
                    workCoalWashingReport2.setComprehensiveYield(cleanCoal.add(washedLumpCoal).add(washedFineCoal)
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                    // 洗耗率：(洗耗 / 洗入量) * 100，保留 2 位小数
                    workCoalWashingReport2.setWashingConsumptionRate(washingConsumption
                            .divide(washingInput, 6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .setScale(2, RoundingMode.HALF_UP));
                }

                当月数据.add(workCoalWashingReport2);
            }else {
                WorkCoalWashingReport workCoalWashingReport2_1 = new WorkCoalWashingReport();
                workCoalWashingReport2_1.setUnitName(fact.getFactoryName());
                当月数据.add(workCoalWashingReport2_1);
            }
        }
        quankuang.setList(当日数据);
        quankuang.setMonthList(当月数据);
        if(workCoalWashingReportSub !=null){
            quankuang.setRemarks(workCoalWashingReportSub.getRemarks());
        }else{
            String rateResult = 当日.stream().filter(plan -> plan != null  && plan.getState() == 0 && plan.getRemarks() != null).map(plan -> plan.getRemarks().toString()).collect(Collectors.joining(" "));
            quankuang.setRemarks(rateResult);
        }
        return success(quankuang);
    }
    /**
     * 新增洗煤数据填报
     */
    @Log(title = "洗煤数据填报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkCoalWashingReport workCoalWashingReport) {
        WorkCoalWashingReport work=new WorkCoalWashingReport();
        work.setReportTime(workCoalWashingReport.getReportTime());
        work.setUnitCode(workCoalWashingReport.getUnitCode());
        work.setUnitName(workCoalWashingReport.getUnitName());
        List<WorkCoalWashingReport> list = workCoalWashingReportService.listWorkCoalWashingReport(work);
        if(list.size()>0){
            if(list.get(0).getState()==0){
                return AjaxResult.error("请联系局里进行驳回");
            }
            workCoalWashingReport.setState(0);
            workCoalWashingReport.setId(list.get(0).getId());
            //选煤厂库存统计表  插入数据或修改数据
            WorkCoalStockSalesStat workCoalStat = new WorkCoalStockSalesStat();
            workCoalStat.setRecordDate(workCoalWashingReport.getReportTime());
            workCoalStat.setCoalType(workCoalWashingReport.getUnitName());
            List<WorkCoalStockSalesStat> list3 = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workCoalStat);
            // 安全获取数值，避免空指针
            long totalInput = workCoalWashingReport.getTotalInput() != null ? workCoalWashingReport.getTotalInput() : 0L;
            long washingInput = workCoalWashingReport.getWashingInput() != null ? workCoalWashingReport.getWashingInput() : 0L;
            if(list3.size()>0){
                long l = totalInput - washingInput;
                WorkCoalStockSalesStat workCoalStockSalesStat = list3.get(0);
                workCoalStockSalesStat.setRawCoalDailyChange(l);//当日增减
                long l1 = workCoalStockSalesStat.getRawCoalPreviousStock() + totalInput - washingInput;
                workCoalStockSalesStat.setRawCoalCurrentStock(l1);
                workCoalStockSalesStatService.updateWorkCoalStockSalesStat(workCoalStockSalesStat);
            }else {
                long l = totalInput - washingInput;
                Date previousDay = getPreviousDay(workCoalWashingReport.getReportTime());//上一天日期
                workCoalStat.setRecordDate(previousDay);
                List<WorkCoalStockSalesStat> list4 = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workCoalStat);
                if(list4.size()>0){
                    WorkCoalStockSalesStat workCoalStockSalesStat = new WorkCoalStockSalesStat();
                    workCoalStockSalesStat.setCoalType(workCoalWashingReport.getUnitName());
                    workCoalStockSalesStat.setRecordDate(workCoalWashingReport.getReportTime());//设置记录日期
                    workCoalStockSalesStat.setRawCoalDailyChange(l);//当日增减
                    long previousStock = list4.get(0).getRawCoalCurrentStock() != null ? list4.get(0).getRawCoalCurrentStock() : 0L;
                    long l1 = previousStock + totalInput - washingInput;
                    workCoalStockSalesStat.setRawCoalCurrentStock(l1);
                    workCoalStockSalesStatService.saveWorkCoalStockSalesStat(workCoalStockSalesStat);
                }else {
                    WorkCoalStockSalesStat workCoalStockSalesStat = new WorkCoalStockSalesStat();
                    workCoalStockSalesStat.setRecordDate(workCoalWashingReport.getReportTime());//设置记录日期
                    workCoalStockSalesStat.setCoalType(workCoalWashingReport.getUnitName());
                    workCoalStockSalesStat.setRawCoalDailyChange(l);//当日增减
                    workCoalStockSalesStat.setRawCoalCurrentStock(l);
                    workCoalStockSalesStatService.saveWorkCoalStockSalesStat(workCoalStockSalesStat);
                }
            }
            return toAjax( workCoalWashingReportService.updateWorkCoalWashingReport(workCoalWashingReport));
        }else {
            workCoalWashingReport.setState(0);
            return toAjax(workCoalWashingReportService.saveWorkCoalWashingReport(workCoalWashingReport));
        }
    }
    /**
     * 洗煤数据退回
     */
    @Log(title = "洗煤数据退回", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        try {
            WorkCoalWashingReport workCoalWashingReportById = workCoalWashingReportService.getWorkCoalWashingReportById(Integer.valueOf(Math.toIntExact(id)));
            workCoalWashingReportById.setState(2);
            return toAjax(workCoalWashingReportService.updateWorkCoalWashingReport(workCoalWashingReportById));
        }catch (Exception e){
            return toAjax(0);
        }
    }
    /**
     * 新增洗煤数据填报备注
     */
    @Log(title = "洗煤数据填报备注", businessType = BusinessType.INSERT)
    @PostMapping("/addbz")
    public AjaxResult addbz(@RequestBody WorkCoalWashingReportSub workCoalWashingReportSub) {
        WorkCoalWashingReportSub sub=new WorkCoalWashingReportSub();
        sub.setReportTime(workCoalWashingReportSub.getReportTime());
        WorkCoalWashingReportSub sub1 = workCoalWashingReportSubService.WorkCoalWashingReportSub(sub);
        if(sub1!=null){
            sub1.setRemarks(workCoalWashingReportSub.getRemarks());
            return toAjax(workCoalWashingReportSubService.updateWorkCoalWashingReportSub(sub1));
        }else {
            return toAjax(workCoalWashingReportSubService.saveWorkCoalWashingReportSub(workCoalWashingReportSub));
        }
    }


    private Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

}

