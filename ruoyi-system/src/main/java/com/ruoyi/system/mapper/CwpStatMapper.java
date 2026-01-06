package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CwpDailySumRow;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

public interface CwpStatMapper {
    /** 当日汇总（不做字符串比较，避免排序规则冲突） */
    CwpDailySumRow selectDailySums(@Param("theDate") LocalDate theDate);
}
