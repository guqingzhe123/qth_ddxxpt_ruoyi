package com.ruoyi.web.controller.chat;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.ImChatMessage;
import com.ruoyi.flowable.service.IImChatMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天控制器
 *
 * @author ruoyi
 */
@Api(tags = "聊天功能接口")
@RestController
@RequestMapping("/im/chat")
public class ImChatController {

    @Autowired
    private IImChatMessageService chatMessageService;

    /**
     * 发送消息
     */
    @ApiOperation("发送聊天消息")
    @PostMapping("/send")
    public AjaxResult sendMessage(@RequestBody ImChatMessage message) {
        return chatMessageService.sendMessage(message) ? AjaxResult.success() : AjaxResult.error("发送失败");
    }

    /**
     * 获取聊天记录
     */
    @ApiOperation("获取聊天记录")
    @GetMapping("/history")
    public TableDataInfo<ImChatMessage> getHistory(
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        return chatMessageService.getChatHistory(senderId, receiverId, pageNum, pageSize);
    }

    /**
     * 获取会话列表
     */
    @ApiOperation("获取会话列表")
    @GetMapping("/sessions")
    public AjaxResult getSessions(@RequestParam String userId) {
        // 无需指定泛型，AjaxResult内部会通过DATA_TAG存储数据
        return AjaxResult.success(chatMessageService.getChatSessions(userId));
    }

    /**
     * 标记消息已读
     */
    @ApiOperation("标记消息为已读")
    @PostMapping("/markRead")
    public AjaxResult markAsRead(
            @RequestParam String userId,
            @RequestParam String targetId) {
        return chatMessageService.markAsRead(userId, targetId) ? AjaxResult.success() : AjaxResult.error("标记失败");
    }
}