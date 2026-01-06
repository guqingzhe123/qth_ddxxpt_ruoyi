package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserMessage;

import java.util.List;

/**
 * 【用户消息提示表】Service接口
 *
 * @author ruoyi
 * @date 2025-11-16
 */
public interface IUserMessageService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserMessage getUserMessageById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userMessage 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserMessage> listUserMessage(UserMessage userMessage);

    /**
     * 新增【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    public int saveUserMessage(UserMessage userMessage);

    /**
     * 修改【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    public int updateUserMessage(UserMessage userMessage);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserMessageByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserMessageById(Long id);
}
