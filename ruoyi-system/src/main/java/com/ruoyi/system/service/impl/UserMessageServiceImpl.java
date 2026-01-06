package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.IUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【用户消息提示表】Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-16
 */
@Service
public class UserMessageServiceImpl implements IUserMessageService {
    @Autowired
    private UserMessageMapper userMessageMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserMessage getUserMessageById(Long id) {
        return userMessageMapper.selectUserMessageById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userMessage 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserMessage> listUserMessage(UserMessage userMessage) {
        return userMessageMapper.selectUserMessageList(userMessage);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int saveUserMessage(UserMessage userMessage) {
        return userMessageMapper.insertUserMessage(userMessage);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserMessage(UserMessage userMessage) {
        return userMessageMapper.updateUserMessage(userMessage);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserMessageByIds(Long[] ids) {
        return userMessageMapper.deleteUserMessageByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserMessageById(Long id) {
        return userMessageMapper.deleteUserMessageById(id);
    }
}
