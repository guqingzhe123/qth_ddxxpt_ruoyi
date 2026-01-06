package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.dto.AppgetFile;
import com.ruoyi.flowable.domain.dto.ApprovalRequestDTO;
import com.ruoyi.workfile.module.BizAttachmentDTO;

import java.util.List;

/**
 * 审批服务接口（含附件处理）
 */
public interface IFlowApprovalService {

    /**
     * 提交审批（含附件关联）
     * @param dto 审批参数
     */
    void approve(ApprovalRequestDTO requestDTO);

    /**
     * 根据流程实例ID查询审批详情（含附件）
     * @param procInsId 流程实例ID
     * @return 审批详情（含意见和附件）
     */
    List<BizAttachmentDTO> getApprovalDetail(AppgetFile procInsId);
    /**
     * 删除附件
     *
     * @param fileId 文件ID
     * @return
     */
    public int remove(String fileId);
}