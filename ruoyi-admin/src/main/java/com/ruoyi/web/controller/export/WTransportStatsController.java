package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WTransportStats;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWTransportStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 驻矿公司煤炭调运日报Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/coalTransportation")
public class WTransportStatsController extends BaseController {
    @Autowired
    private IWTransportStatsService wTransportStatsService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    /**
     * 查询驻矿公司煤炭调运日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WTransportStats wTransportStats) {
        List<WTransportStats> list = wTransportStatsService.listWTransportStats(wTransportStats);
        return getDataTable(list);
    }

    /**
     * 查询驻矿公司煤炭调运日报列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WTransportStats wTransportStats) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭调运日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wTransportStats.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WTransportStats> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WTransportStats> list = wTransportStatsService.listWTransportStats(wTransportStats);
            return getDataTable(list);
        }
    }
    /**
     * 查询驻矿公司煤炭调运日报状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭调运日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WTransportStats wTransportStats=new WTransportStats();
            wTransportStats.setStatsDate(tiaojian.getStatsDate());
            List<WTransportStats> list = wTransportStatsService.listWTransportStats(wTransportStats);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出驻矿公司煤炭调运日报列表
     */
    @Log(title = "驻矿公司煤炭调运日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WTransportStats wTransportStats) {
        List<WTransportStats> list = wTransportStatsService.listWTransportStats(wTransportStats);
        ExcelUtil<WTransportStats> util = new ExcelUtil<WTransportStats>(WTransportStats.class);
        util.exportExcel(response, list, "驻矿公司煤炭调运日报数据");
    }

    /**
     * 获取驻矿公司煤炭调运日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wTransportStatsService.getWTransportStatsById(id));
    }

    /**
     * 新增驻矿公司煤炭调运日报
     */
    @Log(title = "驻矿公司煤炭调运日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WTransportStats> wTransportStatsList) {
        if (wTransportStatsList == null || wTransportStatsList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WTransportStats firstStats = wTransportStatsList.get(0);
        WTransportStats wTransportStats = new WTransportStats();
        wTransportStats.setStatsDate(firstStats.getStatsDate());
        List<WTransportStats> list = wTransportStatsService.listWTransportStats(wTransportStats);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("驻矿公司煤炭调运日报");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstStats.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WTransportStats stats : wTransportStatsList) {
                    if(stats.getId()!=null&&!"".equals(stats.getId())){
                        result += wTransportStatsService.updateWTransportStats(stats);
                    }else{
                        result += wTransportStatsService.saveWTransportStats(stats);
                    }

                }
                return toAjax(result);
            }

            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WTransportStats stats : wTransportStatsList) {
                result += wTransportStatsService.saveWTransportStats(stats);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改驻矿公司煤炭调运日报
     */
    @Log(title = "驻矿公司煤炭调运日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WTransportStats wTransportStats) {
        return toAjax(wTransportStatsService.updateWTransportStats(wTransportStats));
    }

    /**
     * 删除驻矿公司煤炭调运日报
     */
    @Log(title = "驻矿公司煤炭调运日报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wTransportStatsService.deleteWTransportStatsByIds(ids));
    }



    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭调运日报");
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
