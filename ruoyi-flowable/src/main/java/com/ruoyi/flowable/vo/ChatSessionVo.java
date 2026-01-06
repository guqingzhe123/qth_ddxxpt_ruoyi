package com.ruoyi.flowable.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 聊天会话视图对象
 *
 * @author ruoyi
 */
@Data
public class ChatSessionVo {
    /** 目标用户ID */
    private String id;

    /** 最后一条消息内容 */
    private String lastMsgContent;

    /** 最后一条消息时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastMsgTime;

    /** 最后一条消息类型 */
    private Integer lastMsgType;

    /** 未读消息数量 */
    private Integer unreadCount;

    /** 姓名 */
    private String username;

    /** 头像 */
    private String avatar;

    /** 在线状态 */
    private String online;
}