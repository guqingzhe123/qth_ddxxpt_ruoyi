package com.ruoyi.system.service;

import com.ruoyi.system.domain.CoalMineWorkingDay;

import java.util.List;

/**
 * 工作日Service接口
 *
 * @author ruoyi
 * @date 2025-11-28
 */
public interface ICoalMineWorkingDayService {
    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public CoalMineWorkingDay getCoalMineWorkingDayById(Long id);

    /**
     * 查询工作日列表
     *
     * @param coalMineWorkingDay 工作日
     * @return 工作日集合
     */
    public List<CoalMineWorkingDay> listCoalMineWorkingDay(CoalMineWorkingDay coalMineWorkingDay);

    /**
     * 新增工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    public int saveCoalMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay);

    /**
     * 修改工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    public int updateCoalMineWorkingDay(CoalMineWorkingDay coalMineWorkingDay);
    /**
     * 修改工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    public int batcupdateCoalMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay);
    /**
     * 批量删除工作日
     *
     * @param ids 需要删除的工作日主键集合
     * @return 结果
     */
    public int deleteCoalMineWorkingDayByIds(Long[] ids);

    /**
     * 删除工作日信息
     *
     * @param id 工作日主键
     * @return 结果
     */
    public int deleteCoalMineWorkingDayById(Long id);
}
