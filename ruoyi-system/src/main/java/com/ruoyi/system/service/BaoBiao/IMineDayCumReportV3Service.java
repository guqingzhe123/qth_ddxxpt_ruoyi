package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV3RequestDTO;
import com.ruoyi.system.domain.BaoBiao.MineDayCumReportV3VO;

import java.util.List;

public interface IMineDayCumReportV3Service {
    List<MineDayCumReportV3VO> buildReport(MineDayCumReportV3RequestDTO dto);
}
