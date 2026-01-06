package com.ruoyi.system.service;

import com.ruoyi.system.domain.ribaobaobiao.EemSevenDayReq;

import java.util.List;

public interface IMddStatService {

    /**
     * 根据日期返回四段对象的数组（顺序与前端预期一致）：
     * 1) 顶部汇总
     * 2) 七日生产（分公司/七煤）
     * 3) 七日进尺（分公司/七煤）
     * 4) 七日开拓（分公司/七煤）
     */
    List<Object> summaryByDate(EemSevenDayReq.MddStatQueryDTO dto);
}
