package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.WorkflowMyDraft;

import java.util.List;

/**
 * 我起草的流程表Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowMyDraftService {

    /**
     * 查询我起草的流程表列表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 我起草的流程表集合
     */
    public List<WorkflowMyDraft> listWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 新增我起草的流程表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 结果
     */
    public int saveWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 创建我起草的流程表
     */
    public void createMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 更新我起草的流程表状态
     */
    public void updateMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 统计我起草的流程表数量
     * @return
     */
    public int count();

    /**
     * 批量查询我起草的流程表
     *
     * @param businessKeys
     * @return
     */
    List<WorkflowMyDraft> listWorkflowMyDraftByBizIds(List<String> businessKeys);

    /**
     * 根据流程实例ID更新handle_type
     * @param draft 包含procInstId和handleType的实体
     * @return 更新行数
     */
    int updateHandleTypeByProcInsId(WorkflowMyDraft draft);

    /**
     * 根据业务ID查询信息
     * @param bizId 业务ID
     * @return 返回信息
     */
    WorkflowMyDraft updateHandleTypeByProcInsId(String bizId);
}
