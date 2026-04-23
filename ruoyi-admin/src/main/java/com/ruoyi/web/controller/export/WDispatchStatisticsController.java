package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WDispatchStatistics;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWDispatchStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 七矿公司外采煤炭日报Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/externalCoalMining")
public class WDispatchStatisticsController extends BaseController {
    @Autowired
    private IWDispatchStatisticsService wDispatchStatisticsService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态
    /**
     * 查询七矿公司外采煤炭日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WDispatchStatistics wDispatchStatistics) {
        List<WDispatchStatistics> list = wDispatchStatisticsService.listWDispatchStatistics(wDispatchStatistics);
        return getDataTable(list);
    }

    /**
     * 查询七矿公司外采煤炭日报列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WDispatchStatistics wDispatchStatistics) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿公司外采煤炭日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wDispatchStatistics.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WDispatchStatistics> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WDispatchStatistics> list = wDispatchStatisticsService.listWDispatchStatistics(wDispatchStatistics);
            return getDataTable(list);
        }
    }
    /**
     * 查询七矿公司外采煤炭日报状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿公司外采煤炭日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WDispatchStatistics wDispatchStatistics=new WDispatchStatistics();
            wDispatchStatistics.setStatsDate(tiaojian.getStatsDate());
            List<WDispatchStatistics> list = wDispatchStatisticsService.listWDispatchStatistics(wDispatchStatistics);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出七矿公司外采煤炭日报列表
     */
    @Log(title = "七矿公司外采煤炭日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WDispatchStatistics wDispatchStatistics) {
        List<WDispatchStatistics> list = wDispatchStatisticsService.listWDispatchStatistics(wDispatchStatistics);
        ExcelUtil<WDispatchStatistics> util = new ExcelUtil<WDispatchStatistics>(WDispatchStatistics.class);
        util.exportExcel(response, list, "七矿公司外采煤炭日报数据");
    }

    /**
     * 获取七矿公司外采煤炭日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wDispatchStatisticsService.getWDispatchStatisticsById(id));
    }

    /**
     * 新增七矿公司外采煤炭日报
     */
    @Log(title = "七矿公司外采煤炭日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WDispatchStatistics> wDispatchStatisticsList) {
        if (wDispatchStatisticsList == null || wDispatchStatisticsList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WDispatchStatistics firstStatistics = wDispatchStatisticsList.get(0);
        WDispatchStatistics wDispatch = new WDispatchStatistics();
        wDispatch.setStatsDate(firstStatistics.getStatsDate());
        List<WDispatchStatistics> list = wDispatchStatisticsService.listWDispatchStatistics(wDispatch);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("七矿公司外采煤炭日报");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstStatistics.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WDispatchStatistics wDispatchStatistics : wDispatchStatisticsList) {
                    if(wDispatchStatistics.getId()!=null&&!"".equals(wDispatchStatistics.getId())){
                        result += wDispatchStatisticsService.updateWDispatchStatistics(wDispatchStatistics);
                    }else{
                        result += wDispatchStatisticsService.saveWDispatchStatistics(wDispatchStatistics);
                    }
                }
                return toAjax(result);
            }

            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WDispatchStatistics wDispatchStatistics : wDispatchStatisticsList) {
                result += wDispatchStatisticsService.saveWDispatchStatistics(wDispatchStatistics);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改七矿公司外采煤炭日报
     */
    @Log(title = "七矿公司外采煤炭日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WDispatchStatistics wDispatchStatistics) {
        return toAjax(wDispatchStatisticsService.updateWDispatchStatistics(wDispatchStatistics));
    }

    /**
     * 删除七矿公司外采煤炭日报
     */
    @Log(title = "七矿公司外采煤炭日报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wDispatchStatisticsService.deleteWDispatchStatisticsByIds(ids));
    }

    /**
     * 退回七矿公司外采煤炭日报
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿公司外采煤炭日报");
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
