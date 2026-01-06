package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.work.CoalStockDetail;
import com.ruoyi.system.service.work.ICoalStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 各选煤厂库存煤明细表Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/coal/stock")
public class CoalStockDetailController extends BaseController {

    @Autowired
    private ICoalStockDetailService coalStockDetailService;

    /**
     * 查询库存明细列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(CoalStockDetail coalStockDetail) {
        startPage();
        List<CoalStockDetail> list = coalStockDetailService.listCoalStockDetail(coalStockDetail);
        return getDataTable(list);
    }

    /**
     * 导出库存明细列表
     */
    @Log(title = "库存煤明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CoalStockDetail coalStockDetail) {
        List<CoalStockDetail> list = coalStockDetailService.listCoalStockDetail(coalStockDetail);
        ExcelUtil<CoalStockDetail> util = new ExcelUtil<>(CoalStockDetail.class);
        util.exportExcel(response, list, "各选煤厂库存煤明细表");
    }

    /**
     * 获取详情
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(coalStockDetailService.getCoalStockDetailById(id));
    }

    /**
     * 新增库存明细
     */
    @Log(title = "库存煤明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoalStockDetail coalStockDetail) {
        return toAjax(coalStockDetailService.saveCoalStockDetail(coalStockDetail));
    }

    /**
     * 批量新增库存明细
     */
    @Log(title = "库存煤明细", businessType = BusinessType.INSERT)
    @PostMapping("/batch")
    public AjaxResult addBatch(@RequestBody List<CoalStockDetail> coalStockDetails) {

        try {
            if(coalStockDetails.size()>0){
                List<CoalStockDetail> l1=new ArrayList<>();
                CoalStockDetail coalStocak=new CoalStockDetail();
                coalStocak.setReportDate(coalStockDetails.get(0).getReportDate());
                List<CoalStockDetail> coalStockDetails1 = coalStockDetailService.listCoalStockDetail(coalStocak);
                for (CoalStockDetail coal:coalStockDetails) {
                    CoalStockDetail coalStockDetail = coalStockDetails1.stream().filter(item -> coal.getUnitName().equals(item.getUnitName())).findFirst().orElse(new CoalStockDetail());

                    if(coalStockDetail != null&&coalStockDetail.getId() !=null){
                        coal.setId(coalStockDetail.getId());
                        coalStockDetailService.updateCoalStockDetail(coal);
                    }else {
                        l1.add(coal);
                    }
                }
                if(l1.size()>0){
                    coalStockDetailService.saveCoalStockDetails(l1);
                }
            }
            return toAjax(1);
        }catch (Exception e){
            return toAjax(0);
        }
    }


    /**
     * 修改库存明细
     */
    @Log(title = "库存煤明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CoalStockDetail coalStockDetail) {
        return toAjax(coalStockDetailService.updateCoalStockDetail(coalStockDetail));
    }

    /**
     * 删除库存明细
     */
    @PreAuthorize("@ss.hasPermi('system:coal:remove')")
    @Log(title = "库存煤明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(coalStockDetailService.deleteCoalStockDetailByIds(ids));
    }
}
