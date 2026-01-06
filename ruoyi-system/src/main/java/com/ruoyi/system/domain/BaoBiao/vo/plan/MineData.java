package com.ruoyi.system.domain.BaoBiao.vo.plan;

import lombok.Data;

import java.io.Serializable;

@Data
public class MineData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String recordDate;
    private String unitName;
    private String dataType;
    private String currentShift;

    private Integer productionData;//生产数据
    private Integer expandData;//开拓数据
    private Integer footageData;//进尺数据
    private Integer enterWellNum;//入井人数
    private Integer comeOutWellNum;//出井人数
    private Integer totalDownCount;//总出勤人数
    private Integer totalUpCount;//总入井人数

    private Integer isDeleted;
}
