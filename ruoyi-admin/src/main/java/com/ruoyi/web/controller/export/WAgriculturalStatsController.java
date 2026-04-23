package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WAgriculturalStats;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWAgriculturalStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 外销商品煤销量情况Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/exportCoal")
public class WAgriculturalStatsController extends BaseController {
    @Autowired
    private IWAgriculturalStatsService wAgriculturalStatsService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    /**
     * 查询外销商品煤销量情况列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WAgriculturalStats wAgriculturalStats) {
        List<WAgriculturalStats> list = wAgriculturalStatsService.listWAgriculturalStats(wAgriculturalStats);
        return getDataTable(list);
    }

    /**
     * 查询外销商品煤销量情况列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WAgriculturalStats wAgriculturalStats) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤销量情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wAgriculturalStats.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WAgriculturalStats> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WAgriculturalStats> list = wAgriculturalStatsService.listWAgriculturalStats(wAgriculturalStats);
            return getDataTable(list);
        }
    }
    /**
     * 查询外销商品煤销量情况状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤销量情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WAgriculturalStats wAgriculturalStats=new WAgriculturalStats();
            wAgriculturalStats.setStatsDate(tiaojian.getStatsDate());
            List<WAgriculturalStats> list = wAgriculturalStatsService.listWAgriculturalStats(wAgriculturalStats);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出外销商品煤销量情况列表
     */
    @Log(title = "外销商品煤销量情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WAgriculturalStats wAgriculturalStats) {
        List<WAgriculturalStats> list = wAgriculturalStatsService.listWAgriculturalStats(wAgriculturalStats);
        ExcelUtil<WAgriculturalStats> util = new ExcelUtil<WAgriculturalStats>(WAgriculturalStats.class);
        util.exportExcel(response, list, "外销商品煤销量情况数据");
    }

    /**
     * 获取外销商品煤销量情况详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wAgriculturalStatsService.getWAgriculturalStatsById(id));
    }

    /**
     * 新增外销商品煤销量情况
     */
    @Log(title = "外销商品煤销量情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WAgriculturalStats> wAgriculturalStatsList) {
        if (wAgriculturalStatsList == null || wAgriculturalStatsList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WAgriculturalStats firstStats = wAgriculturalStatsList.get(0);
        WAgriculturalStats wAgriculturalStats = new WAgriculturalStats();
        wAgriculturalStats.setStatsDate(firstStats.getStatsDate());
        List<WAgriculturalStats> list = wAgriculturalStatsService.listWAgriculturalStats(wAgriculturalStats);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("外销商品煤销量情况");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstStats.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WAgriculturalStats stats : wAgriculturalStatsList) {
                    if(stats.getId()!=null&&!"".equals(stats.getId())){
                        result += wAgriculturalStatsService.updateWAgriculturalStats(stats);
                    }else{
                        result += wAgriculturalStatsService.saveWAgriculturalStats(stats);
                    }
                }
                return toAjax(result);
            }else {
                return AjaxResult.error("请联系局里进行退回");
            }
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WAgriculturalStats stats : wAgriculturalStatsList) {
                result += wAgriculturalStatsService.saveWAgriculturalStats(stats);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改外销商品煤销量情况
     */
    @Log(title = "外销商品煤销量情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WAgriculturalStats wAgriculturalStats) {
        return toAjax(wAgriculturalStatsService.updateWAgriculturalStats(wAgriculturalStats));
    }

    /**
     * 删除外销商品煤销量情况
     */
    @Log(title = "外销商品煤销量情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wAgriculturalStatsService.deleteWAgriculturalStatsByIds(ids));
    }



    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("外销商品煤销量情况");
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
