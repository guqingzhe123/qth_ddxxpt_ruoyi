// ruoyi-im-process/src/main/java/com/ruoyi/im/chat/service/IChatMessageProcessService.java
package com.ruoyi.im.chat.service;


import com.ruoyi.im.socket.model.ChatMessage;
import org.springframework.stereotype.Service;

/**
 * 聊天消息处理服务接口
 * 用于定义消息持久化、状态更新等核心业务方法
 */
@Service
public interface IChatMessageProcessService {

    /**
     * 保存聊天消息到数据库
     * @param message 聊天消息对象
     */
    void saveChatMessage(ChatMessage message);

    /**
     * 更新消息状态（如：未读→已读）
     * @param messageId 消息唯一ID
     * @param status 状态值（0-未读，1-已读）
     */
    void updateMessageStatus(String messageId, String status);
}