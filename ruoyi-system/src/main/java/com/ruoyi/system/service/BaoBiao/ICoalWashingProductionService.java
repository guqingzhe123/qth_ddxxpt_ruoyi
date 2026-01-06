package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.vo.dto.wash.CoalWashingProductionCreateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionPageQueryDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionUpdateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionVO;

import java.util.List;

public interface ICoalWashingProductionService {
    Long add(CoalWashingProductionCreateDTO dto);
    int edit(CoalWashingProductionUpdateDTO dto);
    int remove(Long id);
    CoalWashingProductionVO get(Long id);
    List<CoalWashingProductionVO> page(CoalWashingProductionPageQueryDTO query);
}
