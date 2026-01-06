package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
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
public class UserMessageInput extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date messageTime;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 发送人id */
    @Excel(name = "发送人id")
    private String sender;

    /** 接收人id */
    @Excel(name = "接收人id")
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

}
