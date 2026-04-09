package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TDailySales;
import com.ruoyi.system.service.ITDailySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 煤气厂销售日报（单版-含期初库存）Controller
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@RestController
@RequestMapping("/system/dailySales")
public class TDailySalesController extends BaseController {
    @Autowired
    private ITDailySalesService tDailySalesService;

    /**
     * 查询煤气厂销售日报（单版-含期初库存）列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(TDailySales tDailySales) {
        List<TDailySales> list = tDailySalesService.listTDailySales(tDailySales);
        if(list.size()==0){
            List<TDailySales> Monthlist = tDailySalesService.listTDailySalesMonth(tDailySales);
            return getDataTable(Monthlist);
        }
        return getDataTable(list);
    }

    /**
     * 导出煤气厂销售日报（单版-含期初库存）列表
     */
    @Log(title = "煤气厂销售日报（单版-含期初库存）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDailySales tDailySales) {
        List<TDailySales> list = tDailySalesService.listTDailySales(tDailySales);
        ExcelUtil<TDailySales> util = new ExcelUtil<TDailySales>(TDailySales.class);
        util.exportExcel(response, list, "煤气厂销售日报（单版-含期初库存）数据");
    }

    /**
     * 获取煤气厂销售日报（单版-含期初库存）详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tDailySalesService.getTDailySalesById(id));
    }

    /**
     * 新增煤气厂销售日报（单版-含期初库存）
     */
    @Log(title = "煤气厂销售日报（单版-含期初库存）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<TDailySales> tDailySalesList) {
        // 先检查所有数据的状态
        for (TDailySales tDailySales : tDailySalesList) {
            TDailySales stock=new TDailySales();
            stock.setStatDate(tDailySales.getStatDate());
            List<TDailySales> list = tDailySalesService.listTDailySales(stock);
            if(list.size()>0 && list.get(0).getState()==0){
                return error("请联系局里进行退回");
            }else {
                tDailySalesService.deleteTDailySalesByStatDate(stock);
            }
        }
        
        // 所有检查通过后，再执行删除和保存操作
        int count = 0;
        for (TDailySales tDailySales : tDailySalesList) {
            tDailySales.setState(0);
            count += tDailySalesService.saveTDailySales(tDailySales);
        }
        return toAjax(count);
    }

    /**
     * 修改煤气厂销售日报（单版-含期初库存）
     */
    @Log(title = "煤气厂销售日报（单版-含期初库存）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDailySales tDailySales) {
        return toAjax(tDailySalesService.updateTDailySales(tDailySales));
    }

    /**
     * 删除煤气厂销售日报（单版-含期初库存）
     */
    @Log(title = "煤气厂销售日报（单版-含期初库存）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tDailySalesService.deleteTDailySalesByIds(ids));
    }
}
