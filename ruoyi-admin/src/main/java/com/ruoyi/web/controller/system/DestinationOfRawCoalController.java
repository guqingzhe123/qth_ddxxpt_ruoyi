package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DestinationOfRawCoal;
import com.ruoyi.system.service.IDestinationOfRawCoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 原煤去向录入表Controller
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@RestController
@RequestMapping("/system/coal")
public class DestinationOfRawCoalController extends BaseController {
    @Autowired
    private IDestinationOfRawCoalService destinationOfRawCoalService;

    /**
     * 查询原煤去向/各矿日实际产量录入列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(DestinationOfRawCoal destinationOfRawCoal) {
        List<DestinationOfRawCoal> list = destinationOfRawCoalService.listDestinationOfRawCoal(destinationOfRawCoal);
        return getDataTable(list);
    }

    /**
     * 导出原煤去向/各矿日实际产量录入列表
     */
    @Log(title = "原煤去向/各矿日实际产量录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DestinationOfRawCoal destinationOfRawCoal) {
        List<DestinationOfRawCoal> list = destinationOfRawCoalService.listDestinationOfRawCoal(destinationOfRawCoal);
        ExcelUtil<DestinationOfRawCoal> util = new ExcelUtil<DestinationOfRawCoal>(DestinationOfRawCoal.class);
        util.exportExcel(response, list, "原煤去向/各矿日实际产量录入数据");
    }

    /**
     * 获取原煤去向/各矿日实际产量录入详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(destinationOfRawCoalService.getDestinationOfRawCoalById(id));
    }

    /**
     * 新增原煤去向/各矿日实际产量录入
     */
    @Log(title = "原煤去向/各矿日实际产量录入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<DestinationOfRawCoal> destinationOfRawCoal) {
        return toAjax(destinationOfRawCoalService.saveDestinationOfRawCoal(destinationOfRawCoal));
    }

    /**
     * 修改原煤去向/各矿日实际产量录入
     */
    @Log(title = "原煤去向/各矿日实际产量录入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DestinationOfRawCoal destinationOfRawCoal) {
        return toAjax(destinationOfRawCoalService.updateDestinationOfRawCoal(destinationOfRawCoal));
    }
    /**
     * 驳回原煤去向
     */
    @Log(title = "原煤去向/各矿日实际产量录入", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult editReject(@RequestBody DestinationOfRawCoal destinationOfRawCoal) {
        List<DestinationOfRawCoal> list = destinationOfRawCoalService.listDestinationOfRawCoal(destinationOfRawCoal);
        if(list.size()>0){
            DestinationOfRawCoal coal=list.get(0);
            coal.setIsDeleted(2);
            return toAjax(destinationOfRawCoalService.updateDestinationOfRawCoal(coal));
        }
        return toAjax(1);
    }
    /**
     * 删除原煤去向/各矿日实际产量录入
     */
    @Log(title = "原煤去向/各矿日实际产量录入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(destinationOfRawCoalService.deleteDestinationOfRawCoalByIds(ids));
    }
}
