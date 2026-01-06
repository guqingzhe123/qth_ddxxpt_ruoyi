package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ChatMessage;
import com.ruoyi.system.mapper.BaoBiao.ChatMessageMapper;
import com.ruoyi.system.service.BaoBiao.IChatMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements IChatMessageService {

    @Resource
    private ChatMessageMapper chatMessageMapper;

    @Override
    public ChatMessage get(Long id) {
        return chatMessageMapper.selectById(id);
    }

    @Override
    public List<ChatMessage> list(ChatMessage query) {
        return chatMessageMapper.selectList(query);
    }

    @Override
    public int sendText(String senderId, String receiverId, String content) {
        ChatMessage m = new ChatMessage();
        m.setSenderId(senderId);
        m.setReceiverId(receiverId);
        m.setRoomId(IChatMessageService.computeRoomId(senderId, receiverId));
        m.setMsgType("TEXT");
        m.setContent(content);
        m.setIsRead(0);
        m.setCreateTime(new Date());
        return chatMessageMapper.insert(m);
    }

    @Override
    public int sendFile(ChatMessage m) {
        // 要求 m 里必须已经设好 senderId/receiverId/fileUrl/fileName/fileSize/mimeType
        m.setRoomId(IChatMessageService.computeRoomId(m.getSenderId(), m.getReceiverId()));
        m.setMsgType(m.getMsgType() == null ? "FILE" : m.getMsgType());
        m.setIsRead(0);
        m.setCreateTime(new Date());
        return chatMessageMapper.insert(m);
    }

    @Override
    public int markRead(String roomId, String receiverId, Long untilId) {
        return chatMessageMapper.markRead(roomId, receiverId, untilId);
    }
}
