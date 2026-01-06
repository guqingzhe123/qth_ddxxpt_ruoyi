package com.ruoyi.workflow.async;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.enums.FLowOperateTypeEnum;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.workflow.exception.WorkflowAsyncException;
import com.ruoyi.workflow.service.IFlowHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> 流程异步处理服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FlowAsyncService implements IAsyncHandler {

    @Autowired
    private IFlowHandleService flowHandleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAsync(AsyncLog asyncLog) {
        log.info("收到消息，消息内容：{}", asyncLog);
        try {
            FlowTaskVo flowTaskVo = getFlowTaskVo(asyncLog);
           // msg={"beanName":"flowAsyncService","createId":"115B0B2D15434B8E90036A5524C10E4A",
            // "createTime":"2025-12-10 21:03:39","exchangeKey":"flow.exchange","failReason":"异步处理失败:流程操作类型为空！",
            // "id":"DAAC0281782E43DBB177334B69E230A5","messageContent":"{\"businessId\":\"78F4F12D29D343A0AEA32467E1CC69F4\",
            // \"comment\":\"方法\",\"defId\":\"flow_i0gw9e1v:17:1317577\",\"fileIds\":[],\"handleType\":\"1\",
            // \"operateType\":\"\",\"procInsId\":\"1762588\",\"targetKey\":\"\",\"taskDefKey\":\"Activity_0m49sex\",
            // \"taskId\":\"1762614\",\"templateId\":\"B05C6441298247FA8870ADD6D3A67644\",\"templateName\":\"新建事项\",
            // \"templateType\":\"2059D9FB9D984DB1AF4BB1625D0F70EA\",\"title\":\"阿松大\",
            // \"todoId\":\"61AEB4086E7F43B9B4150D9409B74B67\",\"type\":\"1\",\"urgencyStatus\":\"0\",
            // \"userId\":\"115B0B2D15434B8E90036A5524C10E4A\",\"variables\":{}}","params":{},"routingKey":"flow-router",
            // "status":"2","updateTime":"2025-12-10 21:03:39"}, exception={}

            log.error("传过来参数"+flowTaskVo);
            FLowOperateTypeEnum operateTypeEnum = getFlowOperateTypeEnum(flowTaskVo);
            switch(operateTypeEnum) {
                case COMPLETE:
                    flowHandleService.completeTask(flowTaskVo, asyncLog.getCreateId());
                    break;
                case REJECT:
                    flowHandleService.taskReject(flowTaskVo, asyncLog.getCreateId());
                    break;
                case BACK:
                    flowHandleService.taskReturn(flowTaskVo, asyncLog.getCreateId());
                    break;
                case CLAIM:
                    flowHandleService.claim(flowTaskVo, asyncLog.getCreateId());
                    break;
                case UNCLAIM:
                    flowHandleService.unClaim(flowTaskVo, asyncLog.getCreateId());
                    break;
            }
        } catch (Exception e) {
            log.error("异步处理失败，原因：", e);
            throw new WorkflowAsyncException("异步处理失败:" + e.getMessage());
        }
    }

    /**
     * 获取流程任务
     *
     * @param asyncLog
     */
    private FlowTaskVo getFlowTaskVo(AsyncLog asyncLog) {
        if (StringUtils.isBlank(asyncLog.getMessageContent())) {
            throw new WorkflowAsyncException("消息内容不能为空！");
        }
        return JSONObject.parseObject(asyncLog.getMessageContent(), FlowTaskVo.class);
    }

    /**
     * 获取流程操作类型枚举
     *
     * @param flowTaskVo
     */
    private FLowOperateTypeEnum getFlowOperateTypeEnum(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getOperateType())) {
            log.error("[流程操作类型为空] 传过来参数"+flowTaskVo);
            throw new WorkflowAsyncException("流程操作类型为空！");
        }
        FLowOperateTypeEnum operateTypeEnum = FLowOperateTypeEnum.getByType(flowTaskVo.getOperateType());
        if (operateTypeEnum == null) {
            log.error("[流程操作类型为空] 传过来参数"+flowTaskVo);
            throw new WorkflowAsyncException("未找到流程操作类型：" + flowTaskVo.getOperateType());
        }
        return operateTypeEnum;
    }
}
