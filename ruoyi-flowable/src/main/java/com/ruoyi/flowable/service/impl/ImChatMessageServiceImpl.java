package com.ruoyi.flowable.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.flowable.domain.ImChatMessage;
import com.ruoyi.flowable.mapper.ImChatMessageMapper;
import com.ruoyi.flowable.service.IImChatMessageService;
import com.ruoyi.flowable.vo.ChatSessionVo;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 聊天消息服务实现
 *
 * @author ruoyi
 */
@Service
public class ImChatMessageServiceImpl extends ServiceImpl<ImChatMessageMapper, ImChatMessage> implements IImChatMessageService {

    @Autowired
    private com.ruoyi.flowable.mapper.ImChatMessageMapper chatMessageMapper;
    @Autowired
    private RedisCache redisCacheOther;

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public boolean sendMessage(ImChatMessage message) {
        message.setMsgTime(DateUtils.getNowDate());
        message.setStatus(0); // 未读
        message.setCreateTime(DateUtils.getNowDate());
        return save(message);
    }

    @Override
    public TableDataInfo<ImChatMessage> getChatHistory(String senderId, String receiverId, int pageNum, int pageSize) {
        Page<ImChatMessage> page = new Page<>(pageNum, pageSize);
        int offset = (pageNum - 1) * pageSize;
        List<ImChatMessage> list = chatMessageMapper.selectChatHistory(senderId, receiverId, offset, pageSize);
        page.setRecords(list);
        page.setTotal(chatMessageMapper.selectChatHistory(senderId, receiverId, 0, Integer.MAX_VALUE).size());
        return new TableDataInfo<>(page.getRecords(), page.getTotal());
    }

    @Override
    public List<ChatSessionVo> getChatSessions(String userId) {
        List<ChatSessionVo> sessions = chatMessageMapper.selectChatSessions(userId);
        // 补充未读数量
        sessions.forEach(session -> {
            int unread = chatMessageMapper.countUnreadMessages(userId, session.getId());
            session.setUnreadCount(unread);

            String userName = sysUserMapper.selectUserIdByUserName(session.getId());
            session.setUsername(userName);

            try {
                //查到用户token
                List<String> tokenList = redisCacheOther.getCacheList("user_tokens:" + session.getId());
                if(tokenList.size()>0){
                    //通过token查询是否在线
                    String token= tokenList.get(tokenList.size() - 1);
                    LoginUser loginUser = redisCacheOther.getCacheObject("login_tokens:" + token);

                    if(loginUser==null){
                        session.setOnline("离线");
                    }else {
                        session.setOnline("在线");
                    }
                }else {
                    session.setOnline("在线");
                }

            }catch (Exception exception){
                session.setOnline("离线");
            }
        });
        return sessions;
    }

    @Override
    public boolean markAsRead(String userId, String targetId) {
        return chatMessageMapper.updateMessageRead(userId, targetId) > 0;
    }
}