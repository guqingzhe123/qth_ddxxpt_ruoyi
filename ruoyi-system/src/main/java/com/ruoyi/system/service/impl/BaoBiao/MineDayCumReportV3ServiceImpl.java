package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV3RequestDTO;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV3VO;
import com.ruoyi.system.mapper.BaoBiao.MineDayCumReportV3Mapper;
import com.ruoyi.system.service.BaoBiao.IMineDayCumReportV3Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MineDayCumReportV3ServiceImpl implements IMineDayCumReportV3Service {

    @Resource
    private MineDayCumReportV3Mapper mapper;

    // 业务常量
    private static final String TYPE_PROD = "生产"; // 生产
    private static final String TYPE_ADV  = "进尺"; // 进尺
    private static final String TYPE_DEV  = "开拓"; // 开拓

    private static final String PLAN_PROD = "生产";
    private static final String PLAN_ADV  = "进尺";
    private static final String PLAN_DEV  = "开拓";

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<MineDayCumReportV3VO> buildReport(MineDayCumReportV3RequestDTO dto) {
        Objects.requireNonNull(dto, "请求体不能为空");
        if (StringUtils.isEmpty(dto.getRecordDate())) {
            throw new IllegalArgumentException("record_date 不能为空（yyyy-MM-dd）");
        }

        LocalDate theDay = LocalDate.parse(dto.getRecordDate(), DTF);
        LocalDate monthStart = theDay.withDayOfMonth(1);
        String dayStr = theDay.format(DTF);
        String monthStartStr = monthStart.format(DTF);
        int days = theDay.getDayOfMonth(); // 自然日累计（含当日）

        Boolean includeSealed = dto.getIncludeSealed() != null && dto.getIncludeSealed();
        List<String> filters = null;
        if (StringUtils.isNotEmpty(dto.getUnitNamesCsv())) {
            filters = Arrays.stream(dto.getUnitNamesCsv().split(","))
                    .map(String::trim)
                    .filter(StringUtils::isNotEmpty)
                    .distinct()
                    .collect(Collectors.toList());
        }

        // 1) 矿列表（一级）
        List<String> mines = mapper.selectTopLevelMines(includeSealed, filters);
        if (mines == null || mines.isEmpty()) return Collections.emptyList();

        List<MineDayCumReportV3VO> list = new ArrayList<>(mines.size());

        for (String unitName : mines) {
            // --- 生产 ---
            BigDecimal prodDaily   = mapper.sumDailyOutput(unitName, dayStr, TYPE_PROD);
            BigDecimal prodCumDone = mapper.sumCumOutput(unitName, monthStartStr, dayStr, TYPE_PROD);
            BigDecimal prodDayPlan = mapper.sumDayPlanForMine(unitName, monthStartStr, PLAN_PROD);
            BigDecimal prodCumPlan = mul(prodDayPlan, days);

            // --- 进尺 ---
            BigDecimal advDaily   = mapper.sumDailyOutput(unitName, dayStr, TYPE_ADV);
            BigDecimal advCumDone = mapper.sumCumOutput(unitName, monthStartStr, dayStr, TYPE_ADV);
            BigDecimal advDayPlan = mapper.sumDayPlanForMine(unitName, monthStartStr, PLAN_ADV);
            BigDecimal advCumPlan = mul(advDayPlan, days);

            // --- 开拓 ---
            BigDecimal devDaily   = mapper.sumDailyOutput(unitName, dayStr, TYPE_DEV);
            BigDecimal devCumDone = mapper.sumCumOutput(unitName, monthStartStr, dayStr, TYPE_DEV);
            BigDecimal devDayPlan = mapper.sumDayPlanForMine(unitName, monthStartStr, PLAN_DEV);
            BigDecimal devCumPlan = mul(devDayPlan, days);

            MineDayCumReportV3VO vo = new MineDayCumReportV3VO();
            vo.setUnitName(unitName);

            // 生产
            vo.setProductionDaily(nz(prodDaily));
            vo.setProductionActual(nz(prodDaily));     // “实际”=当日完成
            vo.setProductionPlanCum(nz(prodCumPlan));
            vo.setProductionDoneCum(nz(prodCumDone));

            // 进尺
            vo.setAdvanceDaily(nz(advDaily));
            vo.setAdvanceActual(nz(advDaily));         // “实际”=当日完成
            vo.setAdvancePlanCum(nz(advCumPlan));
            vo.setAdvanceDoneCum(nz(advCumDone));

            // 开拓
            vo.setDevelopDaily(nz(devDaily));
            vo.setDevelopActual(nz(devDaily));         // “实际”=当日完成
            vo.setDevelopPlanCum(nz(devCumPlan));
            vo.setDevelopDoneCum(nz(devCumDone));

            list.add(vo);
        }
        return list;
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static BigDecimal mul(BigDecimal v, int n) {
        if (v == null) return BigDecimal.ZERO;
        return v.multiply(BigDecimal.valueOf(n));
    }
}
