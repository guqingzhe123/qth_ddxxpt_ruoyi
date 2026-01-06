package com.ruoyi.flowable.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 流程任务DTO（重命名自FlowTaskDTO）
 */
@Data
@ApiModel("流程任务详情参数")
public class AppFlowTaskDTO {  // 类名已修改为AppFlowTaskDTO

    @ApiModelProperty(value = "审批意见")
    private String comment;

    @ApiModelProperty(value = "显示类型")
    private String showType;

    @ApiModelProperty(value = "流程实例ID", required = true)
    private String procInsId;

    @ApiModelProperty(value = "部署ID")
    private String deployId;

    @ApiModelProperty(value = "任务ID", required = true)
    private String taskId;

    @ApiModelProperty(value = "任务定义Key")
    private String taskDefKey;

    @ApiModelProperty(value = "流程定义ID")
    private String defId;

    @ApiModelProperty(value = "目标Key")
    private String targetKey;

    @ApiModelProperty(value = "是否草稿（0-否，1-是）")
    private String isDraft;

    @ApiModelProperty(value = "业务ID")
    private String businessId;

    @ApiModelProperty(value = "待办ID")
    private String todoId;

    @ApiModelProperty(value = "模板ID")
    private String templateId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "处理类型（如1-同意，2-拒绝）")
    private String handleType;

    @ApiModelProperty(value = "流程变量（用于流程流转判断）")
    private Map<String, Object> variables;

    @ApiModelProperty(value = "附件ID列表")
    private List<String> fileIds;

    @ApiModelProperty(value = "执行ID")
    private String executionId;
}