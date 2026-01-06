package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.SystemMessage;
import com.ruoyi.flowable.vo.ChatSessionVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * im系统消息Mapper接口
 * 
 * @author wocurr.com
 */
public interface SystemMessageMapper {
    /**
     * 查询im系统消息
     * 
     * @param id im系统消息主键
     * @return im系统消息
     */
    public SystemMessage selectSystemMessageById(String id);

    /**
     * 查询im系统消息列表
     * 
     * @param systemMessage im系统消息
     * @return im系统消息集合
     */
    public List<SystemMessage> selectSystemMessageList(SystemMessage systemMessage);

    /**
     * 新增im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    public int insertSystemMessage(SystemMessage systemMessage);

    /**
     * 修改im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    public int updateSystemMessage(SystemMessage systemMessage);

    /**
     * 删除im系统消息
     * 
     * @param id im系统消息主键
     * @return 结果
     */
    public int deleteSystemMessageById(String id);

    /**
     * 批量删除im系统消息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSystemMessageByIds(String[] ids);


    /**
     * 查询会话列表
     *
     * @param userId 当前用户ID
     * @return 会话列表
     */
    List<ChatSessionVo> selectChatSessions(@Param("userId") String userId);

    /**
     * 查询会话列表
     *
     * @param userId 当前用户ID
     * @return 会话列表
     */
    //List<SystemMessage> selectSystemMessageByAllUser(@Param("userId") String userId);

}
