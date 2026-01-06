package com.ruoyi.system.domain.Biaodan;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TargetAll extends BaseEntity {
    List<Production> 开拓;
    List<Production> 进尺;
    List<Production> 原煤;
    List<Production> 洗煤;
    List<Production> 外运;
}
