package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SubLongmeiPlanInput;
import com.ruoyi.system.mapper.SubLongmeiPlanInputMapper;
import com.ruoyi.system.service.ISubLongmeiPlanInputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 龙煤计划录入子Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class SubLongmeiPlanInputServiceImpl implements ISubLongmeiPlanInputService {
    @Autowired
    private SubLongmeiPlanInputMapper subLongmeiPlanInputMapper;

    /**
     * 查询龙煤计划录入子
     *
     * @param id 龙煤计划录入子主键
     * @return 龙煤计划录入子
     */
    @Override
    public SubLongmeiPlanInput getSubLongmeiPlanInputById(Long id) {
        return subLongmeiPlanInputMapper.selectSubLongmeiPlanInputById(id);
    }

    /**
     * 查询龙煤计划录入子列表
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 龙煤计划录入子
     */
    @Override
    public List<SubLongmeiPlanInput> listSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput) {
        return subLongmeiPlanInputMapper.selectSubLongmeiPlanInputList(subLongmeiPlanInput);
    }

    /**
     * 新增龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    @Override
    public int saveSubLongmeiPlanInput(List<SubLongmeiPlanInput> subLongmeiPlanInput) {
        return subLongmeiPlanInputMapper.insertSubLongmeiPlanInputBatch(subLongmeiPlanInput);
    }

    /**
     * 修改龙煤计划录入子
     *
     * @param subLongmeiPlanInput 龙煤计划录入子
     * @return 结果
     */
    @Override
    public int updateSubLongmeiPlanInput(SubLongmeiPlanInput subLongmeiPlanInput) {
        return subLongmeiPlanInputMapper.updateSubLongmeiPlanInput(subLongmeiPlanInput);
    }

    /**
     * 批量删除龙煤计划录入子
     *
     * @param ids 需要删除的龙煤计划录入子主键
     * @return 结果
     */
    @Override
    public int deleteSubLongmeiPlanInputByIds(Long[] ids) {
        return subLongmeiPlanInputMapper.deleteSubLongmeiPlanInputByIds(ids);
    }

    /**
     * 删除龙煤计划录入子信息
     *
     * @param id 龙煤计划录入子主键
     * @return 结果
     */
    @Override
    public int deleteSubLongmeiPlanInputById(Long id) {
        return subLongmeiPlanInputMapper.deleteSubLongmeiPlanInputById(id);
    }
    /**
     * 删除龙煤计划录入子信息
     *
     * @param planId 龙煤计划录入子主键
     * @return 结果
     */
    @Override
    public int deleteSubLongmeiPlanInputByPlanId(Long planId) {
        return subLongmeiPlanInputMapper.deleteSubLongmeiPlanInputByPlanId(planId);
    }


}
