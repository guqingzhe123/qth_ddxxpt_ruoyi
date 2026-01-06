package com.ruoyi.flowable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flowable.domain.FlowApprovalAttachment;
import java.util.List;

/**
 * 审批附件关联Mapper接口
 */
public interface FlowApprovalAttachmentMapper extends BaseMapper<FlowApprovalAttachment> {

    /**
     * 根据流程实例ID查询附件列表
     * @param procInsId 流程实例ID
     * @return 附件关联列表
     */
    List<FlowApprovalAttachment> selectByProcInsId(FlowApprovalAttachment procInsId);
}