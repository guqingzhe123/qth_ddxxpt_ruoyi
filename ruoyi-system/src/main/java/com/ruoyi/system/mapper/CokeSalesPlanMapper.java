package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CokeSalesPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 焦炭销售计划录入Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface CokeSalesPlanMapper {
    /**
     * 查询焦炭销售计划录入
     *
     * @param id 焦炭销售计划录入主键
     * @return 焦炭销售计划录入
     */
    public CokeSalesPlan selectCokeSalesPlanById(Long id);

    /**
     * 查询焦炭销售计划录入列表
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 焦炭销售计划录入集合
     */
    public List<CokeSalesPlan> selectCokeSalesPlanList(CokeSalesPlan cokeSalesPlan);

    /**
     * 新增焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    public int insertCokeSalesPlan(CokeSalesPlan cokeSalesPlan);

    /**
     * 修改焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    public int updateCokeSalesPlan(CokeSalesPlan cokeSalesPlan);

    /**
     * 删除焦炭销售计划录入
     *
     * @param id 焦炭销售计划录入主键
     * @return 结果
     */
    public int deleteCokeSalesPlanById(Long id);

    /**
     * 批量删除焦炭销售计划录入
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCokeSalesPlanByIds(Long[] ids);

    public CokeSalesPlan selectCokeSalesPlanDayList(@Param("planMonth")String planMonth);
    public CokeSalesPlan selectCokeSalesPlanYearList(@Param("planMonth")String planMonth);


}
