package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.UserMessage;

import java.util.List;

/**
 * 【用户消息提示表】Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-16
 */
public interface UserMessageMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserMessage selectUserMessageById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userMessage 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserMessage> selectUserMessageList(UserMessage userMessage);

    /**
     * 新增【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    public int insertUserMessage(UserMessage userMessage);

    /**
     * 修改【请填写功能名称】
     *
     * @param userMessage 【请填写功能名称】
     * @return 结果
     */
    public int updateUserMessage(UserMessage userMessage);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserMessageById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserMessageByIds(Long[] ids);
}
