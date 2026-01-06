package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.ribaobaobiao.EnterExitQueryDTO;
import com.ruoyi.system.domain.ribaobaobiao.EnterExitShiftVO;

import java.util.List;

public interface IEnteringExitingMineService {
    List<EnterExitShiftVO> shiftReport(EnterExitQueryDTO dto);
}
