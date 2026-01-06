package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.PesDateAggRow;
import com.ruoyi.system.domain.PesSevenDayRespGroupVO;
import com.ruoyi.system.domain.PesTotalItemVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;
import com.ruoyi.system.mapper.BaoBiao.PesStatMapper;
import com.ruoyi.system.service.IPesStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PesStatServiceImpl implements IPesStatService {

    private final PesStatMapper pesStatMapper;

    @Override
    public List<PesSevenDayRespGroupVO> sevenDayTotals(EemSevenDayReq.PesSevenDayReq req) {
        LocalDate end = parseFlexibleDate(req.getRecord_date());
        LocalDate start = end.minusDays(6); // 向前6天，共7天

        // 查询数据库
        List<PesDateAggRow> carRows = pesStatMapper.selectDailyCarTotals(start, end);
        List<PesDateAggRow> tonRows = pesStatMapper.selectDailyTonnageTotals(start, end);

        // 转 map：day -> total
        Map<LocalDate, BigDecimal> carMap = carRows.stream()
                .collect(Collectors.toMap(PesDateAggRow::getDay, r -> nvl(r.getTotal()), (a,b) -> a));
        Map<LocalDate, BigDecimal> tonMap = tonRows.stream()
                .collect(Collectors.toMap(PesDateAggRow::getDay, r -> nvl(r.getTotal()), (a,b) -> a));

        // 生成 [start..end] 的 7 天列表，按天填充，缺失补 0（升序）
        List<PesTotalItemVO> carSeries = daysRange(start, end).stream()
                .map(d -> new PesTotalItemVO(carMap.getOrDefault(d, BigDecimal.ZERO)))
                .collect(Collectors.toList());

        List<PesTotalItemVO> tonSeries = daysRange(start, end).stream()
                .map(d -> new PesTotalItemVO(tonMap.getOrDefault(d, BigDecimal.ZERO)))
                .collect(Collectors.toList());

        PesSevenDayRespGroupVO group = new PesSevenDayRespGroupVO();
        group.setCarTotals(carSeries);
        group.setTonnageTotals(tonSeries);

        // 按你的示例，返回数组，里面只有一个对象
        return Collections.singletonList(group);
    }

    /** 兼容 'yyyy-M-d' 与 'yyyy-MM-dd' */
    private LocalDate parseFlexibleDate(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("record_date 不能为空");
        }
        s = s.trim();
        List<DateTimeFormatter> patterns = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        for (DateTimeFormatter f : patterns) {
            try { return LocalDate.parse(s, f); } catch (Exception ignore) {}
        }
        // 通用兜底（支持前导零/不带零）
        DateTimeFormatter flex = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-")
                .appendValue(java.time.temporal.ChronoField.MONTH_OF_YEAR, 1, 2, java.time.format.SignStyle.NOT_NEGATIVE)
                .appendLiteral('-')
                .appendValue(java.time.temporal.ChronoField.DAY_OF_MONTH, 1, 2, java.time.format.SignStyle.NOT_NEGATIVE)
                .toFormatter();
        return LocalDate.parse(s, flex);
    }

    private List<LocalDate> daysRange(LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
        return IntStream.rangeClosed(0, (int) days)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    private BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
