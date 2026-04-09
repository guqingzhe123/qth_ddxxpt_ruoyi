package com.ruoyi.system.service;

import com.ruoyi.system.domain.MineInfo;

import java.util.List;

/**
 * 退回Service接口
 *
 * @author ruoyi
 * @date 2026-04-08
 */
public interface IMineInfoService {
    /**
     * 查询退回
     *
     * @param id 退回主键
     * @return 退回
     */
    public MineInfo getMineInfoById(Long id);

    /**
     * 查询退回列表
     *
     * @param mineInfo 退回
     * @return 退回集合
     */
    public List<MineInfo> listMineInfo(MineInfo mineInfo);

    /**
     * 新增退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    public int saveMineInfo(MineInfo mineInfo);

    /**
     * 修改退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    public int updateMineInfo(MineInfo mineInfo);

    /**
     * 批量删除退回
     *
     * @param ids 需要删除的退回主键集合
     * @return 结果
     */
    public int deleteMineInfoByIds(Long[] ids);

    /**
     * 删除退回信息
     *
     * @param id 退回主键
     * @return 结果
     */
    public int deleteMineInfoById(Long id);

    /**
     * 删除退回信息
     *
     * @param id 对应字段
     * @return 结果
     */
    public int deleteMineInfoByDate(MineInfo mineInfo);

}
