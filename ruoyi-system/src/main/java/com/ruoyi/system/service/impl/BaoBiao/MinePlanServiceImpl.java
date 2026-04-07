//package com.ruoyi.system.service.impl.BaoBiao;
//
//import com.ruoyi.system.domain.BaoBiao.MinePlan;
//import com.ruoyi.system.mapper.BaoBiao.MinePlanMapper;
//import com.ruoyi.system.service.BaoBiao.IMinePlanService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ruoyi.system.vo.dto.mine.MinePlanPageQueryDTO;
//import com.ruoyi.system.domain.vo.plan.MinePlanVO;
//import com.ruoyi.system.domain.BaoBiao.entity.MinePlanRow;
//
//
//import java.util.*;
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class MinePlanServiceImpl implements IMinePlanService {
//
//    @Resource
//    private MinePlanMapper minePlanMapper;
//
//    @Override
//    public MinePlan get(Long id) {
//        return minePlanMapper.selectById(id);
//    }
//
//    @Override
//    public List<MinePlan> list(MinePlan query) {
//        return minePlanMapper.selectList(query);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int add(MinePlan entity) {
//        return minePlanMapper.insert(entity);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int edit(MinePlan entity) {
//        return minePlanMapper.update(entity);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int remove(Long id) {
//        return minePlanMapper.deleteById(id);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int removeBatch(List<Long> ids) {
//        return minePlanMapper.deleteByIds(ids);
//    }
//
//
//
//    private static final ObjectMapper M = new ObjectMapper();
//
//    @Resource
//    private MinePlanMapper mapper;
//
//    @Override
//    public List<MinePlanVO> page(MinePlanPageQueryDTO q) {
//        List<MinePlanRow> rows = mapper.selectPage(q);
//        List<MinePlanVO> out = new ArrayList<>(rows.size());
//        for (MinePlanRow r : rows) out.add(toVO(r));
//        return out;
//    }
//
//    private MinePlanVO toVO(MinePlanRow r) {
//        MinePlanVO vo = new MinePlanVO();
//        vo.setId(r.getId());
//        vo.setPlanType(r.getPlanType());
//        vo.setUnitCode(r.getUnitCode());
//        vo.setUnitName(r.getUnitName());
//        vo.setPlanMonth(r.getPlanMonth());
//        vo.setWorkDaysInMonth(r.getWorkDaysInMonth());
//        vo.setUserId(r.getUserId());
//        vo.setMineCategory(r.getMineCategory());
//        vo.setCreateTime(r.getCreateTime());
//        vo.setUpdateTime(r.getUpdateTime());
//
//        List<String> unitName = parseList(r.getUnitNameJson(), String.class);
//        List<Number> monthPlan = parseList(r.getMonthPlanJson(), Number.class);
//        List<Number> dayPlan = parseList(r.getDayPlanJson(), Number.class);
//        List<Number> monthTarget = parseList(r.getMonthTargetJson(), Number.class);
//        List<Number> dayTarget = parseList(r.getDayTargetJson(), Number.class);
//
//        int n = minLen(unitName, monthPlan, dayPlan, monthTarget, dayTarget);
//        List<MinePlanVO.MinePlanItem> items = new ArrayList<>(n);
//        for (int i=0;i<n;i++){
//            MinePlanVO.MinePlanItem it = new MinePlanVO.MinePlanItem();
//            it.setUnit_name(safe(unitName,i));
//            it.setUnit_code(r.getUnitCode()); // 标量复写
//            it.setMonth_plan(safe(monthPlan,i));
//            it.setDay_plan(safe(dayPlan,i));
//            it.setMonth_target(safe(monthTarget,i));
//            it.setDay_target(safe(dayTarget,i));
//            items.add(it);
//        }
//        vo.setDataJSON(items);
//        return vo;
//    }
//
//    // ==== helpers ====
//    private static <T> List<T> parseList(String json, Class<T> cls){
//        if (json==null || json.isEmpty()) return new ArrayList<>();
//        try { return M.readValue(json, M.getTypeFactory().constructCollectionType(List.class, cls)); }
//        catch (Exception e){
//            try {
//                if (json.length()>1 && json.startsWith("\"") && json.endsWith("\""))
//                    json = M.readValue(json, String.class);
//                return M.readValue(json, M.getTypeFactory().constructCollectionType(List.class, cls));
//            } catch (Exception ignore){ return new ArrayList<>(); }
//        }
//    }
//    private static int minLen(List<?>... arrs){
//        int min=Integer.MAX_VALUE; for (List<?> a:arrs){ if (a==null) return 0; min=Math.min(min,a.size()); }
//        return min==Integer.MAX_VALUE?0:min;
//    }
//    private static <T> T safe(List<T> list,int i){ return list!=null && i<list.size()?list.get(i):null; }
//}
//package com.ruoyi.system.service.impl.BaoBiao;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ruoyi.system.domain.BaoBiao.dto.mine.*;
//import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
//import com.ruoyi.system.domain.BaoBiao.vo.mine.MinePlanJsonVO;
//import com.ruoyi.system.mapper.BaoBiao.MinePlanMapper;
//import com.ruoyi.system.service.BaoBiao.IMinePlanService;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@Service
//public class MinePlanServiceImpl implements IMinePlanService {
//
//    @Resource
//    private MinePlanMapper mapper;
//
//    private static final ObjectMapper M = new ObjectMapper();
//
//    @Override
//    public Long add(MinePlanCreateDTO dto) {
//        MinePlanPO po = toPO(dto);
//        po.setIsDeleted(0);
//        mapper.insert(po);
//        return po.getId();
//    }
//
//    @Override
//    public int edit(MinePlanUpdateDTO dto) {
//        MinePlanPO po = toPO(dto);
//        po.setId(dto.getId());
//        return mapper.update(po);
//    }
//
//    @Override
//    public int remove(Long id) {
//        return mapper.softDeleteById(id);
//    }
//
//    @Override
//    public MinePlanJsonVO get(Long id) {
//        MinePlanPO po = mapper.selectById(id);
//        if (po == null) return null;
//        return toVO(po);
//    }
//
//    @Override
//    public List<MinePlanJsonVO> page(MinePlanQueryDTO query) {
//        MinePlanPO c = new MinePlanPO();
//        c.setMineCategory(query.getMine_category());
//        c.setUserId(query.getUser_id());
//        c.setPlanType(query.getPlan_type());
//        c.setIsDeleted(query.getIs_deleted());
//
//        Date pm = normalizeMonthToFirstDay(query.getPlan_month());
//        if (pm != null) c.setPlanMonth(pm);
//
//        List<MinePlanPO> list = mapper.selectList(c);
//        List<MinePlanJsonVO> out = new ArrayList<>(list.size());
//        for (MinePlanPO po : list) out.add(toVO(po));
//        return out;
//    }
//
//    // =============== DTO -> PO ===============
//    private MinePlanPO toPO(MinePlanCreateDTO dto) {
//        MinePlanPO po = new MinePlanPO();
//        po.setPlanType(dto.getPlan_type());
//        po.setUnitCode(dto.getUnit_code());
//        po.setUnitName(dto.getUnit_name());
//        po.setPlanMonth(normalizeMonthToFirstDay(dto.getPlan_month()));
//        po.setWorkDaysInMonth(dto.getWork_days_in_month());
//        po.setUserId(dto.getUser_id());
//        po.setMineCategory(dto.getMine_category());
//
//        Map<String, List<?>> col = explodeToColumns(dto.getData_JSON());
//        po.setUnitNameJSON(writeJson(col.getOrDefault("unit_name", Collections.emptyList())));
//        po.setMonthPlan(writeJson(col.getOrDefault("month_plan", Collections.emptyList())));
//        po.setDayPlan(writeJson(col.getOrDefault("day_plan", Collections.emptyList())));
//        po.setMonthTarget(writeJson(col.getOrDefault("month_target", Collections.emptyList())));
//        po.setDayTarget(writeJson(col.getOrDefault("day_target", Collections.emptyList())));
//        return po;
//    }
//
//    // =============== PO -> VO ===============
//    private MinePlanJsonVO toVO(MinePlanPO po) {
//        MinePlanJsonVO vo = new MinePlanJsonVO();
//        vo.setId(po.getId());
//        vo.setPlan_type(po.getPlanType());
//        vo.setUnit_code(po.getUnitCode());
//        vo.setUnit_name(po.getUnitName());
//        vo.setPlan_month(po.getPlanMonth());
//        vo.setWork_days_in_month(po.getWorkDaysInMonth());
//        vo.setUser_id(po.getUserId());
//        vo.setMine_category(po.getMineCategory());
//
//        List<String> unitName = readList(po.getUnitNameJSON(), String.class);
//        List<BigDecimal> monthPlan = readBigDecimalList(po.getMonthPlan());
//        List<BigDecimal> dayPlan = readBigDecimalList(po.getDayPlan());
//        List<BigDecimal> monthTarget = readBigDecimalList(po.getMonthTarget());
//        List<BigDecimal> dayTarget = readBigDecimalList(po.getDayTarget());
//
//        int n = maxLen(unitName, monthPlan, dayPlan, monthTarget, dayTarget);
//        List<MinePlanDataItem> items = new ArrayList<>(n);
//        for (int i = 0; i < n; i++) {
//            MinePlanDataItem it = new MinePlanDataItem();
//            it.setUnit_name(get(unitName, i));
//            it.setMonth_plan(get(monthPlan, i));
//            it.setDay_plan(get(dayPlan, i));
//            it.setMonth_target(get(monthTarget, i));
//            it.setDay_target(get(dayTarget, i));
//            items.add(it);
//        }
//        vo.setData_JSON(items);
//        return vo;
//    }
//
//    // =============== 工具：行式 -> 列式 ===============
//    private Map<String, List<?>> explodeToColumns(List<MinePlanDataItem> rows) {
//        Map<String, List<?>> map = new HashMap<>();
//        if (CollectionUtils.isEmpty(rows)) return map;
//
//        List<String> unit_name = new ArrayList<>();
//        List<BigDecimal> month_plan = new ArrayList<>();
//        List<BigDecimal> day_plan = new ArrayList<>();
//        List<BigDecimal> month_target = new ArrayList<>();
//        List<BigDecimal> day_target = new ArrayList<>();
//
//        for (MinePlanDataItem r : rows) {
//            unit_name.add(r.getUnit_name());
//            month_plan.add(r.getMonth_plan());
//            day_plan.add(r.getDay_plan());
//            month_target.add(r.getMonth_target());
//            day_target.add(r.getDay_target());
//        }
//        map.put("unit_name", unit_name);
//        map.put("month_plan", month_plan);
//        map.put("day_plan", day_plan);
//        map.put("month_target", month_target);
//        map.put("day_target", day_target);
//        return map;
//    }
//
//    // =============== JSON/日期小工具 ===============
//    private String writeJson(Object obj) {
//        try { return M.writeValueAsString(obj); }
//        catch (Exception e) { throw new RuntimeException("JSON 序列化失败", e); }
//    }
//
//    private <T> List<T> readList(String json, Class<T> clazz) {
//        if (json == null || json.isEmpty()) return Collections.emptyList();
//        try {
//            return M.readValue(json, M.getTypeFactory().constructCollectionType(List.class, clazz));
//        } catch (Exception e) {
//            throw new RuntimeException("JSON 反序列化失败: " + json, e);
//        }
//    }
//    private List<java.math.BigDecimal> readBigDecimalList(String json) {
//        if (json == null || json.isEmpty()) return Collections.emptyList();
//        try {
//            return M.readValue(json, new TypeReference<List<java.math.BigDecimal>>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("JSON 反序列化失败: " + json, e);
//        }
//    }
//
//    @SafeVarargs
//    private final int maxLen(List<?>... lists) {
//        int m = 0;
//        for (List<?> l : lists) if (l != null && l.size() > m) m = l.size();
//        return m;
//    }
//    private <T> T get(List<T> list, int i) {
//        if (list == null || i >= list.size()) return null;
//        return list.get(i);
//    }
//
//    private Date normalizeMonthToFirstDay(String anyDateStr) {
//        if (anyDateStr == null || anyDateStr.trim().isEmpty()) return null;
//        String s = anyDateStr.trim();
//        try {
//            if (s.length() >= 10) {
//                LocalDate d = LocalDate.parse(s.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                return Date.valueOf(d.withDayOfMonth(1));
//            }
//            if (s.length() == 7) {
//                LocalDate d = LocalDate.parse(s + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                return Date.valueOf(d);
//            }
//            LocalDateTime dt = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return Date.valueOf(dt.toLocalDate().withDayOfMonth(1));
//        } catch (Exception e) {
//            String ym = s.substring(0, Math.min(7, s.length()));
//            LocalDate d = LocalDate.parse(ym + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            return Date.valueOf(d);
//        }
//    }
//}
package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.NumUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanCreateDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanUnitItem;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanUpdateDTO;
import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanJu;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanVO;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.BaoBiao.MineDevelopmentDataMapper;
import com.ruoyi.system.mapper.BaoBiao.MinePlanMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMineDevelopmentDataMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMinePlanMapper;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.BaoBiao.IMinePlanService;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MinePlanServiceImpl implements IMinePlanService {

    @Resource
    private MinePlanMapper planMapper;
    @Resource
    private SubMinePlanMapper subMapper;
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    @Resource
    private LongmeiPlanInputMapper longmeiPlan;//龙煤计划主表
    @Resource
    private SubLongmeiPlanInputMapper subLongmeiPlan;//龙煤计划子表
    @Resource
    private SubMineDevelopmentDataMapper subMineDevelopmentDataMapper;//完成数据子表

    @Resource
    private MineDevelopmentDataMapper mineDevelopmentDataMapper;//完成数据主表
    @Resource
    private SysUserMapper sysUserMapper;//查找用户id
    @Resource
    private UserMessageMapper messageMapper;
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作天数

    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//每日计划

    @FunctionalInterface
    public interface MinePlanPostProcessor {
        void process(MinePlanPO m, List<SubMinePlanPO> subs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(MinePlanCreateDTO dto) {
        MinePlanPO m = toPO(dto);
        m.setIsDeleted(0);
        List<MinePlanPO> pos = planMapper.selectList(m);
        Long id;
        if (pos.size() > 0) {
            MinePlanPO min1 = new MinePlanPO();
            min1.setPlanType(m.getPlanType());
            min1.setPlanMonth(m.getPlanMonth());
            min1.setMineCategory("全矿");
            List<MinePlanPO> minePlanPOS = planMapper.selectList(min1);
            Long subId = Long.valueOf(0);
            for (MinePlanPO PO : minePlanPOS) {
                List<SubMinePlanPO> sub = subMapper.selectByPlanId(PO.getId());
                for (SubMinePlanPO subpo : sub) {
                    if (subpo.getUnitName().equals(m.getUnitName())) {
                        if (subpo.getIsDeleted() != null) {
                            if (subpo.getIsDeleted() != 2) {
                                return null;
                            }
                            if (subpo.getIsDeleted() == 2) {
                                subId = subpo.getId();
                            }
                        }
                    }
                }
            }
            id = pos.get(0).getId();
            m.setId(pos.get(0).getId());
            planMapper.update(m);
            subMapper.deleteByPlanId(id);
            subMapper.deleteById(subId);
        } else {
            m.setIsDeleted(0);
            planMapper.insert(m);
            id = m.getId();
        }

        List<SubMinePlanPO> subs = toSubPOList(id, dto.getData_JSON());
        subMapper.batchInsert(subs);

        // 使用函数式接口处理后续逻辑，不影响主业务流程
        executePostProcessing(m, subs, this::processWorkingDays);
        executePostProcessing(m, subs, this::processCompanyData);
        executePostProcessing(m, subs, this::processLongmeiPlan);

        return id;
    }

    // 通用的后处理执行方法
    private void executePostProcessing(MinePlanPO m, List<SubMinePlanPO> subs, MinePlanPostProcessor processor) {
        try {
            processor.process(m, subs);
        } catch (Exception e) {
            System.err.println("后处理执行失败: " + e.getMessage());
        }
    }

    // 处理工作日逻辑
    private void processWorkingDays(MinePlanPO m, List<SubMinePlanPO> subs) {
        String yue1 = DateUtils.returnDateDay(m.getPlanMonth());
        CoalMineWorkingDay coalMineWorkingDay = new CoalMineWorkingDay();
        coalMineWorkingDay.setUnitName(m.getUnitName());
        coalMineWorkingDay.setWorkingMonth(yue1);
        List<CoalMineWorkingDay> coalMineWorkingDays = coalMineWorkingDayMapper.selectCoalMineWorkingDayList(coalMineWorkingDay);
        List<MinePlanDay> dayList = new ArrayList<>();
        List<MinePlanDay> dayAllList = new ArrayList<>();
        for (SubMinePlanPO po : subs) {
            List<Long> 月计划 = NumUtils.splitAverageInterleaved(po.getMonthPlan(), m.getWorkDaysInMonth());
            List<Long> 月目标 = NumUtils.splitAverageInterleaved(po.getMonthTarget(), m.getWorkDaysInMonth());

            adjustDistribution(月计划, po.getMonthPlan());
            adjustDistribution(月目标, po.getMonthTarget());

            int num = 0;
            List<MinePlanDay> minePlanDay = new ArrayList<>();
            for (int i = 0; i < coalMineWorkingDays.size(); i++) {
                if (!po.getUnitName().equals("计划总量")){
                    MinePlanDay minday = new MinePlanDay();
                    minday.setPlanType(m.getPlanType());
                    minday.setAreaName(m.getUnitName());
                    minday.setUnitCode(m.getUnitCode());
                    minday.setPlanMonth(yue1);
                    minday.setPlanDay(i + 1);
                    minday.setUnitCode(po.getUnitCode());
                    minday.setUnitName(po.getUnitName());
                    List<MinePlanDay> dayList1 = minePlanDayMapper.selectMinePlanDayList(minday);
                    if (coalMineWorkingDays.get(i).getWhetherWorking() == 1) {
                        minday.setDayPlan(Math.toIntExact(月计划.get(num)));
                        minday.setDayTarget(Math.toIntExact(月目标.get(num)));
                        num++;
                    } else {
                        minday.setDayPlan(0);
                        minday.setDayTarget(0);
                    }
                    if (dayList1.size() > 0) {
                        minday.setId(dayList1.get(0).getId());
                        minday.setStatus(0);
                        minePlanDay.add(minday);
                        if (minePlanDay.size() >= 50) {
                            minePlanDayMapper.batchUpdateMinePlanDay(minePlanDay);
                            minePlanDay.clear();
                        }
                    } else {
                        minday.setStatus(0);
                        dayList.add(minday);
                    }
                    dayAllList.add(minday);
                }
            }
            if (minePlanDay.size() > 0) {
                minePlanDayMapper.batchUpdateMinePlanDay(minePlanDay);
            }
        }

        for (int i = 0; i < coalMineWorkingDays.size(); i++) {
            CoalMineWorkingDay coalDay = new CoalMineWorkingDay();
            Long workingDays = coalMineWorkingDays.get(i).getWorkingDays();
            if ("生产".equals(m.getPlanType())) {
                if (coalMineWorkingDays.get(i).getWhetherWorking() == 1) {
                    int plan = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayPlan).sum();
                    int target = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayTarget).sum();
                    coalDay.setProductionPlan((long)plan);
                    coalDay.setProductionObjective((long)target);
                } else {
                    coalDay.setProductionPlan(0L);
                    coalDay.setProductionObjective(0L);
                }
            }
            if ("开拓".equals(m.getPlanType())) {
                if (coalMineWorkingDays.get(i).getWhetherWorking() == 1) {
                    int plan = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayPlan).sum();
                    int target = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayTarget).sum();
                    coalDay.setExplorationPlan((long)plan);
                    coalDay.setExplorationObjective((long)target);
                } else {
                    coalDay.setExplorationPlan(0L);
                    coalDay.setExplorationObjective(0L);
                }
            }
            if ("进尺".equals(m.getPlanType())) {
                if (coalMineWorkingDays.get(i).getWhetherWorking() == 1) {
                    int plan = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayPlan).sum();
                    int target = dayAllList.stream().filter(Objects::nonNull).filter(item -> item.getPlanDay() != null && workingDays.longValue() == item.getPlanDay().longValue()).mapToInt(MinePlanDay::getDayTarget).sum();
                    coalDay.setFootagePlan((long)plan);
                    coalDay.setFootageObjective((long)target);
                } else {
                    coalDay.setFootagePlan(0L);
                    coalDay.setFootageObjective(0L);
                }
            }
            coalDay.setUnitName(m.getUnitName());
            coalDay.setUnitCode(m.getUnitCode());
            coalDay.setWorkingMonth(yue1);
            coalDay.setWorkingDays((long) i + 1);
            coalMineWorkingDayMapper.updateCoalMineWorkingDays(coalDay);
        }
        if (dayList.size() > 0) {
            minePlanDayMapper.insertMinePlanDay(dayList);
        }
    }
    // 调整分配结果，使总和精确等于原始值
    private void adjustDistribution(List<Long> distributedValues, BigDecimal originalValue) {
        long actualSum = distributedValues.stream().mapToLong(Long::longValue).sum();
        long difference = originalValue.longValue() - actualSum;

        if (difference != 0 && !distributedValues.isEmpty()) {
            // 将差异加到最后一个元素上
            int lastIndex = distributedValues.size() - 1;
            distributedValues.set(lastIndex, distributedValues.get(lastIndex) + difference);
        }
    }

    // 处理公司数据逻辑
    private void processCompanyData(MinePlanPO m, List<SubMinePlanPO> subs) {
        //给公司数据显示
        MinePlanPO mp0 = new MinePlanPO();
        mp0.setMineCategory("全矿");
        mp0.setPlanMonth(m.getPlanMonth());
        mp0.setWorkDaysInMonth(m.getWorkDaysInMonth());
        mp0.setPlanType(m.getPlanType());
        List<MinePlanPO> 公司计划合计 = planMapper.selectList(mp0);
        if (公司计划合计.size() > 0) {
            SubMinePlanPO subs1 = new SubMinePlanPO();
            subs1.setMinePlanId(公司计划合计.get(0).getId());
            subs1.setUnitCode(m.getUnitCode());
            subs1.setUnitName(m.getUnitName());
            for (SubMinePlanPO su : subs) {
                if (su.getUnitName().equals("计划总量")) {
                    subs1.setIsDeleted(0);
                    subs1.setDayPlan(su.getDayPlan());
                    subs1.setMonthPlan(su.getMonthPlan());
                    subs1.setDayTarget(su.getDayTarget());
                    subs1.setMonthTarget(su.getMonthTarget());
                }
            }
            List<SubMinePlanPO> lsub = new ArrayList<>();
            lsub.add(subs1);
            subMapper.batchInsert(lsub);
        } else {
            mp0.setCreateTime(m.getCreateTime());
            mp0.setUpdateTime(m.getUpdateTime());
            mp0.setPlanType(m.getPlanType());
            mp0.setUnitCode("");
            mp0.setUnitName("");
            mp0.setUserId("");
            mp0.setIsDeleted(0);
            planMapper.insert(mp0);
            Long qid = mp0.getId();
            SubMinePlanPO subs1 = new SubMinePlanPO();
            subs1.setMinePlanId(qid);
            subs1.setUnitCode(m.getUnitCode());
            subs1.setUnitName(m.getUnitName());
            subs1.setIsDeleted(0);
            for (SubMinePlanPO su : subs) {
                if (su.getUnitName().equals("计划总量")) {
                    subs1.setDayPlan(su.getDayPlan());
                    subs1.setMonthPlan(su.getMonthPlan());
                    subs1.setDayTarget(su.getDayTarget());
                    subs1.setMonthTarget(su.getMonthTarget());
                }
            }
            List<SubMinePlanPO> lsub = new ArrayList<>();
            lsub.add(subs1);
            subMapper.batchInsert(lsub);
        }
    }

    // 处理龙煤计划逻辑
    private void processLongmeiPlan(MinePlanPO m, List<SubMinePlanPO> subs) {
        //给龙煤计划表处置
        LongmeiPlanInput longmei = new LongmeiPlanInput();
        String str = DateUtils.getYearMonth1(m.getPlanMonth());
        longmei.setPlanMonth(str);
        List<LongmeiPlanInput> longmeiPlanInputs = longmeiPlan.selectLongmeiPlanInputList(longmei);
        if (longmeiPlanInputs.size() > 0) {
            if (longmeiPlanInputs.get(0).getIsDeleted() != 3) {
                //龙煤主表有数据
                SubLongmeiPlanInput subInput = new SubLongmeiPlanInput();
                subInput.setCoalPlantStorageId(longmeiPlanInputs.get(0).getId());
                List<SubLongmeiPlanInput> sublongmei = subLongmeiPlan.selectSubLongmeiPlanInputList(subInput);
                SubLongmeiPlanInput xiugai = new SubLongmeiPlanInput();

                xiugai.setRawCoalProductionPlanDay(BigDecimal.valueOf(0));
                xiugai.setRawCoalProductionPlanMonth(BigDecimal.valueOf(0));
                xiugai.setExcavationPlanDay(BigDecimal.valueOf(0));
                xiugai.setExcavationPlanMonth(BigDecimal.valueOf(0));
                xiugai.setDevelopmentPlanMonth(BigDecimal.valueOf(0));
                xiugai.setDevelopmentPlanDay(BigDecimal.valueOf(0));

                for (SubLongmeiPlanInput input : sublongmei) {
                    if (input.getUnitName().equals(m.getUnitName())) {
                        xiugai = input;
                    }
                }
                if (xiugai.getId() != null) {
                    // 根据不同的计划类型更新对应的龙煤计划数据
                    for (SubMinePlanPO su : subs) {
                        if ("计划总量".equals(su.getUnitName())) {
                            switch (m.getPlanType()) {
                                case "生产":
                                    xiugai.setRawCoalProductionPlanDay(su.getDayPlan());
                                    xiugai.setRawCoalProductionPlanMonth(su.getMonthPlan());
                                    break;
                                case "进尺":
                                    xiugai.setExcavationPlanDay(su.getDayPlan());
                                    xiugai.setExcavationPlanMonth(su.getMonthPlan());
                                    break;
                                case "开拓":
                                    xiugai.setDevelopmentPlanDay(su.getDayPlan());
                                    xiugai.setDevelopmentPlanMonth(su.getMonthPlan());
                                    break;
                            }
                            break;
                        }
                    }
                    subLongmeiPlan.updateSubLongmeiPlanInput(xiugai);
                }
                else {
                    xiugai.setCoalPlantStorageId(longmeiPlanInputs.get(0).getId());
                    xiugai.setUnitName(m.getUnitName());
                    if ("生产".equals(m.getPlanType())) {
                        for (SubMinePlanPO su : subs) {
                            if (su.getUnitName().equals("计划总量")) {
                                xiugai.setRawCoalProductionPlanDay(su.getDayPlan());
                                xiugai.setRawCoalProductionPlanMonth(su.getMonthPlan());
                            }
                        }

                    }
                    if ("进尺".equals(m.getPlanType())) {
                        for (SubMinePlanPO su : subs) {
                            if (su.getUnitName().equals("计划总量")) {
                                xiugai.setExcavationPlanDay(su.getDayPlan());
                                xiugai.setExcavationPlanMonth(su.getMonthPlan());
                            }
                        }

                    }
                    if ("开拓".equals(m.getPlanType())) {
                        for (SubMinePlanPO su : subs) {
                            if (su.getUnitName().equals("计划总量")) {
                                xiugai.setDevelopmentPlanDay(su.getDayPlan());
                                xiugai.setDevelopmentPlanMonth(su.getMonthPlan());
                            }
                        }
                    }

                    subLongmeiPlan.insertSubLongmeiPlanInput(xiugai);

                }

            }
        } else {
            longmei.setWorkDaysInMonth(Long.valueOf(m.getWorkDaysInMonth()));
            longmei.setIsDeleted(Long.valueOf(0));
            longmei.setCreateTime(m.getCreateTime());
            longmeiPlan.insertLongmeiPlanInput(longmei);
            Long longmeiId = longmei.getId();
            SubLongmeiPlanInput sub = new SubLongmeiPlanInput();
            sub.setCoalPlantStorageId(longmeiId);
            sub.setUnitName(m.getUnitName());

            sub.setRawCoalProductionPlanDay(BigDecimal.valueOf(0));
            sub.setRawCoalProductionPlanMonth(BigDecimal.valueOf(0));
            sub.setExcavationPlanDay(BigDecimal.ZERO);
            sub.setExcavationPlanMonth(BigDecimal.valueOf(0));
            sub.setDevelopmentPlanMonth(BigDecimal.valueOf(0));
            sub.setDevelopmentPlanDay(BigDecimal.valueOf(0));

            if ("生产".equals(m.getPlanType())) {
                for (SubMinePlanPO su : subs) {
                    if (su.getUnitName().equals("计划总量")) {
                        sub.setRawCoalProductionPlanDay(su.getDayPlan());
                        sub.setRawCoalProductionPlanMonth(su.getMonthPlan());
                    }
                }

            }
            if ("进尺".equals(m.getPlanType())) {
                for (SubMinePlanPO su : subs) {
                    if (su.getUnitName().equals("计划总量")) {
                        sub.setExcavationPlanDay(su.getDayPlan());
                        sub.setExcavationPlanMonth(su.getMonthPlan());
                    }
                }

            }
            if ("开拓".equals(m.getPlanType())) {
                for (SubMinePlanPO su : subs) {
                    if (su.getUnitName().equals("计划总量")) {
                        sub.setDevelopmentPlanDay(su.getDayPlan());
                        sub.setDevelopmentPlanMonth(su.getMonthPlan());
                    }
                }
            }
            subLongmeiPlan.insertSubLongmeiPlanInput(sub);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(MinePlanUpdateDTO dto) {
        MinePlanPO m = toPO(dto);
        m.setId(dto.getId());
        int n = planMapper.update(m);

        subMapper.deleteByPlanId(dto.getId());
        List<SubMinePlanPO> subs = toSubPOList(dto.getId(), dto.getData_JSON());
        if (!subs.isEmpty()) subMapper.batchInsert(subs);
        return n;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int subEdit(SubMinePlanPO dto) {
        SubMinePlanPO POS = subMapper.selectById(dto.getId());
        if (dto.getIsDeleted() == 2) {
            String planType = planMapper.selectById(POS.getMinePlanId()).getPlanType();
            String userId = sysUserMapper.selectUserByNickName(POS.getUnitName()).getUserId();
            String message = "调度已退回了" + POS.getUnitName() + planType + "计划";
            messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(), userId, message, new java.util.Date()));
            return subMapper.updateDell(dto.getId(), dto.getIsDeleted());
        } else {
            String userId = sysUserMapper.selectUserByNickName(POS.getUnitName()).getUserId();
            String planType = planMapper.selectById(POS.getMinePlanId()).getPlanType();
            String message = "调度已通过了" + POS.getUnitName() + planType + "计划";
            messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(), userId, message, new java.util.Date()));
            return subMapper.update(dto.getId(), dto.getIsDeleted());
        }
    }

    @Override
    public int remove(Long id) {
        return planMapper.softDeleteById(id);
    }

    @Override
    public MinePlanVO get(Long id) {
        MinePlanPO po = planMapper.selectById(id);
        if (po == null) return null;
        List<SubMinePlanPO> subs = subMapper.selectByPlanId(id);
        return toVO(po, subs);
    }

    @Override
    public List<MinePlanVO> page(MinePlanPageQueryDTO query) {
        MinePlanPO c = new MinePlanPO();
        c.setPlanType(query.getPlan_type());
        c.setMineCategory(query.getMine_category());
        c.setUserId(query.getUser_id());
        c.setIsDeleted(query.getIs_deleted());
        Date month = normalizeMonth(query.getPlan_month());

        c.setUnitCode(query.getUnitCode());
        if (month != null) c.setPlanMonth(month);

        List<MinePlanPO> masters = planMapper.selectList(c);
        if (masters.isEmpty()) return Collections.emptyList();

        List<Long> ids = masters.stream().map(MinePlanPO::getId).collect(Collectors.toList());
        List<SubMinePlanPO> allSubs = subMapper.selectByPlanIds(ids);

        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.QueryTeamName(query.getUnitCode());
        // 如果 allSubs 不包含 miningAreaCategories 里的数据，则将 miningAreaCategories 加入到 allSubs 中
        Set<String> existingUnitNames = allSubs.stream()
                .map(SubMinePlanPO::getUnitName)
                .collect(Collectors.toSet());

        for (MiningAreaCategory category : miningAreaCategories) {
            if (!existingUnitNames.contains(category.getAreaName())) {
                SubMinePlanPO newSub = new SubMinePlanPO();
                newSub.setMinePlanId(ids.get(0)); // 假设使用当前查询条件的ID作为关联
                newSub.setUnitName(category.getAreaName());
                newSub.setUnitCode(category.getAreaCode());
                newSub.setIsDeleted(0); // 默认未删除状态
                newSub.setMonthPlan(BigDecimal.ZERO);
                newSub.setDayPlan(BigDecimal.ZERO);
                newSub.setMonthTarget(BigDecimal.ZERO);
                newSub.setDayTarget(BigDecimal.ZERO);
                allSubs.add(newSub);
            }
        }



        Map<Long, List<SubMinePlanPO>> group = allSubs.stream()
                .collect(Collectors.groupingBy(SubMinePlanPO::getMinePlanId, LinkedHashMap::new, Collectors.toList()));

        List<MinePlanVO> out = new ArrayList<>(masters.size());
        for (MinePlanPO m : masters) {
            out.add(toVO(m, group.getOrDefault(m.getId(), Collections.emptyList())));
        }
        return out;
    }

    @Override
    public List<MinePlanVO> allPage(MinePlanPageQueryDTO query) {

        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setIsSealed(0);
        fac.setLevel(1);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//不是分公司

        MinePlanPO c = new MinePlanPO();
        c.setPlanType(query.getPlan_type());
        c.setMineCategory(query.getMine_category());
        //Date month =DateUtils.getFirstDayOfMonth(query.getPlan_month().toString("yyyy-MM-dd"));
        Date month = normalizeMonth(query.getPlan_month());
        if (month != null) c.setPlanMonth(month);

        List<MinePlanPO> masters = planMapper.selectList(c);
        List<MinePlanVO> out = new ArrayList<>();
        for (MinePlanPO po : masters) {
            List<MinePlanUnitItem> data_JSON = new ArrayList<>();

            List<SubMinePlanPO> data = subMapper.selectByPlanId(po.getId());
            for (MiningAreaCategory mining : miningAreaCategories) {
                MinePlanUnitItem mine = new MinePlanUnitItem();
                mine.setUnit_code(mining.getAreaCode());
                mine.setUnit_name(mining.getAreaName());

                mine.setDay_plan(BigDecimal.valueOf(0));
                mine.setDay_target(BigDecimal.valueOf(0));
                mine.setMonth_plan(BigDecimal.valueOf(0));
                mine.setMonth_target(BigDecimal.valueOf(0));

                mine.setIsDeleted(0);
                for (SubMinePlanPO sub : data) {
                    if (mining.getAreaName().equals(sub.getUnitName())) {
                        mine.setId(sub.getId());
                        mine.setDay_plan(sub.getDayPlan());
                        mine.setDay_target(sub.getDayTarget());
                        mine.setMonth_plan(sub.getMonthPlan());
                        mine.setMonth_target(sub.getMonthTarget());
                        mine.setIsDeleted(sub.getIsDeleted());
                    }
                }
                data_JSON.add(mine);
            }
            MinePlanVO v0 = new MinePlanVO();
            v0.setPlan_type(po.getPlanType());
            v0.setPlan_month(po.getPlanMonth());
            v0.setWork_days_in_month(po.getWorkDaysInMonth());
            v0.setData_JSON(data_JSON);
            out.add(v0);
        }


        return out;
    }

    @Override
    public List<MinePlanJu> productionData(MinePlanJu query) {
        List<MinePlanJu> out = new ArrayList<>();
        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        if (query.getUnitName() != null) {
            fac.setAreaName(query.getUnitName());
        }
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名称

        List<MineData> three = subMineDevelopmentDataMapper.selectDayDate(query.getPlanMonth());

        for (MiningAreaCategory mining : miningAreaCategories) {
            MinePlanJu ju = new MinePlanJu();
            ju.setUnitName(mining.getAreaName());
            ju.setPlanMonth(query.getPlanMonth());
            int num = 0;
            MineData 一班 = three.stream().filter(item -> mining.getAreaName().equals(item.getUnitName()))
                    .filter(item -> "1".equals(item.getCurrentShift())).findFirst().orElse(new MineData());

            MineData 二班 = three.stream().filter(item -> mining.getAreaName().equals(item.getUnitName()))
                    .filter(item -> "2".equals(item.getCurrentShift())).findFirst().orElse(new MineData());

            MineData 三班 = three.stream().filter(item -> mining.getAreaName().equals(item.getUnitName()))
                    .filter(item -> "3".equals(item.getCurrentShift())).findFirst().orElse(new MineData());
//一班
            if (一班.getUnitName() != null) {
                if (一班.getIsDeleted() == 1) {
                    ju.setOneProductionData(0);//生产数据
                    ju.setOneExpandData(BigDecimal.valueOf(0));//开拓
                    ju.setOneFootageData(BigDecimal.valueOf(0));//进尺
                    ju.setOneEnterWellNum(0);//入井人数
                    ju.setOneComeOutWellNum(0);//出境人数
                    ju.setOneTotalDownCount(0);//入井人数
                    ju.setOneTotalUpCount(0);//出井人数
                } else {
                    ju.setOneProductionData(一班.getProductionData());//生产数据
                    ju.setOneExpandData(一班.getExpandData());//开拓
                    ju.setOneFootageData(一班.getFootageData());//进尺
                    ju.setOneEnterWellNum(一班.getEnterWellNum());//入井人数
                    ju.setOneComeOutWellNum(一班.getComeOutWellNum());//出境人数
                    ju.setOneTotalDownCount(一班.getTotalDownCount());//入井人数
                    ju.setOneTotalUpCount(一班.getTotalUpCount());//出井人数
                    num++;
                }
            }
            //二班
            if (二班.getUnitName() != null) {
                if (二班.getIsDeleted() == 1) {
                    ju.setTwoProductionData(0);//生产数据
                    ju.setTwoExpandData(BigDecimal.valueOf(0));//开拓
                    ju.setTwoFootageData(BigDecimal.valueOf(0));//进尺
                    ju.setTwoEnterWellNum(0);//入井人数
                    ju.setTwoComeOutWellNum(0);//出境人数
                    ju.setTwoTotalDownCount(0);//入井人数
                    ju.setTwoTotalUpCount(0);//出井人数
                } else {
                    ju.setTwoProductionData(二班.getProductionData());//生产数据
                    ju.setTwoExpandData(二班.getExpandData());//开拓
                    ju.setTwoFootageData(二班.getFootageData());//进尺
                    ju.setTwoEnterWellNum(二班.getEnterWellNum());//入井人数
                    ju.setTwoComeOutWellNum(二班.getComeOutWellNum());//出境人数
                    ju.setTwoTotalDownCount(二班.getTotalDownCount());//入井人数
                    ju.setTwoTotalUpCount(二班.getTotalUpCount());//出井人数
                    num++;
                }
            }
            //三班
            if (三班.getUnitName() != null) {
                if (三班.getIsDeleted() == 1) {
                    ju.setThreeProductionData(0);//生产数据
                    ju.setThreeExpandData(BigDecimal.valueOf(0));//开拓
                    ju.setThreeFootageData(BigDecimal.valueOf(0));//进尺
                    ju.setThreeEnterWellNum(0);//入井人数
                    ju.setThreeComeOutWellNum(0);//出境人数
                    ju.setThreeTotalDownCount(0);//入井人数
                    ju.setThreeTotalUpCount(0);//出井人数
                } else {
                    ju.setThreeProductionData(三班.getProductionData());//生产数据
                    ju.setThreeExpandData(三班.getExpandData());//开拓
                    ju.setThreeFootageData(三班.getFootageData());//进尺
                    ju.setThreeEnterWellNum(三班.getEnterWellNum());//入井人数
                    ju.setThreeComeOutWellNum(三班.getComeOutWellNum());//出境人数
                    ju.setThreeTotalDownCount(三班.getTotalDownCount());//入井人数
                    ju.setThreeTotalUpCount(三班.getTotalUpCount());//出井人数
                    num++;
                }
            }
            if (num > 2) {
                ju.setIsDeleted("正常");
            } else if (num > 0) {
                ju.setIsDeleted("正常");
            } else {
                ju.setIsDeleted(null);
            }
            if (三班.getIsDeleted() != null && 二班.getIsDeleted() != null && 一班.getIsDeleted() != null) {
                if (三班.getIsDeleted() == 1 & 二班.getIsDeleted() == 1 & 一班.getIsDeleted() == 1) {
                    ju.setIsDeleted("已退回");
                }
            }
            out.add(ju);
        }
        return out;
    }

    @Override
    public String getState(MinePlanPageQueryDTO query) {
        List<MinePlanJu> out = new ArrayList<>();
        MinePlanPO c = new MinePlanPO();
        c.setPlanType(query.getPlan_type());
        c.setMineCategory("全矿");
        Date month = normalizeMonth(query.getPlan_month());
        if (month != null) c.setPlanMonth(month);
        List<MinePlanPO> masters = planMapper.selectList(c);

        for (MinePlanPO minPlan : masters) {
            List<SubMinePlanPO> subMinePlanPOS = subMapper.selectByPlanId(masters.get(0).getId());

            for (SubMinePlanPO subMinePlanPO : subMinePlanPOS) {
                if (subMinePlanPO.getUnitCode().equals(query.getUnitCode())) {
                    if (subMinePlanPO.getIsDeleted() == 0) {
                        return "已上报";
                    }
                    if (subMinePlanPO.getIsDeleted() == 2) {
                        return "已驳回";
                    }
                }

            }
        }
        return "未上报";
    }

    @Override
    public int deleteData(MinePlanJu query) {
        return subMineDevelopmentDataMapper.deleteByMddIdAll(query.getUnitName(), query.getPlanMonth());
    }

    // ===== Helpers =====
    private MinePlanPO toPO(MinePlanCreateDTO dto) {
        MinePlanPO po = new MinePlanPO();
        po.setPlanType(dto.getPlan_type());
        po.setPlanMonth(normalizeMonth(dto.getPlan_month()));
        po.setWorkDaysInMonth(dto.getWork_days_in_month());
        po.setUserId(dto.getUser_id());
        po.setMineCategory(dto.getMine_category());
        po.setUnitCode(dto.getUnit_code());
        po.setUnitName(dto.getUnit_name());

        po.setCompanyWidePlan(dto.getCompany_wide_plan());
        po.setBranchPlan(dto.getBranch_plan());
        po.setSeventhCompanyPlan(dto.getSeventh_company_plan());

        po.setCompanyWideGoals(dto.getCompany_wide_goals());
        po.setBranchCompanyGoals(dto.getBranch_company_goals());
        po.setSeventhCoalCompany(dto.getSeventh_coal_company());
        return po;
    }

    private List<SubMinePlanPO> toSubPOList(Long planId, List<MinePlanUnitItem> Item) {
        List<SubMinePlanPO> list = new ArrayList<>();
        if (Item == null) return list;


        for (MinePlanUnitItem it : Item) {
            SubMinePlanPO s = new SubMinePlanPO();
            s.setIsDeleted(0);
            s.setMinePlanId(planId);
            s.setMonthPlan(it.getMonth_plan());
            s.setDayPlan(it.getDay_plan());
            s.setMonthTarget(it.getMonth_target());
            s.setDayTarget(it.getDay_target());
            s.setUnitName(it.getUnit_name());
            s.setUnitCode(it.getUnit_code());
            list.add(s);
        }
        return list;
    }

    private MinePlanVO toVO(MinePlanPO m, List<SubMinePlanPO> subs) {
        MinePlanVO vo = new MinePlanVO();
        vo.setId(m.getId());
        vo.setPlan_type(m.getPlanType());
        vo.setPlan_month(m.getPlanMonth());
        vo.setWork_days_in_month(m.getWorkDaysInMonth());
        vo.setUser_id(m.getUserId());
        vo.setMine_category(m.getMineCategory());

        vo.setCompany_wide_plan(m.getCompanyWidePlan());
        vo.setBranch_plan(m.getBranchPlan());
        vo.setSeventh_company_plan(m.getSeventhCompanyPlan());
        vo.setCompany_wide_goals(m.getCompanyWideGoals());
        vo.setBranch_company_goals(m.getBranchCompanyGoals());
        vo.setSeventh_coal_company(m.getSeventhCoalCompany());

        List<MinePlanUnitItem> items = new ArrayList<>();
        for (SubMinePlanPO s : subs) {
            MinePlanUnitItem it = new MinePlanUnitItem();
            it.setMonth_plan(s.getMonthPlan());
            it.setDay_plan(s.getDayPlan());
            it.setMonth_target(s.getMonthTarget());
            it.setDay_target(s.getDayTarget());
            it.setUnit_name(s.getUnitName());
            it.setUnit_code(s.getUnitCode());
            items.add(it);
        }
        vo.setData_JSON(items);
        return vo;
    }

    private Date normalizeMonth(String any) {
        if (any == null || any.trim().isEmpty()) return null;
        String s = any.trim();
        try {
            if (s.length() >= 7) {
                String ym = s.substring(0, 7); // yyyy-MM
                LocalDate d = LocalDate.parse(ym + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return Date.valueOf(d);
            }
            LocalDate d = LocalDate.parse(s.substring(0, Math.min(10, s.length())), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return Date.valueOf(LocalDate.of(d.getYear(), d.getMonth(), 1));
        } catch (Exception e) {
            LocalDate d = LocalDate.parse(s.substring(0, Math.min(10, s.length())), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return Date.valueOf(LocalDate.of(d.getYear(), d.getMonth(), 1));
        }
    }
}

