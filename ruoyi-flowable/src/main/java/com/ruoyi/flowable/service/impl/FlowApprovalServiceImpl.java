package com.ruoyi.flowable.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.flowable.domain.FlowApprovalAttachment;
import com.ruoyi.flowable.domain.dto.AppFlowTaskDTO;
import com.ruoyi.flowable.domain.dto.AppgetFile;
import com.ruoyi.flowable.domain.dto.ApprovalRequestDTO;
import com.ruoyi.flowable.mapper.FlowApprovalAttachmentMapper;
import com.ruoyi.flowable.service.IFlowApprovalService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.workfile.module.BizAttachmentDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.common.engine.impl.util.CollectionUtil;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 审批服务实现类（含附件关联和流程流转逻辑）
 */
@Service
public class FlowApprovalServiceImpl implements IFlowApprovalService {
    private static final Logger log = LoggerFactory.getLogger(FlowApprovalServiceImpl.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private FlowApprovalAttachmentMapper approvalAttachmentMapper;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private IFileStorageService fileStorageService;

    /**
     * 提交审批（含附件关联和流程流转）
     *
     * @param requestDTO 审批请求参数（外层包含操作类型和流程任务详情）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approve(ApprovalRequestDTO requestDTO) {
        try {
            // 1. 解析请求参数，获取流程任务详情
            AppFlowTaskDTO flowTask = requestDTO.getFlowTask();
            if (flowTask == null) {
                throw new BaseException("流程任务信息不能为空");
            }

            // 2. 校验任务有效性（防止处理无效/已完成任务）
            String taskId = flowTask.getTaskId();
            if (taskId == null || taskId.trim().isEmpty()) {
                throw new BaseException("任务ID不能为空");
            }
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                throw new BaseException("任务不存在或已被处理，taskId: " + taskId);
            }

            // 3. 校验当前用户是否有权处理该任务（防止越权）
            String currentUserId = userService.selectUserById(SecurityUtils.getUserId()).getUserName();
//            if (!task.getAssignee().equals(currentUserId)) {
//                throw new BaseException("当前用户无权限处理该任务，任务负责人：" + task.getAssignee());
//            }

            // 4. 保存审批意见（关联到流程实例和任务）
            String comment = flowTask.getComment() != null ? flowTask.getComment() : "默认同意";
            taskService.addComment(
                    taskId,
                    flowTask.getProcInsId(),
                    String.format("[%s] %s", currentUserId, comment)  // 意见格式：[处理人] 意见内容
            );
            log.info("保存审批意见成功，taskId: {}，意见：{}", taskId, comment);

            // 5. 关联附件（若前端传递了fileIds）
            List<String> fileIds = flowTask.getFileIds();
            if (CollectionUtil.isNotEmpty(fileIds)) {
                for (String fileId : fileIds) {
                    // 创建附件关联记录
                    FlowApprovalAttachment attachment = new FlowApprovalAttachment();
                    // 手动生成UUID作为主键（兼容低版本MyBatis-Plus，无ASSIGN_ID）
                    attachment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    attachment.setProcInsId(flowTask.getProcInsId());  // 关联流程实例
                    attachment.setFileId(fileId);  // 关联文件ID
                    attachment.setCreateBy(taskId);  // 创建人
                    attachment.setCreateTime(new Date());  // 创建时间
                    // 插入数据库
                    approvalAttachmentMapper.insert(attachment);
                    log.info("关联附件成功，procInsId: {}，fileId: {}", flowTask.getProcInsId(), fileId);
                }
            }

            // 6. 准备流程变量（关键：决定流程下一步流向）
            Map<String, Object> variables = new HashMap<>(16);
            // 6.1 合并前端传递的变量（如业务参数、动态处理人等）
            if (flowTask.getVariables() != null) {
                variables.putAll(flowTask.getVariables());
            }
            // 6.2 补充审批关键变量（供流程网关/节点判断使用）
            variables.put("handleType", flowTask.getHandleType());  // 处理类型（如1-同意，2-拒绝）
            variables.put("approver", currentUserId);  // 当前审批人ID
            variables.put("approveTime", new Date());  // 审批时间
            variables.put("procInsId", flowTask.getProcInsId());  // 流程实例ID（冗余）

            // 7. 完成当前任务并传递变量（触发流程流转到下一阶段）
            taskService.complete(taskId, variables);










            log.info("任务完成，流程已流转至下一阶段。taskId: {}，procInsId: {}，流程变量: {}",
                    taskId, flowTask.getProcInsId(), variables);

        } catch (BaseException e) {
            // 已知业务异常直接抛出
            throw e;
        } catch (Exception e) {
            // 未知异常记录日志并包装
            log.error("审批提交失败", e);
            throw new BaseException("审批处理异常：" + e.getMessage());
        }
    }

    /**
     * 查询审批详情（含审批意见和附件）
     *
     * @return 审批详情（意见+附件）
     */
    @Override
    public List<BizAttachmentDTO> getApprovalDetail(AppgetFile flow) {
        String procInsId=flow.getProcInsId();
        String creatyby=flow.getTaskId();
         if (procInsId == null || procInsId.trim().isEmpty()) {
             return null;
        }

        try {
            FlowApprovalAttachment  flowapp=new FlowApprovalAttachment();
            flowapp.setCreateBy(creatyby);
            flowapp.setProcInsId(procInsId);

            // 2. 查询关联的附件ID列表
            List<FlowApprovalAttachment> attachmentRelations = approvalAttachmentMapper.selectByProcInsId(flowapp);


            List<String> fileIds = attachmentRelations.stream().map(FlowApprovalAttachment::getFileId).collect(Collectors.toList());
            List<FileStorage> fileStorages = fileStorageService.listByIds(fileIds);
            if (CollectionUtils.isEmpty(fileStorages)) {
                return null;
            }

            return fileStorages.stream()
                    .map(fileStorage -> buildBizAttachmentDTO(fileStorage,procInsId))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("查询审批详情失败，procInsId: {}", procInsId, e);
            return null;
        }
    }
    private BizAttachmentDTO buildBizAttachmentDTO(FileStorage fileStorage, String procInsId) {
        BizAttachmentDTO dto = new BizAttachmentDTO();
        dto.setFileId(fileStorage.getFileId());
        dto.setFileName(fileStorage.getFileName() + Constants.DOT + fileStorage.getExtendName());
        dto.setFileExt(fileStorage.getExtendName());
        dto.setFileSize(fileStorage.getFileSize());
        dto.setIdentifier(fileStorage.getIdentifier());
        dto.setSort(fileStorage.getSort());
        dto.setFileurl(fileStorage.getFileUrl());
        return dto;
    }

    @Override
    public int remove(String fileId) {
        return fileStorageService.deleteByFileId(fileId);
    }

}