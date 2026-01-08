package com.ruoyi.system.service.PingBao;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.BaoBiao.po.CoalWashingProductionPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubWashCoalPlanPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.Biaodan.Production;
import com.ruoyi.system.domain.Biaodan.StatisticsFootage;
import com.ruoyi.system.domain.Biaodan.SummaryTable;
import com.ruoyi.system.domain.Biaodan.TargetAll;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.ribaobaobiao.xiMeiRiBaoBaoBiao;
import com.ruoyi.system.domain.work.CompletionRawCoalGeneration;
import com.ruoyi.system.domain.work.SafetyInfluencingFactors;
import com.ruoyi.system.domain.work.StatisticalTableRawCoal;
import com.ruoyi.system.mapper.BaoBiao.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.mapper.work.SafetyInfluencingFactorsMapper;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IShengChanRiBaoService {
    @Autowired
    private SubMinePlanMapper subMinePlanMapper;//计划子表
    @Autowired
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成子表
    @Autowired
    private SubWashCoalPlanMapper subWashCoalPlanMapper ;//洗煤计划子表
    @Autowired
    private CoalWashingProductionMapper coalWashingProductionMapper;//洗煤生产录入表
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    @Autowired
    private DestinationOfRawCoalMapper destinationOfRawCoalMapper;//原煤去向录入表
    @Autowired
    private ComprehensiveProductionStatsMapper comprehensiveProductionStatsMapper;//调度日报其他数据Service业务层处理
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作日Mapper接口
    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//每日计划  煤矿自己看自己的计划
    @Autowired
    private InitialInventoryOfEachMineMapper initialInventoryOfEachMineMapper;//各矿期初库存录入表
    @Autowired
    private SubInitialInventoryOfEachMineMapper subInitial;//各矿期初库存录入表 录入子表
    @Autowired
    private MinePlanOutwardTransportMapper minePlanOutwardTransportMapper;//外运月计划

    @Autowired
    private SafetyInfluencingFactorsMapper safetyInfluencingFactorsMapper;//公司各单位影响安全生产因素（原因）列表

    /***
     * 生产汇总表  传入时间
     */
    public List<SummaryTable> ProductionSummaryTable(Date statsDate) {
        List<SummaryTable> list= new ArrayList<>();
        SummaryTable 合计=new SummaryTable();
//        String yue1= DateUtils.returnDateDay(statsDate);
        List<SubMinePlanPO>  生产年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"生产");
        List<SubMinePlanPO>  开拓年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"开拓");
        List<SubMinePlanPO>  进尺年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"进尺");
        List<SubMinePlanPO>  生产月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"生产");
        List<SubMinePlanPO>  开拓月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"开拓");
        List<SubMinePlanPO>  进尺月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"进尺");
        List<MineData> 月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(statsDate));
        List<MineData> 年完成=subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(statsDate));
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司

        合计.setUnitName("合计");
        for (MiningAreaCategory  mining:miningAreaCategories) {
            SubMinePlanPO 生产月计划内容 = 生产月计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            SubMinePlanPO 开拓月计划内容 = 开拓月计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            SubMinePlanPO 进尺月计划内容 = 进尺月计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            SubMinePlanPO 生产年计划内容 = 生产年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            SubMinePlanPO 开拓年计划内容 = 开拓年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            SubMinePlanPO 进尺年计划内容 = 进尺年计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());

            
            List<MineData> 月完成内容 = 月完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).collect(Collectors.toList());
            List<MineData> 年完成内容 = 年完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).collect(Collectors.toList());
            SummaryTable 煤矿=new SummaryTable();
            煤矿.setUnitName(mining.getAreaName());
            煤矿.setProductionMonthPlan(toInt(生产月计划内容.getMonthPlan()));//原煤本月计划
            煤矿.setProductionMonthCompleted(月完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getProductionData())).sum());//原煤本月完成   toInt(月完成内容.getProductionData())
            煤矿.setProductionCumulativePlan(toInt(生产年计划内容.getMonthPlan()));//原煤本年累计计划
            煤矿.setProductionCumulativeCompleted(年完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getProductionData())).sum());//原煤本年累计完成
            煤矿.setFootageMonthPlan(toInt(进尺月计划内容.getMonthPlan()));//进尺本月计划
            煤矿.setFootageMonthCompleted(月完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getFootageData())).sum());//进尺本月完成
            煤矿.setFootageCumulativePlan(toInt(进尺年计划内容.getMonthPlan()));//进尺本年累计计划
            煤矿.setFootageCumulativeCompleted(年完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getFootageData())).sum());//进尺本年累计完成
            煤矿.setExpandMonthPlan(toInt(开拓月计划内容.getMonthPlan()));//开拓本月计划
            煤矿.setExpandMonthCompleted(月完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getExpandData())).sum());//开拓本月完成
            煤矿.setExpandCumulativePlan(toInt(开拓年计划内容.getMonthPlan()));//开拓本年累计计划
            煤矿.setExpandCumulativeCompleted(年完成内容.stream().filter(Objects::nonNull).mapToInt(po -> toInt(po.getExpandData())).sum());//开拓本年累计完成



            合计.setProductionMonthPlan(toInt(合计.getProductionMonthPlan())+toInt(煤矿.getProductionMonthPlan()));
            合计.setProductionMonthCompleted(toInt(合计.getProductionMonthCompleted())+toInt(煤矿.getProductionMonthCompleted()));
            合计.setProductionCumulativePlan(toInt(合计.getProductionCumulativePlan())+toInt(煤矿.getProductionCumulativePlan()));
            合计.setProductionCumulativeCompleted(toInt(合计.getProductionCumulativeCompleted())+toInt(煤矿.getProductionCumulativeCompleted()));
            合计.setFootageMonthPlan(toInt(合计.getFootageMonthPlan())+toInt(煤矿.getFootageMonthPlan()));
            合计.setFootageMonthCompleted(toInt(合计.getFootageMonthCompleted())+toInt(煤矿.getFootageMonthCompleted()));
            合计.setFootageCumulativePlan(toInt(合计.getFootageCumulativePlan())+toInt(煤矿.getFootageCumulativePlan()));
            合计.setFootageCumulativeCompleted(toInt(合计.getFootageCumulativeCompleted())+toInt(煤矿.getFootageCumulativeCompleted()));
            合计.setExpandMonthPlan(toInt(合计.getExpandMonthPlan())+toInt(煤矿.getExpandMonthPlan()));
            合计.setExpandMonthCompleted(toInt(合计.getExpandMonthCompleted())+toInt(煤矿.getExpandMonthCompleted()));
            合计.setExpandCumulativePlan(toInt(合计.getExpandCumulativePlan())+toInt(煤矿.getExpandCumulativePlan()));
            合计.setExpandCumulativeCompleted(toInt(合计.getExpandCumulativeCompleted())+toInt(煤矿.getExpandCumulativeCompleted()));
            list.add(煤矿);
        }
        list.add(0,合计);
        return list;
    }

    /***
     * 生产指标完成情况
     */

    public List<Production> ProductionCompleted(Date statsDate) {
        List<Production> list= new ArrayList<>();
        String yue1= DateUtils.returnDateRange(DateUtils.getLastDayOfMonth(statsDate));
        String lastYear= DateUtils.returnDateRange(DateUtils.minusOneYear(DateUtils.getLastDayOfMonth(statsDate)));
        Date lastDate = DateUtils.minusOneYear(statsDate);

        List<SubMinePlanPO>  生产月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"生产");
        List<SubMinePlanPO>  开拓月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"开拓");
        List<SubMinePlanPO>  进尺月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"进尺");
        List<SubMinePlanPO>  生产年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"生产");
        List<SubMinePlanPO>  开拓年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"开拓");
        List<SubMinePlanPO>  进尺年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"进尺");
        List<MineData> 月完成 = subMineDevelopmentDataMapper.selectMonthDate(yue1);
        List<MineData> 年完成 = subMineDevelopmentDataMapper.selectYearDate(yue1);
        List<MineData> 去年月完成 = subMineDevelopmentDataMapper.selectMonthDate(lastYear);
        List<MineData> 去年年完成 = subMineDevelopmentDataMapper.selectYearDate(lastYear);

        int 生产本月计划 = 生产月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 生产本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//全公司月完成
        int 生产去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司月完成
        int 生产本月累计计划 = 生产年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 生产本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//全公司1-10月完成
        int 生产去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司1-10月完成
        Production p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("原煤生产");
        p.setMonthPlan(生产本月计划);/** 本月计划 */
        p.setMonthComplete(生产本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(生产去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(生产本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(生产本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(生产去年本月累计完成);    /** 累计上年同期月完成 */
        list.add(p);
        int 开拓本月计划 = 开拓月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 开拓本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司月完成
        int 开拓去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司月完成
        int 开拓本月累计计划 = 开拓年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 开拓本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司1-10月完成
        int 开拓去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司1-10月完成
        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("开拓生产");
        p.setMonthPlan(开拓本月计划);/** 本月计划 */
        p.setMonthComplete(开拓本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(开拓去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(开拓本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(开拓本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(开拓去年本月累计完成);    /** 累计上年同期月完成 */
        list.add(p);
        int 进尺本月计划 = 进尺月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 进尺本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//全公司月完成
        int 进尺去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司月完成
        int 进尺本月累计计划 = 进尺年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 进尺本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//全公司1-10月完成
        int 进尺去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司1-10月完成
        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("进尺");
        p.setMonthPlan(进尺本月计划);/** 本月计划 */
        p.setMonthComplete(进尺本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(进尺去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(进尺本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(进尺本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(进尺去年本月累计完成);    /** 累计上年同期月完成 */
        list.add(p);
        //洗精煤
        List<SubWashCoalPlanPO> 洗煤日月计划 = subWashCoalPlanMapper.selectByPlanDayUnitCode(statsDate);
        List<SubWashCoalPlanPO> 洗煤年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(statsDate);
        List<CoalWashingProductionPO> 月完成洗煤=coalWashingProductionMapper.selecMonthList(statsDate);
        List<CoalWashingProductionPO>  年完成洗煤=coalWashingProductionMapper.selecYearList(statsDate);
        List<SubWashCoalPlanPO> 去年洗煤日月计划 = subWashCoalPlanMapper.selectByPlanDayUnitCode(lastDate);
        List<SubWashCoalPlanPO> 去年洗煤年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(lastDate);
//        List<CoalWashingProductionPO> 去年月完成洗煤=coalWashingProductionMapper.selecMonthList(lastDate);
//        List<CoalWashingProductionPO>  去年年完成洗煤=coalWashingProductionMapper.selecYearList(lastDate);

        int 洗精煤月计划 = 洗煤日月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
        int 洗精煤年计划=洗煤年计划.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();
        int 本月精煤产量=月完成洗煤.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
        int 本年精煤产量=年完成洗煤.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
        int 去年洗精煤月计划 = 去年洗煤日月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
        int 去年洗精煤年计划=去年洗煤年计划.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();

        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("洗精煤");
        p.setMonthPlan(洗精煤月计划);/** 本月计划 */
        p.setMonthComplete(本月精煤产量);/** 本月完成 */
        p.setLastYearmonthComplete(洗精煤年计划); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(本年精煤产量);/** 累计月计划 */
        p.setCumulativeMonthComplete(去年洗精煤月计划);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(去年洗精煤年计划);    /** 累计上年同期月完成 */
        list.add(p);

        //外运
        ComprehensiveProductionStats dis=new ComprehensiveProductionStats();
        dis.setStatsDate(statsDate);
        ComprehensiveProductionStats 本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        dis.setStatsDate(  DateUtils.getFirstDayOfMonth(statsDate));
        ComprehensiveProductionStats 去年本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 去年本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);

        MinePlanOutwardTransport 本月计划 = minePlanOutwardTransportMapper.selectMinePlanOutwardTransportByMonth(statsDate);
        MinePlanOutwardTransport 本年计划 = minePlanOutwardTransportMapper.selectMinePlanOutwardTransportByYear(statsDate);


        p=new Production();
        p.setUnitName("其中精煤");
        p.setUnitType("外运");
        p.setMonthPlan(本月计划 != null ? toInt(本月计划.getCleanCoalSalesTons()) : 0);/** 本月计划 */
        p.setMonthComplete(本月 != null ? toInt(本月.getTotalSalesTonnage()) : 0);/** 本月完成 */
        p.setLastYearmonthComplete(去年本月 != null ? toInt(去年本月.getTotalSalesTonnage()) : 0); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(本年计划 != null ? toInt(本年计划.getCleanCoalSalesTons()) : 0);/** 累计月计划 */
        p.setCumulativeMonthComplete(本年 != null ? toInt(本年.getTotalSalesTonnage()) : 0);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(去年本年 != null ? toInt(去年本年.getTotalSalesTonnage()) : 0);    /** 累计上年同期月完成 */
        list.add(p);

        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("外运");
        p.setMonthPlan(本月计划 != null ? toInt(本月计划.getTotalCompanySalesTons()) : 0);/** 本月计划 */
        p.setMonthComplete(本月 != null ? toInt(本月.getCleanCoalSalesTonnage()) : 0);/** 本月完成 */
        p.setLastYearmonthComplete(去年本月 != null ? toInt(去年本月.getCleanCoalSalesTonnage()) : 0); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(本年计划 != null ? toInt(本年计划.getTotalCompanySalesTons()) : 0);/** 累计月计划 */
        p.setCumulativeMonthComplete(本年 != null ? toInt(本年.getCleanCoalSalesTonnage()) : 0);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(去年本年 != null ? toInt(去年本年.getCleanCoalSalesTonnage()) : 0);    /** 累计上年同期月完成 */
        list.add(p);
        return list;
    }
    /***
     * 生产指标完成情况统计表(上报龙煤)
     */
    public TargetAll statisticsProductionTargetCompletion(Date statsDate) {
        TargetAll targetAll= new TargetAll();

        List<Production> 生产= new ArrayList<>();
        List<Production> 开拓= new ArrayList<>();
        List<Production> 进尺= new ArrayList<>();
        List<Production> 洗煤= new ArrayList<>();
        List<Production> 外运= new ArrayList<>();


        String yue1= DateUtils.returnDateRange(statsDate);
        String lastYear= DateUtils.returnDateRange(DateUtils.minusOneYear(statsDate));
        Date lastDate = DateUtils.minusOneYear(statsDate);
        Date lastYearDate = DateUtils.getYearLastDay(statsDate);

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司



        List<SubMinePlanPO>  生产月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"生产");
        List<SubMinePlanPO>  开拓月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"开拓");
        List<SubMinePlanPO>  进尺月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"进尺");
        List<SubMinePlanPO>  生产年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"生产");
        List<SubMinePlanPO>  开拓年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"开拓");
        List<SubMinePlanPO>  进尺年计划=  subMinePlanMapper.selectByPlanYear(statsDate,"进尺");


        List<SubMinePlanPO>  生产全年计划=  subMinePlanMapper.selectByPlanYear(lastYearDate,"生产");
        List<SubMinePlanPO>  开拓全年计划=  subMinePlanMapper.selectByPlanYear(lastYearDate,"开拓");
        List<SubMinePlanPO>  进尺全年计划=  subMinePlanMapper.selectByPlanYear(lastYearDate,"进尺");

        List<MineData> 月完成 = subMineDevelopmentDataMapper.selectMonthDate(yue1);
        List<MineData> 年完成=subMineDevelopmentDataMapper.selectYearDate(yue1);
        List<MineData> 去年月完成 = subMineDevelopmentDataMapper.selectMonthDate(lastYear);
        List<MineData> 去年年完成=subMineDevelopmentDataMapper.selectYearDate(lastYear);

        int 总公司生产本月计划 = 生产月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 总公司生产本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//全公司月完成
        int 总公司生产去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司月完成
        int 总公司生产本月累计计划 = 生产年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 总公司生产本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//全公司1-10月完成
        int 总公司生产去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司1-10月完成
        int 总公司生产全年月计划 = 生产全年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划


        for (MiningAreaCategory mining:miningAreaCategories) {
            int 生产本月计划 = 生产月计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
            int 生产本月完成 = 月完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getProductionData())).sum();//全公司月完成
            int 生产去年本月完成 = 去年月完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司月完成
            int 生产本月累计计划 = 生产年计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
            int 生产本月累计完成 = 年完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getProductionData())).sum();//全公司1-10月完成
            int 生产去年本月累计完成 = 去年年完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getProductionData())).sum();//去年全公司1-10月完成
            int 生产全年月计划 = 生产全年计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划
            Production p=new Production();
            p.setUnitName(mining.getAreaName());
            p.setUnitType("原煤生产");
            p.setMonthPlan(生产本月计划);/** 本月计划 */
            p.setMonthComplete(生产本月完成);/** 本月完成 */
            p.setLastYearmonthComplete(生产去年本月完成); /** 上年同期月完成 */
            p.setCumulativeMonthPlan(生产本月累计计划);/** 累计月计划 */
            p.setCumulativeMonthComplete(生产本月累计完成);  /** 累计月完成 */
            p.setCumulativeLastYearmonthComplete(生产去年本月累计完成);    /** 累计上年同期月完成 */
            p.setYearPlan(生产全年月计划);    /** 累计上年同期月完成 */
            生产.add(p);

            int 开拓本月计划 = 开拓月计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
            int 开拓本月完成 = 月完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司月完成
            int 开拓去年本月完成 = 去年月完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司月完成
            int 开拓本月累计计划 = 开拓年计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
            int 开拓本月累计完成 = 年完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司1-10月完成
            int 开拓去年本月累计完成 = 去年年完成.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司1-10月完成
            int 开拓全年月计划 = 开拓全年计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划
            p=new Production();
            p.setUnitName(mining.getAreaName());
            p.setUnitType("开拓生产");
            p.setYearPlan(开拓全年月计划);    /** 全年计划 */
            p.setMonthPlan(开拓本月计划);/** 本月计划 */
            p.setCumulativeMonthPlan(开拓本月累计计划);/** 累计月计划 */
            p.setMonthComplete(开拓本月完成);/** 本月完成 */
            p.setCumulativeMonthComplete(开拓本月累计完成);  /** 累计月完成 */
            p.setLastYearmonthComplete(开拓去年本月完成); /** 上年同期月完成 */
            p.setCumulativeLastYearmonthComplete(开拓去年本月累计完成);    /** 累计上年同期月完成 */
            开拓.add(p);



            int 进尺本月计划 = 进尺月计划.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
            int 进尺本月完成 = 月完成.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getFootageData())).sum();//全公司月完成
            int 进尺去年本月完成 = 去年月完成.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司月完成
            int 进尺本月累计计划 = 进尺年计划.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
            int 进尺本月累计完成 = 年完成.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getFootageData())).sum();//全公司1-10月完成
            int 进尺去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司1-10月完成
            int 进尺全年月计划 = 进尺全年计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getUnitName())) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划

            p=new Production();
            p.setUnitName(mining.getAreaName());
            p.setUnitType("进尺");
            p.setMonthPlan(进尺本月计划);/** 本月计划 */
            p.setMonthComplete(进尺本月完成);/** 本月完成 */
            p.setLastYearmonthComplete(进尺去年本月完成); /** 上年同期月完成 */
            p.setCumulativeMonthPlan(进尺本月累计计划);/** 累计月计划 */
            p.setCumulativeMonthComplete(进尺本月累计完成);  /** 累计月完成 */
            p.setCumulativeLastYearmonthComplete(进尺去年本月累计完成);    /** 累计上年同期月完成 */
            p.setYearPlan(进尺全年月计划);
            进尺.add(p);
        }
        Production p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("原煤生产");
        p.setMonthPlan(总公司生产本月计划);/** 本月计划 */
        p.setMonthComplete(总公司生产本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(总公司生产去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(总公司生产本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(总公司生产本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(总公司生产去年本月累计完成);    /** 累计上年同期月完成 */
        p.setYearPlan(总公司生产全年月计划);
        生产.add(0,p);



        int 总公司开拓本月计划 = 开拓月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 总公司开拓本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司月完成
        int 总公司开拓去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司月完成
        int 总公司开拓本月累计计划 = 开拓年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 总公司开拓本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//全公司1-10月完成
        int 总公司开拓去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getExpandData())).sum();//去年全公司1-10月完成
        int 总公司开拓全年月计划 = 生产全年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划

        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("开拓生产");
        p.setMonthPlan(总公司开拓本月计划);/** 本月计划 */
        p.setMonthComplete(总公司开拓本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(总公司开拓去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(总公司开拓本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(总公司开拓本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(总公司开拓去年本月累计完成);    /** 累计上年同期月完成 */
        p.setYearPlan(总公司开拓全年月计划);    /** 累计上年同期月完成 */
        开拓.add(0,p);


        int 总公司进尺本月计划 = 进尺月计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司月计划
        int 总公司进尺本月完成 = 月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//全公司月完成
        int 总公司进尺去年本月完成 = 去年月完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司月完成
        int 总公司进尺本月累计计划 = 进尺年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司1-10月计划
        int 总公司进尺本月累计完成 = 年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//全公司1-10月完成
        int 总公司进尺去年本月累计完成 = 去年年完成.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getFootageData())).sum();//去年全公司1-10月完成
        int 总公司进尺全年月计划 = 生产全年计划.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum();//全公司全年月计划
        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("进尺");
        p.setMonthPlan(总公司进尺本月计划);/** 本月计划 */
        p.setMonthComplete(总公司进尺本月完成);/** 本月完成 */
        p.setLastYearmonthComplete(总公司进尺去年本月完成); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(总公司进尺本月累计计划);/** 累计月计划 */
        p.setCumulativeMonthComplete(总公司进尺本月累计完成);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(总公司进尺去年本月累计完成);    /** 累计上年同期月完成 */
        p.setYearPlan(总公司进尺全年月计划);
        进尺.add(0,p);

        //洗精煤
        List<SubWashCoalPlanPO> 洗煤日月计划 = subWashCoalPlanMapper.selectByPlanDayUnitCode(statsDate);
        List<SubWashCoalPlanPO> 洗煤年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(statsDate);
        List<CoalWashingProductionPO> 月完成洗煤=coalWashingProductionMapper.selecMonthList(statsDate);
        List<CoalWashingProductionPO>  年完成洗煤=coalWashingProductionMapper.selecYearList(statsDate);
        List<SubWashCoalPlanPO> 去年洗煤日月计划 = subWashCoalPlanMapper.selectByPlanDayUnitCode(lastDate);
        List<SubWashCoalPlanPO> 去年洗煤年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(lastDate);

        List<SubWashCoalPlanPO> 洗煤全年计划 = subWashCoalPlanMapper.selectByPlanYearUnitCode(lastYearDate);
        FactoryArchive factory = new FactoryArchive();
        factory.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factory);
        List<xiMeiRiBaoBaoBiao> 洗煤厂= new ArrayList<>();
        for (FactoryArchive fact:factoryArchives) {
            int 洗精煤月计划 = 洗煤日月计划.stream().filter(po -> po != null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
            int 洗精煤年计划=洗煤年计划.stream().filter(po ->po !=null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();
            int 本月精煤产量=月完成洗煤.stream().filter(po ->po !=null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
            int 本年精煤产量=年完成洗煤.stream().filter(po ->po !=null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
            int 去年洗精煤月计划 = 去年洗煤日月计划.stream().filter(po -> po != null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
            int 去年洗精煤年计划=去年洗煤年计划.stream().filter(po ->po !=null).filter(item -> fact.getFactoryName().equals(item.getUnitName())).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();
            p=new Production();
            p.setUnitName(fact.getFactoryName());
            p.setUnitType("洗精煤");
            p.setMonthPlan(洗精煤月计划);/** 本月计划 */
            p.setMonthComplete(本月精煤产量);/** 本月完成 */
            p.setLastYearmonthComplete(洗精煤年计划); /** 上年同期月完成 */
            p.setCumulativeMonthPlan(本年精煤产量);/** 累计月计划 */
            p.setCumulativeMonthComplete(去年洗精煤月计划);  /** 累计月完成 */
            p.setCumulativeLastYearmonthComplete(去年洗精煤年计划);    /** 累计上年同期月完成 */
            洗煤.add(p);
        }


        int 总公司洗精煤月计划 = 洗煤日月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
        int 总公司洗精煤年计划=洗煤年计划.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();
        int 总公司本月精煤产量=月完成洗煤.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
        int 总公司本年精煤产量=年完成洗煤.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoal())).sum();//本月精煤产量
        int 总公司去年洗精煤月计划 = 去年洗煤日月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getCleanCoalMonthCarPlan())).sum();//去年全公司1-10月完成
        int 总公司去年洗精煤年计划=去年洗煤年计划.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();
        int 总公司全年洗精煤年计划=洗煤全年计划.stream().filter(po ->po !=null).mapToInt(po ->toInt(po.getCleanCoalMonthCarPlan())).sum();

        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("洗精煤");
        p.setMonthPlan(总公司洗精煤月计划);/** 本月计划 */
        p.setMonthComplete(总公司本月精煤产量);/** 本月完成 */
        p.setLastYearmonthComplete(总公司洗精煤年计划); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(总公司本年精煤产量);/** 累计月计划 */
        p.setCumulativeMonthComplete(总公司去年洗精煤月计划);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(总公司去年洗精煤年计划);    /** 累计上年同期月完成 */
        p.setYearPlan(总公司全年洗精煤年计划);    /** 累计上年同期月完成 */
        洗煤.add(0,p);

        //外运
        ComprehensiveProductionStats dis=new ComprehensiveProductionStats();
        dis.setStatsDate(statsDate);
        ComprehensiveProductionStats 本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        ComprehensiveProductionStats 去年本月 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsMonthList(dis);
        ComprehensiveProductionStats 去年本年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        dis.setStatsDate(lastYearDate);
        ComprehensiveProductionStats 全年 = comprehensiveProductionStatsMapper.selectComprehensiveProductionStatsYearList(dis);
        MinePlanOutwardTransport 本月计划 = minePlanOutwardTransportMapper.selectMinePlanOutwardTransportByMonth(statsDate);
        MinePlanOutwardTransport 本年计划 = minePlanOutwardTransportMapper.selectMinePlanOutwardTransportByYear(statsDate);

        p=new Production();
        p.setUnitName("其中精煤");
        p.setUnitType("外运");
        p.setMonthPlan(本月计划 != null &&本月计划.getCleanCoalSalesTons() != null ? 本月计划.getCleanCoalSalesTons() : 0);/** 本月计划 */
        p.setMonthComplete(本月 != null && 本月.getCleanCoalSalesTonnage() != null ? toInt(本月.getCleanCoalSalesTonnage()) : 0);/** 本月完成 */
        p.setLastYearmonthComplete(去年本月 != null && 去年本月.getCleanCoalSalesTonnage() != null ? toInt(去年本月.getCleanCoalSalesTonnage()) : 0); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(本年计划 != null ? 本年计划.getCleanCoalSalesTons() : 0);/** 累计月计划 */
        p.setCumulativeMonthComplete(本年 != null && 本年.getCleanCoalSalesTonnage() != null ? toInt(本年.getCleanCoalSalesTonnage()) : 0);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(去年本年 != null && 去年本年.getCleanCoalSalesTonnage() != null ? toInt(去年本年.getCleanCoalSalesTonnage()) : 0);    /** 累计上年同期月完成 */
        p.setYearPlan(全年 != null && 全年.getCleanCoalSalesTonnage() != null ? toInt(全年.getCleanCoalSalesTonnage()) : 0);    /** 全年计划 */
        外运.add(0,p);
        p=new Production();
        p.setUnitName("全公司");
        p.setUnitType("外运");
        p.setMonthPlan(本月计划 != null ? 本月计划.getTotalCompanySalesTons() : 0);/** 本月计划 */
        p.setMonthComplete(本月 != null && 本月.getTotalSalesTonnage() != null ? toInt(本月.getTotalSalesTonnage()) : 0);/** 本月完成 */
        p.setLastYearmonthComplete(去年本月 != null && 去年本月.getTotalSalesTonnage() != null ? toInt(去年本月.getTotalSalesTonnage()) : 0); /** 上年同期月完成 */
        p.setCumulativeMonthPlan(本年计划 != null ? 本年计划.getTotalCompanySalesTons() : 0);/** 累计月计划 */
        p.setCumulativeMonthComplete(本年 != null && 本年.getTotalSalesTonnage() != null ? toInt(本年.getTotalSalesTonnage()) : 0);  /** 累计月完成 */
        p.setCumulativeLastYearmonthComplete(去年本年 != null && 去年本年.getTotalSalesTonnage() != null ? toInt(去年本年.getTotalSalesTonnage()) : 0);    /** 累计上年同期月完成 */
        p.setYearPlan(全年 != null && 全年.getTotalSalesTonnage() != null ? toInt(全年.getTotalSalesTonnage()) : 0);    /** 全年计划 */
        外运.add(0,p);
        targetAll.set开拓(开拓);
        targetAll.set进尺(进尺);
        targetAll.set原煤(生产);
        targetAll.set洗煤(洗煤);
        targetAll.set外运(外运);
        return targetAll;
    }
    public List<StatisticsFootage> actualCompletionStatisticsFootage(Date statsDate)    {
        List<StatisticsFootage> list=new ArrayList<>();
        List<SubMinePlanPO>  开拓月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"进尺");
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        List<MineData> 累计月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(statsDate));//月累计完成
        List<MineData> 日完成 = subMineDevelopmentDataMapper.selectDayDate(DateUtils.returnDateRange(statsDate));//日累计完成
        String yue= DateUtils.returnDateDay(DateUtils.getFirstDayOfMonth(statsDate));
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("进尺");
        minday.setPlanMonth(yue);
        minday.setStatus(0);
        minday.setPlanDay(day);
        List<MinePlanDay> 日计划 = minePlanDayMapper.selectMinePlanDayList(minday);

        for (MiningAreaCategory  mining:miningAreaCategories) {
            StatisticsFootage 公司=new StatisticsFootage();
            公司.setUnitName(mining.getAreaName());
            SubMinePlanPO 开拓月计划内容 = 开拓月计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            int jc = 日完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> po.getFootageData()).sum();
            MineData 累计日完成 = 累计月完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new MineData());

            int jh = 日计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayPlan())).sum();
            int mb = 日计划.stream().filter(po -> po != null).filter(item -> mining.getAreaName().equals(item.getAreaName())).mapToInt(po -> toInt(po.getDayTarget())).sum();

            CoalMineWorkingDay 累计日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByMonth(mining.getAreaName(), yue, day);
            公司.setMonthPlan(开拓月计划内容 != null && 开拓月计划内容.getMonthPlan() != null ? toInt(开拓月计划内容.getMonthPlan()) : 0);
            公司.setMonthTarget(开拓月计划内容 != null && 开拓月计划内容.getMonthTarget() != null ? toInt(开拓月计划内容.getMonthTarget()) : 0);
            公司.setDayPlan(jh);
            公司.setDayTarget(mb);
            公司.setDayComplete(jc);
            公司.setDayCumulativePlan(累计日计划 != null && 累计日计划.getFootagePlan() != null ? toInt(累计日计划.getFootagePlan()) : 0);
            公司.setDayCumulativeTarget(累计日计划 != null && 累计日计划.getFootageObjective() != null ? toInt(累计日计划.getFootageObjective()) : 0);
            公司.setDayCumulativeComplete(累计日完成 != null && 累计日完成.getFootageData() != null ? toInt(累计日完成.getFootageData()) : 0);
            list.add(公司);
        }
        StatisticsFootage 合计=new StatisticsFootage();
        合计.setUnitName("合计");
        合计.setMonthPlan(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum());
        合计.setMonthTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthTarget())).sum());
        合计.setDayPlan(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayPlan())).sum());
        合计.setDayTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayTarget())).sum());
        合计.setDayComplete(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayComplete())).sum());
        合计.setDayCumulativePlan(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayCumulativePlan())).sum());
        合计.setDayCumulativeTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayCumulativeTarget())).sum());
        合计.setDayCumulativeComplete(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayCumulativeComplete())).sum());
        list.add(合计);
        return list;
    }

    public List<StatisticalTableRawCoal> statisticalTableRawCoalProduction(Date statsDate)    {
        String yue= DateUtils.returnDateDay(DateUtils.getFirstDayOfMonth(statsDate));
        String ri = DateUtils.returnDateRange(statsDate);
        int day = DateUtils.getDayFromString(ri, "yyyy-MM-dd");

        List<StatisticalTableRawCoal> list=new ArrayList<>();
        List<SubMinePlanPO>  原煤月计划=  subMinePlanMapper.selectByPlanMonth(statsDate,"生产");
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司
        List<MineData> 累计月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(statsDate));//月累计完成
        List<MineData> 日完成 = subMineDevelopmentDataMapper.selectDayDate(DateUtils.returnDateRange(statsDate));//日累计完成
        Date firstDayOfMonth = DateUtils.getFirstDayOfMonth(statsDate);
        InitialInventoryOfEachMine  initialInventoryOfEachMine= new InitialInventoryOfEachMine();
        initialInventoryOfEachMine.setRecordDate(firstDayOfMonth);
        InitialInventoryOfEachMine initialInventoryOfEachMines = initialInventoryOfEachMineMapper.selectInitialInventoryOfEachMine(initialInventoryOfEachMine);
        List<SubInitialInventoryOfEachMine> 期初库存=new ArrayList<>();
        if(initialInventoryOfEachMines!=null){
            if(initialInventoryOfEachMines.getId()!=null){
                SubInitialInventoryOfEachMine sub=new SubInitialInventoryOfEachMine();
                sub.setInitialInventoryId(initialInventoryOfEachMines.getId());
                期初库存 = subInitial.selectSubInitialInventoryOfEachMineList(sub);
            }
        }
        for (MiningAreaCategory  mining:miningAreaCategories) {
            StatisticalTableRawCoal 公司=new StatisticalTableRawCoal();
            公司.setUnitName(mining.getAreaName());
            DestinationOfRawCoal dest=new DestinationOfRawCoal();
            dest.setRecordDate(statsDate);
            dest.setUnitName(mining.getAreaName());
            List<DestinationOfRawCoal> 日去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(dest);
            List<DestinationOfRawCoal> 月去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalByMonth(dest);
            SubMinePlanPO 原煤月计划内容 = 原煤月计划.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubMinePlanPO());
            int 当日日完成  = 日完成.stream().filter(item ->  mining.getAreaName().equals(item.getUnitName())).mapToInt(po -> po.getProductionData()).sum();
            MineData 累计日完成 = 累计月完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new MineData());
            DestinationOfRawCoal 日销售 = 日去向.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new DestinationOfRawCoal());
            DestinationOfRawCoal 月销售 = 月去向.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new DestinationOfRawCoal());
            SubInitialInventoryOfEachMine 矿起初库存 = 期初库存.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubInitialInventoryOfEachMine());
            CoalMineWorkingDay 日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByDay(mining.getAreaName(), yue, day);
            CoalMineWorkingDay 累计日计划 = coalMineWorkingDayMapper.selectCoalMineWorkingDayByMonth(mining.getAreaName(), yue, day);
            公司.setMonthPlan(原煤月计划内容 != null && 原煤月计划内容.getMonthPlan() != null ? toInt(原煤月计划内容.getMonthPlan()) : 0);
            公司.setMonthTarget(原煤月计划内容 != null && 原煤月计划内容.getMonthTarget() != null ? toInt(原煤月计划内容.getMonthTarget()) : 0);
            公司.setDayPlan(日计划 != null && 日计划.getProductionPlan() != null ? toInt(日计划.getProductionPlan()) : 0);
            公司.setDayTarget(日计划 != null && 日计划.getProductionObjective() != null ? toInt(日计划.getProductionObjective()) : 0);
            公司.setDayComplete(当日日完成);
            公司.setMailySales(日销售 != null && 日销售.getSalesVolume() != null ? toInt(日销售.getSalesVolume()) : 0);
            公司.setMonthlyPlanTotal(累计日计划 != null && 累计日计划.getProductionPlan() != null ? toInt(累计日计划.getProductionPlan()) : 0);
            公司.setMonthlyCompleteTarget(累计日计划 != null && 累计日计划.getProductionObjective() != null ? toInt(累计日计划.getProductionObjective()) : 0);
            公司.setMonthlyCompleteTotal(累计日完成 != null && 累计日完成.getProductionData() != null ? toInt(累计日完成.getProductionData()) : 0);
            公司.setMonthlyMailySales(月销售 != null && 月销售.getSalesVolume() != null ? toInt(月销售.getSalesVolume()) : 0);
            公司.setGroundStorage(矿起初库存 != null && 矿起初库存.getInitialInventoryOfThisMonth() != null ? toInt(矿起初库存.getInitialInventoryOfThisMonth()) : 0);
            list.add(公司);
        }
        StatisticalTableRawCoal 合计=new StatisticalTableRawCoal();
        合计.setUnitName("合计");
        合计.setMonthPlan(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthPlan())).sum());
        合计.setMonthTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthTarget())).sum());
        合计.setDayPlan(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayPlan())).sum());
        合计.setDayTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayTarget())).sum());
        合计.setDayComplete(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getDayComplete())).sum());
        合计.setMailySales(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMailySales())).sum());
        合计.setMonthlyPlanTotal(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthlyPlanTotal())).sum());
        合计.setMonthlyCompleteTarget(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthlyCompleteTarget())).sum());
        合计.setMonthlyCompleteTotal(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthlyCompleteTotal())).sum());
        合计.setMonthlyMailySales(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getMonthlyMailySales())).sum());
        合计.setGroundStorage(list.stream().filter(po -> po != null) .mapToInt(po -> toInt(po.getGroundStorage())).sum());
        // 将合计移到第一位
        list.add(0, 合计);
        return list;
    }

    public List<CompletionRawCoalGeneration> StatisticalTableCompletionRawCoalGeneration(Date statsDate)    {
        List<CompletionRawCoalGeneration> list = new ArrayList<>();
        Date firstDayOfMonth = DateUtils.getLastDayOfMonth(statsDate);
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);
        List<SubMinePlanPO>  原煤月计划=  subMinePlanMapper.selectByPlanMonth(firstDayOfMonth,"生产");
        List<MineData> 月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(firstDayOfMonth));
        CompletionRawCoalGeneration 公司 = new CompletionRawCoalGeneration();
        公司.setUnitName("公司");
        公司.setMonthPlan(原煤月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getMonthPlan())).sum());//计划
        公司.setMonthTarget(原煤月计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getMonthTarget())).sum());//目标
        公司.setProductionData(月完成.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getProductionData())).sum());//实际完成
        公司.setComparedToPlan(公司.getProductionData() - 公司.getMonthPlan());//与计划比
        公司.setComparedToPlanPercentage(String.format("%.1f", (double)公司.getProductionData() / 公司.getMonthPlan() * 100) + "%");//与计划比%
        公司.setComparedToPlanRank(" ");//与计划比名次
        公司.setComparedToTarget(公司.getProductionData() - 公司.getMonthTarget());//与目标比
        公司.setComparedToTargetPlan(String.format("%.1f", (double)公司.getProductionData() / 公司.getMonthTarget() * 100) + "%");//与目标比%
        公司.setComparedToTargetRank(" ");//与目标比名次
        list.add( 公司);
        for (MiningAreaCategory  mining:miningAreaCategories) {
            CompletionRawCoalGeneration 渠道 = new CompletionRawCoalGeneration();
            渠道.setUnitName(mining.getAreaName());
            渠道.setMonthPlan(原煤月计划.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getMonthPlan())).sum());
            渠道.setMonthTarget(原煤月计划.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getMonthTarget())).sum());
            渠道.setProductionData(月完成.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getProductionData())).sum());
            渠道.setComparedToPlan(渠道.getProductionData() - 渠道.getMonthPlan());
            if(渠道.getMonthPlan() !=null  && 渠道.getMonthPlan() > 0){
                渠道.setComparedToPlanPercentage(String.format("%.1f", (double) 渠道.getProductionData() / 渠道.getMonthPlan() * 100) + "%");
            }else {
                渠道.setComparedToPlanPercentage(null);
            }
            渠道.setComparedToPlanRank(null);
            渠道.setComparedToTarget(渠道.getProductionData() - 渠道.getMonthTarget());
            if(渠道.getMonthTarget() !=null  && 渠道.getMonthTarget() > 0){
                渠道.setComparedToTargetPlan(String.format("%.1f", (double) 渠道.getProductionData() / 渠道.getMonthTarget() * 100) + "%");
            }else {
                渠道.setComparedToTargetPlan(null);
            }
            渠道.setComparedToTargetRank(null);
            list.add(渠道);
        }

        // 分离公司和其他单位
        CompletionRawCoalGeneration company = list.remove(0);
        // 按 comparedToPlanPercentage 排名，但保持list顺序不变
        List<CompletionRawCoalGeneration> sortedList = new ArrayList<>(list);
        sortedList.sort((a, b) -> {
            if (a.getComparedToPlanPercentage() == null && b.getComparedToPlanPercentage() == null) return 0;
            if (a.getComparedToPlanPercentage() == null) return 1;
            if (b.getComparedToPlanPercentage() == null) return -1;
            double aPlanPercent = Double.parseDouble(a.getComparedToPlanPercentage().replace("%", ""));
            double bPlanPercent = Double.parseDouble(b.getComparedToPlanPercentage().replace("%", ""));
            return Double.compare(bPlanPercent, aPlanPercent); // 降序排列
        });

        // 为排序后的列表分配排名
        int planRank = 1;
        for (int i = 0; i < sortedList.size(); i++) {
            if (i > 0 && !Objects.equals(sortedList.get(i).getComparedToPlanPercentage(), sortedList.get(i-1).getComparedToPlanPercentage())) {
                planRank = i + 1;
            }
            if(sortedList.get(i).getProductionData() !=null) {
                sortedList.get(i).setComparedToPlanRank(sortedList.get(i).getComparedToPlanPercentage() != null ? planRank + "" : " ");
            }else {
                sortedList.get(i).setComparedToPlanRank(" ");
            }
        }

        // 根据排序后的排名更新原始列表中的排名
        for (CompletionRawCoalGeneration item : list) {
            CompletionRawCoalGeneration rankedItem = sortedList.stream()
                .filter(sortedItem -> sortedItem.getUnitName().equals(item.getUnitName()))
                .findFirst().orElse(null);
            if (rankedItem != null) {
                item.setComparedToPlanRank(rankedItem.getComparedToPlanRank());
            }
        }
        // 按 comparedToTargetPlan 排名，但保持list顺序不变
        List<CompletionRawCoalGeneration> sortedListForTarget = new ArrayList<>(list);
        sortedListForTarget.sort((a, b) -> {
            if (a.getComparedToTargetPlan() == null && b.getComparedToTargetPlan() == null) return 0;
            if (a.getComparedToTargetPlan() == null) return 1;
            if (b.getComparedToTargetPlan() == null) return -1;
            double aTargetPercent = Double.parseDouble(a.getComparedToTargetPlan().replace("%", ""));
            double bTargetPercent = Double.parseDouble(b.getComparedToTargetPlan().replace("%", ""));
            return Double.compare(bTargetPercent, aTargetPercent); // 降序排列
        });

        // 为排序后的列表分配排名
        int targetRank = 1;
        for (int i = 0; i < sortedListForTarget.size(); i++) {
            if (i > 0 && !Objects.equals(sortedListForTarget.get(i).getComparedToTargetPlan(), sortedListForTarget.get(i-1).getComparedToTargetPlan())) {
                targetRank = i + 1;
            }
            if(sortedList.get(i).getProductionData() !=null){
                sortedListForTarget.get(i).setComparedToTargetRank(sortedListForTarget.get(i).getComparedToTargetPlan() != null ? targetRank+"" : " ");
            }else {
                sortedListForTarget.get(i).setComparedToTargetRank(" ");
            }
        }

        // 根据排序后的排名更新原始列表中的排名
        for (CompletionRawCoalGeneration item : list) {
            CompletionRawCoalGeneration rankedItem = sortedListForTarget.stream()
                .filter(sortedItem -> sortedItem.getUnitName().equals(item.getUnitName()))
                .findFirst().orElse(null);
            if (rankedItem != null) {
                item.setComparedToTargetRank(rankedItem.getComparedToTargetRank());
            }
        }

        // 将公司重新添加到列表开头
        list.add(0, company);
        return list;
    }


    public List<CompletionRawCoalGeneration> YearStatisticalTableCompletionRawCoalGeneration(Date statsDate)    {
        List<CompletionRawCoalGeneration> list = new ArrayList<>();
       Date firstDayOfMonth = DateUtils.getLastDayOfMonth(statsDate);

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);
        List<SubMinePlanPO>  生产年计划=  subMinePlanMapper.selectByPlanYear(firstDayOfMonth,"生产");
        List<MineData> 年完成=subMineDevelopmentDataMapper.selectYearDate(DateUtils.returnDateRange(firstDayOfMonth));


        CompletionRawCoalGeneration 公司 = new CompletionRawCoalGeneration();
        公司.setUnitName("公司");
        公司.setMonthPlan(生产年计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getMonthPlan())).sum());//计划
        公司.setMonthTarget(生产年计划.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getMonthTarget())).sum());//目标
        公司.setProductionData(年完成.stream().filter(po -> po != null).mapToInt(po -> toInt(po.getProductionData())).sum());//实际完成
        公司.setComparedToPlan(公司.getProductionData() - 公司.getMonthPlan());//与计划比
        公司.setComparedToPlanPercentage(String.format("%.1f", (double)公司.getProductionData() / 公司.getMonthPlan() * 100) + "%");//与计划比%
        公司.setComparedToPlanRank(" ");//与计划比名次
        公司.setComparedToTarget(公司.getProductionData() - 公司.getMonthTarget());//与目标比
        公司.setComparedToTargetPlan(String.format("%.1f", (double)公司.getProductionData() / 公司.getMonthTarget() * 100) + "%");//与目标比%
        公司.setComparedToTargetRank(" ");//与目标比名次
        list.add( 公司);
        for (MiningAreaCategory  mining:miningAreaCategories) {
            CompletionRawCoalGeneration 渠道 = new CompletionRawCoalGeneration();
            渠道.setUnitName(mining.getAreaName());
            渠道.setMonthPlan(生产年计划.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getMonthPlan())).sum());
            渠道.setMonthTarget(生产年计划.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getMonthTarget())).sum());
            渠道.setProductionData(年完成.stream().filter(po -> po != null && po.getUnitName().equals(mining.getAreaName())).mapToInt(po -> toInt(po.getProductionData())).sum());
            渠道.setComparedToPlan(渠道.getProductionData() - 渠道.getMonthPlan());
            if(渠道.getMonthPlan() !=null && 渠道.getMonthPlan() > 0){
                渠道.setComparedToPlanPercentage(String.format("%.1f", (double) 渠道.getProductionData() / 渠道.getMonthPlan() * 100) + "%");
            }else {
                渠道.setComparedToPlanPercentage(null);
            }
            渠道.setComparedToPlanRank(null);
            渠道.setComparedToTarget(渠道.getProductionData() - 渠道.getMonthTarget());
            if(渠道.getMonthTarget() !=null && 渠道.getMonthTarget() > 0){
                渠道.setComparedToTargetPlan(String.format("%.1f", (double) 渠道.getProductionData() / 渠道.getMonthTarget() * 100) + "%");
            }else {
                渠道.setComparedToTargetPlan(null);
            }
            渠道.setComparedToTargetRank(null);
            list.add(渠道);
        }

        // 分离公司和其他单位
        CompletionRawCoalGeneration company = list.remove(0); // 公司始终在第一位
        // 按 comparedToPlanPercentage 排名，但保持list顺序不变
        List<CompletionRawCoalGeneration> sortedList = new ArrayList<>(list);
        sortedList.sort((a, b) -> {
            if (a.getComparedToPlanPercentage() == null && b.getComparedToPlanPercentage() == null) return 0;
            if (a.getComparedToPlanPercentage() == null) return 1;
            if (b.getComparedToPlanPercentage() == null) return -1;
            double aPlanPercent = Double.parseDouble(a.getComparedToPlanPercentage().replace("%", ""));
            double bPlanPercent = Double.parseDouble(b.getComparedToPlanPercentage().replace("%", ""));
            return Double.compare(bPlanPercent, aPlanPercent); // 降序排列
        });

        // 为排序后的列表分配排名
        int planRank = 1;
        for (int i = 0; i < sortedList.size(); i++) {
            if (i > 0 && !Objects.equals(sortedList.get(i).getComparedToPlanPercentage(), sortedList.get(i-1).getComparedToPlanPercentage())) {
                planRank = i + 1;
            }
            if(sortedList.get(i).getProductionData() !=null){
                sortedList.get(i).setComparedToPlanRank(sortedList.get(i).getComparedToPlanPercentage() != null ? planRank+"" : " ");
            }else {
                sortedList.get(i).setComparedToPlanRank(" ");
            }
        }

        // 根据排序后的排名更新原始列表中的排名
        for (CompletionRawCoalGeneration item : list) {
            CompletionRawCoalGeneration rankedItem = sortedList.stream()
                    .filter(sortedItem -> sortedItem.getUnitName().equals(item.getUnitName()))
                    .findFirst()
                    .orElse(null);
            if (rankedItem != null) {
                item.setComparedToPlanRank(rankedItem.getComparedToPlanRank());
            }
        }
        // 按 comparedToTargetPlan 排名，但保持list顺序不变
        List<CompletionRawCoalGeneration> sortedListForTarget = new ArrayList<>(list);
        sortedListForTarget.sort((a, b) -> {
            if (a.getComparedToTargetPlan() == null && b.getComparedToTargetPlan() == null) return 0;
            if (a.getComparedToTargetPlan() == null) return 1;
            if (b.getComparedToTargetPlan() == null) return -1;
            double aTargetPercent = Double.parseDouble(a.getComparedToTargetPlan().replace("%", ""));
            double bTargetPercent = Double.parseDouble(b.getComparedToTargetPlan().replace("%", ""));
            return Double.compare(bTargetPercent, aTargetPercent); // 降序排列
        });

        // 为排序后的列表分配排名
        int targetRank = 1;
        for (int i = 0; i < sortedListForTarget.size(); i++) {
            if (i > 0 && !Objects.equals(sortedListForTarget.get(i).getComparedToTargetPlan(), sortedListForTarget.get(i-1).getComparedToTargetPlan())) {
                targetRank = i + 1;
            }
            if(sortedList.get(i).getProductionData() !=null){
                sortedListForTarget.get(i).setComparedToTargetRank(sortedListForTarget.get(i).getComparedToTargetPlan() != null ? targetRank+"" : " ");
            }else {
                sortedListForTarget.get(i).setComparedToTargetRank(null);
            }
        }

        // 根据排序后的排名更新原始列表中的排名
        for (CompletionRawCoalGeneration item : list) {
            CompletionRawCoalGeneration rankedItem = sortedListForTarget.stream()
                    .filter(sortedItem -> sortedItem.getUnitName().equals(item.getUnitName()))
                    .findFirst()
                    .orElse(null);
            if (rankedItem != null) {
                item.setComparedToTargetRank(rankedItem.getComparedToTargetRank());
            }
        }
        // 将公司重新添加到列表开头
        list.add(0, company);
        return list;
    }
    public List<SafetyInfluencingFactors> CompanySafetyFactors(Date statsDate)    {
        List<SafetyInfluencingFactors> list = new ArrayList<>();
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);
        SafetyInfluencingFactors safety=new SafetyInfluencingFactors();
        safety.setRecordDate(statsDate);
        List<SafetyInfluencingFactors> safetyInfluencingFactors = safetyInfluencingFactorsMapper.selectSafetyInfluencingFactorsList(safety);
        for (MiningAreaCategory  mining:miningAreaCategories) {
            SafetyInfluencingFactors matchingSafetyFactor = safetyInfluencingFactors.stream()
                .filter(s -> s.getUnitCode().equals(mining.getAreaCode()) && s.getUnitName().equals(mining.getAreaName()) )
                .findFirst()
                .orElse(null);
            if (matchingSafetyFactor == null) {
                matchingSafetyFactor = new SafetyInfluencingFactors();
                matchingSafetyFactor.setUnitName(mining.getAreaName());
                matchingSafetyFactor.setUnitCode(mining.getAreaCode());
                matchingSafetyFactor.setRecordDate(statsDate);
            }
            list.add(matchingSafetyFactor);
        }
        return list;
    }






    private static int toInt(Integer value) {
        // Optional 安全处理 null，默认返回 0
        return Optional.ofNullable(value).orElse(0);
    }
    private static int toInt(Long value) {
        // Optional 安全处理 null，默认返回 0
        return Optional.ofNullable(value).map(Long::intValue).orElse(0);
    }
    private static int toInt(BigDecimal value) {
        // 1. null 转为 BigDecimal.ZERO，非 null 保留原值
        BigDecimal result = Optional.ofNullable(value).orElse(BigDecimal.ZERO);
        // 2. 直接舍去小数（RoundingMode.DOWN），转为 int（超出范围抛异常）
        return result.setScale(0, BigDecimal.ROUND_DOWN).intValueExact();
    }

}
