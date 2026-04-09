package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MineInfo;

import java.util.List;

/**
 * 退回Mapper接口
 *
 * @author ruoyi
 * @date 2026-04-08
 */
public interface MineInfoMapper {
    /**
     * 查询退回
     *
     * @param id 退回主键
     * @return 退回
     */
    public MineInfo selectMineInfoById(Long id);

    /**
     * 查询退回列表
     *
     * @param mineInfo 退回
     * @return 退回集合
     */
    public List<MineInfo> selectMineInfoList(MineInfo mineInfo);

    /**
     * 新增退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    public int insertMineInfo(MineInfo mineInfo);

    /**
     * 修改退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    public int updateMineInfo(MineInfo mineInfo);

    /**
     * 删除退回
     *
     * @param id 退回主键
     * @return 结果
     */
    public int deleteMineInfoById(Long id);

    /**
     * 批量删除退回
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMineInfoByIds(Long[] ids);

    /**
     * 删除退回
     *
     * @param 对应字段
     * @return 结果
     */
    public int deleteMineInfoByDate(MineInfo mineInfo);

}
