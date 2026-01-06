package com.ruoyi.system.domain.ribaobaobiao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 入井/出井 - 七日前每日总合 统计 入参
 * record_date 允许 "yyyy-MM-dd" 或 "yyyy-M-d"
 */
@Data
public class EemSevenDayReq {
    /** 目标日期（示例：2025-10-15 或 2025-10-1） */
    private String record_date;

    /**
     * 产品外销 - 七日前每日总合 统计 入参
     * 说明：为了兼容 "2025-10-1" / "2025-10-01" 两种写法，这里接 String 再在 Service 中做解析。
     */
    @Data
    public static class PesSevenDayReq {
        /** 目标日期（示例：2025-10-15 或 2025-10-1） */
        private String record_date;
    }

    @Data
    public static class MddStatQueryDTO {

        /** 查询日期（yyyy-MM-dd） */
        @NotNull(message = "record_date 不能为空")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate record_date;
    }

    /** 洗煤生产 - 当日汇总 入参 */
    @Data
    public static class CwpDailySummaryReq {
        /** 目标日期（示例：2025-10-15 或 2025-10-1） */
        private String record_date;
    }
}
