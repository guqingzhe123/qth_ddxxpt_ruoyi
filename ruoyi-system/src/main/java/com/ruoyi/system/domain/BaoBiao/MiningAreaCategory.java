package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class MiningAreaCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String areaCode;//采区编码（唯一）
    private String areaName;//采区名称
    private Integer level; //级次（>=1）
    /** 0=未封存 1=已封存 */
    private Integer isSealed;//是否封存 0否 1是
    /** 0=否 1=是 */
    private Integer isPlanned;//是否计划内 0否 1是


    private Date createTime;//创建日期
    private Date updateTime;//修改日期
    private String userid;//用户id? 不知道干什么用的
    private Integer parentId;//父级id
    private Integer isseparate;//是否分矿
    private Integer sequence;//排列顺序
}
