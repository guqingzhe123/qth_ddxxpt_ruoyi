package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.EnteringAndExitingTheMine;

import java.util.List;

/**
 * 人员入井出井日报录入Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface EnteringAndExitingTheMineMapper {
    /**
     * 查询人员入井出井日报录入
     *
     * @param id 人员入井出井日报录入主键
     * @return 人员入井出井日报录入
     */
    public EnteringAndExitingTheMine selectEnteringAndExitingTheMineById(Long id);

    /**
     * 查询人员入井出井日报录入列表
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 人员入井出井日报录入集合
     */
    public List<EnteringAndExitingTheMine> selectEnteringAndExitingTheMineList(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 新增人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    public int insertEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 修改人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    public int updateEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine);

    /**
     * 删除人员入井出井日报录入
     *
     * @param id 人员入井出井日报录入主键
     * @return 结果
     */
    public int deleteEnteringAndExitingTheMineById(Long id);

    /**
     * 批量删除人员入井出井日报录入
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnteringAndExitingTheMineByIds(Long[] ids);
}
