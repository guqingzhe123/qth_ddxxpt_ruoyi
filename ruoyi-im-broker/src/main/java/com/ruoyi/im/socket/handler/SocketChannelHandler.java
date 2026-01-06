package com.ruoyi.im.socket.handler;

import com.ruoyi.im.socket.config.RedisExtendTemplate;
import com.ruoyi.im.socket.constant.ChannelAttrKey;
import com.ruoyi.im.socket.constant.SocketRedisKey;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.ChatMessage;
import com.ruoyi.im.socket.model.RecvSimpleInfo;
import com.ruoyi.im.socket.model.SendInfo;
import com.ruoyi.im.socket.model.UserInfo;
import com.ruoyi.im.socket.processor.AbstractMessageProcessor;
import com.ruoyi.im.socket.processor.ProcessorFactory;
import com.ruoyi.im.socket.utils.SpringContextHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p> 聊天消息处理器 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
public class SocketChannelHandler extends SimpleChannelInboundHandler<SendInfo> {

    /**
     * 读取消息
     *
     * @param ctx   channel上下文
     * @param sendInfo 消息内容
     * @throws Exception 抛出异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendInfo sendInfo) throws Exception {
        //判断是否可以写
        if (ctx.channel().isActive() && ctx.channel().isWritable()) {
            // 创建处理器进行处理
            AbstractMessageProcessor processor = ProcessorFactory.createProcessor(CmdTypeEnum.fromCode(sendInfo.getCmd()));
            if (processor == null) {
                // 如果处理器不存在，直接关闭连接
                ctx.channel().close();
                log.error("{}连接, 处理器不存在，直接关闭连接", ctx.channel().id().asLongText());
                return;
            }
           //log.error("现在有的通道1"+ UserChannelCtxMap.getChannelCtx());
            if(sendInfo.getCmd()==3||sendInfo.getCmd()==4){
                RecvSimpleInfo recvInfo = transForm(sendInfo.getData());
                //processor.process(ctx, recvInfo);
                //Object o = processor.transForm(sendInfo.getData());
                processor.process(ctx, recvInfo);
            }else {
                Object o = processor.transForm(sendInfo.getData());
                processor.process(ctx, o);
            }

        }

//        try {
//            // 1. 校验SendInfo是否为空
//            if (sendInfo == null) {
//                log.error("收到空的SendInfo消息，忽略处理");
//                return;
//            }
//            log.error("现在有的通道1"+ UserChannelCtxMap.getChannelCtx());
//
//            // 2. 获取对应的处理器
//            AbstractMessageProcessor processor = ProcessorFactory.createProcessor(CmdTypeEnum.fromCode(sendInfo.getCmd()));
//            if (processor == null) {
//                log.warn("未找到cmd={}对应的处理器，忽略消息", sendInfo.getCmd());
//                return;
//            }
//
//            // 3. 转换消息格式（修复空指针的核心步骤）
//            RecvSimpleInfo recvInfo = transForm(sendInfo.getData());
//
//            // 4. 校验转换结果是否为空
//            if (recvInfo == null) {
//                log.error("消息转换失败，transForm返回null，cmd={}", sendInfo.getCmd());
//                return;
//            }
//
//            // 5. 处理消息
//            processor.process(ctx, recvInfo);
//        } catch (Exception e) {
//            log.error("{}连接, 处理消息异常：", ctx.channel().id(), e);
//        }

//        //判断是否可以写
//        if (ctx.channel().isActive() && ctx.channel().isWritable()) {
//            // 创建处理器进行处理
//            AbstractMessageProcessor processor = ProcessorFactory.createProcessor(CmdTypeEnum.fromCode(sendInfo.getCmd()));
//            if (processor == null) {
//                // 如果处理器不存在，直接关闭连接
//                ctx.channel().close();
//                log.error("{}连接, 处理器不存在，直接关闭连接", ctx.channel().id().asLongText());
//                return;
//            }
//            if(sendInfo.getData() !=null){
//                RecvSimpleInfo recv=transForm(sendInfo.getData());
//                log.error("传过来消息"+sendInfo.getData());
//                processor.process(ctx, recv);
//            }
//        }
    }
    public RecvSimpleInfo transForm(Object obj) throws InstantiationException, IllegalAccessException {
        AbstractMessageProcessor processor = ProcessorFactory.createProcessor(CmdTypeEnum.fromCode(3));

        // 先通过父类方法进行基础转换
        RecvSimpleInfo recvInfo = (RecvSimpleInfo) processor.transForm(obj, RecvSimpleInfo.class.newInstance());

        // 处理 sender：前端传递的是字符串ID，转为 UserInfo 对象
        if (obj instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) obj;
            // 前端 sender 是字符串ID，手动封装为 UserInfo
            Object senderObj = dataMap.get("senderId");
            if (senderObj instanceof String) {
                UserInfo sender = new UserInfo();
                sender.setId((String) senderObj); // 假设 UserInfo 有 setId 方法
                recvInfo.setSender(sender);
            }

            // 二次确认 receiver 转列表（防止前端偶尔传列表的情况）
            Object receiverObj = dataMap.get("receiverId");
            if (receiverObj instanceof String && (recvInfo.getReceivers() == null || recvInfo.getReceivers().isEmpty())) {
                // 单个字符串转为列表（兼容 Java 8+）
                List<String> receivers = new ArrayList<>();
                receivers.add((String) receiverObj);
                recvInfo.setReceivers(receivers);
            }
            recvInfo.setCmd(3);
            recvInfo.setSendResult(false);
            recvInfo.setServiceName("不知道干什么");

            ChatMessage msg=new ChatMessage();
            if(((Map<?, ?>) obj).size()>0){
                msg.setId(dataMap.get("id").toString());
                msg.setSenderId(dataMap.get("senderId").toString());
                msg.setReceiverId(dataMap.get("receiverId").toString());
                msg.setContent(dataMap.get("content").toString());
                msg.setMessageType((Integer) dataMap.get("messageType"));

                msg.setStatus(0);
                recvInfo.setData(msg);
            }else {
                recvInfo.setData(msg);
            }



        }

        return recvInfo;
    }
    /**
     * 出现异常的处理 打印报错日志
     *
     * @param ctx   channel上下文
     * @param cause 异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 判断异常类型
        if (cause instanceof DecoderException) {
            ctx.channel().close();
            log.error("{}连接, 解码异常：", ctx.channel().id().asLongText(), cause);
        } else if (cause instanceof IOException) {
            ctx.channel().close();
            log.error("{}连接, IO异常：", ctx.channel().id().asLongText(), cause);
        } else {
            log.error("{}连接, 处理消息异常：", ctx.channel().id().asLongText(), cause);
        }
    }

    /**
     * 监控客户端上线
     *
     * @param ctx channel上下文
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("{}连接", ctx.channel().id().asLongText());
    }

    /**
     * 监控客户端离开
     *
     * @param ctx channel上下文
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        AttributeKey<String> userIdAttr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
        String userId = ctx.channel().attr(userIdAttr).get();
        AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
        String token = ctx.channel().attr(tokenAttr).get();
        ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId, token);
        // 判断一下，避免异地登录导致的误删
        if (context != null && ctx.channel().id().equals(context.channel().id())) {
            // 移除channel
            UserChannelCtxMap.removeChannelCtx(userId, token);
            RedisExtendTemplate redisExtendTemplate = SpringContextHolder.getBean(RedisExtendTemplate.class);
            if (redisExtendTemplate == null) {
                log.error("redisExtendTemplate is null, cannot remove user channel context");
                return;
            }
            // 用户下线
            String key = String.join(":", SocketRedisKey.IM_USER_SERVER_ID, userId, token);
            redisExtendTemplate.delete(key);
            log.error("断开连接, channelId:{},userId:{},token:{}", ctx.channel().id().asLongText(), userId, token);
        }
    }

    /**
     * 心跳超时，自动断开连接
     *
     * @param ctx channel上下文
     * @param evt 触发的事件
     * @throws Exception 抛出异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                // 在规定时间内没有收到客户端的上行数据, 主动断开连接
                AttributeKey<String> attr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
                String userId = ctx.channel().attr(attr).get();
                AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
                String token = ctx.channel().attr(tokenAttr).get();
                log.error("心跳超时，即将断开连接，用户id:{}，token:{}", userId, token);
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
