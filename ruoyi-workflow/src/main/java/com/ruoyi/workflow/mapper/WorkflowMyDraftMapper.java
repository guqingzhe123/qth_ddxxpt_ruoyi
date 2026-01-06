package com.ruoyi.workflow.mapper;

import com.ruoyi.workflow.domain.WorkflowMyDraft;

import java.util.List;

/**
 * 我起草的流程表Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-05-16
 */
public interface WorkflowMyDraftMapper {
    /**
     * 查询我起草的流程表
     * 
     * @param id 我起草的流程表主键
     * @return 我起草的流程表
     */
    public WorkflowMyDraft selectWorkflowMyDraftById(String id);

    /**
     * 根据业务id查询我起草的流程表
     * @param businessId
     * @return
     */
    public WorkflowMyDraft selectByBizId(String businessId);

    /**
     * 查询我起草的流程表列表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 我起草的流程表集合
     */
    public List<WorkflowMyDraft> selectWorkflowMyDraftList(WorkflowMyDraft workflowMyDraft);

    /**
     * 新增我起草的流程表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 结果
     */
    public int insertWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 修改我起草的流程表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 结果
     */
    public int updateWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 删除我起草的流程表
     * 
     * @param id 我起草的流程表主键
     * @return 结果
     */
    public int deleteWorkflowMyDraftById(String id);

    /**
     * 批量删除我起草的流程表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowMyDraftByIds(String[] ids);

    /**
     * 查询我起草的流程表数量
     * @param createId
     * @return
     */
    public int count(String createId);

    /**
     * 批量查询我起草的流程表
     *
     * @param businessKeys
     * @return 结果
     */
    List<WorkflowMyDraft> selectWorkflowMyDraftByBizIds(List<String> businessKeys);
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
    WorkflowMyDraft queryHandleTypeByBizId(String bizId);
}
