package com.ruoyi.system.service;


import com.ruoyi.system.domain.EemSevenDayRespGroupVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;

import java.util.List;

public interface IEemStatService {

    /**
     * 七日前（含当天，共7天）每日总合（入井/升井）
     */
    List<EemSevenDayRespGroupVO> sevenDayTotals(EemSevenDayReq req);
}
