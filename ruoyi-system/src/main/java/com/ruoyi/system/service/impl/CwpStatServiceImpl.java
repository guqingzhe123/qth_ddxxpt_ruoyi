package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.CwpDailySumRow;
import com.ruoyi.system.domain.CwpDailySummaryItemVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;
import com.ruoyi.system.mapper.CwpStatMapper;
import com.ruoyi.system.service.ICwpStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CwpStatServiceImpl implements ICwpStatService {

    private final CwpStatMapper cwpStatMapper;

    @Override
     public List<CwpDailySummaryItemVO> dailySummary(EemSevenDayReq.CwpDailySummaryReq req) {
        LocalDate theDate = parseFlexibleDate(req.getRecord_date());

        CwpDailySumRow row = cwpStatMapper.selectDailySums(theDate);
        if (row == null) {
            row = new CwpDailySumRow();
            row.setSumDropIn(BigDecimal.ZERO);
            row.setSumWashIn(BigDecimal.ZERO);
            row.setSumCleanCoal(BigDecimal.ZERO);
        }

        BigDecimal dropIn = nvl(row.getSumDropIn());
        BigDecimal washIn = nvl(row.getSumWashIn());
        BigDecimal clean  = nvl(row.getSumCleanCoal());

        // 按你的说明：精煤产率百分比 = (clean_coal / wash_in) * 100
        BigDecimal yieldPct = BigDecimal.ZERO;
        if (washIn.signum() != 0) {
            yieldPct = clean
                    .divide(washIn, 6, RoundingMode.HALF_UP) // 精煤 ÷ 入洗
                    .multiply(new BigDecimal("100"))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        CwpDailySummaryItemVO vo = new CwpDailySummaryItemVO();
        vo.setTotalDropIn(dropIn.setScale(3, RoundingMode.HALF_UP));
        vo.setTotalWashIn(washIn.setScale(3, RoundingMode.HALF_UP));
        vo.setTotalCleanCoal(clean.setScale(3, RoundingMode.HALF_UP));
        vo.setYieldPercent(yieldPct); // 已保留两位小数

        // 按你的返回格式：外层是数组
        return Collections.singletonList(vo);
    }

    private BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    /** 兼容 'yyyy-M-d' 与 'yyyy-MM-dd' */
    private LocalDate parseFlexibleDate(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("record_date 不能为空");
        }
        s = s.trim();
        List<DateTimeFormatter> formats = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );
        for (DateTimeFormatter f : formats) {
            try { return LocalDate.parse(s, f); } catch (Exception ignore) {}
        }
        throw new IllegalArgumentException("record_date 格式不正确，应为 yyyy-MM-dd");
    }
}
