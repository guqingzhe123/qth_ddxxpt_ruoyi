package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TDailyProductionSalesStock;
import com.ruoyi.system.service.ITDailyProductionSalesStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 焦化产品产销存日报Controller
 * 煤气厂生产、外销商品及库存情况表
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@RestController
@RequestMapping("/system/dailyProductionSalesStock")
public class TDailyProductionSalesStockController extends BaseController {
    @Autowired
    private ITDailyProductionSalesStockService tDailyProductionSalesStockService;

    /**
     * 查询焦化产品产销存日报列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(TDailyProductionSalesStock tDailyProductionSalesStock) {
       List<TDailyProductionSalesStock> list = tDailyProductionSalesStockService.listTDailyProductionSalesStock(tDailyProductionSalesStock);
        if(list.size()==0){
            List<TDailyProductionSalesStock> Monthlist = tDailyProductionSalesStockService.listTDailyProductionSalesStockMonth(tDailyProductionSalesStock);
            return getDataTable(Monthlist);
        }
        return getDataTable(list);
    }

    /**
     * 导出焦化产品产销存日报列表
     */
    @Log(title = "焦化产品产销存日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDailyProductionSalesStock tDailyProductionSalesStock) {
        List<TDailyProductionSalesStock> list = tDailyProductionSalesStockService.listTDailyProductionSalesStock(tDailyProductionSalesStock);
        ExcelUtil<TDailyProductionSalesStock> util = new ExcelUtil<TDailyProductionSalesStock>(TDailyProductionSalesStock.class);
        util.exportExcel(response, list, "焦化产品产销存日报数据");
    }

    /**
     * 获取焦化产品产销存日报详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tDailyProductionSalesStockService.getTDailyProductionSalesStockById(id));
    }

    /**
     * 新增焦化产品产销存日报
     */
    @Log(title = "焦化产品产销存日报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<TDailyProductionSalesStock> tDailyProductionSalesStockList) {
        // 先检查所有数据的状态
        for (TDailyProductionSalesStock tDailyProductionSalesStock : tDailyProductionSalesStockList) {
            TDailyProductionSalesStock stock=new TDailyProductionSalesStock();
            stock.setStatDate(tDailyProductionSalesStock.getStatDate());
            List<TDailyProductionSalesStock> list = tDailyProductionSalesStockService.listTDailyProductionSalesStock(stock);
            if(list.size()>0 && list.get(0).getState()==0){
                return error("请联系局里进行驳回");
            }else {
                tDailyProductionSalesStockService.deleteTDailyProductionSalesStockByStatDate(tDailyProductionSalesStock);
            }
        }
        
        // 所有检查通过后，再执行删除和保存操作
        int count = 0;
        for (TDailyProductionSalesStock tDailyProductionSalesStock : tDailyProductionSalesStockList) {

            tDailyProductionSalesStock.setState(0);
            count += tDailyProductionSalesStockService.saveTDailyProductionSalesStock(tDailyProductionSalesStock);
        }
        return toAjax(count);
    }

    /**
     * 修改焦化产品产销存日报  退回
     */
    @Log(title = "焦化产品产销存日报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDailyProductionSalesStock tDailyProductionSalesStock) {
        return toAjax(tDailyProductionSalesStockService.updateTDailyProductionSalesStock(tDailyProductionSalesStock));
    }

    /**
     * 删除焦化产品产销存日报
     */
    @Log(title = "焦化产品产销存日报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tDailyProductionSalesStockService.deleteTDailyProductionSalesStockByIds(ids));
    }
}
