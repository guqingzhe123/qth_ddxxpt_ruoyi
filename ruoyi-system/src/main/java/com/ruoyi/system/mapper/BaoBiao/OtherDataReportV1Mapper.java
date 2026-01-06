package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.DispatchDailyReportOtherDataV1PO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface OtherDataReportV1Mapper {

    /** 取当日一行（表有唯一索引，最多一行；无则返回 null） */
    DispatchDailyReportOtherDataV1PO selectOneByDate(@Param("recordDate") String recordDate);

    // 累计汇总（当月1号~当日）
    BigDecimal sumCleanCoalOutput(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    BigDecimal sumProductSales(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    BigDecimal sumOutboundCars(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    BigDecimal sumOutboundVolume(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    BigDecimal sumLocalSales(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    BigDecimal sumCleanCoal(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
