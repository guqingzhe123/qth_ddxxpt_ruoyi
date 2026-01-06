package com.ruoyi.flowable.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * 审批提交DTO（含附件）
 * @author ruoyi
 */
@Data
@ApiModel("审批提交参数")
public class FlowApprovalDTO {

    @ApiModelProperty(value = "任务ID", required = true)
    private String taskId;

    @ApiModelProperty(value = "流程实例ID", required = true)
    private String procInsId;

    @ApiModelProperty(value = "审批意见")
    private String comment;

    @ApiModelProperty(value = "附件ID列表")
    private List<String> fileIds;

}