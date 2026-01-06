package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.OtherDataReportV1RequestDTO;
import com.ruoyi.system.domain.BaoBiao.OtherDataReportV1VO;

import java.util.List;

public interface IOtherDataReportV1Service {
    List<OtherDataReportV1VO> byDate(OtherDataReportV1RequestDTO dto);
}
