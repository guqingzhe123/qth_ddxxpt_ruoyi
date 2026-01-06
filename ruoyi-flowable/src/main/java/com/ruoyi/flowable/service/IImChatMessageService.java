package com.ruoyi.flowable.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.ImChatMessage;
import com.ruoyi.flowable.vo.ChatSessionVo;

import java.util.List;

/**
 * 聊天消息服务接口
 *
 * @author ruoyi
 */
public interface IImChatMessageService extends IService<ImChatMessage> {

    /**
     * 发送消息
     *
     * @param message 消息对象
     * @return 结果
     */
    boolean sendMessage(ImChatMessage message);

    /**
     * 获取聊天记录
     *
     * @param senderId 发送者ID
     * @param receiverId 接收者ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 分页数据
     */
    TableDataInfo<ImChatMessage> getChatHistory(String senderId, String receiverId, int pageNum, int pageSize);

    /**
     * 获取会话列表
     *
     * @param userId 用户ID
     * @return 会话列表
     */
    List<ChatSessionVo> getChatSessions(String userId);

    /**
     * 标记消息为已读
     *
     * @param userId 用户ID
     * @param targetId 目标用户ID
     * @return 结果
     */
    boolean markAsRead(String userId, String targetId);
}