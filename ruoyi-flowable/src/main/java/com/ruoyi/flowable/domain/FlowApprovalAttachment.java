package com.ruoyi.flowable.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审批附件关联表
 * 存储审批流程与附件的关联关系
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("flow_approval_attachment")
public class FlowApprovalAttachment extends BaseEntity {


    @TableId(type = IdType.UUID)
    private String id;

    /** 流程实例ID */
    private String procInsId;

    /** 附件文件ID（关联file_storage表的id） */
    private String fileId;

}