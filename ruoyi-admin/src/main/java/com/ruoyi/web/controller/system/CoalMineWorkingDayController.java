package com.ruoyi.web.controller.system;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.NumUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubWashCoalPlanPO;
import com.ruoyi.system.domain.CoalMineWorkingDay;
import com.ruoyi.system.domain.MinePlanDay;
import com.ruoyi.system.mapper.BaoBiao.MinePlanMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMinePlanMapper;
import com.ruoyi.system.mapper.BaoBiao.SubWashCoalPlanMapper;
import com.ruoyi.system.mapper.MinePlanDayMapper;
import com.ruoyi.system.service.ICoalMineWorkingDayService;
import com.ruoyi.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * 工作日Controller
 *
 * @author ruoyi
 * @date 2025-11-28
 */
@RestController
@RequestMapping("/system/day")
public class CoalMineWorkingDayController extends BaseController {
    @Autowired
    private ICoalMineWorkingDayService coalMineWorkingDayService;

    @Resource
    private MinePlanMapper planMapper;//矿计划
    @Resource
    private SubMinePlanMapper subMapper;//矿计划子表
    @Resource
    private MinePlanDayMapper subMapperDay;//矿每个队组计划子表
    @Resource
    private SubWashCoalPlanMapper subPlanMapper;//煤场计划子表
    @Autowired
    private ISysDeptService deptService;//部门对象

    @FunctionalInterface
    public interface WorkingDayPostProcessor {
        void process(List<CoalMineWorkingDay> coalMineWorkingDay, int workingDays, Date firstDayOfMonth, int leixing);
    }

    /**
     * 查询工作日列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(CoalMineWorkingDay coalMineWorkingDay) {
        List<CoalMineWorkingDay> list = coalMineWorkingDayService.listCoalMineWorkingDay(coalMineWorkingDay);
        if (list.size() == 0) {
            List<CoalMineWorkingDay> coalDays = new ArrayList<>();
            int daysInMonth = DateUtils.getDaysInMonth(coalMineWorkingDay.getWorkingMonth());
            for (int i = 1; i <= daysInMonth; i++) {
                CoalMineWorkingDay days = new CoalMineWorkingDay();
                days.setUnitName(coalMineWorkingDay.getUnitName());
                days.setUnitCode(coalMineWorkingDay.getUnitCode());
                days.setWorkingMonth(coalMineWorkingDay.getWorkingMonth());
                days.setWorkingDays((long) i);
                days.setWhetherWorking(1L);
                days.setProductionPlan(0L);
                days.setProductionObjective(0L);
                days.setExplorationPlan(0L);
                days.setExplorationObjective(0L);
                days.setFootagePlan(0L);
                days.setFootageObjective(0L);
                coalDays.add(days);
            }
            coalMineWorkingDayService.saveCoalMineWorkingDay(coalDays);
            List<CoalMineWorkingDay> list1 = coalMineWorkingDayService.listCoalMineWorkingDay(coalMineWorkingDay);
            return getDataTable(list1);
        }

        return getDataTable(list);
    }

    /**
     * 查询工作日列表
     */
    @GetMapping("/listNum")
    public int listNum(CoalMineWorkingDay coalMineWorkingDay) {
        coalMineWorkingDay.setWhetherWorking(1L);
        List<CoalMineWorkingDay> list = coalMineWorkingDayService.listCoalMineWorkingDay(coalMineWorkingDay);
        if (list.size() == 0) {
            List<CoalMineWorkingDay> coalDays = new ArrayList<>();
            int daysInMonth = DateUtils.getDaysInMonth(coalMineWorkingDay.getWorkingMonth());
            for (int i = 1; i <= daysInMonth; i++) {
                CoalMineWorkingDay days = new CoalMineWorkingDay();
                days.setUnitName(coalMineWorkingDay.getUnitName());
                days.setUnitCode(coalMineWorkingDay.getUnitCode());
                days.setWorkingMonth(coalMineWorkingDay.getWorkingMonth());
                days.setWorkingDays((long) i);
                days.setWhetherWorking(1L);
                days.setProductionPlan(0L);
                days.setProductionObjective(0L);
                days.setExplorationPlan(0L);
                days.setExplorationObjective(0L);
                days.setFootagePlan(0L);
                days.setFootageObjective(0L);
                coalDays.add(days);
            }
            coalMineWorkingDayService.saveCoalMineWorkingDay(coalDays);
            return daysInMonth;
        }
        return list.size();
    }


    /**
     * 导出工作日列表
     */
    @Log(title = "工作日", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CoalMineWorkingDay coalMineWorkingDay) {
        List<CoalMineWorkingDay> list = coalMineWorkingDayService.listCoalMineWorkingDay(coalMineWorkingDay);
        ExcelUtil<CoalMineWorkingDay> util = new ExcelUtil<CoalMineWorkingDay>(CoalMineWorkingDay.class);
        util.exportExcel(response, list, "工作日数据");
    }

    /**
     * 获取工作日详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(coalMineWorkingDayService.getCoalMineWorkingDayById(id));
    }

    /**
     * 新增工作日
     */
    @Log(title = "工作日", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<CoalMineWorkingDay> coalMineWorkingDay) {
        for (CoalMineWorkingDay day : coalMineWorkingDay) {
            day.setWhetherWorking(1L);
            day.setProductionPlan(0L);
            day.setProductionObjective(0L);
            day.setExplorationPlan(0L);
            day.setExplorationObjective(0L);
            day.setFootagePlan(0L);
            day.setFootageObjective(0L);
        }
        return toAjax(coalMineWorkingDayService.saveCoalMineWorkingDay(coalMineWorkingDay));
    }

    /**
     * 修改工作日
     */
    @Log(title = "工作日", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<CoalMineWorkingDay> coalMineWorkingDay) {
        try {
            int leixing = 0;
            SysDept sysDept = deptService.selectDeptById(SecurityUtils.getDeptId());
            if (sysDept != null) {
                SysDept sysDeptParent = deptService.selectDeptById(sysDept.getParentId());
                if (sysDeptParent != null) {
                    if (sysDeptParent.getDeptName().equals("全部厂")) {
                        leixing = 1;
                    }
                    if (sysDeptParent.getDeptName().equals("全部矿")) {
                        leixing = 2;
                    }
                }
            }
            //工作日
            Stream<CoalMineWorkingDay> coalMineWorkingDayStream = coalMineWorkingDay.stream().filter(item -> 1 == item.getWhetherWorking());
            int 工作天数 = (int) coalMineWorkingDayStream.count();
            Date firstDayOfMonth = DateUtils.getFirstDayOfMonth(DateUtils.parseDate(coalMineWorkingDay.get(0).getWorkingMonth()));

            // 使用函数式接口处理不同类型的工作日更新逻辑
            if(leixing == 2){
                executePostProcessing(coalMineWorkingDay, 工作天数, firstDayOfMonth, leixing, this::processMineWorkingDay);
            }else if(leixing == 1){
                executePostProcessing(coalMineWorkingDay, 工作天数, firstDayOfMonth, leixing, this::processWashPlantWorkingDay);
            }else {
                executePostProcessing(coalMineWorkingDay, 工作天数, firstDayOfMonth, leixing, this::processDefaultWorkingDay);
            }

        } catch (Exception e) {
            return toAjax(0);
        }
        return toAjax(1);
    }

    // 通用的后处理执行方法
    private void executePostProcessing(List<CoalMineWorkingDay> coalMineWorkingDay, int workingDays,
                                       Date firstDayOfMonth, int leixing, WorkingDayPostProcessor processor) {
        try {
            processor.process(coalMineWorkingDay, workingDays, firstDayOfMonth, leixing);
        } catch (Exception e) {
            System.err.println("后处理执行失败: " + e.getMessage());
        }
    }
    // 处理煤矿工作日逻辑
    private void processMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay, int 工作天数,Date firstDayOfMonth, int leixing) {
        if (leixing == 2) {

            MinePlanPO mine1 = new MinePlanPO();
            mine1.setPlanMonth(firstDayOfMonth);
            mine1.setMineCategory("全矿");
            List<MinePlanPO> planP1 = planMapper.selectList(mine1);
            if(planP1.size()>0){
                for (MinePlanPO p0:planP1) {
                    List<SubMinePlanPO> subMinePlanPOS = subMapper.selectByPlanId(p0.getId());
                    SubMinePlanPO subMinePlanPO = subMinePlanPOS.stream().filter(item -> coalMineWorkingDay.get(0).getUnitCode().equals(item.getUnitCode())).findFirst().orElse(new SubMinePlanPO());
                    if(ObjectUtil.isEmpty(subMinePlanPO.getId())){
                        continue;
                    }
                    if (工作天数 > 0) {
                        long dayTarget = Math.round(subMinePlanPO.getMonthTarget().doubleValue() / 工作天数);
                        long dayPlan = Math.round(subMinePlanPO.getMonthPlan().doubleValue() / 工作天数);
                        subMinePlanPO.setDayTarget(BigDecimal.valueOf(dayTarget));
                        subMinePlanPO.setDayPlan(BigDecimal.valueOf(dayPlan));
                        subMapper.updateDay(subMinePlanPO);
                    }
                }

            }


            MinePlanPO mine = new MinePlanPO();
            mine.setPlanMonth(firstDayOfMonth);
            mine.setUnitCode(coalMineWorkingDay.get(0).getUnitCode());
            List<MinePlanPO> planPO = planMapper.selectList(mine);

            SubMinePlanPO 生产 = new SubMinePlanPO();
            SubMinePlanPO 开拓 = new SubMinePlanPO();
            SubMinePlanPO 进尺 = new SubMinePlanPO();

            for (MinePlanPO po : planPO) {
                List<SubMinePlanPO> subMinePlanPOS = subMapper.selectByPlanId(po.getId());
                List<MinePlanDay> minePlanDay = new ArrayList<>();

                MinePlanDay mineDay = new MinePlanDay();
                mineDay.setPlanType(po.getPlanType());
                mineDay.setAreaName(po.getUnitName());
                mineDay.setPlanMonth(coalMineWorkingDay.get(0).getWorkingMonth());
//                mineDay.setUnitCode(subpo.getUnitCode());
//                mineDay.setUnitName(subpo.getUnitName());
                List<MinePlanDay> dayList = subMapperDay.selectMinePlanDayList(mineDay);

                for (SubMinePlanPO subpo : subMinePlanPOS) {
                    if ("计划总量".equals(subpo.getUnitName())) {
                        if ("生产".equals(po.getPlanType())) {
                            生产 = subpo;
                        }
                        if ("开拓".equals(po.getPlanType())) {
                            开拓 = subpo;
                        }
                        if ("进尺".equals(po.getPlanType())) {
                            进尺 = subpo;
                        }
                    }
                    subpo.setDayPlan(NumUtils.divideBy5WithTwoDecimals(subpo.getMonthPlan(), 工作天数));
                    subpo.setDayTarget(NumUtils.divideBy5WithTwoDecimals(subpo.getMonthTarget(), 工作天数));
                    subMapper.updateDay(subpo);
                    List<Long> 月计划 = NumUtils.splitAverageInterleaved(subpo.getMonthPlan(), 工作天数);
                    List<Long> 月目标 = NumUtils.splitAverageInterleaved(subpo.getMonthTarget(), 工作天数);

                    int num = 0;

                    List<MinePlanDay> minePlanDayList = dayList.stream()
                            .filter(item -> subpo.getUnitCode().equals(item.getUnitCode()))
                            .filter(item -> subpo.getUnitName().equals(item.getUnitName()))
                            .collect(ArrayList::new, (list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
                    for (MinePlanDay day : minePlanDayList) {
                        CoalMineWorkingDay workingDay = coalMineWorkingDay.stream().filter(item -> (long) day.getPlanDay() == item.getWorkingDays()).findFirst().orElse(null);
                        if (workingDay != null && workingDay.getWhetherWorking() == 1) {
                            day.setDayPlan(Math.toIntExact(月计划.get(num)));
                            day.setDayTarget(Math.toIntExact(月目标.get(num)));
                            num++;
                        } else {
                            day.setDayPlan(0);
                            day.setDayTarget(0);
                        }
                        minePlanDay.add(day);
                        if (minePlanDay.size() >= 50) {
                            subMapperDay.batchUpdateMinePlanDay(minePlanDay);
                            minePlanDay.clear();
                        }
                    }
                }
                if (minePlanDay.size() > 0) {
                    subMapperDay.batchUpdateMinePlanDay(minePlanDay);
                }
                po.setWorkDaysInMonth(工作天数);
                planMapper.update(po);
            }

            List<Long> 生产月计划 = new ArrayList<>();
            if (生产.getMonthPlan() != null) {
                生产月计划 = NumUtils.splitAverageInterleaved(生产.getMonthPlan(), 工作天数);
            }
            List<Long> 生产月目标 = new ArrayList<>();
            if (生产.getMonthTarget() != null) {
                生产月目标 = NumUtils.splitAverageInterleaved(生产.getMonthTarget(), 工作天数);
            }
            List<Long> 开拓月计划 = new ArrayList<>();
            if (开拓.getMonthPlan() != null) {
                开拓月计划 = NumUtils.splitAverageInterleaved(开拓.getMonthPlan(), 工作天数);
            }
            List<Long> 开拓月目标 = new ArrayList<>();
            if (开拓.getMonthTarget() != null) {
                开拓月目标 = NumUtils.splitAverageInterleaved(开拓.getMonthTarget(), 工作天数);
            }
            List<Long> 进尺月计划 = new ArrayList<>();
            if (进尺.getMonthPlan() != null) {
                进尺月计划 = NumUtils.splitAverageInterleaved(进尺.getMonthPlan(), 工作天数);
            }
            List<Long> 进尺月目标 = new ArrayList<>();
            if (进尺.getMonthTarget() != null) {
                进尺月目标 = NumUtils.splitAverageInterleaved(进尺.getMonthTarget(), 工作天数);
            }

            int num = 0;
            List<CoalMineWorkingDay> coalMineWorkingDay1 = new ArrayList<>();
            for (CoalMineWorkingDay coalDay1 : coalMineWorkingDay) {
                if (coalDay1.getWhetherWorking() == 1) {
                    if (生产月计划.size() > 0) {
                        coalDay1.setProductionPlan(生产月计划.get(num));
                    } else {
                        coalDay1.setProductionPlan(0L);
                    }
                    if (生产月目标.size() > 0) {
                        coalDay1.setProductionObjective(生产月目标.get(num));
                    } else {
                        coalDay1.setProductionObjective(0L);
                    }
                    if (开拓月计划.size() > 0) {
                        coalDay1.setExplorationPlan(开拓月计划.get(num));
                    } else {
                        coalDay1.setExplorationPlan(0L);
                    }
                    if (开拓月目标.size() > 0) {
                        coalDay1.setExplorationObjective(开拓月目标.get(num));
                    } else {
                        coalDay1.setExplorationObjective(0L);
                    }
                    if (进尺月计划.size() > 0) {
                        coalDay1.setFootagePlan(进尺月计划.get(num));
                    } else {
                        coalDay1.setFootagePlan(0L);
                    }
                    if (进尺月目标.size() > 0) {
                        coalDay1.setFootageObjective(进尺月目标.get(num));
                    } else {
                        coalDay1.setFootageObjective(0L);
                    }
                    num++;
                } else {
                    coalDay1.setProductionPlan(0L);
                    coalDay1.setProductionObjective(0L);
                    coalDay1.setExplorationPlan(0L);
                    coalDay1.setExplorationObjective(0L);
                    coalDay1.setFootagePlan(0L);
                    coalDay1.setFootageObjective(0L);
                }
                coalMineWorkingDay1.add(coalDay1);
            }
            if (coalMineWorkingDay1.size() > 0){
                coalMineWorkingDayService.batcupdateCoalMineWorkingDay(coalMineWorkingDay1);
            }
        }
    }

    // 处理洗煤厂工作日逻辑
    private void processWashPlantWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay, int 工作天数,Date firstDayOfMonth, int leixing) {
        if (leixing == 1) {
            // 设置洗煤厂日计划
            SubWashCoalPlanPO subWashCoalPlanPO = subPlanMapper.selectByPlanMonth(firstDayOfMonth, coalMineWorkingDay.get(0).getUnitCode());

            if (subWashCoalPlanPO != null) {
                List<Long> 入洗日计划 = NumUtils.splitAverageInterleaved(subWashCoalPlanPO.getWashInMonthPlan(), 工作天数);
                List<Long> 精煤量日计划 = NumUtils.splitAverageInterleaved(subWashCoalPlanPO.getCleanCoalMonthPlan(), 工作天数);
                List<Long> 精煤量日计划车数 = NumUtils.splitAverageInterleaved(BigDecimal.valueOf(subWashCoalPlanPO.getCleanCoalMonthCarPlan()), 工作天数);
                List<Long> 末煤日计划 = NumUtils.splitAverageInterleaved(subWashCoalPlanPO.getSlackCoalMonthPlan(), 工作天数);
                List<Long> 末煤日计划车数 = NumUtils.splitAverageInterleaved(BigDecimal.valueOf(subWashCoalPlanPO.getSlackCoalMonthCarPlan()), 工作天数);
                int num = 0;
                for (CoalMineWorkingDay coalDay1 : coalMineWorkingDay) {
                    CoalMineWorkingDay coalDay = new CoalMineWorkingDay();
                    if (coalDay1.getWhetherWorking() == 1) {
                        coalDay.setProductionPlan(入洗日计划.get(num));// 入洗日计划
                        coalDay.setProductionObjective(精煤量日计划.get(num));// 精煤日计划
                        coalDay.setExplorationPlan(精煤量日计划车数.get(num));// 精煤日计划车数
                        coalDay.setExplorationObjective(末煤日计划.get(num));// 块末日计划
                        coalDay.setFootagePlan(末煤日计划车数.get(num));// 块末日计划车数
                        num++;
                    } else {
                        coalDay.setProductionPlan(0L);// 入洗日计划
                        coalDay.setProductionObjective(0L);// 精煤日计划
                        coalDay.setExplorationPlan(0L);// 精煤日计划车数
                        coalDay.setExplorationObjective(0L);// 块末日计划
                        coalDay.setFootagePlan(0L);// 块末日计划车数
                    }
                    coalDay.setUnitName(coalDay1.getUnitName());
                    coalDay.setUnitCode(coalDay1.getUnitCode());
                    coalDay.setWorkingMonth(coalDay1.getWorkingMonth());
                    coalDay.setWorkingDays(coalDay1.getWorkingDays());
                    coalMineWorkingDayService.updateCoalMineWorkingDay(coalDay1);
                }
            }
        }
    }

    // 处理默认工作日逻辑
    private void processDefaultWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay, int 工作天数,Date firstDayOfMonth, int leixing) {
        if (leixing != 1 && leixing != 2) {
            for (CoalMineWorkingDay coalDay1 : coalMineWorkingDay) {
                coalDay1.setProductionPlan(0L);
                coalDay1.setProductionObjective(0L);
                coalDay1.setExplorationPlan(0L);
                coalDay1.setExplorationObjective(0L);
                coalDay1.setFootagePlan(0L);
                coalDay1.setFootageObjective(0L);
                coalMineWorkingDayService.updateCoalMineWorkingDay(coalDay1);
            }
        }
    }

    /**
     * 删除工作日
     */
    @Log(title = "工作日", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(coalMineWorkingDayService.deleteCoalMineWorkingDayByIds(ids));
    }
}

