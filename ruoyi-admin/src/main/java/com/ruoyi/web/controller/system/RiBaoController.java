package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.*;
import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;
import com.ruoyi.system.domain.BaoBiao.po.*;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.ribaobaobiao.*;
import com.ruoyi.system.domain.work.WorkCoalWashingReport;
import com.ruoyi.system.mapper.BaoBiao.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.BaoBiao.*;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.work.IWorkCoalWashingReportService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 日报Controller
 *
 * @author ruoyi
 * 2025-11-06
 */
@Api(tags = "日报")
@RestController
@RequestMapping("/system/ribao")
public class RiBaoController extends BaseController {
//    @Autowired
//    private MinePlanMapper minePlanMapper;//计划主表
    @Autowired
    private SubMinePlanMapper subMinePlanMapper;//计划子表
    @Autowired
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成子表
    @Autowired
    private WashCoalPlanMapper washCoalPlanMapper;//洗煤计划主表
    @Autowired
    private SubWashCoalPlanMapper subWashCoalPlanMapper;//洗煤计划子表
    @Autowired
    private IWorkCoalWashingReportService workCoalWashingReportService;
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    @Autowired
    private RawCoalToDailyReportMapper rawCoalToDailyReportMapper;
    @Autowired
    private SubCoalProductInventoryMapper subCoalProductInventoryMapper;//洗煤厂库存及自用煤录入
    @Autowired
    private SubInitialInventoryOfEachMineMapper subeachMineMapper;//各矿日实际产量录入表 和 各矿期初库存录入表 录入子表
    @Autowired
    private DestinationOfRawCoalMapper destinationOfRawCoalMapper;//原煤去向录入表
    @Autowired
    private ComprehensiveProductionStatsMapper comprehensiveProductionStatsMapper;//调度日报其他数据Service业务层处理
    @Autowired
    private CokeSalesPlanMapper cokeSalesPlanMapper;//焦炭销售计划
    @Autowired
    private ISysDeptService deptService;//部门对象
    @Autowired
    private LongmeiProductionSalesStatsMapper longmei;//龙煤集团各分公司生产外销统计表
    @Resource
    private IEnteringExitingMineService service08;//08 报表专用
    @Resource
    private IMineDayCumReportV2Service service10;//10报表专用
    @Resource
    private IMineDayCumReportV3Service service11;//11报表专用
    @Resource
    private IOtherDataReportV1Service service11other;//11报表专用
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作日Mapper接口
    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//每日计划  煤矿自己看自己的计划


    //------------------可能废弃
    @Autowired
    private CoalWashingProductionMapper coalWashingProductionMapper;//洗煤生产录入表

    /**
     * 查询生产日报 煤矿自己看
     */
    @Anonymous
    @Operation(summary = "查询生产日报")
    @PostMapping("/ShengChanlist")
    public TableDataInfo<BaseEntity> ShengChanlist(@RequestBody tiaoJianRiBao tiaojian) {
        List<shengChanRiBao> list = new ArrayList<>();
        String unitCode = tiaojian.getUnitCode();
        Date statsDate = tiaojian.getStatsDate();
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        MiningAreaCategory areaName = miningAreaCategoryService.getAreaCode(unitCode);//单位名称

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setAreaName(areaName.getAreaName());
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanOneYear(statsDate, "生产",areaName.getAreaName());
        List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(unitCode, statsDate);

        shengChanRiBao 合计=new shengChanRiBao();
        合计.setUnitName("合计");
        合计.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayPlan())).sum()));
        合计.setDayComplete(BigDecimal.valueOf(日完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")) .mapToInt(po -> toInt(po.getProductionData())).sum()));
        合计.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayPlan())).sum()));
        合计.setMonthComplete(BigDecimal.valueOf(月完成.stream().filter(po -> po != null  && po.getUnitNameJSON().equals("合计")) .mapToInt(po -> toInt(po.getProductionData())).sum()));
        合计.setYearPlan(BigDecimal.valueOf(年计划.stream().filter(po -> po != null  && !po.getUnitName().equals("计划总量") ) .mapToInt(po -> toInt(po.getMonthPlan())).sum()));
        合计.setYearComplete(BigDecimal.valueOf(年完成.stream().filter(po -> po != null  && po.getUnitNameJSON().equals("合计")) .mapToInt(po -> toInt(po.getProductionData())).sum()));
        list.add(合计);

        for (MinePlanDay planDay : 日计划) {
            shengChanRiBao ribao = new shengChanRiBao();
            ribao.setUnitName(planDay.getUnitName());
            ribao.setDayPlan(BigDecimal.valueOf(planDay.getDayPlan()));
            MinePlanDay 月计划内容 = 月计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new MinePlanDay());
            if (月计划内容.getDayPlan() != null) {
                ribao.setMonthPlan(BigDecimal.valueOf(月计划内容.getDayPlan()));
            } else {
                ribao.setMonthPlan(BigDecimal.valueOf(0));
            }
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthPlan() != null) {
                ribao.setYearPlan(年计划内容.getMonthPlan());
            } else {
                ribao.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getProductionData() != null) {
                ribao.setDayComplete(BigDecimal.valueOf(日完成内容.getProductionData()));
            } else {
                ribao.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getProductionData() != null) {
                ribao.setMonthComplete(BigDecimal.valueOf(月完成内容.getProductionData()));
            } else {
                ribao.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getProductionData() != null) {
                ribao.setYearComplete(BigDecimal.valueOf(年完成内容.getProductionData()));
            } else {
                ribao.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(ribao);
        }


        return getDataTable(list);
    }
    /**
     * 查询进尺日报 煤矿自己看
     */
    @PostMapping("/JinChilist")
    public TableDataInfo<BaseEntity> JinChilist(@RequestBody tiaoJianRiBao tiaojian) {
        String unitCode = tiaojian.getUnitCode();
        Date statsDate = tiaojian.getStatsDate();
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        MiningAreaCategory areaName = miningAreaCategoryService.getAreaCode(unitCode);//单位名称
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("进尺");
        minday.setAreaName(areaName.getAreaName());
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanOneYear(statsDate, "进尺",areaName.getAreaName());
        List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(unitCode, statsDate);
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计=new shengChanRiBao();
        合计.setUnitName("合计");
        合计.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(po -> po != null).mapToInt(MinePlanDay::getDayPlan).sum()));
        合计.setDayComplete(日完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getFootageData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(po -> po != null).mapToInt(MinePlanDay::getDayPlan).sum()));
        合计.setMonthComplete(月完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getFootageData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setYearPlan(年计划.stream().filter(po -> po != null && !po.getUnitName().equals("计划总量")).map(SubMinePlanPO::getMonthPlan).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setYearComplete(年完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getFootageData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        list.add(合计);

        for (MinePlanDay planDay : 日计划) {
            shengChanRiBao ribao = new shengChanRiBao();
            ribao.setUnitName(planDay.getUnitName());
            ribao.setDayPlan(BigDecimal.valueOf(planDay.getDayPlan()));
            MinePlanDay 月计划内容 = 月计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new MinePlanDay());
            if (月计划内容.getDayPlan() != null) {
                ribao.setMonthPlan(BigDecimal.valueOf(月计划内容.getDayPlan()));
            } else {
                ribao.setMonthPlan(BigDecimal.valueOf(0));
            }
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthPlan() != null) {
                ribao.setYearPlan(年计划内容.getMonthPlan());
            } else {
                ribao.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getFootageData() != null) {
                ribao.setDayComplete(日完成内容.getFootageData());
            } else {
                ribao.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getFootageData() != null) {
                ribao.setMonthComplete(月完成内容.getFootageData());
            } else {
                ribao.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getFootageData() != null) {
                ribao.setYearComplete(年完成内容.getFootageData());
            } else {
                ribao.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(ribao);
        }
        return getDataTable(list);
    }

    /**
     * 查询开拓日报 煤矿自己看
     */
    @PostMapping("/KaiTuolist")
    public TableDataInfo<BaseEntity> Kaituolist(@RequestBody tiaoJianRiBao tiaojian) {
        String unitCode = tiaojian.getUnitCode();
        Date statsDate = tiaojian.getStatsDate();
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        MiningAreaCategory areaName = miningAreaCategoryService.getAreaCode(unitCode);//单位名称
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("开拓");
        minday.setAreaName(areaName.getAreaName());
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanOneYear(statsDate, "开拓",areaName.getAreaName());

        List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(unitCode, statsDate);
        List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(unitCode, statsDate);
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计=new shengChanRiBao();
        合计.setUnitName("合计");
        合计.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(po -> po != null).mapToInt(MinePlanDay::getDayPlan).sum()));
        合计.setDayComplete(日完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getExpandData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(po -> po != null).mapToInt(MinePlanDay::getDayPlan).sum()));
        合计.setMonthComplete(月完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getExpandData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setYearPlan(年计划.stream().filter(po -> po != null && !po.getUnitName().equals("计划总量")).map(SubMinePlanPO::getMonthPlan).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        合计.setYearComplete(年完成.stream().filter(po -> po != null && po.getUnitNameJSON().equals("合计")).map(SubMineDevelopmentDataPO::getExpandData).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        list.add(合计);

        for (MinePlanDay planDay : 日计划) {
            shengChanRiBao ribao = new shengChanRiBao();
            ribao.setUnitName(planDay.getUnitName());
            ribao.setDayPlan(BigDecimal.valueOf(planDay.getDayPlan()));
            MinePlanDay 月计划内容 = 月计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new MinePlanDay());
            if (月计划内容.getDayPlan() != null) {
                ribao.setMonthPlan(BigDecimal.valueOf(月计划内容.getDayPlan()));
            } else {
                ribao.setMonthPlan(BigDecimal.valueOf(0));
            }
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> planDay.getUnitName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthPlan() != null) {
                ribao.setYearPlan(年计划内容.getMonthPlan());
            } else {
                ribao.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getExpandData() != null) {
                ribao.setDayComplete(日完成内容.getExpandData());
            } else {
                ribao.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getExpandData() != null) {
                ribao.setMonthComplete(月完成内容.getExpandData());
            } else {
                ribao.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> planDay.getUnitName().equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getExpandData() != null) {
                ribao.setYearComplete(年完成内容.getExpandData());
            } else {
                ribao.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(ribao);
        }

        return getDataTable(list);
    }

    /**
     * 查询洗煤日报
     */
    @PostMapping("/XiMeilist")
    public TableDataInfo<BaseEntity> XiMeilist(@RequestBody tiaoJianRiBao tiaojian) {
        String unitCode = tiaojian.getUnitCode();
        Date statsDate = tiaojian.getStatsDate();
        SubWashCoalPlanPO 洗煤日月计划 = subWashCoalPlanMapper.selectByPlanMonth(statsDate, unitCode);
        SubWashCoalPlanPO 洗煤年计划 = subWashCoalPlanMapper.selectByPlanYear(statsDate, unitCode);
        WorkCoalWashingReport workReport = new WorkCoalWashingReport();
        workReport.setUnitCode(unitCode);
        workReport.setReportTime(statsDate);
        List<WorkCoalWashingReport> 日完成洗煤 = workCoalWashingReportService.listWorkCoalWashingReport(workReport);
        List<WorkCoalWashingReport> 月完成洗煤 = workCoalWashingReportService.WorkCoalWashingReportlist(workReport);
        List<WorkCoalWashingReport> 年完成洗煤 = workCoalWashingReportService.selectWorkCoalWashingReportYearList(workReport);
        WorkCoalWashingReport dailyReport = (日完成洗煤 != null && !日完成洗煤.isEmpty()) ? 日完成洗煤.get(0) : null;
        WorkCoalWashingReport monthlyReport = (月完成洗煤 != null && !月完成洗煤.isEmpty()) ? 月完成洗煤.get(0) : null;
        WorkCoalWashingReport yearlyReport = (年完成洗煤 != null && !年完成洗煤.isEmpty()) ? 年完成洗煤.get(0) : null;
        List<xiMeiRiBao> list = new ArrayList<>();
        xiMeiRiBao 本日 = new xiMeiRiBao();
        本日.setUnitName("本日");
        本日.setDropIn(dailyReport != null && dailyReport.getTotalInput() != null ? dailyReport.getTotalInput().intValue() : 0);
        本日.setWashIn(dailyReport != null && dailyReport.getWashingInput() != null ? dailyReport.getWashingInput().intValue() : 0);
        本日.setCleanCoal(dailyReport != null && dailyReport.getCleanCoal() != null ? dailyReport.getCleanCoal().intValue() : 0);
        本日.setLumpCoal(dailyReport != null && dailyReport.getWashedLumpCoal() != null ? dailyReport.getWashedLumpCoal().intValue() : 0);
        本日.setSlackCoal(dailyReport != null && dailyReport.getWashedFineCoal() != null ? dailyReport.getWashedFineCoal().intValue() : 0);
        本日.setSludgeCoal(0);
        本日.setAvailableGangue(0);
        本日.setWaste(0);
        本日.setTotal(dailyReport != null && dailyReport.getWashingConsumption() != null ? dailyReport.getWashingConsumption().intValue() : 0);
        本日.setWashInPlan(洗煤日月计划 != null ? 洗煤日月计划.getWashInDayPlan() : null);
        本日.setCleanCoalPlan(洗煤日月计划 != null ? 洗煤日月计划.getCleanCoalDayPlan() : null);
        list.add(本日);

        xiMeiRiBao 本月 = new xiMeiRiBao();
        本月.setUnitName("本月");
        本月.setDropIn(monthlyReport != null && monthlyReport.getTotalInput() != null ? monthlyReport.getTotalInput().intValue() : 0);
        本月.setWashIn(monthlyReport != null && monthlyReport.getWashingInput() != null ? monthlyReport.getWashingInput().intValue() : 0);
        本月.setCleanCoal(monthlyReport != null && monthlyReport.getCleanCoal() != null ? monthlyReport.getCleanCoal().intValue() : 0);
        本月.setLumpCoal(monthlyReport != null && monthlyReport.getWashedLumpCoal() != null ? monthlyReport.getWashedLumpCoal().intValue() : 0);
        本月.setSlackCoal(monthlyReport != null && monthlyReport.getWashedFineCoal() != null ? monthlyReport.getWashedFineCoal().intValue() : 0);
        本月.setSludgeCoal(0);
        本月.setAvailableGangue(0);
        本月.setWaste(0);
        本月.setTotal(monthlyReport != null && monthlyReport.getWashingConsumption() != null ? monthlyReport.getWashingConsumption().intValue() : 0);
        本月.setWashInPlan(洗煤日月计划 != null ? 洗煤日月计划.getWashInMonthPlan() : null);
        本月.setCleanCoalPlan(洗煤日月计划 != null ? 洗煤日月计划.getCleanCoalMonthPlan() : null);
        list.add(本月);

        xiMeiRiBao 本年 = new xiMeiRiBao();
        本年.setUnitName("本年");
        本年.setDropIn(yearlyReport != null && yearlyReport.getTotalInput() != null ? yearlyReport.getTotalInput().intValue() : 0);
        本年.setWashIn(yearlyReport != null && yearlyReport.getWashingInput() != null ? yearlyReport.getWashingInput().intValue() : 0);
        本年.setCleanCoal(yearlyReport != null && yearlyReport.getCleanCoal() != null ? yearlyReport.getCleanCoal().intValue() : 0);
        本年.setLumpCoal(yearlyReport != null && yearlyReport.getWashedLumpCoal() != null ? yearlyReport.getWashedLumpCoal().intValue() : 0);
        本年.setSlackCoal(yearlyReport != null && yearlyReport.getWashedFineCoal() != null ? yearlyReport.getWashedFineCoal().intValue() : 0);
        本年.setSludgeCoal(0);
        本年.setAvailableGangue(0);
        本年.setWaste(0);
        本年.setTotal(yearlyReport != null && yearlyReport.getWashingConsumption() != null ? yearlyReport.getWashingConsumption().intValue() : 0);
        本年.setWashInPlan(洗煤年计划 != null ? 洗煤年计划.getWashInMonthPlan() : null);
        本年.setCleanCoalPlan(洗煤年计划 != null ? 洗煤年计划.getCleanCoalMonthPlan() : null);
        list.add(本年);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-公司生产日报表
     */
    @Operation(summary = "公司数据报表-公司生产日报表")
    @PostMapping("/ShengChanALLlist")
    public TableDataInfo<BaseEntity> ShengChanALLlistModify(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计 = new shengChanRiBao();
        合计.setDayPlan(BigDecimal.ZERO);
        合计.setMonthPlan(BigDecimal.ZERO);
        合计.setYearPlan(BigDecimal.ZERO);
        合计.setDayComplete(BigDecimal.ZERO);
        合计.setMonthComplete(BigDecimal.ZERO);
        合计.setYearComplete(BigDecimal.ZERO);
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanYear(statsDate, "生产");
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);
        合计.setUnitName("合计");
        for (MiningAreaCategory mining : miningAreaCategories) {
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(mining.getAreaCode(), statsDate);
            shengChanRiBao 煤矿 = new shengChanRiBao();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            煤矿.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthPlan() != null) {
                煤矿.setYearPlan(年计划内容.getMonthPlan());
            } else {
                煤矿.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getProductionData() != null) {
                煤矿.setDayComplete(BigDecimal.valueOf(日完成内容.getProductionData()));
            } else {
                煤矿.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getProductionData() != null) {
                煤矿.setMonthComplete(BigDecimal.valueOf(月完成内容.getProductionData()));
            } else {
                煤矿.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getProductionData() != null) {
                煤矿.setYearComplete(BigDecimal.valueOf(年完成内容.getProductionData()));
            } else {
                煤矿.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(煤矿);
            合计.setDayPlan(合计.getDayPlan().add(煤矿.getDayPlan()));
            合计.setMonthPlan(合计.getMonthPlan().add(煤矿.getMonthPlan()));
            合计.setYearPlan(合计.getYearPlan().add(煤矿.getYearPlan()));
            合计.setDayComplete(合计.getDayComplete().add(煤矿.getDayComplete()));
            合计.setMonthComplete(合计.getMonthComplete().add(煤矿.getMonthComplete()));
            合计.setYearComplete(合计.getYearComplete().add(煤矿.getYearComplete()));
        }
        list.add(0, 合计);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-公司进尺日报表
     */
    @Operation(summary = "公司数据报表-公司进尺日报表")
    @PostMapping("/JinChiALLlist")
    public TableDataInfo<BaseEntity> JinChiALLlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计 = new shengChanRiBao();
        合计.setDayPlan(BigDecimal.ZERO);
        合计.setMonthPlan(BigDecimal.ZERO);
        合计.setYearPlan(BigDecimal.ZERO);
        合计.setDayComplete(BigDecimal.ZERO);
        合计.setMonthComplete(BigDecimal.ZERO);
        合计.setYearComplete(BigDecimal.ZERO);
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("进尺");
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanYear(statsDate, "进尺");
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        合计.setUnitName("合计");
        for (MiningAreaCategory mining : miningAreaCategories) {
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(mining.getAreaCode(), statsDate);
            shengChanRiBao 煤矿 = new shengChanRiBao();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            煤矿.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            煤矿.setYearPlan(BigDecimal.valueOf(年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getMonthPlan())).sum()));

            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getFootageData() != null) {
                煤矿.setDayComplete(日完成内容.getFootageData());
            } else {
                煤矿.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getFootageData() != null) {
                煤矿.setMonthComplete(月完成内容.getFootageData());
            } else {
                煤矿.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getFootageData() != null) {
                煤矿.setYearComplete(年完成内容.getFootageData());
            } else {
                煤矿.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(煤矿);
            合计.setDayPlan(合计.getDayPlan().add(煤矿.getDayPlan()));
            合计.setMonthPlan(合计.getMonthPlan().add(煤矿.getMonthPlan()));
            合计.setYearPlan(合计.getYearPlan().add(煤矿.getYearPlan()));
            合计.setDayComplete(合计.getDayComplete().add(煤矿.getDayComplete()));
            合计.setMonthComplete(合计.getMonthComplete().add(煤矿.getMonthComplete()));
            合计.setYearComplete(合计.getYearComplete().add(煤矿.getYearComplete()));
        }
        list.add(0, 合计);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-公司开拓日报表
     */
    @Operation(summary = "公司数据报表-公司开拓日报表")
    @PostMapping("/KaiTuoALLlist")
    public TableDataInfo<BaseEntity> KaiTuoALLlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计 = new shengChanRiBao();
        合计.setDayPlan(BigDecimal.ZERO);
        合计.setMonthPlan(BigDecimal.ZERO);
        合计.setYearPlan(BigDecimal.ZERO);
        合计.setDayComplete(BigDecimal.ZERO);
        合计.setMonthComplete(BigDecimal.ZERO);
        合计.setYearComplete(BigDecimal.ZERO);
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("开拓");
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanYear(statsDate, "开拓");
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        合计.setUnitName("合计");
        for (MiningAreaCategory mining : miningAreaCategories) {
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(mining.getAreaCode(), statsDate);
            shengChanRiBao 煤矿 = new shengChanRiBao();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            煤矿.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum()));
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            煤矿.setYearPlan(Optional.ofNullable(年计划内容.getMonthPlan()).orElse(BigDecimal.ZERO));
            
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            煤矿.setDayComplete(Optional.ofNullable(日完成内容.getExpandData()).orElse(BigDecimal.ZERO));

            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            煤矿.setMonthComplete(Optional.ofNullable(月完成内容.getExpandData()).orElse(BigDecimal.ZERO));

            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            煤矿.setYearComplete(Optional.ofNullable(年完成内容.getExpandData()).orElse(BigDecimal.ZERO));
            list.add(煤矿);
            合计.setDayPlan(合计.getDayPlan().add(煤矿.getDayPlan()));
            合计.setMonthPlan(合计.getMonthPlan().add(煤矿.getMonthPlan()));
            合计.setYearPlan(合计.getYearPlan().add(煤矿.getYearPlan()));
            合计.setDayComplete(合计.getDayComplete().add(煤矿.getDayComplete()));
            合计.setMonthComplete(合计.getMonthComplete().add(煤矿.getMonthComplete()));
            合计.setYearComplete(合计.getYearComplete().add(煤矿.getYearComplete()));
        }

        list.add(0, 合计);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-04洗煤日报表
     */
    @PostMapping("/XiMeiAlllist")
    public AjaxResult XiMeiAlllist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<xiMeiRiBaoBaoBiao> 本日list = new ArrayList<>();
        List<xiMeiRiBaoBaoBiao> 本月list = new ArrayList<>();
        List<xiMeiRiBaoBaoBiao> 本年list = new ArrayList<>();
        xiMeiRiBaoList 返回 = new xiMeiRiBaoList();
        String yue = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        //洗煤生产录入表 主表
        List<CoalWashingProductionPO> 日洗煤生产录入表 = coalWashingProductionMapper.selecList(statsDate);  //日
        List<CoalWashingProductionPO> 月洗煤生产录入表 = coalWashingProductionMapper.selecMonthList(statsDate);  //月
        List<CoalWashingProductionPO> 年洗煤生产录入表 = coalWashingProductionMapper.selecYearList(statsDate);  //年
        //洗煤计划录入子表
        List<SubWashCoalPlanPO> 月计划 = subWashCoalPlanMapper.selectByPlanDayUnitCode(statsDate);//洗煤计划子表  日月
        List<SubWashCoalPlanPO> 年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(statsDate);//洗煤计划子表  年
        //查询所有洗煤厂
        FactoryArchive fac = new FactoryArchive();
        fac.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(fac);
//        List<xiMeiRiBaoBaoBiao> list = new ArrayList<>();
        for (FactoryArchive fact : factoryArchives) {
            xiMeiRiBaoBaoBiao 本日 = new xiMeiRiBaoBaoBiao();
            xiMeiRiBaoBaoBiao 本月 = new xiMeiRiBaoBaoBiao();
            xiMeiRiBaoBaoBiao 本年 = new xiMeiRiBaoBaoBiao();
            本日.setUnitName(fact.getFactoryName());
            本月.setUnitName(fact.getFactoryName());
            本年.setUnitName(fact.getFactoryName());
            for (CoalWashingProductionPO po : 日洗煤生产录入表) {
                //洗煤厂名称
                if (fact.getFactoryName().equals(po.getUnitName())) {
                    本日.setDropIn(po.getDropIn());
                    本日.setWashIn(po.getWashIn());
                    本日.setActualCleanCoal(po.getCleanCoal());//clean_coal  jin
                    本日.setWashBlock(po.getLumpCoal());//lump_coal
                    本日.setWashingFoam(po.getSlackCoal());//slack_coal
                    本日.setWashingConsumption(po.getTotal());//total
                }
            }
            for (CoalWashingProductionPO po : 月洗煤生产录入表) {
                //洗煤厂名称
                if (fact.getFactoryName().equals(po.getUnitName())) {
                    本月.setDropIn(po.getDropIn());
                    本月.setWashIn(po.getWashIn());
                    本月.setActualCleanCoal(po.getCleanCoal());//clean_coal  jin
                    本月.setWashBlock(po.getLumpCoal());//lump_coal
                    本月.setWashingFoam(po.getSlackCoal());//slack_coal
                    本月.setWashingConsumption(po.getTotal());//total
                }
            }
            for (CoalWashingProductionPO po : 年洗煤生产录入表) {
                //洗煤厂名称
                if (fact.getFactoryName().equals(po.getUnitName())) {
                    本年.setDropIn(po.getDropIn());
                    本年.setWashIn(po.getWashIn());
                    本年.setActualCleanCoal(po.getCleanCoal());//clean_coal  jin
                    本年.setWashBlock(po.getLumpCoal());//lump_coal
                    本年.setWashingFoam(po.getSlackCoal());//slack_coal
                    本年.setWashingConsumption(po.getTotal());//total
                }
            }
            CoalMineWorkingDay 日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByDay(fact.getFactoryName(), yue, day);
            if (日计划 != null && 日计划.getProductionObjective() != null) {
                本日.setCleanCoalPlan(BigDecimal.valueOf(日计划.getProductionObjective()));
            } else {
                本日.setCleanCoalPlan(BigDecimal.valueOf(0));
            }
            //洗煤计划录入子表
            for (SubWashCoalPlanPO subwa : 月计划) {
                if (fact.getFactoryName().equals(subwa.getUnitName())) {
                    本月.setCleanCoalPlan(subwa.getCleanCoalMonthPlan());//clean_coal_month_plan
                }
            }
            for (SubWashCoalPlanPO subwa : 年计划) {
                if (fact.getFactoryName().equals(subwa.getUnitName())) {
                    本年.setCleanCoalPlan(subwa.getCleanCoalMonthPlan());//clean_coal_month_plan
                }
            }
            本日list.add(本日);
            本月list.add(本月);
            本年list.add(本年);
        }
        返回.setDay(本日list);
        返回.setMonth(本月list);
        返回.setYear(本年list);
        return success(返回);
    }

    /**
     * 公司数据报表-原煤去向日报表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-原煤去向日报表")
    @PostMapping("/YuanMeiALLlist")
    public TableDataInfo<BaseEntity> YuanMeiALLlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
//        Date yue = DateUtils.getFirstDayOfMonth(statsDate);
        String yearMonth1 = DateUtils.getYearMonth1(statsDate);
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//有效矿的
        List<yuanMeiQuXiangRiBao> list = new ArrayList<>();
        for (MiningAreaCategory mining : miningAreaCategories) {
            CoalWashingProductionPO 原煤入洗 = coalWashingProductionMapper.selectByDay(statsDate, mining.getAreaCode());//矿场数据填报-洗煤生产录入-入洗
            DestinationOfRawCoal dest = new DestinationOfRawCoal();
            dest.setRecordDate(statsDate);
            dest.setUnitName(mining.getAreaName());
            RawCoalToDailyReport 原煤去向月录入表 = rawCoalToDailyReportMapper.selectRawAll(yearMonth1, mining.getAreaName());//公司数据管理-原煤去向月录入表
            List<DestinationOfRawCoal> 日去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(dest);
            List<DestinationOfRawCoal> 月去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalByMonth(dest);
            yuanMeiQuXiangRiBao yuanmei = new yuanMeiQuXiangRiBao();
            yuanmei.setUnitName(mining.getAreaName());
            if (原煤入洗 != null) {
                yuanmei.setWashIn(原煤入洗.getWashIn());
            } else {
                yuanmei.setWashIn(BigDecimal.ZERO);
            }
            if (原煤去向月录入表 != null) {
                yuanmei.setMonthlyPlan(原煤去向月录入表.getMonthlyPlan());
                yuanmei.setWashInWashingPlant(原煤去向月录入表.getCoalWashingPlant());
                yuanmei.setWashableIntoGas(原煤去向月录入表.getGasCompany());
                yuanmei.setCannotWashedIn(原煤去向月录入表.getCannotWashedIn());
                yuanmei.setCoalStorageCapacity(原煤去向月录入表.getCoalStorageCapacity());
            } else {
                yuanmei.setMonthlyPlan(0L);
                yuanmei.setWashInWashingPlant(0L);
                yuanmei.setWashableIntoGas(0L);
                yuanmei.setCannotWashedIn(0L);
                yuanmei.setCoalStorageCapacity("0");
            }
            for (DestinationOfRawCoal 日向 : 日去向) {
                if (mining.getAreaName().equals(日向.getUnitName())) {
                    yuanmei.setDailyActual(日向.getSalesVolume());
                }
            }
            for (DestinationOfRawCoal 月向 : 月去向) {
                if (mining.getAreaName().equals(月向.getUnitName())) {
                    yuanmei.setMonthActual(月向.getSalesVolume());
                }
            }
            list.add(yuanmei);
        }
        return getDataTable(list);
    }

    /**
     * 公司数据报表-洗煤日报表二
     */
    @Anonymous
    @Operation(summary = "公司数据报表-洗煤日报表二")
    @PostMapping("/XiMeiAlllistTwo")
    public TableDataInfo<BaseEntity> XiMeiAlllistTwo(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        Date yue = DateUtils.getFirstDayOfMonth(statsDate);
        List<xiMeiTwoRiBao> list = new ArrayList<>();
        //洗煤生产录入表 主表
        List<CoalWashingProductionPO> 洗煤生产录入表 = coalWashingProductionMapper.selecList(statsDate);
        //洗煤计划录入主表
        WashCoalPlanPO washCoalPlanPO = washCoalPlanMapper.selectByPlan(yue);
        List<SubWashCoalPlanPO> subWashCoalPlanPOS = new ArrayList<>();
        if (washCoalPlanPO != null) {
            subWashCoalPlanPOS = subWashCoalPlanMapper.selectByPlanId(washCoalPlanPO.getId());//洗煤计划录入子表
        }
        //洗煤产品库及自用煤录入
        List<SubCoalProductInventory> coal = subCoalProductInventoryMapper.selectProductInventoryDay(statsDate);
        //查询所有洗煤厂
        FactoryArchive fac = new FactoryArchive();
        fac.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(fac);
        for (FactoryArchive fact : factoryArchives) {
            xiMeiTwoRiBao ximei = new xiMeiTwoRiBao();
            ximei.setUnitName(fact.getFactoryName());
            for (CoalWashingProductionPO po : 洗煤生产录入表) {
                if (fact.getFactoryName().equals(po.getUnitName())) {
                    ximei.setTransferIn(po.getDropIn());
                    ximei.setWashingConsumption(po.getSelfUse());
                    ximei.setWashInActual(po.getWashIn());
                    ximei.setCleanCoalQuantityActual(po.getCleanCoal());
                    ximei.setLaiKuaiQuantityActual(po.getLumpCoal().add(po.getSlackCoal()));
                }
            }
            if (washCoalPlanPO != null) {
                //洗煤计划录入子表
                for (SubWashCoalPlanPO subwa : subWashCoalPlanPOS) {
                    if (fact.getFactoryName().equals(subwa.getUnitName())) {
                        ximei.setWashInPlan(subwa.getWashInDayPlan());
                        ximei.setCleanCoalQuantityPlan(subwa.getCleanCoalDayPlan());
                        ximei.setLaiKuaiQuantityActual(subwa.getSlackCoalDayPlan());
                    }
                }
            }
            //洗煤产品库及自用煤录入
            for (SubCoalProductInventory subco : coal) {
                if (fact.getFactoryName().equals(subco.getUnitName())) {
                    ximei.setOalStockPreStock(subco.getCleanCoalPrevStock());
                    ximei.setOalStockCurrentStock(subco.getCleanCoalCurrentStock());
                    ximei.setLaiKuaiPreStock(subco.getSlackLumpPrevStock());
                    ximei.setLaiKuaiCurrentStock(subco.getSlackLumpCurrentStock());
                    ximei.setSelfUseCoalSelfUse(subco.getDailyPlantSelfUse());
                    ximei.setSelfUseCoalExternalUse(subco.getDailyOutsideSelfUse());
                }
            }
            list.add(ximei);
        }
        return getDataTable(list);
    }

    /**
     * 公司数据报表-07产销存去向库存表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-07产销存去向库存表")
    @PostMapping("/chanXiaoCunlist")
    public TableDataInfo<BaseEntity> chanXiaoCunlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        Date yue = DateUtils.getFirstDayOfMonth(statsDate);

        String nianyue = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        List<chanXiaoCunQuXiang> list = new ArrayList<>();
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名称
        //生产量  当日和累计
        List<MineData> 日生产 = subMineDevelopmentDataMapper.selectDayDate(DateUtils.returnDateRange(statsDate));
        List<MineData> 月生产 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(statsDate));
        //各矿期初库存录入表
        List<SubInitialInventoryOfEachMine> 矿期初库 = subeachMineMapper.selectSubInitialInventoryOfEachList("各矿期初库存录入表", yue);//各矿日实际产量录入表 和 各矿期初库存录入表 录入子表
        //List<CoalMineWorkingDay> 日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByUnitName(nianyue, day);

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(nianyue);
        minday.setStatus(0);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);
        chanXiaoCunQuXiang 合计 = new chanXiaoCunQuXiang();
        合计.setUnitName("合计");
        chanXiaoCunQuXiang 分公司 = new chanXiaoCunQuXiang();
        分公司.setUnitName("分公司");
        chanXiaoCunQuXiang 七煤公司 = new chanXiaoCunQuXiang();
        七煤公司.setUnitName("七煤公司");
        for (MiningAreaCategory mining : miningAreaCategories) {
            //销售量  当日和累计
//            ProductExportSituation product = new ProductExportSituation();
//            product.setExportDate(statsDate);
//            product.setUnitCode(mining.getAreaCode());
            //原煤去向录入表  日
            DestinationOfRawCoal dest = new DestinationOfRawCoal();
            dest.setRecordDate(statsDate);
            dest.setUnitName(mining.getAreaName());
            List<DestinationOfRawCoal> 日去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(dest);
            List<DestinationOfRawCoal> 月去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalByMonth(dest);
            chanXiaoCunQuXiang chan = new chanXiaoCunQuXiang();
            chan.setUnitName(mining.getAreaName());
            int sum = 日计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum();
            chan.setProductionPlan(BigDecimal.valueOf(sum));//日计划数
            for (MineData 日产量 : 日生产) {
                if (mining.getAreaName().equals(日产量.getUnitName())) {
                    if (chan.getProductionToday() != null) {
                        chan.setProductionToday(chan.getProductionToday().add(BigDecimal.valueOf(日产量.getProductionData())));//日生产量
                    } else {
                        chan.setProductionToday(BigDecimal.valueOf(日产量.getProductionData()));//日生产量
                    }
                }
            }
            for (MineData 月产量 : 月生产) {
                if (mining.getAreaName().equals(月产量.getUnitName())) {
                    if (chan.getProductionCumulative() != null) {
                        chan.setProductionCumulative(chan.getProductionCumulative().add(BigDecimal.valueOf(月产量.getProductionData())));//日生产量
                    } else {
                        chan.setProductionCumulative(BigDecimal.valueOf(月产量.getProductionData()));//累计日生产量
                    }
                }
            }
            for (DestinationOfRawCoal 日向 : 日去向) {
                if (mining.getAreaName().equals(日向.getUnitName())) {
                    chan.setRailwayVolumeToday(日向.getRailwayTransportVolume());//铁路运量
                    chan.setWashCoalYardInToday(日向.getWashPlantInbound());//入洗煤厂
                    chan.setSalesYardInToday(日向.getSalesYardInbound());//销售货场
                    chan.setGasCompanyToday(日向.getGasCompanySupply());
                    chan.setSelfUseToday(日向.getSelfUse());
                    chan.setOtherToday(日向.getOtherUse());
                    chan.setInventoryToday(日向.getInventory());
                    chan.setSalesToday(日向.getSalesVolume());
                }
            }
            for (DestinationOfRawCoal 月向 : 月去向) {
                if (mining.getAreaName().equals(月向.getUnitName())) {
                    chan.setRailwayVolumeCumulative(月向.getRailwayTransportVolume());//铁路运量
                    chan.setWashCoalYardInCumulative(月向.getWashPlantInbound());//入洗煤厂
                    chan.setSalesYardInCumulative(月向.getSalesYardInbound());//销售货场
                    chan.setGasCompanyCumulative(月向.getGasCompanySupply());
                    chan.setSelfUseCumulative(月向.getSelfUse());
                    chan.setOtherCumulative(月向.getOtherUse());
                    chan.setInventoryCumulative(月向.getInventory());
                    chan.setSalesCumulative(月向.getSalesVolume());
                }
            }
            for (SubInitialInventoryOfEachMine 期初 : 矿期初库) {
                if (mining.getAreaName().equals(期初.getUnitName())) {
                    chan.setInitialInventory(期初.getInitialInventoryOfThisMonth());
                }
            }
            list.add(chan);
            if (mining.getIsseparate() == 0) {
                分公司.setUnitName("分公司");
                if (分公司.getProductionPlan() != null) {
                    if (chan.getProductionPlan() != null) {
                        分公司.setProductionPlan(分公司.getProductionPlan().add(chan.getProductionPlan()));// 日计划数
                    }
                } else {
                    分公司.setProductionPlan(chan.getProductionPlan());
                }
                if (分公司.getProductionToday() != null) {
                    if (chan.getProductionToday() != null) {
                        分公司.setProductionToday(分公司.getProductionToday().add(chan.getProductionToday()));// 日生产量
                    }
                } else {
                    分公司.setProductionToday(chan.getProductionToday());
                }
                if (分公司.getProductionCumulative() != null) {
                    if (chan.getProductionCumulative() != null) {
                        分公司.setProductionCumulative(分公司.getProductionCumulative().add(chan.getProductionCumulative()));// 累计日生产量
                    }
                } else {
                    分公司.setProductionCumulative(chan.getProductionCumulative());
                }
                if (分公司.getSalesToday() != null) {
                    if (chan.getSalesToday() != null) {
                        分公司.setSalesToday(分公司.getSalesToday().add(chan.getSalesToday()));// 日销售
                    }
                } else {
                    分公司.setSalesToday(chan.getSalesToday());
                }
                if (分公司.getSalesCumulative() != null) {
                    if (chan.getSalesCumulative() != null) {
                        分公司.setSalesCumulative(分公司.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售
                    }
                } else {
                    分公司.setSalesCumulative(chan.getSalesCumulative());
                }
                if (分公司.getRailwayVolumeToday() != null) {
                    if (chan.getRailwayVolumeToday() != null) {
                        分公司.setRailwayVolumeToday(分公司.getRailwayVolumeToday().add(chan.getRailwayVolumeToday()));// 铁路运量（今日）
                    }
                } else {
                    分公司.setRailwayVolumeToday(chan.getRailwayVolumeToday());
                }
                if (分公司.getWashCoalYardInToday() != null) {
                    if (chan.getWashCoalYardInToday() != null) {
                        分公司.setWashCoalYardInToday(分公司.getWashCoalYardInToday().add(chan.getWashCoalYardInToday()));// 入洗煤厂（今日）
                    }
                } else {
                    分公司.setWashCoalYardInToday(chan.getWashCoalYardInToday());
                }
                if (分公司.getSalesYardInToday() != null) {
                    if (chan.getSalesYardInToday() != null) {
                        分公司.setSalesYardInToday(分公司.getSalesYardInToday().add(chan.getSalesYardInToday()));// 销售货场（今日）
                    }
                } else {
                    分公司.setSalesYardInToday(chan.getSalesYardInToday());
                }
                if (分公司.getGasCompanyToday() != null) {
                    if (chan.getGasCompanyToday() != null) {
                        分公司.setGasCompanyToday(分公司.getGasCompanyToday().add(chan.getGasCompanyToday()));// 燃气公司供应（今日）
                    }
                } else {
                    分公司.setGasCompanyToday(chan.getGasCompanyToday());
                }
                if (分公司.getSelfUseToday() != null) {
                    if (chan.getSelfUseToday() != null) {
                        分公司.setSelfUseToday(分公司.getSelfUseToday().add(chan.getSelfUseToday()));// 自用（今日）
                    }
                } else {
                    分公司.setSelfUseToday(chan.getSelfUseToday());
                }
                if (分公司.getOtherToday() != null) {
                    if (chan.getOtherToday() != null) {
                        分公司.setOtherToday(分公司.getOtherToday().add(chan.getOtherToday()));// 其他用途（今日）
                    }
                } else {
                    分公司.setOtherToday(chan.getOtherToday());
                }
                if (分公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        分公司.setInventoryToday(分公司.getInventoryToday().add(chan.getInventoryToday()));// 库存（今日）
                    }
                } else {
                    分公司.setInventoryToday(chan.getInventoryToday());
                }
                if (分公司.getRailwayVolumeCumulative() != null) {
                    if (chan.getRailwayVolumeCumulative() != null) {
                        分公司.setRailwayVolumeCumulative(分公司.getRailwayVolumeCumulative().add(chan.getRailwayVolumeCumulative()));// 铁路运量（累计）
                    }
                } else {
                    分公司.setRailwayVolumeCumulative(chan.getRailwayVolumeCumulative());
                }
                if (分公司.getWashCoalYardInCumulative() != null) {
                    if (chan.getWashCoalYardInCumulative() != null) {
                        分公司.setWashCoalYardInCumulative(分公司.getWashCoalYardInCumulative().add(chan.getWashCoalYardInCumulative()));// 入洗煤厂（累计）
                    }
                } else {
                    分公司.setWashCoalYardInCumulative(chan.getWashCoalYardInCumulative());
                }
                if (分公司.getSalesYardInCumulative() != null) {
                    if (chan.getSalesYardInCumulative() != null) {
                        分公司.setSalesYardInCumulative(分公司.getSalesYardInCumulative().add(chan.getSalesYardInCumulative()));// 销售货场（累计）
                    }
                } else {
                    分公司.setSalesYardInCumulative(chan.getSalesYardInCumulative());
                }
                if (分公司.getGasCompanyCumulative() != null) {
                    if (chan.getGasCompanyCumulative() != null) {
                        分公司.setGasCompanyCumulative(分公司.getGasCompanyCumulative().add(chan.getGasCompanyCumulative()));// 燃气公司供应（累计）
                    }
                } else {
                    分公司.setGasCompanyCumulative(chan.getGasCompanyCumulative());
                }
                if (分公司.getSelfUseCumulative() != null) {
                    if (chan.getSelfUseCumulative() != null) {
                        分公司.setSelfUseCumulative(分公司.getSelfUseCumulative().add(chan.getSelfUseCumulative()));// 自用（累计）
                    }
                } else {
                    分公司.setSelfUseCumulative(chan.getSelfUseCumulative());
                }
                if (分公司.getOtherCumulative() != null) {
                    if (chan.getOtherCumulative() != null) {
                        分公司.setOtherCumulative(分公司.getOtherCumulative().add(chan.getOtherCumulative()));// 其他用途（累计）
                    }
                } else {
                    分公司.setOtherCumulative(chan.getOtherCumulative());
                }
                if (分公司.getInventoryCumulative() != null) {
                    if (chan.getInventoryCumulative() != null) {
                        分公司.setInventoryCumulative(分公司.getInventoryCumulative().add(chan.getInventoryCumulative()));// 库存（累计）
                    }
                } else {
                    分公司.setInventoryCumulative(chan.getInventoryCumulative());
                }
                if (分公司.getInitialInventory() != null) {
                    if (chan.getInitialInventory() != null) {
                        分公司.setInitialInventory(分公司.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                    }
                } else {
                    分公司.setInitialInventory(chan.getInitialInventory());
                }
            }
            if (mining.getIsseparate() == 1) {
                七煤公司.setUnitName("七煤公司");
                if (七煤公司.getProductionPlan() != null) {
                    if (chan.getProductionPlan() != null) {
                        七煤公司.setProductionPlan(七煤公司.getProductionPlan().add(chan.getProductionPlan())); // 日计划数
                    }
                } else {
                    七煤公司.setProductionPlan(chan.getProductionPlan());
                }
                if (七煤公司.getProductionToday() != null) {
                    if (chan.getProductionToday() != null) {
                        七煤公司.setProductionToday(七煤公司.getProductionToday().add(chan.getProductionToday()));// 日生产量
                    }
                } else {
                    七煤公司.setProductionToday(chan.getProductionToday());
                }
                if (七煤公司.getProductionCumulative() != null) {
                    if (chan.getProductionCumulative() != null) {
                        七煤公司.setProductionCumulative(七煤公司.getProductionCumulative().add(chan.getProductionCumulative()));   // 累计日生产量
                    }
                } else {
                    七煤公司.setProductionCumulative(chan.getProductionCumulative());
                }
                if (七煤公司.getSalesToday() != null) {
                    if (chan.getSalesToday() != null) {
                        七煤公司.setSalesToday(七煤公司.getSalesToday().add(chan.getSalesToday())); // 日销售
                    }
                } else {
                    七煤公司.setSalesToday(chan.getSalesToday());
                }
                if (分公司.getSalesCumulative() != null) {
                    if (chan.getSalesCumulative() != null) {
                        分公司.setSalesCumulative(分公司.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售
                    }
                } else {
                    分公司.setSalesCumulative(chan.getSalesToday());
                }
                if (七煤公司.getRailwayVolumeToday() != null) {
                    if (chan.getRailwayVolumeToday() != null) {
                        七煤公司.setRailwayVolumeToday(七煤公司.getRailwayVolumeToday().add(chan.getRailwayVolumeToday()));// 铁路运量（今日）
                    }
                } else {
                    七煤公司.setRailwayVolumeToday(chan.getRailwayVolumeToday());
                }
                if (七煤公司.getWashCoalYardInToday() != null) {
                    if (chan.getWashCoalYardInToday() != null) {
                        七煤公司.setWashCoalYardInToday(七煤公司.getWashCoalYardInToday().add(chan.getWashCoalYardInToday()));// 入洗煤厂（今日）
                    }
                } else {
                    七煤公司.setWashCoalYardInToday(chan.getWashCoalYardInToday());
                }
                if (七煤公司.getSalesYardInToday() != null) {
                    if (chan.getSalesYardInToday() != null) {
                        七煤公司.setSalesYardInToday(七煤公司.getSalesYardInToday().add(chan.getSalesYardInToday()));// 销售货场（今日）
                    }
                } else {
                    七煤公司.setSalesYardInToday(chan.getSalesYardInToday());
                }
                if (七煤公司.getGasCompanyToday() != null) {
                    if (chan.getGasCompanyToday() != null) {
                        七煤公司.setGasCompanyToday(七煤公司.getGasCompanyToday().add(chan.getGasCompanyToday()));// 燃气公司供应（今日）
                    }
                } else {
                    七煤公司.setGasCompanyToday(chan.getGasCompanyToday());
                }
                if (七煤公司.getSelfUseToday() != null) {
                    if (chan.getSelfUseToday() != null) {
                        七煤公司.setSelfUseToday(七煤公司.getSelfUseToday().add(chan.getSelfUseToday()));// 自用（今日）
                    }
                } else {
                    七煤公司.setSelfUseToday(chan.getSelfUseToday());
                }
                if (七煤公司.getOtherToday() != null) {
                    if (chan.getOtherToday() != null) {
                        七煤公司.setOtherToday(七煤公司.getOtherToday().add(chan.getOtherToday()));// 其他用途（今日）
                    }
                } else {
                    七煤公司.setOtherToday(chan.getOtherToday());
                }
                if (七煤公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        七煤公司.setInventoryToday(七煤公司.getInventoryToday().add(chan.getInventoryToday()));// 库存（今日）
                    }
                } else {
                    七煤公司.setInventoryToday(chan.getInventoryToday());
                }
                if (七煤公司.getRailwayVolumeCumulative() != null) {
                    if (chan.getRailwayVolumeCumulative() != null) {
                        七煤公司.setRailwayVolumeCumulative(七煤公司.getRailwayVolumeCumulative().add(chan.getRailwayVolumeCumulative()));// 铁路运量（累计）
                    }
                } else {
                    七煤公司.setRailwayVolumeCumulative(chan.getRailwayVolumeCumulative());
                }
                if (七煤公司.getWashCoalYardInCumulative() != null) {
                    if (chan.getWashCoalYardInCumulative() != null) {
                        七煤公司.setWashCoalYardInCumulative(七煤公司.getWashCoalYardInCumulative().add(chan.getWashCoalYardInCumulative()));// 入洗煤厂（累计）
                    }
                } else {
                    七煤公司.setWashCoalYardInCumulative(chan.getWashCoalYardInCumulative());
                }
                if (七煤公司.getSalesYardInCumulative() != null) {
                    if (chan.getSalesYardInCumulative() != null) {
                        七煤公司.setSalesYardInCumulative(七煤公司.getSalesYardInCumulative().add(chan.getSalesYardInCumulative()));// 销售货场（累计）
                    }
                } else {
                    七煤公司.setSalesYardInCumulative(chan.getSalesYardInCumulative());
                }
                if (七煤公司.getGasCompanyCumulative() != null) {
                    if (chan.getGasCompanyCumulative() != null) {
                        七煤公司.setGasCompanyCumulative(七煤公司.getGasCompanyCumulative().add(chan.getGasCompanyCumulative()));// 燃气公司供应（累计）
                    }
                } else {
                    七煤公司.setGasCompanyCumulative(chan.getGasCompanyCumulative());
                }
                if (七煤公司.getSelfUseCumulative() != null) {
                    if (chan.getSelfUseCumulative() != null) {
                        七煤公司.setSelfUseCumulative(七煤公司.getSelfUseCumulative().add(chan.getSelfUseCumulative()));// 自用（累计）
                    }
                } else {
                    七煤公司.setSelfUseCumulative(chan.getSelfUseCumulative());
                }
                if (七煤公司.getOtherCumulative() != null) {
                    if (chan.getOtherCumulative() != null) {
                        七煤公司.setOtherCumulative(七煤公司.getOtherCumulative().add(chan.getOtherCumulative()));// 其他用途（累计）
                    }
                } else {
                    七煤公司.setOtherCumulative(chan.getOtherCumulative());
                }
                if (七煤公司.getInventoryCumulative() != null) {
                    if (chan.getInventoryCumulative() != null) {
                        七煤公司.setInventoryCumulative(七煤公司.getInventoryCumulative().add(chan.getInventoryCumulative()));// 库存（累计）
                    }
                } else {
                    七煤公司.setInventoryCumulative(chan.getInventoryCumulative());
                }
                if (七煤公司.getInitialInventory() != null) {
                    if (chan.getInitialInventory() != null) {
                        七煤公司.setInitialInventory(七煤公司.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                    }
                } else {
                    七煤公司.setInitialInventory(chan.getInitialInventory());
                }
            }
            合计.setUnitName("合计");
            if (合计.getProductionPlan() != null) {
                if (chan.getProductionPlan() != null) {
                    合计.setProductionPlan(合计.getProductionPlan().add(chan.getProductionPlan()));// 日计划数
                }
            } else {
                合计.setProductionPlan(chan.getProductionPlan());
            }
            if (合计.getProductionToday() != null) {
                if (chan.getProductionToday() != null) {
                    合计.setProductionToday(合计.getProductionToday().add(chan.getProductionToday()));// 日生产量
                }
            } else {
                合计.setProductionToday(chan.getProductionToday());
            }
            if (合计.getProductionCumulative() != null) {
                if (chan.getProductionCumulative() != null) {
                    合计.setProductionCumulative(合计.getProductionCumulative().add(chan.getProductionCumulative()));// 累计日生产量
                }
            } else {
                合计.setProductionCumulative(chan.getProductionCumulative());
            }
            if (合计.getSalesToday() != null) {
                if (chan.getSalesToday() != null) {
                    合计.setSalesToday(合计.getSalesToday().add(chan.getSalesToday()));// 日销售（先处理日销数据）
                }
            } else {
                合计.setSalesToday(chan.getSalesToday());
            }
            if (合计.getSalesCumulative() != null) {
                if (chan.getSalesCumulative() != null) {
                    合计.setSalesCumulative(合计.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售（按原逻辑保留，增加chan非空判断）
                }
            } else {
                合计.setSalesCumulative(chan.getSalesCumulative());
            }
            if (合计.getRailwayVolumeToday() != null) {
                if (chan.getRailwayVolumeToday() != null) {
                    合计.setRailwayVolumeToday(合计.getRailwayVolumeToday().add(chan.getRailwayVolumeToday()));// 铁路运量（今日）
                }
            } else {
                合计.setRailwayVolumeToday(chan.getRailwayVolumeToday());
            }
            if (合计.getWashCoalYardInToday() != null) {
                if (chan.getWashCoalYardInToday() != null) {
                    合计.setWashCoalYardInToday(合计.getWashCoalYardInToday().add(chan.getWashCoalYardInToday()));// 入洗煤厂（今日）
                }
            } else {
                合计.setWashCoalYardInToday(chan.getWashCoalYardInToday());
            }
            if (合计.getSalesYardInToday() != null) {
                if (chan.getSalesYardInToday() != null) {
                    合计.setSalesYardInToday(合计.getSalesYardInToday().add(chan.getSalesYardInToday()));// 销售货场（今日）
                }
            } else {
                合计.setSalesYardInToday(chan.getSalesYardInToday());
            }
            if (合计.getGasCompanyToday() != null) {
                if (chan.getGasCompanyToday() != null) {
                    合计.setGasCompanyToday(合计.getGasCompanyToday().add(chan.getGasCompanyToday()));// 燃气公司供应（今日）
                }
            } else {
                合计.setGasCompanyToday(chan.getGasCompanyToday());
            }
            if (合计.getSelfUseToday() != null) {
                if (chan.getSelfUseToday() != null) {
                    合计.setSelfUseToday(合计.getSelfUseToday().add(chan.getSelfUseToday()));// 自用（今日）
                }
            } else {
                合计.setSelfUseToday(chan.getSelfUseToday());
            }
            if (合计.getOtherToday() != null) {
                if (chan.getOtherToday() != null) {
                    合计.setOtherToday(合计.getOtherToday().add(chan.getOtherToday()));// 其他用途（今日）
                }
            } else {
                合计.setOtherToday(chan.getOtherToday());
            }
            if (合计.getInventoryToday() != null) {
                if (chan.getInventoryToday() != null) {
                    合计.setInventoryToday(合计.getInventoryToday().add(chan.getInventoryToday()));// 库存（今日）
                }
            } else {
                合计.setInventoryToday(chan.getInventoryToday());
            }
            if (合计.getRailwayVolumeCumulative() != null) {
                if (chan.getRailwayVolumeCumulative() != null) {
                    合计.setRailwayVolumeCumulative(合计.getRailwayVolumeCumulative().add(chan.getRailwayVolumeCumulative()));// 铁路运量（累计）
                }
            } else {
                合计.setRailwayVolumeCumulative(chan.getRailwayVolumeCumulative());
            }
            if (合计.getWashCoalYardInCumulative() != null) {
                if (chan.getWashCoalYardInCumulative() != null) {
                    合计.setWashCoalYardInCumulative(合计.getWashCoalYardInCumulative().add(chan.getWashCoalYardInCumulative()));// 入洗煤厂（累计）
                }
            } else {
                合计.setWashCoalYardInCumulative(chan.getWashCoalYardInCumulative());
            }
            if (合计.getSalesYardInCumulative() != null) {
                if (chan.getSalesYardInCumulative() != null) {
                    合计.setSalesYardInCumulative(合计.getSalesYardInCumulative().add(chan.getSalesYardInCumulative()));// 销售货场（累计）
                }
            } else {
                合计.setSalesYardInCumulative(chan.getSalesYardInCumulative());
            }
            if (合计.getGasCompanyCumulative() != null) {
                if (chan.getGasCompanyCumulative() != null) {
                    合计.setGasCompanyCumulative(合计.getGasCompanyCumulative().add(chan.getGasCompanyCumulative()));// 燃气公司供应（累计）
                }
            } else {
                合计.setGasCompanyCumulative(chan.getGasCompanyCumulative());
            }
            if (合计.getSelfUseCumulative() != null) {
                if (chan.getSelfUseCumulative() != null) {
                    合计.setSelfUseCumulative(合计.getSelfUseCumulative().add(chan.getSelfUseCumulative()));// 自用（累计）
                }
            } else {
                合计.setSelfUseCumulative(chan.getSelfUseCumulative());
            }
            if (合计.getOtherCumulative() != null) {
                if (chan.getOtherCumulative() != null) {
                    合计.setOtherCumulative(合计.getOtherCumulative().add(chan.getOtherCumulative()));// 其他用途（累计）
                }
            } else {
                合计.setOtherCumulative(chan.getOtherCumulative());
            }
            if (合计.getInventoryCumulative() != null) {
                if (chan.getInventoryCumulative() != null) {
                    合计.setInventoryCumulative(合计.getInventoryCumulative().add(chan.getInventoryCumulative()));// 库存（累计）
                }
            } else {
                合计.setInventoryCumulative(chan.getInventoryCumulative());
            }
            if (合计.getInitialInventory() != null) {
                if (chan.getInitialInventory() != null) {
                    合计.setInitialInventory(合计.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                }
            } else {
                合计.setInitialInventory(chan.getInitialInventory());
            }
        }
        list.add(0,合计);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-09产销存日报表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-09产销存日报表")
    @PostMapping("/chanXiaoCunRiBaolist")
    public TableDataInfo<BaseEntity> chanXiaoCunRiBaolist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        Date yue = DateUtils.getFirstDayOfMonth(statsDate);
        String nianyue = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        List<chanXiaoCunRiBao> list = new ArrayList<>();
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名称
        chanXiaoCunRiBao 合计 = new chanXiaoCunRiBao();
        合计.setUnitName("合计");
        chanXiaoCunRiBao 分公司 = new chanXiaoCunRiBao();
        分公司.setUnitName("分公司");
        chanXiaoCunRiBao 七煤公司 = new chanXiaoCunRiBao();
        七煤公司.setUnitName("七煤公司");

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(nianyue);
        minday.setStatus(0);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);
        for (MiningAreaCategory mining : miningAreaCategories) {
//            MinePlanPO mine = new MinePlanPO();
//            mine.setPlanMonth(yue);
//            mine.setUnitCode(mining.getAreaCode());
//            mine.setPlanType("生产");
//            MinePlanPO planPO = minePlanMapper.selectMine(mine);
            ProductExportSituation product = new ProductExportSituation();
            product.setExportDate(statsDate);
            product.setUnitCode(mining.getAreaCode());
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            DestinationOfRawCoal dest = new DestinationOfRawCoal();
            dest.setRecordDate(statsDate);
            dest.setUnitName(mining.getAreaName());
            List<SubInitialInventoryOfEachMine> 矿期初库 = subeachMineMapper.selectSubInitialInventoryOfEachList("各矿期初库存录入表", yue);//各矿日实际产量录入表 和 各矿期初库存录入表 录入子表
            List<DestinationOfRawCoal> 日去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(dest);
            List<DestinationOfRawCoal> 月去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalByMonth(dest);
            chanXiaoCunRiBao chan = new chanXiaoCunRiBao();
            chan.setUnitName(mining.getAreaName());

            int sum = 日计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum();
            chan.setTodayPlan(BigDecimal.valueOf(sum));//日计划数
            CoalMineWorkingDay 累计日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByMonth(mining.getAreaName(), nianyue, day);
            if (累计日计划 != null && 累计日计划.getProductionObjective() != null) {
                chan.setCumulativePlan(BigDecimal.valueOf(累计日计划.getProductionPlan()));//累计计划数
            } else {
                chan.setCumulativePlan(BigDecimal.valueOf(0));
            }
            for (SubMineDevelopmentDataPO sub1 : 日完成) {
                if ("合计".equals(sub1.getUnitNameJSON())) {
                    if (chan.getTodayActual() != null) {
                        chan.setTodayActual(chan.getTodayActual().add(BigDecimal.valueOf(sub1.getProductionData())));//日完成
                    } else {
                        chan.setTodayActual(BigDecimal.valueOf(sub1.getProductionData()));//日完成
                    }
                }
            }
            for (SubMineDevelopmentDataPO sub1 : 月完成) {
                if ("合计".equals(sub1.getUnitNameJSON())) {
                    chan.setCumulativeActual(BigDecimal.valueOf(sub1.getProductionData()));//日累计
                }
            }
            for (SubInitialInventoryOfEachMine 期初 : 矿期初库) {
                if (mining.getAreaName().equals(期初.getUnitName())) {
                    chan.setInitialInventory(期初.getInitialInventoryOfThisMonth());
                }
            }
            for (DestinationOfRawCoal 日向 : 日去向) {
                if (mining.getAreaName().equals(日向.getUnitName())) {
                    chan.setSalesToday(日向.getSalesVolume());
                    chan.setInventoryToday(日向.getInventory());
                }
            }
            for (DestinationOfRawCoal 月向 : 月去向) {
                if (mining.getAreaName().equals(月向.getUnitName())) {
                    chan.setSalesCumulative(月向.getSalesVolume());
                    chan.setInventoryCumulative(月向.getInventory());
                }
            }
            list.add(chan);

            if (mining.getIsseparate() == 0) {
                分公司.setUnitName("分公司");
                if (分公司.getTodayPlan() != null) {
                    if (chan.getTodayPlan() != null) {
                        分公司.setTodayPlan(分公司.getTodayPlan().add(chan.getTodayPlan()));// 日计划数
                    }
                } else {
                    分公司.setTodayPlan(chan.getTodayPlan());
                }
                if (分公司.getCumulativePlan() != null) {
                    if (chan.getCumulativePlan() != null) {
                        分公司.setCumulativePlan(分公司.getCumulativePlan().add(chan.getCumulativePlan()));// 累计计划数
                    }
                } else {
                    分公司.setCumulativePlan(chan.getCumulativePlan());
                }
                if (分公司.getTodayActual() != null) {
                    if (chan.getTodayActual() != null) {
                        分公司.setTodayActual(分公司.getTodayActual().add(chan.getTodayActual()));// 本日实际
                    }
                } else {
                    分公司.setTodayActual(chan.getTodayActual());
                }
                if (分公司.getCumulativeActual() != null) {
                    if (chan.getCumulativeActual() != null) {
                        分公司.setCumulativeActual(分公司.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第一次）
                    }
                } else {
                    分公司.setCumulativeActual(chan.getCumulativeActual());
                }
                if (分公司.getCumulativeActual() != null) {
                    if (chan.getCumulativeActual() != null) {
                        分公司.setCumulativeActual(分公司.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第二次，保留原重复累加逻辑）
                    }
                } else {
                    分公司.setCumulativeActual(chan.getCumulativeActual());
                }
                if (分公司.getSalesToday() != null) {
                    if (chan.getSalesToday() != null) {
                        分公司.setSalesToday(分公司.getSalesToday().add(chan.getSalesToday()));// 日销售
                    }
                } else {
                    分公司.setSalesToday(chan.getSalesToday());
                }
                if (分公司.getSalesCumulative() != null) {
                    if (chan.getSalesCumulative() != null) {
                        分公司.setSalesCumulative(分公司.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售
                    }
                } else {
                    分公司.setSalesCumulative(chan.getSalesCumulative());
                }
                if (分公司.getInitialInventory() != null) {
                    if (chan.getInitialInventory() != null) {
                        分公司.setInitialInventory(分公司.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                    }
                } else {
                    分公司.setInitialInventory(chan.getInitialInventory());
                }
                if (分公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        分公司.setInventoryToday(分公司.getInventoryToday().add(chan.getInventoryToday()));// 本日库存（第一次）
                    }
                } else {
                    分公司.setInventoryToday(chan.getInventoryToday());
                }
                if (分公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        分公司.setInventoryToday(分公司.getInventoryToday().add(chan.getInventoryToday()));// 本日库存（第二次，保留原重复累加逻辑）
                    }
                } else {
                    分公司.setInventoryToday(chan.getInventoryToday());
                }
            }
            if (mining.getIsseparate() == 1) {
                七煤公司.setUnitName("七煤公司");
                if (七煤公司.getTodayPlan() != null) {
                    if (chan.getTodayPlan() != null) {
                        七煤公司.setTodayPlan(七煤公司.getTodayPlan().add(chan.getTodayPlan()));// 日计划数
                    }
                } else {
                    七煤公司.setTodayPlan(chan.getTodayPlan());
                }
                if (七煤公司.getCumulativePlan() != null) {
                    if (chan.getCumulativePlan() != null) {
                        七煤公司.setCumulativePlan(七煤公司.getCumulativePlan().add(chan.getCumulativePlan()));// 累计计划数
                    }
                } else {
                    七煤公司.setCumulativePlan(chan.getCumulativePlan());
                }
                if (七煤公司.getTodayActual() != null) {
                    if (chan.getTodayActual() != null) {
                        七煤公司.setTodayActual(七煤公司.getTodayActual().add(chan.getTodayActual()));// 本日实际
                    }
                } else {
                    七煤公司.setTodayActual(chan.getTodayActual());
                }
                if (七煤公司.getCumulativeActual() != null) {
                    if (chan.getCumulativeActual() != null) {
                        七煤公司.setCumulativeActual(七煤公司.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第一次）
                    }
                } else {
                    七煤公司.setCumulativeActual(chan.getCumulativeActual());
                }
                if (七煤公司.getCumulativeActual() != null) {
                    if (chan.getCumulativeActual() != null) {
                        七煤公司.setCumulativeActual(七煤公司.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第二次，保留原重复累加逻辑）
                    }
                } else {
                    七煤公司.setCumulativeActual(chan.getCumulativeActual());
                }
                if (七煤公司.getSalesToday() != null) {
                    if (chan.getSalesToday() != null) {
                        七煤公司.setSalesToday(七煤公司.getSalesToday().add(chan.getSalesToday()));// 日销售
                    }
                } else {
                    七煤公司.setSalesToday(chan.getSalesToday());
                }
                if (七煤公司.getSalesCumulative() != null) {
                    if (chan.getSalesCumulative() != null) {
                        七煤公司.setSalesCumulative(七煤公司.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售
                    }
                } else {
                    七煤公司.setSalesCumulative(chan.getSalesCumulative());
                }
                if (七煤公司.getInitialInventory() != null) {
                    if (chan.getInitialInventory() != null) {
                        七煤公司.setInitialInventory(七煤公司.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                    }
                } else {
                    七煤公司.setInitialInventory(chan.getInitialInventory());
                }
                if (七煤公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        七煤公司.setInventoryToday(七煤公司.getInventoryToday().add(chan.getInventoryToday()));// 本日库存（第一次）
                    }
                } else {
                    七煤公司.setInventoryToday(chan.getInventoryToday());
                }
                if (七煤公司.getInventoryToday() != null) {
                    if (chan.getInventoryToday() != null) {
                        七煤公司.setInventoryToday(七煤公司.getInventoryToday().add(chan.getInventoryToday()));// 本日库存（第二次，保留原重复累加逻辑）
                    }
                } else {
                    七煤公司.setInventoryToday(chan.getInventoryToday());
                }
            }
            合计.setUnitName("合计");
            if (合计.getTodayPlan() != null) {
                if (chan.getTodayPlan() != null) {
                    合计.setTodayPlan(合计.getTodayPlan().add(chan.getTodayPlan()));// 日计划数
                }
            } else {
                合计.setTodayPlan(chan.getTodayPlan());
            }
            if (合计.getCumulativePlan() != null) {
                if (chan.getCumulativePlan() != null) {
                    合计.setCumulativePlan(合计.getCumulativePlan().add(chan.getCumulativePlan()));// 累计计划数
                }
            } else {
                合计.setCumulativePlan(chan.getCumulativePlan());
            }
            if (合计.getTodayActual() != null) {
                if (chan.getTodayActual() != null) {
                    合计.setTodayActual(合计.getTodayActual().add(chan.getTodayActual()));// 本日实际
                }
            } else {
                合计.setTodayActual(chan.getTodayActual());
            }
            if (合计.getCumulativeActual() != null) {
                if (chan.getCumulativeActual() != null) {
                    合计.setCumulativeActual(合计.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第一次）
                }
            } else {
                合计.setCumulativeActual(chan.getCumulativeActual());
            }
            if (合计.getCumulativeActual() != null) {
                if (chan.getCumulativeActual() != null) {
                    合计.setCumulativeActual(合计.getCumulativeActual().add(chan.getCumulativeActual()));// 日累计（第二次，保留原重复累加逻辑）
                }
            } else {
                合计.setCumulativeActual(chan.getCumulativeActual());
            }
            if (合计.getSalesToday() != null) {
                if (chan.getSalesToday() != null) {
                    合计.setSalesToday(合计.getSalesToday().add(chan.getSalesToday()));// 日销售
                }
            } else {
                合计.setSalesToday(chan.getSalesToday());
            }
            if (合计.getSalesCumulative() != null) {
                if (chan.getSalesCumulative() != null) {
                    合计.setSalesCumulative(合计.getSalesCumulative().add(chan.getSalesCumulative()));// 月销售
                }
            } else {
                合计.setSalesCumulative(chan.getSalesCumulative());
            }
            if (合计.getInitialInventory() != null) {
                if (chan.getInitialInventory() != null) {
                    合计.setInitialInventory(合计.getInitialInventory().add(chan.getInitialInventory()));// 期初库存
                }
            } else {
                合计.setInitialInventory(chan.getInitialInventory());
            }
            if (合计.getInventoryToday() != null) {
                if (chan.getInventoryToday() != null) {
                    合计.setInventoryToday(合计.getInventoryToday().add(chan.getInventoryToday()));// 本日库存（第一次）
                }
            } else {
                合计.setInventoryToday(chan.getInventoryToday());
            }
            if (合计.getInventoryCumulative() != null) {
                if (chan.getInventoryCumulative() != null) {
                    合计.setInventoryCumulative(合计.getInventoryCumulative().add(chan.getInventoryCumulative()));// 本日库存（第二次，保留原重复累加逻辑）
                }
            } else {
                合计.setInventoryCumulative(chan.getInventoryCumulative());
            }
        }
//        list.add(分公司);
//        list.add(七煤公司);
        list.add(0,合计);
        return getDataTable(list);
    }

    /**
     * 公司数据报表-14公司其他数据统计表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-14公司其他数据统计表")
    @PostMapping("/otherShuJulist")
    public AjaxResult otherShuJulist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        String yearMonth = DateUtils.getYearMonth(statsDate);
        String Year = DateUtils.getYear(statsDate);
        otherShuJuBaoBiao other = new otherShuJuBaoBiao();
        ComprehensiveProductionStats dis = new ComprehensiveProductionStats();
        dis.setStatsDate(statsDate);
        ComprehensiveProductionStats 本日 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsDayList(dis);
        ComprehensiveProductionStats 本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        CokeSalesPlan 计划 = cokeSalesPlanMapper.selectCokeSalesPlanDayList(yearMonth);
        CokeSalesPlan 年计划 = cokeSalesPlanMapper.selectCokeSalesPlanYearList(Year);
        // 焦炭生产相关
        if (计划 != null && 计划.getProductionTonnageDay() != null) {
            other.setCokeProductionDailyPlan(计划.getProductionTonnageDay());//焦炭生产当日计划
        }
        if (本日 != null && 本日.getCokeDailyProduction() != null) {
            other.setCokeProductionDailyCompletion(本日.getCokeDailyProduction());//焦炭生产当日完成
        }
        if (计划 != null && 计划.getProductionTonnageMonth() != null) {
            other.setCokeProductionMonthlyPlan(计划.getProductionTonnageMonth());//焦炭生产本月计划
        }
        if (本月 != null && 本月.getCokeDailyProduction() != null) {
            other.setCokeProductionMonthlyCompletion(本月.getCokeDailyProduction());//焦炭生产本月完成
        }
        if (年计划 != null && 年计划.getProductionTonnageMonth() != null) {
            other.setCokeProductionYearlyPlan(年计划.getProductionTonnageMonth());//焦炭生产本年计划
        }
        if (本年 != null && 本年.getCokeDailyProduction() != null) {
            other.setCokeProductionYearlyCompletion(本年.getCokeDailyProduction());//焦炭生产本年完成
        }
        // 焦炭销售当日车数相关
        if (计划 != null && 计划.getSellingVehiclesDay() != null) {
            other.setCokeSalesDailyCarPlan(计划.getSellingVehiclesDay());//焦炭销售当日车数计划
        }
        if (本日 != null && 本日.getAcknowledgedCarsTotal() != null) {
            other.setCokeSalesDailyCarCompletion(本日.getCokeDailySalesCars());//焦炭销售当日车数完成
        }
        if (计划 != null && 计划.getSalesTonnageMonth() != null) {
            other.setCokeSalesMonthlyCarPlan(计划.getSalesTonnageMonth());//焦炭销售本月车数计划
        }
        if (本月 != null && 本月.getAcknowledgedCarsTotal() != null) {
            other.setCokeSalesMonthlyCarCompletion(本月.getAcknowledgedCarsTotal());//焦炭销售本月车数完成
        }
        if (年计划 != null && 年计划.getSalesTonnageMonth() != null) {
            other.setCokeSalesYearlyCarPlan(年计划.getSalesTonnageMonth());//焦炭销售本年车数计划
        }
        if (本年 != null && 本年.getAcknowledgedCarsTotal() != null) {
            other.setCokeSalesYearlyCarCompletion(本年.getAcknowledgedCarsTotal());//焦炭销售本年车数完成
        }
        // 焦炭销售吨数相关
        if (计划 != null && 计划.getSalesTonnageDay() != null) {
            other.setCokeSalesDailyTonPlan(计划.getSalesTonnageDay());//焦炭销售当日吨数计划
        }
        if (计划 != null && 计划.getSalesTonnageMonth() != null) {
            other.setCokeSalesMonthlyTonPlan(计划.getSalesTonnageMonth());//焦炭销售本月吨数计划
        }
        if (年计划 != null && 年计划.getSalesTonnageMonth() != null) {
            other.setCokeSalesYearlyTonPlan(年计划.getSalesTonnageMonth());//焦炭销售本年吨数计划
        }
        if (本日 != null && 本日.getCokeDailySalesTonnage() != null) {
            other.setCokeSalesDailyTonCompletion(本日.getCokeDailySalesTonnage());//焦炭销售当日吨数完成
        }
        if (本月 != null && 本月.getCokeDailySalesTonnage() != null) {
            other.setCokeSalesMonthlyTonCompletion(本月.getCokeDailySalesTonnage());//焦炭销售本月吨数完成
        }
        if (本年 != null && 本年.getCokeDailySalesTonnage() != null) {
            other.setCokeSalesYearlyTonCompletion(本年.getCokeDailySalesTonnage());//焦炭销售本年吨数完成
        }
        if (本日 != null && 本日.getDailyPowerGeneration() != null) {
            other.setPowerGenerationDaily(本日.getDailyPowerGeneration());//发电供电统计本日发电
        }
        if (本日 != null && 本日.getDailyPowerSupply() != null) {
            other.setPowerSupplyDaily(本日.getDailyPowerSupply());//发电供电统计本日供电
        }
        if (本日 != null && 本日.getMaxPower() != null) {
            other.setPowerMaxDaily(本日.getMaxPower());
        }
        if (本日 != null && 本日.getMinPower() != null) {
            other.setPowerMinDaily(本日.getMinPower());
        }
        if (本日 != null && 本日.getDailyCoalIn() != null) {
            other.setCoalInflowDaily(本日.getDailyCoalIn());
        }
        if (本日 != null && 本日.getDailyCoalConsumption() != null) {
            other.setCoalConsumptionDaily(本日.getDailyCoalConsumption());
        }
        // 发电供电统计本月数据
        if (本月 != null && 本月.getDailyPowerGeneration() != null) {
            other.setPowerGenerationMonthly(本月.getDailyPowerGeneration());//发电供电统计本月发电
        }
        if (本月 != null && 本月.getDailyPowerSupply() != null) {
            other.setPowerSupplyMonthly(本月.getDailyPowerSupply());//发电供电统计本月供电
        }
        if (本月 != null && 本月.getDailyCoalIn() != null) {
            other.setCoalInflowMonthly(本月.getDailyCoalIn());//发电供电统计本月进煤量
        }
        if (本月 != null && 本月.getDailyCoalConsumption() != null) {
            other.setCoalConsumptionMonthly(本月.getDailyCoalConsumption());//发电供电统计本月耗煤量
        }
        // 发电供电统计本年数据
        if (本年 != null && 本年.getDailyPowerGeneration() != null) {
            other.setPowerGenerationYearly(本年.getDailyPowerGeneration());//发电供电统计本年发电
        }
        if (本年 != null && 本年.getDailyPowerSupply() != null) {
            other.setPowerSupplyYearly(本年.getDailyPowerSupply());//发电供电统计本年供电
        }
        if (本年 != null && 本年.getDailyCoalIn() != null) {
            other.setCoalInflowYearly(本年.getDailyCoalIn());//发电供电统计本年进煤量
        }
        if (本年 != null && 本年.getDailyCoalConsumption() != null) {
            other.setCoalConsumptionYearly(本年.getDailyCoalConsumption());//发电供电统计本年耗煤量
        }
        // 供水量相关
        if (本日 != null && 本日.getDailyLivingWater() != null) {
            other.setDailyWaterSupplyDomestic(本日.getDailyLivingWater());//日供水生活用水
        }
        if (本日 != null && 本日.getDailyIndustrialWater() != null) {
            other.setDailyWaterSupplyIndustrial(本日.getDailyIndustrialWater());//日供水工业用水
        }
        if (本月 != null && 本月.getDailyLivingWater() != null) {
            other.setMonthlyWaterSupplyDomestic(本月.getDailyLivingWater());//月供水生活用水
        }
        if (本月 != null && 本月.getDailyIndustrialWater() != null) {
            other.setMonthlyWaterSupplyIndustrial(本月.getDailyIndustrialWater());//月供水工业用水
        }
        if (本年 != null && 本年.getDailyLivingWater() != null) {
            other.setYearlyWaterSupplyDomestic(本年.getDailyLivingWater());//年供水生活用水
        }
        if (本年 != null && 本年.getDailyIndustrialWater() != null) {
            other.setYearlyWaterSupplyIndustrial(本年.getDailyIndustrialWater());//年供水工业用水
        }
        // 东部管网水温相关
        if (本日 != null && 本日.getEastNetworkSupplyTempMax() != null) {
            other.setEastNetworkSupplyWaterMaxTemp(本日.getEastNetworkSupplyTempMax());
        }
        if (本日 != null && 本日.getEastNetworkSupplyTempMin() != null) {
            other.setEastNetworkSupplyWaterMinTemp(本日.getEastNetworkSupplyTempMin());
        }
        if (本日 != null && 本日.getEastNetworkReturnTempMax() != null) {
            other.setEastNetworkReturnWaterMaxTemp(本日.getEastNetworkReturnTempMax());
        }
        if (本日 != null && 本日.getEastNetworkReturnTempMin() != null) {
            other.setEastNetworkReturnWaterMinTemp(本日.getEastNetworkReturnTempMin());
        }
        // 西部管网水温相关
        if (本日 != null && 本日.getWestNetworkSupplyTempMax() != null) {
            other.setWestNetworkSupplyWaterMaxTemp(本日.getWestNetworkSupplyTempMax());
        }
        if (本日 != null && 本日.getWestNetworkSupplyTempMin() != null) {
            other.setWestNetworkSupplyWaterMinTemp(本日.getWestNetworkSupplyTempMin());
        }
        if (本日 != null && 本日.getWestNetworkReturnTempMax() != null) {
            other.setWestNetworkReturnWaterMaxTemp(本日.getWestNetworkReturnTempMax());
        }
        if (本日 != null && 本日.getWestNetworkReturnTempMin() != null) {
            other.setWestNetworkReturnWaterMinTemp(本日.getWestNetworkReturnTempMin());
        }
        return success(other);
    }

    /**
     * 公司数据报表-15外运统计表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-15外运统计表")
    @PostMapping("/outboundTransportationStatisticsTable")
    public AjaxResult outboundTransportationStatisticsTable(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        waiyuntongjiBiao waiyun = new waiyuntongjiBiao();
        ComprehensiveProductionStats dis = new ComprehensiveProductionStats();
        dis.setStatsDate(statsDate);
        ComprehensiveProductionStats 本日 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsDayList(dis);
        ComprehensiveProductionStats 本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        LongmeiProductionSalesStats longmeiProductionSalesStats = new LongmeiProductionSalesStats();
        longmeiProductionSalesStats.setStatsDate(statsDate);
        List<LongmeiProductionSalesStats> longmeiP = longmei.selectLongmeiProductionSalesStatsList(longmeiProductionSalesStats);
        // 本日数据相关
        if (本日 != null && 本日.getTotalSalesCars() != null) {
            waiyun.setDailyCars(本日.getTotalSalesCars());
        }
        if (本日 != null && 本日.getTotalSalesTonnage() != null) {
            waiyun.setDailyTons(本日.getTotalSalesTonnage());
        }
        if (本日 != null && 本日.getCleanCoalSalesCars() != null) {
            waiyun.setDailyCleanCoalCars(本日.getCleanCoalSalesCars());
        }
        if (本日 != null && 本日.getCleanCoalSalesTonnage() != null) {
            waiyun.setDailyCleanCoalTons(本日.getCleanCoalSalesTonnage());
        }
// 本月数据相关
        if (本月 != null && 本月.getTotalSalesCars() != null) {
            waiyun.setMonthlyCars(本月.getTotalSalesCars());
        }
        if (本月 != null && 本月.getTotalSalesTonnage() != null) {
            waiyun.setMonthlyTons(本月.getTotalSalesTonnage());
        }
        if (本月 != null && 本月.getCleanCoalSalesCars() != null) {
            waiyun.setMonthlyCleanCoalCars(本月.getCleanCoalSalesCars());
        }
        if (本月 != null && 本月.getCleanCoalSalesTonnage() != null) {
            waiyun.setMonthlyCleanCoalTons(本月.getCleanCoalSalesTonnage());
        }
// 本年数据相关
        if (本年 != null && 本年.getTotalSalesCars() != null) {
            waiyun.setYearlyCars(本年.getTotalSalesCars());
        }
        if (本年 != null && 本年.getTotalSalesTonnage() != null) {
            waiyun.setYearlyTons(本年.getTotalSalesTonnage());
        }
        if (本年 != null && 本年.getCleanCoalSalesCars() != null) {
            waiyun.setYearlyCleanCoalCars(本年.getCleanCoalSalesCars());
        }
        if (本年 != null && 本年.getCleanCoalSalesTonnage() != null) {
            waiyun.setYearlyCleanCoalTons(本年.getCleanCoalSalesTonnage());
        }
// 其他本日相关数据
        if (本日 != null && 本日.getAcknowledgedCarsTotal() != null) {
            waiyun.setAdmittedCarsTotal(本日.getAcknowledgedCarsTotal());
        }
        if (本日 != null && 本日.getCleanCoalAcknowledged() != null) {
            waiyun.setAdmittedCleanCoal(本日.getCleanCoalAcknowledged());
        }
        if (本日 != null && 本日.getShenjuCars() != null) {
            waiyun.setShenJu(本日.getShenjuCars());
        }
        if (本日 != null && 本日.getQitaihePowerPlantCars() != null) {
            waiyun.setOutwardTransport(本日.getQitaihePowerPlantCars());
        }
        if (本日 != null && 本日.getQitaihePowerPlant() != null) {
            waiyun.setQiDianChang(本日.getQitaihePowerPlant());
        }
        if (本日 != null && 本日.getLongyangCars() != null) {
            waiyun.setLongYang(本日.getLongyangCars());
        }
        waiyun.setList(longmeiP);
        return success(waiyun);
    }

    @Anonymous
    @Operation(summary = "D08入井、升井日报")
    @PostMapping("/shiftReport")
    public AjaxResult shiftReport(@RequestBody EnterExitQueryDTO dto) {
        List<EnterExitShiftVO> list = service08.shiftReport(dto);
        return AjaxResult.success(list);
    }

    @Anonymous // 便于联调；需要鉴权可去掉并加 @PreAuthorize
    @Operation(summary = "D10调度生产日报1")
    @PostMapping("/dispatchDailyReport1")
    public AjaxResult dispatchDailyReport1(@RequestBody MineDayCumReportV2RequestDTO dto) {
        List<MineDayCumReportV2VO> data = service10.buildReport(dto);
        return AjaxResult.success(data);
    }

    @Anonymous // 便于联调；需要鉴权可去掉并加 @PreAuthorize
    @Operation(summary = "D11调度日报2 上面")
    @PostMapping("/dispatchDailyReport2")
    public AjaxResult dispatchDailyReport2(@RequestBody MineDayCumReportV3RequestDTO dto) {
        List<MineDayCumReportV3VO> data = service11.buildReport(dto);
        return AjaxResult.success(data);
    }

    @Anonymous // 便于联调；需要鉴权可去掉并加 @PreAuthorize
    @Operation(summary = "D11调度日报2 下面")
    @PostMapping("/dispatchDailyReport2other")
    public AjaxResult dispatchDailyReport2other(@RequestBody OtherDataReportV1RequestDTO dto) {
        List<OtherDataReportV1VO> data = service11other.byDate(dto);
        return AjaxResult.success(data);
    }
    /**
     * 公司数据报表-12公司生产目标日报
     */
    @Anonymous
    @Operation(summary = "公司数据报表-12公司生产目标日报")
    @PostMapping("/ShengChanMubiaoALLlist")
    public TableDataInfo<BaseEntity> ShengChanMubiaoALLlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计 = new shengChanRiBao();
        合计.setDayPlan(BigDecimal.ZERO);
        合计.setMonthPlan(BigDecimal.ZERO);
        合计.setYearPlan(BigDecimal.ZERO);
        合计.setDayComplete(BigDecimal.ZERO);
        合计.setMonthComplete(BigDecimal.ZERO);
        合计.setYearComplete(BigDecimal.ZERO);
        String yue1 = DateUtils.returnDateDay(statsDate);

        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanYear(statsDate, "生产");

        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        合计.setUnitName("合计");
        for (MiningAreaCategory mining : miningAreaCategories) {
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(mining.getAreaCode(), statsDate);
            shengChanRiBao 煤矿 = new shengChanRiBao();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayTarget())).sum()));
            煤矿.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayTarget())).sum()));
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthTarget() != null) {
                煤矿.setYearPlan(年计划内容.getMonthTarget());
            } else {
                煤矿.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getProductionData() != null) {
                煤矿.setDayComplete(BigDecimal.valueOf(日完成内容.getProductionData()));
            } else {
                煤矿.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getProductionData() != null) {
                煤矿.setMonthComplete(BigDecimal.valueOf(月完成内容.getProductionData()));
            } else {
                煤矿.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getProductionData() != null) {
                煤矿.setYearComplete(BigDecimal.valueOf(年完成内容.getProductionData()));
            } else {
                煤矿.setYearComplete(BigDecimal.valueOf(0));
            }
            list.add(煤矿);
            合计.setDayPlan(合计.getDayPlan().add(煤矿.getDayPlan()));
            合计.setMonthPlan(合计.getMonthPlan().add(煤矿.getMonthPlan()));
            合计.setYearPlan(合计.getYearPlan().add(煤矿.getYearPlan()));
            合计.setDayComplete(合计.getDayComplete().add(煤矿.getDayComplete()));
            合计.setMonthComplete(合计.getMonthComplete().add(煤矿.getMonthComplete()));
            合计.setYearComplete(合计.getYearComplete().add(煤矿.getYearComplete()));
        }
        list.add(0,合计);
        return getDataTable(list);
    }
    /**
     * 公司数据报表-13公司进尺目标日报表
     */
    @Anonymous
    @Operation(summary = "公司数据报表-13公司进尺目标日报表")
    @PostMapping("/JinChiMubiaoALLlist")
    public TableDataInfo<BaseEntity> JinChiMubiaoALLlist(@RequestBody riBao tiaojian) {
        Date statsDate = tiaojian.getStatsDate();
        List<shengChanRiBao> list = new ArrayList<>();
        shengChanRiBao 合计 = new shengChanRiBao();
        合计.setDayPlan(BigDecimal.ZERO);
        合计.setMonthPlan(BigDecimal.ZERO);
        合计.setYearPlan(BigDecimal.ZERO);
        合计.setDayComplete(BigDecimal.ZERO);
        合计.setMonthComplete(BigDecimal.ZERO);
        合计.setYearComplete(BigDecimal.ZERO);
        String yue1 = DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("进尺");
        minday.setPlanMonth(yue1);
        minday.setStatus(0);
        List<MinePlanDay> 月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        List<SubMinePlanPO> 年计划 = subMinePlanMapper.selectByPlanYear(statsDate, "进尺");
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        合计.setUnitName("合计");
        for (MiningAreaCategory mining : miningAreaCategories) {
            List<SubMineDevelopmentDataPO> 日完成 = subMineDevelopmentDataMapper.selectDay(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 月完成 = subMineDevelopmentDataMapper.selectMonth(mining.getAreaCode(), statsDate);
            List<SubMineDevelopmentDataPO> 年完成 = subMineDevelopmentDataMapper.selectYear(mining.getAreaCode(), statsDate);
            shengChanRiBao 煤矿 = new shengChanRiBao();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setDayPlan(BigDecimal.valueOf(日计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayTarget())).sum()));
            煤矿.setMonthPlan(BigDecimal.valueOf(月计划.stream().filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayTarget())).sum()));
            SubMinePlanPO 年计划内容 = 年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            if (年计划内容.getMonthTarget() != null) {
                煤矿.setYearPlan(年计划内容.getMonthTarget());
            } else {
                煤矿.setYearPlan(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 日完成内容 = 日完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (日完成内容.getFootageData() != null) {
                煤矿.setDayComplete(日完成内容.getFootageData());
            } else {
                煤矿.setDayComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 月完成内容 = 月完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (月完成内容.getFootageData() != null) {
                煤矿.setMonthComplete(月完成内容.getFootageData());
            } else {
                煤矿.setMonthComplete(BigDecimal.valueOf(0));
            }
            SubMineDevelopmentDataPO 年完成内容 = 年完成.stream().filter(item -> "合计".equals(item.getUnitNameJSON())).findFirst().orElse(new SubMineDevelopmentDataPO());
            if (年完成内容.getFootageData() != null) {
                煤矿.setYearComplete(年完成内容.getFootageData());
            } else {
                煤矿.setYearComplete(BigDecimal.valueOf(0));
            }

            list.add(煤矿);
            合计.setDayPlan(合计.getDayPlan().add(煤矿.getDayPlan()));
            合计.setMonthPlan(合计.getMonthPlan().add(煤矿.getMonthPlan()));
            合计.setYearPlan(合计.getYearPlan().add(煤矿.getYearPlan()));
            合计.setDayComplete(合计.getDayComplete().add(煤矿.getDayComplete()));
            合计.setMonthComplete(合计.getMonthComplete().add(煤矿.getMonthComplete()));
            合计.setYearComplete(合计.getYearComplete().add(煤矿.getYearComplete()));
        }
        list.add(0,合计);
        return getDataTable(list);
    }
    /**
     * 获取部门信息
     */
    @GetMapping(value = "/getObtainDepartmentInformation")
    public AjaxResult getObtainDepartmentInformation() {
        bianMa bian = new bianMa();
        SysDept sysDept = deptService.selectDeptById(SecurityUtils.getDeptId());
        if (sysDept != null) {
            String deptName = sysDept.getDeptName();
            SysDept sysDeptParent = deptService.selectDeptById(sysDept.getParentId());
            if (sysDeptParent != null) {
                if (sysDeptParent.getDeptName().equals("全部厂")) {
                    FactoryArchive name = factoryArchiveMapper.getName(deptName);
                    bian.setCode(name.getFactoryCode());
                    bian.setName(name.getFactoryName());
                }
                if (sysDeptParent.getDeptName().equals("全部矿")) {
                    MiningAreaCategory name = miningAreaCategoryService.getAreaName(deptName);
                    bian.setCode(name.getAreaCode());
                    bian.setName(name.getAreaName());
                }
            }
        }
        return success(bian);
    }

    /**
     * 获取页面
     */
    @GetMapping(value = "/getReport")
    public AjaxResult getReport() {
        List<SysMenu> list = menuMapper.selectMenuPermsByRoleList("D293B872415E455CA169514EC7F016CB");
        List<SysMenu> list1 = new ArrayList<>();
        for (SysMenu menu : list) {
            if (!menu.getMenuId().equals("85CDAD9CB0BB410E850C848F9C39CE51")) {
                list1.add(menu);
            }
        }
        return AjaxResult.success(list1);
    }

     private static int toInt(Integer value) {
        return Optional.ofNullable(value).orElse(0);
    }

    private static int toInt(Long value) {
        return Optional.ofNullable(value).map(Long::intValue).orElse(0);
    }

    private static int toInt(BigDecimal value) {
        BigDecimal result = Optional.ofNullable(value).orElse(BigDecimal.ZERO);
        return result.setScale(0, BigDecimal.ROUND_DOWN).intValueExact();
    }

}
