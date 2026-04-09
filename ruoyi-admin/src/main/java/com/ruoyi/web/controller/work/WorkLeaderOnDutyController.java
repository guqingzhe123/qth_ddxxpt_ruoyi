package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.work.WorkLeaderOnDuty;
import com.ruoyi.system.domain.work.WorkLeaderOnDutyList;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.work.IWorkLeaderOnDutyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）Controller
 *
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkLeaderOnDutyController")
public class WorkLeaderOnDutyController extends BaseController {
    @Autowired
    private IWorkLeaderOnDutyService workLeaderOnDutyService;
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    /**
     * 查询领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkLeaderOnDuty workLeaderOnDuty) {
        List<WorkLeaderOnDuty> list = workLeaderOnDutyService.listWorkLeaderOnDuty(workLeaderOnDuty);
        return getDataTable(list);
    }

    @Log(title = "领导带班信息（按日期记录各单位三个班次的带班领导及排班状态）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkLeaderOnDuty workLeaderOnDuty) {
        WorkLeaderOnDuty duty=new WorkLeaderOnDuty();
        duty.setDutyDate(workLeaderOnDuty.getDutyDate());
        duty.setUnitCode(workLeaderOnDuty.getUnitCode());
        List<WorkLeaderOnDuty> list = workLeaderOnDutyService.listWorkLeaderOnDuty(workLeaderOnDuty);
        if (list.size() > 0) {
            if(list.get(0).getStatus().equals("0")){
                return error("请联系局里进行驳回");
            }
        }

        return toAjax(workLeaderOnDutyService.saveWorkLeaderOnDuty(workLeaderOnDuty));
    }

    @Operation(summary = "局端查询")
    @PostMapping("/monthCalendar")
    @Anonymous
    public AjaxResult monthCalendar(@RequestBody WorkLeaderOnDuty workLeaderOnDuty) {
        workLeaderOnDuty.setDutyDate(DateUtils.getLastDayOfMonth(workLeaderOnDuty.getDutyDate()));
        List<WorkLeaderOnDuty> workLeaderOnDuties = workLeaderOnDutyService.selectWorkLeaderOnDutyALLList(workLeaderOnDuty);
        List<WorkLeaderOnDutyList> 矿场=new ArrayList<>();
        int day = DateUtils.getDayFromString(DateUtils.returnDateRange(DateUtils.getLastDayOfMonth(workLeaderOnDuty.getDutyDate())), "yyyy-MM-dd");
        String 月份 = DateUtils.returnDateDay(workLeaderOnDuty.getDutyDate());
        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);
        for (MiningAreaCategory mining:miningAreaCategories) {
            WorkLeaderOnDutyList work=new WorkLeaderOnDutyList();
            List<String> one=new ArrayList<>();
            List<String> two=new ArrayList<>();
            List<String> three=new ArrayList<>();
            work.setUnitName(mining.getAreaName());
            work.setUnitCode(mining.getAreaCode());
            for (int i = 1; i <= day; i++) {
                String 日期 = DateUtils.returnDateRange(DateUtils.parseDate(月份+"-"+i));
                WorkLeaderOnDuty 入井人员 = workLeaderOnDuties.stream().filter(item -> mining.getAreaCode().equals(item.getUnitCode()))
                        .filter(item -> {
                            String 人员值班日期 = DateUtils.returnDateRange(item.getDutyDate());
                            return 日期.equals(人员值班日期);
                        }).findFirst().orElse(new WorkLeaderOnDuty());
                if(入井人员!=null){

                }
                if(入井人员.getStatus()!=null&&入井人员.getStatus().equals("0")){
                    one.add(入井人员.getLeaderShift1());
                    two.add(入井人员.getLeaderShift2());
                    three.add(入井人员.getLeaderShift3());
                }else {
                    one.add(null);
                    two.add(null);
                    three.add(null);
                }
            }

            work.setOne(one);
            work.setTwo(two);
            work.setThree(three);
            矿场.add(work);
        }
        return AjaxResult.success(矿场);
    }


    /**
     * 退回带班领导
     */
    @Log(title = "退回带班领导", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkLeaderOnDuty workLeaderOnDuty) {
        WorkLeaderOnDuty duty=new WorkLeaderOnDuty();
        duty.setDutyDate(workLeaderOnDuty.getDutyDate());
        duty.setUnitCode(workLeaderOnDuty.getUnitCode());
        List<WorkLeaderOnDuty> list = workLeaderOnDutyService.listWorkLeaderOnDuty(workLeaderOnDuty);
        if (list.size() > 0) {
            if(list.get(0).getStatus().equals("0")){
                list.get(0).setStatus("2");
                return toAjax(workLeaderOnDutyService.updateWorkLeaderOnDuty(list.get(0)));
            }
        }
        return toAjax(0 );
    }

}

