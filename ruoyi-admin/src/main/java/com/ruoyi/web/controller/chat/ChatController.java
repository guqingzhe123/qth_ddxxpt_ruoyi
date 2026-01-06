package com.ruoyi.web.controller.chat;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.file.business.module.FileQO;
//import com.ruoyi.im.chat.domain.ChatMessage;
//import com.ruoyi.im.chat.model.FileChatMessage;
//import com.ruoyi.im.chat.service.IChatMessageService;
//import com.ruoyi.im.chat.service.IFileTransferService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 扩展ChatController.java
//@RestController
//@RequestMapping("/sysAdmin/chat")
//public class ChatController {
//
//    @Autowired
//    private IChatMessageService chatMessageService;
//
//    @Autowired
//    private IFileTransferService fileTransferService;
//
//    @ApiOperation("发送文本消息")
//    @PostMapping("/sendText")
//    public ServiceResult sendText(@RequestBody ChatMessage message) {
//        chatMessageService.saveChatMessage(message);
//        return ServiceResult.success();
//    }
//
//    @ApiOperation("上传聊天文件")
//    @PostMapping("/uploadFile")
//    public ServiceResult uploadFile(@ModelAttribute FileQO fileQO) {
//        return ServiceResult.success(fileTransferService.uploadChatFile(fileQO));
//    }
//
//    @ApiOperation("发送文件消息")
//    @PostMapping("/sendFile")
//    public ServiceResult sendFile(@RequestBody FileChatMessage message) {
//        fileTransferService.sendFileMessage(message);
//        return ServiceResult.success();
//    }
//
//    @ApiOperation("查询聊天记录")
//    @GetMapping("/history")
//    public TableDataInfo<ChatMessage> getHistory(
//            @RequestParam String senderId,
//            @RequestParam String receiverId,
//            @RequestParam int pageNum,
//            @RequestParam int pageSize) {
//        return chatMessageService.queryChatHistory(senderId, receiverId, pageNum, pageSize);
//    }
//
//    @ApiOperation("获取聊天对象列表")
//    @GetMapping("/targets")
//    public ServiceResult<List<String>> getChatTargets(@RequestParam String userId) {
//        return ServiceResult.success(chatMessageService.getChatTargets(userId));
//    }
//}
