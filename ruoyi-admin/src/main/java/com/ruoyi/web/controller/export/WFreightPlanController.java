package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WFreightPlan;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWDataRecordService;
import com.ruoyi.system.service.export.IWFreightPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 驻矿公司煤炭发运承认车情况_承认车Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/recordPlan")
public class WFreightPlanController extends BaseController {
    @Autowired
    private IWFreightPlanService wFreightPlanService;

    @Autowired
    private IWDataRecordService wDataRecordService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态
    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WFreightPlan wFreightPlan) {
        List<WFreightPlan> list = wFreightPlanService.listWFreightPlan(wFreightPlan);
        return getDataTable(list);
    }

    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WFreightPlan wFreightPlan) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭发运承认车情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wFreightPlan.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WFreightPlan> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WFreightPlan> list = wFreightPlanService.listWFreightPlan(wFreightPlan);
            return getDataTable(list);
        }
    }
    /**
     * 查询驻矿公司煤炭发运承认车情况_承认车状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭发运承认车情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WFreightPlan wFreightPlan=new WFreightPlan();
            wFreightPlan.setStatsDate(tiaojian.getStatsDate());
            List<WFreightPlan> list = wFreightPlanService.listWFreightPlan(wFreightPlan);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出驻矿公司煤炭发运承认车情况_承认车列表
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_承认车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WFreightPlan wFreightPlan) {
        List<WFreightPlan> list = wFreightPlanService.listWFreightPlan(wFreightPlan);
        ExcelUtil<WFreightPlan> util = new ExcelUtil<WFreightPlan>(WFreightPlan.class);
        util.exportExcel(response, list, "驻矿公司煤炭发运承认车情况_承认车数据");
    }

    /**
     * 获取驻矿公司煤炭发运承认车情况_承认车详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wFreightPlanService.getWFreightPlanById(id));
    }

    /**
     * 新增驻矿公司煤炭发运承认车情况_承认车
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_承认车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WFreightPlan> wFreightPlanList) {
        if (wFreightPlanList == null || wFreightPlanList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WFreightPlan firstPlan = wFreightPlanList.get(0);
        WFreightPlan wFreight = new WFreightPlan();
        wFreight.setStatsDate(firstPlan.getStatsDate());
        List<WFreightPlan> list = wFreightPlanService.listWFreightPlan(wFreight);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("驻矿公司煤炭发运承认车情况");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstPlan.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WFreightPlan wFreightPlan : wFreightPlanList) {
                    if(wFreightPlan.getId()!=null&&!"".equals(wFreightPlan.getId())){
                        result += wFreightPlanService.updateWFreightPlan(wFreightPlan);
                    }else{
                        result += wFreightPlanService.saveWFreightPlan(wFreightPlan);
                    }
                }
                return toAjax(result);
            }

            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WFreightPlan wFreightPlan : wFreightPlanList) {
                result += wFreightPlanService.saveWFreightPlan(wFreightPlan);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改驻矿公司煤炭发运承认车情况_承认车
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_承认车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WFreightPlan wFreightPlan) {
        return toAjax(wFreightPlanService.updateWFreightPlan(wFreightPlan));
    }

    /**
     * 删除驻矿公司煤炭发运承认车情况_承认车
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_承认车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wFreightPlanService.deleteWFreightPlanByIds(ids));
    }

    /**
     * 退回驻矿公司煤炭发运承认车情况_承认车
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭发运承认车情况");
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
