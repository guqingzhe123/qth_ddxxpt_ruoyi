package com.ruoyi.flowable.domain.qo;

import lombok.Data;

import java.util.List;

/**
 * <p> 流程审批意见查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowCommentQo {

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    private String procInsId;      // 流程实例ID（作为业务ID关联附件）
    private String comment;        // 审批意见
    private List<String> fileIds;  // 附件ID列表
}
