package com.ruoyi.system.service;

import com.ruoyi.system.domain.SubLongmeiPlanInput;

import java.util.List;

/**
 * 龙煤计划录入子Service接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ISubLongmeiPlanInputService {
    /**
     * 查询龙煤计划录入子
     *
     * @param id 龙煤计划录入子主键
     * @return 龙煤计划录入子
     */
    public SubLongmeiPlanInput getSubLongmeiPlanInputById(Long id);

    /**
     * 查询龙煤计划录入子列表
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 龙煤计划录入子集合
     */
    public List<SubLongmeiPlanInput> listSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput);

    /**
     * 新增龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    public int saveSubLongmeiPlanInput(List<SubLongmeiPlanInput> subLongmeiPlanInput);

    /**
     * 修改龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    public int updateSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput);

    /**
     * 批量删除龙煤计划录入子
     *
     * @param ids 需要删除的龙煤计划录入子主键集合
     * @return 结果
     */
    public int deleteSubLongmeiPlanInputByIds(Long[] ids);

    /**
     * 删除龙煤计划录入子信息
     *
     * @param id 龙煤计划录入子主键
     * @return 结果
     */
    public int deleteSubLongmeiPlanInputById(Long id);

    int deleteSubLongmeiPlanInputByPlanId(Long planId);
}
