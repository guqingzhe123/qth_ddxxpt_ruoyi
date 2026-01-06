package com.ruoyi.system.domain.BaoBiao.vo.plan;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("MinePlanJu")  // 起一个项目里独一无二的名字
@Data
public class MinePlanJu implements Serializable {
    private static final long serialVersionUID = 1L;

    private String planMonth;
    private String unitName;
    //一班
    private Integer oneProductionData;//生产数据
    private Integer oneExpandData;//开拓
    private Integer oneFootageData;//进尺
    private Integer oneEnterWellNum;//入井人数
    private Integer oneComeOutWellNum;//出境人数
    private Integer oneTotalDownCount;//入井人数
    private Integer oneTotalUpCount;//出井人数

    //二班
    private Integer twoProductionData;//生产数据
    private Integer twoExpandData;//开拓
    private Integer twoFootageData;//进尺
    private Integer twoEnterWellNum;//入井人数
    private Integer twoComeOutWellNum;//出境人数
    private Integer twoTotalDownCount;//入井人数
    private Integer twoTotalUpCount;//出井人数
    //三班
    private Integer threeProductionData;//生产数据
    private Integer threeExpandData;//开拓
    private Integer threeFootageData;//进尺
    private Integer threeEnterWellNum;//入井人数
    private Integer threeComeOutWellNum;//出境人数
    private Integer threeTotalDownCount;//入井人数
    private Integer threeTotalUpCount;//出井人数

    private String isDeleted;//是否删除
}
