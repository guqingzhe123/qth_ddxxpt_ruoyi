// ruoyi-im-process/src/main/java/com/ruoyi/im/chat/listener/ChatMessageStoreListener.java
package com.ruoyi.im.chat.listener;

import com.rabbitmq.client.Channel;
import com.ruoyi.im.chat.service.IChatMessageProcessService;
import com.ruoyi.im.chat.utils.JsonUtil;
import com.ruoyi.im.socket.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ChatMessageStoreListener {

    @Autowired
    private IChatMessageProcessService chatMessageProcessService;

    @RabbitListener(queues = "${chat.process.message.queue}")
    public void storeChatMessage(Channel channel, Message message) throws IOException {
        try {
            String json = new String(message.getBody(), StandardCharsets.UTF_8);
            ChatMessage chatMessage = JsonUtil.decode(json, ChatMessage.class);
            chatMessageProcessService.saveChatMessage(chatMessage);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("消息存储失败", e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}