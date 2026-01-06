package com.ruoyi.im.chat.service.impl;

import com.ruoyi.flowable.domain.ImChatMessage;
import com.ruoyi.flowable.mapper.ImChatMessageMapper;
import com.ruoyi.im.chat.service.IChatMessageProcessService;
import com.ruoyi.im.socket.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IChatMessageProcessServiceImpl implements IChatMessageProcessService {



    @Autowired
    private ImChatMessageMapper imChatMessageMapper;
    /**
     * 保存聊天消息到数据库
     * @param message 聊天消息对象
     */

    @Override
    public void saveChatMessage(ChatMessage message) {

        ImChatMessage ImMessage=new ImChatMessage();
        ImMessage.setId(message.getId());
        ImMessage.setSenderId(message.getSenderId());
        ImMessage.setReceiverId(message.getReceiverId());
        ImMessage.setContent(message.getContent());
        ImMessage.setMsgType(message.getMessageType());

        imChatMessageMapper.insertImChatMessage(ImMessage);
    }
    /**
     * 更新消息状态（如：未读→已读）
     * @param senderId 发送者ID
     * @param receiverId 接收者id
     */
    @Override
    public void updateMessageStatus(String senderId, String receiverId) {
        imChatMessageMapper.updateMessageRead(senderId,receiverId);
    }
}
