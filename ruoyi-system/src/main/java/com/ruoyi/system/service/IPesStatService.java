package com.ruoyi.system.service;

import com.ruoyi.system.domain.PesSevenDayRespGroupVO;
import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;

import java.util.List;

public interface IPesStatService {

    /**
     * 七日前（含当天，共7天）每日总合
     * @param req 入参（record_date）
     * @return 按示例要求，返回只有一个元素的数组，元素内含“车次合计”“吨数合计”
     */
    List<PesSevenDayRespGroupVO> sevenDayTotals(EemSevenDayReq.PesSevenDayReq req);
}
