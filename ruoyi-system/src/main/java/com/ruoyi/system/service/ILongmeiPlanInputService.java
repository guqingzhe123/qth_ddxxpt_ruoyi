package com.ruoyi.system.service;

import com.ruoyi.system.domain.LongmeiPlanInput;

import java.util.List;

/**
 * 龙煤计划录入Service接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ILongmeiPlanInputService {
    /**
     * 查询龙煤计划录入
     *
     * @param id 龙煤计划录入主键
     * @return 龙煤计划录入
     */
    public LongmeiPlanInput getLongmeiPlanInputById(Long id);

    /**
     * 查询龙煤计划录入列表
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 龙煤计划录入集合
     */
    public List<LongmeiPlanInput> listLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput);

    /**
     * 新增龙煤计划录入
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 结果
     */
    public int saveLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput);

    /**
     * 修改龙煤计划录入
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 结果
     */
    public int updateLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput);

    /**
     * 批量删除龙煤计划录入
     *
     * @param ids 需要删除的龙煤计划录入主键集合
     * @return 结果
     */
    public int deleteLongmeiPlanInputByIds(Long[] ids);

    /**
     * 删除龙煤计划录入信息
     *
     * @param id 龙煤计划录入主键
     * @return 结果
     */
    public int deleteLongmeiPlanInputById(Long id);
}
