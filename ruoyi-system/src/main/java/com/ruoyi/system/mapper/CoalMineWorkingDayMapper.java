package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CoalMineWorkingDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作日Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-28
 */
public interface CoalMineWorkingDayMapper {
    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public CoalMineWorkingDay selectCoalMineWorkingDayById(Long id);
    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public CoalMineWorkingDay selectCoalMineWorkingDayByDay    (@Param("unitName") String unitName, @Param("workingMonth") String workingMonth, @Param("workingDays") int workingDays);
    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public CoalMineWorkingDay selectCoalMineWorkingDayByMonth(@Param("unitName") String unitName, @Param("workingMonth") String workingMonth, @Param("workingDays") int workingDays);
    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public CoalMineWorkingDay selectCoalMineWorkingMonth(@Param("workingMonth") String workingMonth, @Param("workingDays") int workingDays);

    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    public List<CoalMineWorkingDay> selectCoalMineWorkingDayByUnitName    (@Param("workingMonth") String workingMonth, @Param("workingDays") int workingDays);




    /**
     * 查询工作日列表
     *
     * @param coalMineWorkingDay 工作日
     * @return 工作日集合
     */
    public List<CoalMineWorkingDay> selectCoalMineWorkingDayList(CoalMineWorkingDay coalMineWorkingDay);

    /**
     * 新增工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    public int insertCoalMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay);

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
     * 修改工作日日计划和日目标
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    public int updateCoalMineWorkingDays(CoalMineWorkingDay coalMineWorkingDay);
    /**
     * 删除工作日
     *
     * @param id 工作日主键
     * @return 结果
     */
    public int deleteCoalMineWorkingDayById(Long id);

    /**
     * 批量删除工作日
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCoalMineWorkingDayByIds(Long[] ids);
}
