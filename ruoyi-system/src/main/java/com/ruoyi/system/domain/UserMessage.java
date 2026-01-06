package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【用户消息提示表】对象 user_message
 *
 * @author ruoyi
 * @date 2025-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 发送时间 */
    private Date messageTime;

    /** 消息内容 */
    private String content;

    /** 发送人id */
    private String sender;

    /** 接收人id */
    private String receiver;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("messageTime", getMessageTime())
                .append("content", getContent())
                .append("sender", getSender())
                .append("receiver", getReceiver())
                .toString();
    }
    public UserMessage(String sender, String receiver, String content, Date messageTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.messageTime = messageTime;
    }
}
