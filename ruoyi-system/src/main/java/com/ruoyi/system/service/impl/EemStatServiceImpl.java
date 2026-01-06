package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.EemDateAggRow;
import com.ruoyi.system.domain.EemSevenDayRespGroupVO;
import com.ruoyi.system.domain.EemTotalItemVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;
import com.ruoyi.system.mapper.BaoBiao.EemStatMapper;
import com.ruoyi.system.service.IEemStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class EemStatServiceImpl implements IEemStatService {

    private final EemStatMapper eemStatMapper;

    @Override
    public List<EemSevenDayRespGroupVO> sevenDayTotals(EemSevenDayReq req) {
        LocalDate end = parseFlexibleDate(req.getRecord_date());
        LocalDate start = end.minusDays(6);

        LocalDateTime startTs = start.atStartOfDay();
        LocalDateTime endExclusiveTs = end.plusDays(1).atStartOfDay(); // [start, end+1)

        // 查询数据库
        List<EemDateAggRow> downRows = eemStatMapper.selectDailyDownTotals(startTs, endExclusiveTs);
        List<EemDateAggRow> upRows   = eemStatMapper.selectDailyUpTotals(startTs, endExclusiveTs);

        Map<LocalDate, BigDecimal> downMap = downRows.stream()
                .collect(Collectors.toMap(EemDateAggRow::getDay, r -> nvl(r.getTotal()), (a,b)->a));
        Map<LocalDate, BigDecimal> upMap = upRows.stream()
                .collect(Collectors.toMap(EemDateAggRow::getDay, r -> nvl(r.getTotal()), (a,b)->a));

        List<LocalDate> days = daysRange(start, end);

        List<EemTotalItemVO> downSeries = days.stream()
                .map(d -> new EemTotalItemVO(downMap.getOrDefault(d, BigDecimal.ZERO)))
                .collect(Collectors.toList());

        List<EemTotalItemVO> upSeries = days.stream()
                .map(d -> new EemTotalItemVO(upMap.getOrDefault(d, BigDecimal.ZERO)))
                .collect(Collectors.toList());

        EemSevenDayRespGroupVO group = new EemSevenDayRespGroupVO();
        group.setDownTotals(downSeries);
        group.setUpTotals(upSeries);

        // 按你的示例，返回数组，里面只有一个对象
        return Collections.singletonList(group);
    }

    /** 兼容 'yyyy-M-d' 与 'yyyy-MM-dd' */
    private LocalDate parseFlexibleDate(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("record_date 不能为空");
        }
        s = s.trim();
        // 优先尝试常见模式
        List<DateTimeFormatter> formats = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );
        for (DateTimeFormatter f : formats) {
            try { return LocalDate.parse(s, f); } catch (Exception ignore) {}
        }
        // 失败就抛错
        throw new IllegalArgumentException("record_date 格式不正确，应为 yyyy-MM-dd");
    }

    private List<LocalDate> daysRange(LocalDate start, LocalDate end) {
        int len = (int) (end.toEpochDay() - start.toEpochDay());
        return IntStream.rangeClosed(0, len)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    private BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
