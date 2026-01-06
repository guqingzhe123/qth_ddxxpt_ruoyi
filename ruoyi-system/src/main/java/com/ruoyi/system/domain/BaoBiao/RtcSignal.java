package com.ruoyi.system.domain.BaoBiao;

import lombok.Data;

@Data
public class RtcSignal {
    private Long id;            // 服务器生成的自增ID（内存自增即可）
    private String type;        // offer / answer / ice / hangup
    private String roomId;      // 房间ID（u:alice_bob）
    private String from;        // 发送者userId
    private String to;          // 接收者userId
    private String sdp;         // SDP（offer/answer）
    private String candidate;   // ICE candidate
    private String sdpMid;      // ICE: sdpMid
    private Integer sdpMLineIndex; // ICE: sdpMLineIndex
    private Long ts;            // 服务器接收时间戳
}
