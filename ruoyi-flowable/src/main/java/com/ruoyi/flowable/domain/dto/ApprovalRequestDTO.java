package com.ruoyi.flowable.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 审批请求外层DTO（适配前端参数格式）
 */
@Data
@ApiModel("审批请求参数（外层）")
public class ApprovalRequestDTO {

    @ApiModelProperty(value = "操作类型（如200）", required = true)
    private String operateType;

    @ApiModelProperty(value = "流程任务详情", required = true)
    private AppFlowTaskDTO flowTask;  // 嵌套flowTask对象
}