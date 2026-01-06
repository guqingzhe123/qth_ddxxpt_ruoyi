package com.ruoyi.system.service;

import com.ruoyi.system.domain.CokeSalesPlan;

import java.util.List;

/**
 * 焦炭销售计划录入Service接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ICokeSalesPlanService {
    /**
     * 查询焦炭销售计划录入
     *
     * @param id 焦炭销售计划录入主键
     * @return 焦炭销售计划录入
     */
    public CokeSalesPlan getCokeSalesPlanById(Long id);

    /**
     * 查询焦炭销售计划录入列表
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 焦炭销售计划录入集合
     */
    public List<CokeSalesPlan> listCokeSalesPlan(CokeSalesPlan cokeSalesPlan);

    /**
     * 新增焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    public int saveCokeSalesPlan(CokeSalesPlan cokeSalesPlan);

    /**
     * 修改焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    public int updateCokeSalesPlan(CokeSalesPlan cokeSalesPlan);

    /**
     * 批量删除焦炭销售计划录入
     *
     * @param ids 需要删除的焦炭销售计划录入主键集合
     * @return 结果
     */
    public int deleteCokeSalesPlanByIds(Long[] ids);

    /**
     * 删除焦炭销售计划录入信息
     *
     * @param id 焦炭销售计划录入主键
     * @return 结果
     */
    public int deleteCokeSalesPlanById(Long id);
}
