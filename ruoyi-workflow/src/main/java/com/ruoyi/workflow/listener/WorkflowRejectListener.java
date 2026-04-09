package com.ruoyi.workflow.listener;

import com.ruoyi.common.event.WorkflowRejectEvent;
import com.ruoyi.workflow.domain.WorkflowMyDraft;
import com.ruoyi.workflow.mapper.WorkflowMyDraftMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 流程退回事件监听器（处理草稿表更新）
 */
@Component
@RequiredArgsConstructor
public class WorkflowRejectListener {

    private final WorkflowMyDraftMapper workflowMyDraftMapper;

    @EventListener
    public void handleWorkflowRejectEvent(WorkflowRejectEvent event) {
        // 更新t_workflow_my_draft表的handle_type为1
        WorkflowMyDraft draft = new WorkflowMyDraft();
        draft.setId(event.getProcInstId());
        draft.setHandleType("1"); // 假设已在WorkflowMyDraft类中添加handleType字段

        // 注意：需要在WorkflowMyDraftMapper中添加按procInstId更新的方法
        workflowMyDraftMapper.updateHandleTypeByProcInsId(draft);
    }
}