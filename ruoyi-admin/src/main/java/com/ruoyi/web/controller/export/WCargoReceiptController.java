package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WCargoReceipt;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWCargoReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 七矿焦炭铁路外运承认车情况Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/receipt")
public class WCargoReceiptController extends BaseController {
    @Autowired
    private IWCargoReceiptService wCargoReceiptService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态
    /**
     * 查询七矿焦炭铁路外运承认车情况列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WCargoReceipt wCargoReceipt) {
        List<WCargoReceipt> list = wCargoReceiptService.listWCargoReceipt(wCargoReceipt);
        return getDataTable(list);
    }

    /**
     * 查询七矿焦炭铁路外运承认车情况列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WCargoReceipt wCargoReceipt) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿焦炭铁路外运承认车情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wCargoReceipt.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WCargoReceipt> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WCargoReceipt> list = wCargoReceiptService.listWCargoReceipt(wCargoReceipt);
            return getDataTable(list);
        }
    }
    /**
     * 查询七矿焦炭铁路外运承认车情况状态
     */
    @GetMapping("/getState")
    public String getState(riBao tiaojian) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿焦炭铁路外运承认车情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(tiaojian.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            return "已退回";
        }else {
            WCargoReceipt wCargoReceipt=new WCargoReceipt();
            wCargoReceipt.setStatsDate(tiaojian.getStatsDate());
            List<WCargoReceipt> list = wCargoReceiptService.listWCargoReceipt(wCargoReceipt);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }
    /**
     * 导出七矿焦炭铁路外运承认车情况列表
     */
    @Log(title = "七矿焦炭铁路外运承认车情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WCargoReceipt wCargoReceipt) {
        List<WCargoReceipt> list = wCargoReceiptService.listWCargoReceipt(wCargoReceipt);
        ExcelUtil<WCargoReceipt> util = new ExcelUtil<WCargoReceipt>(WCargoReceipt.class);
        util.exportExcel(response, list, "七矿焦炭铁路外运承认车情况数据");
    }

    /**
     * 获取七矿焦炭铁路外运承认车情况详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wCargoReceiptService.getWCargoReceiptById(id));
    }

    /**
     * 新增七矿焦炭铁路外运承认车情况
     */
    @Log(title = "七矿焦炭铁路外运承认车情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WCargoReceipt> wCargoReceiptList) {
        if (wCargoReceiptList == null || wCargoReceiptList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        
        // 取第一条数据的日期进行校验
        WCargoReceipt firstReceipt = wCargoReceiptList.get(0);
        WCargoReceipt wCargo = new WCargoReceipt();
        wCargo.setStatsDate(firstReceipt.getStatsDate());
        List<WCargoReceipt> list = wCargoReceiptService.listWCargoReceipt(wCargo);
        if(list.size()>0){
            MineInfo mineInfo = new MineInfo();
            mineInfo.setModuleName("七矿焦炭铁路外运承认车情况");
            mineInfo.setMineName("销售公司");
            mineInfo.setStatus(2L);
            mineInfo.setStatDate(firstReceipt.getStatsDate());
            List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);

            if(mineInfos.size()>0){
                mineInfoService.deleteMineInfoByDate(mineInfo);
                // 已退回状态，批量更新
                int result = 0;
                for (WCargoReceipt wCargoReceipt : wCargoReceiptList) {
                    if(wCargoReceipt.getId()!=null&&!"".equals(wCargoReceipt.getId())){
                        result += wCargoReceiptService.updateWCargoReceipt(wCargoReceipt);
                    }else{
                        result += wCargoReceiptService.saveWCargoReceipt(wCargoReceipt);
                    }
                }
                return toAjax(result);
            }
            return AjaxResult.error("请联系局里进行退回");
        }else {
            // 未上报状态，批量新增
            int result = 0;
            for (WCargoReceipt wCargoReceipt : wCargoReceiptList) {
                result += wCargoReceiptService.saveWCargoReceipt(wCargoReceipt);
            }
            return toAjax(result);
        }
        

    }

    /**
     * 修改七矿焦炭铁路外运承认车情况
     */
    @Log(title = "七矿焦炭铁路外运承认车情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WCargoReceipt wCargoReceipt) {
        return toAjax(wCargoReceiptService.updateWCargoReceipt(wCargoReceipt));
    }

    /**
     * 删除七矿焦炭铁路外运承认车情况
     */
    @Log(title = "七矿焦炭铁路外运承认车情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wCargoReceiptService.deleteWCargoReceiptByIds(ids));
    }



    /**
     * 退回原煤去向对照表
     */
    @GetMapping("/updateState")
    public AjaxResult updateState(riBao tiaojian){
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("七矿焦炭铁路外运承认车情况");
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
