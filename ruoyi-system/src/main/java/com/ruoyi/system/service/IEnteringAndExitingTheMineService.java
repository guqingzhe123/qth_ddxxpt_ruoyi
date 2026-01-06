package com.ruoyi.system.service;

import com.ruoyi.system.domain.EnteringAndExitingTheMine;

import java.util.List;

/**
 * 人员入井出井日报录入Service接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IEnteringAndExitingTheMineService {
    /**
     * 查询人员入井出井日报录入
     *
     * @param id 人员入井出井日报录入主键
     * @return 人员入井出井日报录入
     */
    public EnteringAndExitingTheMine getEnteringAndExitingTheMineById(Long id);

    /**
     * 查询人员入井出井日报录入列表
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 人员入井出井日报录入集合
     */
    public List<EnteringAndExitingTheMine> listEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 新增人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    public int saveEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 修改人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    public int updateEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 批量删除人员入井出井日报录入
     *
     * @param ids 需要删除的人员入井出井日报录入主键集合
     * @return 结果
     */
    public int deleteEnteringAndExitingTheMineByIds(Long[] ids);

    /**
     * 删除人员入井出井日报录入信息
     *
     * @param id 人员入井出井日报录入主键
     * @return 结果
     */
    public int deleteEnteringAndExitingTheMineById(Long id);
}
