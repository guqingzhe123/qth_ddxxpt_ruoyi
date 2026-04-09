package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TProduct;
import com.ruoyi.system.service.ITProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品基础Controller
 * 煤气厂产品销售日报表  产品基础信息（放弃）
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@RestController
@RequestMapping("/system/product")
public class TProductController extends BaseController {
    @Autowired
    private ITProductService tProductService;

    /**
     * 查询产品基础列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(TProduct tProduct) {
        startPage();
        List<TProduct> list = tProductService.listTProduct(tProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品基础列表
     */
    @Log(title = "产品基础", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProduct tProduct) {
        List<TProduct> list = tProductService.listTProduct(tProduct);
        ExcelUtil<TProduct> util = new ExcelUtil<TProduct>(TProduct.class);
        util.exportExcel(response, list, "产品基础数据");
    }

    /**
     * 获取产品基础详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tProductService.getTProductById(id));
    }

    /**
     * 新增产品基础
     */
    @Log(title = "产品基础", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProduct tProduct) {
        return toAjax(tProductService.saveTProduct(tProduct));
    }

    /**
     * 修改产品基础
     */
    @Log(title = "产品基础", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProduct tProduct) {
        return toAjax(tProductService.updateTProduct(tProduct));
    }

    /**
     * 删除产品基础
     */
    @Log(title = "产品基础", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tProductService.deleteTProductByIds(ids));
    }
}
