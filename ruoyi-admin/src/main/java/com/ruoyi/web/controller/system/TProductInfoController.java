package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TProductInfo;
import com.ruoyi.system.service.ITProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品基础信息Controller
 * 煤气厂生产、外销商品及库存情况表  品种类型 配置 可以放弃
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@RestController
@RequestMapping("/system/productInfo")
public class TProductInfoController extends BaseController {
    @Autowired
    private ITProductInfoService tProductInfoService;

    /**
     * 查询产品基础信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(TProductInfo tProductInfo) {
        startPage();
        List<TProductInfo> list = tProductInfoService.listTProductInfo(tProductInfo);
        return getDataTable(list);
    }

    /**
     * 导出产品基础信息列表
     */
    @Log(title = "产品基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProductInfo tProductInfo) {
        List<TProductInfo> list = tProductInfoService.listTProductInfo(tProductInfo);
        ExcelUtil<TProductInfo> util = new ExcelUtil<TProductInfo>(TProductInfo.class);
        util.exportExcel(response, list, "产品基础信息数据");
    }

    /**
     * 获取产品基础信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tProductInfoService.getTProductInfoById(id));
    }

    /**
     * 新增产品基础信息
     */
    @Log(title = "产品基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProductInfo tProductInfo) {
        return toAjax(tProductInfoService.saveTProductInfo(tProductInfo));
    }

    /**
     * 修改产品基础信息
     */
    @Log(title = "产品基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProductInfo tProductInfo) {
        return toAjax(tProductInfoService.updateTProductInfo(tProductInfo));
    }

    /**
     * 删除产品基础信息
     */
    @Log(title = "产品基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tProductInfoService.deleteTProductInfoByIds(ids));
    }
}
