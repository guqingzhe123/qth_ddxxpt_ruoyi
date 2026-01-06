package com.ruoyi.im.socket.processor;


import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.utils.SpringContextHolder;

/**
 * 处理器工厂
 *
 * @author wocurr.com
 */
public class ProcessorFactory {

    public static AbstractMessageProcessor createProcessor(CmdTypeEnum cmd) {
        if (cmd == null) {
            return null;
        }
        switch (cmd) {
            case HEART_BEAT:
                return SpringContextHolder.getBean(HeartbeatProcessor.class);
            case SYSTEM_MESSAGE:
                return SpringContextHolder.getBean(SystemMessageProcessor.class);

            case CHAT_MESSAGE: // 新增普通聊天消息处理器
                return SpringContextHolder.getBean(ChatMessageProcessor.class);
            default:
                return null;
        }
    }

}
