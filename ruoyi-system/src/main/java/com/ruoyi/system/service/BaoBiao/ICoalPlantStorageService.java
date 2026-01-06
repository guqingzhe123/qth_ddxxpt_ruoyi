package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.cps.*;
import com.ruoyi.system.domain.BaoBiao.vo.cps.CpsVO;
import java.util.List;

public interface ICoalPlantStorageService {
    Long add(CpsCreateDTO dto);
    int edit(CpsUpdateDTO dto);
    int remove(Long id);
    CpsVO get(Long id);
    List<CpsVO> page(CpsPageQueryDTO query);
}
