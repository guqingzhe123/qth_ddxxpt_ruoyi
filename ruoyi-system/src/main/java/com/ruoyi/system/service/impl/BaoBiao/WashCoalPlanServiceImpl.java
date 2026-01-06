//package com.ruoyi.system.service.impl.BaoBiao;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ruoyi.system.domain.BaoBiao.WashCoalPlan;
//import com.ruoyi.system.vo.dto.wash.WashCoalPlanPageQueryDTO;
//import com.ruoyi.system.vo.dto.wash.WashCoalPlanUnitItem;
//import com.ruoyi.system.vo.dto.wash.WashCoalPlanVO;
//import com.ruoyi.system.domain.BaoBiao.entity.WashCoalPlanRow;
//import com.ruoyi.system.dto.WashCoalPlanItemDTO;
//import com.ruoyi.system.dto.WashCoalPlanSaveDTO;
//import com.ruoyi.system.mapper.BaoBiao.WashCoalPlanMapper;
//import com.ruoyi.system.service.BaoBiao.IWashCoalPlanService;
//import com.ruoyi.system.vo.WashCoalPlanDetailVO;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.ruoyi.system.vo.WashCoalPlanPageVO;
//import com.ruoyi.system.dto.WashCoalPlanUpdateDTO;
//import com.ruoyi.system.dto.WashCoalPlanUnitPatchDTO;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.*;
//
///**
// * 洗煤计划分页查询（含 data_JSON 还原）
// */
//@Service
//public class WashCoalPlanServiceImpl implements IWashCoalPlanService {
//
//    @Resource
//    private WashCoalPlanMapper washCoalPlanMapper;
//
//    private static final ObjectMapper MAPPER = new ObjectMapper();
//
//    @Resource
//    private WashCoalPlanMapper mapper;
//
//    // 将 data_JSON 列表拆成列式 JSON 数组（序列化成 String）
//    private Map<String, String> toColumnJsonArrays(List<WashCoalPlanItemDTO> items) {
//        List<String> unitCode = new ArrayList<>();
//        List<String> unitName = new ArrayList<>();
//
//        List<BigDecimal> washInMonthPlan = new ArrayList<>();
//        List<BigDecimal> washInDayPlan   = new ArrayList<>();
//
//        List<BigDecimal> cleanCoalMonthPlan = new ArrayList<>();
//        List<BigDecimal> cleanCoalDayPlan   = new ArrayList<>();
//        List<Integer>    cleanCoalMonthCarPlan = new ArrayList<>();
//        List<BigDecimal> cleanCoalDayCarPlan   = new ArrayList<>();
//
//        List<BigDecimal> slackCoalMonthPlan = new ArrayList<>();
//        List<BigDecimal> slackCoalDayPlan   = new ArrayList<>();
//        List<Integer>    slackCoalMonthCarPlan = new ArrayList<>();
//        List<BigDecimal> slackCoalDayCarPlan   = new ArrayList<>();
//
//        for (WashCoalPlanItemDTO it : items) {
//            unitCode.add(it.getUnit_code());
//            unitName.add(it.getUnit_name());
//
//            washInMonthPlan.add(it.getWash_in_month_plan());
//            washInDayPlan.add(it.getWash_in_day_plan());
//
//            cleanCoalMonthPlan.add(it.getClean_coal_month_plan());
//            cleanCoalDayPlan.add(it.getClean_coal_day_plan());
//            cleanCoalMonthCarPlan.add(it.getClean_coal_month_car_plan());
//            cleanCoalDayCarPlan.add(it.getClean_coal_day_car_plan());
//
//            slackCoalMonthPlan.add(it.getSlack_coal_month_plan());
//            slackCoalDayPlan.add(it.getSlack_coal_day_plan());
//            slackCoalMonthCarPlan.add(it.getSlack_coal_month_car_plan());
//            slackCoalDayCarPlan.add(it.getSlack_coal_day_car_plan());
//        }
//
//        Map<String, String> jsonMap = new HashMap<>();
//        try {
//            jsonMap.put("unitCode", MAPPER.writeValueAsString(unitCode));
//            jsonMap.put("unitName", MAPPER.writeValueAsString(unitName));
//
//            jsonMap.put("washInMonthPlan", MAPPER.writeValueAsString(washInMonthPlan));
//            jsonMap.put("washInDayPlan",   MAPPER.writeValueAsString(washInDayPlan));
//
//            jsonMap.put("cleanCoalMonthPlan",    MAPPER.writeValueAsString(cleanCoalMonthPlan));
//            jsonMap.put("cleanCoalDayPlan",      MAPPER.writeValueAsString(cleanCoalDayPlan));
//            jsonMap.put("cleanCoalMonthCarPlan", MAPPER.writeValueAsString(cleanCoalMonthCarPlan));
//            jsonMap.put("cleanCoalDayCarPlan",   MAPPER.writeValueAsString(cleanCoalDayCarPlan));
//
//            jsonMap.put("slackCoalMonthPlan",    MAPPER.writeValueAsString(slackCoalMonthPlan));
//            jsonMap.put("slackCoalDayPlan",      MAPPER.writeValueAsString(slackCoalDayPlan));
//            jsonMap.put("slackCoalMonthCarPlan", MAPPER.writeValueAsString(slackCoalMonthCarPlan));
//            jsonMap.put("slackCoalDayCarPlan",   MAPPER.writeValueAsString(slackCoalDayCarPlan));
//        } catch (Exception e) {
//            throw new RuntimeException("序列化 JSON 失败", e);
//        }
//        return jsonMap;
//    }
//
//    // 将列式 JSON 数组还原回 data_JSON 列表
//    private List<WashCoalPlanItemDTO> toItemsFromColumns(WashCoalPlan plan) {
//        try {
//            List<String> unitCode = MAPPER.readValue(plan.getUnitCode(), new TypeReference<List<String>>() {});
//            List<String> unitName = MAPPER.readValue(plan.getUnitName(), new TypeReference<List<String>>() {});
//
//            List<BigDecimal> washInMonthPlan = plan.getWashInMonthPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getWashInMonthPlan(), new TypeReference<List<BigDecimal>>() {});
//            List<BigDecimal> washInDayPlan   = plan.getWashInDayPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getWashInDayPlan(),   new TypeReference<List<BigDecimal>>() {});
//
//            List<BigDecimal> cleanCoalMonthPlan = plan.getCleanCoalMonthPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getCleanCoalMonthPlan(), new TypeReference<List<BigDecimal>>() {});
//            List<BigDecimal> cleanCoalDayPlan   = plan.getCleanCoalDayPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getCleanCoalDayPlan(),   new TypeReference<List<BigDecimal>>() {});
//            List<Integer>    cleanCoalMonthCarPlan = plan.getCleanCoalMonthCarPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getCleanCoalMonthCarPlan(), new TypeReference<List<Integer>>() {});
//            List<BigDecimal> cleanCoalDayCarPlan   = plan.getCleanCoalDayCarPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getCleanCoalDayCarPlan(),   new TypeReference<List<BigDecimal>>() {});
//
//            List<BigDecimal> slackCoalMonthPlan = plan.getSlackCoalMonthPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getSlackCoalMonthPlan(), new TypeReference<List<BigDecimal>>() {});
//            List<BigDecimal> slackCoalDayPlan   = plan.getSlackCoalDayPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getSlackCoalDayPlan(),   new TypeReference<List<BigDecimal>>() {});
//            List<Integer>    slackCoalMonthCarPlan = plan.getSlackCoalMonthCarPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getSlackCoalMonthCarPlan(), new TypeReference<List<Integer>>() {});
//            List<BigDecimal> slackCoalDayCarPlan   = plan.getSlackCoalDayCarPlan()==null ? Collections.emptyList()
//                    : MAPPER.readValue(plan.getSlackCoalDayCarPlan(),   new TypeReference<List<BigDecimal>>() {});
//
//            int n = unitCode.size();
//            List<WashCoalPlanItemDTO> list = new ArrayList<>(n);
//            for (int i = 0; i < n; i++) {
//                WashCoalPlanItemDTO it = new WashCoalPlanItemDTO();
//                it.setUnit_code(unitCode.get(i));
//                it.setUnit_name(unitName.size() > i ? unitName.get(i) : null);
//
//                it.setWash_in_month_plan(washInMonthPlan.size() > i ? washInMonthPlan.get(i) : null);
//                it.setWash_in_day_plan(  washInDayPlan.size()   > i ? washInDayPlan.get(i)   : null);
//
//                it.setClean_coal_month_plan(   cleanCoalMonthPlan.size()    > i ? cleanCoalMonthPlan.get(i)    : null);
//                it.setClean_coal_day_plan(     cleanCoalDayPlan.size()      > i ? cleanCoalDayPlan.get(i)      : null);
//                it.setClean_coal_month_car_plan(cleanCoalMonthCarPlan.size()> i ? cleanCoalMonthCarPlan.get(i) : null);
//                it.setClean_coal_day_car_plan( cleanCoalDayCarPlan.size()   > i ? cleanCoalDayCarPlan.get(i)   : null);
//
//                it.setSlack_coal_month_plan(   slackCoalMonthPlan.size()    > i ? slackCoalMonthPlan.get(i)    : null);
//                it.setSlack_coal_day_plan(     slackCoalDayPlan.size()      > i ? slackCoalDayPlan.get(i)      : null);
//                it.setSlack_coal_month_car_plan(slackCoalMonthCarPlan.size()> i ? slackCoalMonthCarPlan.get(i) : null);
//                it.setSlack_coal_day_car_plan( slackCoalDayCarPlan.size()   > i ? slackCoalDayCarPlan.get(i)   : null);
//
//                list.add(it);
//            }
//            return list;
//        } catch (Exception e) {
//            throw new RuntimeException("反序列化 JSON 失败", e);
//        }
//    }
//
//    private Date toMonthFirstDay(String planMonthInput) {
//        try {
//            if (planMonthInput == null || planMonthInput.trim().isEmpty()) {
//                throw new IllegalArgumentException("plan_month 不能为空");
//            }
//            if (planMonthInput.length() >= 7) {
//                String ym = planMonthInput.substring(0, 7); // yyyy-MM
//                LocalDate first = LocalDate.parse(ym + "-01");
//                return Date.from(first.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            }
//            // fallback：完整时间格式
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(planMonthInput);
//        } catch (Exception e) {
//            throw new RuntimeException("plan_month 解析失败，期望 yyyy-MM 或完整时间", e);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long save(WashCoalPlanSaveDTO dto) {
//        if (dto.getData_JSON() == null || dto.getData_JSON().isEmpty()) {
//            throw new IllegalArgumentException("data_JSON 不能为空");
//        }
//        Map<String, String> cols = toColumnJsonArrays(dto.getData_JSON());
//
//        WashCoalPlan po = new WashCoalPlan();
//        po.setPlanMonth(toMonthFirstDay(dto.getPlan_month()));
//        po.setWorkDaysInMonth(dto.getWork_days_in_month());
//        po.setDataSource(dto.getData_source());
//
//        po.setUnitCode(cols.get("unitCode"));
//        po.setUnitName(cols.get("unitName"));
//        po.setWashInMonthPlan(cols.get("washInMonthPlan"));
//        po.setWashInDayPlan(cols.get("washInDayPlan"));
//
//        po.setCleanCoalMonthPlan(cols.get("cleanCoalMonthPlan"));
//        po.setCleanCoalDayPlan(cols.get("cleanCoalDayPlan"));
//        po.setCleanCoalMonthCarPlan(cols.get("cleanCoalMonthCarPlan"));
//        po.setCleanCoalDayCarPlan(cols.get("cleanCoalDayCarPlan"));
//
//        po.setSlackCoalMonthPlan(cols.get("slackCoalMonthPlan"));
//        po.setSlackCoalDayPlan(cols.get("slackCoalDayPlan"));
//        po.setSlackCoalMonthCarPlan(cols.get("slackCoalMonthCarPlan"));
//        po.setSlackCoalDayCarPlan(cols.get("slackCoalDayCarPlan"));
//
//        po.setUserId(String.valueOf(dto.getUser_id()));
//        po.setIsDeleted(0);
//        po.setMineCategory(dto.getMine_category());
//
//        washCoalPlanMapper.insert(po);
//        return po.getId();
//    }
//
//    @Override
//    public WashCoalPlanDetailVO detail(Long id) {
//        WashCoalPlan po = washCoalPlanMapper.selectById(id);
//        if (po == null) return null;
//
//        WashCoalPlanDetailVO vo = new WashCoalPlanDetailVO();
//        vo.setId(po.getId());
//        vo.setData_source(po.getDataSource());
//        vo.setWork_days_in_month(po.getWorkDaysInMonth());
//        vo.setUser_id(po.getUserId());
//        vo.setMine_category(po.getMineCategory());
//        vo.setCreate_time(po.getCreateTime());
//        vo.setUpdate_time(po.getUpdateTime());
//
//        if (po.getPlanMonth() != null) {
//            vo.setPlan_month(new SimpleDateFormat("yyyy-MM").format(po.getPlanMonth()));
//        }
//
//        vo.setData_JSON(toItemsFromColumns(po));
//        return vo;
//    }
//
//    @Override
//    public List<WashCoalPlanPageVO> pageList(String planMonth, String mineCategory, String userId) {
//        return washCoalPlanMapper.selectPageList(planMonth, mineCategory, userId);
//    }
//
//    /** ============= 整单更新（全量/部分字段） ============= */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int update(WashCoalPlanUpdateDTO dto) {
//        if (dto.getId() == null) throw new IllegalArgumentException("id 不能为空");
//        WashCoalPlan exist = washCoalPlanMapper.selectById(dto.getId());
//        if (exist == null) throw new IllegalArgumentException("记录不存在: " + dto.getId());
//
//        // 头部字段可选更新
//        if (dto.getPlan_month() != null && !dto.getPlan_month().trim().isEmpty()) {
//            exist.setPlanMonth(toMonthFirstDay(dto.getPlan_month()));
//        }
//        if (dto.getWork_days_in_month() != null) {
//            exist.setWorkDaysInMonth(dto.getWork_days_in_month());
//        }
//        if (dto.getData_source() != null) {
//            exist.setDataSource(dto.getData_source());
//        }
//        if (dto.getMine_category() != null) {
//            exist.setMineCategory(dto.getMine_category());
//        }
//        if (dto.getUser_id() != null) {
//            exist.setUserId(String.valueOf(dto.getUser_id()));
//        }
//
//        // 如果带 data_JSON 就全量替换列式 JSON
//        if (dto.getData_JSON() != null && !dto.getData_JSON().isEmpty()) {
//            Map<String, String> cols = toColumnJsonArrays(dto.getData_JSON());
//            exist.setUnitCode(cols.get("unitCode"));
//            exist.setUnitName(cols.get("unitName"));
//            exist.setWashInMonthPlan(cols.get("washInMonthPlan"));
//            exist.setWashInDayPlan(cols.get("washInDayPlan"));
//            exist.setCleanCoalMonthPlan(cols.get("cleanCoalMonthPlan"));
//            exist.setCleanCoalDayPlan(cols.get("cleanCoalDayPlan"));
//            exist.setCleanCoalMonthCarPlan(cols.get("cleanCoalMonthCarPlan"));
//            exist.setCleanCoalDayCarPlan(cols.get("cleanCoalDayCarPlan"));
//            exist.setSlackCoalMonthPlan(cols.get("slackCoalMonthPlan"));
//            exist.setSlackCoalDayPlan(cols.get("slackCoalDayPlan"));
//            exist.setSlackCoalMonthCarPlan(cols.get("slackCoalMonthCarPlan"));
//            exist.setSlackCoalDayCarPlan(cols.get("slackCoalDayCarPlan"));
//        }
//
//        return washCoalPlanMapper.updateById(exist);
//    }
//
//    /** ============= 按 unit_code 局部更新 ============= */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateUnit(WashCoalPlanUnitPatchDTO dto) {
//        if (dto.getId() == null) throw new IllegalArgumentException("id 不能为空");
//        if (dto.getUnit_code() == null || dto.getUnit_code().trim().isEmpty()) {
//            throw new IllegalArgumentException("unit_code 不能为空");
//        }
//        WashCoalPlan po = washCoalPlanMapper.selectById(dto.getId());
//        if (po == null) throw new IllegalArgumentException("记录不存在: " + dto.getId());
//
//        try {
//            List<String> unitCode = MAPPER.readValue(po.getUnitCode(), new TypeReference<List<String>>() {});
//            int idx = unitCode.indexOf(dto.getUnit_code());
//            if (idx < 0) throw new IllegalArgumentException("unit_code 不存在: " + dto.getUnit_code());
//
//            // 辅助：读/写一个 BigDecimal 列
//            java.util.function.BiConsumer<String, BigDecimal> setDecimal = (colName, val) -> {
//                if (val == null) return;
//                try {
//                    List<BigDecimal> arr = poFieldToBigDecimalList(po, colName);
//                    ensureSize(arr, idx + 1);
//                    arr.set(idx, val);
//                    setPoFieldFromList(po, colName, arr);
//                } catch (Exception e) {
//                    throw new RuntimeException("更新列失败: " + colName, e);
//                }
//            };
//            // 辅助：读/写一个 Integer 列
//            java.util.function.BiConsumer<String, Integer> setInteger = (colName, val) -> {
//                if (val == null) return;
//                try {
//                    List<Integer> arr = poFieldToIntegerList(po, colName);
//                    ensureSize(arr, idx + 1);
//                    arr.set(idx, val);
//                    setPoFieldFromList(po, colName, arr);
//                } catch (Exception e) {
//                    throw new RuntimeException("更新列失败: " + colName, e);
//                }
//            };
//
//            // 可选更新的字段（只改非空）
//            setDecimal.accept("washInMonthPlan", dto.getWash_in_month_plan());
//            setDecimal.accept("washInDayPlan",   dto.getWash_in_day_plan());
//
//            setDecimal.accept("cleanCoalMonthPlan",    dto.getClean_coal_month_plan());
//            setDecimal.accept("cleanCoalDayPlan",      dto.getClean_coal_day_plan());
//            setInteger.accept("cleanCoalMonthCarPlan", dto.getClean_coal_month_car_plan());
//            setDecimal.accept("cleanCoalDayCarPlan",   dto.getClean_coal_day_car_plan());
//
//            setDecimal.accept("slackCoalMonthPlan",    dto.getSlack_coal_month_plan());
//            setDecimal.accept("slackCoalDayPlan",      dto.getSlack_coal_day_plan());
//            setInteger.accept("slackCoalMonthCarPlan", dto.getSlack_coal_month_car_plan());
//            setDecimal.accept("slackCoalDayCarPlan",   dto.getSlack_coal_day_car_plan());
//
//            // 可选：更新单位名称
//            if (dto.getUnit_name() != null) {
//                List<String> names = MAPPER.readValue(po.getUnitName(), new TypeReference<List<String>>() {});
//                ensureSize(names, idx + 1);
//                names.set(idx, dto.getUnit_name());
//                po.setUnitName(MAPPER.writeValueAsString(names));
//            }
//
//            return washCoalPlanMapper.updateById(po);
//        } catch (Exception e) {
//            throw new RuntimeException("按 unit_code 更新失败", e);
//        }
//    }
//
//    /* ======== 私有小工具：从 po 读/写 JSON 数组字段 ======== */
//    private List<BigDecimal> poFieldToBigDecimalList(WashCoalPlan po, String col) throws Exception {
//        String json = (String) WashCoalPlan.class.getMethod("get" + up(col)).invoke(po);
//        if (json == null || json.trim().isEmpty()) return new ArrayList<>();
//        return MAPPER.readValue(json, new TypeReference<List<BigDecimal>>() {});
//    }
//    private List<Integer> poFieldToIntegerList(WashCoalPlan po, String col) throws Exception {
//        String json = (String) WashCoalPlan.class.getMethod("get" + up(col)).invoke(po);
//        if (json == null || json.trim().isEmpty()) return new ArrayList<>();
//        return MAPPER.readValue(json, new TypeReference<List<Integer>>() {});
//    }
//    private <T> void setPoFieldFromList(WashCoalPlan po, String col, List<T> list) throws Exception {
//        String json = MAPPER.writeValueAsString(list);
//        WashCoalPlan.class.getMethod("set" + up(col), String.class).invoke(po, json);
//    }
//    private void ensureSize(List<?> list, int size) {
//        while (list.size() < size) {
//            if (list instanceof List<?>) {
//                ((List) list).add(null);
//            }
//        }
//    }
//    private String up(String s) {
//        return s.substring(0,1).toUpperCase() + s.substring(1);
//    }
//
//
//
//    @Override
//    public List<WashCoalPlanVO> page(WashCoalPlanPageQueryDTO query) {
//        List<WashCoalPlanRow> rows = mapper.selectPage(query);
//        List<WashCoalPlanVO> vos = new ArrayList<>(rows.size());
//        for (WashCoalPlanRow r : rows) {
//            vos.add(buildVO(r));
//        }
//        return vos;
//    }
//
//    /**
//     * 把“列式 JSON 的行”转换为 VO（data_JSON 数组）
//     */
//    private WashCoalPlanVO buildVO(WashCoalPlanRow r) {
//        WashCoalPlanVO vo = new WashCoalPlanVO();
//        vo.setId(r.getId());
//        vo.setPlanMonth(r.getPlanMonth());
//        vo.setWorkDaysInMonth(r.getWorkDaysInMonth());
//        vo.setUserId(r.getUserId());
//        vo.setMineCategory(r.getMineCategory());
//        vo.setCreateTime(r.getCreateTime());
//        vo.setUpdateTime(r.getUpdateTime());
//
//        // ===== 反序列化各列 JSON 为 List<?>，再按索引“拉链式组装”为 data_JSON =====
//        List<String> unitCodeArr             = parseList(r.getUnitCodeJson(),             String.class);
//        List<String> unitNameArr             = parseList(r.getUnitNameJson(),             String.class);
//        List<BigDecimal> washInMonthPlanArr  = parseList(r.getWashInMonthPlanJson(),      BigDecimal.class);
//        List<BigDecimal> washInDayPlanArr    = parseList(r.getWashInDayPlanJson(),        BigDecimal.class);
//        List<BigDecimal> cleanMonthArr       = parseList(r.getCleanCoalMonthPlanJson(),   BigDecimal.class);
//        List<BigDecimal> cleanDayArr         = parseList(r.getCleanCoalDayPlanJson(),     BigDecimal.class);
//        List<Integer>    cleanMonthCarArr    = parseList(r.getCleanCoalMonthCarPlanJson(),Integer.class);
//        List<BigDecimal> cleanDayCarArr      = parseList(r.getCleanCoalDayCarPlanJson(),  BigDecimal.class);
//        List<BigDecimal> slackMonthArr       = parseList(r.getSlackCoalMonthPlanJson(),   BigDecimal.class);
//        List<BigDecimal> slackDayArr         = parseList(r.getSlackCoalDayPlanJson(),     BigDecimal.class);
//        List<Integer>    slackMonthCarArr    = parseList(r.getSlackCoalMonthCarPlanJson(),Integer.class);
//        List<BigDecimal> slackDayCarArr      = parseList(r.getSlackCoalDayCarPlanJson(),  BigDecimal.class);
//
//        int n = minLen(
//                unitCodeArr, unitNameArr, washInMonthPlanArr, washInDayPlanArr,
//                cleanMonthArr, cleanDayArr, cleanMonthCarArr, cleanDayCarArr,
//                slackMonthArr, slackDayArr, slackMonthCarArr, slackDayCarArr
//        );
//
//        List<WashCoalPlanUnitItem> items = new ArrayList<>(Math.max(n,0));
//        for (int i = 0; i < n; i++) {
//            WashCoalPlanUnitItem it = new WashCoalPlanUnitItem();
//            it.setUnitCode(safe(unitCodeArr, i));
//            it.setUnitName(safe(unitNameArr, i));
//            it.setWashInMonthPlan(safe(washInMonthPlanArr, i));
//            it.setWashInDayPlan(safe(washInDayPlanArr, i));
//            it.setCleanCoalMonthPlan(safe(cleanMonthArr, i));
//            it.setCleanCoalDayPlan(safe(cleanDayArr, i));
//            it.setCleanCoalMonthCarPlan(safe(cleanMonthCarArr, i));
//            it.setCleanCoalDayCarPlan(safe(cleanDayCarArr, i));
//            it.setSlackCoalMonthPlan(safe(slackMonthArr, i));
//            it.setSlackCoalDayPlan(safe(slackDayArr, i));
//            it.setSlackCoalMonthCarPlan(safe(slackMonthCarArr, i));
//            it.setSlackCoalDayCarPlan(safe(slackDayCarArr, i));
//            items.add(it);
//        }
//        vo.setDataJSON(items);
//        return vo;
//    }
//
//    // ====== 小工具 ======
//    private static int minLen(List<?>... arrs) {
//        int min = Integer.MAX_VALUE;
//        for (List<?> a : arrs) {
//            if (a == null) return 0;
//            min = Math.min(min, a.size());
//        }
//        return min == Integer.MAX_VALUE ? 0 : min;
//    }
//
//    private static <T> T safe(List<T> list, int i) {
//        return (list != null && i < list.size()) ? list.get(i) : null;
//    }
//
//    private static <T> List<T> parseList(String json, Class<T> clazz) {
//        if (json == null || json.isEmpty()) return new ArrayList<>();
//        try {
//            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
//        } catch (Exception e) {
//            // 容错：有些驱动/方言可能把 JSON 再包了一层引号
//            try {
//                String unquoted = json;
//                if (json.length() > 1 && json.charAt(0) == '"' && json.charAt(json.length()-1) == '"') {
//                    unquoted = MAPPER.readValue(json, String.class);
//                }
//                return MAPPER.readValue(unquoted, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
//            } catch (Exception ex) {
//                // 出错则返回空，避免分页中断
//                return new ArrayList<>();
//            }
//        }
//    }
//}
package com.ruoyi.system.service.impl.BaoBiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.NumUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.dto.wash.*;
import com.ruoyi.system.domain.BaoBiao.po.SubWashCoalPlanPO;
import com.ruoyi.system.domain.BaoBiao.po.WashCoalPlanPO;
import com.ruoyi.system.domain.CoalMineWorkingDay;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.mapper.BaoBiao.SubWashCoalPlanMapper;
import com.ruoyi.system.mapper.BaoBiao.WashCoalPlanMapper;
import com.ruoyi.system.mapper.CoalMineWorkingDayMapper;
import com.ruoyi.system.service.BaoBiao.IWashCoalPlanService;
import com.ruoyi.system.service.ISysDeptService;
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
public class WashCoalPlanServiceImpl implements IWashCoalPlanService {

    @Resource
    private WashCoalPlanMapper planMapper;
    @Resource
    private SubWashCoalPlanMapper subMapper;

    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作天数
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(WashCoalPlanCreateDTO dto) {
        WashCoalPlanPO po = toPO(dto);
        WashCoalPlanPO planPO = planMapper.selectByPlan(po.getPlanMonth());
//        String yue1 = DateUtils.returnDateDay(dto.getPlan_month());
        if(planPO !=null){
            List<SubWashCoalPlanPO> subWashCoalPlanPOS = subMapper.selectByPlanId(planPO.getId());
            if(subWashCoalPlanPOS.stream().filter(x -> x.getUnitCode().equals(dto.getData_JSON().get(0).getUnit_code())).count() > 0){
                SubWashCoalPlanPO subWashCoalPlanPO = subWashCoalPlanPOS.stream().filter(x -> x.getUnitCode().equals(dto.getData_JSON().get(0).getUnit_code())).findFirst().orElse(null);
                if(subWashCoalPlanPO.getIsDeleted()==0){
                    return Long.valueOf(0);
                }
            }
        }

        CoalMineWorkingDay coalMineWorkingDay = new CoalMineWorkingDay();
        coalMineWorkingDay.setUnitName(dto.getData_JSON().get(0).getUnit_name());
        coalMineWorkingDay.setWorkingMonth(dto.getPlan_month());
        List<CoalMineWorkingDay> coalMineWorkingDays = coalMineWorkingDayMapper.selectCoalMineWorkingDayList(coalMineWorkingDay);

        WashCoalPlanUnitItem washCoalPlanUnitItem = dto.getData_JSON().get(0);
        List<Long> 入洗日计划 = NumUtils.splitAverageInterleaved(washCoalPlanUnitItem.getWash_in_month_plan(), dto.getWork_days_in_month());
        List<Long> 精煤量日计划 = NumUtils.splitAverageInterleaved(washCoalPlanUnitItem.getClean_coal_month_plan(), dto.getWork_days_in_month());
        List<Long> 精煤量日计划车数 = NumUtils.splitAverageInterleaved(BigDecimal.valueOf(washCoalPlanUnitItem.getClean_coal_month_car_plan()), dto.getWork_days_in_month());
        List<Long> 末煤日计划 = NumUtils.splitAverageInterleaved(washCoalPlanUnitItem.getSlack_coal_month_plan(), dto.getWork_days_in_month());
        List<Long> 末煤日计划车数 = NumUtils.splitAverageInterleaved(BigDecimal.valueOf(washCoalPlanUnitItem.getSlack_coal_month_car_plan()), dto.getWork_days_in_month());
        if (coalMineWorkingDays.size() > 入洗日计划.size()) {
            throw new IllegalStateException("工作日数量超过计划数据长度");
        }
        int num = 0;
        for (int i = 0; i < coalMineWorkingDays.size(); i++) {
            CoalMineWorkingDay coalDay = new CoalMineWorkingDay();
            if (coalMineWorkingDays.get(i).getWhetherWorking() == 1) {
                coalDay.setProductionPlan(入洗日计划.get(num));//入洗日计划
                coalDay.setProductionObjective(精煤量日计划.get(num));//精煤日计划
                coalDay.setExplorationPlan(精煤量日计划车数.get(num));//精煤日计划车数
                coalDay.setExplorationObjective(末煤日计划.get(num));//块末日计划
                coalDay.setFootagePlan(末煤日计划车数.get(num));//块末日计划车数
                num++;
            } else {
                coalDay.setProductionPlan(0L);//入洗日计划
                coalDay.setProductionObjective(0L);//精煤日计划
                coalDay.setExplorationPlan(0L);//精煤日计划车数
                coalDay.setExplorationObjective(0L);//块末日计划
                coalDay.setFootagePlan(0L);//块末日计划车数
            }
            coalDay.setUnitName(coalMineWorkingDays.get(i).getUnitName());
            coalDay.setUnitCode(coalMineWorkingDays.get(i).getUnitCode());
            coalDay.setWorkingMonth(dto.getPlan_month());
            coalDay.setWorkingDays((long) i + 1);
            coalMineWorkingDayMapper.updateCoalMineWorkingDays(coalDay);
        }


        if (planPO == null) {
            po.setIsDeleted(0);
            planMapper.insert(po);
            Long planId = po.getId();
            List<SubWashCoalPlanPO> subs = toSubPOList(planId, dto.getData_JSON());
            if (!subs.isEmpty()) subMapper.batchInsert(subs);
            return planId;
        } else {
            Long planId = planPO.getId();
            List<SubWashCoalPlanPO> subs = toSubPOList(planId, dto.getData_JSON());
            for (SubWashCoalPlanPO subpo : subs) {

                SubWashCoalPlanPO subWashCoalPlanPO = subMapper.selectByPlanIdUnitName(subpo.getUnitName(), planId);

                if (subWashCoalPlanPO.getIsDeleted() != 2) {
                    return Long.valueOf(0);
                }
                subpo.setIsDeleted(0);
                subMapper.uopDate(subpo);
            }
            return planId;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(WashCoalPlanUpdateDTO dto) {
        WashCoalPlanPO po = toPO(dto);
        po.setId(dto.getId());
        int n = planMapper.update(po);
        // 简单策略：全量替换子表
        subMapper.deleteByPlanId(dto.getId());
        List<SubWashCoalPlanPO> subs = toSubPOList(dto.getId(), dto.getData_JSON());
        if (!subs.isEmpty()) subMapper.batchInsert(subs);
        return n;
    }

    @Override
    public int remove(Long id) {
        // 软删主表；子表不删（查询时不返回主表=1的数据）
        return planMapper.softDeleteById(id);
    }

    @Override
    public WashCoalPlanVO get(Long id) {
        WashCoalPlanPO po = planMapper.selectById(id);
        if (po == null) return null;
        List<SubWashCoalPlanPO> subs = subMapper.selectByPlanId(id);
        return toVO(po, subs);
    }

    @Override
    public List<WashCoalPlanVO> page(WashCoalPlanPageQueryDTO query) {
        WashCoalPlanPO c = new WashCoalPlanPO();
        c.setMineCategory(query.getMine_category());
        c.setUserId(query.getUser_id());
        c.setIsDeleted(query.getIs_deleted());
        Date month = normalizeMonth(query.getPlan_month());
        if (month != null) c.setPlanMonth(month);

        List<WashCoalPlanPO> masters = planMapper.selectList(c);
        if (masters.isEmpty()) return Collections.emptyList();

        if (masters.size() > 1) {
            throw new IllegalStateException("计划数据异常");
        }
        WashCoalPlanPO planPO = masters.get(0);
//        List<Long> ids = masters.stream().map(WashCoalPlanPO::getId).collect(Collectors.toList());
//        List<SubWashCoalPlanPO> allSubs = subMapper.selectByPlanIds(ids);
        List<SubWashCoalPlanPO> allSubs = subMapper.selectByPlanId(planPO.getId());






        Map<Long, List<SubWashCoalPlanPO>> group = allSubs.stream().collect(Collectors.groupingBy(SubWashCoalPlanPO::getWashCoalPlanId, LinkedHashMap::new, Collectors.toList()));
        List<WashCoalPlanVO> out = new ArrayList<>(masters.size());

        List<SubWashCoalPlanPO> subs = group.getOrDefault(planPO.getId(), Collections.emptyList());
        if (StrUtil.isNotEmpty(query.getUnit_code())) {

            CoalMineWorkingDay coalMineWorkingDay = new CoalMineWorkingDay();
            coalMineWorkingDay.setUnitCode(query.getUnit_code());
            coalMineWorkingDay.setWorkingMonth(query.getPlan_month());

            List<CoalMineWorkingDay> coalMineWorkingDays = coalMineWorkingDayMapper.selectCoalMineWorkingDayList(coalMineWorkingDay);
            int 工作天数 = (int) coalMineWorkingDays.stream().filter(x -> x.getWhetherWorking() == 1).count();
            planPO.setWorkDaysInMonth(工作天数);


            List<SubWashCoalPlanPO> collect = subs.stream().filter(swc -> StrUtil.isNotEmpty(swc.getUnitCode()) && query.getUnit_code().equals(swc.getUnitCode())).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(collect)) {
                out.add(toVO(planPO, subs));
            }
        }else {
            List<SubWashCoalPlanPO> Allsubs=new ArrayList<>();
            FactoryArchive factory=new FactoryArchive();
            factory.setIsSealed(0);
            List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factory);

            for (FactoryArchive factoryArchive:factoryArchives ) {

                List<SubWashCoalPlanPO> collect = subs.stream().filter(swc -> StrUtil.isNotEmpty(swc.getUnitCode()) && factoryArchive.getFactoryCode().equals(swc.getUnitCode())).collect(Collectors.toList());
                if (CollUtil.isNotEmpty(collect)) {
                    Allsubs.addAll(collect);
                }else {
                    List<SubWashCoalPlanPO> l1=new ArrayList<>();
                    SubWashCoalPlanPO subWash = new SubWashCoalPlanPO();
                    subWash.setUnitCode(factoryArchive.getFactoryCode());
                    subWash.setUnitName(factoryArchive.getFactoryName());
                    l1.add(subWash);
                    Allsubs.addAll(l1);
                }
            }


            out.add(toVO(planPO, Allsubs));
        }
        return out;
    }

    @Override
    public int deleteData(WashCoalPlanV1 query) {
        java.util.Date yue = DateUtils.getFirstDayOfMonth(query.getPlanMonth());
        WashCoalPlanPO planPO = planMapper.selectByPlan(yue);
        if (planPO != null) {
            return subMapper.deleteByMddIdAll(query.getUnitName(), planPO.getId());
        }
        return 0;
    }

    // ===== Helpers =====

    private WashCoalPlanPO toPO(WashCoalPlanCreateDTO dto) {
        WashCoalPlanPO po = new WashCoalPlanPO();
        po.setPlanMonth(normalizeMonth(dto.getPlan_month()));
        po.setWorkDaysInMonth(dto.getWork_days_in_month());
        po.setUserId(dto.getUser_id());
        po.setMineCategory(dto.getMine_category());
        return po;
    }

    private List<SubWashCoalPlanPO> toSubPOList(Long planId, List<WashCoalPlanUnitItem> items) {
        List<SubWashCoalPlanPO> list = new ArrayList<>();
        if (items == null) return list;
        for (WashCoalPlanUnitItem it : items) {
            SubWashCoalPlanPO s = new SubWashCoalPlanPO();
            s.setWashCoalPlanId(planId);
            s.setUnitCode(it.getUnit_code());
            s.setUnitName(it.getUnit_name());
            s.setIsDeleted(0);

            s.setWashInMonthPlan(it.getWash_in_month_plan());
            s.setWashInDayPlan(it.getWash_in_day_plan());

            s.setCleanCoalMonthPlan(it.getClean_coal_month_plan());
            s.setCleanCoalDayPlan(it.getClean_coal_day_plan());
            s.setCleanCoalMonthCarPlan(it.getClean_coal_month_car_plan());
            s.setCleanCoalDayCarPlan(it.getClean_coal_day_car_plan());

            s.setSlackCoalMonthPlan(it.getSlack_coal_month_plan());
            s.setSlackCoalDayPlan(it.getSlack_coal_day_plan());
            s.setSlackCoalMonthCarPlan(it.getSlack_coal_month_car_plan());
            s.setSlackCoalDayCarPlan(it.getSlack_coal_day_car_plan());
            list.add(s);
        }
        return list;
    }

    private WashCoalPlanVO toVO(WashCoalPlanPO m, List<SubWashCoalPlanPO> subs) {
        WashCoalPlanVO vo = new WashCoalPlanVO();
        vo.setId(m.getId());
        vo.setPlan_month(m.getPlanMonth());
        vo.setWork_days_in_month(m.getWorkDaysInMonth());
        vo.setUser_id(m.getUserId());
        vo.setMine_category(m.getMineCategory());

        String str = deptService.getisbool();
        if (str == null) {
            List<WashCoalPlanUnitItem> items = new ArrayList<>();
            for (SubWashCoalPlanPO s : subs) {
                WashCoalPlanUnitItem it = new WashCoalPlanUnitItem();
                it.setUnit_code(s.getUnitCode());
                it.setUnit_name(s.getUnitName());
                it.setIs_deleted(s.getIsDeleted());
                it.setWash_in_month_plan(s.getWashInMonthPlan());
                it.setWash_in_day_plan(s.getWashInDayPlan());
                it.setClean_coal_month_plan(s.getCleanCoalMonthPlan());
                it.setClean_coal_day_plan(s.getCleanCoalDayPlan());
                it.setClean_coal_month_car_plan(s.getCleanCoalMonthCarPlan());
                it.setClean_coal_day_car_plan(s.getCleanCoalDayCarPlan());
                it.setSlack_coal_month_plan(s.getSlackCoalMonthPlan());
                it.setSlack_coal_day_plan(s.getSlackCoalDayPlan());
                it.setSlack_coal_month_car_plan(s.getSlackCoalMonthCarPlan());
                it.setSlack_coal_day_car_plan(s.getSlackCoalDayCarPlan());
                items.add(it);
            }
            vo.setData_JSON(items);
        }
        if (str != null) {
            List<WashCoalPlanUnitItem> items = new ArrayList<>();
            for (SubWashCoalPlanPO s : subs) {
                if (str.equals(s.getUnitName())) {
                    WashCoalPlanUnitItem it = new WashCoalPlanUnitItem();
                    it.setUnit_code(s.getUnitCode());
                    it.setUnit_name(s.getUnitName());

                    it.setIs_deleted(s.getIsDeleted());

                    it.setWash_in_month_plan(s.getWashInMonthPlan());
                    it.setWash_in_day_plan(s.getWashInDayPlan());

                    it.setClean_coal_month_plan(s.getCleanCoalMonthPlan());
                    it.setClean_coal_day_plan(s.getCleanCoalDayPlan());
                    it.setClean_coal_month_car_plan(s.getCleanCoalMonthCarPlan());
                    it.setClean_coal_day_car_plan(s.getCleanCoalDayCarPlan());

                    it.setSlack_coal_month_plan(s.getSlackCoalMonthPlan());
                    it.setSlack_coal_day_plan(s.getSlackCoalDayPlan());
                    it.setSlack_coal_month_car_plan(s.getSlackCoalMonthCarPlan());
                    it.setSlack_coal_day_car_plan(s.getSlackCoalDayCarPlan());
                    items.add(it);
                }
            }
            vo.setData_JSON(items);
        }

        return vo;
    }

    private Date normalizeMonth(String any) {
        if (any == null || any.trim().isEmpty()) return null;
        String s = any.trim();
        // 取“年-月”的1号
        try {
            if (s.length() >= 7) {
                String ym = s.substring(0, 7); // yyyy-MM
                LocalDate d = LocalDate.parse(ym + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return Date.valueOf(d);
            }
            // 兜底：直接当 yyyy-MM-dd
            LocalDate d = LocalDate.parse(s.substring(0, Math.min(10, s.length())), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return Date.valueOf(LocalDate.of(d.getYear(), d.getMonth(), 1));
        } catch (Exception e) {
            // 再兜底：强切前10位
            LocalDate d = LocalDate.parse(s.substring(0, Math.min(10, s.length())), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return Date.valueOf(LocalDate.of(d.getYear(), d.getMonth(), 1));
        }
    }
}

