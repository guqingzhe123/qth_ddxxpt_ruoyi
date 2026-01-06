package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV2RequestDTO;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV2VO;

import java.util.List;

public interface IMineDayCumReportV2Service {
    List<MineDayCumReportV2VO> buildReport(MineDayCumReportV2RequestDTO dto);
}
