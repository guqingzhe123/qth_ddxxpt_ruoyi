package com.ruoyi.flowable.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 聊天消息实体类
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImChatMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private String id;

    /** 发送者ID */
    @Excel(name = "发送者ID")
    private String senderId;

    /** 发送者名称 */
    @Excel(name = "发送者名称")
    private String senderName;

    /** 接收者ID */
    @Excel(name = "接收者ID")
    private String receiverId;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 消息类型（1-文本 2-文件 3-图片） */
    @Excel(name = "消息类型")
    private Integer msgType;

    /** 消息状态（0-未读 1-已读） */
    @Excel(name = "消息状态")
    private Integer status;

    /** 消息时间 */
    @Excel(name = "消息时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date msgTime;

    /** 关联文件ID（msgType=2/3时使用） */
    @Excel(name = "关联文件ID")
    private String fileId;

    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("senderId", senderId)
                .append("receiverId", receiverId)
                .append("content", content)
                .append("msgType", msgType)
                .append("status", status)
                .append("msgTime", msgTime)
                .append("createTime", createTime)
                .toString();
    }
}