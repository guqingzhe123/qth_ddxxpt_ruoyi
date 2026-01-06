package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MinePlanDay;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 【日计划数存储位置】Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-30
 */
public interface MinePlanDayMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MinePlanDay selectMinePlanDayById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MinePlanDay> selectMinePlanDayList(MinePlanDay minePlanDay);
    /**
     * 查询【请填写功能名称】列表
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MinePlanDay> selectMinePlanCumulativeDayList(MinePlanDay minePlanDay);


    /**
     * 查询【请填写功能名称】列表
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MinePlanDay> selectMinePlanMonth(MinePlanDay minePlanDay);
    /**
     * 新增【请填写功能名称】
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 结果
     */
    public int insertMinePlanDay(List<MinePlanDay> minePlanDay);

    /**
     * 修改【请填写功能名称】
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 结果
     */
    public int updateMinePlanDay(MinePlanDay minePlanDay);

    /**
     * 修改【请填写功能名称】
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 结果
     */
    public int batchUpdateMinePlanDay(@Param("list") List<MinePlanDay> list);




    /**
     * 修改【请填写功能名称】
     *
     * @param minePlanDay 【请填写功能名称】
     * @return 结果
     */
    public int updateStatePlanDay(MinePlanDay minePlanDay);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMinePlanDayById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMinePlanDayByIds(Long[] ids);
}
