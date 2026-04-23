package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.po.SubMineDevelopmentDataPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.MinePlanDay;
import com.ruoyi.system.domain.work.AnQuan;
import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.domain.work.WorkProductionStatus;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMineDevelopmentDataMapper;
import com.ruoyi.system.mapper.MinePlanDayMapper;
import com.ruoyi.system.service.work.IWorkProductionDailyReportService;
import com.ruoyi.system.service.work.IWorkProductionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安全生产信息日报Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/AnQuanController")
public class AnQuanController extends BaseController {
    @Autowired
    private IWorkProductionStatusService workProductionStatusService;//子表 生产情况
    @Autowired
    private IWorkProductionDailyReportService workProductionDailyReportService;//主表情况
    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//队组工作日查询
    @Autowired
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成子表
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案

    /**
     * 新增安全生产信息日报
     */
    @Log(title = "安全生产信息日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnQuan anQuan) {
        WorkProductionDailyReport work=new WorkProductionDailyReport();
        work.setUnit(anQuan.getUnit());
        work.setUnitCode(anQuan.getUnitCode());
        work.setReportDate(anQuan.getReportDate());
        WorkProductionDailyReport workProductionDailyReport = workProductionDailyReportService.WorkProductionDailyReport(work);
        if(workProductionDailyReport !=null){
            work.setId(workProductionDailyReport.getId());
            work.setMineDailyAdvance(anQuan.getMineDailyAdvance());
            work.setMineTotalAdvance(anQuan.getMineTotalAdvance());
            work.setMineDailyDevelopment(anQuan.getMineDailyDevelopment());
            work.setMineTotalDevelopment(anQuan.getMineTotalDevelopment());
            work.setMovingStatus(anQuan.getMovingStatus());
            work.setEquipmentStatus(anQuan.getEquipmentStatus());
            work.setProductionImpact(anQuan.getProductionImpact());
            work.setSafetyStatus(anQuan.getSafetyStatus());
            work.setAttendanceStatus(anQuan.getAttendanceStatus());
            work.setOnDutyLeader(anQuan.getOnDutyLeader());
            work.setOneShift(anQuan.getOneShift());
            work.setTwoShift(anQuan.getTwoShift());
            work.setThreeShift(anQuan.getThreeShift());
            work.setLeaderSign(anQuan.getLeaderSign());
            work.setDispatchSign(anQuan.getDispatchSign());
            work.setStatus(0);
            workProductionDailyReportService.updateWorkProductionDailyReport(work);
            WorkProductionStatus workStatus=new WorkProductionStatus();
            workStatus.setParentId(workProductionDailyReport.getId());
            List<WorkProductionStatus> workStatuses = workProductionStatusService.listWorkProductionStatus(workStatus);
            for (WorkProductionStatus workPoduct:workStatuses) {
                WorkProductionStatus 日销售 = anQuan.getProductionStatus().stream().filter(item -> workPoduct.getUnit().equals(item.getUnit())).findFirst().orElse(new WorkProductionStatus());
                workPoduct.setMailySales(日销售.getMailySales());
                workProductionStatusService.updateWorkProductionStatus(workPoduct);
            }
        }
        else {
            work.setMineDailyAdvance(anQuan.getMineDailyAdvance());
            work.setMineTotalAdvance(anQuan.getMineTotalAdvance());
            work.setMineDailyDevelopment(anQuan.getMineDailyDevelopment());
            work.setMineTotalDevelopment(anQuan.getMineTotalDevelopment());
            work.setMovingStatus(anQuan.getMovingStatus());
            work.setEquipmentStatus(anQuan.getEquipmentStatus());
            work.setProductionImpact(anQuan.getProductionImpact());
            work.setSafetyStatus(anQuan.getSafetyStatus());
            work.setAttendanceStatus(anQuan.getAttendanceStatus());
            work.setOnDutyLeader(anQuan.getOnDutyLeader());
            work.setOneShift(anQuan.getOneShift());
            work.setTwoShift(anQuan.getTwoShift());
            work.setThreeShift(anQuan.getThreeShift());
            work.setLeaderSign(anQuan.getLeaderSign());
            work.setDispatchSign(anQuan.getDispatchSign());
            work.setStatus(0);
            workProductionDailyReportService.saveWorkProductionDailyReport(work);
            List<WorkProductionStatus> workProductionStatus  =new ArrayList<>();
            for (WorkProductionStatus workPoduct:anQuan.getProductionStatus()) {
                workPoduct.setParentId(work.getId());
                workProductionStatus.add(workPoduct);
            }
            workProductionStatusService.saveWorkProductionStatus(workProductionStatus);
        }
        return toAjax(1);
    }

    /**
     * 查询安全生产信息日报  矿端查看
     */
    @GetMapping("/list")
    public AjaxResult list(AnQuan anQuan) {
        WorkProductionDailyReport work=new WorkProductionDailyReport();
        work.setUnitCode(anQuan.getUnitCode());
        work.setReportDate(anQuan.getReportDate());
        WorkProductionDailyReport workProductionDailyReport = workProductionDailyReportService.WorkProductionDailyReport(work);
        if(workProductionDailyReport !=null){
            anQuan.setId(workProductionDailyReport.getId());
            anQuan.setStatus(workProductionDailyReport.getStatus());
            anQuan.setUnit(workProductionDailyReport.getUnit());
            anQuan.setUnitCode(workProductionDailyReport.getUnitCode());
            anQuan.setReportDate(workProductionDailyReport.getReportDate());
            anQuan.setMineDailyAdvance(workProductionDailyReport.getMineDailyAdvance());
            anQuan.setMineTotalAdvance(workProductionDailyReport.getMineTotalAdvance());
            anQuan.setMineDailyDevelopment(workProductionDailyReport.getMineDailyDevelopment());
            anQuan.setMineTotalDevelopment(workProductionDailyReport.getMineTotalDevelopment());
            anQuan.setMovingStatus(workProductionDailyReport.getMovingStatus());
            anQuan.setEquipmentStatus(workProductionDailyReport.getEquipmentStatus());
            anQuan.setProductionImpact(workProductionDailyReport.getProductionImpact());
            anQuan.setSafetyStatus(workProductionDailyReport.getSafetyStatus());
            anQuan.setAttendanceStatus(workProductionDailyReport.getAttendanceStatus());
            anQuan.setOnDutyLeader(workProductionDailyReport.getOnDutyLeader());
            anQuan.setOneShift(workProductionDailyReport.getOneShift());
            anQuan.setTwoShift(workProductionDailyReport.getTwoShift());
            anQuan.setThreeShift(workProductionDailyReport.getThreeShift());
            anQuan.setLeaderSign(workProductionDailyReport.getLeaderSign());
            anQuan.setDispatchSign(workProductionDailyReport.getDispatchSign());

            WorkProductionStatus workStatus=new WorkProductionStatus();
            workStatus.setParentId(workProductionDailyReport.getId());
            List<WorkProductionStatus> workStatuses = workProductionStatusService.listWorkProductionStatus(workStatus);

            List<WorkProductionStatus> all =new ArrayList<>();
            WorkProductionStatus status=new WorkProductionStatus();
            status.setUnit("合计");
            status.setDailyPlan(null);
            status.setDailyComplete(null);
            status.setMailySales(Long.valueOf(workStatuses.stream().filter(po -> po != null) .mapToInt(po -> Math.toIntExact(po.getMailySales())).sum()));
            status.setMonthlyPlanTotal(null);
            status.setMonthlyCompleteTotal(null);
            all.add(status);
            for (WorkProductionStatus wo:workStatuses) {
                all.add(wo);
            }
            anQuan.setProductionStatus(all);
        }
        else {
            List<WorkProductionStatus> workStatuses = new ArrayList<>();

            Date statsDate=anQuan.getReportDate();
            String yue1= DateUtils.returnDateDay(statsDate);
            String ri = DateUtils.returnDateRange(statsDate);
            int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

            MinePlanDay minePlanDay =new MinePlanDay();
            minePlanDay.setPlanType("生产");
            minePlanDay.setPlanMonth(yue1);
            minePlanDay.setPlanDay(day);
            minePlanDay.setAreaName(anQuan.getUnit());
            List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minePlanDay);

            for (MinePlanDay plan:日计划) {
                WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                workProductionStatus.setUnitCode(plan.getUnitCode());
                workProductionStatus.setUnit(plan.getUnitName());
                workStatuses.add(workProductionStatus);
            }

            if(anQuan.getUnitCode().equals(("101200"))){//胜利煤矿
                FactoryArchive factoryArch = new FactoryArchive();
                factoryArch.setIsSealed(0);
                List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);
                for (FactoryArchive fact:factoryArchives) {
                    WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                    workProductionStatus.setUnitCode(fact.getFactoryCode());
                    workProductionStatus.setUnit("一井"+fact.getFactoryName());
                    workStatuses.add(workProductionStatus);
                }
                for (FactoryArchive fact:factoryArchives) {
                    WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                    workProductionStatus.setUnitCode(fact.getFactoryCode());
                    workProductionStatus.setUnit("六井"+fact.getFactoryName());
                    workStatuses.add(workProductionStatus);
                }
                anQuan.setProductionStatus(workStatuses);
            } else if(anQuan.getUnitCode().equals(("101000"))){//向阳煤矿
                FactoryArchive factoryArch = new FactoryArchive();
                factoryArch.setIsSealed(0);
                List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);
                for (FactoryArchive fact:factoryArchives) {
                    WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                    workProductionStatus.setUnitCode(fact.getFactoryCode());
                    workProductionStatus.setUnit("二井"+fact.getFactoryName());
                    workStatuses.add(workProductionStatus);
                }
                for (FactoryArchive fact:factoryArchives) {
                    WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                    workProductionStatus.setUnitCode(fact.getFactoryCode());
                    workProductionStatus.setUnit("六井"+fact.getFactoryName());
                    workStatuses.add(workProductionStatus);
                }
                anQuan.setProductionStatus(workStatuses);
            }else {
                FactoryArchive factoryArch = new FactoryArchive();
                factoryArch.setIsSealed(0);
                List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);
                for (FactoryArchive fact:factoryArchives) {
                    WorkProductionStatus workProductionStatus=new WorkProductionStatus();
                    workProductionStatus.setUnitCode(fact.getFactoryCode());
                    workProductionStatus.setUnit(fact.getFactoryName());
                    workStatuses.add(workProductionStatus);
                }
                anQuan.setProductionStatus(workStatuses);
            }

            WorkProductionStatus workProductionStatus=new WorkProductionStatus();
            workProductionStatus.setUnitCode("000000");
            workProductionStatus.setUnit("其他");
            workStatuses.add(workProductionStatus);
            anQuan.setProductionStatus(workStatuses);

        }

        return success(anQuan);
    }

    /**
     * 查询安全生产信息日报  局端查看
     */
    @GetMapping("/Alllist")
    public AjaxResult Alllist(AnQuan anQuan) {
        WorkProductionDailyReport work=new WorkProductionDailyReport();
        work.setUnitCode(anQuan.getUnitCode());
        work.setReportDate(anQuan.getReportDate());

        Date statsDate=anQuan.getReportDate();
        String yue1= DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        WorkProductionDailyReport workProductionDailyReport = workProductionDailyReportService.WorkProductionDailyReport(work);
        if(workProductionDailyReport !=null){
            MinePlanDay minePlanDay =new MinePlanDay();
            minePlanDay.setPlanType("生产");
            minePlanDay.setPlanMonth(yue1);
            minePlanDay.setPlanDay(day);
            minePlanDay.setAreaName(anQuan.getUnit());
            List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minePlanDay);
            List<MinePlanDay> 累计日计划 = minePlanDayMapper.selectMinePlanCumulativeDayList(minePlanDay);
            List<SubMineDevelopmentDataPO>  洗煤厂日完成=subMineDevelopmentDataMapper.selectDay(anQuan.getUnitCode(),statsDate);
            List<SubMineDevelopmentDataPO>  洗煤厂月完成=subMineDevelopmentDataMapper.selectMonth(anQuan.getUnitCode(),statsDate);

            WorkProductionStatus workStatus=new WorkProductionStatus();
            workStatus.setParentId(workProductionDailyReport.getId());
            List<WorkProductionStatus> 洗煤 = workProductionStatusService.listWorkProductionStatus(workStatus);


            List<WorkProductionStatus> workStatuses = new ArrayList<>();
            WorkProductionStatus 全矿=new WorkProductionStatus();
            全矿.setUnit("全矿");
            全矿.setDailyPlan(Long.valueOf(日计划.stream().filter(po -> po != null).mapToInt(po -> po.getDayPlan()).sum()));
            全矿.setDailyComplete(Long.valueOf(洗煤厂日完成.stream().filter(po -> po != null && "合计".equals(po.getUnitNameJSON()))
                    .mapToInt(po -> po.getProductionData()).sum()));
            全矿.setMailySales(Long.valueOf(洗煤.stream().filter(po -> po != null) .mapToInt(po -> Math.toIntExact(po.getMailySales())).sum()));
            全矿.setMonthlyPlanTotal(Long.valueOf(累计日计划.stream().filter(po -> po != null) .mapToInt(po -> po.getDayPlan()).sum()));//累计日计划
            全矿.setMonthlyCompleteTotal(Long.valueOf(洗煤厂月完成.stream().filter(po -> po != null && "合计".equals(po.getUnitNameJSON())) .mapToInt(po -> po.getProductionData()).sum()));
            workStatuses.add(全矿);

            for (WorkProductionStatus day1:洗煤) {
                WorkProductionStatus 队组=new WorkProductionStatus();
                long rijihuaValue=Long.valueOf(日计划.stream().filter(po -> po != null).filter(po -> day1.getUnit().equals(po.getUnitName())) .mapToInt(po -> po.getDayPlan()).sum());
                long riwanchengValue=Long.valueOf(洗煤厂日完成.stream().filter(po -> po != null).filter(po -> day1.getUnit().equals(po.getUnitNameJSON())).mapToInt(po -> po.getProductionData()).sum());
                long leijijihuaValue=Long.valueOf(Long.valueOf(累计日计划.stream().filter(po -> po != null) .filter(po -> day1.getUnit().equals(po.getUnitName())).mapToInt(po -> po.getDayPlan()).sum()));
                long leiwanchengValue=Long.valueOf(Long.valueOf(洗煤厂月完成.stream().filter(po -> po != null).filter(po -> day1.getUnit().equals(po.getUnitNameJSON())).mapToInt(po -> po.getProductionData()).sum()));
                Long rijihua = (rijihuaValue == 0) ? null : rijihuaValue;
                Long riwancheng = (riwanchengValue == 0) ? null : riwanchengValue;
                Long leijijihua = (leijijihuaValue == 0) ? null : leijijihuaValue;
                Long leiwancheng = (leiwanchengValue == 0) ? null : leiwanchengValue;

                队组.setUnit(day1.getUnit());
                队组.setUnitCode(day1.getUnitCode());
                队组.setDailyPlan(rijihua);
                队组.setDailyComplete(riwancheng);
                队组.setMailySales(day1.getMailySales());
                队组.setMonthlyPlanTotal(leijijihua);//累计日计划
                队组.setMonthlyCompleteTotal(leiwancheng);
                workStatuses.add(队组);
            }
            anQuan.setProductionStatus(workStatuses);
            List<MineData> 月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(anQuan.getReportDate()));
            List<MineData> 日完成 = subMineDevelopmentDataMapper.selectDayDate(DateUtils.returnDateRange(anQuan.getReportDate()));

            BigDecimal jcSum = 月完成.stream().filter(item -> anQuan.getUnit().equals(item.getUnitName())).filter(po -> po.getFootageData() != null).map(po -> po.getFootageData()).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal ktSum = 月完成.stream().filter(item -> anQuan.getUnit().equals(item.getUnitName())).filter(po -> po.getExpandData() != null).map(po -> po.getExpandData()).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal jc = 日完成.stream().filter(item -> anQuan.getUnit().equals(item.getUnitName())).filter(po -> po.getFootageData() != null).map(po -> po.getFootageData()).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal kt = 日完成.stream().filter(item -> anQuan.getUnit().equals(item.getUnitName())).filter(po -> po.getExpandData() != null).map(po -> po.getExpandData()).reduce(BigDecimal.ZERO, BigDecimal::add);
            anQuan.setId(workProductionDailyReport.getId());
            anQuan.setUnit(workProductionDailyReport.getUnit());
            anQuan.setUnitCode(workProductionDailyReport.getUnitCode());

            anQuan.setMineDailyAdvance(jc);
            anQuan.setMineTotalAdvance(jcSum);
            anQuan.setMineDailyDevelopment(kt);
            anQuan.setMineTotalDevelopment( ktSum);
            anQuan.setMovingStatus(workProductionDailyReport.getMovingStatus());
            anQuan.setEquipmentStatus(workProductionDailyReport.getEquipmentStatus());
            anQuan.setProductionImpact(workProductionDailyReport.getProductionImpact());
            anQuan.setSafetyStatus(workProductionDailyReport.getSafetyStatus());
            anQuan.setAttendanceStatus(workProductionDailyReport.getAttendanceStatus());
            anQuan.setOnDutyLeader(workProductionDailyReport.getOnDutyLeader());
            anQuan.setOneShift(workProductionDailyReport.getOneShift());
            anQuan.setTwoShift(workProductionDailyReport.getTwoShift());
            anQuan.setThreeShift(workProductionDailyReport.getThreeShift());
            anQuan.setLeaderSign(workProductionDailyReport.getLeaderSign());
            anQuan.setDispatchSign(workProductionDailyReport.getDispatchSign());
            anQuan.setStatus(workProductionDailyReport.getStatus());
            anQuan.setProductionStatus(workStatuses);
        }
        return success(anQuan);
    }

    /**
     * 查询安全生产信息日报  矿端查看
     */
    @DeleteMapping("/{menuId}")
    public AjaxResult delet(@PathVariable("menuId") String menuId) {
        WorkProductionDailyReport workProductionDailyReport = workProductionDailyReportService.getWorkProductionDailyReportById(menuId);
        if(workProductionDailyReport !=null){
            workProductionDailyReport.setStatus(2);
            return toAjax(workProductionDailyReportService.updateWorkProductionDailyReport(workProductionDailyReport));
        }
        return toAjax(0);
    }
}
