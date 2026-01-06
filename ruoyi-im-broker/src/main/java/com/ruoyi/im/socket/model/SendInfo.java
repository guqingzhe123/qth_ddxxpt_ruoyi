package com.ruoyi.im.socket.model;

import lombok.Data;

@Data
public class SendInfo<T> {

    /**
     * 命令
     */
    private Integer cmd;

    /**
     * 推送消息体
     */
    private T data;

    private String sender;
    private String receiver;
    private String messageId; // 消息唯一标识


}
