package com.ruoyi.system.service;

import com.ruoyi.system.domain.CwpDailySummaryItemVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;

import java.util.List;

public interface ICwpStatService {
    /** 洗煤生产 - 当日汇总 */
    List<CwpDailySummaryItemVO> dailySummary(EemSevenDayReq.CwpDailySummaryReq req);
}
