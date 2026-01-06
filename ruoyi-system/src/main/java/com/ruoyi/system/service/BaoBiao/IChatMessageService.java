package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ChatMessage;
import java.util.List;

public interface IChatMessageService {
    ChatMessage get(Long id);

    List<ChatMessage> list(ChatMessage query); // 可配合分页

    int sendText(String senderId, String receiverId, String content);

    int sendFile(ChatMessage fileMsg); // 已经填好 file* 字段的对象

    int markRead(String roomId, String receiverId, Long untilId);

    static String computeRoomId(String a, String b) {
        if (a == null || b == null) return null;
        return (a.compareTo(b) <= 0) ? (a + "|" + b) : (b + "|" + a);
    }
}
