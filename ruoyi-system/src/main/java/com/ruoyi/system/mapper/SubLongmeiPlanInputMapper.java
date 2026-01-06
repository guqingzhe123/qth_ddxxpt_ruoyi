package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SubLongmeiPlanInput;

import java.util.List;

/**
 * 龙煤计划录入子Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-06
 */
public interface SubLongmeiPlanInputMapper {
    /**
     * 查询龙煤计划录入子
     *
     * @param id 龙煤计划录入子主键
     * @return 龙煤计划录入子
     */
    public SubLongmeiPlanInput selectSubLongmeiPlanInputById(Long id);

    /**
     * 查询龙煤计划录入子列表
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 龙煤计划录入子集合
     */
    public List<SubLongmeiPlanInput> selectSubLongmeiPlanInputList(SubLongmeiPlanInput subLongmeiPlanInput);

    /**
     * 新增龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    public int insertSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput);
    /**
     * 新增龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    public int insertSubLongmeiPlanInputBatch(List<SubLongmeiPlanInput> subLongmeiPlanInput);

    /**
     * 修改龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    public int updateSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput);

    /**
     * 删除龙煤计划录入子
     *
     * @param id 龙煤计划录入子主键
     * @return 结果
     */
    public int deleteSubLongmeiPlanInputById(Long id);

    /**
     * 批量删除龙煤计划录入子
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubLongmeiPlanInputByIds(Long[] ids);
    /**
     * 删除龙煤计划录入子
     *
     * @param id 龙煤计划录入子主键
     * @return 结果
     */
    public int deleteSubLongmeiPlanInputByPlanId(Long coalPlantStorageId);

}
