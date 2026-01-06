package com.ruoyi.system.service.impl;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;
import com.ruoyi.system.mapper.CoalMineWorkingDayMapper;
import com.ruoyi.system.mapper.MddStatMapper;
import com.ruoyi.system.mapper.MinePlanDayMapper;
import com.ruoyi.system.service.IMddStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MddStatServiceImpl implements IMddStatService {

    @Resource
    private MddStatMapper mapper;
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;//工作日Mapper接口

    @Autowired
    private MinePlanDayMapper minePlanDayMapper;//每日计划  煤矿自己看自己的计划

    private static final String TYPE_PRODUCTION = "生产"; // 对应“生产”
    private static final String TYPE_ADVANCE    = "进尺"; // 对应“进尺”
    private static final String TYPE_TUNNELING  = "开拓"; // 对应“开拓”

    private static final int SEP_BRANCH = 0; // 分公司
    private static final int SEP_QIMEI  = 1; // 七煤公司

    @Override
    public List<Object> summaryByDate(EemSevenDayReq.MddStatQueryDTO dto) {
        LocalDate day = dto.getRecord_date();
        LocalDate start = day.minusDays(6);
        LocalDate end = day;
        String yearMonth = day.format(DateTimeFormatter.ofPattern("yyyy-MM"));
// 获取日期中的日部分
        int dayOfMonth = day.getDayOfMonth();

        // 1) 顶部汇总
        MddDailyOverviewVO top = new MddDailyOverviewVO();
        // 生产
        MineData mineData = mapper.sumOutputByDateAndType(day);
        if(mineData !=null){
            top.setCompanyProduction(mineData.getProductionData());//全公司生产
            top.setCompanyAdvance(mineData.getFootageData());//全公司生产
            top.setCompanyTunneling(mineData.getExpandData());//全公司开拓
        }
//        CoalMineWorkingDay coalMineWorkingDay = coalMineWorkingDayMapper.selectCoalMineWorkingMonth(yearMonth, dayOfMonth);

        String yue1 = DateUtils.returnDateDay(String.valueOf(end));
        MinePlanDay minday = new MinePlanDay();
        minday.setPlanType("生产");
        minday.setPlanMonth(yue1);
        minday.setPlanDay(dayOfMonth);
        minday.setStatus(0);
        List<MinePlanDay> 生产月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanType("开拓");
        List<MinePlanDay> 开拓月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
        minday.setPlanType("进尺");
        List<MinePlanDay> 进尺月计划 = minePlanDayMapper.selectMinePlanMonth(minday);

        top.setBranchProduction(Math.toIntExact(生产月计划.stream().mapToInt(po -> toInt(po.getDayPlan())).sum()));
        top.setQimeiProduction(Math.toIntExact( 生产月计划.stream().mapToInt(po -> toInt(po.getDayTarget())).sum()));
        top.setBranchAdvance(Math.toIntExact(进尺月计划.stream().mapToInt(po -> toInt(po.getDayPlan())).sum()));
        top.setQimeiAdvance(Math.toIntExact(进尺月计划.stream().mapToInt(po -> toInt(po.getDayTarget())).sum()));
        top.setBranchTunneling(Math.toIntExact(开拓月计划.stream().mapToInt(po -> toInt(po.getDayPlan())).sum()));
        top.setQimeiTunneling(Math.toIntExact(开拓月计划.stream().mapToInt(po -> toInt(po.getDayTarget())).sum()));

        //top.setBranchProduction(z(mapper.PlanData(day, TYPE_PRODUCTION)));
        //top.setQimeiProduction (z(mapper.targetData(day, TYPE_PRODUCTION)));
        // 进尺
        //top.setBranchAdvance(z(mapper.PlanData(day, TYPE_ADVANCE)));
        //top.setQimeiAdvance (z(mapper.targetData(day, TYPE_ADVANCE)));
        // 开拓
        //top.setBranchTunneling(z(mapper.PlanData(day, TYPE_TUNNELING)));
        //top.setQimeiTunneling (z(mapper.targetData(day, TYPE_TUNNELING)));


        // 拿到数据库已有的点
        List<MineData> rows = mapper.sevenDaySeriesProductionAndPlanning(start, end);
        // 转成 map 便于填补
        LinkedHashMap<String, Integer> 生产 = new LinkedHashMap<>();//生产
        LinkedHashMap<String, Integer> 开拓 = new LinkedHashMap<>();//开拓
        LinkedHashMap<String, Integer> 进尺 = new LinkedHashMap<>();//进尺
        if (rows != null) {
            for (MineData r : rows) {
                生产.put(r.getRecordDate(), r.getProductionData());
                开拓.put(r.getRecordDate(), r.getExpandData());
                进尺.put(r.getRecordDate(), r.getFootageData());
            }
        }


        // 2) 七日生产  -- 近七日  生产数据 和计划数据
        MddSevenDayProductionVO prod7 = new MddSevenDayProductionVO();
        prod7.setBranchSevenDays(seven(start, end, 生产));
        prod7.setPlanSevenDays (sevendayPlanData(start, end,day, TYPE_PRODUCTION));

        // 3) 七日进尺
        MddSevenDayAdvanceVO adv7 = new MddSevenDayAdvanceVO();
        adv7.setBranchSevenDays(seven(start, end, 进尺));
        adv7.setQimeiSevenDays (fill7(start, end, TYPE_ADVANCE, SEP_QIMEI));

        // 4) 七日开拓
        MddSevenDayTunnelingVO tun7 = new MddSevenDayTunnelingVO();
        tun7.setBranchSevenDays(seven(start, end, 开拓));
        tun7.setQimeiSevenDays (fill7(start, end, TYPE_TUNNELING, SEP_QIMEI));

        // 返回顺序必须与前端预期一致
        List<Object> result = new ArrayList<>();
        result.add(top);
        result.add(prod7);
        result.add(adv7);
        result.add(tun7);
        return result;
    }

    /**
     * 将 null 安全转 0
     */
    private Integer z(BigDecimal v) {
        if (v == null) {
            return null;
        }
        BigDecimal integerBd = v.setScale(0, RoundingMode.DOWN); // DOWN 规则：直接截断小数位（推荐）

        // 3. 范围校验：避免超出 Integer 取值范围导致溢出
        BigDecimal minInt = new BigDecimal(Integer.MIN_VALUE); // -2147483648
        BigDecimal maxInt = new BigDecimal(Integer.MAX_VALUE); // 2147483647

        if (integerBd.compareTo(minInt) < 0) {
            throw new ArithmeticException(String.format("数值 [%s] 舍去小数后为 [%s]，超出 Integer 最小值 [%d]",
                    v, integerBd, Integer.MIN_VALUE));
        }
        if (integerBd.compareTo(maxInt) > 0) {
            throw new ArithmeticException(String.format("数值 [%s] 舍去小数后为 [%s]，超出 Integer 最大值 [%d]",
                    v, integerBd, Integer.MAX_VALUE));
        }

        // 4. 安全转换为 Integer
        return integerBd.intValue();
    }

    /** 构造连续 7 天序列：将缺失日期用 0 填充，并仅返回 { "总合": x } 的列表 */
    private List<SeriesPointVO> fill7(LocalDate start, LocalDate end, String dataType, int isSeparate) {
        // 拿到数据库已有的点
        List<DateTotalRow> rows = mapper.sevenDaySeries(start, end, isSeparate);

        // 转成 map 便于填补
        LinkedHashMap<LocalDate, Integer> map = new LinkedHashMap<>();
        if (rows != null) {
            for (DateTotalRow r : rows) {
                map.put(r.getRecordDate(), r.getTotal());
            }
        }

        List<SeriesPointVO> list = new ArrayList<>();
        LocalDate d = start;
        while (!d.isAfter(end)) {
            Integer val = map.getOrDefault(d, 0);
            SeriesPointVO p = new SeriesPointVO();
            p.setTotal(val);
            list.add(p);
            d = d.plusDays(1);
        }
        return list;
    }

    private List<SeriesPointVO> seven(LocalDate start, LocalDate end, LinkedHashMap<String, Integer> map) {

        List<SeriesPointVO> list = new ArrayList<>();
        LocalDate d = start;
        while (!d.isAfter(end)) {
            Integer val = map.getOrDefault(d.toString(), 0);
            SeriesPointVO p = new SeriesPointVO();
            p.setTotal(val);
            list.add(p);
            d = d.plusDays(1);
        }
        return list;
    }


    private List<SeriesPointVO> sevendayProductionData(LocalDate start, LocalDate end, String dataType) {
        // 拿到数据库已有的点
        List<MineData> rows = mapper.sevenDaySeriesProductionAndPlanning(start, end);

        // 转成 map 便于填补
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        if (rows != null) {
            for (MineData r : rows) {
                map.put(r.getRecordDate(), r.getProductionData());
            }
        }

        List<SeriesPointVO> list = new ArrayList<>();
        LocalDate d = start;
        while (!d.isAfter(end)) {
            Integer val = map.getOrDefault(d, 0);
            SeriesPointVO p = new SeriesPointVO();
            p.setTotal(val);
            list.add(p);
            d = d.plusDays(1);
        }
        return list;
    }

    private List<SeriesPointVO> sevendayPlanData(LocalDate start, LocalDate end,LocalDate day, String dataType) {

        List<SeriesPointVO> list = new ArrayList<>();
        LocalDate d = start;
        while (!d.isAfter(end)) {
            int dayOfMonth = d.getDayOfMonth();
            String yue1 = DateUtils.returnDateDay(String.valueOf(d));
            MinePlanDay minday = new MinePlanDay();
            minday.setPlanType("生产");
            minday.setPlanMonth(yue1);
            minday.setPlanDay(dayOfMonth);
            minday.setStatus(0);
            List<MinePlanDay> 生产月计划 = minePlanDayMapper.selectMinePlanMonth(minday);
            SeriesPointVO p = new SeriesPointVO();
            p.setTotal(Math.toIntExact(生产月计划.stream().mapToInt(po -> toInt(po.getDayPlan())).sum()));
            list.add(p);
            d = d.plusDays(1);
        }
        return list;
    }
    private static int toInt(Integer value) {
        return Optional.ofNullable(value).orElse(0);
    }

}
