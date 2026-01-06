package com.ruoyi.flowable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.flowable.domain.FlowApprovalAttachment;
import com.ruoyi.flowable.domain.dto.AppFlowTaskDTO;
import com.ruoyi.flowable.domain.dto.AppgetFile;
import com.ruoyi.flowable.domain.dto.ApprovalRequestDTO;
import com.ruoyi.flowable.domain.dto.FlowApprovalDTO;
import com.ruoyi.flowable.service.IFlowApprovalService;
import com.ruoyi.workfile.module.BizAttachmentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审批控制器（含附件处理）
 */
@Api(tags = "审批管理（含附件）")
@RestController
@RequestMapping("/flowable/process")
public class FlowApprovalController extends BaseController {

    @Autowired
    private IFlowApprovalService flowApprovalService;

    /**
     * 提交审批（支持附件）
     */
    @ApiOperation("提交审批（含附件）")
    @PostMapping("/approve")
    public AjaxResult submitApproval(@RequestBody ApprovalRequestDTO requestDTO) {
        flowApprovalService.approve(requestDTO);
        return AjaxResult.success("审批提交成功，流程已流转至下一阶段");
    }

    /**
     * 查询审批详情（含附件）
     */
    @ApiOperation("查询审批详情（含附件）")
    @PostMapping("/detail")
    public AjaxResult getDetail(@RequestBody AppgetFile procInsId) {
        List<BizAttachmentDTO> list = flowApprovalService.getApprovalDetail(procInsId);
        return success(list);
    }
}