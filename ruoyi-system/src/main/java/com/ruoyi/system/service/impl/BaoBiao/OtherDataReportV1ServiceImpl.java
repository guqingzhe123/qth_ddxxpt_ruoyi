package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.DispatchDailyReportOtherDataV1PO;
import com.ruoyi.system.domain.BaoBiao.OtherDataReportV1RequestDTO;
import com.ruoyi.system.domain.BaoBiao.OtherDataReportV1VO;
import com.ruoyi.system.mapper.BaoBiao.OtherDataReportV1Mapper;
import com.ruoyi.system.service.BaoBiao.IOtherDataReportV1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class OtherDataReportV1ServiceImpl implements IOtherDataReportV1Service {

    @Resource
    private OtherDataReportV1Mapper mapper;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<OtherDataReportV1VO> byDate(OtherDataReportV1RequestDTO dto) {
        if (dto == null || dto.getRecordDate() == null) {
            throw new IllegalArgumentException("record_date 不能为空（yyyy-MM-dd）");
        }
        LocalDate theDay = LocalDate.parse(dto.getRecordDate(), DTF);
        String dayStr = theDay.format(DTF);
        String monthStart = theDay.withDayOfMonth(1).format(DTF);

        // 当日数据（可能为 null）
        DispatchDailyReportOtherDataV1PO day = mapper.selectOneByDate(dayStr);

        // 累计
        BigDecimal cumCleanCoalOutput = mapper.sumCleanCoalOutput(monthStart, dayStr);
        BigDecimal cumProductSales    = mapper.sumProductSales(monthStart, dayStr);
        BigDecimal cumOutboundCars    = mapper.sumOutboundCars(monthStart, dayStr);
        BigDecimal cumOutboundVolume  = mapper.sumOutboundVolume(monthStart, dayStr);
        BigDecimal cumLocalSales      = mapper.sumLocalSales(monthStart, dayStr);
        BigDecimal cumCleanCoal       = mapper.sumCleanCoal(monthStart, dayStr);

        OtherDataReportV1VO vo = new OtherDataReportV1VO();
        // 当日（无数据则 0）
        vo.setDailyCleanCoalOutput(nz(day == null ? null : day.getDailyCleanCoalOutput()));
        vo.setDailyProductSales(nz(day == null ? null : day.getDailyProductSales()));
        vo.setDailyOutboundCars(nz(day == null ? null : day.getDailyOutboundCars()));
        vo.setDailyOutboundVolume(nz(day == null ? null : day.getDailyOutboundVolume()));
        vo.setDailySalesTotal(nz(day == null ? null : day.getDailySalesTotal()));

        vo.setSubtotal(nz(day == null ? null : day.getSubtotal()));
        vo.setRawCoal(nz(day == null ? null : day.getRawCoal()));
        vo.setCleanCoal(nz(day == null ? null : day.getCleanCoal()));
        vo.setOther(nz(day == null ? null : day.getOther()));

        vo.setDailyLocalSales(nz(day == null ? null : day.getDailyLocalSales()));

        // 累计
        vo.setCumCleanCoalOutput(nz(cumCleanCoalOutput));
        vo.setCumProductSales(nz(cumProductSales));
        vo.setCumOutboundCars(nz(cumOutboundCars));
        vo.setCumOutboundVolume(nz(cumOutboundVolume));
        vo.setCumLocalSales(nz(cumLocalSales));
        vo.setCumCleanCoal(nz(cumCleanCoal));

        return Collections.singletonList(vo);
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
