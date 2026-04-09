package com.ruoyi.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 流程退回事件（用于跨模块通知更新草稿状态）
 */
@Getter
public class WorkflowRejectEvent extends ApplicationEvent {

    /** 流程实例ID */
    private final String procInstId;

    public WorkflowRejectEvent(Object source, String procInstId) {
        super(source);
        this.procInstId = procInstId;
    }
}