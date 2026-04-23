package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WLoadingStatistics;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWLoadingStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 4月份外销品种煤日报Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/statistics")
public class WLoadingStatisticsController extends BaseController {
    @Autowired
    private IWLoadingStatisticsService wLoadingStatisticsService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    /**
     * 查询4月份外销品种煤日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WLoadingStatistics wLoadingStatistics) {
        List<WLoadingStatistics> list = wLoadingStatisticsService.listWLoadingStatistics(wLoadingStatistics);
        return getDataTable(list);
    }

    /**
     * 查询4月份外销品种煤日报列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WLoadingStatistics wLoadingStatistics) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("4月份外销品种煤日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wLoadingStatistics.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WLoadingStatistics> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WLoadingStatistics> list = wLoadingStatisticsService.listWLoadingStatistics(wLoadingStatistics);
            return getDataTable(list);
        }
    }
    /**
     * 查询4月份外销品种煤日报状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("4月份外销品种煤日报");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WLoadingStatistics wLoadingStatistics=new WLoadingStatistics();
            wLoadingStatistics.setStatsDate(tiaojian.getStatsDate());
            List<WLoadingStatistics> list = wLoadingStatisticsService.listWLoadingStatistics(wLoadingStatistics);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出4月份外销品种煤日报列表
     */
    @Log(title = "4月份外销品种煤日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WLoadingStatistics wLoadingStatistics) {
        List<WLoadingStatistics> list = wLoadingStatisticsService.listWLoadingStatistics(wLoadingStatistics);
        ExcelUtil<WLoadingStatistics> util = new ExcelUtil<WLoadingStatistics>(WLoadingStatistics.class);
        util.exportExcel(response, list, "4月份外销品种煤日报数据");
    }

    /**
     * 获取4月份外销品种煤日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wLoadingStatisticsService.getWLoadingStatisticsById(id));
    }

    /**
     * 新增4月份外销品种煤日报
     */
    @Log(title = "4月份外销品种煤日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WLoadingStatistics> wLoadingStatisticsList) {
        if (wLoadingStatisticsList == null || wLoadingStatisticsList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WLoadingStatistics firstStatistics = wLoadingStatisticsList.get(0);
        WLoadingStatistics wLoading = new WLoadingStatistics();
        wLoading.setStatsDate(firstStatistics.getStatsDate());
        List<WLoadingStatistics> list = wLoadingStatisticsService.listWLoadingStatistics(wLoading);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("4月份外销品种煤日报");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstStatistics.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WLoadingStatistics wLoadingStatistics : wLoadingStatisticsList) {
                    if(wLoadingStatistics.getId()!=null&&!"".equals(wLoadingStatistics.getId())){
                        result += wLoadingStatisticsService.updateWLoadingStatistics(wLoadingStatistics);
                    }else{
                        result += wLoadingStatisticsService.saveWLoadingStatistics(wLoadingStatistics);
                    }
                }
                return toAjax(result);
            }

            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WLoadingStatistics wLoadingStatistics : wLoadingStatisticsList) {
                result += wLoadingStatisticsService.saveWLoadingStatistics(wLoadingStatistics);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改4月份外销品种煤日报
     */
    @Log(title = "4月份外销品种煤日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WLoadingStatistics wLoadingStatistics) {
        return toAjax(wLoadingStatisticsService.updateWLoadingStatistics(wLoadingStatistics));
    }

    /**
     * 删除4月份外销品种煤日报
     */
    @Log(title = "4月份外销品种煤日报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wLoadingStatisticsService.deleteWLoadingStatisticsByIds(ids));
    }

    /**
     * 退回4月份外销品种煤日报
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("4月份外销品种煤日报");
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
