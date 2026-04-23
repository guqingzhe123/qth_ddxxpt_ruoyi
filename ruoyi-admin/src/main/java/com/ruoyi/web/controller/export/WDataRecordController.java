package com.ruoyi.web.controller.export;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.domain.export.WDataRecord;
import com.ruoyi.system.domain.ribaobaobiao.riBao;
import com.ruoyi.system.service.IMineInfoService;
import com.ruoyi.system.service.export.IWDataRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 驻矿公司煤炭发运承认车情况_详情Controller
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/system/record")
public class WDataRecordController extends BaseController {
    @Autowired
    private IWDataRecordService wDataRecordService;

    @Resource
    private IMineInfoService mineInfoService;//退回状态

    /**
     * 查询驻矿公司煤炭发运承认车情况_详情列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WDataRecord wDataRecord) {
        List<WDataRecord> list = wDataRecordService.listWDataRecord(wDataRecord);
        return getDataTable(list);
    }

    /**
     * 查询驻矿公司煤炭发运承认车情况_详情列表
     */
    @GetMapping("/Julist")
    public TableDataInfo<BaseEntity> Julist(WDataRecord wDataRecord) {
        MineInfo mineInfo = new MineInfo();
        mineInfo.setModuleName("驻矿公司煤炭发运承认车情况");
        mineInfo.setMineName("销售公司");
        mineInfo.setStatus(2L);
        mineInfo.setStatDate(wDataRecord.getStatsDate());
        List<MineInfo> mineInfos = mineInfoService.listMineInfo(mineInfo);
        if(mineInfos.size()>0){
            List<WDataRecord> list=new ArrayList<>();
            return getDataTable(list);
        }else {
            List<WDataRecord> list = wDataRecordService.listWDataRecord(wDataRecord);
            return getDataTable(list);
        }
    }
    /**
     * 查询驻矿公司煤炭发运承认车情况_详情状态
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
            WDataRecord wDataRecord=new WDataRecord();
            wDataRecord.setStatsDate(tiaojian.getStatsDate());
            List<WDataRecord> list = wDataRecordService.listWDataRecord(wDataRecord);
            if(list.size()>0){
                return "已上报";
            }else {
                return "未上报";
            }
        }
    }

    /**
     * 导出驻矿公司煤炭发运承认车情况_详情列表
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WDataRecord wDataRecord) {
        List<WDataRecord> list = wDataRecordService.listWDataRecord(wDataRecord);
        ExcelUtil<WDataRecord> util = new ExcelUtil<WDataRecord>(WDataRecord.class);
        util.exportExcel(response, list, "驻矿公司煤炭发运承认车情况_详情数据");
    }

    /**
     * 获取驻矿公司煤炭发运承认车情况_详情详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(wDataRecordService.getWDataRecordById(id));
    }

    /**
     * 新增驻矿公司煤炭发运承认车情况_详情
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WDataRecord> wDataRecordList) {
        if (wDataRecordList == null || wDataRecordList.isEmpty()) {
            return AjaxResult.error("数据不能为空");
        }
        int result = 0;
        for (WDataRecord wDataRecord : wDataRecordList) {
            if(wDataRecord.getId()!=null&&!"".equals(wDataRecord.getId())){
                result += wDataRecordService.updateWDataRecord(wDataRecord);
            }else{
                result += wDataRecordService.saveWDataRecord(wDataRecord);
            }
        }
        return toAjax(result);
    }

    /**
     * 修改驻矿公司煤炭发运承认车情况_详情
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WDataRecord wDataRecord) {
        return toAjax(wDataRecordService.updateWDataRecord(wDataRecord));
    }

    /**
     * 删除驻矿公司煤炭发运承认车情况_详情
     */
    @Log(title = "驻矿公司煤炭发运承认车情况_详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(wDataRecordService.deleteWDataRecordByIds(ids));
    }



    /**
     * 退回驻矿公司煤炭发运承认车情况_详情
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
