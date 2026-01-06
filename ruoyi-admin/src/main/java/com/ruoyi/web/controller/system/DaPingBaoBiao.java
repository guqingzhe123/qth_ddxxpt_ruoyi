package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMineDevelopmentDataPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanP1;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;
import com.ruoyi.system.domain.ribaobaobiao.jiaoTanQingKuang;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.domain.ribaobaobiao.shengChanRiBao;
import com.ruoyi.system.mapper.BaoBiao.SubMineDevelopmentDataMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMinePlanMapper;
import com.ruoyi.system.mapper.CoalMineWorkingDayMapper;
import com.ruoyi.system.mapper.ComprehensiveProductionStatsMapper;
import com.ruoyi.system.mapper.MinePlanDayMapper;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Api(tags = "大屏接口")
@RestController
@RequestMapping("/system/largeScreen")
public class DaPingBaoBiao extends BaseController {
    @Resource
    private IMddStatService largeScreenStatistics;//大屏统计
    @Resource
    private IPesStatService pesStatService; //七日产耗趋势
    @Resource
    private IEemStatService eemStatService; //入井/出井 - 七日前每日总合统计
    @Resource
    private ICwpStatService cwpStatService; //洗煤生产 - 当日汇总接口
    @Autowired
    private IUserMessageService userMessageService;
    @Autowired
    private SubMinePlanMapper subMinePlanMapper;//  计划子表
    @Autowired
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成子表
    @Autowired
    private ComprehensiveProductionStatsMapper comprehensiveProductionStatsMapper;//调度日报其他数据Service业务层处理

    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作日Mapper接口
    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//每日计划  煤矿自己看自己的计划
    /**
     * 查询【用户消息提示表】列表
     */
    @PostMapping("/UserMessagelist")
    public TableDataInfo<BaseEntity> list(@RequestBody UserMessageInput userMessage) {
        startPage();
        UserMessage user=new UserMessage(null,userMessage.getReceiver(),null,null);
        List<UserMessage> list = userMessageService.listUserMessage(user);
        return getDataTable(list);
    }

    /**
     * POST /dev-api/system/mddStat/summaryByDate
     * 入参：{ "record_date": "yyyy-MM-dd" }
     * 出参：四段对象的数组（键名与产品约定完全一致）
     */
    @Anonymous
    @Operation(summary = "按日期汇总（生产/进尺/开拓 + 七日序列）")  //数据汇总   及柱状图  近7日公司生产产量   及柱状图 近7日公司开拓进尺数据
    @PostMapping("/summaryByDate")
    public AjaxResult summaryByDate(@Validated @RequestBody EemSevenDayReq.MddStatQueryDTO dto) {
        List<Object> data = largeScreenStatistics.summaryByDate(dto);
        return AjaxResult.success(data);
    }
    @Anonymous
    @Operation(summary = "产品外销 - 七日前每日总合（车次/吨数），按示例结构返回") //折线图7日产销趋势
    @PostMapping("/sevenDayTotals")
    public AjaxResult sevenDayTotals(@RequestBody EemSevenDayReq.PesSevenDayReq req) {
        List<PesSevenDayRespGroupVO> data = pesStatService.sevenDayTotals(req);
        return AjaxResult.success(data);
    }

    @Anonymous
    @Operation(summary = "入井/出井 - 七日前每日总合（入井/升井），返回数组（仅一个对象，含两个数组字段）")//7日采掘工人升入井趋势
    @PostMapping("/EnteringExitingwell")
    public AjaxResult EnteringExitingwell(@RequestBody EemSevenDayReq req) {
        EemSevenDayRespGroupVO vo= new EemSevenDayRespGroupVO();
        LocalDate end = DateUtils.parseFlexibleDate(req.getRecord_date());
        LocalDate start = end.minusDays(6);
        List<LocalDate> days = daysRange(start, end);

        List<EemTotalItemVO> 出=new ArrayList<>();
        List<EemTotalItemVO> 入=new ArrayList<>();
        for (LocalDate date:days) {
            SubMineDevelopmentDataPO  日完成=subMineDevelopmentDataMapper.selectAllDay(DateUtils.toDate(date));
            EemTotalItemVO 出1=new EemTotalItemVO();
            EemTotalItemVO 入1=new EemTotalItemVO();
            出1.setTotal(BigDecimal.valueOf(日完成 != null ? 日完成.getEnterWellNum() : 0));
            入1.setTotal(BigDecimal.valueOf(日完成 != null ? 日完成.getComeOutWellNum() : 0));
            出.add(出1);
            入.add(入1);
        }
        vo.setDownTotals(入);//入井人数
        vo.setUpTotals(出);//出井人数
        List<EemSevenDayRespGroupVO> data = new ArrayList<>(); //eemStatService.sevenDayTotals(req);
        data.add(vo);
        return AjaxResult.success(data);
    }
    @Anonymous
    @Operation(summary = "洗煤生产-按日汇总（掉入/入洗/精煤 + 精煤产率百分比）")//洗煤（全公司）数据统计
    @PostMapping("/dailySummary")
    public AjaxResult dailySummary(@RequestBody EemSevenDayReq.CwpDailySummaryReq req) {
        List<CwpDailySummaryItemVO> data = cwpStatService.dailySummary(req);
        return AjaxResult.success(data);
    }
    @Anonymous
    @Operation(summary = "大屏-生产业务看板")//洗煤（全公司）数据统计
    @PostMapping("/ProductionBusinessDashboard")
    public AjaxResult ProductionBusinessDashboard(@RequestBody riBao tiaojian) {
        Date statsDate=tiaojian.getStatsDate();
        Date yue= DateUtils.getFirstDayOfMonth(statsDate);

        String yue1= DateUtils.returnDateDay(statsDate);
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        MinePlanPO mine=new MinePlanPO();
        mine.setPlanMonth(yue);
        mine.setMineCategory("全矿");
        mine.setPlanType("生产");
        SubMinePlanP1 月生产计划 = subMinePlanMapper.selectAllJiHua(mine);
        SubMinePlanP1 年生产计划 = subMinePlanMapper.selectAllJiHuaYear(mine);
        mine.setPlanType("进尺");
        SubMinePlanP1 月进尺计划 = subMinePlanMapper.selectAllJiHua(mine);
        SubMinePlanP1 年进尺计划 = subMinePlanMapper.selectAllJiHuaYear(mine);
        mine.setPlanType("开拓");
        SubMinePlanP1 月开拓计划 = subMinePlanMapper.selectAllJiHua(mine);
        SubMinePlanP1 年开拓计划 = subMinePlanMapper.selectAllJiHuaYear(mine);

        //List<CoalMineWorkingDay> 日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByUnitName(yue1, day);
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(yue1);
        minday.setPlanDay(day);
        minday.setStatus(0);
        List<MinePlanDay> 生产月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanType("开拓");
        List<MinePlanDay> 开拓月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanType("进尺");
        List<MinePlanDay> 进尺月计划 = minePlanDayMapper.selectMinePlanMonth(minday);

        SubMineDevelopmentDataPO  日完成=subMineDevelopmentDataMapper.selectAllDay(statsDate);
        SubMineDevelopmentDataPO  月完成=subMineDevelopmentDataMapper.selectAllMonth(statsDate);
        SubMineDevelopmentDataPO  年完成=subMineDevelopmentDataMapper.selectAllYear(statsDate);
        List<shengChanRiBao> data =new ArrayList<>();
        shengChanRiBao 生产=new shengChanRiBao();
        shengChanRiBao 开拓=new shengChanRiBao();
        shengChanRiBao 进尺=new shengChanRiBao();
        生产.setUnitName("生产");
        开拓.setUnitName("开拓");
        进尺.setUnitName("进尺");
        生产.setDayPlan(BigDecimal.valueOf(生产月计划.stream().mapToInt(po -> po.getDayPlan()).sum()));
        进尺.setDayPlan(BigDecimal.valueOf(进尺月计划.stream().mapToInt(po -> po.getDayPlan()).sum()));
        开拓.setDayPlan(BigDecimal.valueOf(开拓月计划.stream().mapToInt(po -> po.getDayPlan()).sum()));

        生产.setMonthPlan(月生产计划 != null ? 月生产计划.getMonthPlan() : BigDecimal.valueOf(0));
        生产.setYearPlan(年生产计划 != null ? 年生产计划.getYearPlan() : BigDecimal.valueOf(0));
        进尺.setMonthPlan(月进尺计划 != null ? 月进尺计划.getMonthPlan() : BigDecimal.valueOf(0));
        进尺.setYearPlan(年进尺计划 != null ? 年进尺计划.getYearPlan() : BigDecimal.valueOf(0));
        开拓.setMonthPlan(月开拓计划 != null ? 月开拓计划.getMonthPlan() : BigDecimal.valueOf(0));
        开拓.setYearPlan(年开拓计划 != null ? 年开拓计划.getYearPlan() : BigDecimal.valueOf(0));

        开拓.setDayComplete(BigDecimal.valueOf(日完成 != null ? 日完成.getExpandData() : 0));
        进尺.setDayComplete(BigDecimal.valueOf(日完成 != null ? 日完成.getFootageData() : 0));
        生产.setDayComplete(BigDecimal.valueOf(日完成 != null ? 日完成.getProductionData() : 0));
        
        开拓.setMonthComplete(BigDecimal.valueOf(月完成 != null ? 月完成.getExpandData() : 0));
        进尺.setMonthComplete(BigDecimal.valueOf(月完成 != null ? 月完成.getFootageData() : 0));
        生产.setMonthComplete(BigDecimal.valueOf(月完成 != null ? 月完成.getProductionData() : 0));
        
        开拓.setYearComplete(BigDecimal.valueOf(年完成 != null ? 年完成.getExpandData() : 0));
        进尺.setYearComplete(BigDecimal.valueOf(年完成 != null ? 年完成.getFootageData() : 0));
        生产.setYearComplete(BigDecimal.valueOf(年完成 != null ? 年完成.getProductionData() : 0));

        data.add(生产);
        data.add(开拓);
        data.add(进尺);
        return AjaxResult.success(data);
    }

    @Anonymous
    @Operation(summary = "焦炭生产、销售情况")//洗煤（全公司）数据统计
    @PostMapping("/ProductionSalesCoke")
    public AjaxResult ProductionSalesCoke(@RequestBody riBao tiaojian){
        ComprehensiveProductionStats dis=new ComprehensiveProductionStats();
        dis.setStatsDate(tiaojian.getStatsDate());
        ComprehensiveProductionStats 本日 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsDayList(dis);
        jiaoTanQingKuang jiaotan = new jiaoTanQingKuang();
        jiaotan.setCokeDailyProduction(本日 != null ? 本日.getCokeDailyProduction() : BigDecimal.valueOf(0));  /** 本日生产吨数 */
        jiaotan.setTotalSalesCars(本日 != null ? 本日.getTotalSalesCars() : "0");  /** 外运销售车数 */
        jiaotan.setTotalSalesTonnage(本日 != null ? 本日.getTotalSalesTonnage() : BigDecimal.valueOf(0));  /** 外运销售吨数 */
        jiaotan.setCleanCoalSalesCars(本日 != null ? 本日.getCleanCoalSalesCars() : "0");  /** 外运销售吨数 */
        jiaotan.setCleanCoalSalesTonnage(本日 != null ? 本日.getCleanCoalSalesTonnage() : BigDecimal.valueOf(0));  /** 外运销售吨数 */
        return AjaxResult.success(jiaotan);
    }
    private List<LocalDate> daysRange(LocalDate start, LocalDate end) {
        int len = (int) (end.toEpochDay() - start.toEpochDay());
        return IntStream.rangeClosed(0, len)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }
}
