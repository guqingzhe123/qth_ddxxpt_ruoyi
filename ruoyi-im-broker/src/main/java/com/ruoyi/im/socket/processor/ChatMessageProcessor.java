// ruoyi-im-broker/src/main/java/com/ruoyi/im/socket/processor/ChatMessageProcessor.java
package com.ruoyi.im.socket.processor;

import com.alibaba.fastjson.JSON;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.enums.SendCodeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.ChatMessage;
import com.ruoyi.im.socket.model.RecvSimpleInfo;
import com.ruoyi.im.socket.model.SendInfo;
import com.ruoyi.im.socket.utils.JsonUtil;
import com.ruoyi.im.socket.utils.RedisCache;
import com.ruoyi.im.socket.utils.SpringContextHolder;
import com.ruoyi.mq.service.RabbitService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class ChatMessageProcessor extends AbstractMessageProcessor<RecvSimpleInfo> {

    private RabbitService rabbitService;
    @Autowired
    private RedisCache redisCacheOther;

    @Override
    public void process(ChannelHandlerContext ctx, RecvSimpleInfo recvInfo) {

        log.info("接收聊天消息: 发送方={}, 接收方={}, 内容={}", recvInfo.getSender().getId(), recvInfo.getReceivers(), recvInfo.getData());

        ChatMessage message = (ChatMessage) recvInfo.getData();
         AtomicBoolean sendStatus = new AtomicBoolean(false);

        try {

            log.info("接收到消息发送方"+recvInfo.getSender().getId());
            log.info("接收到消息接收方"+recvInfo.getReceivers());
            // 遍历接收者列表
             for (String receId : recvInfo.getReceivers()) {

                log.error("现在有的通道2"+ UserChannelCtxMap.getChannelCtx());
                 List<String> tokenList = redisCacheOther.getCacheList("user_tokens:" + receId);
                // 获取接收者在线通道
                ChannelHandlerContext channelCtx = UserChannelCtxMap.getChannelCtx(receId, tokenList.get(tokenList.size() - 1));


                if (Objects.isNull(channelCtx)) {
                    log.warn("接收者[{}]通道不存在（可能未在线）", receId);
                    message.setStatus(0); // 未读
                } else if (!channelCtx.channel().isActive()) { // 检查通道是否活跃
                    log.warn("接收者[{}]通道已关闭", receId);
                    UserChannelCtxMap.removeChannelCtx(receId,null); // 清理无效通道
                    message.setStatus(0);
                } else {
                    // 构建发送消息
                    SendInfo<ChatMessage> sendInfo = new SendInfo<>();
                    sendInfo.setCmd(CmdTypeEnum.CHAT_MESSAGE.code());
                    sendInfo.setSender(recvInfo.getSender().getId());
                    sendInfo.setReceiver(receId);
                    sendInfo.setData(message);
                    channelCtx.channel().writeAndFlush(sendInfo);
                    //sendStatus = true;
                    message.setStatus(1); // 已读
                    log.info("准备发送消息给[{}]：{}", receId, JSON.toJSONString(sendInfo)); // 打印完整消息

                    channelCtx.channel().writeAndFlush(sendInfo).addListener(future -> {
                        if (future.isSuccess()) {
                            log.info("消息成功发送给[{}]", receId);
                            sendStatus.set(true);
                            message.setStatus(1); // 已读
                        } else {
                            log.error("消息发送给[{}]失败", receId, future.cause());
                            sendStatus.set(false);
                            message.setStatus(0); // 发送失败，标记为未读
                        }
                        // 发送结果确认后再持久化消息
                        persistentMessage(recvInfo, receId, sendStatus.get() ? SendCodeEnum.SUCCESS : SendCodeEnum.NOT_ONLINE, message);

                    });
                }
//                if (Objects.nonNull(channelCtx)) {
//
//                } else {
//                    message.setStatus(0); // 未读
//                    log.warn("接收者{}不在线，消息将标记为未读", receId);
//                }
                // 持久化消息
                //persistentMessage(recvInfo, receId, sendStatus ? SendCodeEnum.SUCCESS : SendCodeEnum.NOT_ONLINE, message);
            }
        } catch (Exception e) {
            log.error("聊天消息处理异常", e);
            persistentMessage(recvInfo, message.getReceiverId(), SendCodeEnum.UNKNOW_ERROR, message);
        }
    }

    private void persistentMessage(RecvSimpleInfo recvInfo, String receId, SendCodeEnum sendCode, ChatMessage message) {
        try {
            if (rabbitService == null) {
                rabbitService = SpringContextHolder.getBean(RabbitService.class);
            }
            // 发送到消息队列，由ruoyi-im-process处理持久化
            rabbitService.convertAndSend(
                    "chat.store.exchange",
                    "chat-store-router",
                    JsonUtil.encode(message)
            );
        } catch (Exception e) {
            log.error("消息持久化失败", e);
        }
    }
    // 在 ChatMessageProcessor 中重写 transForm 方法，处理单个接收者转换为列表
//    @Override
//    public RecvSimpleInfo transForm(Object obj) throws InstantiationException, IllegalAccessException {
//        // 先通过父类方法进行基础转换
//        RecvSimpleInfo recvInfo = super.transForm(obj, RecvSimpleInfo.class.newInstance());
//
//        // 处理 sender：前端传递的是字符串ID，转为 UserInfo 对象
//        if (obj instanceof Map) {
//            Map<String, Object> dataMap = (Map<String, Object>) obj;
//            // 前端 sender 是字符串ID，手动封装为 UserInfo
//            Object senderObj = dataMap.get("sender");
//            if (senderObj instanceof String) {
//                UserInfo sender = new UserInfo();
//                sender.setId((String) senderObj); // 假设 UserInfo 有 setId 方法
//                recvInfo.setSender(sender);
//            }
//
//            // 二次确认 receiver 转列表（防止前端偶尔传列表的情况）
//            Object receiverObj = dataMap.get("receiver");
//            if (receiverObj instanceof String && (recvInfo.getReceivers() == null || recvInfo.getReceivers().isEmpty())) {
//                // 单个字符串转为列表（兼容 Java 8+）
//                List<String> receivers = new ArrayList<>();
//                receivers.add((String) receiverObj);
//                recvInfo.setReceivers(receivers);
//            }
//        }
//
//        return recvInfo;
//    }
    @Override
    public RecvSimpleInfo transForm(Object obj) throws InstantiationException, IllegalAccessException {
        return transForm(obj, RecvSimpleInfo.class.newInstance());
    }
}