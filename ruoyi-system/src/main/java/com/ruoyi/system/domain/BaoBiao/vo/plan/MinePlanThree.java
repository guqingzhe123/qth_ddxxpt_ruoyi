package com.ruoyi.system.domain.BaoBiao.vo.plan;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("MinePlanThree")  // 起一个项目里独一无二的名字
@Data
public class MinePlanThree implements Serializable {
    private static final long serialVersionUID = 1L;

    private String planType;
    private Integer oneClass;
    private Integer twoClass;
    private Integer threeClass;
}
