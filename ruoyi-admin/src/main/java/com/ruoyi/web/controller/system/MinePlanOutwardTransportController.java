package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MinePlanOutwardTransport;
import com.ruoyi.system.service.IMinePlanOutwardTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 外运计划Controller
 *
 * @author ruoyi
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/system/transport")
public class MinePlanOutwardTransportController extends BaseController {
    @Autowired
    private IMinePlanOutwardTransportService minePlanOutwardTransportService;

    /**
     * 查询外运计划列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(MinePlanOutwardTransport minePlanOutwardTransport) {
        List<MinePlanOutwardTransport> list = minePlanOutwardTransportService.listMinePlanOutwardTransport(minePlanOutwardTransport);
        return getDataTable(list);
    }

    /**
     * 导出外运计划列表
     */
    @Log(title = "外运计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MinePlanOutwardTransport minePlanOutwardTransport) {
        List<MinePlanOutwardTransport> list = minePlanOutwardTransportService.listMinePlanOutwardTransport(minePlanOutwardTransport);
        ExcelUtil<MinePlanOutwardTransport> util = new ExcelUtil<MinePlanOutwardTransport>(MinePlanOutwardTransport.class);
        util.exportExcel(response, list, "外运计划数据");
    }

    /**
     * 获取外运计划详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(minePlanOutwardTransportService.getMinePlanOutwardTransportById(id));
    }

    /**
     * 新增外运计划
     */
    @Log(title = "外运计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MinePlanOutwardTransport minePlanOutwardTransport) {
        MinePlanOutwardTransport minePlanOut=new MinePlanOutwardTransport();
        minePlanOut.setPlanMonth(minePlanOutwardTransport.getPlanMonth());
        List<MinePlanOutwardTransport> list = minePlanOutwardTransportService.listMinePlanOutwardTransport(minePlanOut);
        if(list.size()>0){
            minePlanOutwardTransport.setId(list.get(0).getId());
            return toAjax(minePlanOutwardTransportService.updateMinePlanOutwardTransport(minePlanOutwardTransport));
        }else {
            return toAjax(minePlanOutwardTransportService.saveMinePlanOutwardTransport(minePlanOutwardTransport));
        }
    }

    /**
     * 修改外运计划
     */
    @Log(title = "外运计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MinePlanOutwardTransport minePlanOutwardTransport) {
        return toAjax(minePlanOutwardTransportService.updateMinePlanOutwardTransport(minePlanOutwardTransport));
    }

    /**
     * 删除外运计划
     */
    @Log(title = "外运计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(minePlanOutwardTransportService.deleteMinePlanOutwardTransportByIds(ids));
    }
}

