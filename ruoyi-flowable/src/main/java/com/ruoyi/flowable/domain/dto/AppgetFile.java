package com.ruoyi.flowable.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询附件外层")
public class  AppgetFile {
    @ApiModelProperty(value = "流程实例ID", required = true)
    private String procInsId;

    @ApiModelProperty(value = "创建人ID", required = true)
    private String taskId;

}
