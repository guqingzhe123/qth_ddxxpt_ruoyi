package com.ruoyi.flowable.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flowable.domain.ImChatMessage;
import com.ruoyi.flowable.vo.ChatSessionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天消息数据层
 *
 * @author ruoyi
 */
public interface ImChatMessageMapper extends BaseMapper<ImChatMessage> {

    /**
     * 新增用户信息
     *
     * @param message 消息信息
     * @return 结果
     */
    public int insertImChatMessage(ImChatMessage message);

    /**
     * 查询聊天记录
     *
     * @param senderId 发送者ID
     * @param receiverId 接收者ID
     * @param offset 偏移量
     * @param limit 条数
     * @return 消息列表
     */
    List<ImChatMessage> selectChatHistory(
            @Param("senderId") String senderId,
            @Param("receiverId") String receiverId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    /**
     * 查询会话列表
     *
     * @param userId 当前用户ID
     * @return 会话列表
     */
    List<ChatSessionVo> selectChatSessions(@Param("userId") String userId);

    /**
     * 统计未读消息数
     *
     * @param userId 用户ID
     * @param targetId 目标用户ID
     * @return 未读数量
     */
    int countUnreadMessages(
            @Param("userId") String userId,
            @Param("targetId") String targetId);

    /**
     * 更新消息状态（如：未读→已读）
     * @param senderId 发送者ID
     * @param receiverId 接收者id
     */
    int updateMessageRead(
            @Param("senderId") String senderId,
            @Param("receiverId") String receiverId);
}
