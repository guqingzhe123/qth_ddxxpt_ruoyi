package com.ruoyi.system.vo.dto.wash;

import lombok.Data;

@Data
public class CoalWashingProductionUpdateDTO extends CoalWashingProductionCreateDTO {
    private Long id; // 主键ID

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
