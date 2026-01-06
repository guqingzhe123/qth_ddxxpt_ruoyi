package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    ChatMessage selectById(@Param("id") Long id);
    List<ChatMessage> selectList(ChatMessage query); // 支持按 roomId/senderId/receiverId 条件查询
    int insert(ChatMessage m);
    int markRead(@Param("roomId") String roomId,
                 @Param("receiverId") String receiverId,
                 @Param("untilId") Long untilId);
    int deleteById(@Param("id") Long id);
}
