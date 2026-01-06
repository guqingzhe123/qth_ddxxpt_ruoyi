package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV2RequestDTO;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV2VO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import com.ruoyi.system.domain.CoalMineWorkingDay;
import com.ruoyi.system.mapper.BaoBiao.MineDayCumReportV2Mapper;
import com.ruoyi.system.service.BaoBiao.IMineDayCumReportV2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MineDayCumReportV2ServiceImpl implements IMineDayCumReportV2Service {

    @Resource
    private MineDayCumReportV2Mapper mapper;

    private static final String TYPE_PROD = "生产";
    private static final String TYPE_ADV  = "进尺";
    private static final String TYPE_DEV  = "开拓";

    //private static final String PLAN_PROD = "生产";
    //private static final String PLAN_ADV  = "进尺";
    //private static final String PLAN_DEV  = "开拓";

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<MineDayCumReportV2VO> buildReport(MineDayCumReportV2RequestDTO dto) {
        Objects.requireNonNull(dto, "请求体不能为空");
        if (StringUtils.isEmpty(dto.getRecordDate())) {
            throw new IllegalArgumentException("recordDate 不能为空（yyyy-MM-dd）");
        }
        LocalDate theDay = LocalDate.parse(dto.getRecordDate(), DTF);
        LocalDate monthStart = theDay.withDayOfMonth(1);
        String dayStr = theDay.format(DTF);
        String monthStartStr = monthStart.format(DTF);
        int days = theDay.getDayOfMonth(); // 自然日累计到今天
        String ri = theDay.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        Boolean includeSealed = dto.getIncludeSealed() != null && dto.getIncludeSealed();
        List<String> filters = null;
        if (StringUtils.isNotEmpty(dto.getUnitNamesCsv())) {
            filters = Arrays.stream(dto.getUnitNamesCsv().split(","))
                    .map(String::trim)
                    .filter(StringUtils::isNotEmpty)
                    .distinct()
                    .collect(Collectors.toList());
        }

        // 1) 一级矿
        List<String> mines = mapper.selectTopLevelMines(includeSealed, filters);
        if (mines == null || mines.isEmpty()) return Collections.emptyList();

        List<MineDayCumReportV2VO> result = new ArrayList<>(mines.size());
        for (String unitName : mines) {
            MineDayCumReportV2VO vo = new MineDayCumReportV2VO();
            vo.setUnitName(unitName);

            MineData 当日   = mapper.sumDailyOutput(unitName, dayStr);//当日完成
            MineData 月累计完成   = mapper.sumCumOutput(unitName, monthStartStr, dayStr);//累完成（data_type=生产，月初至今）
            CoalMineWorkingDay 月累计计划   = mapper.sumDayPlanForMine(unitName, ri,days);//累计划（生产：day_plan×日至今）

            // 当日产量相关
            if (当日 != null && 当日.getProductionData() != null) {
                vo.setProductionDaily(BigDecimal.valueOf(当日.getProductionData()));
            }

// 月累计计划产量
            if (月累计计划 != null && 月累计计划.getProductionPlan() != null) {
                vo.setProductionPlanCum(BigDecimal.valueOf(月累计计划.getProductionPlan()));
            }

// 月累计完成产量
            if (月累计完成 != null && 月累计完成.getProductionData() != null) {
                vo.setProductionDoneCum(BigDecimal.valueOf(月累计完成.getProductionData()));
            }

// --- 进尺 ---
// 当日进尺
            if (当日 != null && 当日.getFootageData() != null) {
                vo.setAdvanceDaily(BigDecimal.valueOf(当日.getFootageData()));
            }

// 月累计完成进尺
            if (月累计完成 != null && 月累计完成.getFootageData() != null) {
                vo.setAdvanceDoneCum(BigDecimal.valueOf(月累计完成.getFootageData()));
            }

// 月累计计划进尺
            if (月累计计划 != null && 月累计计划.getFootagePlan() != null) {
                vo.setAdvancePlanCum(BigDecimal.valueOf(月累计计划.getFootagePlan()));
            }

// --- 开拓 ---
// 当日开拓
            if (当日 != null && 当日.getExpandData() != null) {
                vo.setDevelopDaily(BigDecimal.valueOf(当日.getExpandData()));
            }

// 月累计完成开拓
            if (月累计完成 != null && 月累计完成.getExpandData() != null) {
                vo.setDevelopDoneCum(BigDecimal.valueOf(月累计完成.getExpandData()));
            }

// 月累计计划开拓（注意：原方法名可能存在拼写错误，ExploratioPlan 应为 ExplorationPlan，需确认）
            if (月累计计划 != null && 月累计计划.getExplorationPlan() != null) {
                vo.setDevelopPlanCum(BigDecimal.valueOf(月累计计划.getExplorationPlan()));
            }

//            BigDecimal advDaily   = mapper.sumDailyOutput(unitName, dayStr, TYPE_ADV);
//            BigDecimal advCumDone = mapper.sumCumOutput(unitName, monthStartStr, dayStr, TYPE_ADV);
//            BigDecimal advDayPlan = mapper.sumDayPlanForMine(unitName, monthStartStr, TYPE_ADV);
//            BigDecimal advCumPlan = safeMul(advDayPlan, days);



//            BigDecimal devDaily   = mapper.sumDailyOutput(unitName, dayStr, TYPE_DEV);
//            BigDecimal devCumDone = mapper.sumCumOutput(unitName, monthStartStr, dayStr, TYPE_DEV);
//            BigDecimal devDayPlan = mapper.sumDayPlanForMine(unitName, monthStartStr, TYPE_DEV);
//            BigDecimal devCumPlan = safeMul(devDayPlan, days);



            result.add(vo);
        }
        return result;
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static BigDecimal safeMul(BigDecimal v, int n) {
        if (v == null) return BigDecimal.ZERO;
        return v.multiply(BigDecimal.valueOf(n));
    }
}
