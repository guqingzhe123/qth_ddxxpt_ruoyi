package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.domain.work.*;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMineDevelopmentDataMapper;
import com.ruoyi.system.mapper.DestinationOfRawCoalMapper;
import com.ruoyi.system.mapper.InitialInventoryOfEachMineMapper;
import com.ruoyi.system.mapper.SubInitialInventoryOfEachMineMapper;
import com.ruoyi.system.service.BaoBiao.ICoalProductInventoryService;
import com.ruoyi.system.service.BaoBiao.IFactoryArchiveService;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.work.IWorkCoalStockSalesStatService;
import com.ruoyi.system.service.work.IWorkCoalWashingReportService;
import com.ruoyi.system.service.work.IWorkCompanyCoalStockService;
import com.ruoyi.system.service.work.IWorkThermalPowerCoalSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 煤气公司精煤库存统计Controller
 *
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/WorkCompanyCoalStock")
public class WorkCompanyCoalStockController extends BaseController {
    @Autowired
    private IWorkCompanyCoalStockService workCompanyCoalStockService;//煤气公司精煤库存统计Controller
    @Autowired
    private IWorkCoalStockSalesStatService workCoalStockSalesStatService;//煤炭库存销售统计Controller
    @Autowired
    private IWorkThermalPowerCoalSalesService workThermalPowerCoalSalesService;//热电厂煤种销售库存统计Controller

    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    @Autowired
    private DestinationOfRawCoalMapper destinationOfRawCoalMapper;//原煤去向录入表
    @Autowired
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成子表

    @Autowired
    private InitialInventoryOfEachMineMapper initialInventoryOfEachMineMapper;//各矿期初库存录入表
    @Autowired
    private SubInitialInventoryOfEachMineMapper subInitial;//各矿期初库存录入表 录入子表

    @Resource
    private IFactoryArchiveService factoryArchiveService;//洗煤厂列表


    @Autowired
    private IWorkCoalWashingReportService workCoalWashingReportService;//洗煤生产录入
    @Resource
    private ICoalProductInventoryService  coalProductInventoryService ;//洗煤产品录入
    @Resource
    private IMineInfoService mineInfoService;//退回状态

    @GetMapping("/list")  //厂端获取
    public TableDataInfo<BaseEntity> list(WorkCoalStockSalesStat workStock) {
        List<WorkCoalStockSalesStat> list = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workStock);
        if(workStock.getCoalType()==null){
            List<WorkCoalStockSalesStat> AllList =new ArrayList<>();
            FactoryArchive factoryArchive=new FactoryArchive();
            factoryArchive.setFactoryType("所属厂档案");
            factoryArchive.setIsSealed(0);
            List<FactoryArchive> list1 = factoryArchiveService.list(factoryArchive);
            for (FactoryArchive factoryArchive1:list1) {

                if(list.stream().filter(x -> x.getCoalType().equals(factoryArchive1.getFactoryCode())).count() > 0){
                    list.stream().filter(x -> x.getCoalType().equals(factoryArchive1.getFactoryCode())).forEach(AllList::add);
                }else {
                    WorkCoalStockSalesStat workCoalStockSalesStat = new WorkCoalStockSalesStat();
                    workCoalStockSalesStat.setCoalType(factoryArchive1.getFactoryName());
                    AllList.add(workCoalStockSalesStat);
                }
            }

            return getDataTable(AllList);
        }else{ 
            if(list.size()==0){
                WorkCoalStockSalesStat workCoalStockSalesStat1 = new WorkCoalStockSalesStat();

                WorkCoalWashingReport workCoalWashingReport=new WorkCoalWashingReport();
                workCoalWashingReport.setUnitName(workStock.getCoalType());
                workCoalWashingReport.setReportTime(workStock.getRecordDate());
                List<WorkCoalWashingReport> workCoalWashingReports = workCoalWashingReportService.listWorkCoalWashingReport(workCoalWashingReport);//洗煤生产录入

                SubCoalProductInventory subCoalProductInventory=new SubCoalProductInventory();
                subCoalProductInventory.setUnitName(workStock.getCoalType());
                subCoalProductInventory.setRecordDate(workStock.getRecordDate());
                List<SubCoalProductInventory> subCoalProductInventories = coalProductInventoryService.listSubCoalProductInventory(subCoalProductInventory);//洗煤产品录入里的
//                    选煤厂库存煤量统计表  精煤当日生产（洗煤生成录入里的 精煤）
//                    选煤厂库存煤量统计表  沫煤当日生产（洗煤生成录入里的 洗沫）
//                    选煤厂库存煤量统计表  精煤现存（洗煤产品录入里的 精煤现存）
//                    选煤厂库存煤量统计表  沫煤现存（洗煤产品录入里的 沫块煤现存）
                if(workCoalWashingReports.size()>0){
                    workCoalStockSalesStat1.setCleanCoalDailyProduction(workCoalWashingReports.get(0).getCleanCoal());//精煤当日生产
                    workCoalStockSalesStat1.setLeanCoalDailyProduction(workCoalWashingReports.get(0).getWashedLumpCoal());//沫煤当日生产
                }
                if(subCoalProductInventories.size()>0){
                    workCoalStockSalesStat1.setCleanCoalCurrentStock(subCoalProductInventories.get(0).getCleanCoalCurrentStock() != null ? subCoalProductInventories.get(0).getCleanCoalCurrentStock().longValue() : 0L);//精煤现存
                    workCoalStockSalesStat1.setLeanCoalCurrentStock(subCoalProductInventories.get(0).getSlackLumpCurrentStock() != null ? subCoalProductInventories.get(0).getSlackLumpCurrentStock().longValue() : 0L);//沫煤现存
                }
                workStock.setRecordDate(getPreviousDay(workStock.getRecordDate()));
                List<WorkCoalStockSalesStat> list1 = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workStock);
                for (WorkCoalStockSalesStat workCoalStockSalesStat:list1){
                    workCoalStockSalesStat1.setRecordDate(workStock.getRecordDate());
                    workCoalStockSalesStat1.setCoalType(workStock.getCoalType());
                    workCoalStockSalesStat1.setRawCoalPreviousStock(workCoalStockSalesStat.getRawCoalCurrentStock());
                    workCoalStockSalesStat1.setCleanCoalPreviousWarehouse(workCoalStockSalesStat.getCleanCoalCurrentWarehouse());
                    workCoalStockSalesStat1.setLeanCoalPreviousWarehouse(workCoalStockSalesStat.getLeanCoalCurrentWarehouse());
                    workCoalStockSalesStat1.setSlimePreviousWarehouse(workCoalStockSalesStat.getSlimeCurrentWarehouse());
                }
                list.add(workCoalStockSalesStat1);
            }
        }
        return getDataTable(list);
    }
    @PostMapping("/bureauList")  //局端获取
    public AjaxResult bureauList(@RequestBody riBao tiaojian) {
        Date previousDay = getPreviousDay(tiaojian.getStatsDate());//上一天日期
        WorkCompanyCoalStock workStock = new WorkCompanyCoalStock();
        workStock.setRecordDate(tiaojian.getStatsDate());
        List<WorkCompanyCoalStock> list = workCompanyCoalStockService.listWorkCompanyCoalStock(workStock);
        WorkThermalPowerCoalSales workThermalPowerCoalSale = new WorkThermalPowerCoalSales();
        workThermalPowerCoalSale.setRecordDate(tiaojian.getStatsDate());
        List<WorkThermalPowerCoalSales> list2 = workThermalPowerCoalSalesService.listWorkThermalPowerCoalSales(workThermalPowerCoalSale);

        if(list.size()==0){
            workStock.setRecordDate(previousDay);
            List<WorkCompanyCoalStock> workStocks = workCompanyCoalStockService.listWorkCompanyCoalStock(workStock);
            for (WorkCompanyCoalStock workStock1:workStocks){
                WorkCompanyCoalStock workCoaltock = new WorkCompanyCoalStock();
                workCoaltock.setRecordDate(tiaojian.getStatsDate());
                workCoaltock.setCoalGrade(workStock1.getCoalGrade());
                workCoaltock.setLeanCoalPreviousStock(workStock1.getLeanCoalCurrentStock());
                workCoaltock.setMainCokePreviousStock(workStock1.getMainCokeCurrentStock());
                workCoaltock.setFatCoalPreviousStock(workStock1.getFatCoalCurrentStock());
                workCoaltock.setTotalPreviousStock(workStock1.getTotalCurrentStock());
            }
        }
        WorkCoalStockSalesStat workCoalStat = new WorkCoalStockSalesStat();
        workCoalStat.setRecordDate(tiaojian.getStatsDate());
        List<WorkCoalStockSalesStat> list3 = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workCoalStat);

        WorkCoalStockSalesStat workCoalStat1 = new WorkCoalStockSalesStat();
        workCoalStat1.setRecordDate(previousDay);
        List<WorkCoalStockSalesStat> list4 = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workCoalStat1);
        List<WorkCoalStockSalesStat> listAll =new ArrayList<>();

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名
        FactoryArchive factoryArch = new FactoryArchive();
        factoryArch.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArch);

        List<MineData> 累计月完成 = subMineDevelopmentDataMapper.selectMonthDate(DateUtils.returnDateRange(tiaojian.getStatsDate()));//月累计完成
        List<MineData> 日完成 = subMineDevelopmentDataMapper.selectDayDate(DateUtils.returnDateRange(tiaojian.getStatsDate()));//日累计完成
        DestinationOfRawCoal dest=new DestinationOfRawCoal();
        dest.setRecordDate(tiaojian.getStatsDate());
        List<DestinationOfRawCoal> 日去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(dest);
        List<DestinationOfRawCoal> 月去向 = destinationOfRawCoalMapper.selectDestinationOfRawCoalByMonth(dest);

        Date firstDayOfMonth = DateUtils.getFirstDayOfMonth(tiaojian.getStatsDate());
        InitialInventoryOfEachMine initialInventoryOfEachMine= new InitialInventoryOfEachMine();
        initialInventoryOfEachMine.setRecordDate(firstDayOfMonth);
        InitialInventoryOfEachMine initialInventoryOfEachMines = initialInventoryOfEachMineMapper.selectInitialInventoryOfEachMine(initialInventoryOfEachMine);
        List<SubInitialInventoryOfEachMine> 期初库存=new ArrayList<>();
        if (initialInventoryOfEachMines!=null){
            if(initialInventoryOfEachMines.getId()!=null){
                SubInitialInventoryOfEachMine sub=new SubInitialInventoryOfEachMine();
                sub.setInitialInventoryId(initialInventoryOfEachMines.getId());
                期初库存 = subInitial.selectSubInitialInventoryOfEachMineList(sub);
            }
        }



        for (FactoryArchive factory : factoryArchives){
            WorkCoalStockSalesStat workCoalStockSalesStat1 = list3.stream().filter(item -> factory.getFactoryName().equals(item.getCoalType())).findFirst().orElse(new WorkCoalStockSalesStat());
            if(workCoalStockSalesStat1.getCoalType() !=null){
                listAll.add(workCoalStockSalesStat1);
            }else {
                //前日库存数据
                WorkCoalStockSalesStat workCoalStockSalesStat2 = list4.stream().filter(item -> factory.getFactoryName().equals(item.getCoalType())).findFirst().orElse(new WorkCoalStockSalesStat());
                WorkCoalStockSalesStat workCoalStockSalesStat = new WorkCoalStockSalesStat();
                workCoalStockSalesStat.setRecordDate(tiaojian.getStatsDate());
                workCoalStockSalesStat.setCoalType(factory.getFactoryName());
                if(workCoalStockSalesStat2.getCoalType() !=null){
                    workCoalStockSalesStat.setRawCoalPreviousStock(workCoalStockSalesStat2.getRawCoalCurrentStock());
                    workCoalStockSalesStat.setCleanCoalPreviousWarehouse(workCoalStockSalesStat2.getCleanCoalCurrentWarehouse());
                    workCoalStockSalesStat.setLeanCoalPreviousWarehouse(workCoalStockSalesStat2.getLeanCoalCurrentWarehouse());
                    workCoalStockSalesStat.setSlimePreviousWarehouse(workCoalStockSalesStat2.getSlimeCurrentWarehouse());
                }
                listAll.add(workCoalStockSalesStat);
            }
        }

        for (MiningAreaCategory mining :miningAreaCategories){
            WorkCoalStockSalesStat workCoalStockSalesStat1 = list3.stream().filter(item -> mining.getAreaName().equals(item.getCoalType())).findFirst().orElse(new WorkCoalStockSalesStat());
            if(workCoalStockSalesStat1.getCoalType() !=null){
                listAll.add(workCoalStockSalesStat1);
            }else {
                //前日库存数据
                WorkCoalStockSalesStat workCoalStockSalesStat = new WorkCoalStockSalesStat();
                workCoalStockSalesStat.setRecordDate(tiaojian.getStatsDate());
                workCoalStockSalesStat.setCoalType(mining.getAreaName());
                MineData 当日日完成 = 日完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new MineData());
                MineData 当月日完成 = 累计月完成.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new MineData());
                DestinationOfRawCoal 日销售 = 日去向.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new DestinationOfRawCoal());
                DestinationOfRawCoal 月销售 = 月去向.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new DestinationOfRawCoal());
                SubInitialInventoryOfEachMine 当月库存 = 期初库存.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubInitialInventoryOfEachMine());
                int 今日完成 = 当日日完成 != null && 当日日完成.getProductionData() != null ? toInt(当日日完成.getProductionData()) : 0;
                int 今日销售 = 日销售 != null && 日销售.getSalesVolume() != null ? toInt(日销售.getSalesVolume()) : 0;
                int 当月完成 = 当月日完成 != null && 当月日完成.getProductionData() != null ? toInt(当月日完成.getProductionData()) : 0;
                int 今月销售 = 月销售 != null && 月销售.getSalesVolume() != null ? toInt(月销售.getSalesVolume()) : 0;
                int 今月库存 = 当月库存 != null && 当月库存.getInitialInventoryOfThisMonth() != null ? toInt(当月库存.getInitialInventoryOfThisMonth()) : 0;

                int i = 今月库存 + (当月完成 - 今月销售) - (今日完成 - 今日销售);
                workCoalStockSalesStat.setRawCoalPreviousStock(Long.valueOf(i));//昨日库存
                workCoalStockSalesStat.setRawCoalDailyChange((long) (今日完成-今日销售));//今日增减
                long l = workCoalStockSalesStat.getRawCoalPreviousStock() + workCoalStockSalesStat.getRawCoalDailyChange();
                workCoalStockSalesStat.setRawCoalCurrentStock(l);//当日库存
                listAll.add(workCoalStockSalesStat);
            }
        }

        WorkCoalStock stock = new WorkCoalStock();
        stock.setListCompanyCoalStock(list);
        stock.setListThermalPowerCoalSales(list2);
        stock.setListCoalStockSalesStat(listAll);
        return AjaxResult.success(stock);
    }

    @PostMapping("/gasbureauList")  //局端获取
    public AjaxResult gasbureauList(@RequestBody riBao tiaojian) {
        Date previousDay = getPreviousDay(tiaojian.getStatsDate());//上一天日期
        WorkCompanyCoalStock workStock = new WorkCompanyCoalStock();
        workStock.setRecordDate(tiaojian.getStatsDate());
        List<WorkCompanyCoalStock> list = workCompanyCoalStockService.listWorkCompanyCoalStock(workStock);
        if(list.size()==0){

            workStock.setRecordDate(getPreviousDay(tiaojian.getStatsDate()));
            List<WorkCompanyCoalStock>  listBefer = workCompanyCoalStockService.listWorkCompanyCoalStock(workStock);
            List<WorkCompanyCoalStock>  listBeferreturn = new ArrayList<>();

            for (WorkCompanyCoalStock workCompanyCoalStock : listBefer){
                if(!workCompanyCoalStock.getCoalGrade().equals("合理库存煤量")){
                    WorkCompanyCoalStock returnStock=new WorkCompanyCoalStock();
                    returnStock.setRecordDate(tiaojian.getStatsDate());
                    returnStock.setCoalGrade(workCompanyCoalStock.getCoalGrade());
                    returnStock.setLeanCoalPreviousStock(workCompanyCoalStock.getLeanCoalCurrentStock());
                    returnStock.setMainCokePreviousStock(workCompanyCoalStock.getMainCokeCurrentStock());
                    returnStock.setFatCoalPreviousStock(workCompanyCoalStock.getFatCoalCurrentStock());
                    returnStock.setTotalPreviousStock(workCompanyCoalStock.getTotalCurrentStock());
                    listBeferreturn.add(returnStock);
                }
            }


            WorkCoalStock stock = new WorkCoalStock();
            stock.setListCompanyCoalStock(listBeferreturn);
            return AjaxResult.success(stock);
        }


        WorkCoalStock stock = new WorkCoalStock();
        stock.setListCompanyCoalStock(list);
        return AjaxResult.success(stock);
    }


    /**
     * 各选煤厂库存煤量统计表添加  厂端
     */
    @Log(title = "各选煤厂库存煤量统计表添加", businessType = BusinessType.INSERT)
    @PostMapping("/factory")
    public AjaxResult add(@RequestBody WorkCoalStockSalesStat workCompanyCoalStock) {
        WorkCoalStockSalesStat workCompany = new WorkCoalStockSalesStat();
        workCompany.setRecordDate(workCompanyCoalStock.getRecordDate());
        List<WorkCoalStockSalesStat> list = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workCompany);
        if (list.size() > 0){
            workCompanyCoalStock.setId(list.get(0).getId());
            return toAjax(workCoalStockSalesStatService.updateWorkCoalStockSalesStat(workCompanyCoalStock));
        }else {
            return toAjax(workCoalStockSalesStatService.saveWorkCoalStockSalesStat(workCompanyCoalStock));
        }
    }
    /**
     * 煤气公司精煤库存统计对象  局端保存
     */
    @Log(title = "煤气公司精煤库存统计对象", businessType = BusinessType.INSERT)
    @PostMapping("/bureau")
    public AjaxResult add(@RequestBody WorkCoalStock work) {
        List<WorkCompanyCoalStock> listCompanyCoalStock = work.getListCompanyCoalStock();
        List<WorkThermalPowerCoalSales> listThermalPowerCoalSales = work.getListThermalPowerCoalSales();
        List<WorkCoalStockSalesStat> listCoalStockSalesStat = work.getListCoalStockSalesStat();
        try{

            if(listCompanyCoalStock.size()>0){
                MineInfo mineInfo = new MineInfo();
                mineInfo.setModuleName("煤气公司精煤库存");
                mineInfo.setMineName("煤气公司精煤库存");
                mineInfo.setStatDate(listCompanyCoalStock.get(0).getRecordDate());
                List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
                if(mineInfos.size()>0){
                    mineInfoService.deleteMineInfoByDate(mineInfo);
                }else {
                    return AjaxResult.error("请联系局里进行驳回");
                }
            }
            for (WorkCompanyCoalStock workCompanyCoalStock : listCompanyCoalStock){
                WorkCompanyCoalStock workCompany = new WorkCompanyCoalStock();
                workCompany.setRecordDate(workCompanyCoalStock.getRecordDate());
                workCompany.setCoalGrade(workCompanyCoalStock.getCoalGrade());
                List<WorkCompanyCoalStock> workCompanyCoalStocks = workCompanyCoalStockService.listWorkCompanyCoalStock(workCompany);
                if (workCompanyCoalStocks.size() > 0){
                    workCompanyCoalStock.setId(workCompanyCoalStocks.get(0).getId());
                    workCompanyCoalStockService.updateWorkCompanyCoalStock(workCompanyCoalStock);
                }else {
                    workCompanyCoalStockService.saveWorkCompanyCoalStock(workCompanyCoalStock);
                }
            }

            for (WorkThermalPowerCoalSales workThermalPowerCoalSale : listThermalPowerCoalSales){
                WorkThermalPowerCoalSales workThermalPower = new WorkThermalPowerCoalSales();
                workThermalPower.setRecordDate(workThermalPowerCoalSale.getRecordDate());
                workThermalPower.setCoalType(workThermalPowerCoalSale.getCoalType());
                List<WorkThermalPowerCoalSales> workThermalPowerCoalSales = workThermalPowerCoalSalesService.listWorkThermalPowerCoalSales(workThermalPower);
                if (workThermalPowerCoalSales.size() > 0){
                    workThermalPowerCoalSale.setId(workThermalPowerCoalSales.get(0).getId());
                    workThermalPowerCoalSalesService.updateWorkThermalPowerCoalSales(workThermalPowerCoalSale);
                }else {
                    workThermalPowerCoalSalesService.saveWorkThermalPowerCoalSales(workThermalPowerCoalSale);
                }
            }

//            for (WorkCoalStockSalesStat workCoalStockSalesStat : listCoalStockSalesStat){
//                WorkCoalStockSalesStat workThermalSale = new WorkCoalStockSalesStat();
//                workThermalSale.setRecordDate(workCoalStockSalesStat.getRecordDate());
//                workThermalSale.setCoalType(workCoalStockSalesStat.getCoalType());
//                List<WorkCoalStockSalesStat> workThermalPowerCoalSales = workCoalStockSalesStatService.listWorkCoalStockSalesStat(workThermalSale);
//                if (workThermalPowerCoalSales.size() > 0){
//                    workCoalStockSalesStat.setId(workThermalPowerCoalSales.get(0).getId());
//                    workCoalStockSalesStatService.updateWorkCoalStockSalesStat(workCoalStockSalesStat);
//                }else {
//                    workCoalStockSalesStatService.saveWorkCoalStockSalesStat(workCoalStockSalesStat);
//                }
//            }
        }catch (Exception e){
            return toAjax(0);
        }
        return toAjax(1);
    }

    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(WorkCompanyCoalStock raw){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("煤气公司精煤库存");
        mineInfo.setMineName("煤气公司精煤库存");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(raw.getRecordDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return AjaxResult.error("已经退回");
        }else {
            mineInfoService.saveMineInfo(mineInfo);
            return AjaxResult.success("退回成功");
        }
    }



    // 辅助方法
    private Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }
    private static int toInt(BigDecimal value) {
        // 1. null 转为 BigDecimal.ZERO，非 null 保留原值
        BigDecimal result = Optional.ofNullable(value).orElse(BigDecimal.ZERO);
        // 2. 直接舍去小数（RoundingMode.DOWN），转为 int（超出范围抛异常）
        return result.setScale(0, BigDecimal.ROUND_DOWN).intValueExact();
    }
    private static int toInt(Integer value) {
        // Optional 安全处理 null，默认返回 0
        return Optional.ofNullable(value).orElse(0);
    }
    private static int toInt(Long value) {
        // Optional 安全处理 null，默认返回 0
        return Optional.ofNullable(value).map(Long::intValue).orElse(0);
    }
}
