package com.ruoyi.im.socket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ChatMessage {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private Integer messageType; // 1-文本 2-文件
    private String fileId;
    private Integer status; // 0-未读 1-已读

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
}
