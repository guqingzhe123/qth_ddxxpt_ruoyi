package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.dev.*;
import com.ruoyi.system.domain.BaoBiao.vo.dev.MddVO;

import java.util.List;

public interface IMineDevelopmentDataService {
    Long add(MddCreateDTO dto);
    int edit(MddUpdateDTO dto);
    int remove(Long id);
    MddVO get(Long id);
    List<MddVO> page(MddPageQueryDTO query);
}
