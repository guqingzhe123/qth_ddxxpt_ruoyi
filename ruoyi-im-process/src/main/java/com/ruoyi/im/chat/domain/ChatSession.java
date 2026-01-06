// ruoyi-socket/src/main/java/com/ruoyi/im/chat/model/ChatSession.java
package com.ruoyi.im.chat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 聊天会话信息
 */
@Data
public class ChatSession {
    /** 聊天对象ID（用户ID） */
    private String targetId;
    /** 聊天对象名称（用户名） */
    private String targetName;
    /** 最新消息内容 */
    private String lastMessage;
    /** 最新消息时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;
    /** 未读消息数量 */
    private Integer unreadCount;
    /** 消息类型（1-文本 2-文件） */
    private Integer lastMsgType;
}