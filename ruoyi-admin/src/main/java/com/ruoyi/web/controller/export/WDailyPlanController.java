package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WDailyPlan;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWDailyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 外销商品煤日执行情况Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/exportGoods")
public class WDailyPlanController extends BaseController {
    @Autowired
    private IWDailyPlanService wDailyPlanService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态
    /**
     * 查询外销商品煤日执行情况列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WDailyPlan wDailyPlan) {
        List<WDailyPlan> list = wDailyPlanService.listWDailyPlan(wDailyPlan);
        return getDataTable(list);
    }
    /**
     * 查询外销商品煤日执行情况情况列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WDailyPlan wDailyPlan) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤日执行情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wDailyPlan.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WDailyPlan> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WDailyPlan> list = wDailyPlanService.listWDailyPlan(wDailyPlan);
            return getDataTable(list);
        }
    }


    /**
     * 查询外销商品煤日执行情况状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤日执行情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WDailyPlan wDailyPlan=new WDailyPlan();
            wDailyPlan.setStatsDate(tiaojian.getStatsDate());
            List<WDailyPlan> list = wDailyPlanService.listWDailyPlan(wDailyPlan);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }


    /**
     * 导出外销商品煤日执行情况列表
     */
    @Log(title = "外销商品煤日执行情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WDailyPlan wDailyPlan) {
        List<WDailyPlan> list = wDailyPlanService.listWDailyPlan(wDailyPlan);
        ExcelUtil<WDailyPlan> util = new ExcelUtil<WDailyPlan>(WDailyPlan.class);
        util.exportExcel(response, list, "外销商品煤日执行情况数据");
    }

    /**
     * 获取外销商品煤日执行情况详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wDailyPlanService.getWDailyPlanById(id));
    }

    /**
     * 新增外销商品煤日执行情况
     */
    @Log(title = "外销商品煤日执行情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WDailyPlan> wDailyPlan) {

        if (wDailyPlan == null || wDailyPlan.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }

        // 取第一条数据的日期进行校验
        WDailyPlan firstReceipt = wDailyPlan.get(0);
        WDailyPlan wCargo = new WDailyPlan();
        wCargo.setStatsDate(firstReceipt.getStatsDate());
        List<WDailyPlan> list = wDailyPlanService.listWDailyPlan(wCargo);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("外销商品煤日执行情况");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstReceipt.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WDailyPlan wCargoReceipt : wDailyPlan) {
                    if(wCargoReceipt.getId()!=null&&!"".equals(wCargoReceipt.getId())){
                        result += wDailyPlanService.updateWDailyPlan(wCargoReceipt);
                    }else{
                        result += wDailyPlanService.saveWDailyPlan(wCargoReceipt);
                    }
                }
                return toAjax(result);
            }else {
                return AjaxResult.error("请联系局里进行退回");
            }
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WDailyPlan wCargoReceipt : wDailyPlan) {
                result += wDailyPlanService.saveWDailyPlan(wCargoReceipt);
            }
            return toAjax(result);
        }


    }

    /**
     * 修改外销商品煤日执行情况
     */
    @Log(title = "外销商品煤日执行情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WDailyPlan wDailyPlan) {
        return toAjax(wDailyPlanService.updateWDailyPlan(wDailyPlan));
    }

    /**
     * 删除外销商品煤日执行情况
     */
    @Log(title = "外销商品煤日执行情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wDailyPlanService.deleteWDailyPlanByIds(ids));
    }

    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤日执行情况");
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
