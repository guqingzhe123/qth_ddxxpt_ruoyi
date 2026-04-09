package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.RawCoalWhereabouts;
import com.ruoyi.system.domain.RawCoalWhereaboutsAddInput;
import com.ruoyi.system.domain.work.WorkProductionDailyReport;
import com.ruoyi.system.domain.work.WorkProductionStatus;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.IRawCoalWhereaboutsService;
import com.ruoyi.system.service.work.IWorkProductionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 原煤去向对照（记录各煤矿原煤每日及累计去向数据）Controller
 *
 * @author ruoyi
 * @date 2025-11-07
 */
@RestController
@RequestMapping("/system/whereabouts")
public class RawCoalWhereaboutsController extends BaseController {
    @Autowired
    private IRawCoalWhereaboutsService rawCoalWhereaboutsService;

    @Autowired
    private IWorkProductionStatusService workProductionStatusService;//日报子表 生产情况

    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表


    @Resource
    private IMineInfoService mineInfoService;//退回状态


    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(RawCoalWhereabouts rawCoalWhereabouts) {
        List<RawCoalWhereabouts> list = rawCoalWhereaboutsService.listRawCoalWhereabouts(rawCoalWhereabouts);
        if(list.size() ==0 ){
            List<RawCoalWhereabouts> alllist = rawCoalWhereaboutsService.Alllist(rawCoalWhereabouts.getRq());
            for (RawCoalWhereabouts rawCoal2:alllist) {
                RawCoalWhereabouts rawCoal=new RawCoalWhereabouts();
                rawCoal.setRq(rawCoalWhereabouts.getRq());
                rawCoal.setDanwei(rawCoal2.getDanwei());
                rawCoal.setXichangRi(BigDecimal.valueOf(0));
                rawCoal.setXichangLeiji(rawCoal2.getXichangLeiji());
                rawCoal.setTiaoxianchangRi(BigDecimal.valueOf(0));
                rawCoal.setTiaoxianchangLeiji(rawCoal2.getTiaoxianchangLeiji());
                rawCoal.setXinxuanchangRi(BigDecimal.valueOf(0));
                rawCoal.setXinxuanchangLeiji(rawCoal2.getXinxuanchangLeiji());
                rawCoal.setTiexuanchangRi(BigDecimal.valueOf(0));
                rawCoal.setTiexuanchangLeiji(rawCoal2.getTiexuanchangLeiji());
                rawCoal.setLongxichangRi(BigDecimal.valueOf(0));
                rawCoal.setLongxichangLeiji(rawCoal2.getLongxichangLeiji());
                rawCoal.setFuxichangRi(BigDecimal.valueOf(0));
                rawCoal.setFuxichangLeiji(rawCoal2.getFuxichangLeiji());
                rawCoal.setMeiqigongsiRi(BigDecimal.valueOf(0));
                rawCoal.setMeiqigongsiLeiji(rawCoal2.getMeiqigongsiLeiji());
                rawCoal.setZiyongRi(BigDecimal.valueOf(0));
                rawCoal.setZiyongLeiji(rawCoal2.getZiyongLeiji());
                rawCoal.setGanshidianchangRi(BigDecimal.valueOf(0));
                rawCoal.setGanshidianchangLeiji(rawCoal2.getGanshidianchangLeiji());
                rawCoal.setHuochangchuRi(BigDecimal.valueOf(0));
                rawCoal.setHuochangchuLeiji(rawCoal2.getHuochangchuLeiji());
                rawCoal.setTiewaixiaoRi(BigDecimal.valueOf(0));
                rawCoal.setTiewaixiaoLeiji(rawCoal2.getTiewaixiaoLeiji());
                rawCoal.setQitaRi(BigDecimal.valueOf(0));
                rawCoal.setQitaLeiji(rawCoal2.getQitaLeiji());

                WorkProductionDailyReport workStatus=new WorkProductionDailyReport();
                workStatus.setReportDate(rawCoalWhereabouts.getRq());
                workStatus.setUnit(rawCoal2.getDanwei());
                WorkProductionStatus workStatuses = workProductionStatusService.listWorkProductionStatusDay(workStatus);
                if (workStatuses != null) {
                    rawCoal.setZonghejiRi(BigDecimal.valueOf(workStatuses.getMailySales()));
                } else {
                    rawCoal.setZonghejiRi(BigDecimal.ZERO);
                }
                rawCoal.setZonghejiLeiji(rawCoal2.getZonghejiLeiji());
                rawCoal.setRiShengchan(BigDecimal.valueOf(0));
                rawCoal.setRiCha(rawCoal2.getRiCha());
                rawCoal.setLeijiCha(rawCoal2.getLeijiCha());
                list.add(rawCoal);
            }
        }

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);

        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("原煤去向对照表");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(rawCoalWhereabouts.getRq());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

        List<RawCoalWhereabouts> sortedList = new ArrayList<>();
        for (MiningAreaCategory miningAreaCategory : miningAreaCategories) {
            for (RawCoalWhereabouts item : list) {
                if (item.getDanwei().equals(miningAreaCategory.getAreaName())) {

                    for (MineInfo info : mineInfos){
                        if(info.getMineName().equals("七洗厂")){
                            item.setXichangRi(BigDecimal.valueOf(0));
                            item.setXichangLeiji(BigDecimal.valueOf(0));
                        }
                        if(info.getMineName().equals("桃选厂")){
                            item.setTiaoxianchangRi(BigDecimal.valueOf(0));
                            item.setTiaoxianchangLeiji(BigDecimal.valueOf(0));
                        }
                        if(info.getMineName().equals("新选厂")){
                            item.setXinxuanchangRi(BigDecimal.valueOf(0));
                            item.setXinxuanchangLeiji(BigDecimal.valueOf(0));
                        }
                        if(info.getMineName().equals("铁选厂")){
                            item.setTiexuanchangRi(BigDecimal.valueOf(0));
                            item.setTiexuanchangLeiji(BigDecimal.valueOf(0));
                        }
                        if(info.getMineName().equals("龙洗厂")){
                            item.setLongxichangRi(BigDecimal.valueOf(0));
                            item.setLongxichangLeiji(BigDecimal.valueOf(0));
                        }
                        if(info.getMineName().equals("富洗厂")){
                            item.setFuxichangRi(BigDecimal.valueOf(0));
                            item.setFuxichangLeiji(BigDecimal.valueOf(0));
                        }
                    }
                    sortedList.add(item);
                    break;
                }
            }
        }
        list = sortedList;

        return getDataTable(list);
    }

    /**
     * 新增原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Log(title = "原煤去向对照（记录各煤矿原煤每日及累计去向数据）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<RawCoalWhereaboutsAddInput> rawCoalWhereabouts) {
        RawCoalWhereabouts raw=new RawCoalWhereabouts();
        raw.setRq(rawCoalWhereabouts.get(0).getRq());
        List<RawCoalWhereabouts> list = rawCoalWhereaboutsService.listRawCoalWhereabouts(raw);






        if(list.size()>0){
            String mineName=null;
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("原煤去向对照表");
            mineInfo.setStatDate(raw.getRq());
            for (RawCoalWhereabouts input:list) {
                RawCoalWhereaboutsAddInput input1 = rawCoalWhereabouts.stream().filter(item -> input.getDanwei().equals(item.getDanwei())).findFirst().orElse(new RawCoalWhereaboutsAddInput());

                mineInfo.setMineName(input1.getCoalWashing());//那个洗煤厂退回
                List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

                mineName=input1.getCoalWashing();
                 if(input1.getCoalWashing().equals("七洗厂")){
                    if(mineInfos.size()==0 && input.getXichangRi() !=null  && input.getXichangLeiji() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }

                    input.setXichangRi(input1.getRi());
                    input.setXichangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("桃选厂")){
                    if(mineInfos.size()==0 && input.getTiaoxianchangRi() !=null  && input.getTiaoxianchangLeiji() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }


                    input.setTiaoxianchangRi(input1.getRi());
                    input.setTiaoxianchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("新选厂")){
                    if(mineInfos.size()==0 && input.getXinxuanchangRi() !=null  && input.getXinxuanchangLeiji() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }

                    input.setXinxuanchangRi(input1.getRi());
                    input.setXinxuanchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("铁选厂")){
                    if(mineInfos.size()==0 && input.getTiexuanchangRi() !=null  && input.getTiexuanchangRi() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }

                    input.setTiexuanchangRi(input1.getRi());
                    input.setTiexuanchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("龙洗厂")){
                    if(mineInfos.size()==0 && input.getLongxichangRi() !=null  && input.getLongxichangLeiji() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }
                    input.setLongxichangRi(input1.getRi());
                    input.setLongxichangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("富洗厂")){
                    if(mineInfos.size()==0 && input.getFuxichangRi() !=null  && input.getFuxichangLeiji() !=null){
                        return AjaxResult.error("请联系局里进行驳回");
                    }
                    input.setFuxichangRi(input1.getRi());
                    input.setFuxichangLeiji(input1.getLeiji());
                }
                rawCoalWhereaboutsService.updateRawCoalWhereabouts(input);
            }
            if(mineName !=null){
                mineInfo.setMineName(mineName);
                mineInfoService.deleteMineInfoByDate(mineInfo);
            }

        }
        else {
            List<RawCoalWhereabouts> listRaw =new ArrayList<>();
            for (RawCoalWhereaboutsAddInput input1:rawCoalWhereabouts) {
                RawCoalWhereabouts input =new RawCoalWhereabouts();
                input.setDanwei(input1.getDanwei());
                input.setRq(input1.getRq());
                 if(input1.getCoalWashing().equals("七洗厂")){
                    input.setXichangRi(input1.getRi());
                    input.setXichangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("桃选厂")){
                    input.setTiaoxianchangRi(input1.getRi());
                    input.setTiaoxianchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("新选厂")){
                    input.setXinxuanchangRi(input1.getRi());
                    input.setXinxuanchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("铁选厂")){
                    input.setTiexuanchangRi(input1.getRi());
                    input.setTiexuanchangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("龙洗厂")){
                    input.setLongxichangRi(input1.getRi());
                    input.setLongxichangLeiji(input1.getLeiji());
                }
                if(input1.getCoalWashing().equals("富洗厂")){
                    input.setFuxichangRi(input1.getRi());
                    input.setFuxichangLeiji(input1.getLeiji());
                }
                listRaw.add(input);
            }
            return toAjax(rawCoalWhereaboutsService.saveRawCoalWhereabouts(listRaw));
        }
        return toAjax(1);
    }
    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表   洗煤厂端
     */
    @GetMapping("/coalWashingList")
    public TableDataInfo<BaseEntity> coalWashingList(RawCoalWhereaboutsAddInput raw) {
        RawCoalWhereabouts rawCoalWhereabouts =new RawCoalWhereabouts();
        rawCoalWhereabouts.setRq(raw.getRq());
        List<RawCoalWhereabouts> list = rawCoalWhereaboutsService.listRawCoalWhereabouts(rawCoalWhereabouts);

        List<RawCoalWhereaboutsAddInput> listinput = new ArrayList<>();
        if(list.size()>0){
            for (RawCoalWhereabouts input:list) {
                RawCoalWhereaboutsAddInput input1 =new RawCoalWhereaboutsAddInput();
                input1.setCoalWashing(raw.getCoalWashing());
                input1.setDanwei(input.getDanwei());
                input1.setRq(input.getRq());
                if(raw.getCoalWashing().equals("七洗厂")){
                    input1.setRi(input.getXichangRi());
                    input1.setLeiji(input.getXichangLeiji());
                }
                if(raw.getCoalWashing().equals("桃选厂")){
                    input1.setRi(input.getTiaoxianchangRi());
                    input1.setLeiji(input.getTiaoxianchangLeiji());
                }
                if(raw.getCoalWashing().equals("新选厂")){
                    input1.setRi(input.getXinxuanchangRi());
                    input1.setLeiji(input.getXinxuanchangLeiji());
                }
                if(raw.getCoalWashing().equals("铁选厂")){
                    input1.setRi(input.getTiexuanchangRi());
                    input1.setLeiji(input.getTiexuanchangLeiji());
                }
                if(raw.getCoalWashing().equals("龙洗厂")){
                    input1.setRi(input.getLongxichangRi());
                    input1.setLeiji(input.getLongxichangLeiji());
                }
                if(raw.getCoalWashing().equals("富洗厂")){
                    input1.setRi(input.getFuxichangRi());
                    input1.setLeiji(input.getFuxichangLeiji());
                }
                listinput.add(input1);
            }

        }else {
            List<RawCoalWhereabouts> alllist = rawCoalWhereaboutsService.Alllist(rawCoalWhereabouts.getRq());
            for (RawCoalWhereabouts rawCoal2:alllist) {
                RawCoalWhereaboutsAddInput input1 =new RawCoalWhereaboutsAddInput();
                input1.setRq(raw.getRq());
                input1.setDanwei(rawCoal2.getDanwei());
                if(raw.getCoalWashing().equals("七洗厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getXichangLeiji());
                }
                if(raw.getCoalWashing().equals("桃选厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getTiaoxianchangLeiji());
                }
                if(raw.getCoalWashing().equals("新选厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getXinxuanchangLeiji());
                }
                if(raw.getCoalWashing().equals("铁选厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getTiexuanchangLeiji());
                }
                if(raw.getCoalWashing().equals("龙洗厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getLongxichangLeiji());
                }
                if(raw.getCoalWashing().equals("富洗厂")){
                    input1.setRi(BigDecimal.valueOf(0));
                    input1.setLeiji(rawCoal2.getFuxichangLeiji());
                }
                listinput.add(input1);
            }
        }
        return getDataTable(listinput);
    }


//    /**
//     * 新增原煤去向对照（记录各煤矿原煤每日及累计去向数据）
//     */
//    @Log(title = "原煤去向对照（记录各煤矿原煤每日及累计去向数据）", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody List<RawCoalWhereabouts> rawCoalWhereabouts) {
//        return toAjax(rawCoalWhereaboutsService.saveRawCoalWhereabouts(rawCoalWhereabouts));
//    }
    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     */
    @GetMapping("/Alllist")
    public TableDataInfo<BaseEntity> Alllist(Date statsDate) {
        List<RawCoalWhereabouts> list = rawCoalWhereaboutsService.Alllist(statsDate);
        return getDataTable(list);
    }
    /**
     * 导出原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     */
    @Log(title = "原煤去向对照（记录各煤矿原煤每日及累计去向数据）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RawCoalWhereabouts rawCoalWhereabouts) {
        List<RawCoalWhereabouts> list = rawCoalWhereaboutsService.listRawCoalWhereabouts(rawCoalWhereabouts);
        ExcelUtil<RawCoalWhereabouts> util = new ExcelUtil<RawCoalWhereabouts>(RawCoalWhereabouts.class);
        util.exportExcel(response, list, "原煤去向对照（记录各煤矿原煤每日及累计去向数据）数据");
    }
    /**
     * 获取原煤去向对照（记录各煤矿原煤每日及累计去向数据）详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(rawCoalWhereaboutsService.getRawCoalWhereaboutsById(id));
    }
    /**
     * 修改原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Log(title = "原煤去向对照（记录各煤矿原煤每日及累计去向数据）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RawCoalWhereabouts rawCoalWhereabouts) {
        return toAjax(rawCoalWhereaboutsService.updateRawCoalWhereabouts(rawCoalWhereabouts));
    }

    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(RawCoalWhereaboutsAddInput raw){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("原煤去向对照表");
        mineInfo.setMineName(raw.getCoalWashing());//那个洗煤厂退回
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(raw.getRq());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return AjaxResult.error("已经退回");
        }else {
            mineInfoService.saveMineInfo(mineInfo);
            return AjaxResult.success("退回成功");
        }
    }

    /**
     * 删除原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Log(title = "原煤去向对照（记录各煤矿原煤每日及累计去向数据）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(rawCoalWhereaboutsService.deleteRawCoalWhereaboutsByIds(ids));
    }
}